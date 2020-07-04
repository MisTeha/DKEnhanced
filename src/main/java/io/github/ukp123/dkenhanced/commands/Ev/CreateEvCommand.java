package io.github.ukp123.dkenhanced.commands.Ev;

import com.plotsquared.core.plot.PlotArea;
import io.github.ukp123.dkenhanced.utils.PsUtils;
import io.github.ukp123.dkenhanced.utils.TimeUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class CreateEvCommand implements CommandExecutor {
    // TODO: 2020-01-28 permid
    // TODO: 2020-01-30 a default config.. samal päeval 18:00-21:00 ja siis teema
    // TODO: 2020-01-28 check for repeating names
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        final TimeZone timeZone = TimeUtils.timeZone;
        ZonedDateTime now = ZonedDateTime.now(timeZone.toZoneId());
        Date nowDate = Date.from(now.toInstant());
        String name;
        String startTimeStr;
        String endTimeStr;
        String theme;
        SimpleDateFormat sdf;
        Date startTimeDate = null;
        Date endTime;
        long duration = 0;
        PlotArea plotArea;
        boolean ifDuration;

        if (commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage("Seda käsku ei saa konsoolis teha.");
            return true;
        }
        Player player = (Player) commandSender;

        plotArea = PsUtils.getPlotArea(player.getLocation());
        if (plotArea == null) {
            player.sendMessage("sa pole plottide alal.");
            return true;
        }

        if (args.length < 2) {
            player.sendMessage("createev (nimi) (alguse kuupäev) (alguse aeg) (lõpu kuupäev) (lõpu aeg) (teema) või \n createev (nimi) (alguse kuupäev) (alguse aeg) (kestvus) (teema)");
            return true;
        }
        name = args[0];


        try {
            duration = TimeUtils.parseDateDiff(args[3], true);
            ifDuration = true;
        } catch (Exception e) {
            player.sendMessage(ChatColor.YELLOW + "DEBUG: not using duration");
            ifDuration = false;
        }

        if (ifDuration) {
            if (args.length < 5) {
                player.sendMessage("lisage teema"); // TODO: 2020-02-15
                return true;
            }

            try {
                startTimeDate = getStartTime(args, now);
            } catch (ParseException e) {
                player.sendMessage("Alguse aeg pole õige");
                return true;
            }

            theme = String.join(" ", Arrays.copyOfRange(args, 4, args.length));

            try {
                new BuildingContest(name, theme, plotArea, startTimeDate, duration);
                player.sendMessage(ChatColor.GREEN + "ev loodud"); // TODO: 2020-02-23 korralik message
                return true;
            } catch (SQLException e) {
                player.sendMessage(ChatColor.RED + "Midagi läks valesti: " + ChatColor.RESET + e.toString()); // TODO: 2020-02-23 korralik message
                e.printStackTrace();
                return true;
            }

        }
        try {
            startTimeDate = getStartTime(args, now);
        } catch (ParseException e) {
            player.sendMessage("Alguse aeg pole õige");
            return true;
        }
        return true;
    }
    // /createev ev 08.05 15:00 09.05 23:59 Ilus moderne maja

    private Date getStartTime(String[] args, ZonedDateTime now) throws ParseException {
        String startTimeStr = String.join(" ", Arrays.copyOfRange(args, 1, 3));
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM HH.mm yyyy");
        String timeString = startTimeStr + " " + now.getYear();
        return sdf.parse(timeString);
    }
}

