package com.strong.yujiaapp.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * �̳߳ش���
 * @author Administrator
 *
 */
public class ThreadPoolManager {
	private ExecutorService service;
	
	private ThreadPoolManager(){
		//�����ͳ���ͨ������ִ��һЩ�����ں̵ܶ��첽������
        //��������һ���Կ�ʼ���̳߳�
		service = Executors.newCachedThreadPool();
	}
	
	private static ThreadPoolManager manager;
	private Future<?> futrue;
	
	
	public static ThreadPoolManager getInstance(){
		if(manager==null)
		{
			manager= new ThreadPoolManager();
		}
		return manager;
	}
	
	public void addTask(Runnable runnable){
		futrue = service.submit(runnable);
		
	}
	
	public void cacelTask(){
		if(futrue != null ){
			futrue.cancel(true);
		}
	}
	public void shutDown(){
		if(service != null ){
        service.shutdown();
		}
		manager = null;
    }
}
