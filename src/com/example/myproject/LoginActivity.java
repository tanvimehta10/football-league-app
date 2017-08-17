package com.example.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

	EditText email,pass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login1);
		
		email = (EditText)findViewById(R.id.loginEmailEditText);
		pass = (EditText)findViewById(R.id.loginPassEditText);
	}

	public void onlogin(View v)
	{
		Intent myintent=new Intent(LoginActivity.this, MenuPageActivity.class);
		startActivity(myintent);
		finish();
		
		/*
		if(Validations.VerifyIsBlank(email) && Validations.VerifyIsBlank(pass) ){
			if(Validations.VerifyEmail(email)){
				//Web service call
				
				//Success
				Intent myintent=new Intent(LoginActivity.this, MenuPageActivity.class);
				startActivity(myintent);
				finish();
			}
		}
		*/
		
		
	}
	
	public void forgotpasw(View v)
	{
		Intent fpintent= new Intent(LoginActivity.this, ForgotPassActivity.class);
		startActivity(fpintent);
	}
}
