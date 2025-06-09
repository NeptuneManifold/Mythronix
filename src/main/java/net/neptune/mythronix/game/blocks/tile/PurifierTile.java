package net.neptune.mythronix.game.blocks.tile;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.neptune.mythronix.game.recipes.ModRecipeTypes;
import net.neptune.mythronix.game.recipes.types.PurifierRecipe;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;

public class PurifierTile extends TileEntity implements IAnimatable, ITickableTileEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private int timeBurn = 100;
    private int timeBurnCurrent = 0;
    private ItemStack outputItem;

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(4){
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if(slot == 3) return false;
                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 64;
            }
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot,stack)) return stack;

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public boolean isBurning;

    private <E extends TileEntity & IAnimatable>PlayState predicate(AnimationEvent<E> e){
        e.getController().transitionLengthTicks = 0;
        if(isBurning){
            e.getController().setAnimation(new AnimationBuilder().addAnimation("animation.purifier.purifing"));
        } else {
            Random rand = new Random();
            int i = rand.nextInt(5000);

            if(i == 1 && !(e.getController().getAnimationState() == AnimationState.Running)){
                e.getController().setAnimation(new AnimationBuilder().addAnimation("animation.purifier.idle"));
            } else {
                e.getController().clearAnimationCache();
            }
        }
        return PlayState.CONTINUE;
    }

    public PurifierTile(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public PurifierTile() {
        this(ModTileEntities.PURIFIER_TILE.get());
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<PurifierTile>(this,"controller",0,this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }


    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        if(nbt.contains("burnTime")){
            timeBurn = nbt.getInt("burnTime");
        }
        if(nbt.contains("currentBurnTime")) {
            timeBurnCurrent = nbt.getInt("currentBurnTime");
        }
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.putInt("burnTime", timeBurn);
        compound.putInt("currentBurnTime", timeBurnCurrent);
        compound.put("inv", itemHandler.serializeNBT());
        return super.save(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    public void craft(){
        Inventory inv = new Inventory(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<PurifierRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(ModRecipeTypes.PURIFIER_RECIPE, inv, level);

        recipe.ifPresent(iRecpe -> {
            ItemStack output = iRecpe.getResultItem();

            if(timeBurnCurrent == 0 && itemHandler.getStackInSlot(3).getCount() <= 63){
                if(itemHandler.getStackInSlot(3).sameItem(output) || itemHandler.getStackInSlot(3).isEmpty()){
                    outputItem = output;
                    itemHandler.extractItem(0,1, false);
                    itemHandler.extractItem(1,1, false);
                    itemHandler.extractItem(2,1, false);
                    isBurning = true;
                }
            }
        });
        if(isBurning){
            timeBurnCurrent++;

            if(timeBurnCurrent == timeBurn){
                craftTheItem(outputItem);
            }
        }
        setChanged();
    }

    private void craftTheItem(ItemStack output) {
        if(itemHandler.getStackInSlot(3).isEmpty()){
            itemHandler.setStackInSlot(3, output);
        } else {
            itemHandler.getStackInSlot(3).grow(1);
        }
        isBurning = false;
        timeBurnCurrent = 0;
    }

    @Override
    public void tick() {

        if(level.isClientSide) return;

        craft();

        setChanged();
        this.level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT tag = new CompoundNBT();

        tag.putBoolean("isBurning", isBurning);

        return new SUpdateTileEntityPacket(this.worldPosition, -1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT tag = pkt.getTag();

        isBurning = tag.getBoolean("isBurning");
    }
}
