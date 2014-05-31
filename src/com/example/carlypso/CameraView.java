package com.example.carlypso;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
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
		try{
		counter = Integer.parseInt(intent.getStringExtra(ExtShotActivity.IMAGE_COUNTER));
		counter++;
		}
		catch(NumberFormatException e){
			counter = 1;
		}
		
		//Initialise all value
		layout = (FrameLayout)findViewById(R.id.layout);
        imageViewBack = (ImageView)findViewById(R.id.imageView1);
		imageViewNext = (ImageView)findViewById(R.id.imageView2);
		captureButton = (ImageView)findViewById(R.id.imageView3);
		retakeButton =  (ImageView)findViewById(R.id.imageView4);
        heading = (TextView)findViewById(R.id.textHeading);
        
        
		
		
		switchCaseMethod(counter);
		
        layout.addView(new Preview(this,counter,bitmapTop,captureButton));
        layout.addView(new DrawOnTop(this,bitmapTop),new LayoutParams (LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        
		imageViewBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(counter == 1 || counter == 11 || counter == 20 || counter == 25 || counter == 36){
					counter--;
					Intent intent = new Intent(getApplicationContext(),ExtShotActivity.class);
					intent.putExtra(MY_COUNTER, Integer.toString(counter));
					startActivity(intent);
				}
				counter--;								//The counter is decreased to go to the next silluate
				switchCaseMethod(counter);				//The value of bitmap image and heading-description is obtained
				layout.removeAllViews();				//All view are removed from from layout
				
				layout.addView(new Preview(context,counter,bitmapTop,captureButton));
		        layout.addView(new DrawOnTop(context,bitmapTop),new LayoutParams (LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			}
		});
		retakeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				counter = counter;						//The counter is increased to go to the next silluate
				switchCaseMethod(counter);				//The value of bitmap image and heading-description is obtained
				layout.removeAllViews();				//All view are removed from from layout
				
				layout.addView(new Preview(context,counter,bitmapTop,captureButton));
		        layout.addView(new DrawOnTop(context,bitmapTop),new LayoutParams (LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				
			}
		});
		//Assign onClick to back button
		imageViewNext.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				if(counter == 9 || counter == 18 || counter == 23 || counter == 34){
					counter++;
					Intent intent = new Intent(getApplicationContext(),ExtShotActivity.class);
					intent.putExtra(MY_COUNTER, Integer.toString(counter));
					startActivity(intent);
				}
				else if(counter == 1){ //Change to 45 later
					startActivity(new Intent(getApplicationContext(),UploadActivity.class));
				}
				else{
				counter++;								//The counter is increased to go to the next silluate
				switchCaseMethod(counter);				//The value of bitmap image and heading-description is obtained
				layout.removeAllViews();				//All view are removed from from layout
				
				layout.addView(new Preview(context,counter,bitmapTop,captureButton));
		        layout.addView(new DrawOnTop(context,bitmapTop),new LayoutParams (LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				}
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
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im1);
			break;
		case 2:
			pictureDescriptionString = getResources().getString(R.string.description1);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im2);
			break;
		case 3:
			pictureDescriptionString = getResources().getString(R.string.description2);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im3);
			break;
		case 4:
			pictureDescriptionString = getResources().getString(R.string.description2);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im4);
			break;
		case 5:
			pictureDescriptionString = getResources().getString(R.string.description2);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im5);
			break;
		case 6:
			pictureDescriptionString = getResources().getString(R.string.description2);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im6);
			break;
		case 7:
			pictureDescriptionString = getResources().getString(R.string.description3_1);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im7);
			break;
		case 8:
			pictureDescriptionString = getResources().getString(R.string.description3_1);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im8);
			break;
		case 9:
			pictureDescriptionString = getResources().getString(R.string.description3);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im9);
			break;
		
		//Exterior closeups
		case 10:
			pictureDescriptionString = getResources().getString(R.string.description4);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im10);
			break;	
			
		case 11:
			pictureDescriptionString = getResources().getString(R.string.description6);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im11);
			break;
		case 12:
			pictureDescriptionString = getResources().getString(R.string.description7);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im12);
			break;
		case 13:
			pictureDescriptionString = getResources().getString(R.string.description9);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im13);
			break;
		case 14:
			pictureDescriptionString = getResources().getString(R.string.description10);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im14);
			break;
		case 15:
			pictureDescriptionString = getResources().getString(R.string.description11);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im15);
			break;
		case 16:
			pictureDescriptionString = getResources().getString(R.string.description12);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im16);
			break;
		case 17:
			pictureDescriptionString = getResources().getString(R.string.description13);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im17);
			break;
		case 18:
			pictureDescriptionString = getResources().getString(R.string.description14);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im18);
			break;
		
		//Mechanical close ups
		
		case 19:
			pictureDescriptionString = getResources().getString(R.string.description15);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im19);
			break;
		case 20:
			pictureDescriptionString = getResources().getString(R.string.description16);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im20);
			break;
		case 21:
			pictureDescriptionString = getResources().getString(R.string.description17);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im21);
			break;
		case 22:
			pictureDescriptionString = getResources().getString(R.string.description18);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im22);
			break;
		case 23:
			pictureDescriptionString = getResources().getString(R.string.description19);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im23);
			break;
		
			
			
		//Interior close ups
		
		case 24:
			pictureDescriptionString = getResources().getString(R.string.description20);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im24);
			break;
		case 25:
			pictureDescriptionString = getResources().getString(R.string.description21);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im25);
			break;
		case 26:
			pictureDescriptionString = getResources().getString(R.string.description22);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im26);
			break;
		case 27:
			pictureDescriptionString = getResources().getString(R.string.description23);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im27);
			break;
		case 28:
			pictureDescriptionString = getResources().getString(R.string.description24);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im28);
			break;
		case 29:
			pictureDescriptionString = getResources().getString(R.string.description25);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im29);
			break;
		case 30:
			pictureDescriptionString = getResources().getString(R.string.description26);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im30);
			break;
			
		case 31:
			pictureDescriptionString = getResources().getString(R.string.description27);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im31);
			break;
		case 32:
			pictureDescriptionString = getResources().getString(R.string.description28);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im32);
			break;
		case 33:
			pictureDescriptionString = getResources().getString(R.string.description29);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im33);
			break;
		case 34:
			pictureDescriptionString = getResources().getString(R.string.description30);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im34);
			break;
		
		
		
		//Accesorries
		
		case 35:
			pictureDescriptionString = getResources().getString(R.string.description31);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im35);
			break;
		case 36:
			pictureDescriptionString = getResources().getString(R.string.description32);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im36);
			break;
		case 37:
			pictureDescriptionString = getResources().getString(R.string.description33);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im37);
			break;
		case 38:
			pictureDescriptionString = getResources().getString(R.string.description34);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im38);
			break;
		case 39:
			pictureDescriptionString = getResources().getString(R.string.description35);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im39);
			break;
		case 40:
			pictureDescriptionString = getResources().getString(R.string.description36);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im40);
			break;
			
		case 41:
			pictureDescriptionString = getResources().getString(R.string.description37);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im41);
			break;
		case 42:
			pictureDescriptionString = getResources().getString(R.string.description38);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im42);
			break;
		case 43:
			pictureDescriptionString = getResources().getString(R.string.description39);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im43);
			break;
		case 44:
			pictureDescriptionString = getResources().getString(R.string.description40);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im44);
			break;
		case 45:
			pictureDescriptionString = getResources().getString(R.string.description41);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im45);
			break;
		
		}
		heading.setText(pictureDescriptionString);
	}
	
	


}
