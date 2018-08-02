package com.ymagis;

import static org.junit.Assert.*;

import org.junit.Test;

public class chargerTabTest {

	@Test
	public void test() {
		MastermindService mastermindServiceTest=new MastermindService();
		int size = 8;
		String i = "1";
		String[] tab = new String[size];
	    String[] output = mastermindServiceTest.chargerTab(tab, size, i);
	    String[] tabCor = {i,i,i,i,i,i,i,i};
	    assertEquals(tabCor, output); 
	}

}
