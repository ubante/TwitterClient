package com.ubante.assignments.twitterclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class ComposeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}

	public void onCancelTweet(View v) {
		Toast.makeText(this, "Cancelling tweet", Toast.LENGTH_LONG).show();
		Intent i = new Intent(this, TimelineActivity.class);
		startActivity(i);	
	}
	
	public void onSubmitTweet(View v) {
		Toast.makeText(this, "Submitting tweet", Toast.LENGTH_LONG).show();

		
	}
}
