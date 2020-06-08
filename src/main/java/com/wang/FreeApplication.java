package com.wang;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FreeApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(FreeApplication.class,args);
    }
    
 
	public void threadPool() {
    	ExecutorService fix=Executors.newFixedThreadPool(2);
    	ExecutorService cache=Executors.newCachedThreadPool();//动态增加
    	ExecutorService single=Executors.newSingleThreadExecutor();
    	ScheduledExecutorService schedule=Executors.newScheduledThreadPool(5);
    	
    	fix.execute(()->{
    		
    	});
    	
    	cache.execute(()->{
    		
    	});
    	
    	single.execute(()->{
    		
    	});
    	
    	schedule.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				
			}
		},1,1,TimeUnit.SECONDS);   	
    	
    }
    
    public void fun() {
    	 LocalDate.now().lengthOfYear();
    	 HashSet<Object>hashSets=new HashSet<>();
    	 hashSets.isEmpty();
    }
    
    @SuppressWarnings("unused")
	public void fun2() {
    	List<String>list=new ArrayList<String>();
    	list.add("1");
    	list.add("2");
        String[] strArray=	list.toArray(new String[0]);
    	strArray.clone();
    	List<?> list1 = Arrays.asList(strArray);
    	ThreadGroup group=new ThreadGroup("用户线程组");
    	Thread thread=new Thread(group,()->{
    		
    	});
    	
    	
    	ThreadLocal<String> threadLocal=new ThreadLocal<>();
    	
    	Timer timer=new Timer();
    	timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
			}
		}, 1, 5);
    	
    	timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
			}
		}, 1, 5);
    	
    	LongAdder longadder=new LongAdder();
    	longadder.add(10);
    	
    	Math.random();
    }
   
}
