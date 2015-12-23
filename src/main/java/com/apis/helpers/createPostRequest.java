package com.apis.helpers;

import com.apis.twitterOauthAuthentication.authorizationHeader;
import com.apis.enums.getKindsOfRequest;
import com.apis.enums.getUrlsTwitter;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static com.apis.enums.getKindsOfRequest.NormalJson;
import static com.apis.enums.getKindsOfRequest.NormalStatus;
import static com.apis.enums.getKindsOfRequest.TwitterSecureStatus;
import static com.apis.enums.getKindsOfRequest.TwitterSecureJson;

public class createPostRequest
{
    private static String sendPostRequest(String postUrl, String [] parameters, getKindsOfRequest kindOfPost) throws IOException
    {
        String urlToRequest = postUrl;
        StringBuilder builder = new StringBuilder();
        if (parameters.length > 0)
        {
            for(String s : parameters)
            {
                builder.append(s+"&");
            }
            urlToRequest = urlToRequest + "?" + builder.toString();
            urlToRequest = urlToRequest.substring(0, urlToRequest.length()-1);
        }

        if (urlToRequest.equals(getUrlsTwitter.BaseUrl.getUrl() + getUrlsTwitter.DeleteStatusById.getUrl()))
        {
            postUrl = urlToRequest = urlToRequest.replace(":id", propertiesFile.getTwitterSetting("lastTweetId"));
        }

        URL obj = new URL(urlToRequest);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");

        if (kindOfPost == getKindsOfRequest.TwitterSecureJson)
        {
            String authorizationValue = authorizationHeader.buildAuthorizationString(postUrl, parameters);
            con.setRequestProperty("Authorization", authorizationValue);
        }

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.flush();
        wr.close();

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


    public static String sendSecureTwitterApiPostRequestAndReturnResponseJson(String requestUrl, String[] parameters) throws IOException
    {
        return sendPostRequest(getUrlsTwitter.BaseUrl.getUrl() + requestUrl, parameters, TwitterSecureJson);
    }

    public static String sendSecureTwitterApiPostRequestAndReturnResponseJson(String requestUrl) throws IOException
    {
        String[] parameters = {};
        return sendPostRequest(getUrlsTwitter.BaseUrl.getUrl() + requestUrl, parameters, TwitterSecureJson);
    }


    public static String sendSecureTwitterApiPostRequestAndReturnStatusCode(String requestUrl, String[] parameters) throws Exception
    {
        return sendPostRequest(getUrlsTwitter.BaseUrl.getUrl() + requestUrl, parameters, TwitterSecureStatus);
    }

    public static String sendSecureTwitterApiPostRequestAndReturnStatusCode(String requestUrl) throws Exception
    {
        String[] parameters = {};
        return sendPostRequest(getUrlsTwitter.BaseUrl.getUrl() + requestUrl, parameters, TwitterSecureStatus);
    }


    public static String sendPostRequestAndReturnResponseJson(String requestUrl, String[] parameters) throws IOException
    {
        return sendPostRequest(getUrlsTwitter.BaseUrl.getUrl() + requestUrl, parameters, NormalJson);
    }

    public static String sendPostRequestAndReturnResponseJson(String requestUrl) throws IOException
    {
        String[] parameters = {};
        return sendPostRequest(getUrlsTwitter.BaseUrl.getUrl() + requestUrl, parameters, NormalJson);
    }


    public static String sendPostRequestAndReturnResponseStatusCode(String requestUrl, String[] parameters) throws Exception
    {
        return sendPostRequest(getUrlsTwitter.BaseUrl.getUrl() + requestUrl, parameters, NormalStatus);
    }

    public static String sendPostRequestAndReturnResponseStatusCode(String requestUrl) throws Exception
    {
        String[] parameters = {};
        return sendPostRequest(getUrlsTwitter.BaseUrl.getUrl() + requestUrl, parameters, NormalStatus);
    }

}