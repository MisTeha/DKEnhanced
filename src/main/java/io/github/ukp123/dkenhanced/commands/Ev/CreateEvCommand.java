package io.github.ukp123.dkenhanced.commands.Ev;

import com.github.intellectualsites.plotsquared.plot.object.PlotArea;
import io.github.ukp123.dkenhanced.utils.PsUtils;
import io.github.ukp123.dkenhanced.utils.TimeUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CreateEvCommand implements CommandExecutor {
    // TODO: 2020-01-28 permid
    // TODO: 2020-01-30 a default config.. samal päeval 18:00-21:00 ja siis teema
    // TODO: 2020-01-28 check for repeating names
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        String name;
        String startTimeStr;
        String endTimeStr;
        String theme;
        SimpleDateFormat sdf;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Tallinn");
        Calendar startTime = new Calendar.Builder().build();//  watefak is dis mdea see lic ei andnud errorit
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
        sdf = new SimpleDateFormat("dd.MM HH.mm");
        //sdf.setCalendar();
        try {
            duration = TimeUtils.parseDateDiff(args[3], true);
            ifDuration = true;
        } catch (Exception e) {
            ifDuration = false;
        }
        if (ifDuration) {
            if (args.length < 5) {
                player.sendMessage("lisage teema"); // TODO: 2020-02-15
                return true;
            }

            startTimeStr = String.join(" ", Arrays.copyOfRange(args, 1, 3));
            player.sendMessage("FOR DEBUG: " + startTimeStr); //for debug
            sdf = new SimpleDateFormat("dd.MM HH.mm");
            sdf.setCalendar(startTime);
            try {
                sdf.parse(startTimeStr);
                //need read siin üleval on mingid imelikud, ilmselt kaugel õigest.
            } catch (ParseException e) {
                player.sendMessage("Alguse aeg pole õige");
                return true;
            }
            theme = String.join(" ", Arrays.copyOfRange(args, 4, args.length));

            new BuildingContest(name, theme, plotArea, startTimeDate, duration);
        }

        startTimeStr = args[1] + " " + args[2];
        endTimeStr = args[3] + " " + args[4];
        theme = String.join(" ", Arrays.copyOfRange(args, 5, args.length));
        



        return false;
    } // /createev ev 08.05 15:00 09.05 23:59 Ilus moderne maja
}
