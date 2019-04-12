package com.ycy.http;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AutoRun implements ServletContextListener {


    public void contextInitialized(ServletContextEvent arg0){
        HttpData h = new HttpData();
        h.execute();


    }
    public void contextDestroyed(ServletContextEvent arg0){
    }
}
