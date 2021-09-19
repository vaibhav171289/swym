package com.lotus.main;

import com.lotus.model.TweetDetails;
import com.lotus.workers.ServiceThread;
import com.lotus.workers.Workers;
import com.lotus.workers.TweetPipeline;

/**
 * Imagine you are building a system which accepts a stream of events/data eg:
 * tweets. The system allows to configure a number of condition/action pairs,
 * eg: 10 tweets from userX, hashtag=awesome-hashtag, and when these queries are
 * hit a set of actions can be called on these hits.
 * 
 * 
 * Examples of events/data could be a record with attributes like username,
 * hashtag, content, location, etc. Condtitions can be configured and with
 * actions to be called when those conditions are hit. Examples of actions could
 * be an external HTTP API call or RPC of some sort with the result of the
 * query. Keeping the time constraints for the assignment in mind, please limit
 * yourself to choosing simple events/query.
 * 
 * A few examples are below:
 * 
 * We set a condition that whenever someone tweets from a specific location say
 * Pune, India or London, UK call a third party API with that data. For
 * simplicity, Instead of calling a 3rd party API you can just print on the
 * screen the fact that this tweet was made from that location. Sample input:
 * {"user-name": "Anuj", "location": "Pune", "text": "hello world"}
 * {"user-name": "Vaibhav", "location": "London", "text": "hello bitter world"}
 * 
 * 
 * 
 * Another example: we set a rule that whenever a user hits 1000 tweets we tag
 * their tweet with a special emoji or change it's display color. For
 * simplicity, you can take the input from a text/csv/json file or drive your
 * code using unit tests. For the output you can just display on the screen that
 * the condition of X tweets was met for user Y.
 * 
 * 
 * The actual choice of attributes, queries and actions is left to your
 * discretion, and it will be very helpful to understand the rationale behind
 * your choices. And of course, feel free to make any assumptions (such as
 * building the initial solution for a specific query on a specific attribute,
 * but being able to talk about how you make it more generic, build scale etc.)
 * that help you appropriately define the scope of the assignment (plan for the
 * exercise to be no more than 2-3 hours of work).
 * 
 * 
 * Here's what we will focus on while reviewing/evaluating the app:
 * 
 * 1. Functional completeness given the scope you defined.
 * 
 * 2. Quality - Does it handle the basic scenarios well? Does it handle the edge
 * cases well?
 * 
 * 3. Simplicity and the ability of the solution to work on different products
 * 
 * 4. Overall approach, design and architecture
 * 
 * 5. Deployment of your service - totally open ended and up to you, so can be
 * local on a laptop, can be on the cloud, can be some hosted app
 * infrastructure, whatever you are comfortable with.
 * 
 * 5. Code review - ideally, checking into a Git repo would be great. Let me
 * know if you have issues doing that.
 * 
 * 6. Fancy ideas for features - even if you aren't implementing, I'd love to
 * hear your ideas on other features you'd consider adding and how you would
 * approach them
 */
public class JsonFileInputMain {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("please provide a valid input file");
		}
		String filename = args[0];
		JsonFileInputMain object = new JsonFileInputMain();
		Config config = new Config(filename);
		TweetDetails tweet = null;
		Workers pool = new Workers(10,1);
		pool.activateTweetWorkers();
		pool.activateAnalyticsWorkers();
		TweetPipeline pipeline = TweetPipeline.getInstance();
		while ((tweet = config.readData()) != null) {
			pipeline.addTweet(tweet);
		}

	}

}
