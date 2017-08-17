package com.example.myproject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class LatestNewsList_Activity extends Activity implements OnItemClickListener{

	String response;
	List<latestNewsList> newsList = new ArrayList<latestNewsList>();
	LatestNewsList_Adapter newslistadapter;
	
	ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		listview=(ListView)findViewById(R.id.listView1);
		
		listview.setOnItemClickListener(this);
		
		// async task
		new AsyncTask<Void,Void,Void>() {
		

			@Override
			protected Void doInBackground(Void... params) {

				// call webservice
			
			HttpConnection hc = HttpConnection.getInstance();

				// Create URL
				URL url;
				try {
					url = new URL(
							"http://api.qa.lw.clubdev.co.uk/demo/LeagueNews.json");
					// Create Params to send with URL
					List<NameValuePair> parameters = new ArrayList<NameValuePair>();

					NameValuePair nvp = new BasicNameValuePair("auth_id",
							"49eb79bb8a");
					parameters.add(nvp);
					// nvp = new BasicNameValuePair("div", "146");
					 //parameters.add(nvp);

					 //nvp = new BasicNameValuePair("auth_id", "dafdsfsa");
					// parameters.add(nvp);

					// Call webservice

					response = hc.Get(url,parameters);

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return null;
			}

			@Override
			protected void onPostExecute(Void result) {

				// Process response and update UI
				System.out.println(response);
				newsList = parse(response);
				newslistadapter=new LatestNewsList_Adapter(LatestNewsList_Activity.this,newsList);				
				listview.setAdapter(newslistadapter);

				super.onPostExecute(result);
			}

		}.execute();

	}

	protected List<latestNewsList> parse(String response) {

		List<latestNewsList> list = new ArrayList<latestNewsList>();

		try {
			JSONObject jsonObj = new JSONObject(response);
			JSONArray jarray = jsonObj.getJSONArray("pageData");
			JSONObject jObject = jarray.getJSONObject(0);
			//JSONObject jobj = jObject.getJSONObject("news_items");
			JSONArray jarr = jObject.getJSONArray("news_items");
			JSONObject jobj,j;
			
			int length = jarr.length();
			
			for(int i=0;i<length;i++){
				jobj = jarr.getJSONObject(i);
				j = jobj.getJSONObject("LeagueNews");
				latestNewsList news = new latestNewsList(j.getString("id"),j.getString("title"));
				list.add(news);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent myintent=new Intent(LatestNewsList_Activity.this,latest_news_Activity.class);
		latestNewsList news = (latestNewsList) newslistadapter.getItem(position);
		
		myintent.putExtra("Id",news.Id);
		startActivity(myintent);
	}


	

}
