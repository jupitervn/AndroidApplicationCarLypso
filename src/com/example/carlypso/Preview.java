package com.example.carlypso;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
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
       mCamera.stopPreview(); 
       mCamera.release();
       mCamera = null;
    } 
   
  
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) { 
        // Now that the size is known, set up the camera parameters and begin 
        // the preview. 
    	/**
    	android.hardware.Camera.Parameters parameters = mCamera.getParameters(); 
        parameters.setPreviewSize(w, h); 
        parameters.setPictureFormat(PixelFormat.JPEG);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
        */
    	Camera.Parameters parameters = mCamera.getParameters();
        List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();

        // You need to choose the most appropriate previewSize for your app
        Camera.Size previewSize = previewSizes.get(1);// .... select one of previewSizes here
        
        parameters.setPreviewSize(previewSize.width, previewSize.height);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    } 
    
    private void setMyOnClick(){
    	captureButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(context, "hello" , Toast.LENGTH_SHORT).show();
				new MyTakePictureAsnc().execute();
				
			}
		});
    }
    private class MyTakePictureAsnc extends AsyncTask<String, String, String>{
    	

		@Override
		protected String doInBackground(String... params) {
			mCamera.takePicture(null, null, new PhotoHandler(context,counter,bitmapTop));
			// TODO Auto-generated method stub
			return null;
		}
    	
    }
    
    
}
