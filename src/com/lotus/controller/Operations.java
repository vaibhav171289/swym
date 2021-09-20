package com.lotus.controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import com.lotus.model.TweetDetails;
import com.lotus.util.Lockobj;
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
		LinkedList<TweetDetails> ll = null;
		if (userToTweet.containsKey(tweet.getUsername())) {
			ll = userToTweet.get(tweet.getUsername());
			ll.add(tweet);
		} else {
			ll = new LinkedList<TweetDetails>();
			ll.add(tweet);
			userToTweet.put(tweet.getUsername(), ll);
		}
		switch (ll.size()) {
		case 5:
			updateMileStoneUsers(new Pair<String, Integer>(tweet.getUsername(), 5));
			break;
		case 6:
			updateMileStoneUsers(new Pair<String, Integer>(tweet.getUsername(), 6));
			break;
		case 7:
			updateMileStoneUsers(new Pair<String, Integer>(tweet.getUsername(), 7));
			break;
		default:
			break;

		}
	}

	private void updateUserMap(String username, String hashtag) {
		if (userToHashtagsMap.containsKey(username)) {
			ConcurrentHashMap<String, Integer> value = userToHashtagsMap.get(username);
			value.put(hashtag, value.getOrDefault(hashtag, 0) + 1);
		} else {
			ConcurrentHashMap<String, Integer> value = new ConcurrentHashMap<String, Integer>();
			value.put(hashtag, 1);
			userToHashtagsMap.put(username, value);
		}
	}

	private void updateDistinctHasttag(String hashtag) {
		synchronized (top100hashtags) {
			hashtagCountMap.put(hashtag, hashtagCountMap.getOrDefault(hashtag, 0) + 1);
		
			int hashtagCount = hashtagCountMap.get(hashtag);
			if(top100hashtags.isEmpty()) {
				top100hashtags.add(new Pair<String,Integer>(hashtag, hashtagCount));
			}else
			if(top100hashtags.peekLast().getSecond() >= hashtagCount) {
				if(top100hashtags.size() < 100) {
					top100hashtags.addLast(new Pair<String,Integer>(hashtag, hashtagCount));
				}
			}else
			  updateTrendingHashtags(hashtag, hashtagCount);
		}

	}

	private void updateTrendingHashtags(String hashtag, int count) {
		Iterator<Pair<String, Integer>> it = top100hashtags.iterator();
		boolean notfound = true;
		Pair<String, Integer> p = null;
		while (it.hasNext()) {
			p = it.next();
			if (p.getFirst().equalsIgnoreCase(hashtag)) {
				notfound = false;
				break;
			}
		}
		if (!notfound) {
			top100hashtags.remove(p);
			p.setSecond(count);
			if(!top100hashtags.isEmpty())

			if(top100hashtags.isEmpty())
				top100hashtags.add(p);
			else
			if ( p.getSecond() > top100hashtags.peekFirst().getSecond()) {
				top100hashtags.addFirst(p);
			} else {
				int index = 0;
				it = top100hashtags.iterator();
				while (it.hasNext()) {
					Pair<String, Integer> temp = it.next();
					if (temp.getSecond() <= p.getSecond()) {
						top100hashtags.add(index, p);
						return;
					}
					index++;
				}
			}
		}
	}

	private void updateLocationTweetCount(String location) {
		synchronized (locationTweetCountMap) {
			locationTweetCountMap.put(location, locationTweetCountMap.getOrDefault(location, 0) + 1);
		}
	}

	private void updateMileStoneUsers(Pair<String, Integer> p) {
		synchronized (milestoneUser) {
			milestoneUser.add(p);
		}

	}
}
