package com.example.carlypso;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Preview extends SurfaceView implements SurfaceHolder.Callback { 
	private Bitmap bitmapTop;
	private final int counter;
	private final Context context;
    private final SurfaceHolder mHolder; 
    private Camera mCamera = null;
    
    @SuppressWarnings("deprecation")
	Preview(Context context,int counter,Bitmap bitmap) { 
        super(context); 
        this.context = context;
        this.counter = counter;
        this.bitmapTop = bitmap;
        mHolder = getHolder(); 
        mHolder.addCallback(this); 
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 
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
    public void takePicture() {
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
    
}
