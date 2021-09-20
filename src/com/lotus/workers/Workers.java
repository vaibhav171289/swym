package com.lotus.workers;

import com.lotus.controller.AnalyticsImp;
import com.lotus.controller.Operations;

/**
 * A very simple thread pool class. The pool size is set at construction time
 * and remains fixed. Threads are cycled through a FIFO idle queue.
 */
public class Workers {
    final int workerThreadSize;
    final int analyticsThreadSize;
    final Operations operations;
	public Workers(int workerThreadSize, int analyticsThreadSize) {
		this.workerThreadSize  = workerThreadSize;
		this.analyticsThreadSize = analyticsThreadSize;
		this.operations = new Operations();
	}
	public void activateTweetWorkers() {
		TweetPipeline tweetPipeline = TweetPipeline.getInstance();
		for (int i = 0; i < workerThreadSize; i++) {
			ServiceThread thread = new ServiceThread(tweetPipeline, operations);

			// Set thread name for debugging. Start it.
			thread.setName("Worker->" + (i + 1));
			thread.start();
		}
	}
	public void activateAnalyticsWorkers() {
		
		AnalyticsImp analytics = new AnalyticsImp(new Operations());
		for (int i = 0; i < analyticsThreadSize; i++) {
			AnalyticsThreads an = new AnalyticsThreads(analytics);
			Thread th = new Thread(an,"Analytics->"+(i+1));
			th.start();
		}
	}
}