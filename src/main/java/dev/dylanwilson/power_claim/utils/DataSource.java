package dev.dylanwilson.power_claim.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.dylanwilson.power_claim.Main;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private final HikariDataSource dataSource;

    public DataSource(Main main) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://" + main.getConfiguration().getString(ConfigOption.MYSQL_HOST)+ ":" +main.getConfiguration().getString(ConfigOption.MYSQL_PORT)+ "/" + main.getConfiguration().getString(ConfigOption.MYSQL_DATABASE));
        hikariConfig.setUsername(main.getConfiguration().getString(ConfigOption.MYSQL_USER));
        hikariConfig.setPassword(main.getConfiguration().getString(ConfigOption.MYSQL_PASS));

        dataSource = new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}