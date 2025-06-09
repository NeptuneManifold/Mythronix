package net.neptune.mythronix.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.worldgen.flower.ModFlowerGeneration;
import net.neptune.mythronix.worldgen.ore.ModOreGeneration;
import net.neptune.mythronix.worldgen.tree.ModTreeGeneration;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Method;
import java.util.HashMap;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent e) {
        ModOreGeneration.generateOres(e);
        ModFlowerGeneration.generateFlowers(e);
        ModTreeGeneration.generateTrees(e);
    }
}
