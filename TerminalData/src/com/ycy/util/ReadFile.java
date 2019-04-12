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
    private   static  String URL1;
    private   static  String USER1;
    private   static  String PASSWORD1;
    private   static  String URL2;
    private   static  String USER2;
    private   static  String PASSWORD2;


    static  {  
       Properties prop =  new  Properties();  
       InputStream in = ReadFile. class.getClassLoader() .getResourceAsStream( "configuration.properties" );
        try  {  
           prop.load(in);  
           DRIVER = prop.getProperty( "DRIVER" ).trim(); 
           URL1 = prop.getProperty( "URL1" ).trim();
           USER1 = prop.getProperty( "USER1" ).trim();
           PASSWORD1 = prop.getProperty( "PASSWORD1" ).trim();
            URL2 = prop.getProperty( "URL2" ).trim();
            USER2 = prop.getProperty( "USER2" ).trim();
            PASSWORD2 = prop.getProperty( "PASSWORD2" ).trim();
       }  catch  (IOException e) {
           e.printStackTrace();  
       }  
   }  


    private ReadFile() {
   }  


	public static String getDRIVER() {
		return DRIVER;
	}

	public static String getURL1() {
		return URL1;
	}


	public static String getUSER1() {
		return USER1;
	}


	public static String getPASSWORD1() {
		return PASSWORD1;
	}


    public static String getURL2() {
        return URL2;
    }

    public static String getUSER2() {
        return USER2;
    }

    public static String getPASSWORD2() {
        return PASSWORD2;
    }
}
