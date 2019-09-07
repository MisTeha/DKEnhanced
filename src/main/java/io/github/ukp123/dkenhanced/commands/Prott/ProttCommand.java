package io.github.ukp123.dkenhanced.commands.Prott;

import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class ProttCommand {


    public static void commandPrott(Player player, DKEnhanced plugin, String[] args) {
        if (plugin.getConfig().getString("permissions.prott") == null) {
            player.sendMessage(plugin.chatPrefix + ChatColor.RED + "Plugin ei ole konfigureeritud. Palun kontakteeru administraatoriga.");
            return;
        }
        if (!player.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("permissions.prott")))) {
            player.sendMessage(plugin.noPermissionMessage);
            return;
        }
        if (!checkWEAndWG(player, plugin)) {
            return;
        }
        if (CreateWorldEditSelection.getSelection(player, plugin, false) == null) {
            return;
        }
        switch (args.length) {
            case 1:
                player.sendMessage(plugin.chatPrefix + ChatColor.RED + "Palun sisesta mängija, kellele ala teed.");
                return;
            case 2:
                String pgPlayer = args[1];
                CreateRegion.createRegion(player, pgPlayer, plugin, false);
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
