package io.github.ukp123.dkenhanced.commands.Prott;

import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.utils.messageutils.MessageUtils;
import io.github.ukp123.dkenhanced.utils.messageutils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class ProttCommand {


    public static void commandPrott(Player player, DKEnhanced plugin, String[] args) {
        if (plugin.getConfig().getString("commands.prott.permission") == null) {
            MessageUtils.sendMessage(Messages.ERROR_PLUGIN_UNCONFIGURED, player);
            return;
        }
        if (!player.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("commands.prott.permission")))) {
            MessageUtils.sendMessage(Messages.ERROR_NOPERM_MESSAGE, player);
            return;
        }
        if (!checkWEAndWG(player, plugin)) {
            return;
        }
        if (CreateWorldEditSelection.getSelection(player, plugin, false) == null) {
            return;
        }
        if (args.length == 1) {
            MessageUtils.sendMessage(Messages.PROTT_PLAYER_UNDEFINED, player);
            return;
        }
        if (args.length >= 2) {
            boolean ignoreLimit = false;
            String pgPlayer = args[1];
            String ignorelimits = "-ignorelimit";
            for (String arg :
                    args) {
                if (arg.contentEquals(ignorelimits)) {
                    MessageUtils.sendMessage(Messages.PROTT_IGNORING_PROTLIMIT, player);
                    ignoreLimit = true;
                }
            }
            CreateRegion.createRegion(player, pgPlayer, plugin, ignoreLimit);
        }
    }

    public static boolean checkWEAndWG(Player player, DKEnhanced plugin) {
        Plugin tempWE = plugin.getServer().getPluginManager().getPlugin("WorldEdit");
        Plugin tempWG = plugin.getServer().getPluginManager().getPlugin("WorldGuard");
        if (tempWE == null && tempWG == null) {
            MessageUtils.sendMessage(Messages.PROTT_WE_WG_UNDEFINED, player);
            return false;
        } else if (tempWE == null) {
            MessageUtils.sendMessage(Messages.PROTT_WE_UNDEFINED, player);
            return false;
        } else if (tempWG == null) {
            MessageUtils.sendMessage(Messages.PROTT_WG_UNDEFINED, player);
            return false;
        }
        return true;
    }
}
