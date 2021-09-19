package com.lotus.controller;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.MinMaxPriorityQueue;
import com.lotus.model.TweetDetails;
import com.lotus.util.Pair;
import com.lotus.util.PairComparator;

public interface Controller {
	/*first is user and second will be tweets list*/
	public static ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> userToHashtagsMap = 
			new ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>>();
	
	public static ConcurrentHashMap<String, Integer> hashtagCountMap = new ConcurrentHashMap<String, Integer>();
	
	public static MinMaxPriorityQueue<Pair<String,Integer>> top100hashtags  = 
			MinMaxPriorityQueue.orderedBy(new PairComparator()).maximumSize(100).create();
	
	public static ConcurrentHashMap<String, Integer> locationTweetCountMap = new ConcurrentHashMap<String, Integer>();

	public static  ConcurrentHashMap<String, LinkedList<TweetDetails>> userToTweet = 
			new ConcurrentHashMap<String, LinkedList<TweetDetails>>();
	public void parseTweet(TweetDetails tweet);
}
