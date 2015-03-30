package com.product_rate.common;

import com.product_rate.model.user.SQLiteUserDAO;
import com.product_rate.model.user.UserDTO;
import com.product_rate.ui.authentication.SignInActivity;
import com.product_rate.ui.product.ProductsDashboardActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity {

	int SPLASH_TIME_OUT = 5000;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		SQLiteUserDAO userDAO = new SQLiteUserDAO(this);
		final UserDTO userDTO = userDAO.getUserDetails();

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (userDTO.userid != null && !userDTO.userid.equalsIgnoreCase("null")) {
					Intent intent=new Intent(SplashScreenActivity.this,ProductsDashboardActivity.class);
					startActivity(intent);
					finish();
				}else{
					Intent intent = new Intent(SplashScreenActivity.this,SignInActivity.class);
					startActivity(intent);
					finish();
				}
			}
		}, SPLASH_TIME_OUT);
	}

	/*//SharedPreferences sp = getSharedPreferences(AppConstants.MyPREFERENCES, MODE_WORLD_READABLE);
	//boolean isSession = sp.getBoolean(AppConstants.ISSESSION,false);
	if (isSession) {
		Intent intent=new Intent(SplashScreenActivity.this,ProductsDashboardActivity.class);
		startActivity(intent);
		finish();
	} else {
		Intent intent = new Intent(SplashScreenActivity.this,SignInActivity.class);
		startActivity(intent);
		finish();
	}*/

}
