package com.codepath.apps.MySimpleTweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONObject;

public class TweetActivity extends AppCompatActivity {

    private ImageView ivProfileImage;
    private TextView tvUserName;
    private EditText etTweet;
    private TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        client = TwitterApplication.getRestClient();
        Intent i = getIntent();
        tvUserName = (TextView)findViewById(R.id.tvName);
        ivProfileImage = (ImageView)findViewById(R.id.ivProfileImage);
        etTweet = (EditText)findViewById(R.id.etTweet);

        tvUserName.setText(i.getStringExtra("username"));
        ivProfileImage.setImageResource(android.R.color.transparent);
        Picasso.with(this).load(i.getStringExtra("image")).into(ivProfileImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tweet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void postTweet(View view) {
        client.tweet(etTweet.getText().toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Intent data = new Intent();
                data.putExtra("code", 200); // ints work too
                // Activity finished ok, return the data
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
