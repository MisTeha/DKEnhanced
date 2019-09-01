package io.github.ukp123.dkenhanced;

import org.bukkit.plugin.java.JavaPlugin;

public final class DKEnhanced extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DKEnhanced on aktiveeritud!");
    }

    @Override
    public void onDisable() {
        getLogger().info("DKEnhanced on deaktiveeritud!");
    }
}
