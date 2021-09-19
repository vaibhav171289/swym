package com.lotus.controller;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.lotus.model.TweetDetails;

class OperationsTest {
    private Operations op = new Operations();
	@Test
	void test() {
		
		TweetDetails tweet1 = new TweetDetails();
		tweet1.setUsername("vaibhav");
		tweet1.setLocation("hyderabad");
		tweet1.setHashtag("#swym");
		tweet1.setText("hi");
		op.parseTweet(tweet1);
		TweetDetails tweet2 = new TweetDetails();
		tweet2.setUsername("anuj");
		tweet2.setLocation("pune");
		tweet2.setHashtag("#awesome");
		tweet2.setText("hello");
		op.parseTweet(tweet2);
		TweetDetails tweet3 = new TweetDetails();
		tweet3.setUsername("vaibhav");
		tweet3.setLocation("london");
		tweet3.setHashtag("#swym");
		tweet3.setText("how are u?");
		op.parseTweet(tweet3);
		
		assert(Controller.hashtagCountMap.size() == 2);
		assert(Controller.locationTweetCountMap.size() == 3);
		assert(Controller.top100hashtags.size() ==3);
		LinkedList<TweetDetails> ll = Controller.userToTweet.get("vaibhav");
		assert(ll.size() == 2);
		LinkedList<TweetDetails> ll1 = Controller.userToTweet.get("anuj");
		assert(ll1.size() == 1);
	}
  
}
