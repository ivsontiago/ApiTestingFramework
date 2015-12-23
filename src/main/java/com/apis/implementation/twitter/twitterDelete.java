package com.apis.implementation.twitter;

import com.apis.enums.getUrlsTwitter;
import com.apis.helpers.createPostRequest;

import java.io.IOException;

public class twitterDelete {

    public static String myLastTweet() throws IOException {
        String postRequestUrl = getUrlsTwitter.DeleteStatusById.getUrl();
        return createPostRequest.sendSecureTwitterApiPostRequestAndReturnResponseJson(postRequestUrl);
    }
}