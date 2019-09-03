package io.github.ukp123.dkenhanced;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.*;

public final class DKEnhanced extends JavaPlugin {

    public String chatPrefix = ChatColor.BLUE + "[" + ChatColor.DARK_AQUA + "DK" + ChatColor.AQUA + "Enhanced" + ChatColor.BLUE + "] ";

    public String chatUA = chatPrefix + ChatColor.RED + "Tundmatu argument" + ChatColor.GRAY;


    public String noPermissionMessage = chatPrefix + ChatColor.RED + "Sul pole õigust sellele käsule.";

    @Override
    public void onEnable() {
        getLogger().info("DKEnchanced on aktiveeritud");
        this.getCommand("dk").setExecutor(new MainCommand(this));
        this.getCommand("dktest").setExecutor(new TestCommand(this));
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("DKEnhanced on deaktiveeritud");

    }
}
