/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.activities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lrodriguezn
 */
@Repository
public interface CalendarActivitiesRepository extends JpaRepository<CalendarActivities, Long>{
@Query(nativeQuery = true, value="SELECT COUNT(1) FROM CALENDAR_ACTIVITIES WHERE  END_ACTIVITY_DAY > NOW() AND  END_ACTIVITY_DAY <= ADDDATE(NOW(),(SELECT _VALUE FROM  SYSTEM_PARAMETERS WHERE _KEY ='NOTIFICATION_ACTIVITIES')) ")
int accountPendingActivitiesToExpire();
    
}
