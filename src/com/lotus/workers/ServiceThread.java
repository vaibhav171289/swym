package com.lotus.workers;

import com.lotus.controller.Operations;
import com.lotus.model.TweetDetails;

public class ServiceThread extends Thread {
	private TweetPipeline tweetPipeline;
	private TweetDetails tweet = null;
	private Operations operations = null;

	public ServiceThread( TweetPipeline tweetPipeline, Operations op) {
		this.tweetPipeline = tweetPipeline;
		this.operations = op;
	}

	@Override
	public void run() {
		while (true) {
			/*
			 * try { // Sleep and release object lock tweetPipeline.tweetQueue.wait();
			 * 
			 * } catch (InterruptedException e) { e.printStackTrace(); // Clear interrupt
			 * status Thread.interrupted(); }
			 */
			tweet = tweetPipeline.getTweet();
			if (tweet != null) {
				operations.parseTweet(tweet);
			}
			try {
				Thread.sleep(11000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
