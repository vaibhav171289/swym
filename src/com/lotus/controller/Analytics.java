package com.lotus.controller;

import java.util.ArrayList;

public abstract class Analytics {
	public static final String col_BLUE = "\033[1;34m";
    public static final String col_CYAN = "\033[1;36m";
    public static final String col_GREEN = "\033[1;32m";
    public static final String col_PURPLE = "\033[1;35m";
    public static final String col_RED = "\033[1;31m";
    public static final String col_WHITE = "\033[1;37m";
    public static final String col_YELLOW = "\033[1;33m";
    public static final String col_blue = "\033[0;34m";
    public static final String col_cyan = "\033[0;36m";
    public static final String col_green = "\033[0;32m";
    public static final String col_grey = "\033[1;30m";
    public static final String col_purple = "\033[0;35m";
    public static final String col_red = "\033[0;31m";
    public static final String col_white = "\033[0;37m";
    public static final String col_yellow = "\033[0;33m";
    public static final String col_NC = "\033[0m";

    public static final String col_rev = "\033[7m";
    public static final String col_reset = "\033[m";
	public abstract int getDistinctHashtagCount();
	public abstract int getDistinctUserCount();
	public abstract int getLocationBasedTweetCount(String location);
	public abstract void mostUsedText();
	public abstract ArrayList<String> getMostInfluentialHashtags();
	public abstract void highlightMilestoneUsers();

}
