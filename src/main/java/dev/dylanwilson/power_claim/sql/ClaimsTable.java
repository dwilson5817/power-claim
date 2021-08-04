package dev.dylanwilson.power_claim.sql;

import dev.dylanwilson.power_claim.Main;
import dev.dylanwilson.power_claim.utils.ConfigOption;
import org.apache.commons.io.IOUtils;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class ClaimsTable {
    Main main;

    public ClaimsTable(Main main) {
        this.main = main;

        checkTable();
    }

    public boolean claimChunk(UUID uuid, int x, int z) {
        try {
            Connection connection = main.getDataSource().getConnection();

            String query = "INSERT INTO powerclaim_claims (owner, x, z)\n" +
                    "VALUES ('" + uuid + "', " + x + ", " + z + ");";

            Statement statement = connection.createStatement();

            statement.execute(query);

            statement.close();
            connection.close();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean unclaimChunk(int x, int z) {
        try {
            Connection connection = main.getDataSource().getConnection();

            String query = "UPDATE powerclaim_claims t\n" +
                    "SET t.active = FALSE\n" +
                    "WHERE x = '" + x + "'\n" +
                    "AND z = '" + z + "'\n" +
                    "AND active = TRUE;";

            Statement statement = connection.createStatement();

            statement.execute(query);

            statement.close();
            connection.close();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public String getOwner(int x, int z) {
        String owner = null;

        try {
            Connection connection = main.getDataSource().getConnection();

            String query = "SELECT owner\n" +
                    "FROM powerclaim_claims\n" +
                    "WHERE x = '" + x +"'\n" +
                    "AND z = '" + z + "'\n" +
                    "AND active = TRUE;";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                owner = resultSet.getString("owner");
            }

            statement.close();
            connection.close();

            return owner;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    private void checkTable() {
        try {
            Connection connection = main.getDataSource().getConnection();

            String query = "SELECT table_name\n" +
                    "FROM information_schema.tables\n" +
                    "WHERE table_schema = '" + main.getConfiguration().getString(ConfigOption.MYSQL_DATABASE) +"'\n" +
                    "AND table_name = 'powerclaim_claims';";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            if(! resultSet.next()) {
                main.getLogger().info("Claims table doesn't exist, so it will be created");
                Statement createTableStatement = connection.createStatement();
                createTableStatement.execute(
                        IOUtils.toString(main.getResource("migrations/1970_01_01_0000_create_claims_table.sql"),
                                StandardCharsets.UTF_8));
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
