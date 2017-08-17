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

public class LeagueTableActivity extends Activity implements OnItemClickListener{
	
	String response;
	List<Divisions_LeagueTable> divSelect = new ArrayList<Divisions_LeagueTable>();
	LeagueTableDivisionsAdapter leaguetabledivadapter;
	
	ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_division_league_table);
		listview=(ListView)findViewById(R.id.listView1);
		
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
							
							"http://api.qa.lw.clubdev.co.uk/demo/Divisions/index.json");
					// Create Params to send with URL
					List<NameValuePair> parameters = new ArrayList<NameValuePair>();

					NameValuePair nvp = new BasicNameValuePair("auth_id",
							"49eb79bb8a");
					parameters.add(nvp);
					// nvp = new BasicNameValuePair("div", "146");
					 //parameters.add(nvp);

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
				divSelect = parse(response);
				leaguetabledivadapter=new LeagueTableDivisionsAdapter(LeagueTableActivity.this,divSelect);
				
				listview.setAdapter(leaguetabledivadapter);

				super.onPostExecute(result);
			}

		}.execute();

	}

	protected List<Divisions_LeagueTable> parse(String response) {

		List<Divisions_LeagueTable> list = new ArrayList<Divisions_LeagueTable>();

		try {
			JSONObject jsonObj = new JSONObject(response);
			JSONArray jarray = jsonObj.getJSONArray("pageData");
			JSONObject jObject = jarray.getJSONObject(0);
			JSONArray jarr = jObject.getJSONArray("Divisions");
			JSONObject jobj = jarr.getJSONObject(0);
			System.out.println("rrr");
			Iterator keys = jobj.keys();
			while (keys.hasNext()) {
				String currentDynamicKey = (String) keys.next();
				// System.out.println(currentDynamicKey);
				String currentDynamicValue = jobj.getString(currentDynamicKey);
				// System.out.println(currentDynamicValue);
				Divisions_LeagueTable div = new Divisions_LeagueTable(currentDynamicKey,
						currentDynamicValue);
				list.add(div);

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
		
		Intent myintent=new Intent(LeagueTableActivity.this,LeagueTableTeamInfoActivity.class);
		Divisions_LeagueTable div = (Divisions_LeagueTable) leaguetabledivadapter.getItem(position);
		myintent.putExtra("DivName", div.Name);
		myintent.putExtra("Id", div.Id);
		startActivity(myintent);
		
	}

	

}



