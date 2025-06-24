package net.neptune.mythronix.worldgen;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.worldgen.entities.ModEntityGeneration;
import net.neptune.mythronix.worldgen.flower.ModFlowerGeneration;
import net.neptune.mythronix.worldgen.ore.ModOreGeneration;
import net.neptune.mythronix.worldgen.tree.ModTreeGeneration;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent e) {
        ModOreGeneration.generateOres(e);
        ModFlowerGeneration.generateFlowers(e);
        ModTreeGeneration.generateTrees(e);
        ModEntityGeneration.generateEntities(e);
    }
}
