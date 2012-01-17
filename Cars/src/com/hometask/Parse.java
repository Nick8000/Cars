package com.hometask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parse 
{
	public Parse(String str)
	{
		this.str = str;
		try {
			parse();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	private void parse() throws JSONException
	{
		str = str.substring(str.indexOf("["));
		str = str.substring(0, str.indexOf("]")+1);
		JSONArray array = new JSONArray(str);
		JSONarr = array;
	}
	public CarInfo getCarFromJSONObject(JSONObject object) throws JSONException
	{
		return new CarInfo((Integer)object.get("id"), object.get("name").toString(), object.get("url").toString(), object.get("make_icon").toString());
	}
	public static JSONArray JSONarr;
	private String str;
}
