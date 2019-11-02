package io.github.ukp123.dkenhanced.commands.plotsquared;

import com.github.intellectualsites.plotsquared.api.PlotAPI;
import com.github.intellectualsites.plotsquared.plot.config.Settings;
import com.github.intellectualsites.plotsquared.plot.object.Plot;
import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Array;
import java.util.Set;
import java.util.UUID;


public class hinda implements CommandExecutor {
    DKEnhanced plugin;

    public hinda(DKEnhanced p) {
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        PlotAPI api = new PlotAPI();
        Location l = player.getLocation();
        com.github.intellectualsites.plotsquared.plot.object.Location pl = new com.github.intellectualsites.plotsquared.plot.object.Location();
        pl.setX(l.getBlockX());
        pl.setY(l.getBlockY());
        pl.setZ(l.getBlockZ());
        pl.setWorld(l.getWorld().getName());
        Plot currentplot = pl.getOwnedPlot();
        //TODO: kõik messaged ikka configi ka panna :face_palm:
        if (currentplot == null) {
            player.sendMessage(ChatColor.RED + "sa ei asu kellegi alal");
            return true;
        }
        Set<UUID> plotownersUUID = currentplot.getOwners();
        Object[] plotownersUUIDa = plotownersUUID.toArray();
        if (plotownersUUIDa.length > 0) {
            player.sendMessage(ChatColor.RED + "Siin alal on rohkem kui 1 omanik. Palun üleliigsed eemaldada.");
        }
        UUID plotownerUUID = (UUID) plotownersUUIDa[0];
        player.sendMessage(plotownerUUID.toString());
        return true;
    }
}
