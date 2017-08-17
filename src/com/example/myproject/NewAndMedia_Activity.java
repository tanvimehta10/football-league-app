package com.example.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewAndMedia_Activity extends Activity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newsandmedianew);
	}
	
	public void clickNews(View v)
	{
		Intent myintent=new Intent(NewAndMedia_Activity.this,LatestNewsList_Activity.class);
		startActivity(myintent);
	}
	
	public void clickNotice(View v)
	{
		Intent myintent=new Intent(NewAndMedia_Activity.this,latest_news_Activity.class);
		startActivity(myintent);	
	}
	
	public void clickPhotos(View v)
	{
		Intent myintent=new Intent(NewAndMedia_Activity.this,latest_news_Activity.class);
		startActivity(myintent);	
	}
	
	public void clickVideos(View v)
	{
		Intent myintent=new Intent(NewAndMedia_Activity.this,latest_news_Activity.class);
		startActivity(myintent);	
	}
}


