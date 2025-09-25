package net.neptune.mythronix.game.blocks.tile;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.neptune.mythronix.game.items.ModItems;
import net.neptune.mythronix.game.recipes.ModRecipeTypes;
import net.neptune.mythronix.game.recipes.types.UnCorrupterRecipe;
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

public class UnCorrupterTile extends TileEntity implements IAnimatable, ITickableTileEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private int timeBurn = 180;
    private int timeBurnCurrent = 0;
    private ItemStack outputItem;

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(3){
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if(slot == 2) return false;

                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 64;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {

                if(!isItemValid(slot, stack)) return stack;

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    private boolean isBurning;

    public boolean isBurning() {
        return isBurning;
    }

    public void setIsBurning(boolean goodItem) {
        this.isBurning = goodItem;
        this.setChanged();

        if (!level.isClientSide) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    private <E extends TileEntity & IAnimatable>PlayState predicate(AnimationEvent<E> e){
        e.getController().transitionLengthTicks = 0;

        if(this.isBurning){
            e.getController().setAnimation(new AnimationBuilder().addAnimation("animation.uncorrupter.uncorrupting"));

            return PlayState.CONTINUE;
        } else {
            return PlayState.STOP;
        }

    }

    public UnCorrupterTile(TileEntityType<?> type) {
        super(type);
    }

    public UnCorrupterTile() {
        this(ModTileEntities.UNCORRUPTER_TILE.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));

        if(nbt.contains("burnTime")) {
            timeBurn = nbt.getInt("burnTime");
        }
        if(nbt.contains("currentBurnTime")){
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

    public void craft() {
        Inventory inv = new Inventory(itemHandler.getSlots());

        for(int i = 0; i < itemHandler.getSlots(); i++){
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        if(itemHandler.getStackInSlot(0).getItem().equals(ModItems.CORRUPTED_SWORD.get()) ||
                itemHandler.getStackInSlot(0).getItem().equals(ModItems.CORRUPTED_AXE.get()) ||
                        itemHandler.getStackInSlot(0).getItem().equals(ModItems.CORRUPTED_PICKAXE.get()) ||
                        itemHandler.getStackInSlot(0).getItem().equals(ModItems.CORRUPTED_HOE.get()) ||
                        itemHandler.getStackInSlot(0).getItem().equals(ModItems.CORRUPTED_SHOVEL.get())) {


            ItemStack oldItem = itemHandler.getStackInSlot(0);

            ItemStack output = ItemStack.of(oldItem.getOrCreateTag().getCompound("oldItem"));

            if(itemHandler.getStackInSlot(1).getItem().equals(ModItems.SOLIDIFIED_MANA.get())) {
                if(itemHandler.getStackInSlot(2).isEmpty()){
                    outputItem = output;
                    itemHandler.extractItem(0,1, false);
                    itemHandler.extractItem(1,1, false);

                    setIsBurning(true);
                }
            }

        } else {

            Optional<UnCorrupterRecipe> reciper = level.getRecipeManager()
                    .getRecipeFor(ModRecipeTypes.UNCORRUPTER_RECIPE, inv, level);

            reciper.ifPresent(iRecipe -> {
                ItemStack output = iRecipe.getResultItem();

                if(timeBurnCurrent == 0 && itemHandler.getStackInSlot(2).getCount() < output.getMaxStackSize()){
                    if(itemHandler.getStackInSlot(2).sameItem(output) || itemHandler.getStackInSlot(2).isEmpty()){
                        outputItem = output;

                        itemHandler.extractItem(0,1,false);
                        itemHandler.extractItem(1,1,false);

                        setIsBurning(true);
                    }
                }
            });

            if(isBurning()){
                timeBurnCurrent++;

                if(timeBurnCurrent == timeBurn){
                    craftTheItem(outputItem);
                }
            }

            setChanged();
        }
    }

    private void craftTheItem(ItemStack output){
        if(itemHandler.getStackInSlot(2).isEmpty()) {
            itemHandler.setStackInSlot(2, output);
        } else {
            itemHandler.getStackInSlot(2).grow(1);
        }

        setIsBurning(false);
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

        tag.putBoolean("isBurning", this.isBurning);

        return new SUpdateTileEntityPacket(this.worldPosition, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT tag = pkt.getTag();

        this.isBurning = tag.getBoolean("isBurning");
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<UnCorrupterTile>(this, "controller",0,this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
