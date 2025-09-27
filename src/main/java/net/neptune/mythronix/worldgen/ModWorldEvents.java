package net.neptune.mythronix.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.structures.ModStructures;
import net.neptune.mythronix.worldgen.entities.ModEntityGeneration;
import net.neptune.mythronix.worldgen.flower.ModFlowerGeneration;
import net.neptune.mythronix.worldgen.ore.ModOreGeneration;
import net.neptune.mythronix.worldgen.structures.ModStructuresGeneration;
import net.neptune.mythronix.worldgen.tree.ModTreeGeneration;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent e) {
        ModStructuresGeneration.generateStructures(e);
        ModOreGeneration.generateOres(e);
        ModFlowerGeneration.generateFlowers(e);
        ModTreeGeneration.generateTrees(e);
        ModEntityGeneration.generateEntities(e);
    }

    @SubscribeEvent
    public static void addDimensionalSpacing(final WorldEvent.Load e){
        if(e.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) e.getWorld();

            try {
                Method GETCODEC_METHOD =
                        ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_a_230347_a_");
                ResourceLocation cgRl = Registry.CHUNK_GENERATOR.getKey(
                        (Codec<? extends ChunkGenerator>)GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));

                if(cgRl != null && cgRl.getNamespace().equals("terraforged")) {
                    return;
                }
            } catch (Exception ex){
                LogManager.getLogger().error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator");
            }

            if(serverWorld.getChunkSource().generator instanceof FlatChunkGenerator && serverWorld.dimension().equals(ServerWorld.OVERWORLD)) {
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap =
                    new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
            tempMap.putIfAbsent(ModStructures.CORRUPTED_HOUSE.get(),
                    DimensionStructuresSettings.DEFAULTS.get(ModStructures.CORRUPTED_HOUSE.get()));
            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }
}
