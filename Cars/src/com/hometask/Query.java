package com.hometask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Query {
	public Query() throws IOException
	{
		page = "";
		getFromUrl();
	}
	private void getFromUrl() throws IOException
	{
		HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://buyersguide.caranddriver.com/api/feed/?mode=json&q=make");
        try{
            HttpResponse response = client.execute(request);
            request(response);
        }catch(Exception ex){
            ex.printStackTrace();
        }
	}
	 private void request(HttpResponse response){
	        try{
	            InputStream in = response.getEntity().getContent();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	            StringBuilder str = new StringBuilder();
	            String line = null;
	            while((line = reader.readLine()) != null){
	                str.append(line + "\n");
	            }
	            in.close();
	            page = str.toString();
	        }catch(Exception ex){
	            page = "Error";
	        }
	 }
	public String getPage()
	{
		return page;
	}
	private static String page;
}
