package com.huizi.leave.leavemanagementsystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
	private static SimpleDateFormat format;
	static{
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	public static Date parse(String strDate) throws ParseException {
		strDate = strDate.replace('T', ' ');
		return format.parse(strDate);
	}
	
	public static String dateToString(Date date){
		return format.format(date);
	}
	
	public static String dateToH5String(Date date){
		String h5 = format.format(date);
		h5 = h5.replace(' ', 'T');
		return h5;
	}
	
}
