package com.apis.tests.twitterApiTests.steps;

import com.apis.implementation.twitter.twitterSend;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertTrue;

public class sendDirectMessageSteps {
    String returnJson = "";

    @Given("^I send the direct message ([^\"]*) to ([^\"]*) ([^\"]*)$")
    public void iTweetSendAMessageToUserName(String message, String userName, String userId) throws Throwable {
        returnJson = twitterSend.newDirectMessageTo(message, userName, userId);
    }

    @Then("^the ([^\"]*) should be successfully sent$")
    public void theMessageShouldBeSuccessfullySent(String message) throws Throwable {
        assertTrue(returnJson.contains("\"text\":\"") && returnJson.contains(message));
    }
}