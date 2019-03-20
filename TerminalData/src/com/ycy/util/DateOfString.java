package com.ycy.util;

import java.text.SimpleDateFormat;

public class DateOfString {
	 /**
      * 日期格式字符串转换成时间戳
      * @param date 字符串日期
      * @param format 如：yyyy-MM-dd HH:mm:ss
      * @return
      */
	public String date1TimeStamp(String date_str,String format) {
		try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
	
}
