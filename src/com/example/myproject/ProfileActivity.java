package com.example.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_profile);
	}
	
	public void resetpasw(View v)
	{
		Intent i=new Intent(ProfileActivity.this,ResetPasswordActivity.class);
		startActivity(i);
	}

}
