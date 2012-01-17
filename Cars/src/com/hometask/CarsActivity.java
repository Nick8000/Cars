package com.hometask;

import java.util.ArrayList;
import java.util.Arrays;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CarsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setTitle("Car makes");
        final ListView list = (ListView)findViewById(R.id.listView1);
        final EditText txt = (EditText)findViewById(R.id.editText1);
        
        txt.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
				String h = txt.getText().toString();
				if(h.length() == 1)
					h = h.toUpperCase();
				if(h.equals(""))
				txt.setText(h);
				list.setFilterText(h);
				return false;
			}
		});
//        LoadingActivity loading = new LoadingActivity();
//        Intent intent = new Intent(this, loading.getClass());
//        startActivity(intent);
        Loading load = new Loading();
        load.run();
        
        final ArrayList<CarInfo> carsUnsorted = load.getCarArray();
        
        final ArrayList<CarInfo> cars = Sort(carsUnsorted);
        
        ArrayAdapter<Object> adapter = new MyAdapter(getApplicationContext(), cars);
        list.setAdapter(adapter);
        
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) { 
				Toast tt = Toast.makeText(getApplicationContext(), cars.get((int)id).getName(), Toast.LENGTH_LONG);
				tt.show();
				String url = cars.get((int)id).getUrl();
				Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(intent);
			}
		}); 	
    }

    private ArrayList<CarInfo> Sort(ArrayList<CarInfo> cars)
    {
    	ArrayList<CarInfo> newCars = new ArrayList<CarInfo>();
    	int [] ids = new int[cars.size()];
    	for(int i = 0; i < cars.size(); i ++)
    	{
    		ids[i] = cars.get(i).getId();
    	}  	
    	Arrays.sort(ids);
    	for(int i = 0; i < cars.size(); i++)
    	{
    		for(int j = 0; j < cars.size(); j++)
    		{
    			if(cars.get(j).getId() == ids[i])
    			{
    				newCars.add(cars.get(j));
    				break;
    			}
    		}
    	}
    	
    	return newCars;
    }
}
