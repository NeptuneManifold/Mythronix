package net.neptune.mythronix.menus.containers;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.neptune.mythronix.Main;

public class ModContainers {
    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Main.MODID);

    public static final RegistryObject<ContainerType<PurifierContainer>> PURIFIER_CONTAINER =
            CONTAINERS.register("purifier_container",
                    () -> IForgeContainerType.create((((windowId, inv, data) -> {
                        BlockPos pos = data.readBlockPos();
                        World world = inv.player.level;
                        return new PurifierContainer(windowId, world, pos, inv, inv.player);
                    }))));

    public static void register(IEventBus bus){
        CONTAINERS.register(bus);
    }
}
