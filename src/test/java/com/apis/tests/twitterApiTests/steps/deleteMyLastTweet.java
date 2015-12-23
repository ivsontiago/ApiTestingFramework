package com.apis.tests.twitterApiTests.steps;

import com.apis.implementation.twitter.twitterDelete;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class deleteMyLastTweet {

    String responseJson;

    @When("^I send a request to delete the message$")
    public void iSendARequestToDeleteTheMessage() throws Throwable {
        responseJson = twitterDelete.myLastTweet();
    }

    @Then("^the ([^\"]*) should be deleted$")
    public void theMessageShouldBeDeleted(String message) throws Throwable {
        assertTrue(responseJson.contains(message));
    }
}