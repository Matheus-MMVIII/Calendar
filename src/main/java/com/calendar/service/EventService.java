package com.calendar.service;

import com.calendar.config.DatabaseConfig;
import com.calendar.exception.NotFoundException;
import com.calendar.model.Event;
import com.calendar.repository.EventRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event create(Map<String, String> payload) throws SQLException {
        String title = payload.get("title");
        String description = payload.get("description");
        String location = payload.get("location");
        String start_date = payload.get("start_date");
        String end_date = payload.get("end_date");
        boolean is_repeated = Boolean.parseBoolean(payload.get("is_repeated"));

        try (Connection connection = DatabaseConfig.getConnection()) {
            return eventRepository.insert(connection,
                    new Event(-1, title, description, location, start_date, end_date, is_repeated));
        }
    }

    public Event findById(int id) throws SQLException {
        try (Connection connection = DatabaseConfig.getConnection()) {
            return eventRepository.findById(connection, id)
                    .orElseThrow(() -> new NotFoundException("Event not found. "));
        }
    }

    public List<Event> listAll() throws SQLException {
        try (Connection connection = DatabaseConfig.getConnection()) {
            return eventRepository.listAll(connection);
        }
    }

    public Event update(Map<String, String> payload, int id) throws SQLException {
        try (Connection connection = DatabaseConfig.getConnection()) {
            String title = payload.get("title");
            String description = payload.get("description");
            String location = payload.get("location");
            String startDate = payload.get("start_date");
            String endDate = payload.get("end_date");
            boolean repeat = Boolean.valueOf(payload.get("id_repeated"));
            return eventRepository.update(connection, new Event(id, title, description, location, startDate, endDate, repeat));
        }
    }

    public boolean delete(int id) throws SQLException {
        try (Connection connection = DatabaseConfig.getConnection()) {
            return eventRepository.delete(connection, id);
        }
    }
}
