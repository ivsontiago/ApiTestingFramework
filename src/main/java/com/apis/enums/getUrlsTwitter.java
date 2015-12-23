package com.apis.enums;

public enum getUrlsTwitter {

    BaseUrl("https://api.twitter.com/1.1/"),

    //Get requests:
    GetUserTimeline("statuses/user_timeline.json?screen_name=ivson__&count=1"),
    GetFollowersList("followers/ids.json?cursor=-1&screen_name=ivson__&count=5000"),

    //Post requests:
    UpdateStatus("statuses/update.json"),
    DeleteStatusById("statuses/destroy/:id.json"),
    SendDirectMessage("direct_messages/new.json"),
    GetRetweets("statuses/retweets/:id.json"),
    RetweetAStatus("statuses/retweet/:id.json");

    private String Url;

    getUrlsTwitter(String s) {
        Url = s;
    }

    public String getUrl() {
        return Url;
    }
}