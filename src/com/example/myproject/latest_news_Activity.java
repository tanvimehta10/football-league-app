package com.example.myproject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.utils.TimeUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class latest_news_Activity extends Activity {

	String response;
	List<latest_news> newsList = new ArrayList<latest_news>();
	
	String Id;
	String newsurl;
	LinearLayout linlayout;
	TextView pubdate,newstitle,newsdetails;
	ImageView newsimg;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.latestnews_items);
		Id = getIntent().getStringExtra("Id");
		
		newsurl = "http://api.qa.lw.clubdev.co.uk/demo/LeagueNews/view/" + Id
				+ ".json?auth_id=49eb79bb8a";

		pubdate=(TextView)findViewById(R.id.publishedDate);
		newstitle=(TextView)findViewById(R.id.newsTitle);
		newsdetails=(TextView)findViewById(R.id.newsInfo);
		newsimg=(ImageView)findViewById(R.id.newsimage);
		// async task
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {

				// call webservice

				HttpConnection hc = HttpConnection.getInstance();

				// Create URL
				URL url;
				try {
					url = new URL(newsurl);
					// Create Params to send with URL (not needed here!!)

					// Call webservice

					response = hc.Get(url);

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

				ParseAndDisplay(response);

				
				super.onPostExecute(result);
			}
		}.execute();

	}

	private void ParseAndDisplay(String response) {
		// TODO Auto-generated method stub
		latest_news news = new latest_news();

		try {
			JSONObject jsonObj = new JSONObject(response);
			JSONArray jarray = jsonObj.getJSONArray("pageData");
			JSONObject jObject = jarray.getJSONObject(0);
			JSONObject jobj = jObject.getJSONObject("first_news");
			JSONObject newsobj = jobj.getJSONObject("LeagueNews");
			JSONObject imgobj = jobj.getJSONObject("LeagueImage");

			news.setId(newsobj.getString("id"));
			news.setTitle(newsobj.getString("title"));
			news.setDescription(newsobj.getString("description"));
			news.setCreated(newsobj.getString("created"));
			news.setImageFilename(imgobj.getString("ImageFilename"));

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		pubdate.setText("Published " + TimeUtils.formatDate(news.getCreated()));
		newstitle.setText(news.getTitle());
		newsdetails.setText(Html.fromHtml( news.getDescription()) );
		
	}

}
