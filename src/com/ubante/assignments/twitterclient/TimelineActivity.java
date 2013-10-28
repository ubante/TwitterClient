package com.ubante.assignments.twitterclient;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ubante.assignments.twitterclient.fragments.HomeTimelineFragment;
import com.ubante.assignments.twitterclient.fragments.MentionsFragment;
import com.ubante.assignments.twitterclient.fragments.TweetsListFragment;

/*
 * This is the activity that follows the Login activity.
 */
public class TimelineActivity extends FragmentActivity implements TabListener {
	TweetsListFragment fragmentTweets;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setupNavigationTabs();
		refreshTimeline();	
	}
	
	private void setupNavigationTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		Tab tabHome = actionBar.newTab().setText("Home")
				.setTag("HomeTimelineFragment").setIcon(R.drawable.ic_action_home)
				.setTabListener(this);
		Tab tabMentions = actionBar.newTab().setText("Mentions")
				.setTag("MentionsFragment").setIcon(R.drawable.ic_action_at)
				.setTabListener(this);
		actionBar.addTab(tabHome);
		actionBar.addTab(tabMentions);
		actionBar.selectTab(tabHome);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.timeline, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	// This is called when user hits refresh or compose from action bar.
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_refresh:
	    		Toast.makeText(this, "Refreshing timeline", Toast.LENGTH_LONG).show();
	            refreshTimeline();
	            return true;
	        case R.id.action_compose:
	            composeTweet();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	// When the user hits the compose icon in the action bar.
	public void composeTweet() {
		Intent i = new Intent(this, ComposeActivity.class);
		startActivity(i);		
	}
	
	// When the user hits the refresh icon in the action bar.
	public void refreshTimeline() {
/*		fragmentTweets = 
				(TweetsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentTweets);
				
		TwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
				fragmentTweets.getAdapter().addAll(tweets);
			}
			
			public void onSuccess(JSONArray jsonTweets) {
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
				Log.d("DEBUG", jsonTweets.toString());
				ListView lvTweets = (ListView) findViewById(R.id.lvTweets);
				TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
				lvTweets.setAdapter(adapter);
			}
		});	
*/		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		
		if (tab.getTag() == "HomeTimelineFragment") {
			fts.replace(R.id.frame_container, new HomeTimelineFragment());
		} else {
			fts.replace(R.id.frame_container, new MentionsFragment());
		}
		fts.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}

}
