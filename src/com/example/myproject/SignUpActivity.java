package com.example.myproject;

import com.example.utils.Validations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends Activity{
	
	EditText FirstName,LastName,email,pass,confirmPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
		
		FirstName=(EditText)findViewById(R.id.FirstNameText);
		LastName=(EditText)findViewById(R.id.LastNameText);
		email=(EditText)findViewById(R.id.EmailText);
		pass=(EditText)findViewById(R.id.passwordText);
		confirmPass=(EditText)findViewById(R.id.confirmPassText);
	}
	
	public void onPayment(View v)
	{
		
		if(Validations.VerifyIsBlank(FirstName) && Validations.VerifyIsBlank(LastName) && Validations.VerifyEmail(email) && Validations.VerifyIsBlank(pass) && Validations.VerifyIsBlank(confirmPass)){
			if(Validations.VerifyIfEqual(pass, confirmPass)){
				//Web service call
				
				//Success
				Intent myintent=new Intent(SignUpActivity.this, MenuPageActivity.class);
				startActivity(myintent);
				finish();
			}
			
			
		}
	}
	
}
