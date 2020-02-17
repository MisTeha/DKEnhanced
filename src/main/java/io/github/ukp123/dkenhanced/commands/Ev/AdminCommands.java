package io.github.ukp123.dkenhanced.commands.Ev;

import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.utils.PsUtils;
import io.github.ukp123.dkenhanced.utils.messageutils.MessageUtils;
import io.github.ukp123.dkenhanced.utils.messageutils.Messages;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class AdminCommands extends PsUtils {


    public static void adminCommands(DKEnhanced plugin, Player player, String[] args) throws SQLException {
        if (args.length == 0) {
            MessageUtils.sendMessage(Messages.HINDAADMIN_NOARG, player);
            return;
        }
    }
}
