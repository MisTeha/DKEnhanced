package io.github.ukp123.dkenhanced.commands.Prott.MemberCommands;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ModifyMembers {

    static void addMember(ProtectedRegion region, Player member) {
        UUID memberUUID = member.getUniqueId();
        DefaultDomain members = region.getMembers();
        members.addPlayer(memberUUID);
    }

    static void removeMember(ProtectedRegion region, Player member) {
        UUID memberUUID = member.getUniqueId();
        DefaultDomain members = region.getMembers();
        members.removePlayer(memberUUID);
    }

    static String removeMember(ProtectedRegion region, String member) {
        DefaultDomain members = region.getMembers();
        members.removePlayer(member);
        return members.getPlayers().toString();
    }
}