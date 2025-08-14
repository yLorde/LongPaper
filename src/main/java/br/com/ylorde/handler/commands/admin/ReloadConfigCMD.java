package br.com.ylorde.handler.commands.admin;

import br.com.ylorde.Main;
import br.com.ylorde.commands.admin.ReloadConfigCommand;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

import java.util.Objects;

public record ReloadConfigCMD(Main plugin) {
    public LiteralCommandNode<CommandSourceStack> buid(String commandName) {
        return Commands.literal(commandName)
                .requires(sender -> sender.getSender().hasPermission(
                        Objects.requireNonNull(plugin.getConfig().getString("commands.reloadConfig.permission"))
                )).executes(ReloadConfigCommand::execute).build();
    }
}
