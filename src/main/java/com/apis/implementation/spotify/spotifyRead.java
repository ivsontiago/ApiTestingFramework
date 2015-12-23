package com.apis.implementation.spotify;

import com.apis.enums.getUrlsSpotify;
import com.apis.helpers.createGetRequest;
import com.apis.helpers.genericHelpers;
import com.apis.helpers.propertiesFile;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class spotifyRead {

    private static List<String[]> createHeader() throws IOException {
        List<String[]> headerParameters = new ArrayList<>();
        String[] accessKey = {"X-Mashape-Key", propertiesFile.getSpotifySetting("X-Mashape-Key")};
        String[] format = {"Accept", "text/plain"};
        headerParameters.add(accessKey);
        headerParameters.add(format);
        return headerParameters;
    }

    public static String searchAlbums(String albumName) throws Exception {
        return createGetRequest.sendGetRequestAndReturnResponseJson(
                getUrlsSpotify.BaseUrl.getUrl()
                        + getUrlsSpotify.SearchAlbums.getUrl()
                        + "?q="
                        + albumName.replace(" ", "+"),
                createHeader());
    }

    public static int numberOfItemsFound(String response) {
        if (genericHelpers.validateJson(response)) {
            JSONObject responseJson = new JSONObject(response);
            JSONObject resultsInfo = new JSONObject(responseJson.get("info").toString());
            return (int) resultsInfo.get("num_results");
        } else
            return -1;
    }

    public static String searchArtists(String artistName) throws Exception {
        return createGetRequest.sendGetRequestAndReturnResponseJson(
                getUrlsSpotify.BaseUrl.getUrl()
                        + getUrlsSpotify.SearchArtists.getUrl()
                        + "?q="
                        + artistName.replace(" ", "+"),
                createHeader());
    }

    public static String searchTracks(String trackName) throws Exception {
        return createGetRequest.sendGetRequestAndReturnResponseJson(
                getUrlsSpotify.BaseUrl.getUrl()
                        + getUrlsSpotify.SearchArtists.getUrl()
                        + "?q="
                        + trackName.replace(" ", "+"),
                createHeader());
    }
}