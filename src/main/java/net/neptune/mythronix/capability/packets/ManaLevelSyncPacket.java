package net.neptune.mythronix.capability.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import net.neptune.mythronix.capability.IManaLevel;
import net.neptune.mythronix.game.ModCapabilities;

import java.util.function.Supplier;

public class ManaLevelSyncPacket {
    private final int playerManaLevel;
    private final int playerManaXp;
    private final int playerId;

    public ManaLevelSyncPacket(int playerManaLevel, int playerManaXp, int playerId) {
        this.playerManaLevel = playerManaLevel;
        this.playerManaXp = playerManaXp;
        this.playerId = playerId;
    }

    public void encode(PacketBuffer buffer){
        buffer.writeInt(playerManaLevel);
        buffer.writeInt(playerManaXp);
        buffer.writeInt(playerId);
    }

    public static ManaLevelSyncPacket decode(PacketBuffer buffer) {
        return new ManaLevelSyncPacket(buffer.readInt(), buffer.readInt(), buffer.readInt());
    }

    public void handle(Supplier<NetworkEvent.Context> ctx){
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            int playerId = this.playerId;
            World world = Minecraft.getInstance().level;
            if(world == null) return;

            Entity entity = world.getEntity(playerId);
            if(entity instanceof PlayerEntity){
                IManaLevel manaLevel = entity.getCapability(ModCapabilities.MANA_LEVEL).orElse(null);
                if(manaLevel != null) {
                    manaLevel.setManaLevel(this.playerManaLevel);
                    manaLevel.setManaXp(this.playerManaXp);
                }
            }
        });

        context.setPacketHandled(true);
    }
}
