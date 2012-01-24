package com.hometask;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.settings);
	    
	    CheckBox check1 = (CheckBox)findViewById(R.id.checkBox1);
	    CheckBox check2 = (CheckBox)findViewById(R.id.checkBox2);
	    
	    check1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				showState(arg1);
			}
		});
	    
	    check2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				showState(isChecked);
			}
		});
	}
	
	
	private void showState(boolean arg)
	{
		String state = "";
		if(arg)
			state = "Checked";
		else
			state = "Unchecked";
		Toast.makeText(getApplicationContext(), state, Toast.LENGTH_LONG);
	}

}
