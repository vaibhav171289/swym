package com.lotus.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.lotus.util.Lockobj;
import com.lotus.util.Pair;

public class AnalyticsImp extends Analytics {
	private Operations operations;

	public AnalyticsImp(Operations op) {
		this.operations = op;
	}

	@Override
	public int getDistinctHashtagCount() {
		Map<String, Integer> hashtagCountMap = Controller.hashtagCountMap;
		if (hashtagCountMap.size() > 0) {
			System.out.println(col_RED);
			System.out.printf("DISTINCT HASHTAGS COUNT: ");
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
		synchronized (Controller.locationTweetCountMap) {
			ConcurrentHashMap<String, Integer> loc = Controller.locationTweetCountMap;
			return loc.get(location);
		}
	}

	@Override
	public void mostUsedText() {

	}

	@Override
	public ArrayList<String> getMostInfluentialHashtags() {
		ArrayList<String> listInDecensdingOrder = new ArrayList<String>();
		synchronized (Controller.top100hashtags) {
			LinkedList<Pair<String, Integer>> top100hashtags = Controller.top100hashtags;
			Iterator<Pair<String, Integer>> it = top100hashtags.iterator();
			while (it.hasNext()) {
				Pair<String, Integer> p = it.next();
				listInDecensdingOrder.add(p.getFirst());
			}
		}
		if (listInDecensdingOrder.size() > 0) {
			System.out.println(col_GREEN);
			System.out.printf("Top trending HashTags: ");
			System.out.print(listInDecensdingOrder);
			System.out.println(col_NC);
		}
		return listInDecensdingOrder;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void highlightMilestoneUsers() {
		// creating a copy before
		
		synchronized (Controller.milestoneUser) {
			LinkedList<Pair<String, Integer>> ll = Controller.milestoneUser;

			while (!ll.isEmpty()) {
				Pair<String, Integer> p = ll.peek();
				ll.poll();
				int count = p.getSecond();
				switch (count) {
				case 5: {
					System.out.println(col_CYAN);
					System.out.printf(
							"Hurray!!!  " + p.getFirst() + " earn a badge of fast learner by tweets of " + count);
					System.out.println(col_NC);
					break;
				}
				case 6: {
					System.out.println(col_YELLOW);
					System.out.printf("Wow!!!  " + p.getFirst() + " earn a badge of flash power by tweets of " + count);
					System.out.println(col_NC);
					break;
				}
				case 7: {
					System.out.println(col_WHITE);
					System.out.printf("Superb!!!  " + p.getFirst() + " earn a badge of thor by tweets of " + count);
					System.out.println(col_NC);
					break;
				}
				default:

				}
			}
		}

	}

}
