package dev.dylanwilson.power_claim.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
    FileConfiguration config;

    public Config(JavaPlugin plugin) {
        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();
    }

    public String getString(ConfigOption option) {
        return config.getString(option.toString().toLowerCase().replaceAll("_", "\\."));
    }
}
