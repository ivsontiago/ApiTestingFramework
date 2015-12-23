package com.apis.tests.spotifyApiTests.steps;

import com.apis.implementation.spotify.spotifyRead;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertTrue;

public class searchAlbumsSteps {
    String response;

    @Given("^I search for an album on Spotify using ([^\"]*)$")
    public void iTweetAnAlbumNameOnSpotify(String albumName) throws Throwable {
        response = spotifyRead.searchAlbums(albumName);
    }

    @Then("^the album should ([^\"]*)$")
    public void theExpectedResultShouldBeCorrect(String expectedResult) throws Throwable {
        if (expectedResult.equals("exist")) {
            assertTrue("Was the item found?", spotifyRead.numberOfItemsFound(response) >= 1);
        } else if (expectedResult.equals("not exist")) {
            assertTrue("Was the item found?", spotifyRead.numberOfItemsFound(response) == 0);
        }
    }
}