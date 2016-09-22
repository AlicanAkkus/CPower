package com.cpower.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wora
 * @Cpower provide the instant memory details.
 * */

public class CPower extends Thread {
	private Logger logger = LogManager.getLogger(CPower.class);
	private static CPower cPower;
	private volatile boolean serviceUP;//guarantees that any thread that reads a field will see the most recently written value.
	private int delay = 5;//default will be print memory statistic every 5 minutes

	public CPower() {
	}

	public static synchronized CPower getInstance() {
		if (cPower == null) {
			ApplicationContext appContext = new ClassPathXmlApplicationContext("appContext.xml");
			cPower = (CPower) appContext.getBean("cPower");
		}
		return cPower;
	}

	public CPower initialize(int delay) {
		if (cPower == null) {
			throw new IllegalStateException("CPower don\'t initialized!");
		}

		if(delay >= 1){
			this.delay = delay;
		}

		this.serviceUP = true;
		start();
		
		return this;
	}
	
	public void stopCPower(){
		this.serviceUP = false;
	}

	@Override
	public void run() {

		while (serviceUP) {
			try {
				final long max = Runtime.getRuntime().maxMemory() / 1024;
				final long total = Runtime.getRuntime().totalMemory() / 1024;
				final long free = Runtime.getRuntime().freeMemory() / 1024;
				final long used = total - free;

				// Build log string
				final StringBuilder logString = new StringBuilder("\n\tMax Memory : ").append(String.valueOf(max)).append(" kb.");
				logString.append("\n\tTotal Memory : ").append(String.valueOf(total)).append(" kb.");
				logString.append("\n\tUsed Memory : ").append(String.valueOf(used)).append(" kb.");
				logString.append("\n\tFree Memory : ").append(String.valueOf(free)).append(" kb.");

				logger.info(logString.toString());

				Thread.sleep(delay*1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void main(String[] args) {
		CPower cPower = CPower.getInstance();
		cPower.initialize(10);
		cPower.stopCPower();
		
	}
}
