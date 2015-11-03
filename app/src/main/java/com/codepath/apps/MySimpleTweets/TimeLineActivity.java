package com.codepath.apps.MySimpleTweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.MySimpleTweets.fragments.HomeTimelineFragment;
import com.codepath.apps.MySimpleTweets.fragments.MentionsTimelineFragment;

public class TimeLineActivity extends AppCompatActivity {

    private static int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        ViewPager vpPager = (ViewPager)findViewById(R.id.viewpager);
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabStrip  = (PagerSlidingTabStrip)findViewById(R.id.tabs);
        tabStrip.setViewPager(vpPager);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time_line, menu);
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
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_tweet) {
            Intent i = new Intent(TimeLineActivity.this, TweetActivity.class);
            i.putExtra("screenname", HomeTimelineFragment.username);
            i.putExtra("name", HomeTimelineFragment.name);
            i.putExtra("image", HomeTimelineFragment.imageUrl);
            startActivityForResult(i, REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ActivityOne.java, time to handle the result of the sub-activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
//            tweetListFragment.populateTimeLine();
        }
    }


    public class TweetsPagerAdapter extends FragmentPagerAdapter{
        public String tabTitlesp[] = {"Home", "Mentions"};

        public TweetsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            if(position==0) {
                return new HomeTimelineFragment();
            } else if (position == 1) {
                return new MentionsTimelineFragment();
            } else {
                return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitlesp[position];
        }

        @Override
        public int getCount() {
            return tabTitlesp.length;
        }
    }

    public void onProfileView(MenuItem menu) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }
}
