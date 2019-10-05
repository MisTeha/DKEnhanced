package io.github.ukp123.dkenhanced.commands.Prott.MemberCommands;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.commands.Prott.ProttCommand;
import com.sk89q.worldedit.util.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MemberCommand {


    public static void addMemberCommand(Player player, String[] args, DKEnhanced plugin) {
        if (!ProttCommand.checkWEAndWG(player, plugin)) return;
        FileConfiguration messagesConfig = plugin.getMessagesConfig();
        FileConfiguration config = plugin.getConfig();
        String addPermission = "dkenhanced.member.add";
        String noPermissionMessage = plugin.replaceMessageVariables("ErrorMessages.no_permission_message");
        if (noPermissionMessage == null) {
            player.sendMessage(plugin.replaceMessageVariables("ErrorMessages.plugin_unconfigured"));
            return;
        }
        if (!player.hasPermission(addPermission)) {
            player.sendMessage(noPermissionMessage);
            return;
        }
        if (args.length == 1) {
            player.sendMessage(plugin.replaceMessageVariables("MemberCommands.add.player_undefined"));
            return;
        }
        Player member = plugin.getServer().getPlayer(args[1]);
        if (member == null) {
            player.sendMessage(plugin.replaceMessageVariables("ProttCommand.player_disconnected", args[1]));
            return;
        }
        getCurrentRegion(player);
    }

    private static ProtectedRegion getCurrentRegion(Player player) {
        Location loc = BukkitAdapter.adapt(player.getLocation());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(loc);
        ProtectedRegion hpRegion = null;
        int i = Integer.MIN_VALUE;
        for (ProtectedRegion region : set) {
            if (region.getPriority() >= i) {
                i = region.getPriority();
                hpRegion = region;
            }
        }
        return hpRegion;
    }

}
