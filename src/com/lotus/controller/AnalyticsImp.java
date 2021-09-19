package com.lotus.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.MinMaxPriorityQueue;
import com.lotus.util.Pair;

public class AnalyticsImp extends Analytics {
	private Operations operations;

	public AnalyticsImp(Operations op) {
		this.operations = op;
	}

	@Override
	public synchronized int getDistinctHashtagCount() {
		Map<String, Integer> hashtagCountMap = Controller.hashtagCountMap;
		if (hashtagCountMap.size() > 0) {
			System.out.println(col_RED);
			System.out.printf("DISTINCT HASHTAGS COUNT");
			for (Entry<String, Integer> e : hashtagCountMap.entrySet()) {
				System.out.printf(e.getKey() + " : " + e.getValue() + " , ");
			}
			System.out.println(col_NC);
		}
		return hashtagCountMap.size();
	}

	@Override
	public int getDistinctUserCount() {
		System.out.println(col_PURPLE);
		System.out.printf("DISTINCT USER COUNT: " + Controller.userToTweet.size());
		System.out.println(col_NC);
		return Controller.userToTweet.size();
	}

	@Override
	public int getLocationBasedTweetCount(String location) {
		ConcurrentHashMap<String, Integer> loc = Controller.locationTweetCountMap;
		return loc.get(location);
	}

	@Override
	public void mostUsedText() {

	}

	@Override
	public synchronized ArrayList<String> getMostInfluentialHashtags() {
		MinMaxPriorityQueue<Pair<String, Integer>> top100hashtags = Controller.top100hashtags;
		ArrayList<String> listInDecensdingOrder = new ArrayList<String>();
		Iterator<Pair<String, Integer>> it = top100hashtags.iterator();
		while (it.hasNext()) {
			Pair<String, Integer> p = it.next();
			listInDecensdingOrder.add(p.getFirst());
		}
//		Collections.reverse(listInDecensdingOrder);
		if (listInDecensdingOrder.size() > 0) {
			System.out.println(col_GREEN);
			System.out.printf("Top trending HashTags: ");
			System.out.print(listInDecensdingOrder);
			System.out.println(col_NC);
		}
		return listInDecensdingOrder;
	}

}
