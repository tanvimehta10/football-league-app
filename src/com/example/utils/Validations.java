package com.example.utils;

import android.widget.EditText;

/**
 * <p>
 * Contains static methods for input validations
 * </p>
 * 
 * 
 */
public class Validations {

	/**
	 * 
	 * @param editText
	 * @return
	 */
	public static boolean VerifyMaxLength(EditText editText) {
		if (VerifyIsBlank(editText)) {
			if (editText.getText().toString().trim().length() > 20) {
				editText.setError("Maximum of 20 characters allowed!");
				editText.requestFocus();
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return false;
		}
	}
	
	
	
	/**
	 * Checks whether the field is empty or not. Set the error text on the
	 * edittext
	 * 
	 * @param editText
	 * @return True if field not empty, false otherwise
	 */
	public static boolean VerifyIsBlank(EditText editText) {
		if (editText.getText().toString().trim().length() == 0) {
			editText.setError("Please enter the required field!");
			editText.requestFocus();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks whether email address is valid or not. Set the error text on the
	 * edittext
	 * 
	 * @param editText
	 * @return True if email is valid, false otherwise
	 */
	public static boolean VerifyEmail(EditText editText) {
		if (VerifyIsBlank(editText)) {
			String email = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			if (!(editText.getText().toString().trim().matches(email))) {
				editText.setError("Email address is invalid!");
				editText.requestFocus();
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * Checks whether phone no is valid or not. Set the error text on the
	 * EditText
	 * 
	 * @param editText
	 * @return True if phone no is valid and 10 digits, false otherwise
	 */
	public static boolean VerifyIsPhoneNumber(EditText editText) {
		if (VerifyIsBlank(editText)) {
			String phoneno = "^[0-9]{10}$";
			if (!(editText.getText().toString().matches(phoneno))) {
				editText.setError("Phone number is invalid!");
				editText.requestFocus();
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public static boolean VerifyIfEqual(EditText editText,EditText text)
	{
		
		String password=editText.getText().toString();
		String conpassword=text.getText().toString();
		if(password.equals(conpassword)){
			return true;
			
			
		}
		else{
			text.setError("Passwords donot match");
			text.requestFocus();
			return false;
			
		}
	}
	
}

