package com.hometask;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.os.AsyncTask;

public class Task extends AsyncTask<Void, Void, ArrayList<CarInfo>>{

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	@Override
	protected ArrayList<CarInfo> doInBackground(Void... params) {
		ArrayList<CarInfo> cars = new ArrayList<CarInfo>();
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
		return cars;
	}
	
}
