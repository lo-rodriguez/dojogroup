/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.activities;

/**
 *
 * @author lrodriguezn
 */
public class ActivitiesDTO {

    private String title;
    private String start;
    private String end;
    private String className;
    private String url;
    private String eventColor;
    private Boolean allDay;
    private Long id;

    public ActivitiesDTO() {
    }

    public ActivitiesDTO(String title, String start, String end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public ActivitiesDTO(String title, String start, String end, String className, Long id) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.className = className;
        this.id = id;
    }

    public ActivitiesDTO(String title, String start, String end, String className, String url, Boolean allDay, Long id) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.className = className;
        this.url = url;
        this.allDay = allDay;
        this.id = id;
    }

    public String getEventColor() {
        return eventColor;
    }

    public void setEventColor(String eventColor) {
        this.eventColor = eventColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
