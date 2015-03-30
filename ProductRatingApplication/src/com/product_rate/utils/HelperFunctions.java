package com.product_rate.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HelperFunctions {
	
	public static String getDateForDatabase() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strCustomDateTime = dateFormat.format(date);
		return strCustomDateTime;
	}

	public static String getDateTimeForDatabase() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String strCustomDateTime = dateFormat.format(date);
		return strCustomDateTime;
	}

	public static String addDaysToDate(String date, int daysToAdd) 
	{
		Date todayDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String strDate = sdf.format(todayDate);
		Date parsedDate=null;
		try {
			parsedDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(strDate);
		//System.out.println(""+parsedDate);
		Calendar now = Calendar.getInstance();
		now.setTime(parsedDate);
		now.add(Calendar.DAY_OF_MONTH, daysToAdd);
		//System.out.println(now.getTime());
		Date newDate =now.getTime();
		return dateFormat.format(newDate);
	}


	public static boolean compareTwoDates(String dateTwo)
	{
		boolean flag=false;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = sdf.parse(getDateTimeForDatabase());
			Date date2 = sdf.parse(dateTwo);

			//System.out.println(sdf.format(date1));
			//System.out.println(sdf.format(date2));

			if(date1.compareTo(date2)>0) {
				//System.out.println("Date1 is after Date2");
				flag=true;
			}else if(date1.compareTo(date2)<0) {
				//System.out.println("Date1 is before Date2");
				flag=false;
			}else if(date1.compareTo(date2)==0) {
				//System.out.println("Date1 is equal to Date2");
				flag=true;
			}else{
				//System.out.println("How to get here?");
				flag=false;
			}
		}catch(ParseException ex){
			ex.printStackTrace();	  
			flag=false;
		}
		return flag;
	}

	public static String compareTwoDatesOffset(String dateOne, String dateTwo)
	{
		String flag="false";
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = sdf.parse(dateOne);
			Date date2 = sdf.parse(dateTwo);

			//System.out.println(sdf.format(date1));
			//System.out.println(sdf.format(date2));

			if(date1.compareTo(date2)>0) {
				//System.out.println("Date1 is after Date2");
				flag="true";
			}else if(date1.compareTo(date2)<0) {
				//System.out.println("Date1 is before Date2");
				flag="false";
			}else if(date1.compareTo(date2)==0) {
				//System.out.println("Date1 is equal to Date2");
				flag="true";
			}else{
				//System.out.println("How to get here?");
				flag="false";
			}
		}catch(Exception ex){
			//ex.printStackTrace();	  
			flag="error";
		}
		return flag;
	}

	public static String getDateForDisplay(String tempDate) {
		Date date = null;
		if (tempDate == null || tempDate.trim().equals("") || tempDate.trim().equalsIgnoreCase("null")) {
			date = new Date(); // Current date
		} else {
			date = new Date(Date.parse(tempDate));
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String retDate = dateFormat.format(date);
		return retDate;
	}

	public static String getDateForDisplayMMDDYYYYHHSSMM(String tempDate)
	{
		Date date = null;
		String retDate="";
		SimpleDateFormat dateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		SimpleDateFormat sdate = null;
		if (tempDate == null || tempDate.trim().equals("") || tempDate.trim().equals(" ") || tempDate.trim().equalsIgnoreCase("null")) {
			date = new Date(); // Current date
			retDate = dateFormat.format(date);
		} else {
			try {
				date = dateFormat1.parse(tempDate);
				retDate = dateFormat.format(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				retDate=tempDate;
			}			
		}

		return retDate;

	}

	public static String convertDateTimeForDisplay(String tempDate) {
		Date date = null;
		String retDate="";
		SimpleDateFormat dateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat sdate = null;
		if (tempDate == null || tempDate.trim().equals("") || tempDate.trim().equals(" ") || tempDate.trim().equalsIgnoreCase("null")) {
			date = new Date(); // Current date
			retDate = dateFormat.format(date);
		} else {
			try {
				date = dateFormat1.parse(tempDate);
				retDate = dateFormat.format(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				retDate=tempDate;
			}			
		}

		return retDate;
	}
}