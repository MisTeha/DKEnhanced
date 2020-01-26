package io.github.ukp123.dkenhanced.commands.Ev;

import com.github.intellectualsites.plotsquared.plot.object.Location;
import com.github.intellectualsites.plotsquared.plot.object.Plot;
import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PsUtils {

    private static DKEnhanced plugin;

    public PsUtils(DKEnhanced p) {
        plugin = p;
    }

    protected static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    //Kui siin tekib error ja sa ei saa aru miks siis vaata kas database server töötab.. ma pole väga taibukas vahel..
    protected static Location getPlotLocation(Player player) {
        Location pl = new Location();
        org.bukkit.Location l = player.getLocation();
        pl.setX(l.getBlockX());
        pl.setY(l.getBlockY());
        pl.setZ(l.getBlockZ());
        pl.setWorld(l.getWorld().getName());
        return pl;
    }

    protected static String getAreaID(Player player) {
        return getPlotLocation(player).getPlotArea().toString();
    }

    protected static Player getPlayerfromUUID(UUID uuid) throws NullPointerException {
        Player p;
        p = plugin.getServer().getPlayer(uuid);
        if (p == null) throw new NullPointerException();
        return p;
    }

    protected static OfflinePlayer getOfflinePlayerFromUUID(UUID uuid) {
        return plugin.getServer().getOfflinePlayer(uuid);
    }

    protected static boolean areaCheck(Player player) {
        return getPlotLocation(player).isPlotArea();
    }

    protected static Plot getCurrentPlot(Player player) {
        return getPlotLocation(player).getOwnedPlot();
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
