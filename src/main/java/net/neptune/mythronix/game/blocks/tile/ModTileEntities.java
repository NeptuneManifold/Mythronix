package net.neptune.mythronix.game.blocks.tile;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.blocks.ModBlocks;
import net.neptune.mythronix.game.blocks.geckolib.CorrupterStatue;

public class ModTileEntities {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Main.MODID);

    public static final RegistryObject<TileEntityType<PurifierTile>> PURIFIER_TILE =
            TILE_ENTITIES.register("purifier_tile", () -> TileEntityType.Builder.of(
                    PurifierTile::new, ModBlocks.PURIFIER.get()).build(null));

    public static final RegistryObject<TileEntityType<CorrupterStatueTile>> CORRUPTER_STATUE_TILE =
            TILE_ENTITIES.register("corrupter_statue_tile", () -> TileEntityType.Builder.of(
                    CorrupterStatueTile::new, ModBlocks.CORRUPTER_STATUE.get()).build(null));

    public static void register(IEventBus bus){
        TILE_ENTITIES.register(bus);
    }
}
