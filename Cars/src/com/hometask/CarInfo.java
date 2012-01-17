package com.hometask;

import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;

public class CarInfo {
	public CarInfo(int id, String name, String url, String icon)
	{
		this.id = id;
		this.name = name;
		try 
		{
			this.url = url;
			this.icon = new URL(icon);
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		getImageByUrl();
	}
	private void getImageByUrl()
	{
		ImageDownloader im = new ImageDownloader(icon.toString());
		image = im.getImage();
	}
	public String toString()
	{
		return "ID: "+id+"\nName: "+name+"\nURL: "+url.toString()+"\nIcon: "+icon.toString();
	}
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public String getUrl()
	{
		return url;
	}
	public URL getIconUrl()
	{
		return icon;
	}
	public Bitmap getImage()
	{
		return image;
	}
	private int id;
	private String name;
	private String url;
	private URL icon;
	private Bitmap image;
}
// id name url make_icon