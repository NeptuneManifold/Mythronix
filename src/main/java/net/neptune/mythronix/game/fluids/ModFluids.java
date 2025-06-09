package net.neptune.mythronix.game.fluids;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.neptune.mythronix.Main;

import net.neptune.mythronix.game.blocks.ModBlocks;
import net.neptune.mythronix.game.items.ModItems;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Main.MODID);

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOW_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");
    
    public static final RegistryObject<FlowingFluid> ETHERUM_FLUID
            = FLUIDS.register("etherum_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.ETHERUM_PROPERTIES));

    public static final RegistryObject<FlowingFluid> ETHERUM_FLOWING
            = FLUIDS.register("etherum_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.ETHERUM_PROPERTIES));

    public static final ForgeFlowingFluid.Properties ETHERUM_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> ETHERUM_FLUID.get(), () -> ETHERUM_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOW_RL)
            .density(15).luminosity(5).viscosity(5).sound(SoundEvents.WATER_AMBIENT).overlay(WATER_OVERLAY_RL)
            .color(0xff19fce0)).slopeFindDistance(5).levelDecreasePerBlock(3)
            .block(() -> ModFluids.ETHERUM_BLOCK.get()).bucket(() -> ModItems.ETHERUM_BUCKET.get());

    public static final RegistryObject<FlowingFluidBlock> ETHERUM_BLOCK = ModBlocks.BLOCKS.register("etherum",
            () -> new FlowingFluidBlock(() -> ModFluids.ETHERUM_FLUID.get(), AbstractBlock.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static void register(IEventBus bus){
        FLUIDS.register(bus);
    }
}
