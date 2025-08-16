package br.com.ylorde.commands.player;

import br.com.ylorde.utils.Console;
import br.com.ylorde.utils.ConvertToColoredText;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AquecerCustomCommand {
    public static int execute(@NotNull CommandContext<CommandSourceStack> ctx) {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("LongPaper");
        assert plugin != null;

        CommandSender sender = ctx.getSource().getSender();
        Entity executor = ctx.getSource().getExecutor();

        ConvertToColoredText convertToColoredText = new ConvertToColoredText();
        Console console = new Console();

        if (!(executor instanceof Player player)) {
            sender.sendRichMessage("<red><bold>ERRO</bold></red><yellow> Apenas jogadores podem usar esse comando!</yellow>");
            return Command.SINGLE_SUCCESS;
        }

        if (sender == executor) {
            ItemStack handItem = player.getItemInHand();

            if (handItem.isEmpty()) {
                if (plugin.getConfig().getBoolean("commands.config.soundOnUse")) {
                    player.playSound(player, Objects.requireNonNull(plugin.getConfig().getString("commands.config.closeSound")), 1,1);
                };

                player.sendMessage(convertToColoredText.convert(
                        Objects.requireNonNull(plugin.getConfig().getString("commands.aquecer.customItems.onErrorMessage"))
                ));
                return Command.SINGLE_SUCCESS;
            }

            String itemName = handItem.getType().name();

            if (!handItem.getItemMeta().hasCustomModelData()) {
                if (plugin.getConfig().getBoolean("commands.config.soundOnUse")) {
                    player.playSound(player, Objects.requireNonNull(plugin.getConfig().getString("commands.config.closeSound")), 1,1);
                }

                player.sendMessage(convertToColoredText.convert(
                        Objects.requireNonNull(plugin.getConfig().getString("commands.aquecer.customItems.onErrorMessage"))
                ));

                return Command.SINGLE_SUCCESS;
            }

            String onUseMessage = plugin.getConfig().getString("commands.aquecer.customItems.list."+handItem.getItemMeta().getCustomModelData()+".onUseMessage");
            String itemType = plugin.getConfig().getString("commands.aquecer.customItems.list."+handItem.getItemMeta().getCustomModelData()+".material");

            if (!itemName.equals(itemType)) {
                if (plugin.getConfig().getBoolean("commands.config.soundOnUse")) {
                    player.playSound(player, Objects.requireNonNull(plugin.getConfig().getString("commands.config.closeSound")), 1,1);
                }

                player.sendMessage(convertToColoredText.convert(
                        Objects.requireNonNull(plugin.getConfig().getString("commands.aquecer.customItems.onErrorMessage"))
                ));

                return Command.SINGLE_SUCCESS;
            }

            if (onUseMessage == null) {
                if (plugin.getConfig().getBoolean("commands.config.soundOnUse")) {
                    player.playSound(player, Objects.requireNonNull(plugin.getConfig().getString("commands.config.closeSound")), 1,1);
                }

                player.sendMessage(convertToColoredText.convert(
                        Objects.requireNonNull(plugin.getConfig().getString("commands.aquecer.customItems.onErrorMessage"))
                ));

                return Command.SINGLE_SUCCESS;
            }

            String command = plugin.getConfig().getString("commands.aquecer.customItems.list."+handItem.getItemMeta().getCustomModelData()+".consoleCommand");

            int itemSize = handItem.getAmount();
            int multiplier = plugin.getConfig().getInt("commands.aquecer.customItems.list."+handItem.getItemMeta().getCustomModelData()+".multiplier");

            handItem.subtract(itemSize);
            assert command != null;

            command = command
                    .replace("%player_nickname", player.getName())
                    .replace("%amount", String.valueOf(itemSize * multiplier));

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);

            player.sendMessage(convertToColoredText.convert(onUseMessage.replace("%amount", String.valueOf(itemSize))));

            if (plugin.getConfig().getBoolean("commands.aquecer.enableSound")) {
                player.playSound(player, Objects.requireNonNull(plugin.getConfig().getString("commands.aquecer.sound")), 1,1);
            }

        }

        return Command.SINGLE_SUCCESS;
    }
}
