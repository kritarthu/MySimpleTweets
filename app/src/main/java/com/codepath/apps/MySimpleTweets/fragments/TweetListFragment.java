package com.codepath.apps.MySimpleTweets.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.MySimpleTweets.EndlessScrollListener;
import com.codepath.apps.MySimpleTweets.R;
import com.codepath.apps.MySimpleTweets.TweetsArrayAdapter;
import com.codepath.apps.MySimpleTweets.models.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kupadhy on 11/1/15.
 */
public abstract class TweetListFragment extends Fragment {

    private TweetsArrayAdapter aTweets;
    private ArrayList<Tweet> tweets;
    protected ListView lvTweets;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list, parent, false);
        lvTweets = (ListView)v.findViewById(R.id.lvTweets);
        lvTweets.setAdapter(aTweets);
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                populateTimeline(page);
                // or customLoadMoreDataFromApi(totalItemsCount);
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(getActivity(), tweets);
    }

    public void addAll(List<Tweet> tweets) {
        aTweets.addAll(tweets);

    }

    protected abstract void populateTimeline(int maxId);

}
