/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.activities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lrodriguezn calendar_activities;
 * +----------------------------+--------------+------+-----+---------+------------
 * ----+ | Field | Type | Null | Key | Default | Extra |
 * +----------------------------+--------------+------+-----+---------+------------
 * ----+ | id_Calendar_activity | int(11) | NO | PRI | NULL | auto_increm ent |
 * | activity_name | varchar(40) | NO | | NULL | | | start_day_activity | date |
 * NO | | NULL | | | end_activity_day | date | NO | | NULL | | |
 * activity_star_time | int(3) | YES | | NULL | | | activity_end_time | int(3) |
 * YES | | NULL | |activity_end_time | type | int(1) | NO | | NULL | | |
 * publishing_social_networks | int(11) | NO | | 0 | | | description |
 * varchar(220) | YES | | NULL | | | notice_compliance | int(2) | YES | | NULL |
 * | | users_ notify | varchar(400) | YES | | NULL | |
 * +----------------------------+--------------+------+-----+---------+------------
 * ----+
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "calendar_activities")
public class CalendarActivities implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_Calendar_activity")
    private int idCalendarActivity;
    @Column(name = "activity_name")
    private String activityName;
    @Column(name = "start_day_activity")
    private Date startDayActivity;
    @Column(name = "end_activity_day")
    private Date endActivityDay;
    @Column(name = "activity_star_time")
    private Short activityStarTime;
    @Column(name = "activity_end_time")
    private Short activityEndTime;
    @Column(name = "type")
    private Short type;
    @Column(name = "publishing_social_networks")
    private Short publishingSocialNetworks;
    @Column(name = "description")
    private String description;
    @Column(name = "notice_compliance")
    private Short noticeCompliance;

    public int getIdCalendarActivity() {
        return idCalendarActivity;
    }

    public void setIdCalendarActivity(int idCalendarActivity) {
        this.idCalendarActivity = idCalendarActivity;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getStartDayActivity() {
        return startDayActivity;
    }

    public void setStartDayActivity(Date startDayActivity) {
        this.startDayActivity = startDayActivity;
    }

    public Date getEndActivityDay() {
        return endActivityDay;
    }

    public void setEndActivityDay(Date endActivityDay) {
        this.endActivityDay = endActivityDay;
    }

    public Short getActivityStarTime() {
        return activityStarTime;
    }

    public void setActivityStarTime(Short activityStarTime) {
        this.activityStarTime = activityStarTime;
    }

    public Short getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Short activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getPublishingSocialNetworks() {
        return publishingSocialNetworks;
    }

    public void setPublishingSocialNetworks(Short publishingSocialNetworks) {
        this.publishingSocialNetworks = publishingSocialNetworks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getNoticeCompliance() {
        return noticeCompliance;
    }

    public void setNoticeCompliance(Short noticeCompliance) {
        this.noticeCompliance = noticeCompliance;
    }

}
