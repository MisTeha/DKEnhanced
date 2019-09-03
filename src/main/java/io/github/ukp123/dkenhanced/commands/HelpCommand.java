package io.github.ukp123.dkenhanced.commands;

import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HelpCommand {

    public static boolean commandHelp(Player player, DKEnhanced plugin, String[] args) {
        if (args.length == 1) {
            if (commandList(player, plugin)) {
                return true;
            }
        } else {
            if (sCommandHelp(player, plugin, args)) return true;
        }
        return true;
    }

    private static String asthLine = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "===== " + ChatColor.RESET + "" + ChatColor.DARK_AQUA + "DK" +
            ChatColor.AQUA + "Enhanced" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + " =====";
    private static String asthLineblw = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "======================";

    public static boolean sCommandHelp(Player player, DKEnhanced plugin, String[] args) {
        if (args.length == 2) {
            String lowerArg1 = args[1].toLowerCase();
            switch (lowerArg1) {
                case "help":
                    player.sendMessage(plugin.chatPrefix + ChatColor.AQUA + "/dk help [käsk] " + ChatColor.GRAY + "Näitab kõiki käske ja nende tähendusi | käsu täpsemat tähendust.");
                    return true;
                case "prott":
                    player.sendMessage(plugin.chatPrefix + ChatColor.AQUA + "/dk prott " + ChatColor.DARK_RED + "Arendaja peab siia veel midagi lisama.");
                    //TODO: Korralik seletus.
                    return true;
                default:
                    player.sendMessage(plugin.chatPrefix + plugin.chatUA + args[1]);
                    return true;
            }
        }
        return true;
    }

    public static boolean commandList(Player player, DKEnhanced plugin) {
        player.sendMessage(asthLine);
        player.sendMessage(ChatColor.DARK_AQUA + "/dk " + ChatColor.GRAY + "help" + ChatColor.RESET + " - näitab seda lehte.");
        player.sendMessage(ChatColor.DARK_AQUA + "/dk " + ChatColor.GRAY + "prott" + ChatColor.RESET + " - Lisab kaitstud ala sinu valitud alale.");
        player.sendMessage(asthLineblw);
        return true;
    }


}