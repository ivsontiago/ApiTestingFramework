package com.apis.tests.spotifyApiTests.steps;

import com.apis.implementation.spotify.spotifyRead;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertTrue;

public class searchArtistsSteps
{
    String response;

    @Given("^I search for an artist on Spotify using ([^\"]*)$")
    public void iSearchForAnArtistOnSpotifyUsingArtistName(String artistName) throws Throwable
    {
        response = spotifyRead.searchArtists(artistName);
    }

    @Then("^the artist should ([^\"]*)$")
    public void theExpectedResultShouldBeCorrect(String expectedResult) throws Throwable
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