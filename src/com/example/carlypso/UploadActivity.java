package com.example.carlypso;

import java.io.File;

import org.apache.commons.net.ftp.FTPClient;

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
import android.widget.Toast;

public class UploadActivity extends Activity {
	private Button buttonUpload;
	private MyFTPClient client = new MyFTPClient();
	private String str;
	private String srcFilePath;
	private File pictureFileDir,sdDir;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		context = this;
		getIntent();
		
		sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
       // pictureFileDir =  new File(sdDir, "/CameraAPIDemo");
       // srcFilePath = pictureFileDir.getPath();
		
        
        
		buttonUpload = (Button)findViewById(R.id.button1);
		buttonUpload.setBackgroundColor(Color.GREEN);
		buttonUpload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//new UploadToFTP().execute();
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_upload, menu);
		return true;
	}
	
	
	private class UploadToFTP extends AsyncTask<String, String, String>{

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

		@Override
		protected String doInBackground(String... params) {
			
			String ftpDirectoryName = "";
			// TODO Auto-generated method stub
			
			if(client.ftpConnect(MyString.HOST_NAME, MyString.USER_NAME, MyString.PASSWORD, MyString.PORT)){
				if(client.ftpMakeDirectory(MyString.VIN_NUMBER)){
					client.ftpChangeDirectory(MyString.VIN_NUMBER);
					ftpDirectoryName = client.ftpGetCurrentWorkingDirectory();
					
					for(int i=0;i<MyString.photoName.size();i++){
						//pictureFileDir =  new File("file://mnt/"+sdDir.getPath()+"/CameraAPIDemo/"+MyString.photoName.get(i).toString());
						//File f = new File(context.getFilesDir().getAbsolutePath()+"/CameraAPIDemo/"+MyString.photoName.get(i).toString());
						File f = new File(sdDir.getPath()+File.separator+"CameraAPIDemo/",MyString.photoName.get(i).toString());
						if(client.ftpUpload(f.getPath(),
								MyString.photoName.get(i).toString(),ftpDirectoryName,context)){
							
							Toast.makeText(context, MyString.photoName.get(i).toString()+" uploaded ", Toast.LENGTH_SHORT).show();
						}
					}
				}
				
			}
			return null;
		}
    	
    }
	
	

}
