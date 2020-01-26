package io.github.ukp123.dkenhanced.commands.Ev;

import com.github.intellectualsites.plotsquared.plot.object.Plot;
import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.utils.DatabaseUtils;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Set;
import java.util.UUID;


public class GradePlot extends PsUtils {

    private static String multiple_owners;

    public GradePlot(DKEnhanced p) {
        super(p);
    }

    public static boolean gradePlot(Player player, int hinne, DKEnhanced plugin) throws SQLException {
        DatabaseUtils.addAreaGrade(player, hinne);
        return true;
    }

    private static Object[] getOwnerUUIDs(Player player) {
        Plot currentPlot = getCurrentPlot(player);
        Set<UUID> plotownersUUID = currentPlot.getOwners();
        return plotownersUUID.toArray();
    }

}
