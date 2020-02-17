package io.github.ukp123.dkenhanced.utils.database;

public enum Statements {
    CREATE_EV_TABLE(
        "CREATE TABLE IF NOT EXISTS DKEnhanced_ev (" +
                "id int(11) NOT NULL AUTO_INCREMENT," +
                "name varchar(36) NOT NULL," +
                "theme tinytext NOT NULL, " +
                "plot_area varchar(36) NOT NULL," +
                "start_time timestamp NOT NULL, " +
                "end_time timestamp NOT NULL, " +
                "timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "PRIMARY KEY (id)" +
                ")"
    ),
    INSERT_NEW_EV(
            "INSERT INTO DKEnhanced_ev (id, name, theme, plot_area, start_time, end_time, timestamp) values (default, ?, ?, ?, ?, ?, default)");

    public final String statement;

    Statements(String s) {
        statement = s;
    }

    @Override
    public String toString() {
        return statement;
    }
}
