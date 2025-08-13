package br.com.ylorde.commands.player;

import br.com.ylorde.utils.ConvertToColoredText;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LixeiraCommand {
    public static int execute(@NotNull CommandContext<CommandSourceStack> ctx) {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("LongPaper");
        assert plugin != null;

        CommandSender sender = ctx.getSource().getSender();
        Entity executor = ctx.getSource().getExecutor();

        ConvertToColoredText convertToColoredText = new ConvertToColoredText();

        if (!(executor instanceof Player player)) {
            sender.sendRichMessage("<red><bold>ERRO</bold></red> <yellow>Apenas jogadores pode usar esse comando!</yellow>");
            return Command.SINGLE_SUCCESS;
        }

        Inventory inventory = Bukkit.createInventory(
                player,
                plugin.getConfig().getInt("commands.lixeira.slots"),
                convertToColoredText.convert(Objects.requireNonNull(plugin.getConfig().getString("commands.lixeira.interfaceTitle")))
        );

        sender.sendMessage(convertToColoredText.convert(
                Objects.requireNonNull(plugin.getConfig().getString("commands.lixeira.onUseMessage"))
        ));

        player.openInventory(inventory);

        if (plugin.getConfig().getBoolean("commands.config.soundOnUse")) {
            player.playSound(player, Objects.requireNonNull(plugin.getConfig().getString("commands.config.openSound")), 1,1);
        };

        return Command.SINGLE_SUCCESS;
    }
}
