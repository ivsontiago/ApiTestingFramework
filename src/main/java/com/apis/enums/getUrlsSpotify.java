package com.apis.enums;

public enum getUrlsSpotify {

    BaseUrl("https://mager-spotify-web.p.mashape.com/"),

    //Get requests:
    SearchAlbums("search/1/album.json"),
    SearchArtists("search/1/artist.json"),
    SearchTracks("search/1/track.json");

    private String Url;

    getUrlsSpotify(String s) {
        Url = s;
    }

    public String getUrl() {
        return Url;
    }
}