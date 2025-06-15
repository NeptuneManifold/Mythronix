package net.neptune.mythronix.game.effects;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.neptune.mythronix.Main;

public class ModEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Main.MODID);

    public static final RegistryObject<Effect> FREEZE = EFFECTS.register("freeze", () -> new FreezeEffect()
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", (double) -0.25, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<Effect> MANA_BOOST = EFFECTS.register("mana_boost", () -> new ManaBoostEffect());

    public static void register(IEventBus bus){
        EFFECTS.register(bus);
    }
}
