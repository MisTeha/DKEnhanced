package io.github.ukp123.dkenhanced.commands.Ev;

import io.github.ukp123.dkenhanced.utils.TimeUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CreateEvCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage("Seda käsku ei saa konsoolis teha.");
            return true;
        }
        Player player = (Player) commandSender;

        //TODO: permid
        if (args.length != 2) {
            player.sendMessage("valed argumendid");
            return true;
        }

        String name = args[0];
        try {
            long duration = TimeUtils.parseDateDiff(args[1], true);
        } catch (Exception e) {
            player.sendMessage();
        }


        return false;
    }
}
