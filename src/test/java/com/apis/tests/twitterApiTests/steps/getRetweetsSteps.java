package com.apis.tests.twitterApiTests.steps;

import com.apis.helpers.genericHelpers;
import com.apis.implementation.twitter.twitterRead;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertTrue;

public class getRetweetsSteps {

    String response = "";

    @Given("^I request the last retweets of status ID (\\d+)$")
    public void iRequestTheLastRetweetsOfStatusID(String statusId) throws Throwable {
        response = twitterRead.otherUsersStatusRetweets(statusId);
    }

    @Then("^the response should contain at least (\\d+) retweets$")
    public void theResponseShouldContainAtLeastRetweets(int numberOfRetweets) throws Throwable {
        assertTrue("Does the status have " + numberOfRetweets + " retweets?", genericHelpers.getJsonArrayLength(response) >= numberOfRetweets);
    }
}
