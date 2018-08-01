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
import com.ymagis.api.tools.Constantes;

public class Api {

    private static final String baseUrl = "http://172.16.37.129/api/";
    

    public static ResponseVO sendWithMsgBody(String methode, String msgCorps, String apiType) throws IOException {
        URL obj = new URL(baseUrl.concat(apiType));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(methode);
        if (!Constantes.GET_METHOD.equalsIgnoreCase(methode)) {
            con.setDoOutput(true);
        }
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        OutputStreamWriter out = null;
        if (!Constantes.GET_METHOD.equalsIgnoreCase(methode)) {
            out = new OutputStreamWriter(con.getOutputStream(), Constantes.UTF_8);
            out.write(msgCorps);
            out.flush();
            out.close();
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), Constantes.UTF_8));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        Map<String, Object> linkedHashMapFromString = getLinkedHashMapFromString(response.toString());

        in.close();
        con.disconnect();
        return getDataFromResult(linkedHashMapFromString.toString(), Constantes.TEST_END_POINT);
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
            if (Constantes.START_END_POINT.equalsIgnoreCase(api)) {
                resultVO = new ResponseVO();
                resultVO.setName(tabRep[0].split("=")[1]);
                resultVO.setSize(tabRep[1].split("=")[1]);
                resultVO.setIdQuiz(tabRep[2].split("=")[1].substring(0, 1));
            }
            if (Constantes.TEST_END_POINT.equalsIgnoreCase(api)) {
                resultVO = new ResponseVO();
                resultVO.setwPlace(tabRep[0].split("=")[1]);
                resultVO.setgPlace(tabRep[1].split("=")[1].substring(0, 1));
            }
        }
        return resultVO;
    }

}
