package net.neptune.mythronix.worldgen.structures;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.neptune.mythronix.game.structures.ModStructures;
import net.neptune.mythronix.worldgen.biomes.ModBiomes;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModStructuresGeneration {

    public static void generateStructures(final BiomeLoadingEvent e){
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, e.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(e.getName().toString().contains(ModBiomes.SPIRIT_PLAINS.get().getRegistryName().toString())) {
            List<Supplier<StructureFeature<?,?>>> structures = e.getGeneration().getStructures();

            structures.add(() -> ModStructures.CORRUPTED_HOUSE.get().configured(IFeatureConfig.NONE));
        }
    }
}
