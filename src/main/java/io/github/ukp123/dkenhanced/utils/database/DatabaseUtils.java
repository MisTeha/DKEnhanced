package io.github.ukp123.dkenhanced.utils.database;


import com.plotsquared.core.plot.Plot;
import io.github.ukp123.dkenhanced.DKEnhanced;
import io.github.ukp123.dkenhanced.utils.PsUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class DatabaseUtils extends PsUtils {

    private static Connection connection;
    private static String host, database, username, password;
    private static int port;

    DKEnhanced plugin;
    static Statement statement;

    private final static String plotgrades = "DKEnhanced_plotgrades";
    private final static String plotbuilders = "DKEnhanced_plotbuilders";
    private final static String plots = "DKEnhanced_plots";
    private final static String ev = "DKEnhanced_ev";

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
                statement = connection.createStatement();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                return;
            }

            try {
                if (!checkTables()) {
                    plugin.getLogger().warning("Tabeleid ei eksisteeri. Loon tabelid..");
                    createTables();
                    plugin.getLogger().info("Tabelid loodud.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    private boolean checkTables() throws SQLException {
        DatabaseMetaData md = connection.getMetaData();
        ResultSet grs = md.getTables(null, null, plotgrades, null);
        ResultSet brs = md.getTables(null, null, plotbuilders, null);
        ResultSet prs = md.getTables(null, null, plots, null);
        ResultSet evrs = md.getTables(null, null, ev, null);
        if (!grs.next() || !brs.next() || !prs.next() || !evrs.next()) {
            return false;
        }
        return true;
    }

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

    public static void createTables() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + plotgrades + "(" +
                "plot_x int(11)," +
                "plot_y int(11)," +
                "plot_area VARCHAR(45)," +
                "hindaja VARCHAR(36)," +
                "hindajaUN VARCHAR (16)," +
                "hinne INT," +
                "timestamp TIMESTAMP)");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + plotbuilders + "(" +
                "plot_x int(11)," +
                "plot_y int(11)," +
                "plot_area VARCHAR(45)," +
                "ehitaja VARCHAR(36)," +
                "ehitajaUN VARCHAR(16))");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + plots + "(" +
                "plot_x int(11)," +
                "plot_y int(11)," +
                "plot_area VARCHAR(45)," +
                "plot_included BOOLEAN)");
        executeStatement(Statements.CREATE_EV_TABLE, false);
    }

    public static void addAreaGrade(Player player, int grade) throws SQLException {
        String plotgradeQuery = "INSERT INTO " + plotgrades + "(plot_x, plot_y, plot_area, hindaja, hindajaUN, hinne, timestamp) values (?, ?, ?, ?, ?, ?, ?)";
        Plot plot = getCurrentPlot(player);
        int plot_x = plot.getId().getX();
        int plot_y = plot.getId().getY();
        String plot_area = plot.getArea().toString();
        String hindaja = player.getUniqueId().toString();
        String hindajaUN = player.getName();

        Calendar calendar = Calendar.getInstance();

        java.sql.Timestamp javaTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());

        PreparedStatement pgstmnt = connection.prepareStatement(plotgradeQuery);
        pgstmnt.setInt(1, plot_x);
        pgstmnt.setInt(2, plot_y);
        pgstmnt.setString(3, plot_area);
        pgstmnt.setString(4, hindaja);
        pgstmnt.setString(5, hindajaUN);
        pgstmnt.setInt(6, grade);
        pgstmnt.setTimestamp(7, javaTimestamp);

        pgstmnt.executeUpdate();
        pgstmnt.close();
        
        Set<UUID> builders = new HashSet<>();
        builders.addAll(plot.getOwners());
        builders.addAll(plot.getTrusted());
        builders.addAll(plot.getMembers());

        for (UUID u : builders) {
            String buildername;
            try {
                buildername = getPlayerfromUUID(u).getName();
            } catch (NullPointerException e) {
                buildername = getOfflinePlayerFromUUID(u).getName();
            }

            String query = "INSERT INTO " + plotbuilders + "(plot_x, plot_y, plot_area, ehitaja, ehitajaUN) values (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, plot_x);
            statement.setInt(2, plot_y);
            statement.setString(3, plot_area);
            statement.setString(4, u.toString());
            statement.setString(5, buildername);

            statement.executeUpdate();
            statement.close();
        }
    }

    public static ResultSet executeStatement(Statements statements, boolean result, Object... parameters) throws SQLException {
        String sql = statements.statement;

        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 0; i < parameters.length; i++) {
            Object obj = parameters[i];
            if (obj instanceof Integer) {
                statement.setInt(i + 1, (Integer) obj);
            } else if (obj instanceof String) {
                statement.setString(i + 1, (String) obj);
            } else if (obj instanceof Long) {
                statement.setLong(i + 1, (Long) obj);
            } else {
                statement.setObject(i + 1, obj);
            }
        }

        if (result) {
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } else {
            statement.execute();
            statement.close();
        }
        return null;
    }


    public static Connection getConnection() {
        return connection;
    }
}
