package com.ycy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ��ȡxml
 * @author yangchengyu
 *
 */
public final class ReadFile { 
	
    private   static  String DRIVER; 
    private   static  String URL; 
    private   static  String USER;
    private   static  String PASSWORD;
    private   static  String app_key;



    static  {  
       Properties prop =  new  Properties();  
       InputStream in = ReadFile. class.getClassLoader() .getResourceAsStream( "configuration.properties" );
        try  {  
           prop.load(in);  
           DRIVER = prop.getProperty( "DRIVER" ).trim(); 
           URL = prop.getProperty( "URL" ).trim();
           USER = prop.getProperty( "USER" ).trim();
           PASSWORD = prop.getProperty( "PASSWORD" ).trim();
            app_key = prop.getProperty( "app_key" ).trim();
       }  catch  (IOException e) {
           e.printStackTrace();  
       }  
   }  


    private ReadFile() {
   }  

    public   static  String getApp_key() {
        return  app_key;
   }

	public static String getDRIVER() {
		return DRIVER;
	}

	public static String getURL() {
		return URL;
	}


	public static String getUSER() {
		return USER;
	}


	public static String getPASSWORD() {
		return PASSWORD;
	}







}
