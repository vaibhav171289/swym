package com.lotus.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
class AnalyticsTest {

	@Test
	void test() {
		AnalyticsImp ai = new AnalyticsImp(null);
		ai.getMostInfluentialHashtags();
	}

}
