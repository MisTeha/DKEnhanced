package io.github.ukp123.dkenhanced.commands.Prott;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionOperationException;
import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.commands.utils.messageutils.MessageUtils;
import io.github.ukp123.dkenhanced.commands.utils.messageutils.Messages;
import org.bukkit.entity.Player;


class CreateWorldEditSelection {

    private static WorldEditPlugin wep;

    //if player's selection doesn't throw IncompleteRegionException (player hasn't selected a region), saves player's selection and returns true. Otherwise returns false.
    static Region getSelection(Player player, DKEnhanced plugin, Boolean expandVert) {
        wep = (WorldEditPlugin) plugin.getServer().getPluginManager().getPlugin("WorldEdit");
        try {
            Region selection;
            assert wep != null;
            selection = wep.getSession(player).getSelection(BukkitAdapter.adapt(player.getWorld()));
            if (expandVert) {
                selection.expand(
                        BlockVector3.at(0, (255 + 1), 0),
                        BlockVector3.at(0, -(255 + 1), 0)
                );
            }
            return selection;
        } catch (IncompleteRegionException | RegionOperationException e) {
            MessageUtils.sendMessage(Messages.PROTT_SELECTION_UNDEFINED, player);
            return null;
        }
    }

}
