package dev.dylanwilson.power_claim;

import dev.dylanwilson.power_claim.commands.Claim;
import dev.dylanwilson.power_claim.commands.Unclaim;
import dev.dylanwilson.power_claim.sql.ClaimsTable;
import dev.dylanwilson.power_claim.utils.Config;
import dev.dylanwilson.power_claim.sql.DataSource;
import dev.dylanwilson.power_claim.utils.Lang;
import dev.dylanwilson.power_claim.utils.Message;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private Config config;
    private Lang lang;
    private DataSource dataSource;

    private ClaimsTable claimsTable;
    @Override
    public void onEnable() {
        this.config = new Config(this);
        this.lang = new Lang(this);
        this.dataSource = new DataSource(this);

        this.claimsTable = new ClaimsTable(this);

        this.getCommand("claim").setExecutor(new Claim(this));
        this.getCommand("unclaim").setExecutor(new Unclaim(this));

        getLogger().info(lang.getMessage(Message.PLUGIN_STARTUP));
    }

    @Override
    public void onDisable() {
        getLogger().info(lang.getMessage(Message.PLUGIN_SHUTDOWN));
    }

    public Config getConfiguration() {
        return config;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public ClaimsTable getClaimsTable() {
        return claimsTable;
    }

    public Lang getLang() {
        return lang;
    }
}
