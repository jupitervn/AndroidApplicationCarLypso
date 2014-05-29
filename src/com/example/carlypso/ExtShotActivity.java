package com.example.carlypso;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ExtShotActivity extends Activity {
	private ImageView imageViewBack, imageViewNext;
	public final static String IMAGE_COUNTER = "image.counter";
	private String topHeading ;
	private int counter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ext_shot);
		Intent intent = getIntent();
		try{
			counter = Integer.parseInt(intent.getStringExtra(CameraView.MY_COUNTER).toString());
		}
		catch(NullPointerException e){
			counter = 0;
		}
		
		imageViewBack = (ImageView)findViewById(R.id.imageView1);
		imageViewNext = (ImageView)findViewById(R.id.imageView2);
		
		switch (counter) {
		case 0:
			topHeading = getResources().getString(R.string.string6);
			break;
		case 10:
			topHeading = getResources().getString(R.string.string7);
			break;
		case 19:
			topHeading = getResources().getString(R.string.string8);
			break;
		case 24:
			topHeading = getResources().getString(R.string.string9);
			break;
		case 35:
			topHeading = getResources().getString(R.string.string10);
			break;
		default:
			break;
		}
		imageViewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),DescriptionActivity.class));
			}
		});
		//Assign onClick to back button
		imageViewNext.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),CameraView.class);
				intent.putExtra(IMAGE_COUNTER, Integer.toString(counter));
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_ext_shot, menu);
		return true;
	}

}
