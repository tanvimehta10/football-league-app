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

import com.example.utils.Pixels;
import com.inqbarna.tablefixheaders.TableFixHeaders;



import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;


public class LeagueTableTeamInfoActivity extends Activity   {
	
	String response;
	List<LeagueTable_Info> teamInfo = new ArrayList<LeagueTable_Info>();
	TableFixHeaders tableFixHeaders;
	private Context context = this;
	String divName, id;
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.league_table_teaminfo);
		divName = getIntent().getStringExtra("DivName");
		id = getIntent().getStringExtra("Id");
		textView = (TextView)findViewById(R.id.divNameTitle1);
		textView.setText(divName);
		tableFixHeaders = (TableFixHeaders) findViewById(R.id.table);
		
		
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
					nvp = new BasicNameValuePair("div", id);
					parameters.add(nvp);

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
				teamInfo = parse(response);
				tableFixHeaders.setAdapter(new MyAdapter(LeagueTableTeamInfoActivity.this));
				super.onPostExecute(result);
			}

		}.execute();

	}

	protected List<LeagueTable_Info> parse(String response) {

		List<LeagueTable_Info> list = new ArrayList<LeagueTable_Info>();

		try {
			JSONObject jsonObj = new JSONObject(response);
			JSONArray jarray = jsonObj.getJSONArray("pageData");
			JSONObject jObject = jarray.getJSONObject(0);
			JSONArray jarr = jObject.getJSONArray("points_table");
			
			int length=jarr.length();
			for(int i=0;i<length;i++)
			{
				JSONObject jobj = jarr.getJSONObject(i);
				LeagueTable_Info info=new LeagueTable_Info();
				info.setTeam_id(jobj.getString("team_id"));
				info.setTeam_name(jobj.getString("team_name"));
				info.setTotal_played(jobj.getString("total_played"));
				info.setTotal_won(jobj.getString("total_won"));
				info.setTotal_drawn(jobj.getString("total_drawn"));
				info.setTotal_lost(jobj.getString("total_lost"));
				info.setGoals_scored(jobj.getString("goals_scored"));
				info.setGoals_conceded(jobj.getString("goals_conceded"));
				info.setGoals_difference(jobj.getString("goals_difference"));
				info.setTotal_points(jobj.getString("total_points"));
				info.setClub_id(jobj.getString("club_id"));
				list.add(info);
			}
			
			System.out.println("rrr");
			
				

			
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


	

	public class MyAdapter extends LeagueTableInfo_Adapter {

		private final int width;
		private final int height;

		public MyAdapter(Context context) {
			super(context);

			Resources resources = context.getResources();

			width = resources.getDimensionPixelSize(R.dimen.table_width);
			height = resources.getDimensionPixelSize(R.dimen.table_height);
		}

		@Override
		public int getRowCount() {
			return teamInfo.size();
		}

		@Override
		public int getColumnCount() {
			return 8;
		}

		@Override
		public int getWidth(int column) {
			if(column==-1)
			{
				return (int) Pixels.convertDpToPixel(165, context);
			}
			return width;
		}

		@Override
		public int getHeight(int row) {
			return height;
		}

		@Override
		public String getCellString(int row, int column) {
			//return "Lorem (" + row + ", " + column + ")";
			if(row < 0){
				
				switch (column) {
				
				case -1:
					return "Team";
				case 0:
					return "Pld";
				case 1:
					return "GD";
				case 2:
					return "Pts";
				case 3:
					return "W";

				case 4:
					return "D";
				case 5:
					return "L";
				case 6:
					return "F";
				case 7:
					return "A";


				}
				//return "Sunil (" + row + ", " + column + ")";
			}
			else{
				LeagueTable_Info info = teamInfo.get(row);
				
				switch (column) {
				case 0:
					return info.getTotal_played();
				case 1:
					return info.getGoals_difference();
				case 2:
					return info.getTotal_points();
				case 3:
					return info.getTotal_won();
				case 4:
					return info.getTotal_drawn();
				case 5:
					return info.getTotal_lost();
				case 6:
					return info.getGoals_scored();
				case 7:
					return info.getGoals_conceded();
				default:
					return info.getTeam_name();
				}
				//return "Lorem (" + row + ", " + column + ")";
			}
			return null;
		
		}

		@Override
		public int getLayoutResource(int row, int column) {
			final int layoutResource;
			switch (getItemViewType(row, column)) {
				case 0:
					layoutResource = R.layout.item_table1_header;
				break;
				case 1:
					layoutResource = R.layout.item_table1;
				break;
				case 2:
					layoutResource = R.layout.item_table_left;
				break;
				default:
					throw new RuntimeException("error");
			}
			return layoutResource;
		}

		@Override
		public int getItemViewType(int row, int column) {
			if (row < 0) {
				return 0;
			} 
			else if (column == -1){
				return 2;
			}
			else {
				return 1;
			}
		}

		@Override
		public int getViewTypeCount() {
			return 3;
		}
	}


}






