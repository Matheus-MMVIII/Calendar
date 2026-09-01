package com.calendar.model;

public class Event {
    private String title;
    private String description;
    private String location;
    private Date date;
    private boolean repeat;

    public Event(String title, String description, String location, Date date, boolean repeat) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.repeat = repeat;
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

    public Date getCalendar() {
        return date;
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

    public void setCalendar(Date date) {
        this.date = date;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}
