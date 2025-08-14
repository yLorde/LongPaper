package br.com.ylorde.handler.commands.player;

import br.com.ylorde.Main;
import br.com.ylorde.commands.player.CustomCraftRecipe;
import br.com.ylorde.commands.player.CustomFurnaceRecipe;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

import java.util.Objects;

public record CustomRecipesCMD(Main plugin) {
    public LiteralCommandNode<CommandSourceStack> build() {
        return Commands.literal("customRecipes")
                .then(Commands.literal("craft")
                        .requires(sender -> sender.getSender().hasPermission(
                                Objects.requireNonNull(plugin.getConfig().getString("commands.customRecipes.craft.permission"))
                        ))
                        .executes(CustomCraftRecipe::execute)
                )
                .then(Commands.literal("furnace")
                        .requires(sender -> sender.getSender().hasPermission(
                                Objects.requireNonNull(plugin.getConfig().getString("commands.customRecipes.furnace.permission"))
                        ))
                        .executes(CustomFurnaceRecipe::execute)
                )
                .build();
    }
}
