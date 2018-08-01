package com.ymagis;

import static org.junit.Assert.*;
import org.junit.Test;

public class chargerTabStarTest {

  @Test
  public void test() {
    MastermindService mastermindServiceTest=new MastermindService();
    String[] tabIndCorr = {"*","*","*","*","*","*","*","*"};
    String[] tabCor = {"0","*","*","*","*","*","*","*"};
    String[] output =  mastermindServiceTest.chargerTabStar(tabCor,8, 0, 0, tabIndCorr);
    
    assertEquals(tabCor, output);  }

}
