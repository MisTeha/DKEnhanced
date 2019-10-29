package io.github.ukp123.dkenhanced.commands.Prott.MemberCommands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import com.sun.org.apache.xpath.internal.operations.Mod;
import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.commands.Prott.ProttCommand;
import com.sk89q.worldedit.util.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class MemberCommand {

    public static void modifyMemberCommand(Player player, String[] args, DKEnhanced plugin, boolean add) {
        if (!ProttCommand.checkWEAndWG(player, plugin)) return;
        FileConfiguration messagesConfig = plugin.getMessagesConfig();
        FileConfiguration config = plugin.getConfig();
        String addPermission = "dkenhanced.member.add";
        String removePermission = "dkenhanced.member.remove";
        String noPermissionMessage = plugin.replaceMessageVariables("ErrorMessages.no_permission_message");
        String noRegion = plugin.replaceMessageVariables("MemberCommands.no_region");
        String playerNotOwner = plugin.replaceMessageVariables("MemberCommands.player_not_owner");
        String playerUndefined = plugin.replaceMessageVariables("MemberCommands.player_undefined");
        String playerDisconnected = "Midagi läks valesti.. MemberCommand.java:33"; //Kui kõik töötab, ei lähe see väärtus kunagi kasutusse.
        String memberAdded = plugin.replaceMessageVariables("MemberCommands.member_added");
        String memberRemoved = plugin.replaceMessageVariables("MemberCommands.member_removed");
        boolean hasWgPerms = false;
        if (noPermissionMessage == null) {
            player.sendMessage(plugin.replaceMessageVariables("ErrorMessages.plugin_unconfigured"));
            return;
        }
        if (add) {
            if (!player.hasPermission("worldguard.region.addmember.*")) {
                if (!player.hasPermission(addPermission)) {
                    player.sendMessage(noPermissionMessage);
                    return;
                }
            } else {hasWgPerms = true;}
        } else {
            if (!player.hasPermission("worldguard.region.removemember.*")) {
                if (!player.hasPermission(removePermission)) {
                    player.sendMessage(noPermissionMessage);
                    return;
                }
            } else {hasWgPerms = true;}
        }
        if (getCurrentRegion(player) == null) {
            player.sendMessage(noRegion);
            return;
        }
        if (!playerIsOwner(player, plugin) && !hasWgPerms) {
            player.sendMessage(playerNotOwner);
            return;
        }
        if (args.length <= 1) {
            player.sendMessage(playerUndefined);
            return;
        }
        playerDisconnected = plugin.replaceMessageVariables("ProttCommand.player_disconnected", args[1]);
        Player member = plugin.getServer().getPlayer(args[1]);
        if (add) {
            if (member == null) {
                player.sendMessage(playerDisconnected);
                return;
            }
            ModifyMembers.addMember(getCurrentRegion(player), member);
            player.sendMessage(memberAdded);
        } else {
            if (member == null) {
                player.sendMessage(ModifyMembers.removeMember(getCurrentRegion(player), args[1]));
                player.sendMessage(memberRemoved);
            } else {
                ModifyMembers.removeMember(getCurrentRegion(player), member);
                player.sendMessage(memberRemoved);
            }
        }
    }



    static ProtectedRegion getCurrentRegion(Player player) {
        Location loc = BukkitAdapter.adapt(player.getLocation());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(loc);
        if (set.size() == 0) {
            player.sendMessage();
            return null;
        }
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

    static boolean playerIsOwner(Player player, DKEnhanced plugin) {
        ProtectedRegion region = getCurrentRegion(player);
        WorldGuardPlugin wgp = (WorldGuardPlugin) plugin.getServer().getPluginManager().getPlugin("WorldGuard");
        LocalPlayer localPlayer = wgp.wrapPlayer(player);
        return region.isOwner(localPlayer);
    }
}
