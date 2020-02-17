package io.github.ukp123.dkenhanced.commands.Ev;

import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.utils.PsUtils;
import io.github.ukp123.dkenhanced.utils.messageutils.MessageUtils;
import io.github.ukp123.dkenhanced.utils.messageutils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Arrays;
//TODO: sellest peaks saama /ev, mida kasutavad kõik - adminid setupimisest kuni mängijateni kes tahavad ala.
public class HindaCommand extends PsUtils implements CommandExecutor {
    DKEnhanced plugin;

    public HindaCommand(DKEnhanced p) {
        plugin = p;
    }

    private boolean admin = false;


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage("Seda käsku ei saa konsoolis teha.");
            return true;
        }
        Player player = (Player) commandSender;
        if (player.hasPermission("dkenhanced.hinda.admin")) {
            admin = true;
        }
        if (!player.hasPermission("dkenhanced.hinda") && !admin) {
            MessageUtils.sendMessage(Messages.ERROR_NOPERM_MESSAGE, player);
            return true;
        }
        if (args.length > 0 && args[0].equalsIgnoreCase("admin") && admin) {
            String[] modargs = Arrays.copyOfRange(args, 1, args.length);
            try {
                AdminCommands.adminCommands(plugin, player, modargs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        if (!areaCheck(player)) {
            MessageUtils.sendMessage(Messages.HINDA_NOPLOTAREA, player);
            return true;
        }
        if (getCurrentPlot(player) == null) {
            MessageUtils.sendMessage(Messages.HINDA_NOPLOT, player);
            return true;
        }

        if (args.length == 0) {
            MessageUtils.sendMessage(Messages.HINDA_GRADE_UNDEFINED, player);
            return true;
        }

        if (args.length == 1) {
            if (PsUtils.isInt(args[0])) {
                try {
                    GradePlot.gradePlot(player, Integer.parseInt(args[0]), plugin);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return true;
    }
}