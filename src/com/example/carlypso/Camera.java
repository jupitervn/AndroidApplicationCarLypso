package com.example.carlypso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Camera extends Activity {
	private ImageView imageViewBack, imageViewNext;
	private ImageView retakeButton, captureButton;
	private Bitmap bitmapTop,bitmapCanvas;
	private FrameLayout layout;
	private Preview mPreview;
	private DrawOnTop mDraw;
	private int counter;
	private String pictureDescriptionString = "";
	private TextView heading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera); 
		Intent intent = getIntent();
		counter = Integer.parseInt(intent.getStringExtra(ExtShotActivity.IMAGE_COUNTER));
		
		switch(counter){
		case 1:
			pictureDescriptionString = getResources().getString(R.string.description1);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 2:
			pictureDescriptionString = getResources().getString(R.string.description2);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 3:
			pictureDescriptionString = getResources().getString(R.string.description3);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 4:
			pictureDescriptionString = getResources().getString(R.string.description4);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 5:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 6:
			pictureDescriptionString = getResources().getString(R.string.description6);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 7:
			pictureDescriptionString = getResources().getString(R.string.description7);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 8:
			pictureDescriptionString = getResources().getString(R.string.description8);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 9:
			pictureDescriptionString = getResources().getString(R.string.description9);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 10:
			pictureDescriptionString = getResources().getString(R.string.description10);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
			
			
			
		case 11:
			pictureDescriptionString = getResources().getString(R.string.description1);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 12:
			pictureDescriptionString = getResources().getString(R.string.description2);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 13:
			pictureDescriptionString = getResources().getString(R.string.description3);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 14:
			pictureDescriptionString = getResources().getString(R.string.description4);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 15:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 16:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 17:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 18:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 19:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 20:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
			
			
		case 21:
			pictureDescriptionString = getResources().getString(R.string.description1);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 22:
			pictureDescriptionString = getResources().getString(R.string.description2);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 23:
			pictureDescriptionString = getResources().getString(R.string.description3);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 24:
			pictureDescriptionString = getResources().getString(R.string.description4);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 25:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 26:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 27:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 28:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 29:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 30:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
			
		case 31:
			pictureDescriptionString = getResources().getString(R.string.description1);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 32:
			pictureDescriptionString = getResources().getString(R.string.description2);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 33:
			pictureDescriptionString = getResources().getString(R.string.description3);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 34:
			pictureDescriptionString = getResources().getString(R.string.description4);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 35:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 36:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 37:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 38:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 39:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 40:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
			
		case 41:
			pictureDescriptionString = getResources().getString(R.string.description1);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 42:
			pictureDescriptionString = getResources().getString(R.string.description2);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 43:
			pictureDescriptionString = getResources().getString(R.string.description3);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 44:
			pictureDescriptionString = getResources().getString(R.string.description4);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		case 45:
			pictureDescriptionString = getResources().getString(R.string.description5);
			bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.car1);
			break;
		
		}

        layout = (FrameLayout)findViewById(R.id.layout);
        heading = (TextView)findViewById(R.id.textHeading);
        heading.setText(pictureDescriptionString);
        
        
		mPreview = new Preview(this);
		mDraw = new DrawOnTop(this);
		
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
	
	public class DrawOnTop extends View { 
        public DrawOnTop(Context context) { 
            super(context); 

    } 

    @Override 
    protected void onDraw(Canvas canvas) { 
    	 if (bitmapTop != null) {
    	   
  		   //canvas.drawBitmap(bitmapTop, 0, 0, null);
    		 bitmapTop = getResizedBitmap(bitmapTop, canvas.getHeight(), canvas.getWidth());
    		 canvas.drawBitmap(bitmapTop, 0, 0, null);
    		 
  	   }
         super.onDraw(canvas);  
    	} 
	} 
	
	public class PhotoHandler implements PictureCallback {

	    private final Context context;

	    public PhotoHandler(Context context) {
	        this.context = context;
	    }

	    @Override
	    public void onPictureTaken(byte[] data, android.hardware.Camera camera) {

	        File sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

	        File pictureFileDir =  new File(sdDir, "/CameraAPIDemo");

	        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
	            Toast.makeText(context, "Can't create directory to save image.",Toast.LENGTH_LONG).show();
	            return;

	        }

	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
	        String date = dateFormat.format(new Date());
	        String photoFile = "Picture_" + date + ".jpg";

	        String filename = pictureFileDir.getPath() + File.separator + photoFile;

	        File pictureFile = new File(filename);

	        try {
	            FileOutputStream fos = new FileOutputStream(pictureFile);
	            fos.write(data);
	            fos.close();
	            Toast.makeText(context, "New Image saved:" + photoFile,Toast.LENGTH_LONG).show();
	            
	            // TODO: Merge the photo
	            try {
	                Bitmap bottomImage = BitmapFactory.decodeFile(pictureFile.getAbsolutePath()); //blue
	                System.gc();
	                bitmapCanvas = Bitmap.createBitmap(bottomImage.getWidth(), bottomImage.getHeight(), Bitmap.Config.ARGB_8888);
	                Canvas c = new Canvas(bitmapCanvas);
	                Resources res = getResources();

	                //Bitmap topImage = BitmapFactory.decodeResource(getResources(), R.drawable.logo); //green
	                
	                Bitmap topImage = bitmapTop;
	                Drawable drawable1 = new BitmapDrawable(bottomImage);
	                Drawable drawable2 = new BitmapDrawable(topImage);


	                drawable1.setBounds(0, 0, bottomImage.getWidth(), bottomImage.getHeight());
	                drawable2.setBounds(0, 0, bottomImage.getWidth(), bottomImage.getHeight());
	                
	                drawable1.draw(c);
	                drawable2.draw(c);


	            } catch (Exception e) {
	            }
	            // To write the file out to the SDCard:
	            OutputStream os = null;
	            try {
	                os = new FileOutputStream(filename);
	                bitmapCanvas.compress(Bitmap.CompressFormat.PNG, 50, os);
	            } catch(IOException e) {
	                e.printStackTrace();
	            }
	        } catch (Exception error) {
	            //Log.d(LOG_TAG, "File" + filename + "not saved: " + error.getMessage());
	            Toast.makeText(context, "Image could not be saved.",
	                    Toast.LENGTH_LONG).show();
	        }
	    }

	}
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
	    int width = bm.getWidth();
	    int height = bm.getHeight();
	    float scaleWidth = ((float) newWidth) / width;
	    float scaleHeight = ((float) newHeight) / height;
	    // CREATE A MATRIX FOR THE MANIPULATION
	    Matrix matrix = new Matrix();
	    // RESIZE THE BIT MAP
	    matrix.postScale(scaleWidth, scaleHeight);

	    // "RECREATE" THE NEW BITMAP
	    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
	    return resizedBitmap;
	}
	
	public class Preview extends SurfaceView implements SurfaceHolder.Callback { 
        SurfaceHolder mHolder; 
        android.hardware.Camera mCamera;
        @SuppressWarnings("deprecation")
		Preview(Context context) { 
            super(context); 
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
        public void takePicture() {
     	   mCamera.takePicture(null, null, new PhotoHandler(getApplicationContext()));
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) { 
            // Now that the size is known, set up the camera parameters and begin 
            // the preview. 
        	android.hardware.Camera.Parameters parameters = mCamera.getParameters(); 
            parameters.setPreviewSize(w, h); 
            parameters.setPictureFormat(PixelFormat.JPEG);
            mCamera.startPreview();
            //mCamera.setParameters(parameters); 
             
       } 
    }
	
	


}
