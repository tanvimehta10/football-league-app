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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class sponsorsActivity extends Activity implements OnItemClickListener{
	
	String response;
	List<sponsors> sponsorlist = new ArrayList<sponsors>();
	sponsorsAdapter sponsoradapter;
	
	ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sponsors_listview);
		listview=(ListView)findViewById(R.id.sponsorlistView);
		
		listview.setOnItemClickListener(this);
		
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
					 nvp = new BasicNameValuePair("div", "146");
					parameters.add(nvp);

					 nvp = new BasicNameValuePair("team", "1102");
					 parameters.add(nvp);

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
				sponsorlist = parse(response);
				sponsoradapter=new sponsorsAdapter(sponsorsActivity.this, sponsorlist);
				
				listview.setAdapter(sponsoradapter);

				super.onPostExecute(result);
			}

		}.execute();

	}

	protected List<sponsors> parse(String response) {

		List<sponsors> list = new ArrayList<sponsors>();

		try {
			JSONObject jsonObj = new JSONObject(response);
			JSONArray jarray = jsonObj.getJSONArray("pageData");
			JSONObject jObject = jarray.getJSONObject(2);
			JSONArray jarr = jObject.getJSONArray("DivisionTeam");
			JSONObject jobj = jarr.getJSONObject(0);
			System.out.println("rrr");
			Iterator keys = jobj.keys();
			while (keys.hasNext()) {
				String currentDynamicKey = (String) keys.next();
				// System.out.println(currentDynamicKey);
				String currentDynamicValue = jobj.getString(currentDynamicKey);
				// System.out.println(currentDynamicValue);
			//	LeagueTable_Info info = new LeagueTable_Info(currentDynamicKey,
				//		currentDynamicValue);
		//	list.add(info);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//return list;
		return sponsorlist;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		
		Intent myintent=new Intent(sponsorsActivity.this,LeagueTableTeamInfoActivity.class);
		sponsors table = (sponsors)sponsoradapter .getItem(position);
		//myintent.putExtra("DivName", table.Name);
		//myintent.putExtra("Id", table.Id);
		startActivity(myintent);
		
	}

}
