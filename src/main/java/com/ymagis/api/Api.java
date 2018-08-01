package com.ymagis.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Api {

    public static void main(String[] args) {
        // try {
        // //Map<String, Object> mapResult = new
        // Api().sendWithMsgBody("http://172.16.37.129/api/test", "POST", "{\"token\" :
        // \"tokenmm4\", \"result\" : \"11111\"}");
        // //Map<String, Object> mapResult = new
        // ClasseMain().sendWithMsgBody("http://172.16.37.129/api/start", "POST",
        // "{\"token\" : \"tokenmm4\"}");
        //// System.out.println(mapResult.toString());
        //// ResponseVO resultVO = getDataFromResult(mapResult.toString(), "test");
        // //resultVO resultVO = getDataFromResult(mapResult.toString(), "start");
        //
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

    }

    private static final String baseUrl = "http://172.16.37.129/api/";
    private static final String TEST_END_POINT = "test";
    private static final String START_END_POINT = "start";

    public static ResponseVO sendWithMsgBody(String methode, String msgCorps, String apiType) throws IOException {
        URL obj = new URL(baseUrl.concat(apiType));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(methode);
        if (!"GET".equalsIgnoreCase(methode)) {
            con.setDoOutput(true);
        }
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        OutputStreamWriter out = null;
        if (!"GET".equalsIgnoreCase(methode)) {
            out = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            out.write(msgCorps);
            out.flush();
            out.close();
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        Map<String, Object> linkedHashMapFromString = getLinkedHashMapFromString(response.toString());

        in.close();
        con.disconnect();
        return getDataFromResult(linkedHashMapFromString.toString(), "test");
    }

    /**
     * 
     * @param value
     * @return
     * @throws JsonParseException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getLinkedHashMapFromString(String value) throws JsonParseException, IOException {
        // objet qui sert � convertir en java object
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(value, LinkedHashMap.class);
    }

    public static ResponseVO getDataFromResult(String response, String api) {
        if (null == response || response == "") {
            System.err.println("Error response is empty");
            return null;
        }
        ResponseVO resultVO = null;
        String tabRep[] = response.split(",");
        if (tabRep.length > 1) {
            if (START_END_POINT.equalsIgnoreCase(api)) {
                resultVO = new ResponseVO();
                resultVO.setName(tabRep[0].split("=")[1]);
                resultVO.setSize(tabRep[1].split("=")[1]);
                resultVO.setIdQuiz(tabRep[2].split("=")[1].substring(0, 1));
            }
            if (TEST_END_POINT.equalsIgnoreCase(api)) {
                resultVO = new ResponseVO();
                resultVO.setwPlace(tabRep[0].split("=")[1]);
                resultVO.setgPlace(tabRep[1].split("=")[1].substring(0, 1));
            }
        }
        return resultVO;
    }

}
