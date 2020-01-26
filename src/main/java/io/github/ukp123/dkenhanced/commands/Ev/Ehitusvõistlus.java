package io.github.ukp123.dkenhanced.commands.Ev;

import com.github.intellectualsites.plotsquared.plot.object.PlotArea;
import io.github.ukp123.dkenhanced.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Ehitusvõistlus {

    private int id;
    private String name;
    private Date startTime;
    private Date endTime;
    private PlotArea plotArea;

    Connection connection = DatabaseUtils.getConnection();
    String tablename = DatabaseUtils.getEv();

    public Ehitusvõistlus(String name, PlotArea plotArea, Date startTime, Date endTime) throws SQLException {
        this.name = name;
        this.plotArea = plotArea;
        this.startTime = startTime;
        this.endTime = endTime;

        Calendar calendar = Calendar.getInstance();

        String plot_area = plotArea.toString();
        Timestamp start_time = new java.sql.Timestamp(startTime.getTime());
        Timestamp end_time = new java.sql.Timestamp(endTime.getTime());
        Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());

        String query = "INSERT INTO " + tablename + " (id, name, plot_area, start_time, end_time, timestamp) values (default, ?, ?, ?, ?, ?)";
        PreparedStatement evprepstatement = connection.prepareStatement(query);
        evprepstatement.setString(1, name);
        evprepstatement.setString(2, plot_area);
        evprepstatement.setTimestamp(3, start_time);
        evprepstatement.setTimestamp(4, end_time);
        evprepstatement.setTimestamp(5, timestamp);
    }

    public Ehitusvõistlus(String name, PlotArea plotArea, Date startTime, long duration) throws SQLException {
        this.name = name;
        this.plotArea = plotArea;
        this.startTime = startTime;

        int duration_ = (int) duration;
        Calendar calendar = Calendar.getInstance();
        Calendar endcalendar = Calendar.getInstance();
        endcalendar.add(Calendar.MILLISECOND, duration_);

        String plot_area = plotArea.toString();
        Timestamp start_time = new java.sql.Timestamp(startTime.getTime());
        Timestamp end_time = new java.sql.Timestamp(endcalendar.getTime().getTime());
        Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());

        String query = "INSERT INTO " + tablename + " (id, name, plot_area, start_time, end_time, timestamp) values (default, ?, ?, ?, ?, ?)";
        PreparedStatement evprepstatement = connection.prepareStatement(query);
        evprepstatement.setString(1, name);
        evprepstatement.setString(2, plot_area);
        evprepstatement.setTimestamp(3, start_time);
        evprepstatement.setTimestamp(4, end_time);
        evprepstatement.setTimestamp(5, timestamp);
    }
}
