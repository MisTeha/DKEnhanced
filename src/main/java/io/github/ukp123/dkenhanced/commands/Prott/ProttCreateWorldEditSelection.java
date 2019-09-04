package io.github.ukp123.dkenhanced.commands.Prott;

import com.google.gson.JsonElement;
import com.google.gson.internal.$Gson$Preconditions;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ProttCreateWorldEditSelection {

    private static WorldEditPlugin wep;
    private static WorldGuardPlugin wgp;
    private static Region selection;
    private static Player pgPlayer; //ProtGettingPlayer - player who is getting the protection.
    static String lowerUnCheckedName;

    public static boolean commandPrott(Player player, DKEnhanced plugin, String[] args) {
        if (!checkAndCreatePlugins(player, plugin)) {
            return true;
        }
        if (!checkAndCreateSelection(player, plugin)) {
            return true;
        }
        if (args.length == 1) {
            player.sendMessage(plugin.chatPrefix + ChatColor.RED + "Palun sisesta mängija, kellele ala teed.");
            return true;
        }
        if (args.length == 2) {
            if (!checkIfNameIsPlayer(player, plugin, args[1].toLowerCase())) return true;
        }
        return true;
    }

    //if WorldEdit and WorldGuard is installed, returns true and creates WorldGuardPlugin and WorldEditPlugin variables. Otherwise returns false.
    private static boolean checkAndCreatePlugins (Player player, DKEnhanced plugin) {
        if (plugin.getServer().getPluginManager().getPlugin("WorlEdit") == null) {
            player.sendMessage(plugin.chatPrefix + ChatColor.RED + "Pluginat WorldEdit pole installitud. /prott käsk ei tööta selleta. Vajadusel kontakteeru adminstraatoriga.");
            return false;
        }
        if (plugin.getServer().getPluginManager().getPlugin("WorldGuard") == null) {
            player.sendMessage(plugin.chatPrefix + ChatColor.RED + "Pluginat WorldGuard pole installitud. /prott käsk ei tööta selleta. Vajadusel kontakteeru administraatoriga.");
            return false;
        }
        wep = (WorldEditPlugin)plugin.getServer().getPluginManager().getPlugin("WorldEdit");
        wgp = (WorldGuardPlugin)plugin.getServer().getPluginManager().getPlugin("WorldGuard");
        return true;
    }
    //if player's selection doesn't throw IncompleteRegionException (player hasn't selected a region), saves player's selection and returns true. Otherwise returns false.
    private static boolean checkAndCreateSelection(Player player, DKEnhanced plugin) {
        try {
            selection = wep.getSession(player).getSelection(BukkitAdapter.adapt(player.getWorld()));
            return true;
        } catch (IncompleteRegionException e) {
            player.sendMessage(plugin.chatPrefix + ChatColor.RED + "Ala valimatta.");
            return false;
        }
    }
    //if given username is recognized as a player on a server (usually if player is online), saves the player and returns true. Otherwise returns false.
    private static boolean checkIfNameIsPlayer (Player player, DKEnhanced plugin, String lowerUnCheckedName) {
        Player convertedPgPlayer = plugin.getServer().getPlayer(lowerUnCheckedName);
        if (convertedPgPlayer == null) {
            player.sendMessage(plugin.chatPrefix + ChatColor.RED + "Mängijat ei leitud. Kui lood ala mängijale, kes on offline või pole veel serveriga liitunud, " +
                    "lisa prott käsu lõppu lipp -offline.");
            return false;
        }
        pgPlayer = convertedPgPlayer;
        return true;
    }
}
