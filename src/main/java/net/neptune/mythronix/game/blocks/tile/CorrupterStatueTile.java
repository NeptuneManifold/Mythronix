package net.neptune.mythronix.game.blocks.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class CorrupterStatueTile extends TileEntity implements IAnimatable {

    public static boolean goodItem = false;
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public CorrupterStatueTile(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public CorrupterStatueTile() {
        this(ModTileEntities.CORRUPTER_STATUE_TILE.get());
    }

    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<CorrupterStatueTile>(this,"controller",0,this::predicate));
    }

    private PlayState predicate(AnimationEvent<CorrupterStatueTile> e) {
        if(goodItem){
            e.getController().setAnimation(new AnimationBuilder().addAnimation("animation.corrupter_statue.revive"));

            goodItem = false;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
