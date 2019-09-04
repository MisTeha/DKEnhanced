package io.github.ukp123.dkenhanced.commands.help;

import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HelpCommand {

    public static boolean commandHelp(Player player, DKEnhanced plugin, String[] args) {
        if (plugin.getConfig().getString("permissions.help") == null) {
            player.sendMessage(plugin.chatPrefix + ChatColor.RED + "Plugin pole konfigureeritud. Palun kontakteeru administraatoriga");
            return true;
        }
        if (!player.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("permissions.help")))) {
            player.sendMessage(plugin.noPermissionMessage);
            return true;
        }
        if (args.length == 0) {
            if (HelpCommandPages.credits(player, plugin)) return true;
        } else  if (args.length == 1) {
            if (HelpCommandPages.commandList(player, plugin)) return true;
        } else { //if there are more than 1 arguments.
            if (HelpCommandPages.sCommandHelp(player, plugin, args)) return true;
        }
        return true;
    }






}