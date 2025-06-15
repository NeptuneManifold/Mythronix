package net.neptune.mythronix.worldgen.ore;

import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;
import net.neptune.mythronix.game.blocks.ModBlocks;

public enum OreType {
    MANA(Lazy.of(ModBlocks.MANA_ORE), 4,0,40,3),
    PYRROTHITE(Lazy.of(ModBlocks.PYRROTHITYE_ORE), 4, 0, 100, 20),
    AQUALITE(Lazy.of(ModBlocks.AQUALITE_ORE), 4, 0, 100, 20),
    PHYTOLITE(Lazy.of(ModBlocks.PHYTOLITE_ORE), 4, 0, 100, 20),
    CRYONITE(Lazy.of(ModBlocks.CRYONITE_ORE), 4, 0, 100, 20),
    ARCANITE(Lazy.of(ModBlocks.ARCANITE_ORE), 4, 0, 100, 20),
    AERONITE(Lazy.of(ModBlocks.AERONITE_ORE), 4, 0, 100, 20);


    private final Lazy<Block> block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;
    private final int veinsPerChunk;

    OreType(Lazy<Block> block, int maxVeinSize, int minHeight, int maxHeight, int veinsPerChunk){
        this.block = block;
        this.maxVeinSize = maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinsPerChunk = veinsPerChunk;
    }

    public Lazy<Block> getBlock() {
        return block;
    }

    public int getVeinsPerChunk() {
        return veinsPerChunk;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public static OreType get(Block block){
        for(OreType ore : values()){
            if(block == ore.block){
                return ore;
            }
        }
        return null;
    }
}
