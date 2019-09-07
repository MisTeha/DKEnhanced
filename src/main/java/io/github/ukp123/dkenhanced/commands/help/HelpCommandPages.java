package io.github.ukp123.dkenhanced.commands.help;

import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

class HelpCommandPages {


    static boolean sCommandHelp(Player player, DKEnhanced plugin, String[] args) {
        if (args.length == 2) {
            String lowerArg1 = args[1].toLowerCase();
            switch (lowerArg1) {
                case "help":
                    player.sendMessage(plugin.chatPrefix + ChatColor.AQUA + "/dk " + ChatColor.DARK_AQUA + "help [käsk] " + ChatColor.GRAY + "Näitab kõiki käske ja nende tähendusi | käsu täpsemat tähendust.");
                    return true;
                case "prott":
                    player.sendMessage(plugin.chatPrefix + ChatColor.AQUA + "/dk " + ChatColor.DARK_AQUA + "prott " + ChatColor.GRAY + "Lisab protti alale, mille oled valinud. \n" +
                    ChatColor.RED + "See käsk pole täiustatud selles plugina versioonis.");
                    //TODO: Korralik seletus kui command on actually valmis tehtud.
                    return true;
                default:
                    player.sendMessage(plugin.chatPrefix + plugin.chatUA + args[1]);
                    return true;
            }
        }
        return true;
    }

    static boolean commandList(Player player, DKEnhanced plugin) {
        player.sendMessage(asthLine);
        player.sendMessage(ChatColor.AQUA + "/dk " + ChatColor.DARK_AQUA + "help" + ChatColor.GRAY + " - näitab seda lehte.");
        player.sendMessage(ChatColor.AQUA + "/dk " + ChatColor.DARK_AQUA + "prott" + ChatColor.GRAY + " - lisab kaitstud ala sinu valitud alale.");
        player.sendMessage(ChatColor.AQUA + "/dk " + ChatColor.DARK_AQUA + "reload" + ChatColor.GRAY + " - uuendab konfiguratsiooni.");
        player.sendMessage(asthLineblw);
        return true;
    }

    static boolean credits(Player player, DKEnhanced plugin) {
        player.sendMessage(
                asthLine + "\n" +
                ChatColor.DARK_AQUA + "Arendaja" + ChatColor.AQUA + " ● " + ChatColor.GRAY + "ukp123" + "\n" +
                ChatColor.DARK_AQUA + "Versioon" + ChatColor.AQUA + " ● " + ChatColor.GRAY + plugin.getDescription().getVersion() + "\n" +
                ChatColor.DARK_AQUA + "Abikäsk" + ChatColor.AQUA + " ● " + ChatColor.GRAY + "/dk help" + "\n" +
                ChatColor.DARK_AQUA + "Prefix" + ChatColor.AQUA + " ● " + ChatColor.RESET + plugin.chatPrefix + "\n" +
                asthLineblw);
        return true;
    }

    private static String asthLine = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "===== " + ChatColor.RESET + "" + ChatColor.DARK_AQUA + "DK" +
            ChatColor.AQUA + "Enhanced" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + " =====";
    private static String asthLineblw = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "====================";
}
