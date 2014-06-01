package com.example.carlypso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlyso.bitmap.BitmapUtils;

public class CameraView extends Activity implements Callback, OnClickListener, PictureCallback {
    private ImageView imageViewBack, imageViewNext;
    private ImageView retakeButton, captureButton;
    private Bitmap bitmapTop;
    private FrameLayout layout;
    private int counter;
    private String pictureDescriptionString = "";
    private TextView heading;
    public final static String MY_COUNTER = "my.counter";
    private Context context;
    private SurfaceView surfaceView;
    private SurfaceHolder mHolder;
    private ProgressDialog progressDialog;
    private Camera mCamera;
    private DrawOnTop mDrawOnTopView;
    private boolean isPreviewing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        context = this;
        Intent intent = getIntent();
        try {
            counter = Integer.parseInt(intent.getStringExtra(ExtShotActivity.IMAGE_COUNTER));
            counter++;
        } catch (NumberFormatException e) {
            counter = 1;
        }

        //Initialise all value
        layout = (FrameLayout) findViewById(R.id.layout);
        imageViewBack = (ImageView) findViewById(R.id.imageView1);
        imageViewNext = (ImageView) findViewById(R.id.imageView2);
        captureButton = (ImageView) findViewById(R.id.imageView3);
        retakeButton = (ImageView) findViewById(R.id.imageView4);
        heading = (TextView) findViewById(R.id.textHeading);

        surfaceView = (SurfaceView) findViewById(R.id.sf_camera_view);
        captureButton.setOnClickListener(this);
        imageViewBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (counter == 1 || counter == 11 || counter == 20 || counter == 25 || counter == 36) {
                    counter--;
                    Intent intent = new Intent(getApplicationContext(), ExtShotActivity.class);
                    intent.putExtra(MY_COUNTER, Integer.toString(counter));
                    startActivity(intent);
                }
                counter--; //The counter is decreased to go to the next silluate
                switchCaseMethod(counter); //The value of bitmap image and heading-description is obtained
                startCameraPreview();
            }
        });
        retakeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                counter = counter; //The counter is increased to go to the next silluate
                switchCaseMethod(counter); //The value of bitmap image and heading-description is obtained
                startCameraPreview();
            }
        });
        //Assign onClick to back button
        imageViewNext.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (counter == 9 || counter == 18 || counter == 23 || counter == 34) {
                    counter++;
                    Intent intent = new Intent(getApplicationContext(), ExtShotActivity.class);
                    intent.putExtra(MY_COUNTER, Integer.toString(counter));
                    startActivity(intent);
                }
                else if (counter == 45) { //Change to 45 later
                    startActivity(new Intent(getApplicationContext(), UploadActivity.class));
                }
                else {
                    counter++; //The counter is increased to go to the next silluate
                    switchCaseMethod(counter); //The value of bitmap image and heading-description is obtained
                    startCameraPreview();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHolder = surfaceView.getHolder();
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mHolder.addCallback(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHolder.removeCallback(this);
        stopAndReleaseCamera();
    }

    private void stopAndReleaseCamera() {
        isPreviewing = false;
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_camera, menu);
        return true;
    }

    private void switchCaseMethod(int counter) {

        switch (counter) {
        //Exterior body shot
        case 1:
            pictureDescriptionString = getResources().getString(R.string.description1);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im1);
            break;
        case 2:
            pictureDescriptionString = getResources().getString(R.string.description1);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im2);
            break;
        case 3:
            pictureDescriptionString = getResources().getString(R.string.description2);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im3);
            break;
        case 4:
            pictureDescriptionString = getResources().getString(R.string.description2);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im4);
            break;
        case 5:
            pictureDescriptionString = getResources().getString(R.string.description2);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im5);
            break;
        case 6:
            pictureDescriptionString = getResources().getString(R.string.description2);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im6);
            break;
        case 7:
            pictureDescriptionString = getResources().getString(R.string.description3_1);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im7);
            break;
        case 8:
            pictureDescriptionString = getResources().getString(R.string.description3_1);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im8);
            break;
        case 9:
            pictureDescriptionString = getResources().getString(R.string.description3);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im9);
            break;

        //Exterior closeups
        case 10:
            pictureDescriptionString = getResources().getString(R.string.description4);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im10);
            break;

        case 11:
            pictureDescriptionString = getResources().getString(R.string.description6);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im11);
            break;
        case 12:
            pictureDescriptionString = getResources().getString(R.string.description7);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im12);
            break;
        case 13:
            pictureDescriptionString = getResources().getString(R.string.description9);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im13);
            break;
        case 14:
            pictureDescriptionString = getResources().getString(R.string.description10);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im14);
            break;
        case 15:
            pictureDescriptionString = getResources().getString(R.string.description11);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im15);
            break;
        case 16:
            pictureDescriptionString = getResources().getString(R.string.description12);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im16);
            break;
        case 17:
            pictureDescriptionString = getResources().getString(R.string.description13);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im17);
            break;
        case 18:
            pictureDescriptionString = getResources().getString(R.string.description14);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im18);
            break;

        //Mechanical close ups

        case 19:
            pictureDescriptionString = getResources().getString(R.string.description15);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im19);
            break;
        case 20:
            pictureDescriptionString = getResources().getString(R.string.description16);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im20);
            break;
        case 21:
            pictureDescriptionString = getResources().getString(R.string.description17);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im21);
            break;
        case 22:
            pictureDescriptionString = getResources().getString(R.string.description18);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im22);
            break;
        case 23:
            pictureDescriptionString = getResources().getString(R.string.description19);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im23);
            break;

        //Interior close ups

        case 24:
            pictureDescriptionString = getResources().getString(R.string.description20);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im24);
            break;
        case 25:
            pictureDescriptionString = getResources().getString(R.string.description21);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im25);
            break;
        case 26:
            pictureDescriptionString = getResources().getString(R.string.description22);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im26);
            break;
        case 27:
            pictureDescriptionString = getResources().getString(R.string.description23);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im27);
            break;
        case 28:
            pictureDescriptionString = getResources().getString(R.string.description24);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im28);
            break;
        case 29:
            pictureDescriptionString = getResources().getString(R.string.description25);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im29);
            break;
        case 30:
            pictureDescriptionString = getResources().getString(R.string.description26);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im30);
            break;

        case 31:
            pictureDescriptionString = getResources().getString(R.string.description27);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im31);
            break;
        case 32:
            pictureDescriptionString = getResources().getString(R.string.description28);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im32);
            break;
        case 33:
            pictureDescriptionString = getResources().getString(R.string.description29);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im33);
            break;
        case 34:
            pictureDescriptionString = getResources().getString(R.string.description30);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im34);
            break;

        //Accesorries

        case 35:
            pictureDescriptionString = getResources().getString(R.string.description31);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im35);
            break;
        case 36:
            pictureDescriptionString = getResources().getString(R.string.description32);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im36);
            break;
        case 37:
            pictureDescriptionString = getResources().getString(R.string.description33);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im37);
            break;
        case 38:
            pictureDescriptionString = getResources().getString(R.string.description34);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im38);
            break;
        case 39:
            pictureDescriptionString = getResources().getString(R.string.description35);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im39);
            break;
        case 40:
            pictureDescriptionString = getResources().getString(R.string.description36);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im40);
            break;

        case 41:
            pictureDescriptionString = getResources().getString(R.string.description37);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im41);
            break;
        case 42:
            pictureDescriptionString = getResources().getString(R.string.description38);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im42);
            break;
        case 43:
            pictureDescriptionString = getResources().getString(R.string.description39);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im43);
            break;
        case 44:
            pictureDescriptionString = getResources().getString(R.string.description40);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im44);
            break;
        case 45:
            pictureDescriptionString = getResources().getString(R.string.description41);
            bitmapTop = BitmapFactory.decodeResource(getResources(), R.drawable.im45);
            break;

        }
        heading.setText(pictureDescriptionString);
        mDrawOnTopView.setBitmapTop(bitmapTop);
        mDrawOnTopView.invalidate();
    }

    private void showLoadingDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(getString(R.string.saving_text));
        }
        progressDialog.show();
    }

    private void hideLoadingDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            if (mCamera == null) {
                mCamera = Camera.open();
            }
            mHolder = holder;
            mCamera.setPreviewDisplay(holder);
            setupCamera();

        } catch (IOException e) {
            e.printStackTrace();
            mCamera.release();
        }
    }

    private void setupCamera() {
        Camera.Parameters parameters = mCamera.getParameters();
        Camera.Size bestPictureSize = getOptimalSizes(parameters.getSupportedPictureSizes());
        if (bestPictureSize != null) {
            parameters.setPictureSize(bestPictureSize.width, bestPictureSize.height);
        }
        Camera.Size bestPreviewSize = getOptimalSizes(parameters.getSupportedPreviewSizes());
        if (bestPreviewSize != null) {
            parameters.setPreviewSize(bestPreviewSize.width, bestPreviewSize.height);
            mDrawOnTopView = new DrawOnTop(this, bitmapTop, bestPreviewSize);
            layout.addView(mDrawOnTopView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        switchCaseMethod(counter);
        mCamera.setParameters(parameters);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        startCameraPreview();
    }

    private void startCameraPreview() {
        if (mCamera != null) {
            mCamera.startPreview();
            isPreviewing = true;
        } else {
            isPreviewing = false;
        }

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

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopAndReleaseCamera();

    }

    @Override
    public void onClick(View v) {
        if (mCamera != null && isPreviewing) {
            mCamera.takePicture(null, null, this);
        }
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        showLoadingDialog();
        if (data != null) {
            camera.stopPreview();
            showLoadingDialog();
            SaveImageTask task = new SaveImageTask(bitmapTop);
            task.execute(data);
        } else {
            Toast.makeText(context, R.string.cant_save_image, Toast.LENGTH_LONG).show();
            camera.startPreview();
        }
    }

    private class SaveImageTask extends AsyncTask<byte[], Void, String> {
        private Bitmap bitmapTop;
        private String filePath = null;
        private String fileName = null;

        public SaveImageTask(Bitmap overlayBitmap) {
            bitmapTop = overlayBitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            File sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File pictureFileDir = new File(sdDir, "/CameraAPIDemo");

            if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
                Toast.makeText(context, "Can't create directory to save image.", Toast.LENGTH_LONG).show();
                cancel(true);
            }
            fileName = MyString.VIN_NUMBER + "_" + Integer.toString(counter) + ".jpg";
            filePath = pictureFileDir.getPath() + File.separator + fileName;
        }

        @Override
        protected String doInBackground(byte[]... params) {
            byte[] data = params[0];
            String destFileName = null;
            if (!isCancelled()) {
                File pictureFile = new File(filePath);
                try {
                    Bitmap underlayBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, null);
                    int width = underlayBitmap.getWidth();
                    int height = underlayBitmap.getHeight();
                    Matrix underlayMatrix = BitmapUtils.getOptimalMatrix(width, height, Constants.IMAGE_WIDTH,
                            Constants.IMAGE_HEIGHT, true);
                    Matrix overlayMatrix = BitmapUtils.getOptimalMatrix(bitmapTop.getWidth(), bitmapTop.getHeight(),
                            Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT, true);
                    Bitmap bmOverlay = Bitmap.createBitmap(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT,
                            underlayBitmap.getConfig());
                    Canvas canvas = new Canvas(bmOverlay);
                    Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
                    canvas.drawBitmap(underlayBitmap, underlayMatrix, paint);
                    canvas.drawBitmap(bitmapTop, overlayMatrix, paint);
                    canvas.save();
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    bmOverlay.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.close();
                    bmOverlay.recycle();

                    destFileName = fileName;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return destFileName;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                MyString.photoName.add(result);
                Toast.makeText(context, "New Image saved:" + result, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, R.string.cant_save_image, Toast.LENGTH_LONG).show();
                mCamera.startPreview();
            }
            hideLoadingDialog();

        }

    }


}
