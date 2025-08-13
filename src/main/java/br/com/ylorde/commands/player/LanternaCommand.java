package br.com.ylorde.commands.player;

import br.com.ylorde.utils.ConvertToColoredText;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LanternaCommand {
    public static int execute(@NotNull CommandContext<CommandSourceStack> ctx) {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("LongPaper");
        assert plugin != null;

        CommandSender sender = ctx.getSource().getSender();
        Entity executor = ctx.getSource().getExecutor();

        if (!(executor instanceof Player player)) {
            sender.sendRichMessage("<red><bold>ERRO</bold></red> <yellow>Apenas jogadores pode usar esse comando!</yellow>");
            return Command.SINGLE_SUCCESS;
        }

        if (sender == executor) {
            PotionEffectType effectType = PotionEffectType.NIGHT_VISION;

            if (player.hasPotionEffect(effectType)) {
                player.removePotionEffect(effectType);

                if (plugin.getConfig().getBoolean("commands.config.soundOnUse")) {
                    player.playSound(player, Objects.requireNonNull(plugin.getConfig().getString("commands.config.closeSound")), 1 ,1);
                }

                player.sendMessage(
                        new ConvertToColoredText().convert(Objects.requireNonNull(plugin.getConfig().getString("commands.lanterna.onDisableMessage")))
                );
            } else {

                if (plugin.getConfig().getBoolean("commands.config.soundOnUse")) {
                    player.playSound(player, Objects.requireNonNull(plugin.getConfig().getString("commands.config.openSound")), 1,1);
                }

                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 255, false, false, false));
                player.sendMessage(
                        new ConvertToColoredText().convert(
                                Objects.requireNonNull(plugin.getConfig().getString("commands.lanterna.onEnableMessage"))
                        )
                );
            }
        }

        return Command.SINGLE_SUCCESS;
    }
}
