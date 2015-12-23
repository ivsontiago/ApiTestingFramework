package com.apis.implementation.twitter;

import com.apis.enums.getUrlsTwitter;
import com.apis.helpers.createGetRequest;
import com.apis.helpers.genericHelpers;
import org.json.JSONArray;
import org.json.JSONObject;

public class twitterRead {

    public static String followersList() throws Exception {
        return createGetRequest.sendGetRequestToTwitterAndReturnResponseJson(getUrlsTwitter.GetFollowersList.getUrl());
    }

    public static int myAmountOfFollowers(String followersList) {
        Object json = genericHelpers.convertStringToJsonObjectOrArray(followersList);

        if (genericHelpers.validateJson(json.toString())) {
            JSONObject jsonObj = (JSONObject) json;
            JSONArray idsList = (JSONArray) jsonObj.get("ids");
            return genericHelpers.getJsonArrayLength(idsList.toString());
        }
        return 0;
    }

    public static String otherUsersStatusRetweets(String statusId) throws Exception {
        String requestUrl = getUrlsTwitter.GetRetweets.getUrl().replace(":id", statusId);
        return createGetRequest.sendGetRequestToTwitterAndReturnResponseJson(requestUrl);
    }

    public static String myLatestTweets() throws Exception {
        return createGetRequest.sendGetRequestToTwitterAndReturnResponseJson(getUrlsTwitter.GetUserTimeline.getUrl());
    }
}