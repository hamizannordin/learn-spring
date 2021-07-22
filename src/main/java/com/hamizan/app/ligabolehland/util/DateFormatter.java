/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author hamizan
 */
@Component("dateFormatter")
public class DateFormatter {
    
    SimpleDateFormat sdfDatetime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
    
    public String dateTimeString (Date date){
        return sdfDatetime.format(date);
    }
    
    public String dateString (Date date){
        return sdfDate.format(date);
    }
    
    public String timeString (Date date){
        return sdfTime.format(date);
    }
    
    public Date dateTime (String date) throws ParseException{
        return sdfDatetime.parse(date);
    }
    
    public Date date (String date) throws ParseException{
        return sdfDate.parse(date);
    }
    
    public Date time (String date) throws ParseException{
        return sdfTime.parse(date);
    }
    
}
