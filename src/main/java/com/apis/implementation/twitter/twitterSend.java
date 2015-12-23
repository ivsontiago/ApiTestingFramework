package com.apis.implementation.twitter;

import com.apis.helpers.createPostRequest;
import com.apis.helpers.genericHelpers;
import com.apis.helpers.propertiesFile;
import com.apis.enums.getUrlsTwitter;
import org.apache.commons.configuration.ConfigurationException;
import org.json.JSONObject;

import java.io.IOException;

public class twitterSend {

    private static void updateLastTweetId(String response) throws ConfigurationException
    {
        if (genericHelpers.validateJson(response))
        {
            JSONObject obj = new JSONObject(response);
            String newTweetId = obj.get("id").toString();
            propertiesFile.setLastTweetId(newTweetId);
        }
    }

    public static String newStatus(String messageToPost) throws IOException, ConfigurationException
    {
        String postRequestUrl = getUrlsTwitter.UpdateStatus.getUrl();
        String[] parameters = {"status=" + genericHelpers.percentEncode(messageToPost)};
        String responseJson = createPostRequest.sendSecureTwitterApiPostRequestAndReturnResponseJson(postRequestUrl, parameters);
        updateLastTweetId(responseJson);
        return responseJson;
    }

    public static String newDirectMessageTo(String message, String userScreenName, String userId) throws IOException
    {
        String postRequestUrl = getUrlsTwitter.SendDirectMessage.getUrl();
        String[] parameters = {"text=" + genericHelpers.percentEncode(message),
                "screen_name=" + userScreenName,
                "user_id=" + userId};
        return createPostRequest.sendSecureTwitterApiPostRequestAndReturnResponseJson(postRequestUrl, parameters);
    }

    public static String retweetAStatus(String statusId) throws IOException, ConfigurationException
    {
        String postRequestUrl = getUrlsTwitter.RetweetAStatus.getUrl().replace(":id", statusId);
        String responseJson = createPostRequest.sendSecureTwitterApiPostRequestAndReturnResponseJson(postRequestUrl);
        updateLastTweetId(responseJson);
        return responseJson;
    }
}