package io.github.ukp123.dkenhanced.commands.Ev;

import com.github.intellectualsites.plotsquared.plot.object.PlotArea;
import io.github.ukp123.dkenhanced.utils.database.DatabaseUtils;
import io.github.ukp123.dkenhanced.utils.database.Statements;

import java.sql.Connection;
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

    public BuildingContest(String name, String theme, PlotArea plotArea, Date startTime, Date endTime) {
        this.name = name;
        this.plotArea = plotArea;
        this.startTime = startTime;
        this.endTime = endTime;

        Calendar calendar = Calendar.getInstance();

        String plot_area = plotArea.toString();
        Timestamp start_time = new java.sql.Timestamp(startTime.getTime());
        Timestamp end_time = new java.sql.Timestamp(endTime.getTime());
        Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());

        DatabaseUtils.executeStatement(Statements.INSERT_NEW_EV, false, name, theme, plot_area, start_time, end_time, timestamp);
    }

    public BuildingContest(String name, String theme, PlotArea plotArea, Date startTime, long duration) {
        this.name = name;
        this.plotArea = plotArea;
        this.startTime = startTime;

        int duration_ = (int) duration;
        Calendar calendar = Calendar.getInstance();
        Calendar endcalendar = Calendar.getInstance();
        endcalendar.add(Calendar.MILLISECOND, duration_);
        // TODO: 2020-02-16 SEE PEAB LISAMA DURATIONI STARTTIMELE MITTE NIISAMA

        String plot_area = plotArea.toString();
        Timestamp start_time = new java.sql.Timestamp(startTime.getTime());
        Timestamp end_time = new java.sql.Timestamp(endcalendar.getTime().getTime());

        DatabaseUtils.executeStatement(Statements.INSERT_NEW_EV, false, name, theme, plot_area, start_time, end_time);
    }
}
