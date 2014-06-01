package com.example.carlypso;

import java.io.IOException;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

public class Preview extends SurfaceView implements SurfaceHolder.Callback {
	private Bitmap bitmapTop;
	private final int counter;
	private final Context context;
    private final SurfaceHolder mHolder; 
    private Camera mCamera = null;
    private ImageView captureButton;
    private ProgressDialog progressDialog;
    
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
    } 

    @Override
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

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) { 
       mCamera.stopPreview(); 
       mCamera.release();
       mCamera = null;
    } 
   
  
    @Override
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
        for (Camera.Size previewSize: previewSizes) {
            Log.d("D.Vu", "Preview size " + previewSize.width + " " + previewSize.height);
          
        }
        List<Camera.Size> pictureSizes = parameters.getSupportedPictureSizes();
        for (Camera.Size pictureSize : pictureSizes) {
            Log.d("D.Vu", "Picture Size " + pictureSize.width + " " + pictureSize.height);
        }
        Camera.Size bestPictureSize = getOptimalSizes(pictureSizes);
        if (bestPictureSize != null) {
            parameters.setPictureSize(bestPictureSize.width, bestPictureSize.height);
        }
        Camera.Size bestPreviewSize = getOptimalSizes(parameters.getSupportedPreviewSizes());
        if (bestPreviewSize != null) {
            Log.d("D.Vu", "Best preview size " + bestPreviewSize.width + " " + bestPreviewSize.height);
            parameters.setPreviewSize(bestPreviewSize.width, bestPreviewSize.height);
        }
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    } 
    
    private Camera.Size getOptimalSizes(List<Camera.Size> sizes) {
        Camera.Size returnSize = null;
        for (Camera.Size size : sizes) {
            if (size.width == Constants.IMAGE_WIDTH && size.height == Constants.IMAGE_HEIGHT) {
                returnSize = size;
                break;
            }
        }

        if (returnSize == null) {
            float oriRatio = Constants.IMAGE_WIDTH / (float) Constants.IMAGE_HEIGHT;
            for (Camera.Size size : sizes) {
                float sizeRatio = size.width / (float) size.height;
                if (sizeRatio == oriRatio) {
                    returnSize = size;
                    break;
                }
            }
        }
        return returnSize;
    }





}
