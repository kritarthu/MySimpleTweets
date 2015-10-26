package com.codepath.apps.MySimpleTweets.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kupadhy on 10/25/15.
 */
public class Tweet {

    private String body;
    private long uid;
    private String createdAt;
    private User user;

    public User getUser() {
        return user;
    }

    public static Tweet fromJSON(JSONObject jsonObject) {

        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));

        } catch (JSONException e) {
            Log.d("DEBUG", e.getMessage());
        }
        return tweet;
    }

    public long getUid() {
        return uid;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public static ArrayList<Tweet> fromJSONArray (JSONArray jsonArray){

        ArrayList<Tweet> tweetArrayList = new ArrayList<Tweet>();
        for(int i = 0 ; i < jsonArray.length(); i++) {
            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = fromJSON(tweetJson);
                if(tweet!=null) {
                    tweetArrayList.add(tweet);
                }
            } catch (JSONException e) {
                Log.d("DEBUG", e.getMessage());
                continue;
            }
        }
        return tweetArrayList;
    }
}
