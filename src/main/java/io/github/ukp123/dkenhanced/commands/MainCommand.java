package io.github.ukp123.dkenhanced.commands;

import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.commands.Prott.MemberCommands.MemberCommand;
import io.github.ukp123.dkenhanced.commands.Prott.ProttCommand;
import io.github.ukp123.dkenhanced.commands.help.HelpCommand;
import io.github.ukp123.dkenhanced.utils.messageutils.MessageUtils;
import io.github.ukp123.dkenhanced.utils.messageutils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;


public class MainCommand implements CommandExecutor {

    private DKEnhanced plugin;
    private Connection connection;


    public MainCommand(DKEnhanced p) {
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Konsooli commandid ei ole veel toetatud.");
            return true;
        }
        Player player = (Player) sender;
        if (plugin.getConfig().getString("commands.prott.permission") == null) {
            MessageUtils.sendMessage(Messages.ERROR_NOPERM_MESSAGE, player);
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
            case "add":
                MemberCommand.modifyMemberCommand(player, args, plugin, true);
                return true;
            case "remove":
            case "rem":
                MemberCommand.modifyMemberCommand(player, args, plugin, false);
                return true;
            case "testmessages":
                for (Messages m : Messages.values()) {
                    MessageUtils.sendMessage(m, player, ChatColor.DARK_RED + args[1]);
                }
            default:
                MessageUtils.sendMessage(Messages.ERROR_UNKNOWN_ARG, player, args[0]);
                return true;
        }
    }
}




