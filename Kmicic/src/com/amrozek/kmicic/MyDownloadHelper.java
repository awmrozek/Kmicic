package com.amrozek.kmicic;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.ConnectException;
import android.content.Context;

import android.util.Log;

public class MyDownloadHelper {
	public final String FILE = "slownik.db";
	public final String TAG = "[Kmicic][MyDownloadHelper] ** ";

	public MyDownloadHelper (Context context) {
		new File("/data/data/com.amrozek.kmicic/databases/helloworld").mkdirs();
	}

	public boolean checkInstall () {
		return (new File("/data/data/com.amrozek.kmicic/databases/slownik.db")).exists();
	}

	public boolean downloadDictionary() {
		InputStream in = null;
		OutputStream out = null;
		try {
		  in = new FileInputStream("/sdcard/slownik.db");
		  out = new FileOutputStream("/data/data/com.amrozek.kmicic/databases/slownik.db");
                  if (new File("/data/data/com.amrozek.kmicic/databases/slownik.db").exists()) {
			  copyFile(in, out);
			  in.close();
			  in = null;
			  out.flush();
			  out.close();
			  out = null;
                  } else {
			Log.v ("[Kmicic][downloadDictionary]", "File /sdcard/slownik.db does not exist!");
			return false;
		  }
		} catch(Exception e) {
		    Log.e("[Kmicic][downloadDictionary][errorCopying] * ", e.getMessage());
			return false;
	    }
		return true;
	}
	private void copyFile(InputStream in, OutputStream out) throws IOException {
	    byte[] buffer = new byte[1024];
	    int read;
	    while((read = in.read(buffer)) != -1){
	      out.write(buffer, 0, read);
	    }
	}
}

