package com.ubante.assignments.twitterclient.fragments;

import org.json.JSONArray;

import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.ubante.assignments.twitterclient.TwitterApp;
import com.ubante.assignments.twitterclient.models.Tweet;

public class UserTimelineFragment extends TweetsListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TwitterApp.getRestClient().getUserTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				getAdapter().addAll(Tweet.fromJson(jsonTweets));
			}
		});
	}
}