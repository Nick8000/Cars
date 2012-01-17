package com.hometask;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

public class Loading implements Runnable{
	private static ArrayList<CarInfo> cars = new ArrayList<CarInfo>();
	public Loading(){}
	public ArrayList<CarInfo> getCarArray()
	{
		return cars;
	}
	public void run() {
		Query q = null;
        try {
        	q = new Query();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    	Parse parse = new Parse(q.getPage());
    	
    	for(int i = 0; i < Parse.JSONarr.length(); i++)
        {
			try {
				cars.add(parse.getCarFromJSONObject(Parse.JSONarr.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
	}
}
