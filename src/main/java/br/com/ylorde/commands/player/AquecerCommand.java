package br.com.ylorde.commands.player;

import br.com.ylorde.utils.ConvertToColoredText;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class AquecerCommand {
    public static int execute(@NotNull CommandContext<CommandSourceStack> ctx) {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("LongPaper");
        assert plugin != null;

        CommandSender sender = ctx.getSource().getSender();
        Entity executor = ctx.getSource().getExecutor();

        ConvertToColoredText convertToColoredText = new ConvertToColoredText();

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
                        Objects.requireNonNull(plugin.getConfig().getString("commands.aquecer.invalidItemMessage"))
                ));
                return Command.SINGLE_SUCCESS;
            }

            String itemName = handItem.getType().name();
            String resultItem = plugin.getConfig().getString("commands.aquecer.items."+itemName);
            String customItem = plugin.getConfig().getString("commands.aquecer.custom."+itemName);

            int itemSize = handItem.getAmount();

            if (customItem != null) {
                int amount = plugin.getConfig().getInt("commands.aquecer.custom."+itemName+".amount");
                List<String> lore = plugin.getConfig().getStringList("commands.aquecer.custom."+itemName+".lore");
                String result = Objects.requireNonNull(plugin.getConfig().getString("commands.aquecer.custom."+itemName+".result"));
                String recipeName = Objects.requireNonNull(plugin.getConfig().getString("commands.aquecer.custom."+itemName+".recipeName"));
                int resultAmount = plugin.getConfig().getInt("commands.aquecer.custom."+itemName+".resultAmount");

                if (itemSize < amount) {
                    player.sendMessage(convertToColoredText.convert(
                            Objects.requireNonNull(plugin.getConfig().getString("commands.aquecer.customItemsInsufficientMessage"))
                                    .replace("%amount", String.valueOf(amount))
                                    .replace("%recipeName", recipeName)
                    ));
                    return Command.SINGLE_SUCCESS;
                }

                ItemStack newCustomItem = new ItemStack(
                        Material.valueOf(result), resultAmount
                );

                newCustomItem.setLore(lore);

                player.getItemInHand().subtract(amount);
                player.give(newCustomItem);

                player.sendMessage(convertToColoredText.convert(
                        Objects.requireNonNull(plugin.getConfig().getString("commands.aquecer.custom."+itemName+".message"))
                ));
                return Command.SINGLE_SUCCESS;
            }

            if (resultItem == null)
            {
                if (plugin.getConfig().getBoolean("commands.config.soundOnUse")) {
                    player.playSound(player, Objects.requireNonNull(plugin.getConfig().getString("commands.config.closeSound")), 1,1);
                }

                player.sendMessage(convertToColoredText.convert(
                        Objects.requireNonNull(plugin.getConfig().getString("commands.aquecer.invalidItemMessage"))
                ));

                return Command.SINGLE_SUCCESS;
            }

            ItemStack smeltItem = new ItemStack(Material.valueOf(
                    plugin.getConfig().getString("commands.aquecer.items."+itemName)
            ), itemSize);

            player.sendMessage(convertToColoredText.convert(
                    Objects.requireNonNull(plugin.getConfig().getString("commands.aquecer.onUseMessage"))
                            .replace("%amount", String.valueOf(itemSize))
            ));

            player.getItemInHand().subtract(itemSize);
            player.setItemInHand(smeltItem);

            if (plugin.getConfig().getBoolean("commands.config.soundOnUse")) {
                player.playSound(player, Objects.requireNonNull(plugin.getConfig().getString("commands.config.openSound")), 1,1);
            }
        }

        return Command.SINGLE_SUCCESS;
    }
}
