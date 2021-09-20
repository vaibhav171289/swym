package com.lotus.controller;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import com.lotus.model.TweetDetails;
import com.lotus.util.Pair;

public interface Controller {
	/*first is user and second will be tweets list*/
	public static ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> userToHashtagsMap = 
			new ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>>();
	
	public static ConcurrentHashMap<String, Integer> hashtagCountMap = new ConcurrentHashMap<String, Integer>();
	
	public static LinkedList<Pair<String,Integer>> top100hashtags  = 
			new  LinkedList<Pair<String,Integer>>();
	
	public static ConcurrentHashMap<String, Integer> locationTweetCountMap = new ConcurrentHashMap<String, Integer>();

	public static  ConcurrentHashMap<String, LinkedList<TweetDetails>> userToTweet = 
			new ConcurrentHashMap<String, LinkedList<TweetDetails>>();
	public static LinkedList<Pair<String,Integer>> milestoneUser = new LinkedList<Pair<String,Integer>>();
	
	public void parseTweet(TweetDetails tweet);
}
