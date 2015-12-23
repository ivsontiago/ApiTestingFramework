package com.apis.tests.twitterApiTests.steps;

import com.apis.implementation.twitter.twitterRead;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertTrue;

public class retrieveFollowersListSteps {
    private String followersList;

    @Given("^I request my list of followers$")
    public void iRequestMyListOfFollowers() throws Throwable {
        followersList = twitterRead.followersList();
    }

    @Then("^I must have at least (\\d+) follower$")
    public void iMustHaveAtLeastFollower(int expectedNumberOfFollowers) throws Throwable {
        int numberOfFollowers = twitterRead.myAmountOfFollowers(followersList);
        assertTrue(numberOfFollowers >= expectedNumberOfFollowers);
    }
}
