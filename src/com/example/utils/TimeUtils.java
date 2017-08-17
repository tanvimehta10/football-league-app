package com.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class TimeUtils {
	
	public static String getTodayDate(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("EE dd MMM yyyy", Locale.getDefault());
		String formattedDate = df.format(c.getTime());
		return formattedDate;
	}
	
	public static String formatDate(String datestring){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		SimpleDateFormat formatter = new SimpleDateFormat("EE dd MMM, yyyy", Locale.getDefault());
		Date date;
		String formattedDate = null;
		try {
			date = dateFormat.parse(datestring);
			formattedDate = formatter.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return formattedDate;

	}

}
