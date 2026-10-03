package com.calendar.repository;

import com.calendar.model.Date;
import com.calendar.model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    public Event insert(Connection connection, Event event) throws SQLException {
        String sql = "INSERT INTO event (title, description, location, start_date, end_date, is_repeated) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, event.getTitle());
            statement.setString(2, event.getDescription());
            statement.setString(3, event.getLocation());
            statement.setObject(4, event.getStartDate());
            statement.setObject(5, event.getEndDate());
            statement.setBoolean(6, event.isRepeat());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Event(
                            generatedKeys.getInt(1),
                            event.getTitle(),
                            event.getDescription(),
                            event.getLocation(),
                            event.getStartDate(),
                            event.getEndDate(),
                            event.isRepeat());
                }
            }
        }
        throw new SQLException("Failure to generate event identifier. ");
    }

    public List<Event> listAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM event ORDER BY id";//LIMIT ?
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Event> eventList = new ArrayList<>();
                while (resultSet.next()) {
                    eventList.add(mapRow(resultSet));
                }
                return eventList;
            }
        }
    }

    private Event mapRow(ResultSet resultSet) throws SQLException {
        return new Event(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getString("location"),
                resultSet.getString("start_date"),
                resultSet.getString("end_date"),
                resultSet.getBoolean("is_repeated"));
    }
}
