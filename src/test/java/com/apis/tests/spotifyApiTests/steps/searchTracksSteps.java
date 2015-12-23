package com.apis.tests.spotifyApiTests.steps;

import com.apis.implementation.spotify.spotifyRead;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertTrue;

public class searchTracksSteps
{
    String response;

    @Given("^I search for a track on Spotify using ([^\"]*)$")
    public void iSearchForATrackOnSpotifyUsingTrackName(String trackName) throws Throwable
    {
        response = spotifyRead.searchTracks(trackName);
    }

    @Then("^the track should ([^\"]*)$")
    public void theTrackShouldExpectedResult(String expectedResult) throws Throwable
    {
        if (expectedResult.equals("exist"))
        {
            assertTrue("Was the item found?", spotifyRead.numberOfItemsFound(response) >= 1);
        }
        else if (expectedResult.equals("not exist"))
        {
            assertTrue("Was the item found?", spotifyRead.numberOfItemsFound(response) == 0);
        }
    }
}