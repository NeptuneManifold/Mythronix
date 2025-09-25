package net.neptune.mythronix.game.blocks.tile;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.neptune.mythronix.game.entities.CorrupterEntity;
import net.neptune.mythronix.game.entities.ModEntityTypes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class CorrupterDoorTile extends TileEntity implements ITickableTileEntity, IAnimatable {
    private boolean goodItem;
    private int anim;

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public CorrupterDoorTile(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public CorrupterDoorTile() {
        this(ModTileEntities.CORRUPTER_DOOR_TILE.get());
    }

    public boolean isGoodItem() {
        return goodItem;
    }

    public void setGoodItem(boolean goodItem) {
        this.goodItem = goodItem;
        this.setChanged();

        if (!level.isClientSide) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.goodItem = nbt.getBoolean("GoodItem");
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        nbt.putBoolean("GoodItem", this.goodItem);
        return nbt;
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putBoolean("GoodItem", this.goodItem); // envoie le boolean
        return new SUpdateTileEntityPacket(this.worldPosition, 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT nbt = pkt.getTag();
        if (nbt != null) {
            this.goodItem = nbt.getBoolean("GoodItem"); // synchronise côté client
        }
    }

    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<CorrupterDoorTile>(this,"controller",0,this::predicate));
    }

    private PlayState predicate(AnimationEvent<CorrupterDoorTile> e) {
        if(isGoodItem()){
            e.getController().setAnimation(new AnimationBuilder().addAnimation("animation.door.open", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
        }

        return PlayState.CONTINUE;
    }



    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void tick() {
        if(isGoodItem()){
            if(this.anim >= 40){

                BlockPos left = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY(), this.getBlockPos().getZ());
                BlockPos right = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY(), this.getBlockPos().getZ());

                BlockPos middleCenter = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() + 1, this.getBlockPos().getZ());
                BlockPos middleLeft = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY() + 1, this.getBlockPos().getZ());
                BlockPos middleRight = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY() + 1, this.getBlockPos().getZ());

                BlockPos upCenter = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() + 2, this.getBlockPos().getZ());
                BlockPos upLeft = new BlockPos(this.getBlockPos().getX() + 1, this.getBlockPos().getY() + 2, this.getBlockPos().getZ());
                BlockPos upRight = new BlockPos(this.getBlockPos().getX() - 1, this.getBlockPos().getY() + 2, this.getBlockPos().getZ());

                this.getLevel().destroyBlock(this.getBlockPos(), false);

                this.getLevel().destroyBlock(left, false);
                this.getLevel().destroyBlock(right, false);

                this.getLevel().destroyBlock(middleRight, false);
                this.getLevel().destroyBlock(middleCenter, false);
                this.getLevel().destroyBlock(middleLeft, false);

                this.getLevel().destroyBlock(upCenter, false);
                this.getLevel().destroyBlock(upLeft, false);
                this.getLevel().destroyBlock(upRight, false);
            }

            this.anim++;
        }
    }
}
