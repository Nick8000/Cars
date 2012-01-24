package com.hometask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        final ListView list = (ListView)findViewById(R.id.listView1);
        final EditText txt = (EditText)findViewById(R.id.editText1);
        txt.setVisibility(0);
                
        txt.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
				String h = txt.getText().toString();
				if(h.length() > 0)
					h = h.toUpperCase();
				txt.setText(h);
				list.setFilterText(h);
				return false;
			}
		});
        
	    Intent intent = new Intent(this, com.hometask.LoadingActivity.class/*load.getClass()*/);
	    startActivity(intent);
	    
        Task task = new Task();
        task.execute(null);
        ArrayList<CarInfo> carsUnsorted = new ArrayList<CarInfo>();
        
        
		try {
			carsUnsorted = task.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		final ArrayList<CarInfo> cars;
		if(false) // show each 5 ids false will changed when the settings will work
			cars = eachFiveIds(Sort(carsUnsorted));
		else
			cars = Sort(carsUnsorted);
        
        
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

    private ArrayList<CarInfo> eachFiveIds(ArrayList<CarInfo> allCars)
    {
    	ArrayList<CarInfo> temp = Sort(allCars);
    	ArrayList<CarInfo> eachFive = new ArrayList<CarInfo>();
    	for(int i = 0; i < temp.size(); i++)
    		if((temp.get(i).getId() % 5) == 0)
    			eachFive.add(temp.get(i));
    	return eachFive;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.settings:
            showSettings();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    private void showSettings()
    {
    	Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_LONG).show();
    	Intent intent = new Intent(this, com.hometask.SettingsActivity.class);
    	startActivity(intent);
    }
    
    private ArrayList<CarInfo> Sort(ArrayList<CarInfo> cars){
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
