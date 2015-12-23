package com.apis.tests.twitterApiTests.steps;

import com.apis.implementation.twitter.twitterRead;
import com.apis.implementation.twitter.twitterSend;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertTrue;

public class newTweetSteps {

    @Given("^I tweet a ([^\"]*)$")
    public void iTweetAMessage(String message) throws Throwable
    {
        twitterSend.newStatus(message);
    }

    @Then("^the ([^\"]*) should appear on my timeline$")
    public void theMessagesShouldAppearOnMyTimeline(String message) throws Throwable
    {
        String latestTweets = twitterRead.myLatestTweets();
        assertTrue("Was the tweet posted?", latestTweets.contains(message));
    }

}