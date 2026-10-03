package com.calendar.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {

    private static final DateTimeFormatter FORMARTTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private int id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean repeat;

    public Event(int id, String title, String description, String location,
                 String startDate, String endDate, boolean repeat) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setLocation(location);
        setStartDate(startDate);
        setEndDate(endDate);
        setRepeat(repeat);
    }

    public Event(int id, String title, String description, String location,
                 LocalDateTime startDate, LocalDateTime endDate, boolean repeat) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setLocation(location);
        setStartDate(startDate);
        setEndDate(endDate);
        setRepeat(repeat);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getStringStartDate() {
        return startDate.format(FORMARTTER);
    }

    public String getStringEndDate() {
        return endDate.format(FORMARTTER);
    }

    public String getCalendar() {
        return startDate.format(FORMARTTER) + "/" + endDate.format(FORMARTTER);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDateTime.parse(startDate, FORMARTTER);
    }

    public void setEndDate(String endDate) {
        this.endDate = LocalDateTime.parse(endDate, FORMARTTER);
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}
