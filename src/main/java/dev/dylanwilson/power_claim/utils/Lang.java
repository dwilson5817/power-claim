package dev.dylanwilson.power_claim.utils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Lang {
    private final String LANG_FILE = "lang.yml";

    private File langConfigFile;
    private FileConfiguration langConfig;

    public Lang(JavaPlugin plugin) {
        langConfigFile = new File(plugin.getDataFolder(), LANG_FILE);
        if (! langConfigFile.exists()) {
            langConfigFile.getParentFile().mkdirs();
            plugin.saveResource(LANG_FILE, false);
        }

        langConfig= new YamlConfiguration();
        try {
            langConfig.load(langConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String getMessage(Message message) {
        return langConfig.getString(message.toString().toLowerCase());
    }
}
