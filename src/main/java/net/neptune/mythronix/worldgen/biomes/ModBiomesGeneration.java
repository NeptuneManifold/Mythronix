package net.neptune.mythronix.worldgen.biomes;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModBiomesGeneration {

    public static void generatesBiomes() {
        addBiome(ModBiomes.MAGIC_FOREST.get(), BiomeManager.BiomeType.WARM, 20, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MAGICAL);
        addBiome(ModBiomes.SPIRIT_PLAINS.get(), BiomeManager.BiomeType.WARM, 10, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.MAGICAL);
    }

    private static void addBiome(Biome biome, BiomeManager.BiomeType type, int weight, BiomeDictionary.Type... types){
        RegistryKey<Biome> key = RegistryKey.create(ForgeRegistries.Keys.BIOMES,
                Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome)));

        BiomeDictionary.addTypes(key, types);
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key, weight));
    }

}
