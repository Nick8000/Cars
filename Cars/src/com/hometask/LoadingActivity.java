package com.hometask;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

public class LoadingActivity extends Activity{
	private static ArrayList<CarInfo> cars = new ArrayList<CarInfo>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.loading);
	    Loading l = new Loading();
	    Thread t = new Thread(l);
	    t.start();
	    while(t.isAlive())
	    	continue;
	    cars = l.getCarArray();
	    finish();
	}
	public ArrayList<CarInfo> getCarArray()
	{
		return cars;
	} 
}
