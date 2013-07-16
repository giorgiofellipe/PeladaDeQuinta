package com.giorgio.peladadequinta2.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


public class StringUtils {
	public static String join(String[] aNomesTime1, String delimiter) {
	    String joined = "";
	    int noOfItems = 0;
	    for (String item : aNomesTime1) {
	        joined += item;
	        if (++noOfItems < aNomesTime1.length)
	            joined += delimiter;
	    }
	    return joined;
	}
	
	public static Date stringToDate(String aDate,String aFormat) {
		if(aDate==null) return null;
		ParsePosition pos = new ParsePosition(0);
		SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
		Date stringDate = simpledateformat.parse(aDate, pos);
		return stringDate; 
   }
	
	public static String dateToString(Date aDate, String aFormat) {
		SimpleDateFormat dateformat = new SimpleDateFormat(aFormat);
        StringBuilder sDate = new StringBuilder(dateformat.format(aDate));
        return sDate.toString();
	}
}
