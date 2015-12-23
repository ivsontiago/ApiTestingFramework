package com.apis.tests.setUp;

import com.apis.implementation.twitter.twitterDelete;
import com.apis.twitterOauthAuthentication.bearerToken;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class hooks
{
    @Before("@DeleteLastTweet")
    public void testStart() throws Throwable {
        bearerToken.refreshBearerTokenOnPropertiesFile();
    }

    @After("@DeleteLastTweet")
    public void testFinish() throws Throwable {
        twitterDelete.myLastTweet();
    }
}