package io.github.ukp123.dkenhanced.commands.Ev;

import com.plotsquared.core.plot.Plot;
import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.utils.PsUtils;
import io.github.ukp123.dkenhanced.utils.database.DatabaseUtils;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Set;
import java.util.UUID;


public class GradePlot extends PsUtils {

    private static String multiple_owners;


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
