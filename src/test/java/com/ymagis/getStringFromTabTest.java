package com.ymagis;

import static org.junit.Assert.*;

import org.junit.Test;

public class getStringFromTabTest {

	@Test
	public void test() {
		MastermindService mastermindServiceTest=new MastermindService();
		String i = "1";
		String[] tab = {i,i,i,i,i,i,i,i};
	    String output = mastermindServiceTest.getStringFromTab(tab);
	    String res = "11111111";
	    assertEquals(res, output); 
	}

}
