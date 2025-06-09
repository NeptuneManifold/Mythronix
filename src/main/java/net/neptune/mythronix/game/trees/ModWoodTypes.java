package net.neptune.mythronix.game.trees;

import net.minecraft.block.WoodType;
import net.minecraft.util.ResourceLocation;
import net.neptune.mythronix.Main;

public class ModWoodTypes {
    public static final WoodType FIRE_WOOD = WoodType.create(new ResourceLocation(Main.MODID, "fire_tree").toString());
    public static final WoodType ICE_WOOD = WoodType.create(new ResourceLocation(Main.MODID, "ice_tree").toString());
    public static final WoodType WATER_WOOD = WoodType.create(new ResourceLocation(Main.MODID, "water_tree").toString());
    public static final WoodType WIND_WOOD = WoodType.create(new ResourceLocation(Main.MODID, "wind_tree").toString());
    public static final WoodType ARCANE_WOOD = WoodType.create(new ResourceLocation(Main.MODID, "arcane_tree").toString());
    public static final WoodType NATURE_WOOD = WoodType.create(new ResourceLocation(Main.MODID, "nature_tree").toString());
}
