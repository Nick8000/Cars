package com.hometask;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.hometask.CarInfo;
public class MyAdapter extends ArrayAdapter<Object>{

	private Context context;
	private ArrayList<CarInfo> objects;
	public MyAdapter(Context context, ArrayList<CarInfo> objects) {
		super(context, R.layout.querying, objects.toArray());
		this.context = context;
		this.objects = objects;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.querying, parent, false);
		// viewHolder
		rowView.setTag(objects.get(position).getId());
		TextView textView = (TextView) rowView.findViewById(R.id.textView1);
		TextView textView2 = (TextView) rowView.findViewById(R.id.textView2);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1);
		
		textView.setText(Integer.toString(objects.get(position).getId()));
		textView.setTextColor(Color.GREEN);
		textView2.setText(objects.get(position).getName());
		textView2.setTextColor(Color.RED);
		imageView.setImageBitmap(objects.get(position).getImage());
		return rowView;
	}
}
