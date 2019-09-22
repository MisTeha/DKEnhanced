package io.github.ukp123.dkenhanced.commands;

import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.commands.Prott.ProttCommand;
import io.github.ukp123.dkenhanced.commands.help.HelpCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MainCommand implements CommandExecutor {

    private DKEnhanced plugin;

    public MainCommand(DKEnhanced p) {
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (plugin.getConfig().getString("commands.prott.permission") == null) {
            player.sendMessage(plugin.replaceMessageVariables("ErrorMessages.no_permission_message"));
            return true;
        }
        if (args.length == 0) {
            HelpCommand.commandHelp(player, plugin, args);
            return true;
        }
        String lowerArg0 = args[0].toLowerCase();
        switch (lowerArg0) {
            case "help":
                HelpCommand.commandHelp(player, plugin, args);
                return true;
            case "prott":
                ProttCommand.commandPrott(player, plugin, args);
                return true;
            default:
                player.sendMessage(plugin.replaceMessageVariables("ErrorMessages.unknown_arg", args[0]));
                return true;
        }
    }
}




