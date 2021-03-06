package com.example.carlypso;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class DescriptionActivity extends Activity {
	private ImageView imageViewBack, imageViewNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description);
		getIntent();
		imageViewBack = (ImageView)findViewById(R.id.imageView1);
		imageViewNext = (ImageView)findViewById(R.id.imageView2);
		
		imageViewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),EnterVinActivity.class));
			}
		});
		//Assign onClick to back button
		imageViewNext.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),ExtShotActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_description, menu);
		return true;
	}

}
