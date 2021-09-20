package com.lotus.workers;

import com.lotus.controller.AnalyticsImp;

public class AnalyticsThreads implements Runnable {
	
	private AnalyticsImp analytics;

	public AnalyticsThreads(AnalyticsImp an) {
		this.analytics = an;
	}
	@Override
	public void run() {
		while(true) {
			analytics.getDistinctHashtagCount();
			analytics.getDistinctUserCount();
			analytics.getMostInfluentialHashtags();
			analytics.highlightMilestoneUsers();
			System.out.println("========================Updated Values at interval of 10 secs======================================");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
