package io.github.ukp123.dkenhanced.commands.Ev;

import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class AdminCommands extends PsUtils {

    public AdminCommands(DKEnhanced p) {
        super(p);
    }

    public static void adminCommands(DKEnhanced plugin, Player player, String[] args) throws SQLException {
        if (args.length == 0) {
            player.sendMessage(plugin.replaceMessageVariables("HindaCommand.admin.no_arg_given"));
            return;
        }
    }
}
