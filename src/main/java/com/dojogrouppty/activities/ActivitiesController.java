/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.activities;

import com.dojogrouppty.common.ParentControllerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author lrodriguezn
 */
@Controller
public class ActivitiesController  extends ParentControllerService{
    @Autowired
   ActivitiesService activitiesService;
        private static final Logger logger
            = LoggerFactory.getLogger(ActivitiesController.class);
    @GetMapping("/calendarActivities")
    String calendarActivities() {
        return PAGE_CALENDAR_ACTIVITIES;
    }

    @RequestMapping(value = "/allActivities",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    String allActivities() {      
        return  activitiesService.getCelendarActivitiesJson();
    }
    /**
     * Method that performs the insertion of a new event
     * @param start
     * @param end
     * @param title
     * @return 
     */
    @RequestMapping(value = "/addActivity/{start}/{end}/{title}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Secured({"ROLE_USER", "ROLE_ADMIN","ROLE_SUPER_ADMIN"})        
    String addActivitiy(@PathVariable String start,@PathVariable String end,@PathVariable String title) {
        logger.debug("In with start:["+start+"], end:["+end+"], title:["+title+"]");
        return  activitiesService.addActivity(start, end, title);
    }
}
