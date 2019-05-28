/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.othertest;

import com.dojogrouppty.common.CustomMultipartFile;
import com.dojogrouppty.error.GenericBZKException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.fail;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lrodriguezn
 */
public class OtherTest {
	public static final String FORMAT_DATE = "dd/MM/yyyy";
	public static final SimpleDateFormat dateformat = new SimpleDateFormat(FORMAT_DATE);
    @Test
    public void simpleSplitDate() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 2018, 11, 24, 10, 33
        String stDate = new StringBuilder().append(Calendar.YEAR).append(",").append(cal.get(Calendar.MONTH) + 1).append(",").append(cal.get(Calendar.DAY_OF_MONTH)).append(",").append(cal.get(Calendar.HOUR)).append(",").append(+cal.get(Calendar.MINUTE)).toString();
        System.out.println("String format init date in javascript:" + stDate);
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        stDate = new StringBuilder().append(year).append(",").append(month).append(",").append(day).append(",").append(cal.get(Calendar.HOUR)).append(",").append(+cal.get(Calendar.MINUTE)).toString();
        System.out.println("String format init date in javascript2:" + stDate);
        System.out.println("localDate.toString():" + localDate.toString());

    }

    @Test
    public void testFormatDate() throws ParseException {
        // Make a new Date object. It will be initialized to the current time.
        Date now = new Date();

        // See what toString() returns
        System.out.println(" 1. " + now.toString());

        // Next, try the default DateFormat
        System.out.println(" 2. " + DateFormat.getInstance().format(now));

        // And the default time and date-time DateFormats
        System.out.println(" 3. " + DateFormat.getTimeInstance().format(now));
        System.out.println(" 4. "
                + DateFormat.getDateTimeInstance().format(now));

        // Next, try the short, medium and long variants of the 
        // default time format 
        System.out.println(" 5. "
                + DateFormat.getTimeInstance(DateFormat.SHORT).format(now));
        System.out.println(" 6. "
                + DateFormat.getTimeInstance(DateFormat.MEDIUM).format(now));
        System.out.println(" 7. "
                + DateFormat.getTimeInstance(DateFormat.LONG).format(now));

        // For the default date-time format, the length of both the
        // date and time elements can be specified. Here are some examples:
        System.out.println(" 8. " + DateFormat.getDateTimeInstance(
                DateFormat.SHORT, DateFormat.SHORT).format(now));
        System.out.println(" 9. " + DateFormat.getDateTimeInstance(
                DateFormat.MEDIUM, DateFormat.SHORT).format(now));
        System.out.println("10. " + DateFormat.getDateTimeInstance(
                DateFormat.LONG, DateFormat.LONG).format(now));
        System.out.println("FormatDate:"+DateFormat.getInstance().format(dateformat.parse("01/02/2019")));
    }
    @Test
    public void showSystempProperties(){
       System.out.println("user.home:"+ System.getProperty("user.home"));
    }
    @Ignore @Test
    public void testStore(){
        byte []arr = null;
        MultipartFile file = new CustomMultipartFile(arr,"text.txt");
        try {
            storeOnDisk( file);
        } catch (GenericBZKException ex) {
            Logger.getLogger(OtherTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }
        private void storeOnDisk(MultipartFile file) throws GenericBZKException {
            Path rootLocation;
            String LOCATION = System.getProperty("LOCATION_PHOTO");
             rootLocation = Paths.get(LOCATION);
        try {
            if (file.isEmpty()) {
                throw new GenericBZKException("Error el archivo esta vacio: " + file.getOriginalFilename());
            }
            
            Files.copy(file.getInputStream(),rootLocation.resolve(file.getOriginalFilename()) );
        } catch (IOException e) {
            throw new GenericBZKException("Error no se puede guardar el archivo: " + file.getOriginalFilename());
        }
    }

}
