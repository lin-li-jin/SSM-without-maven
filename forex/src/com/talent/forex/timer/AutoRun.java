package com.talent.forex.timer;

import java.util.Timer;  
import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;  
  
public class AutoRun implements ServletContextListener {  
      
    private Timer timer = null;  
  
    public void contextInitialized(ServletContextEvent arg0) {  
      //暂时关闭调度任务 
//         timer=new Timer(true);
//         timer.schedule(new Task(),1000*10,1000*50);//延迟30秒，每30秒执行一次
    }  
      
    public void contextDestroyed(ServletContextEvent arg0) {  
        
        timer.cancel();  
    }  
  
} 
