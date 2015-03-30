package com.product_rate.ui.authentication;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.product_rate.common.R;
import com.product_rate.model.product.ProductDTO;
import com.product_rate.model.product.SQLiteProductDAO;
import com.product_rate.ui.product.RateProductActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class SignInActivity extends Activity implements OnClickListener{
	SignInActivity context;
	AccountManager mAccountManager;
	String token;
	int serverCode;
	private static final String SCOPE = "oauth2:https://www.googleapis.com/auth/userinfo.profile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		context=this;
		ImageView btnSignIn=(ImageView) findViewById(R.id.btn_sign_in);
		btnSignIn.setOnClickListener(this);

		SQLiteProductDAO dao=new SQLiteProductDAO(this);
		int count = dao.isProductExist();
		if(count==0)
		{
			for (int i = 0; i < 5; i++) {
				ProductDTO DTO = new ProductDTO();
				DTO.setProduct_name("PRODUCT "+(i+1));
				dao.insertProducts(DTO);	
			}	
		}	
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_sign_in:
			//signInWithGplus();
			syncGoogleAccount();
			break;

		default:
			break;
		}
	}	

	private String[] getAccountNames() {
		mAccountManager = AccountManager.get(this);
		Account[] accounts = mAccountManager
				.getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
		String[] names = new String[accounts.length];
		for (int i = 0; i < names.length; i++) {
			names[i] = accounts[i].name;
		}
		return names;
	}

	private AbstractGetNameTask getTask(SignInActivity activity, String email,
			String scope) {
		return new GetNameInForeground(activity, email, scope);

	}

	public void syncGoogleAccount() {
		if (isNetworkAvailable() == true) {
			String[] accountarrs = getAccountNames();
			if (accountarrs.length > 0) {
				//you can set here account for login
				getTask(SignInActivity.this, accountarrs[0], SCOPE).execute();
			} else {
				Toast.makeText(SignInActivity.this, "No Google Account Sync!",
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(SignInActivity.this, "No Network Service!",
					Toast.LENGTH_SHORT).show();
		}
	}

	public boolean isNetworkAvailable() {

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			Log.e("Network Testing", "***Available***");
			return true;
		}
		Log.e("Network Testing", "***Not Available***");
		return false;
	}	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(this.getClass().getName(), "onKeyDown");
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}	
}