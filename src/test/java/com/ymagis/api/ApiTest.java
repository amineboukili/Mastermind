/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ymagis.api;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Team_4
 */
public class ApiTest {

    public ApiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of start method, of class ApiClient.
     */
    @Test
    public void testStart() throws Exception {
//        ResponseVO ret = new ResponseVO();
//        ret.setName(UUID.randomUUID().toString());
//        ret.setSize(String.valueOf(Math.random()));
//        ret.setIdQuiz(String.valueOf(Math.random()));
//
//        ClientResponse res = Mockito.mock(ClientResponse.class);
//        Mockito.when(res.getEntity(StartReturn.class)).thenReturn(ret);
//
//        Api client = Mockito.spy(new Api());
//        Mockito.when(client.execute(Mockito.anyString())).thenReturn(res);
//        assertTrue(ret.getSize() == client.start());
//
//        try {
//            client.start();
//            fail("Exception should have been thrown");
//        } catch (Exception e) {
//            assertEquals(ret.getError(), e.getMessage());
//        }
    }

    /**
     * Test of test method, of class ApiClient.
     */
    @Test
    public void testTest() throws Exception {
//        ProposalResult ret = new ProposalResult();
//        ret.setGood(Double.valueOf(Math.random()).intValue());
//        ret.setWrongPlace(Double.valueOf(Math.random()).intValue());
//
//        ClientResponse res = Mockito.mock(ClientResponse.class);
//        Mockito.when(res.getEntity(ProposalResult.class)).thenReturn(ret);
//
//        ApiClient client = Mockito.spy(new ApiClient());
//        Mockito.when(client.execute(Mockito.anyString(), Mockito.anyString())).thenReturn(res);
//
//        ProposalResult newRes = client.test(UUID.randomUUID().toString());
//        assertTrue(ret.getGood() == newRes.getGood());
//        assertTrue(ret.getWrongPlace()== newRes.getWrongPlace());
//
//        ret.setError(UUID.randomUUID().toString());
//        try {
//            client.test(UUID.randomUUID().toString());
//            fail("Exception should have been thrown");
//        } catch (Exception e) {
//            assertEquals(ret.getError(), e.getMessage());
//        }
    }

}