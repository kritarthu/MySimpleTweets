package com.codepath.apps.MySimpleTweets;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
	public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
	public static final String REST_CONSUMER_KEY = "2d2rnuGTDFTiFgzK5jNByWTwd";       // Change this
	public static final String REST_CONSUMER_SECRET = "bpW3mwZoRMGFHqPgIbxIPnDD0m34MiU1tKuTLfBDX96MmvtODR"; // Change this
	public static final String REST_CALLBACK_URL = "oauth://cpsimpletweets"; // Change this (here and in manifest)

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	// CHANGE THIS
	// DEFINE METHODS for different API endpoints here
	public void getInterestingnessList(AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("?nojsoncallback=1&method=flickr.interestingness.getList");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
		params.put("format", "json");
		client.get(apiUrl, params, handler);
	}


	public void getHomePageTimeline(int maxId, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/home_timeline.json");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
		params.put("count", 15);
		params.put("since_id", maxId);
        Log.d("DEBUG", "getHomePageTimeline" + maxId);
        client.get(apiUrl, params, handler);
	}

    public void getUserTimeline(String screenname,int maxId, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/user_timeline.json");
        // Can specify query string params directly or through RequestParams.
        RequestParams params = new RequestParams();
        params.put("count", 15);
        params.put("since_id", maxId);
        params.put("screen_name", screenname);
        Log.d("DEBUG", "getUserTimeline"+maxId);
        client.get(apiUrl, params, handler);
    }

    public void getUserInfo(String screenname, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("users/show.json");
        Log.d("DEBUG", "getUserInfo " + screenname);
        RequestParams params = new RequestParams();
        if(screenname!=null) {
            params.put("screen_name", screenname);
        } else {
            params = null;
        }
        client.get(apiUrl, params, handler);
    }

	public void getMentionsTimeline(int maxId, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/mentions_timeline.json");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
        Log.d("DEBUG", "getMentionsTimeline"+maxId);
        params.put("count", 15);
        params.put("since_id", maxId);
        client.get(apiUrl, params, handler);
	}

    public void getUserDetails(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("account/settings.json");
        // Can specify query string params directly or through RequestParams.
        RequestParams params = new RequestParams();
        client.get(apiUrl, params, handler);
    }

    public void verifyCredentials(AsyncHttpResponseHandler handler) {
        Log.d("DEBUG", "verifyCredentials");
        String apiUrl = getApiUrl("account/verify_credentials.json");
        // Can specify query string params directly or through RequestParams.
        client.get(apiUrl, null, handler);
    }

    public void getUserProfileImageDetails(String username, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("users/show.json?screen_name="+username);
        // Can specify query string params directly or through RequestParams.
        RequestParams params = new RequestParams();
        client.get(apiUrl, params, handler);
    }

    public void tweet(String tweet, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/update.json");
        // Can specify query string params directly or through RequestParams.
        RequestParams params = new RequestParams();
        params.put("status", tweet);
        client.post(apiUrl, params, handler);
    }
}