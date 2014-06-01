package com.example.carlypso;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class UploadActivity extends Activity {
    private Button buttonUpload;
    private MyFTPClient client = new MyFTPClient();
    private String str;
    private File sdDir;
    private Context context;
    private ProgressBar uploadProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        context = this;
        getIntent();

        sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        // pictureFileDir =  new File(sdDir, "/CameraAPIDemo");
        // srcFilePath = pictureFileDir.getPath();

        buttonUpload = (Button) findViewById(R.id.button1);
        buttonUpload.setBackgroundColor(Color.GREEN);
        buttonUpload.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (MyString.photoName.size() > 0) {
                    
                    new UploadToFTP().execute();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.no_photo_to_upload, Toast.LENGTH_LONG).show();
                }
            }
        });
        uploadProgressBar = (ProgressBar) findViewById(R.id.pb_upload);
        uploadProgressBar.setProgress(0);
        uploadProgressBar.setMax(MyString.photoName.size());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_upload, menu);
        return true;
    }

    private class UploadToFTP extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            uploadProgressBar.setProgress(0);
            uploadProgressBar.setVisibility(View.VISIBLE);
            buttonUpload.setText(R.string.uploading_label);
            buttonUpload.setEnabled(false);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = false;
			String ftpDirectoryName = "";
			
			if(client.ftpConnect(MyString.HOST_NAME, MyString.USER_NAME, MyString.PASSWORD, MyString.PORT)){
                boolean canGotoDirectory = client.ftpChangeDirectory(MyString.VIN_NUMBER);
                if (!canGotoDirectory) {
                    client.ftpMakeDirectory(MyString.VIN_NUMBER);
                    canGotoDirectory = client.ftpChangeDirectory(MyString.VIN_NUMBER);
                }
                if (canGotoDirectory) {
                    ftpDirectoryName = client.ftpGetCurrentWorkingDirectory();
					for(int i=0;i<MyString.photoName.size();i++){
                        File f = new File(sdDir.getPath() + File.separator + "CameraAPIDemo/",
                                MyString.photoName.get(i));
                        if (f.exists()) {
                            result = client.ftpUpload(f.getPath(), MyString.photoName.get(i), ftpDirectoryName,
                                    context);
                            if (result) {
                                publishProgress(i);
                            } else {
                                return false;
                            }
                        } else {
                            publishProgress(i);
                        }
					}
				}
				
			}
			return result;
		}

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            uploadProgressBar.setProgress(values[0] + 1);
            buttonUpload.setText(getString(R.string.upload_progress, uploadProgressBar.getProgress(),
                    uploadProgressBar.getMax()));
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            buttonUpload.setEnabled(true);
            buttonUpload.setText(R.string.upload_btn_label);
            uploadProgressBar.setVisibility(View.GONE);
            if (result) {
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), R.string.cant_upload_file, Toast.LENGTH_LONG).show();
            }

        }

    }

}
