package dev.dylanwilson.power_claim;

import dev.dylanwilson.power_claim.utils.Config;
import dev.dylanwilson.power_claim.utils.Lang;
import dev.dylanwilson.power_claim.utils.Message;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private Config config;
    private Lang lang;

    @Override
    public void onEnable() {
        this.config = new Config(this);
        this.lang = new Lang(this);

        getLogger().info(lang.getMessage(Message.PLUGIN_STARTUP));
    }

    @Override
    public void onDisable() {
        getLogger().info(lang.getMessage(Message.PLUGIN_SHUTDOWN));
    }
}
