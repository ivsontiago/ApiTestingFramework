package com.apis.tests.twitterApiTests.steps;

import com.apis.implementation.twitter.twitterRead;
import com.apis.implementation.twitter.twitterSend;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class retweetAStatusSteps {
    String myTimeline;

    @Given("^I send a request to retweet a ([^\"]*)$")
    public void iSendARequestToRetweetAMessageId(String statusId) throws Throwable {
        twitterSend.retweetAStatus(statusId);
    }

    @When("^I refresh my timeline$")
    public void iRefreshMyTimeline() throws Throwable {
        myTimeline = twitterRead.myLatestTweets();
    }
}