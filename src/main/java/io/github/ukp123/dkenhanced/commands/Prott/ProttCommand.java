package io.github.ukp123.dkenhanced.commands.Prott;

import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class ProttCommand {


    public static void commandPrott(Player player, DKEnhanced plugin, String[] args) {
        if (plugin.getConfig().getString("commands.prott.permission") == null) {
            player.sendMessage(plugin.replaceMessageVariables("Error_Messages.unknown_arg"));
            return;
        }
        if (!player.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("commands.prott.permission")))) {
            player.sendMessage(plugin.replaceMessageVariables("ErrorMessages.no_permission_message"));
            return;
        }
        if (!checkWEAndWG(player, plugin)) {
            return;
        }
        if (CreateWorldEditSelection.getSelection(player, plugin, false) == null) {
            return;
        }
        if (args.length == 1) {
            player.sendMessage(plugin.replaceMessageVariables("ProttCommand.player_undefined"));
            return;
        }
        if (args.length >= 2) {
            boolean ignoreLimit = false;
            String pgPlayer = args[1];
            String ignorelimits = "-ignorelimit";
            for (String arg :
                    args) {
                if (arg.contentEquals(ignorelimits)) {
                    player.sendMessage(plugin.replaceMessageVariables("ProttCommand.ignoring_prot_limit"));
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
            player.sendMessage(plugin.replaceMessageVariables("ProttCommand.we_wg_undefined"));
            return false;
        } else if (tempWE == null) {
            player.sendMessage(plugin.replaceMessageVariables("ProttCommand.we_undefined"));
            return false;
        } else if (tempWG == null) {
            player.sendMessage(plugin.replaceMessageVariables("ProttCommand.wg_undefined"));
            return false;
        }
        return true;
    }
}
