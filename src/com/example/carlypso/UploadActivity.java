package com.example.carlypso;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;

public class UploadActivity extends Activity {
	private Button buttonUpload;
	private MyFTPClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		buttonUpload = (Button)findViewById(R.id.button1);
		buttonUpload.setBackgroundColor(Color.GREEN);
		getIntent();
		new UploadToFTP().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_upload, menu);
		return true;
	}
	
	
	private class UploadToFTP extends AsyncTask<String, String, String>{
    	

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			//connnectingwithFTP("178.63.0.66", "photos", "CaRlYpSo");
			
			//connnectingwithFTP("appsoidtester.comli.com", "a6422541", "irishhngf12345");
			if(client.ftpConnect(host, username, password, port))
			return null;
		}
    	
    }

}
