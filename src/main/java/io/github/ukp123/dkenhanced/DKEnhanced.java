package io.github.ukp123.dkenhanced;

import io.github.ukp123.dkenhanced.commands.MainCommand;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class DKEnhanced extends JavaPlugin {

    private String name = getDescription().getName() + " ";

    private String namepVersion = getDescription().getName() + " v" + getDescription().getVersion() + " ";

    private File customConfigFile;
    private FileConfiguration customConfig;

    private void updateConfig(DKEnhanced plugin) throws IOException {
        getConfig().options().copyDefaults(true);
        saveConfig();
        customConfig.options().copyDefaults(true);

    }

    public String replaceMessageVariables(String tempm) {
        String m = getMessagesConfig().getString("prefix");
        if (m == null) {
            return "null";
        }
        if (m.contains("{PREFIX}")) {
            m.replace("{PREFIX}", customConfig.getString("prefix"));
        }
        if (m.contains("{DEVELOPER}")) {
            m.replace("{DEVELOPER}", "ukp123");
        }
        if (m.contains("{VERSION}")) {
            m.replace("{VERSION}", getDescription().getVersion());
        }
        if (m.contains("{HELP_COMMAND}")) {
            m.replace("{HELP_COMMAND}", "/dk help");
        }
        if (m.contains("{PROT_LIMIT}")) {
            m.replace("{PROT_LIMIT}", Integer.toString(getConfig().getInt("prott.prot_limit")));
        }
        return m;
    }

    @Override
    public void onEnable() {
        getLogger().info(namepVersion + "on aktiveeritud.");
        Objects.requireNonNull(this.getCommand("dk")).setExecutor(new MainCommand(this));
        CreateMessagesConfig();
        try {
            updateConfig(this);
        } catch (IOException e) {
            e.printStackTrace();
            getLogger().warning("mida sa ajad kuidas me siia jõudsime");
        }
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

    public FileConfiguration getMessagesConfig() {
        return this.customConfig;
    }

    private void CreateMessagesConfig() {
        customConfigFile = new File(getDataFolder(), "messages.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }
        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            getLogger().warning("jõudsid siia");
        }
    }
}
