package com.lotus.controller;

import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.lotus.model.TweetDetails;
import com.lotus.util.Pair;

public class Operations implements Controller {

	@Override
	public void parseTweet(TweetDetails tweet) {
		String username = tweet.getUsername();
		String location = tweet.getLocation();
		String hashtag = tweet.getHashtag();
		String text = tweet.getText();
		/*
		 * System.out.println("username: " + username); System.out.println("location: "
		 * + location); System.out.println("hashtag: " + hashtag);
		 * System.out.println("text: " + text);
		 * System.out.println("--------------------------------------------");
		 */
		updateUserMap(username, hashtag);

		updateDistinctHasttag(hashtag);

		updateLocationTweetCount(location);

		saveTweet(tweet);

	}

	private synchronized void saveTweet(TweetDetails tweet) {
		if (userToTweet.containsKey(tweet.getUsername())) {
			LinkedList<TweetDetails> ll = userToTweet.get(tweet.getUsername());
			ll.add(tweet);
		} else {
			LinkedList<TweetDetails> ll = new LinkedList<TweetDetails>();
			ll.add(tweet);
			userToTweet.put(tweet.getUsername(), ll);
		}
	}

	private synchronized void updateUserMap(String username, String hashtag) {
		if (userToHashtagsMap.containsKey(username)) {
			ConcurrentHashMap<String, Integer> value = userToHashtagsMap.get(username);
			value.put(hashtag, value.getOrDefault(hashtag, 0) + 1);
		} else {
			ConcurrentHashMap<String, Integer> value = new ConcurrentHashMap<String, Integer>();
			value.put(hashtag, 1);
			userToHashtagsMap.put(username, value);
		}
	}

	private synchronized void updateDistinctHasttag(String hashtag) {
		int old  = hashtagCountMap.getOrDefault(hashtag, 0);
		hashtagCountMap.put(hashtag, hashtagCountMap.getOrDefault(hashtag, 0) + 1);

		if(old  > 0) {
			top100hashtags.remove(new Pair<String, Integer>(hashtag, old));
		}
		
		int hashtagCount = hashtagCountMap.get(hashtag);
		top100hashtags.add(new Pair<String, Integer>(hashtag, hashtagCount));
	}

	private synchronized void updateLocationTweetCount(String location) {
		locationTweetCountMap.put(location, locationTweetCountMap.getOrDefault(location, 0) + 1);
	}
}
