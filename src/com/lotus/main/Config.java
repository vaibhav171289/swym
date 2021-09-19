package com.lotus.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.lotus.model.TweetDetails;

public class Config {
	private Gson gsonParser;
	private JsonReader reader = null ;
	public Config(String filepath) {
			gsonParser = new Gson();
			try {
		            reader = new JsonReader(new FileReader(new File(filepath)));
		              
		        reader.beginArray();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public TweetDetails readData() {
		try {
			if(reader.hasNext()) {
			TweetDetails tweet = gsonParser.fromJson(reader, TweetDetails.class);
			return tweet;
			}
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
