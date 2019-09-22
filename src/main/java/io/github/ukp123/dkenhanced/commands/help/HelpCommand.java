package io.github.ukp123.dkenhanced.commands.help;

import com.zachsthings.libcomponents.config.ConfigurationFile;
import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HelpCommand {

    public static void commandHelp(Player player, DKEnhanced plugin, String[] args) {
        FileConfiguration messages = plugin.getMessagesConfig();
        if (plugin.getConfig().getString("commands.help.permission") == null) {
            player.sendMessage(plugin.replaceMessageVariables("ErrorMessages.plugin_unconfigured"));
            return;
        }
        if (!player.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("commands.help.permission")))) {
            player.sendMessage(plugin.replaceMessageVariables("ErrorMessages.no_permission_message"));
            return;
        }
        if (args.length == 0) {
            HelpCommandPages.credits(player, plugin);
            return;
        } else  if (args.length == 1) {
            HelpCommandPages.commandList(player, plugin);
            return;
        } else { //if there are more than 1 arguments.
            HelpCommandPages.sCommandHelp(player, plugin, args);
            return;
        }
    }






}