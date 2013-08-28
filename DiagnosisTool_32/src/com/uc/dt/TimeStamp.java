package com.uc.dt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStamp 
{
	// Return Time Stamp as YYYYMMDDHHMMSS
	public String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String sDate = dateFormat.format( date );
        return sDate;
    } 
	
	public String getFormatTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String sDate = dateFormat.format( date );
        return sDate;
    } 
	
	// Return Time Stamp as YYYYMMDD
	public String getDate() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String sDate = dateFormat.format( date );
        return sDate;
    } 
	// Return Time Stamp as HHMMSS
	public String getTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("HHmmss");
        Date date = new Date();
        String sDate = dateFormat.format( date );
        return sDate;
    } 
}
