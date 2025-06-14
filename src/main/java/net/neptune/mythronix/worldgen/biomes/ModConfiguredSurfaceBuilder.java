package net.neptune.mythronix.worldgen.biomes;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.neptune.mythronix.Main;

public class ModConfiguredSurfaceBuilder {

    public static ConfiguredSurfaceBuilder<?> MAGIC_FOREST = register("magic_forest",
            SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderConfig(
                    Blocks.GRASS_BLOCK.defaultBlockState(),
                    Blocks.DIRT.defaultBlockState(),
                    Blocks.SAND.defaultBlockState()
            )));

    public static ConfiguredSurfaceBuilder<?> SPIRIT_PLAINS = register("spirit_plains",
            SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderConfig(
                    Blocks.GRASS_BLOCK.defaultBlockState(),
                    Blocks.DIRT.defaultBlockState(),
                    Blocks.SAND.defaultBlockState()
            )));

    private static <SC extends ISurfaceBuilderConfig>ConfiguredSurfaceBuilder<SC> register(String id,
                                                                                           ConfiguredSurfaceBuilder<SC> csb){
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER,
                new ResourceLocation(Main.MODID, id), csb);
    }

}
