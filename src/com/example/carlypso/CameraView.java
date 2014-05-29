package com.example.carlypso;

import java.io.IOException;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class CameraView extends Activity {
	private ImageView imageViewBack, imageViewNext;
	private ImageView retakeButton, captureButton;
	private Bitmap bitmapTop,bitmapCanvas;
	private FrameLayout layout;
	private Preview mPreview;
	private DrawOnTop mDraw;
	private int counter;
	private String pictureDescriptionString = "";
	private TextView heading;
	public final static String MY_COUNTER = "my.counter";
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera); 
		context = this;
		
		Intent intent = getIntent();
		counter = Integer.parseInt(intent.getStringExtra(ExtShotActivity.IMAGE_COUNTER));
		
		//switchCaseMethod(counter);
		//Toast.makeText(getApplicationContext(), MyString.VIN_NUMBER, Toast.LENGTH_SHORT).show();
		bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);

        layout = (FrameLayout)findViewById(R.id.layout);
        heading = (TextView)findViewById(R.id.textHeading);
        heading.setText(pictureDescriptionString);
        
        
		mPreview = new Preview(this,counter,bitmapTop);
		mDraw = new DrawOnTop(this,bitmapTop);
		
        layout.addView(mPreview);
        layout.addView(mDraw,new LayoutParams (LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        
		
		imageViewBack = (ImageView)findViewById(R.id.imageView1);
		imageViewNext = (ImageView)findViewById(R.id.imageView2);
		captureButton = (ImageView)findViewById(R.id.imageView3);
		retakeButton =  (ImageView)findViewById(R.id.imageView4);
		
		
		imageViewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),ExtShotActivity.class));
			}
		});
		//Assign onClick to back button
		imageViewNext.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//startActivity(new Intent(getApplicationContext(),ExtShotActivity.class));
				//mPreview = new Preview(context,counter,bitmapTop);
				mDraw = new DrawOnTop(context,bitmapTop);
				//layout.removeAllViewsInLayout();
				layout.addView(mPreview);
		        layout.addView(mDraw,new LayoutParams (LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				/**
				if(counter == 9 || counter == 18 || counter == 23 || counter == 34){
					Intent intent = new Intent(getApplicationContext(),ExtShotActivity.class);
					intent.putExtra(MY_COUNTER, Integer.toString(counter+1));
					startActivity(intent);
				}
				else{
					counter++;
					switchCaseMethod(counter);
					heading.setText(pictureDescriptionString);
					mPreview = new Preview(Camera.this);
					mDraw = new DrawOnTop(Camera.this,bitmapTop);
			        layout.addView(mPreview);
			        layout.addView(mDraw,new LayoutParams (LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				}*/
			}
		});
		captureButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mPreview.takePicture();
				
			}
		});
		retakeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_camera, menu);
		return true;
	}

	
	
	private void switchCaseMethod(int counter){
		switch(counter){
		//Exterior body shot
		case 1:
			pictureDescriptionString = getResources().getString(R.string.description1);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 2:
			pictureDescriptionString = getResources().getString(R.string.description1);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 3:
			pictureDescriptionString = getResources().getString(R.string.description2);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 4:
			pictureDescriptionString = getResources().getString(R.string.description2);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 5:
			pictureDescriptionString = getResources().getString(R.string.description2);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 6:
			pictureDescriptionString = getResources().getString(R.string.description2);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 7:
			pictureDescriptionString = getResources().getString(R.string.description3_1);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 8:
			pictureDescriptionString = getResources().getString(R.string.description3_1);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 9:
			pictureDescriptionString = getResources().getString(R.string.description3);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		
		//Exterior closeups
		case 10:
			pictureDescriptionString = getResources().getString(R.string.description4);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;	
			
		case 11:
			pictureDescriptionString = getResources().getString(R.string.description6);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 12:
			pictureDescriptionString = getResources().getString(R.string.description7);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 13:
			pictureDescriptionString = getResources().getString(R.string.description9);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 14:
			pictureDescriptionString = getResources().getString(R.string.description10);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 15:
			pictureDescriptionString = getResources().getString(R.string.description11);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 16:
			pictureDescriptionString = getResources().getString(R.string.description12);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 17:
			pictureDescriptionString = getResources().getString(R.string.description13);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 18:
			pictureDescriptionString = getResources().getString(R.string.description14);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		
		//Mechanical close ups
		case 19:
			pictureDescriptionString = getResources().getString(R.string.description15);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 20:
			pictureDescriptionString = getResources().getString(R.string.description16);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 21:
			pictureDescriptionString = getResources().getString(R.string.description17);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 22:
			pictureDescriptionString = getResources().getString(R.string.description18);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 23:
			pictureDescriptionString = getResources().getString(R.string.description19);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		
			
			
		//Interior close ups
		case 24:
			pictureDescriptionString = getResources().getString(R.string.description20);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 25:
			pictureDescriptionString = getResources().getString(R.string.description21);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 26:
			pictureDescriptionString = getResources().getString(R.string.description22);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 27:
			pictureDescriptionString = getResources().getString(R.string.description23);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 28:
			pictureDescriptionString = getResources().getString(R.string.description24);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 29:
			pictureDescriptionString = getResources().getString(R.string.description25);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 30:
			pictureDescriptionString = getResources().getString(R.string.description26);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
			
		case 31:
			pictureDescriptionString = getResources().getString(R.string.description27);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 32:
			pictureDescriptionString = getResources().getString(R.string.description28);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 33:
			pictureDescriptionString = getResources().getString(R.string.description29);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 34:
			pictureDescriptionString = getResources().getString(R.string.description30);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		
		
		
		//Accesorries
		case 35:
			pictureDescriptionString = getResources().getString(R.string.description31);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 36:
			pictureDescriptionString = getResources().getString(R.string.description32);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 37:
			pictureDescriptionString = getResources().getString(R.string.description33);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 38:
			pictureDescriptionString = getResources().getString(R.string.description34);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 39:
			pictureDescriptionString = getResources().getString(R.string.description35);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 40:
			pictureDescriptionString = getResources().getString(R.string.description36);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
			
		case 41:
			pictureDescriptionString = getResources().getString(R.string.description37);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 42:
			pictureDescriptionString = getResources().getString(R.string.description38);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 43:
			pictureDescriptionString = getResources().getString(R.string.description39);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 44:
			pictureDescriptionString = getResources().getString(R.string.description40);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 45:
			pictureDescriptionString = getResources().getString(R.string.description41);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		
		}
	}
	
	


}
