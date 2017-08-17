package com.example.myproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ForgotPassActivity extends Activity {

	EditText email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	setContentView(R.layout.forgot_passw);
	email = (EditText)findViewById(R.id.forgotpassEmail);
	
	}
	
	

}
