/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.activities;

import com.dojogrouppty.common.DateUtils;
import com.dojogrouppty.common.ParentControllerService;
import com.dojogrouppty.common.TYPES_ACTIVITIES;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 *
 * @author lrodriguezn
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ActivitiesService   extends ParentControllerService{
  @Autowired CalendarActivitiesRepository calendarActivitiesRepository;
  /**
   * Count of the pending activities to expire
   * @return accoount
   */
  public int accountPendingActivitiesToExpire(){
     return calendarActivitiesRepository.accountPendingActivitiesToExpire();
  }
  /**
   * Get all calendar activities 
   * @return List activities in format JSON
   */
  public String getCelendarActivitiesJson(){
      List <CalendarActivities> list = calendarActivitiesRepository.findAll();
      ArrayList <ActivitiesDTO> listDto = new ArrayList <ActivitiesDTO> ();
      for(CalendarActivities cal:list){
          ActivitiesDTO dto = new ActivitiesDTO();
          dto.setTitle(cal.getActivityName());
          dto.setId((long)cal.getIdCalendarActivity());
          dto.setStart(DateUtils.getISO8601StringForDate(cal.getStartDayActivity()));
          dto.setEnd(DateUtils.getISO8601StringForDate(cal.getEndActivityDay()));
          TYPES_ACTIVITIES op = TYPES_ACTIVITIES.getOption(Integer.SIZE);
          switch(op){
              case INTERNAL:
                  dto.setClassName(TYPES_ACTIVITIES.INTERNAL._getClass()); break;
              case EXTERNAL:
                  dto.setClassName(TYPES_ACTIVITIES.EXTERNAL._getClass());break;    
              default:   
                  dto.setClassName(TYPES_ACTIVITIES.DEFAULT._getClass());break;
          }
          listDto.add(dto);
      }
      return gson.toJson(listDto);
  }
  /**
   * Method that performs the insertion of a new event
   * @param _start
   * @param _end
   * @param _title
   * @return OK
   */
  public String addActivity(String _start,String _end,String _title){
      Date start = new Date(Long.parseLong(_start));
      Date end = new Date (Long.parseLong(_end));
      CalendarActivities act = new CalendarActivities();
      act.setActivityName(_title);
      act.setStartDayActivity(start);
      act.setEndActivityDay(end);
      calendarActivitiesRepository.save(act);
      if( act.getIdCalendarActivity()<=0){
          return gson.toJson(NO_OK);
      }
      return gson.toJson(OK);
  }
}
