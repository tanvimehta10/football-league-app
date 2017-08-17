package com.example.myproject;

import com.example.utils.TimeUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuPageActivity extends Activity {

	TextView dateTimeTxtView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_page);
		
		dateTimeTxtView = (TextView)findViewById(R.id.dateTimeTextView);
		dateTimeTxtView.setText(TimeUtils.getTodayDate());
		
		
		
	}
	
	
public void fixClick(View v)
{
	Intent myintent=new Intent(MenuPageActivity.this,fix_res_tablelistActivity.class);
	startActivity(myintent);
}

public void listview(View v)
{
	Intent myintent=new Intent(MenuPageActivity.this,list_viewActivity.class);
	startActivity(myintent);
}

public void displaydivisons(View v)
{
	Intent myintent=new Intent(MenuPageActivity.this, LeagueTableActivity.class);
	startActivity(myintent);
}

public void displaynews(View v)
{
	Intent myintent=new Intent(MenuPageActivity.this,NewAndMedia_Activity.class);
	startActivity(myintent);
}

public void displaysponsors(View v)
{
	Intent myintent=new Intent(MenuPageActivity.this, sponsorsActivity.class);
	startActivity(myintent);
}
}
