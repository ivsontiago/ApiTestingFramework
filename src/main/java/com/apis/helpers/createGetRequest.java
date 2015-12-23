package com.apis.helpers;

import com.apis.enums.getKindsOfRequest;
import com.apis.enums.getUrlsTwitter;
import com.apis.twitterOauthAuthentication.bearerToken;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class createGetRequest {

    private static String sendGetRequest(String urlToGet, List<String[]> headerParameters, getKindsOfRequest kindOfRequest) throws Exception {
        URL obj = new URL(urlToGet);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        String responseCode = "";

        if (headerParameters != null)
            for (int i = 0; i < headerParameters.size(); i++)
                con.setRequestProperty(headerParameters.get(i)[0], headerParameters.get(i)[1]);

        switch (kindOfRequest) {
            case TwitterSecureJson:
                String token = bearerToken.readBearerTokenFromPropertiesFile();
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Host", "api.twitter.com");
                con.setRequestProperty("User-Agent", "AutomatedTestsForTwitter");
                con.setRequestProperty("Authorization", "Bearer " + token);
                con.setUseCaches(false);
                break;
            case TwitterSecureStatus:
                responseCode = Integer.toString(con.getResponseCode());
                return responseCode;
            case NormalJson:
                break;
            case NormalStatus:
                responseCode = Integer.toString(con.getResponseCode());
                return responseCode;
            default:
                break;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    //Twitter Get Apis
    public static String sendGetRequestToTwitterAndReturnResponseJson(String requestUrl) throws Exception {
        List<String[]> headerParameters = null;
        return sendGetRequest(getUrlsTwitter.BaseUrl.getUrl() + requestUrl, headerParameters, getKindsOfRequest.TwitterSecureJson);
    }

    public static String sendGetRequestToTwitterAndReturnResponseStatusCode(String requestUrl) throws Exception {
        List<String[]> headerParameters = null;
        return sendGetRequest(getUrlsTwitter.BaseUrl.getUrl() + requestUrl, headerParameters, getKindsOfRequest.TwitterSecureStatus);
    }


    //Generic Get Apis
    public static String sendGetRequestAndReturnResponseJson(String fullRequestUrl) throws Exception {
        List<String[]> headerParameters = null;
        return sendGetRequest(fullRequestUrl, headerParameters, getKindsOfRequest.NormalJson);
    }

    public static String sendGetRequestAndReturnResponseJson(String fullRequestUrl, List<String[]> headerParameters) throws Exception {
        return sendGetRequest(fullRequestUrl, headerParameters, getKindsOfRequest.NormalJson);
    }

    public static String sendGetRequestAndReturnResponseStatusCode(String fullRequestUrl) throws Exception {
        List<String[]> headerParameters = null;
        return sendGetRequest(fullRequestUrl, headerParameters, getKindsOfRequest.NormalStatus);
    }

    public static String sendGetRequestAndReturnResponseStatusCode(String fullRequestUrl, List<String[]> headerParameters) throws Exception {
        return sendGetRequest(fullRequestUrl, headerParameters, getKindsOfRequest.NormalStatus);
    }
}