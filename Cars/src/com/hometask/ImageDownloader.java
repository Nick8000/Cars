package com.hometask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageDownloader {
	public ImageDownloader(String url)
	{
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Bitmap getImage()
	{
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection)url.openConnection();
		
		conn.setDoInput(true);
		conn.connect();
		InputStream is = conn.getInputStream();
		image = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
		
	}
	private Bitmap image;
	private URL url;
}
