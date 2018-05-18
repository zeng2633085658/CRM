package com.crm.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ContextUtils {
	//double类型转换为字符串
	public static String doubleToStr(double d){
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		return nf.format(d);
	}
	//上传文件重命名函数
	public static String getFilename(){
		Date d = new Date();
		int year = d.getYear()+1900;
		int month = d.getMonth()+1;
		int day = d.getDate();
		int hour = d.getHours();
		int minute = d.getMinutes();
		int second = d.getSeconds();
		String sdate = year+"";
		if(month<10)
			sdate += "0"+month;
		else
			sdate += month;
		if(day<10){
			sdate += "0"+day;
		}else{
			sdate += day;
		}
		if(hour<10){
			sdate += "0"+hour;
		}else{
			sdate += hour;
		}
		if(minute<10){
			sdate += "0"+minute;
		}else{
			sdate += minute;
		}
		if(second<10){
			sdate += "0"+second;
		}else{
			sdate += second;
		}
		return sdate;
	}

	//date类型转换为格式化后的字符串类型
	public static String dateToStrLong(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	//date类型转换为格式化后的字符串类型
	public static String dateToStrShort(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d);
	}
	public static String dateToStrShort1(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddmmssms");
		return sdf.format(d);
	}
	public static Date StringDateShort(String str){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date =  sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	//get方式提交数据，进行乱码处理
	public String toStr(String str){
		try{
			str = new String(str.getBytes("iso-8859-1"),"utf-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd mm:ss");
		String strDate = "2012-3-1";
		try {
			Date date=sdf.parse(strDate);
			System.out.println(dateToStrShort(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
