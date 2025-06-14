package net.neptune.mythronix.worldgen.biomes;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.*;
import net.neptune.mythronix.Main;

import java.util.function.Supplier;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Main.MODID);

    public static final RegistryObject<Biome> MAGIC_FOREST = BIOMES.register("magic_forest",
            () -> makeMagicForest(() -> ModConfiguredSurfaceBuilder.MAGIC_FOREST, 0.001F, 0.25F));
    public static final RegistryObject<Biome> SPIRIT_PLAINS = BIOMES.register("spirit_plains",
            () -> makeSpiritPlains(() -> ModConfiguredSurfaceBuilder.SPIRIT_PLAINS));

    private static Biome makeMagicForest(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale){
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.farmAnimals(mobspawninfo$builder);
        DefaultBiomeFeatures.commonSpawns(mobspawninfo$builder);

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultCarvers(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addMossyStoneBlock(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultOres(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addForestGrass(biomegenerationsettings$builder);

        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN)
                .biomeCategory(Biome.Category.FOREST)
                .depth(depth)
                .scale(scale)
                .temperature(0.7F)
                .downfall(0.6F)
                .specialEffects((new BiomeAmbience.Builder())
                        .waterColor(0x0093ff)
                        .waterFogColor(0x0093ff)
                        .fogColor(0x0093ff)
                        .skyColor(0x00f7ff)
                        .grassColorOverride(0x00bcb3)
                        .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build())
                .mobSpawnSettings(mobspawninfo$builder.build())
                .generationSettings(biomegenerationsettings$builder.build())
                .build();
    }

    private static Biome makeSpiritPlains(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder){
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultCarvers(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addMossyStoneBlock(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultOres(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addPlainGrass(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addPlainVegetation(biomegenerationsettings$builder);

        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN)
                .biomeCategory(Biome.Category.PLAINS)
                .depth(0.125F).scale(0.08F)
                .temperature(0.8F).downfall(0.4F)
                .specialEffects((new BiomeAmbience.Builder())
                        .waterColor(0x0077b3).waterFogColor(0x45a6d8)
                        .fogColor(0x45a6d8).skyColor(calculateSkyColor(0.8F))
                        .grassColorOverride(0x36c9c0)
                        .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build())
                .mobSpawnSettings(mobspawninfo$builder.build())
                .generationSettings(biomegenerationsettings$builder.build())
                .build();
    }

    private static int calculateSkyColor(float pTemperature) {
        float lvt_1_1_ = pTemperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static void register(IEventBus bus){
        BIOMES.register(bus);
    }
}
