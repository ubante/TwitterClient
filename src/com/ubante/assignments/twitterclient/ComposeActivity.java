package com.ubante.assignments.twitterclient;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends Activity {
	EditText etTweet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		etTweet = (EditText) findViewById(R.id.etTweet);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}

	public void returnToTimeline() {
		Intent i = new Intent(this, TimelineActivity.class);
		startActivity(i);	
	}
	
	public void onCancelTweet(View v) {
		Toast.makeText(this, "Cancelling tweet", Toast.LENGTH_LONG).show();
		returnToTimeline();
	}
	
	public void onSubmitTweet2(View v) {
		Toast.makeText(this, etTweet.getText().toString(), Toast.LENGTH_LONG).show();
	}
	
	public void onSubmitTweet(View v) {
		Toast.makeText(this, "Tweeting", Toast.LENGTH_LONG).show();
		TwitterApp.getRestClient().postStatusUpdate(etTweet.getText().toString(), 
				new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				//returnToTimeline();
				//ComposeActivity.this.finish();
				setResult(RESULT_OK);
				ComposeActivity.this.finish();
				// can't finish ?
				//finish();
			}
			
			@Override
			public void onFailure(Throwable t, JSONObject response) {
				Log.e("ERROR", "Failed to post tweet", t);
				Toast.makeText(ComposeActivity.this, "Failed to tweet", Toast.LENGTH_LONG).show();
			}
		});
		
	}
}
