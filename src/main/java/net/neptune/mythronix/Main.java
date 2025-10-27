package net.neptune.mythronix;

import net.minecraft.block.WoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.neptune.mythronix.capability.packets.ModPackets;
import net.neptune.mythronix.game.ModCapabilities;
import net.neptune.mythronix.game.blocks.ModBlocks;
import net.neptune.mythronix.game.blocks.geckolib.render.CorrupterDoorRenderer;
import net.neptune.mythronix.game.blocks.geckolib.render.CorrupterStatueRenderer;
import net.neptune.mythronix.game.blocks.geckolib.render.PurifierRenderer;
import net.neptune.mythronix.game.blocks.geckolib.render.UnCorrupterRenderer;
import net.neptune.mythronix.game.blocks.tile.ModTileEntities;
import net.neptune.mythronix.game.effects.ModEffects;
import net.neptune.mythronix.game.entities.ModEntityTypes;
import net.neptune.mythronix.game.entities.render.*;
import net.neptune.mythronix.game.fluids.ModFluids;
import net.neptune.mythronix.game.items.ModItems;
import net.neptune.mythronix.game.particles.ModParticles;
import net.neptune.mythronix.game.recipes.ModRecipeTypes;
import net.neptune.mythronix.game.structures.ModStructures;
import net.neptune.mythronix.game.trees.ModWoodTypes;
import net.neptune.mythronix.menus.containers.ModContainers;
import net.neptune.mythronix.menus.screens.ModScreens;
import net.neptune.mythronix.worldgen.biomes.*;
import org.apache.logging.log4j.*;
import software.bernie.geckolib3.GeckoLib;

@Mod(Main.MODID)
public class Main {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "mt";

    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MODID, "channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public Main() {

        /** TODO:
         * Update the patchouli book
         * Mana
         * loot for the boss
         * Fix Fallen Guardian
         * Craft for the Sword of life
         * Summon corrupter structure
         * effect better render corrupted
         * Spawn Tharvyrn inside
        */

        GeckoLib.initialize();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModParticles.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::enqueueIMC);
        bus.addListener(this::processIMC);
        bus.addListener(this::doClientStuff);

        ModBlocks.register(bus);
        ModItems.register(bus);
        ModBiomes.register(bus);
        ModTileEntities.register(bus);
        ModRecipeTypes.register(bus);
        ModContainers.register(bus);
        ModFluids.register(bus);
        ModEffects.register(bus);
        ModEntityTypes.register(bus);
        ModStructures.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent e) {
        e.enqueueWork(() -> {

            WoodType.register(ModWoodTypes.FIRE_WOOD);
            WoodType.register(ModWoodTypes.ICE_WOOD);
            WoodType.register(ModWoodTypes.WATER_WOOD);
            WoodType.register(ModWoodTypes.WIND_WOOD);
            WoodType.register(ModWoodTypes.NATURE_WOOD);
            WoodType.register(ModWoodTypes.ARCANE_WOOD);
            ModBiomesGeneration.generatesBiomes();
        });

        ModCapabilities.register();
        ModPackets.register();
        ModStructures.setupStructures();
    }

    private void doClientStuff(final FMLClientSetupEvent e) {
        e.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(ModBlocks.FIRE_LEAVES.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ICE_LEAVES.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.WATER_LEAVES.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.WIND_LEAVES.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARCANE_LEAVES.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.NATURE_LEAVES.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.CORRUPTED_LEAVES.get(), RenderType.cutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.FIRE_SAPLING.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ICE_SAPLING.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.WATER_SAPLING.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.WIND_SAPLING.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARCANE_SAPLING.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.NATURE_SAPLING.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.CORRUPTED_SAPLING.get(), RenderType.cutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.CORRUPTED_TRAPDOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.CORRUPTED_DOOR.get(), RenderType.cutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.LYS_FLOWER.get(), RenderType.cutout());

            Atlases.addWoodType(ModWoodTypes.ARCANE_WOOD);
            Atlases.addWoodType(ModWoodTypes.FIRE_WOOD);
            Atlases.addWoodType(ModWoodTypes.ICE_WOOD);
            Atlases.addWoodType(ModWoodTypes.WIND_WOOD);
            Atlases.addWoodType(ModWoodTypes.WATER_WOOD);
            Atlases.addWoodType(ModWoodTypes.NATURE_WOOD);
            Atlases.addWoodType(ModWoodTypes.CORRUPTED_WOOD);

            RenderTypeLookup.setRenderLayer(ModFluids.ETHERUM_FLUID.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(ModFluids.ETHERUM_BLOCK.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(ModFluids.ETHERUM_FLOWING.get(), RenderType.translucent());

            RenderTypeLookup.setRenderLayer(ModBlocks.CORRUPTED_TORCH.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.CORRUPTED_TORCH_WALL.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.CORRUPTED_LANTERN.get(), RenderType.cutout());

            ModScreens.register();
        });

        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BOREAL_DEER.get(), BorealDeerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CORRUPTED_GOLEM.get(), CorruptedGolemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.THARVYRN.get(), TharvyrnRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CORRUPTER.get(), CorrupterRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FALLEN_GUARDIAN.get(), FallenGuardianRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CORRUPTION_ORB.get(), CorruptionOrbRenderer::new);

        ClientRegistry.bindTileEntityRenderer(ModTileEntities.PURIFIER_TILE.get(), PurifierRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.CORRUPTER_STATUE_TILE.get(), CorrupterStatueRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.CORRUPTER_DOOR_TILE.get(), CorrupterDoorRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.UNCORRUPTER_TILE.get(), UnCorrupterRenderer::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent e) {
    }

    private void processIMC(final InterModProcessEvent e) {
    }
}
