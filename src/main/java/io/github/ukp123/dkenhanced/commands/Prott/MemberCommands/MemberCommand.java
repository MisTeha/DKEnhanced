package io.github.ukp123.dkenhanced.commands.Prott.MemberCommands;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.commands.Prott.ProttCommand;
import io.github.ukp123.dkenhanced.commands.utils.messageutils.MessageUtils;
import io.github.ukp123.dkenhanced.commands.utils.messageutils.Messages;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MemberCommand {

    public static void modifyMemberCommand(Player player, String[] args, DKEnhanced plugin, boolean add) {
        if (!ProttCommand.checkWEAndWG(player, plugin)) return;
        FileConfiguration messagesConfig = plugin.getMessagesConfig();
        FileConfiguration config = plugin.getConfig();
        String addPermission = "dkenhanced.member.add";
        String removePermission = "dkenhanced.member.remove";
        boolean hasWgPerms = false;
        if (add) {
            if (!player.hasPermission("worldguard.region.addmember.*")) {
                if (!player.hasPermission(addPermission)) {
                    MessageUtils.sendMessage(Messages.ERROR_NOPERM_MESSAGE, player);
                    return;
                }
            } else {hasWgPerms = true;}
        } else {
            if (!player.hasPermission("worldguard.region.removemember.*")) {
                if (!player.hasPermission(removePermission)) {
                    MessageUtils.sendMessage(Messages.ERROR_NOPERM_MESSAGE, player);
                    return;
                }
            } else {hasWgPerms = true;}
        }
        if (getCurrentRegion(player) == null) {
            MessageUtils.sendMessage(Messages.MEMBER_NO_REGION, player);
            return;
        }
        if (!playerIsOwner(player, plugin) && !hasWgPerms) {
            MessageUtils.sendMessage(Messages.MEMBER_PLAYER_NOTOWNER, player);
            return;
        }
        if (args.length <= 1) {
            MessageUtils.sendMessage(Messages.MEMBER_PLAYER_UNDEFINED, player);
            return;
        }
        Player member = plugin.getServer().getPlayer(args[1]);
        if (add) {
            if (member == null) {
                MessageUtils.sendMessage(Messages.PROTT_PLAYER_DISCONNECTED, player);
                return;
            }
            ModifyMembers.addMember(getCurrentRegion(player), member);
            MessageUtils.sendMessage(Messages.MEMBER_MEMBER_ADDED, player);
        } else {
            //// TODO: 2020-01-27 vaata mis siin toimub selle sendMessage asjaga ja siis paranda if juures warning
            if (member == null) {
                player.sendMessage(ModifyMembers.removeMember(getCurrentRegion(player), args[1]));
                MessageUtils.sendMessage(Messages.MEMBER_MEMBER_REMOVED, player);
            } else {
                ModifyMembers.removeMember(getCurrentRegion(player), member);
                MessageUtils.sendMessage(Messages.MEMBER_MEMBER_REMOVED, player);
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
