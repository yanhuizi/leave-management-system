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
		strDate = strDate.trim().replace('T', ' ');
		//页面 datetime-local 控件的值可能是 yyyy-MM-dd HH:mm（没有秒），补足秒后再解析。
		if (strDate.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
			strDate = strDate + ":00";
		}
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
