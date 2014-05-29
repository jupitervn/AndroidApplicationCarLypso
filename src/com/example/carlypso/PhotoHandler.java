package com.example.carlypso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;

public class PhotoHandler implements PictureCallback {
	private final Context context;
	private final int counter;
	private final Bitmap bitmap;
	private Bitmap bitmapCanvas;
	private byte[] data;

    public PhotoHandler(Context context,int counter,Bitmap bitmap) {
        this.context = context;
        this.counter = counter;
        this.bitmap = bitmap;
    }

    @Override
    public void onPictureTaken(byte[] data,Camera camera) {
        File sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

        File pictureFileDir =  new File(sdDir, "/CameraAPIDemo");

        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
            Toast.makeText(context, "Can't create directory to save image.",Toast.LENGTH_LONG).show();
            return;

        }

        String photoFile = MyString.VIN_NUMBER + Integer.toString(counter) + ".jpg";

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
                //Resources res = context.getResources();

                //Bitmap topImage = BitmapFactory.decodeResource(getResources(), R.drawable.logo); //green
                
                Bitmap topImage = bitmap;
                @SuppressWarnings("deprecation")
				Drawable drawable1 = new BitmapDrawable(bottomImage);
                @SuppressWarnings("deprecation")
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
