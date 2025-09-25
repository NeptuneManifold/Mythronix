package net.neptune.mythronix.game.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.blocks.deco.CorrupterAltarBlock;
import net.neptune.mythronix.game.blocks.geckolib.CorrupterDoor;
import net.neptune.mythronix.game.blocks.geckolib.CorrupterStatue;
import net.neptune.mythronix.game.blocks.geckolib.PurifierBlock;
import net.neptune.mythronix.game.blocks.ores.*;
import net.neptune.mythronix.game.items.ModItems;
import net.neptune.mythronix.game.particles.ModParticles;
import net.neptune.mythronix.game.trees.*;
import net.neptune.mythronix.tabs.ModTabs;
import net.neptune.mythronix.game.trees.CorruptedTree;

import java.util.Random;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);

    public static final RegistryObject<Block> CORRUPTED_PLANKS = newBlockBaseDrops("corrupted_planks", ModTabs.mythronix, Material.WOOD, 1, ToolType.AXE, 2,3, SoundType.WOOD);

    public static final RegistryObject<Block> FIRE_LOG = newBlockRotate("fire_log", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);
    public static final RegistryObject<Block> NATURE_LOG = newBlockRotate("nature_log", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);
    public static final RegistryObject<Block> WATER_LOG = newBlockRotate("water_log", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);
    public static final RegistryObject<Block> ICE_LOG = newBlockRotate("ice_log", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);
    public static final RegistryObject<Block> WIND_LOG = newBlockRotate("wind_log", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);
    public static final RegistryObject<Block> ARCANE_LOG = newBlockRotate("arcane_log", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);
    public static final RegistryObject<Block> CORRUPTED_LOG = newBlockRotate("corrupted_log", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);

    public static final RegistryObject<Block> CORRUPTED_WOOD = newBlockRotate("corrupted_wood", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);

    public static final RegistryObject<Block> FIRE_LEAVES = newBlockLeaves("fire_leaves", ModTabs.mythronix, Material.LEAVES, 0.2F, 0.2F);
    public static final RegistryObject<Block> NATURE_LEAVES = newBlockLeaves("nature_leaves", ModTabs.mythronix, Material.LEAVES, 0.2F, 0.2F);
    public static final RegistryObject<Block> WATER_LEAVES = newBlockLeaves("water_leaves", ModTabs.mythronix, Material.LEAVES, 0.2F, 0.2F);
    public static final RegistryObject<Block> ICE_LEAVES = newBlockLeaves("ice_leaves", ModTabs.mythronix, Material.LEAVES, 0.2F, 0.2F);
    public static final RegistryObject<Block> WIND_LEAVES = newBlockLeaves("wind_leaves", ModTabs.mythronix, Material.LEAVES, 0.2F, 0.2F);
    public static final RegistryObject<Block> ARCANE_LEAVES = newBlockLeaves("arcane_leaves", ModTabs.mythronix, Material.LEAVES, 0.2F, 0.2F);
    public static final RegistryObject<Block> CORRUPTED_LEAVES = newBlockLeaves("corrupted_leaves", ModTabs.mythronix, Material.LEAVES, 0.2F, 0.2F);

    public static final RegistryObject<Block> CORRUPTED_STAIRS = newStairsBlock("corrupted_stairs", () -> CORRUPTED_PLANKS.get().defaultBlockState() ,ModTabs.mythronix, Material.WOOD, 1, ToolType.AXE, 2,3, SoundType.WOOD);

    public static final RegistryObject<Block> CORRUPTED_SLAB = newSlabBlock("corrupted_slab", ModTabs.mythronix, Material.WOOD, 1, ToolType.AXE, 2,3, SoundType.WOOD);

    public static final RegistryObject<Block> CORRUPTED_DOOR = newDoorBlock("corrupted_door", ModTabs.mythronix, Material.WOOD, 1, ToolType.AXE, 2,3, SoundType.WOOD);

    public static final RegistryObject<Block> CORRUPTED_TRAPDOOR = newTrapdoorBlock("corrupted_trapdoor", ModTabs.mythronix, Material.WOOD, 1, ToolType.AXE, 2,3, SoundType.WOOD);

    public static final RegistryObject<Block> CORRUPTED_FENCE = newFenceBlock("corrupted_fence", ModTabs.mythronix, Material.WOOD, 1, ToolType.AXE, 2,3, SoundType.WOOD);

    public static final RegistryObject<Block> CORRUPTED_FENCE_GATE = newFenceGateBlock("corrupted_fence_gate", ModTabs.mythronix, Material.WOOD, 1, ToolType.AXE, 2,3, SoundType.WOOD);

    public static final RegistryObject<Block> CORRUPTED_PRESSURE_PLATE = newPressurePlateBlock("corrupted_pressure_plate", ModTabs.mythronix, Material.WOOD, 1, ToolType.AXE, 2,3, SoundType.WOOD);

    public static final RegistryObject<Block> CORRUPTED_BUTTON = newButtonBlock("corrupted_button", ModTabs.mythronix, Material.WOOD, 1, ToolType.AXE, 2,3, SoundType.WOOD);

    public static final RegistryObject<Block> CORRUPTED_BOOKSHELF = newBlockBaseDrops("corrupted_bookshelf", ModTabs.mythronix, Material.WOOD,1, ToolType.AXE,1.5f,1.5f, SoundType.WOOD);

    public static final RegistryObject<Block> MAGICAL_WALL = newBlockBase("magical_wall", ModTabs.mythronix, Material.WOOD, 1, ToolType.PICKAXE, -1f, 3600000f, SoundType.STONE);
    public static final RegistryObject<Block> UNCCORUPTER = registerBlock("uncorrupter", () -> new UnCorrupterBlock());

    public static final RegistryObject<Block> FIRE_SAPLING = registerBlock("fire_sapling", () ->
            new SaplingBlock(new FireTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING).noOcclusion().sound(SoundType.GRASS)), ModTabs.mythronix);
    public static final RegistryObject<Block> NATURE_SAPLING = registerBlock("nature_sapling", () ->
            new SaplingBlock(new NatureTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING).noOcclusion().sound(SoundType.GRASS)), ModTabs.mythronix);
    public static final RegistryObject<Block> WATER_SAPLING = registerBlock("water_sapling", () ->
            new SaplingBlock(new WaterTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING).noOcclusion().sound(SoundType.GRASS)), ModTabs.mythronix);
    public static final RegistryObject<Block> ICE_SAPLING = registerBlock("ice_sapling", () ->
            new SaplingBlock(new IceTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING).noOcclusion().sound(SoundType.GRASS)), ModTabs.mythronix);
    public static final RegistryObject<Block> WIND_SAPLING = registerBlock("wind_sapling", () ->
            new SaplingBlock(new WindTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING).noOcclusion().sound(SoundType.GRASS)), ModTabs.mythronix);
    public static final RegistryObject<Block> ARCANE_SAPLING = registerBlock("arcane_sapling", () ->
            new SaplingBlock(new ArcaneTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING).noOcclusion().sound(SoundType.GRASS)), ModTabs.mythronix);
    public static final RegistryObject<Block> CORRUPTED_SAPLING = registerBlock("corrupted_sapling", () ->
            new SaplingBlock(new CorruptedTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING).noOcclusion().sound(SoundType.GRASS)), ModTabs.mythronix);

    public static final RegistryObject<Block> MANA_ORE = newBlockBase("mana_ore", ModTabs.mythronix, Material.STONE, 2, ToolType.PICKAXE,
            4.0F,4.0F, SoundType.STONE);

    public static final RegistryObject<Block> CORRUPTED_BRICKS = newBlockBase("corrupted_bricks", ModTabs.mythronix, Material.STONE, 1, ToolType.PICKAXE,
            1.5F,6.0F, SoundType.STONE);

    public static final RegistryObject<Block> CORRUPTED_BRICKS_STAIRS = newStairsBlock("corrupted_bricks_stairs", () -> CORRUPTED_BRICKS.get().defaultBlockState()  ,ModTabs.mythronix, Material.STONE, 1, ToolType.PICKAXE,
            1.5F,6.0F, SoundType.STONE);

    public static final RegistryObject<Block> CORRUPTED_BRICKS_WALL = newWallBlock("corrupted_bricks_wall", ModTabs.mythronix, Material.STONE, 1, ToolType.PICKAXE,
            1.5F,6.0F, SoundType.STONE);

    public static final RegistryObject<Block> CORRUPTED_BRICKS_SLAB = newSlabBlock("corrupted_bricks_slab", ModTabs.mythronix, Material.STONE, 1, ToolType.PICKAXE,
            1.5F,6.0F, SoundType.STONE);

    public static final RegistryObject<Block> CRACKED_CORRUPTED_BRICKS = newBlockBase("cracked_corrupted_bricks", ModTabs.mythronix, Material.STONE, 1, ToolType.PICKAXE,
            1.5F,6.0F, SoundType.STONE);

    public static final RegistryObject<Block> MOSSY_CORRUPTED_BRICKS = newBlockBase("mossy_corrupted_bricks", ModTabs.mythronix, Material.STONE, 1, ToolType.PICKAXE,
            1.5F,6.0F, SoundType.STONE);

    public static final RegistryObject<Block> CHISELED_CORRUPTED_BRICKS = newBlockBase("chiseled_corrupted_bricks", ModTabs.mythronix, Material.STONE, 1, ToolType.PICKAXE,
            1.5F,6.0F, SoundType.STONE);

    public static final RegistryObject<Block> MANA_STONE = newBlockBase("mana_stone", ModTabs.mythronix, Material.STONE, 0, ToolType.PICKAXE,
            1.5F, 6.0F, SoundType.STONE);
    public static final RegistryObject<Block> MANA_COBBLESTONE = newBlockBase("mana_cobblestone", ModTabs.mythronix, Material.STONE, 0, ToolType.PICKAXE,
            1.5F, 6.0F, SoundType.STONE);

    public static final RegistryObject<Block> CORRUPTED_LANTERN = newLanternBlock("corrupted_lantern", ModTabs.mythronix, Material.WOOD, 0,
            ToolType.PICKAXE, 3.5F, 3.5F, SoundType.LANTERN);

    public static final RegistryObject<Block> PYRROTHITYE_ORE = registerBlock("pyrrothite_ore", () -> new PyrrothiteOreBlock(AbstractBlock.Properties.of(Material.STONE)
            .harvestTool(ToolType.PICKAXE).harvestLevel(3).strength(6.0F, 6.0F)
                    .requiresCorrectToolForDrops().sound(SoundType.NETHER_ORE).lightLevel((p_235464_0_) -> {
                return 3;
            })),
            ModTabs.mythronix);

    public static final RegistryObject<Block> AQUALITE_ORE = registerBlock("aqualite_ore", () -> new AqualiteOreBlock(AbstractBlock.Properties.of(Material.STONE)
                    .harvestTool(ToolType.PICKAXE).harvestLevel(2).strength(6.0F, 6.0F)
            .requiresCorrectToolForDrops().sound(SoundType.STONE)), ModTabs.mythronix);

    public static final RegistryObject<Block> PHYTOLITE_ORE = registerBlock("phytolite_ore", () -> new PhytoliteOreBlock(AbstractBlock.Properties.of(Material.STONE)
            .harvestTool(ToolType.PICKAXE).harvestLevel(2).strength(6.0F, 6.0F)
            .requiresCorrectToolForDrops().sound(SoundType.STONE)), ModTabs.mythronix);

    public static final RegistryObject<Block> CRYONITE_ORE = registerBlock("cryonite_ore", () -> new CryoniteOreBlock(AbstractBlock.Properties.of(Material.STONE)
            .harvestTool(ToolType.PICKAXE).harvestLevel(2).strength(6.0F, 6.0F).
            requiresCorrectToolForDrops().sound(SoundType.STONE)), ModTabs.mythronix);

    public static final RegistryObject<Block> ARCANITE_ORE = registerBlock("arcanite_ore", () -> new ArcaniteOreBlock(AbstractBlock.Properties.of(Material.STONE)
            .harvestTool(ToolType.PICKAXE).harvestLevel(2).strength(6.0F, 6.0F)
            .requiresCorrectToolForDrops().sound(SoundType.STONE)), ModTabs.mythronix);

    public static final RegistryObject<Block> AERONITE_ORE = registerBlock("aeronite_ore", () -> new AeroniteOreBlock(AbstractBlock.Properties.of(Material.STONE)
            .harvestTool(ToolType.PICKAXE).harvestLevel(2).strength(6.0F, 6.0F)
            .requiresCorrectToolForDrops().sound(SoundType.STONE)), ModTabs.mythronix);

    public static final RegistryObject<Block> RUBY_ORE = newBlockBase("ruby_ore", ModTabs.mythronix, Material.STONE, 2,
            ToolType.PICKAXE, 3.0F, 3.0F, SoundType.STONE);
    public static final RegistryObject<Block> RUBY_BLOCK = newBlockBase("ruby_block", ModTabs.mythronix, Material.METAL,2,
            ToolType.PICKAXE,3.0F, 3.0F, SoundType.METAL);

    public static final RegistryObject<Block> LYS_FLOWER = newFlower("lys_flower", ModTabs.mythronix, Effects.GLOWING, 60);

    //geckolib

    public static final RegistryObject<Block> PURIFIER = registerBlock("purifier", () -> new PurifierBlock(), ModTabs.mythronix);

    public static final RegistryObject<Block> CORRUPTER_STATUE = registerBlocknoitem("corrupter_statue", () -> new CorrupterStatue());
    public static final RegistryObject<Block> CORRUPTER_DOOR = registerBlocknoitem("corrupter_door", () -> new CorrupterDoor());

    public static final RegistryObject<Block> CORRUPTER_ALTAR = registerBlock("corrupter_altar", () -> new CorrupterAltarBlock(), ModTabs.mythronix);

    public static final RegistryObject<Block> CORRUPTED_TORCH_WALL = registerBlocknoitem("corrupted_torch_wall",
            () -> new WallTorchBlock(AbstractBlock.Properties.of(Material.WOOD)
                    .noCollission()
                    .strength(0.0F)
                    .lightLevel((state) -> 14)
                    .sound(SoundType.WOOD),
                    ParticleTypes.FLAME) {
                @Override
                public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
                    if (world.isClientSide) {
                        double x = pos.getX() + 0.5D;
                        double y = pos.getY() + 0.7D;
                        double z = pos.getZ() + 0.5D;

                        world.addParticle(ModParticles.CORRUPTED_FIRE.get(),
                                x, y, z,
                                0.0D, 0.01D, 0.0D);
                    }
                }
            }
    );

    public static final RegistryObject<Block> CORRUPTED_TORCH = registerBlocknoitem("corrupted_torch",
            () -> new TorchBlock(AbstractBlock.Properties.of(Material.WOOD)
                    .noCollission()
                    .strength(0.0F)
                    .lightLevel((state) -> 14)
                    .sound(SoundType.WOOD),
                    ParticleTypes.FLAME) {

                @Override
                public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
                    if (world.isClientSide) {
                        double x = pos.getX() + 0.5D;
                        double y = pos.getY() + 0.7D;
                        double z = pos.getZ() + 0.5D;

                        world.addParticle(ModParticles.CORRUPTED_FIRE.get(),
                                x, y, z,
                                0.0D, 0.01D, 0.0D);
                    }
                }
            });

    public static <T extends Block> RegistryObject<T> registerBlocknoitem(String id, Supplier<T> block){
        RegistryObject<T> toreturn = BLOCKS.register(id, block);

        return toreturn;
    }

    public static RegistryObject<Block> newBlockBase(String id, ItemGroup tab, Material mat, int harvestLevel,
                                                     ToolType type, float hardness, float resistance, SoundType sound){
        return registerBlock(id, () -> new Block(AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).requiresCorrectToolForDrops().strength(hardness, resistance).sound(sound)), tab);
    }

    public static RegistryObject<Block> newBlockBaseDrops(String id, ItemGroup tab, Material mat, int harvestLevel,
                                                     ToolType type, float hardness, float resistance, SoundType sound){
        return registerBlock(id, () -> new Block(AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).strength(hardness, resistance).sound(sound)), tab);
    }

    public static RegistryObject<Block> newPressurePlateBlock(String id, ItemGroup tab, Material mat, int harvestLevel,
                                                              ToolType type, float hardness, float resistance, SoundType soung){
        return registerBlock(id, () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING ,AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).strength(hardness, resistance).sound(soung)), tab);
    }

    public static RegistryObject<Block> newButtonBlock(String id, ItemGroup tab, Material mat, int harvestLevel,
                                                              ToolType type, float hardness, float resistance, SoundType soung){
        return registerBlock(id, () -> new WoodButtonBlock(AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).strength(hardness, resistance).sound(soung)), tab);
    }

    public static RegistryObject<Block> newFenceBlock(String id, ItemGroup tab, Material mat, int harvestLevel,
                                                              ToolType type, float hardness, float resistance, SoundType soung){
        return registerBlock(id, () -> new FenceBlock(AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).strength(hardness, resistance).sound(soung)), tab);
    }

    public static RegistryObject<Block> newWallBlock(String id, ItemGroup tab, Material mat, int harvestLevel,
                                                      ToolType type, float hardness, float resistance, SoundType soung){
        return registerBlock(id, () -> new WallBlock(AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).strength(hardness, resistance).sound(soung)), tab);
    }

    public static RegistryObject<Block> newLanternBlock(String id, ItemGroup tab, Material mat, int harvestLevel,
                                                     ToolType type, float hardness, float resistance, SoundType soung){
        return registerBlock(id, () -> new LanternBlock(AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).strength(hardness, resistance).sound(soung).lightLevel((p_235464_0_) -> {
                            return 5;
                        })), tab);
    }

    public static RegistryObject<Block> newFenceGateBlock(String id, ItemGroup tab, Material mat, int harvestLevel,
                                                      ToolType type, float hardness, float resistance, SoundType soung){
        return registerBlock(id, () -> new FenceGateBlock(AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).strength(hardness, resistance).sound(soung)), tab);
    }

    public static RegistryObject<Block> newTrapdoorBlock(String id, ItemGroup tab, Material mat, int harvestLevel,
                                                      ToolType type, float hardness, float resistance, SoundType soung){
        return registerBlock(id, () -> new TrapDoorBlock(AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).strength(hardness, resistance).sound(soung).noOcclusion()), tab);
    }

    public static RegistryObject<Block> newDoorBlock(String id, ItemGroup tab, Material mat, int harvestLevel,
                                                      ToolType type, float hardness, float resistance, SoundType soung){
        return registerBlock(id, () -> new DoorBlock(AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).strength(hardness, resistance).noOcclusion().sound(soung)), tab);
    }

    public static RegistryObject<Block> newStairsBlock(String id, Supplier<BlockState> state, ItemGroup tab, Material mat, int harvestLevel,
                                                      ToolType type, float hardness, float resistance, SoundType soung){
        return registerBlock(id, () -> new StairsBlock(state ,AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).strength(hardness, resistance).sound(soung)), tab);
    }

    public static RegistryObject<Block> newSlabBlock(String id, ItemGroup tab, Material mat, int harvestLevel,
                                                      ToolType type, float hardness, float resistance, SoundType soung){
        return registerBlock(id, () -> new SlabBlock(AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).strength(hardness, resistance).sound(soung)), tab);
    }

    public static RegistryObject<Block> newFlower(String id, ItemGroup tab, Effect effect, int duration){
        return registerBlock(id, () -> new FlowerBlock(effect, duration, AbstractBlock.Properties.copy(Blocks.DANDELION).noOcclusion().noCollission().sound(SoundType.GRASS)), tab);
    }

    public static RegistryObject<Block> newBlockRotate(String id, ItemGroup tab, Material mat, int harvestLevel,
                                                     ToolType type, float hardness, float resistance, SoundType sound){
        return registerBlock(id, () -> new RotatedPillarBlock(AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).strength(hardness, resistance).sound(sound)), tab);
    }

    public static RegistryObject<Block> newBlockLeaves(String id, ItemGroup tab, Material mat, float hardness, float resistance){
        return registerBlock(id, () -> new LeavesBlock(AbstractBlock.Properties.of(mat)
                .strength(hardness, resistance).noOcclusion().randomTicks().sound(SoundType.GRASS)), tab);
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String id, Supplier<T> block, ItemGroup tab){
        RegistryObject<T> toreturn = BLOCKS.register(id, block);
        registerBlockItem(id, toreturn, tab);

        return toreturn;
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String id, Supplier<T> block){
        RegistryObject<T> toreturn = BLOCKS.register(id, block);
        registerBlockItem(id, toreturn);

        return toreturn;
    }

    private static <T extends Block> void registerBlockItem(String id, RegistryObject<T> toreturn, ItemGroup tab) {
        ModItems.ITEMS.register(id, () -> new BlockItem(toreturn.get(), new Item.Properties().tab(tab)));
    }

    private static <T extends Block> void registerBlockItem(String id, RegistryObject<T> toreturn) {
        ModItems.ITEMS.register(id, () -> new BlockItem(toreturn.get(), new Item.Properties()));
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
