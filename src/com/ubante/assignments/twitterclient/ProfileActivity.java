package com.ubante.assignments.twitterclient;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ubante.assignments.twitterclient.models.User;

public class ProfileActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		TwitterApp.getRestClient().getMyInfo(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject myInfo) {
				User me = User.fromJson(myInfo);
				getActionBar().setTitle("@" + me.getScreenName());
				populateProfileHeader(me);
			}
		});	
		
	}

	protected void populateProfileHeader(User user) {
		TextView tvProfileName = (TextView) findViewById(R.id.tvProfileName);
		TextView tvProfileTagline = (TextView) findViewById(R.id.tvProfileTagline);
		TextView tvFollowers = (TextView) findViewById(R.id.tvFollowersCount);
		TextView tvFollowing = (TextView) findViewById(R.id.tvFollowingCount);
		ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
		tvProfileName.setText(user.getName());
		tvProfileTagline.setText(user.getTagline());
		tvFollowers.setText(user.getFollowersCount() + " Followers");
		tvFollowing.setText(user.getFriendsCount() + " Following");
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
