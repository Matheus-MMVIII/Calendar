package com.calendar.model;

public class Event {
    private String title;
    private String description;
    private String location;
    private Date startDate;
    private Date endDate;
    private boolean repeat;

    public Event(String title, String description, String location, Date startDate, Date endDate, boolean repeat) {
        setTitle(title);
        setDescription(description);
        setLocation(location);
        setStartDate(startDate);
        setEndDate(endDate);
        setRepeat(repeat);
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getCalendar() {
        return startDate.getDate() + "/" + endDate.getDate();
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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}
