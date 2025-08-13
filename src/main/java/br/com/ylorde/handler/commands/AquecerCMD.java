package br.com.ylorde.handler.commands;

import br.com.ylorde.Main;
import br.com.ylorde.commands.player.AquecerCommand;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

import java.util.Objects;

public record AquecerCMD(Main plugin) {
    public LiteralCommandNode<CommandSourceStack> build() {
        return Commands.literal("aquecer")
                .requires(sender -> sender.getSender().hasPermission(
                        Objects.requireNonNull(plugin.getConfig().getString("commands.aquecer.permission"))
                ))
                .executes(AquecerCommand::execute)
                .build();
    }
}
