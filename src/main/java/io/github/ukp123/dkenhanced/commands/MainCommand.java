package io.github.ukp123.dkenhanced.commands;

import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.commandannatation.Command;
import io.github.ukp123.dkenhanced.commandannatation.CommandArgs;
import io.github.ukp123.dkenhanced.commandannatation.Completer;
import io.github.ukp123.dkenhanced.commands.Prott.MemberCommands.MemberCommand;
import io.github.ukp123.dkenhanced.commands.Prott.ProttCommand;
import io.github.ukp123.dkenhanced.commands.help.HelpCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;


public class MainCommand {

    private DKEnhanced plugin;

    public MainCommand(DKEnhanced p) {
        plugin = p;
    }

    @io.github.ukp123.dkenhanced.commandannatation.Command(name = "dk")
    public boolean dk(CommandArgs cargs) {
        Player player = (Player) cargs.getSender();
        String[] args = cargs.getArgs();
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
            case "add":
                MemberCommand.modifyMemberCommand(player, args, plugin, true);
                return true;
            case "remove":
            case "rem":
                MemberCommand.modifyMemberCommand(player, args, plugin, false);
                return true;
            default:
                player.sendMessage(plugin.replaceMessageVariables("ErrorMessages.unknown_arg", args[0]));
                return true;
        }
    }
    @Command(name = "test", aliases = { "testing" }, description = "This is a test command", usage = "This is how you use it")
    public void test(CommandArgs args) {
        args.getSender().sendMessage("This is a test command");
    }

    @Command(name = "test.sub", aliases = { "test.subcommand"})
    public void testSub(CommandArgs args) {
        args.getSender().sendMessage("This is a test subcommand");
    }

    @Command(name = "dk.lol", aliases = {"dk.loll", "omg"})
    @Completer(name = "dk.lol", aliases = {"dk.loll", "omg"})
    public List<String> dklol(CommandArgs args) {
        args.getSender().sendMessage("This is a dk lol or loll");
        args.getSender().sendMessage(args.getArgs()[0]);
        List<String> ls = new ArrayList<>();
        ls.add("hello men");
        ls.add("How ya doin");
        return ls;
    }

}





