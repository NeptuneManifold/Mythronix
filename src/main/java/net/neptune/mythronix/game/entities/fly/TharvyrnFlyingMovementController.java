package net.neptune.mythronix.game.entities.fly;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.util.math.MathHelper;
import net.neptune.mythronix.game.entities.TharvyrnEntity;

public class TharvyrnFlyingMovementController extends FlyingMovementController {
    private final int maxTurn;
    private final boolean hoversInPlace;

    public TharvyrnFlyingMovementController(MobEntity p_i225710_1_, int p_i225710_2_, boolean p_i225710_3_)
    {
        super(p_i225710_1_, p_i225710_2_, p_i225710_3_);
        this.maxTurn = p_i225710_2_;
        this.hoversInPlace = p_i225710_3_;
    }

    @Override
    public void tick() {
        if (this.operation == MovementController.Action.MOVE_TO) {
            this.operation = MovementController.Action.WAIT;
            this.mob.setNoGravity(true);

            double dx = this.wantedX - this.mob.getX();
            double dy = this.wantedY - this.mob.getY();
            double dz = this.wantedZ - this.mob.getZ();
            double distSq = dx * dx + dy * dy + dz * dz;

            if (distSq < 2.5e-7) {
                this.mob.setZza(0.0F);
                this.mob.setYya(0.0F);
                return;
            }

            // Rotation horizontale
            float targetYaw = (float)(MathHelper.atan2(dz, dx) * (180F / Math.PI)) - 90.0F;
            this.mob.yRot = this.rotlerp(this.mob.yRot, targetYaw, 90.0F);

            // Vitesse
            float speed = (float)(this.speedModifier *
                    (this.mob.isOnGround() ?
                            this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED) :
                            this.mob.getAttributeValue(Attributes.FLYING_SPEED)));

            this.mob.setSpeed(speed);

            // Déplacement horizontal
            this.mob.setZza(1.0F);

            // Rotation verticale + montée/descente
            double horizontalDist = MathHelper.sqrt(dx * dx + dz * dz);
            float targetPitch = (float)(-(MathHelper.atan2(dy, horizontalDist) * (180F / Math.PI)));
            this.mob.xRot = this.rotlerp(this.mob.xRot, targetPitch, this.maxTurn);

            if (Math.abs(dy) > 0.1) {
                this.mob.setYya(dy > 0 ? speed : -speed);
            } else {
                this.mob.setYya(0.0F);
            }
        } else {
            if (!this.hoversInPlace) {
                this.mob.setNoGravity(false);
            }
            this.mob.setZza(0.0F);
            this.mob.setYya(0.0F);
        }
    }
}
