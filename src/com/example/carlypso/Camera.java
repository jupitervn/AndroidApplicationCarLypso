package com.example.carlypso;

import java.io.IOException;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Camera extends Activity {
	private ImageView imageViewBack, imageViewNext;
	private ImageView retakeButton, captureButton;
	private Bitmap bitmap;
	private FrameLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getIntent();
		Preview mPreview = new Preview(this); 
        DrawOnTop mDraw = new DrawOnTop(this); 
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        
        layout = (FrameLayout)findViewById(R.id.layout);
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
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_camera, menu);
		return true;
	}
	
	public class DrawOnTop extends View { 
        public DrawOnTop(Context context) { 
            super(context); 

    } 

    @Override 
    protected void onDraw(Canvas canvas) { 
    	 if (bitmap != null) {
  		   canvas.drawBitmap(bitmap, 0, 0, null);
  	   }
         super.onDraw(canvas);  
    	} 
	} 
	
	public class Preview extends SurfaceView implements SurfaceHolder.Callback { 
        SurfaceHolder mHolder; 
        android.hardware.Camera mCamera;

        @SuppressWarnings("deprecation")
		Preview(Context context) { 
            super(context); 
            // Install a SurfaceHolder.Callback so we get notified when the 
            // underlying surface is created and destroyed. 
            mHolder = getHolder(); 
            mHolder.addCallback(this); 
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 
        } 

        public void surfaceCreated(SurfaceHolder holder) { 
            // The Surface has been created, acquire the camera and tell it where 
            // to draw. 
            mCamera = android.hardware.Camera.open(); 
             try {
        mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {

            e.printStackTrace();
        } 
        } 

        public void surfaceDestroyed(SurfaceHolder holder) { 
           // Surface will be destroyed when we return, so stop the preview. 
           // Because the CameraDevice object is not a shared resource, it's very 
           // important to release it when the activity is paused. 
           mCamera.stopPreview(); 
           mCamera = null; 
        } 

        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) { 
            // Now that the size is known, set up the camera parameters and begin 
            // the preview. 
        	android.hardware.Camera.Parameters parameters = mCamera.getParameters(); 
            parameters.setPreviewSize(w, h); 
            mCamera.setParameters(parameters); 
            mCamera.startPreview(); 
       } 
    }


}
