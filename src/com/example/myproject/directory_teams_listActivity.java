package com.example.myproject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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
import android.widget.ListView;
import android.widget.TextView;

public class directory_teams_listActivity extends Activity {

	
	String divName, id;
	String response;
	List<Teams> teamList = new ArrayList<Teams>();
	TeamsAdapter teamadapter;
	TextView textView;
	ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.directory_teams_list);
		divName = getIntent().getStringExtra("DivName");
		id = getIntent().getStringExtra("Id");
		listview=(ListView)findViewById(R.id.teamlist);
		textView = (TextView)findViewById(R.id.divNameTitle);
		textView.setText(divName);
		//listview.setOnItemClickListener(this);
		
		// async task
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {

				// call webservice
			
				HttpConnection hc = HttpConnection.getInstance();

				// Create URL
				URL url;
				try {
					url = new URL(
							"http://api.qa.lw.clubdev.co.uk/demo/TeamDirectories/index.json");
					// Create Params to send with URL
					List<NameValuePair> parameters = new ArrayList<NameValuePair>();

					NameValuePair nvp = new BasicNameValuePair("auth_id",
							"49eb79bb8a");
					parameters.add(nvp);
					 nvp = new BasicNameValuePair("div", id);
					 parameters.add(nvp);

					// nvp = new BasicNameValuePair("auth_id", "dafdsfsa");
					// parameters.add(nvp);

					// Call webservice

					response = hc.Get(url, parameters);

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
				teamList = parse(response);
				teamadapter=new TeamsAdapter(directory_teams_listActivity.this,teamList);
				
				listview.setAdapter(teamadapter);

				super.onPostExecute(result);
			}

		}.execute();

	}

	protected List<Teams> parse(String response) {

		List<Teams> list = new ArrayList<Teams>();

		try {
			JSONObject jsonObj = new JSONObject(response);
			JSONArray jarray = jsonObj.getJSONArray("pageData");
			JSONObject jObject = jarray.getJSONObject(0);
			JSONObject jobj = jObject.getJSONObject("teams");
			Iterator keys = jobj.keys();
			while (keys.hasNext()) {
				String currentDynamicKey = (String) keys.next();
				// System.out.println(currentDynamicKey);
				String currentDynamicValue = jobj.getString(currentDynamicKey);
				// System.out.println(currentDynamicValue);
				Teams team = new Teams(currentDynamicKey,
						currentDynamicValue);
				list.add(team);

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

	
}
