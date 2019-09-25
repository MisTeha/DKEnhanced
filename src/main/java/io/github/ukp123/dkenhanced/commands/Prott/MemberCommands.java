package io.github.ukp123.dkenhanced.commands.Prott;

import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MemberCommands {

    static void addMember(ProtectedRegion region, Player member) {
        UUID memberUUID = member.getUniqueId();
        DefaultDomain members = region.getMembers();
        members.addPlayer(memberUUID);
    }

    static void removeMember(ProtectedRegion region, Player member) {
        UUID memberUUID = member.getUniqueId();
        DefaultDomain members = region.getMembers();
        members.removePlayer(memberUUID);
        //TODO: this isn't finished at all..
    }
}
