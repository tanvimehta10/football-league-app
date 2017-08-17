package com.example.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_page);
	}
public void doLogin(View v)
{
	Intent myintent=new Intent(HomeActivity.this, LoginActivity.class);
	startActivity(myintent);
}
public void doSignup(View n)
{
	Intent newintent=new Intent(HomeActivity.this,SignUpActivity.class );
	startActivity(newintent);		
}
}
