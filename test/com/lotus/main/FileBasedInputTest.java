package com.lotus.main;

import org.junit.jupiter.api.Test;

class FileBasedInputTest {

	@Test
	void test() {
		String args[]= {"data.json"};
		JsonFileInputMain.main(args);
		
	}

}
