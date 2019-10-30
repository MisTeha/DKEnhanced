package io.github.ukp123.dkenhanced.db;

import io.github.ukp123.dkenhanced.DKEnhanced;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

    private static Connection connection;
    private static String host, database, username, password;
    private static int port;

    DKEnhanced plugin;

    public DatabaseUtils(DKEnhanced p) {
        plugin = p;
    }

    public BukkitRunnable r = new BukkitRunnable() {
        @Override
        public void run() {
            FileConfiguration config = plugin.getConfig();
            host = config.getString("db.mysql.host");
            port = config.getInt("db.mysql.port");
            database = config.getString("db.mysql.database");
            username = config.getString("db.mysql.username");
            password = config.getString("db.mysql.password");
            try {
                openConnection();
                Statement statement = connection.createStatement();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };


    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?verifyServerCertificate=false&useSSL=true", this.username, this.password);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
