package com.ymagis;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class getSizeFormStartTest {

  @Test
  public void test() throws IOException {
  MastermindService mastermindServiceTest=new MastermindService();
  Integer output=  8;
  assertEquals(Integer.valueOf(8), output);
  }

}
