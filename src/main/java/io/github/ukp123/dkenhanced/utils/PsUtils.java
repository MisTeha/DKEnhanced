package io.github.ukp123.dkenhanced.utils;


import com.github.intellectualsites.plotsquared.plot.object.Location;
import com.github.intellectualsites.plotsquared.plot.object.Plot;
import com.github.intellectualsites.plotsquared.plot.object.PlotArea;
import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PsUtils {

    public static DKEnhanced plugin;
    

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    //Kui siin tekib error ja sa ei saa aru miks siis vaata kas database server töötab.. ma pole väga taibukas vahel..
    public static Location getPlotLocation(org.bukkit.Location location) {
        return new Location(location.getWorld().getName(), location.getBlockX(),  location.getBlockY(), location.getBlockZ());
    }

    public static String getAreaID(Player player) {
        return getPlotLocation(player.getLocation()).getPlotArea().toString();
    }

    public static Player getPlayerfromUUID(UUID uuid) throws NullPointerException {
        Player p;
        p = plugin.getServer().getPlayer(uuid);
        if (p == null) throw new NullPointerException();
        return p;
    }

    public static OfflinePlayer getOfflinePlayerFromUUID(UUID uuid) {
        return plugin.getServer().getOfflinePlayer(uuid);
    }

    public static boolean areaCheck(Player player) {
        return getPlotLocation(player.getLocation()).isPlotArea();
    }

    public static PlotArea getPlotArea(org.bukkit.Location location) {
        return getPlotLocation(location).getPlotArea();
    }

    public static Plot getCurrentPlot(Player player) {
        return getPlotLocation(player.getLocation()).getOwnedPlot();
    }


        //seda võibolla vaja millegi jaoks kunagi mul pole aimugi
        /*
        player.sendMessage(Integer.toString(plotID));
        PlotId wa = PlotId.fromString("5;5");
        PlotPlayer plotplayer = PlotPlayer.wrap(player);
        PlotArea awd = plotplayer.getApplicablePlotArea();
        Plot hmm = new Plot(awd, wa);
        player.sendMessage(hmm.toString());
         */
}
