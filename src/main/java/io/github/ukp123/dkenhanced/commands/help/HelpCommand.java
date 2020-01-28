package io.github.ukp123.dkenhanced.commands.help;

import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.commands.utils.messageutils.MessageUtils;
import io.github.ukp123.dkenhanced.commands.utils.messageutils.Messages;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HelpCommand {

    public static void commandHelp(Player player, DKEnhanced plugin, String[] args) {
        FileConfiguration messages = plugin.getMessagesConfig();
        if (plugin.getConfig().getString("commands.help.permission") == null) {
            MessageUtils.sendMessage(Messages.ERROR_PLUGIN_UNCONFIGURED, player);
            return;
        }
        if (!player.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("commands.help.permission")))) {
            MessageUtils.sendMessage(Messages.ERROR_NOPERM_MESSAGE, player);
            return;
        }
        if (args.length == 0) {
            HelpCommandPages.credits(player);
            return;
        } else  if (args.length == 1) {
            HelpCommandPages.commandList(player);
            return;
        } else { //if there are more than 1 arguments.
            HelpCommandPages.sCommandHelp(player, args);
            return;
        }
    }






}