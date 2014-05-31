package com.example.carlypso;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class UploadActivity extends Activity {
	private Button buttonUpload;
	private MyFTPClient client;
	private String str;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		getIntent();
		buttonUpload = (Button)findViewById(R.id.button1);
		buttonUpload.setBackgroundColor(Color.GREEN);
		buttonUpload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new UploadToFTP().execute();
				
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
			// TODO Auto-generated method stub
			//connnectingwithFTP("178.63.0.66", "photos", "CaRlYpSo");
			
			//connnectingwithFTP("appsoidtester.comli.com", "a6422541", "irishhngf12345");
			if(client.ftpConnect(MyString.HOST_NAME, MyString.USER_NAME, MyString.PASSWORD, MyString.PORT)){
				str = client.ftpGetCurrentWorkingDirectory();
			}
			return null;
		}
    	
    }

}
