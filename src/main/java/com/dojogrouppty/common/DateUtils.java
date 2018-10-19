/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author lrodriguezn
 */
public class DateUtils {
    
    private DateUtils(){
    };
    final static public String stJavaScriptIniDate(Date date){
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        return new StringBuilder().append(year).append(",").append(month).append(",").append(day).append(",").append(cal.get(Calendar.HOUR)).append(",").append(+cal.get(Calendar.MINUTE)).toString();        
    }
    	/**
	 * Return an ISO 8601 combined date and time string for current date/time
	 * 
	 * @return String with format "yyyy-MM-dd'T'HH:mm:ss'Z'"
	 */
	public static String getISO8601StringForCurrentDate() {
		Date now = new Date();
		return getISO8601StringForDate(now);
	}

	/**
	 * Return an ISO 8601 combined date and time string for specified date/time
	 * 
	 * @param date
	 *            Date
	 * @return String with format "yyyy-MM-dd'T'HH:mm:ss'Z'"
	 */
	public static String getISO8601StringForDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return dateFormat.format(date);
	}
        public static Date addDay(int d){
            Calendar cal = Calendar.getInstance();
//            int t = cal.get(Calendar.DAY_OF_MONTH);
            cal.add(Calendar.DAY_OF_MONTH, d);
            return cal.getTime();
        }
        public static Date date2999() throws ParseException{    
           return ParentControllerService.dateformat.parse(ParentControllerService.DATE_NULL);
        }
        public static Date yearsAgo(int year){
            Calendar cal = Calendar.getInstance();
            year = cal.get(Calendar.YEAR) - year;
            cal.set(year, Calendar.DECEMBER, 1);
          return cal.getTime();
        }
}
