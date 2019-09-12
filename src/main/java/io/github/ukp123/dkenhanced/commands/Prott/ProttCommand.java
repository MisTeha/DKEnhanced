package io.github.ukp123.dkenhanced.commands.Prott;

import com.sk89q.minecraft.util.commands.CommandContext;
import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class ProttCommand {


    public static void commandPrott(Player player, DKEnhanced plugin, String[] args) {
        if (plugin.getConfig().getString("commands.prott.permission") == null) {
            player.sendMessage(plugin.chatPrefix + ChatColor.RED + "Plugin ei ole konfigureeritud. Palun kontakteeru administraatoriga.");
            return;
        }
        if (!player.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("commands.prott.permission")))) {
            player.sendMessage(plugin.noPermissionMessage);
            return;
        }
        if (!checkWEAndWG(player, plugin)) {
            return;
        }
        if (CreateWorldEditSelection.getSelection(player, plugin, false) == null) {
            return;
        }
        if (args.length == 1) {
            player.sendMessage(plugin.chatPrefix + ChatColor.RED + "Palun sisesta mängija, kellele ala teed.");
            return;
        }
        if (args.length >= 2) {
            boolean ignoreLimit = false;
            String pgPlayer = args[1];
            String ignorelimits = "-ignorelimit";
            for (String arg :
                    args) {
                if (arg.contentEquals(ignorelimits)) {
                    player.sendMessage(plugin.chatPrefix + ChatColor.YELLOW + "Ignoreerin prottide limiiti.");
                    ignoreLimit = true;
                }
            }
            CreateRegion.createRegion(player, pgPlayer, plugin, ignoreLimit);
        }
    }

    private static boolean checkWEAndWG(Player player, DKEnhanced plugin) {
        Plugin tempWE = plugin.getServer().getPluginManager().getPlugin("WorldEdit");
        Plugin tempWG = plugin.getServer().getPluginManager().getPlugin("WorldGuard");
        if (tempWE == null && tempWG == null) {
            player.sendMessage(plugin.chatPrefix + ChatColor.RED + "WorldEdit ega WordGuard ei ole installitud. " + ChatColor.GRAY + "/dk prott " + ChatColor.RED + "ei toimi nendeta.");
            return false;
        } else if (tempWE == null) {
            player.sendMessage(plugin.chatPrefix + "WorldEdit ei ole installitud. " + ChatColor.GRAY + "/dk prott " + ChatColor.RED + "ei tööta selleta.");
            return false;
        } else if (tempWG == null) {
            player.sendMessage(plugin.chatPrefix + ChatColor.RED + "WorldGuard ei ole installitud. " + ChatColor.GRAY + "/dk prott " + ChatColor.RED + "ei tööta selleta.");
            return false;
        }
        return true;
    }
}
