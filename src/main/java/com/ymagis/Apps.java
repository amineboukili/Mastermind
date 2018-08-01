package com.ymagis;/*
 * (c) Copyright Ymagis S.A.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
*/


import com.ymagis.api.ApiClient;
import com.ymagis.responses.StartResponse;
import com.ymagis.responses.TestResponse;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Team_4
 */
public class Apps {

    public static void main(String[] args) {
        try {
            System.out.println("Testing schedule retrieval: OK");
            //ApiClient apiClient = new ApiClient("http://172.16.37.129/api/","token","tokenmm4",30000,3000);
            ApiClient apiClient = new ApiClient("http://172.16.37.129/api/","token","tokenmm4", 30000,3000);
//            StartResponse startResponse = apiClient.getStartResponse();
//            System.out.println(startResponse.toString());
            TestResponse testResponse = apiClient.getTestResponse("11111");
            System.out.println(testResponse.toString());
            
        } catch (Exception ex) {
            Logger.getLogger(Apps.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
