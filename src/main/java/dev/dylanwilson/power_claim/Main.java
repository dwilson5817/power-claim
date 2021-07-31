package dev.dylanwilson.power_claim;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private Config config;

    @Override
    public void onEnable() {
        this.config = new Config(this);

        getLogger().info("onEnable is called!");
        getLogger().info(config.getString(ConfigOption.MYSQL_DATABASE));
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
