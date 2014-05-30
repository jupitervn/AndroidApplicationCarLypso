package com.example.carlypso;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Preview extends SurfaceView implements SurfaceHolder.Callback { 
	private Bitmap bitmapTop;
	private final int counter;
	private final Context context;
    private final SurfaceHolder mHolder; 
    private Camera mCamera = null;
    private ImageView captureButton;
    
    @SuppressWarnings("deprecation")
	Preview(Context context,int counter,Bitmap bitmap, ImageView captureButton) { 
        super(context); 
        this.context = context;
        this.counter = counter;
        this.bitmapTop = bitmap;
        mHolder = getHolder(); 
        mHolder.addCallback(this); 
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        this.captureButton = captureButton;
        this.setMyOnClick();
    } 

    public void surfaceCreated(SurfaceHolder holder) { 
        // The Surface has been created, acquire the camera and tell it where 
        // to draw. 
    		 mCamera = Camera.open(); 
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
       mCamera.release();
       mCamera = null;
    } 
   
  /**  
    private void takePicture() {
 	   mCamera.takePicture(null, null, new PhotoHandler(context,counter,bitmapTop));
 	   //mCamera.release();
    }
    
  */  

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) { 
        // Now that the size is known, set up the camera parameters and begin 
        // the preview. 
    	android.hardware.Camera.Parameters parameters = mCamera.getParameters(); 
        parameters.setPreviewSize(w, h); 
        parameters.setPictureFormat(PixelFormat.JPEG);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    } 
    
    private void setMyOnClick(){
    	captureButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "hello" , Toast.LENGTH_SHORT).show();
				
			}
		});
    }
    
    
}
