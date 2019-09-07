package io.github.ukp123.dkenhanced.commands.Prott;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

class CreateRegion {



    static void createRegion(Player sender, String pgPlayer, DKEnhanced plugin, Boolean bypassPlimit) {
        if (!bypassPlimit) {

        }
        ProtectedRegion region;
        Region selection = CreateWorldEditSelection.getSelection(sender, plugin, true);
        assert selection != null;
        BlockVector3 min = selection.getMinimumPoint();
        BlockVector3 max = selection.getMaximumPoint();
        com.sk89q.worldedit.world.World selectionWorld = selection.getWorld();
        try {
            region = new ProtectedCuboidRegion(pgPlayer, min, max);
        } catch (IllegalArgumentException e) {
            sender.sendMessage(plugin.chatPrefix + ChatColor.RED + "Oled kasutanud ala nimes keelatud tähti. Ala ei loodud.");
            return;
        }
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        assert selectionWorld != null;
        RegionManager regions = container.get(selectionWorld);
        assert regions != null;
        regions.addRegion(region);
        applyFlagsAndOwner(region, pgPlayer, selection);
        sender.sendMessage(plugin.chatPrefix + ChatColor.GREEN + "Ala on loodud.");
    }

    static void boo(String PGplayer) {

    }

    private static void applyFlagsAndOwner(ProtectedRegion region, String pgPlayer, Region selection) {
        DefaultDomain owners = region.getOwners();
        owners.addPlayer(pgPlayer);
        region.setFlag(Flags.GREET_MESSAGE, "Tere tulemast " + pgPlayer + " alele!");
        region.setFlag(Flags.FAREWELL_MESSAGE, "Lahkusite " + pgPlayer + " alalt!");
        region.setFlag(Flags.RIDE, StateFlag.State.ALLOW);
        region.setFlag(Flags.TELE_LOC, getCenterLocation(selection));
    }
    private static com.sk89q.worldedit.util.Location getCenterLocation(Region selection) {
        Vector3 pos1 = selection.getMinimumPoint().toVector3();
        Vector3 pos2 = selection.getMaximumPoint().toVector3();

        Vector3 vectorCenter = pos1.add(pos2).divide(2).floor();
        BlockVector3 bv3Center = vectorCenter.toBlockPoint();
        World world = BukkitAdapter.adapt(Objects.requireNonNull(selection.getWorld()));
        Location center = new Location(world, bv3Center.getBlockX(), world.getHighestBlockYAt(BukkitAdapter.adapt(world, bv3Center)), bv3Center.getBlockZ());
        return (BukkitAdapter.adapt(center));
    }

}

