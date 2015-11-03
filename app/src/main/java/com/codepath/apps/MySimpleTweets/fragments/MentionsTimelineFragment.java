package com.codepath.apps.MySimpleTweets.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.MySimpleTweets.TwitterApplication;
import com.codepath.apps.MySimpleTweets.TwitterClient;
import com.codepath.apps.MySimpleTweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by kupadhy on 11/1/15.
 */
public class MentionsTimelineFragment extends TweetListFragment{
    private TwitterClient client;
    public static String username;
    public static String imageUrl;
    public static String name;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        populateTimeLine();
    }

    public void populateTimeLine() {
        client.getMentionsTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("DEBUG", "Response" + response.toString());
                addAll(Tweet.fromJSONArray(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject object) {
                Log.d("DEBUG", object.toString());

            }
        });
    }
}
