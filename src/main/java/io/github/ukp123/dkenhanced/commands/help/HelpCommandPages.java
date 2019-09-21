package io.github.ukp123.dkenhanced.commands.help;

import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

class HelpCommandPages {

    static void sCommandHelp(Player player, DKEnhanced plugin, String[] args) {
        if (args.length == 2) {
            String lowerArg1 = args[1].toLowerCase();
            switch (lowerArg1) {
                case "help":
                    player.sendMessage(plugin.replaceMessageVariables("SpecificHelpPage.help"));
                    return;
                case "prott":
                    player.sendMessage(plugin.replaceMessageVariables("SpecificHelpPage.prott"));
                    return;
                default:
                    String finalmessage = plugin.replaceMessageVariables("Error_Messages.unknown_arg");
                    finalmessage.replace("{ARGUMENT}", args[1]);
                    player.sendMessage(finalmessage);
                    return;
            }
        }
        return;
    }
    static boolean commandList(Player player, DKEnhanced plugin) {
        player.sendMessage(
        plugin.replaceMessageVariables("HelpPage.upper_line") + "\n" +
        plugin.replaceMessageVariables("HelpPage.help") + "\n" +
        plugin.replaceMessageVariables("HelpPage.prott") +
        plugin.replaceMessageVariables("HelpPage.bottom_line"));
        return true;
    }
    static boolean credits(Player player, DKEnhanced plugin) {
        player.sendMessage(
                plugin.replaceMessageVariables("HelpPage.upper_line") + "\n" +
                plugin.replaceMessageVariables("Credits.developer") + "\n" +
                plugin.replaceMessageVariables("Credits.version") + "\n" +
                plugin.replaceMessageVariables("Credits.help_command") + "\n" +
                plugin.replaceMessageVariables("Credits.prefix") + "\n" +
                plugin.replaceMessageVariables("HelpPage.bottom_line"));
        return true;
    }
}
