package net.neptune.mythronix.capability.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.neptune.mythronix.Main;
import net.neptune.mythronix.capability.ManaLevelUtils;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.ToIntFunction;

public class ManaLevelCommands {
    public ManaLevelCommands(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> literalCommandNode = dispatcher.register(
                Commands.literal("manalevel")
                        // /manalevel add <targets> <amount> [xp|levels]
                        .then(Commands.literal("add")
                                        .then(Commands.argument("targets", EntityArgument.players())
                                                .then(Commands.argument("amount", IntegerArgumentType.integer())
                                                        .executes((cmd) -> addManaLevel(cmd.getSource(), EntityArgument.getPlayers(cmd, "targets"), IntegerArgumentType.getInteger(cmd, "amount"), Type.XP)).then(Commands.literal("xp")
                                                                .executes((cmd) -> addManaLevel(cmd.getSource(), EntityArgument.getPlayers(cmd, "targets"), IntegerArgumentType.getInteger(cmd, "amount"), Type.XP))
                                                        ).then(Commands.literal("levels")
                                                                .executes((cmd) -> addManaLevel(cmd.getSource(), EntityArgument.getPlayers(cmd, "targets"), IntegerArgumentType.getInteger(cmd, "amount"), Type.LEVELS))
                                                        )
                                                )
                                        )
                                // /manalevel set <targets> <amount> [xp|levels]
                        ).then(Commands.literal("set")
                                        .then(Commands.argument("targets", EntityArgument.players())
                                                .then(Commands.argument("amount", IntegerArgumentType.integer(0))
                                                        .executes((cmd) -> setManaLevel(cmd.getSource(), EntityArgument.getPlayers(cmd, "targets"), IntegerArgumentType.getInteger(cmd, "amount"), Type.XP)).then(Commands.literal("xp")
                                                                .executes((cmd) -> setManaLevel(cmd.getSource(), EntityArgument.getPlayers(cmd, "targets"), IntegerArgumentType.getInteger(cmd, "amount"), Type.XP))
                                                        ).then(Commands.literal("levels")
                                                                .executes((cmd) -> setManaLevel(cmd.getSource(), EntityArgument.getPlayers(cmd, "targets"), IntegerArgumentType.getInteger(cmd, "amount"), Type.LEVELS))
                                                        )
                                                )
                                        )
                                // /manalevel get targets <targets> [xp|levels]
                        ).then(Commands.literal("get")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .then(Commands.literal("xp")
                                                .executes((cmd) -> getManaLevel(cmd.getSource(), EntityArgument.getPlayer(cmd, "target"), Type.XP))
                                        ).then(Commands.literal("levels")
                                                .executes((cmd) -> getManaLevel(cmd.getSource(), EntityArgument.getPlayer(cmd, "target"), Type.LEVELS))
                                        )
                                )
                        )
        );

        dispatcher.register(Commands.literal("manalevel").redirect(literalCommandNode));
    }

    // region Commands
    private static int addManaLevel(CommandSource src, Collection<? extends ServerPlayerEntity> targets, int amount, Type type) {
        for(ServerPlayerEntity target : targets) {
            type.add.accept(target, amount);
        }

        if(targets.size() == 1) {
            src.sendSuccess(new TranslationTextComponent("commands." + Main.MODID + ".manalevel.add." + type.name + ".success.single", amount, targets.iterator().next().getDisplayName()), true);
        } else {
            src.sendSuccess(new TranslationTextComponent("commands." + Main.MODID + ".manalevel.add." + type.name + ".success.multiple", amount, targets.size()), true);
        }

        return targets.size();
    }

    private static int setManaLevel(CommandSource src, Collection<? extends ServerPlayerEntity> targets, int amount, Type type) {
        for(ServerPlayerEntity target : targets) {
            type.set.accept(target, amount);
        }

        if(targets.size() == 1) {
            src.sendSuccess(new TranslationTextComponent("commands." + Main.MODID + ".manalevel.set." + type.name + ".success.single", amount, targets.iterator().next().getDisplayName()), true);
        } else {
            src.sendSuccess(new TranslationTextComponent("commands." + Main.MODID + ".manalevel.set." + type.name + ".success.multiple", amount, targets.size()), true);
        }

        return targets.size();
    }

    private static int getManaLevel(CommandSource src, ServerPlayerEntity target, Type type) {
        int i = type.get.applyAsInt(target);
        src.sendSuccess(new TranslationTextComponent("commands." + Main.MODID + ".manalevel.get." + type.name + ".success", i, target.getDisplayName()), true);

        return i;
    }
    // endregion

    enum Type {
        XP("xp", ManaLevelUtils::addManaXp, ManaLevelUtils::setManaXp, ManaLevelUtils::getManaXp),
        LEVELS("levels", ManaLevelUtils::addManaLevel, ManaLevelUtils::setManaLevel, ManaLevelUtils::getManaLevel);

        private final String name;
        private final BiConsumer<ServerPlayerEntity, Integer> add;
        private final BiConsumer<ServerPlayerEntity, Integer> set;
        private final ToIntFunction<ServerPlayerEntity> get;

        Type(String name, BiConsumer<ServerPlayerEntity, Integer> add, BiConsumer<ServerPlayerEntity, Integer> set, ToIntFunction<ServerPlayerEntity> get) {
            this.name = name;
            this.add = add;
            this.set = set;
            this.get = get;
        }
    }
}
