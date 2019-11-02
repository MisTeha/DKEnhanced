package io.github.ukp123.dkenhanced.commands.plotsquared;

import io.github.ukp123.dkenhanced.db.DatabaseUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class test implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player player = (Player) sender;

        String playerWorldName = player.getWorld().getName();
        try {
            DatabaseUtils.createTable(playerWorldName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
