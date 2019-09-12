package io.github.ukp123.dkenhanced;

import io.github.ukp123.dkenhanced.commands.MainCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DKEnhanced extends JavaPlugin {

    private String name = getDescription().getName() + " ";

    private String namepVersion = getDescription().getName() + " v" + getDescription().getVersion() + " ";

    public String chatPrefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(getConfig().getString("prefix")));

    public String chatUA = chatPrefix + ChatColor.RED + "Tundmatu argument " + ChatColor.WHITE + "- " + ChatColor.GRAY;


    public String noPermissionMessage = chatPrefix + ChatColor.RED + "Sul pole õigust sellele käsule.";

    private void updateConfig(DKEnhanced plugin) {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onEnable() {
        getLogger().info(namepVersion + "on aktiveeritud.");
        Objects.requireNonNull(this.getCommand("dk")).setExecutor(new MainCommand(this));
        updateConfig(this);
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
