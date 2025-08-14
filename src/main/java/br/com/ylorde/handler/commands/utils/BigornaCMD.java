package br.com.ylorde.handler.commands.utils;

import br.com.ylorde.Main;
import br.com.ylorde.commands.utils.BigornaCommand;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

import java.util.Objects;

public record BigornaCMD(Main plugin) {
    public LiteralCommandNode<CommandSourceStack> build() {
        return Commands.literal("bigorna")
                .requires(sender -> sender.getSender().hasPermission(
                        Objects.requireNonNull(plugin.getConfig().getString("commmands.bigorna.permission"))
                ))
                .executes(BigornaCommand::execute)
                .build();
    }
}
