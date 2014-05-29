package com.example.carlypso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;

public class DrawOnTop extends View { 
	private Context context;
	private Bitmap bitmapTop;
	
    public DrawOnTop(Context context,Bitmap bitmapTop) { 
        super(context);
        this.bitmapTop = bitmapTop;
        
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
    
    private Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
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
} 
