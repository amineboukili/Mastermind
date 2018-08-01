package com.ymagis;

import static org.junit.Assert.*;
import org.junit.Test;

public class getSizeOffTabStartTest {

  @Test
  public void test() {

    MastermindService mastermindServiceTest=new MastermindService();
    String[] output =  mastermindServiceTest.getSizeOffTabStart(8);
    String[] tabCor = {"*","*","*","*","*","*","*","*"};
    assertEquals(tabCor, output); 
  }

}
