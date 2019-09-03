package io.github.ukp123.dkenhanced;

import io.github.ukp123.dkenhanced.commands.HelpCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;


public class MainCommand implements CommandExecutor {

    DKEnhanced plugin;

    MainCommand(DKEnhanced p) {
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (plugin.getConfig().getString("permissions.help") == null) {
            player.sendMessage(plugin.chatPrefix + ChatColor.RED + "Plugin pole konfigureeritud. Palun kontakteeru administraatoriga");
            return true;
        }
        if (!player.hasPermission(Objects.requireNonNull(plugin.getConfig().getString("permissions.help")))) {
            player.sendMessage(plugin.noPermissionMessage);
            return true;
        }
        if (args.length == 0) {
            if (HelpCommand.commandList(player, plugin)) {
                return true;
            }
        }
        String lowerArg0 = args[0].toLowerCase();
        switch (lowerArg0) {
            case "help":
                if (HelpCommand.commandHelp(player, plugin, args)) {
                    return true;
                }
            default:
                player.sendMessage(plugin.chatPrefix + ChatColor.RED + "Tundmatu argument" + ChatColor.RESET + " - " + ChatColor.GRAY + args[0]);
                return true;
        }
    }

}



