package net.neptune.mythronix.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.capability.packets.ManaLevelSyncPacket;
import net.neptune.mythronix.game.ModCapabilities;

public class ManaLevelUtils {
    public static int getManaLevel(Entity entity){
        return entity.getCapability(ModCapabilities.MANA_LEVEL).map(IManaLevel::getManaLevel).orElse(0);
    }

    public static int getManaXp(Entity entity){
        return entity.getCapability(ModCapabilities.MANA_LEVEL).map(IManaLevel::getManaXp).orElse(0);
    }

    public static void addManaXp(Entity entity, int amount){
        entity.getCapability(ModCapabilities.MANA_LEVEL).ifPresent(manaLevel -> {
            manaLevel.addManaXp(amount);

            update((PlayerEntity) entity, manaLevel);
        });
    }

    public static void addManaLevel(Entity entity, int amount){
        entity.getCapability(ModCapabilities.MANA_LEVEL).ifPresent(manaLevel -> {
            manaLevel.addManaLevel(amount);

            update((PlayerEntity) entity, manaLevel);
        });
    }

    public static void setManaXp(Entity entity, int amount){
        entity.getCapability(ModCapabilities.MANA_LEVEL).ifPresent(manaLevel -> {
            manaLevel.setManaXp(amount);

            update((PlayerEntity) entity, manaLevel);
        });
    }

    public static void setManaLevel(Entity entity, int amount){
        entity.getCapability(ModCapabilities.MANA_LEVEL).ifPresent(manaLevel -> {
            manaLevel.setManaLevel(amount);

            update((PlayerEntity) entity, manaLevel);
        });
    }

    public static void update(PlayerEntity entity){
        entity.getCapability(ModCapabilities.MANA_LEVEL).ifPresent(manaLevel -> {
            update(entity, manaLevel);
        });
    }

    private static void update(PlayerEntity player, IManaLevel manaLevel) {
        // Adv
        Main.NETWORK.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new ManaLevelSyncPacket(manaLevel.getManaLevel(), manaLevel.getManaXp(), player.getId()));
    }
}
