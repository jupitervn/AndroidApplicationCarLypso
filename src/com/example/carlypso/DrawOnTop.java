package com.example.carlypso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.view.View;

import com.example.carlyso.bitmap.BitmapUtils;

public class DrawOnTop extends View {
    private Bitmap bitmapTop;
    private Camera.Size previewSize;

    public DrawOnTop(Context context, Bitmap bitmapTop, Camera.Size previewSize) {
        super(context);
        this.setBitmapTop(bitmapTop);
        this.previewSize = previewSize;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getBitmapTop() != null) {
            int viewWidth = getMeasuredWidth();
            int viewHeight = getMeasuredHeight();

            if (viewHeight > 0 && viewWidth > 0) {
                float scaleWidth = viewWidth / (float) previewSize.width;
                float scaleHeight = viewHeight / (float) previewSize.height;
                Matrix matrix = BitmapUtils.getOptimalMatrix(bitmapTop.getWidth(), bitmapTop.getHeight(),
                        previewSize.width, previewSize.height, true);
                matrix.postScale(scaleWidth, scaleHeight);
                canvas.drawBitmap(getBitmapTop(), matrix, null);
                canvas.save();
            }

        }
        super.onDraw(canvas);
    }

    public Bitmap getBitmapTop() {
        return bitmapTop;
    }

    public void setBitmapTop(Bitmap bitmapTop) {
        this.bitmapTop = bitmapTop;
    }

}
