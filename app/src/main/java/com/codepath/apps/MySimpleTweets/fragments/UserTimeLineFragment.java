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
public class UserTimeLineFragment extends TweetListFragment {

    private TwitterClient client;
    public static UserTimeLineFragment newInstance(String screen_name) {
        UserTimeLineFragment userTimeLineFragment = new UserTimeLineFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screen_name);
        userTimeLineFragment.setArguments(args);
        return userTimeLineFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        populateTimeline();
    }

    private void populateTimeline() {
        String screen_name = getArguments().getString("screen_name");
        client.getUserTimeline(screen_name, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("DEBUG", "Response1" + response.toString());
                addAll(Tweet.fromJSONArray(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject object) {
                Log.d("DEBUG", object.toString());
            }
        });
    }
}

