package com.codepath.apps.MySimpleTweets.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kupadhy on 10/25/15.
 */
public class User {
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public static User fromJSON(JSONObject jsonObject) {
        User user = new User();
        try {
            user.name = jsonObject.getString("name");
            user.uid = jsonObject.getLong("id");
            user.profileImageUrl = jsonObject.getString("profile_image_url");
            user.screenName = jsonObject.getString("screen_name");

        } catch (JSONException e) {
            Log.d("DEBUG", e.getMessage());
        }
        return user;
    }

}
