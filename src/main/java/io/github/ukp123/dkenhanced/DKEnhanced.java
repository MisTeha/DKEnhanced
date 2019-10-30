package io.github.ukp123.dkenhanced;

import io.github.ukp123.dkenhanced.commands.MainCommand;
import io.github.ukp123.dkenhanced.db.DatabaseUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Objects;

public final class DKEnhanced extends JavaPlugin {

    private File customConfigFile;
    private FileConfiguration customConfig;

    private String name = getDescription().getName() + " ";
    private String namepVersion = getDescription().getName() + " v" + getDescription().getVersion() + " ";


    private void updateConfig(DKEnhanced plugin) throws IOException {
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadCustomConfig();
        getMessagesConfig().options().copyDefaults(true);
        getMessagesConfig().save(customConfigFile);
    }

    public String replaceMessageVariables(String path) {
        String m = getMessagesConfig().getString(path);
        if (m == null) {
            return "null";
        }
        m = m.replace("{PREFIX}", Objects.requireNonNull(customConfig.getString("prefix")));
        m = m.replace("{DEVELOPER}", "ukp123");
        m = m.replace("{VERSION}", getDescription().getVersion());
        m = m.replace("{HELP_COMMAND}", "/dk help");
        m = m.replace("{PROT_LIMIT}", Integer.toString(getConfig().getInt("commands.prott.prot_limit")));
        m = ChatColor.translateAlternateColorCodes('&', m);
        return m;
    }

    public String replaceMessageVariables(String path, String arg) {
        String m = replaceMessageVariables(path);
        m = m.replace("{V}", arg);
        return m;
    }

    DatabaseUtils dbu = new DatabaseUtils(this);

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("dk")).setExecutor(new MainCommand(this));
        CreateMessagesConfig();
        try {
            updateConfig(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (getServer().getPluginManager().getPlugin("WorldEdit") == null) {
            getLogger().warning("WorldEdit ei ole installitud. Osad " + name + "funktsioonid ei pruugi toimida.");
        }
        if (getServer().getPluginManager().getPlugin("WorldGuard") == null) {
            getLogger().warning("WorldGuard ei ole installitud. Osad " + name + "funktsioonid ei pruugi toimida.");
        }
        dbu.r.runTaskAsynchronously(this);
        getLogger().info(namepVersion + "on aktiveeritud.");
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
        }
    }

    private void reloadCustomConfig() throws UnsupportedEncodingException {
        if (customConfigFile == null) {
            customConfigFile = new File(getDataFolder(), "messages.yml");
        }
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

        // Look for defaults in the jar
        Reader defConfigStream = new InputStreamReader(Objects.requireNonNull(this.getResource("messages.yml")), "UTF8");
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        customConfig.setDefaults(defConfig);
    }


}
