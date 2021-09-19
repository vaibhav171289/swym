package com.lotus.workers;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.lotus.model.TweetDetails;

public class TweetPipeline {
	private static TweetPipeline pipeline = null;
   public ConcurrentLinkedQueue<TweetDetails> tweetQueue = new ConcurrentLinkedQueue<TweetDetails>();
   private  TweetPipeline() {}
   public static TweetPipeline getInstance() {
	   if(pipeline == null) {
		   pipeline = new TweetPipeline();
	   }
	   return pipeline;
   }
   public synchronized void addTweet(TweetDetails tweet) {
	   tweetQueue.add(tweet);
//	   tweetQueue.notifyAll();
   }
   public synchronized TweetDetails getTweet() {
	   return tweetQueue.poll();
   }
   
}
