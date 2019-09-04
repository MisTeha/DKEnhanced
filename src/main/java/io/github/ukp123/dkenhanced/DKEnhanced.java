package io.github.ukp123.dkenhanced;

import io.github.ukp123.dkenhanced.commands.MainCommand;
import io.github.ukp123.dkenhanced.commands.TestCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DKEnhanced extends JavaPlugin {

    private String name = getDescription().getName() + " ";

    private String namepVersion = getDescription().getName() + " v" + getDescription().getVersion() + " ";

    public String chatPrefix = ChatColor.BLUE + "[" + ChatColor.DARK_AQUA + "DK" + ChatColor.AQUA + "Enhanced" + ChatColor.BLUE + "] ";

    public String chatUA = chatPrefix + ChatColor.RED + "Tundmatu argument " + ChatColor.GRAY;


    public String noPermissionMessage = chatPrefix + ChatColor.RED + "Sul pole õigust sellele käsule.";

    @Override
    public void onEnable() {
        getLogger().info(namepVersion + "on aktiveeritud.");
        Objects.requireNonNull(this.getCommand("dk")).setExecutor(new MainCommand(this));
        Objects.requireNonNull(this.getCommand("dktest")).setExecutor(new TestCommand(this));
        saveDefaultConfig();
        if (getServer().getPluginManager().getPlugin("WorldEdit") == null) {
            getLogger().warning("WorldEdit ei ole installitud. Osad " + name + "funktsioonid ei pruugi toimida.");
        }
        if (getServer().getPluginManager().getPlugin("WorldGuard") == null) {
            getLogger().warning("WorldGuard ei ole installitud. Osad " + name + "funktsioonid ei pruugi toimida.");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info(namepVersion + " on deaktiveeritud.");
    }
}
