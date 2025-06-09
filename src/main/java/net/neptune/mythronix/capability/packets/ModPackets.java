package net.neptune.mythronix.capability.packets;

import net.neptune.mythronix.Main;

public class ModPackets {
    public static void register(){
        int idx = 0;

        Main.NETWORK.registerMessage(idx++, ManaLevelSyncPacket.class, ManaLevelSyncPacket::encode, ManaLevelSyncPacket::decode, ManaLevelSyncPacket::handle);
    }
}
