package net.neptune.mythronix.game.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.game.blocks.geckolib.PurifierBlock;
import net.neptune.mythronix.game.blocks.ores.AqualiteOreBlock;
import net.neptune.mythronix.game.blocks.ores.CryoniteOreBlock;
import net.neptune.mythronix.game.blocks.ores.PhytoliteOreBlock;
import net.neptune.mythronix.game.blocks.ores.PyrrothiteOreBlock;
import net.neptune.mythronix.game.items.ModItems;
import net.neptune.mythronix.game.trees.*;
import net.neptune.mythronix.tabs.ModTabs;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);

    public static final RegistryObject<Block> FIRE_LOG = newBlockRotate("fire_log", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);
    public static final RegistryObject<Block> NATURE_LOG = newBlockRotate("nature_log", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);
    public static final RegistryObject<Block> WATER_LOG = newBlockRotate("water_log", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);
    public static final RegistryObject<Block> ICE_LOG = newBlockRotate("ice_log", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);
    public static final RegistryObject<Block> WIND_LOG = newBlockRotate("wind_log", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);
    public static final RegistryObject<Block> ARCANE_LOG = newBlockRotate("arcane_log", ModTabs.mythronix, Material.WOOD, 0, ToolType.AXE, 2F, 2F, SoundType.WOOD);

    public static final RegistryObject<Block> FIRE_LEAVES = newBlockLeaves("fire_leaves", ModTabs.mythronix, Material.LEAVES, 0.2F, 0.2F);
    public static final RegistryObject<Block> NATURE_LEAVES = newBlockLeaves("nature_leaves", ModTabs.mythronix, Material.LEAVES, 0.2F, 0.2F);
    public static final RegistryObject<Block> WATER_LEAVES = newBlockLeaves("water_leaves", ModTabs.mythronix, Material.LEAVES, 0.2F, 0.2F);
    public static final RegistryObject<Block> ICE_LEAVES = newBlockLeaves("ice_leaves", ModTabs.mythronix, Material.LEAVES, 0.2F, 0.2F);
    public static final RegistryObject<Block> WIND_LEAVES = newBlockLeaves("wind_leaves", ModTabs.mythronix, Material.LEAVES, 0.2F, 0.2F);
    public static final RegistryObject<Block> ARCANE_LEAVES = newBlockLeaves("arcane_leaves", ModTabs.mythronix, Material.LEAVES, 0.2F, 0.2F);

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

    public static final RegistryObject<Block> MANA_ORE = newBlockBase("mana_ore", ModTabs.mythronix, Material.STONE, 2, ToolType.PICKAXE,
            4.0F,4.0F, SoundType.STONE);

    public static final RegistryObject<Block> MANA_STONE = newBlockBase("mana_stone", ModTabs.mythronix, Material.STONE, 0, ToolType.PICKAXE,
            1.5F, 6.0F, SoundType.STONE);
    public static final RegistryObject<Block> MANA_COBBLESTONE = newBlockBase("mana_cobblestone", ModTabs.mythronix, Material.STONE, 0, ToolType.PICKAXE,
            1.5F, 6.0F, SoundType.STONE);

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

    public static RegistryObject<Block> newBlockBase(String id, ItemGroup tab, Material mat, int harvestLevel,
                                                     ToolType type, float hardness, float resistance, SoundType sound){
        return registerBlock(id, () -> new Block(AbstractBlock.Properties.of(mat)
                .harvestLevel(harvestLevel).harvestTool(type).requiresCorrectToolForDrops().strength(hardness, resistance).sound(sound)), tab);
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

    private static <T extends Block> void registerBlockItem(String id, RegistryObject<T> toreturn, ItemGroup tab) {
        ModItems.ITEMS.register(id, () -> new BlockItem(toreturn.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
