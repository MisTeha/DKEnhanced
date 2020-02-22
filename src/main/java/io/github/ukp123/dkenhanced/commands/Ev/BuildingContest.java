package io.github.ukp123.dkenhanced.commands.Ev;

import com.github.intellectualsites.plotsquared.plot.object.PlotArea;
import io.github.ukp123.dkenhanced.utils.TimeUtils;
import io.github.ukp123.dkenhanced.utils.database.DatabaseUtils;
import io.github.ukp123.dkenhanced.utils.database.Statements;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("SpellCheckingInspection")
public class BuildingContest {

    private int id;
    private String name;
    private Date startTime;
    private Date endTime;
    private PlotArea plotArea;

    Connection connection = DatabaseUtils.getConnection();

    public BuildingContest(String name, String theme, PlotArea plotArea, Date startTime, Date endTime) throws SQLException {
        this.name = name;
        this.plotArea = plotArea;
        this.startTime = startTime;
        this.endTime = endTime;

        Calendar calendar = Calendar.getInstance(TimeUtils.timeZone);

        String plot_area = plotArea.toString();
        Timestamp start_time = new java.sql.Timestamp(startTime.getTime());
        Timestamp end_time = new java.sql.Timestamp(endTime.getTime());
        Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());


        DatabaseUtils.executeStatement(Statements.INSERT_NEW_EV, false, name, theme, plot_area, start_time, end_time, timestamp);

    }

    public BuildingContest(String name, String theme, PlotArea plotArea, Date startTime, long duration) throws SQLException {
        this.name = name;
        this.plotArea = plotArea;
        this.startTime = startTime;

        Calendar endcalendar = Calendar.getInstance(TimeUtils.timeZone);
        endcalendar.setTime(startTime);
        endcalendar.add(Calendar.MILLISECOND, (int) duration);


        String plot_area = plotArea.toString();
        Timestamp start_time = new java.sql.Timestamp(startTime.getTime());
        Timestamp end_time = new java.sql.Timestamp(endcalendar.getTime().getTime());

        DatabaseUtils.executeStatement(Statements.INSERT_NEW_EV, false, name, theme, plot_area, start_time, end_time);
    }
}
