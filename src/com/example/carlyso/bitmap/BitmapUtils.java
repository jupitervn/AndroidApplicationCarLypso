/**
*/
package com.example.carlyso.bitmap;

import android.graphics.Matrix;

/**
 * Bitmap Utils.
 */
public class BitmapUtils {
    /**
     * Create a matrix to transform bitmap with width/height to targetWidth/targetHeight
     * 
     * @param width
     * @param height
     * @param targetWidth
     * @param targetHeight
     * @param shouldKeepRatio
     *            should keep the original aspect ratio or not.
     * @return
     */
    public static Matrix getOptimalMatrix(int width,
            int height,
            int targetWidth,
            int targetHeight,
            boolean shouldKeepRatio) {
        Matrix matrix = new Matrix();
        int newHeight = targetHeight;
        int newWidth = targetWidth;
        if (shouldKeepRatio) {
            float aspectRatio = width / (float) height;
            newHeight = (int) (targetWidth / aspectRatio);
            if (newHeight > targetHeight) {
                newWidth = (int) (targetHeight * aspectRatio);
                newHeight = targetHeight;
            }
        }
        int left = (targetWidth - newWidth) / 2;
        int top = (targetHeight - newHeight) / 2;
        float scaleWidth = newWidth / (float) width;
        float scaleHeight = newHeight / (float) height;
        matrix.preScale(scaleWidth, scaleHeight);
        matrix.postTranslate(left, top);
        return matrix;
    }

}
