package com.product_rate.ui.product;

import java.util.ArrayList;

import com.google.android.gms.internal.fo;
import com.product_rate.common.R;
import com.product_rate.model.product.SQLiteProductDAO;
import com.product_rate.model.result.ResultDTO;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class ResultActivity extends Activity implements OnClickListener{

	LinearLayout linearValues;
	ResultActivity context;
	ArrayList<ResultDTO> resultList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		context=this;
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		LinearLayout linearHeaders=(LinearLayout) findViewById(R.id.linear_headers);
		linearValues=(LinearLayout) findViewById(R.id.linear_values);
		
		SQLiteProductDAO dao = new SQLiteProductDAO(context);
		resultList = dao.getUserRates();
		
		if(resultList==null || resultList.size()==0)
		{
			linearHeaders.setVisibility(View.GONE);
			linearValues.setVisibility(View.GONE);
		}else{
			linearHeaders.setVisibility(View.VISIBLE);
			linearValues.setVisibility(View.VISIBLE);
		}
		setUpUI();
	}

	private void setUpUI()
	{
		TextView tvRateTheProducts=(TextView)findViewById(R.id.btn_rate_the_products);
		tvRateTheProducts.setOnClickListener(this);
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.weight=1;

		for (int i = 0; i < resultList.size(); i++) {
			
			LinearLayout.LayoutParams paramsL = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			//paramsL.weight=1;
			LinearLayout ll=new LinearLayout(this);
			ll.setOrientation(LinearLayout.HORIZONTAL);
			
			for (int j = 0; j < 3; j++) {

				TextView tv=new TextView(this);
				tv.setLayoutParams(params);
				//params.setMargins(0, 0, 1, 0);
				tv.setTextSize(15.0f);
				tv.setPadding(0, 15, 0, 15);
				tv.setTextColor(getResources().getColor(android.R.color.black));
				tv.setTypeface(null, Typeface.BOLD);
				tv.setGravity(Gravity.CENTER);

				if(j==0)
				{
					tv.setText(resultList.get(i).getProduct_name());
					tv.setBackgroundColor(getResources().getColor(android.R.color.white));
				}
				
				if(j==1)
				{	
					params.setMargins(0, 0, 2, 0);
					tv.setBackgroundColor(getResources().getColor(R.color.medium_semitransperent_background));
					tv.setText(""+resultList.get(i).getAvg_mtd_rate());
				}

				if(j==2)
				{	
					params.setMargins(2, 0, 0, 0);
					tv.setText(""+resultList.get(i).getAvg_ytd_rate());
					tv.setBackgroundColor(getResources().getColor(R.color.medium_semitransperent_background));
				}
				if(i%2==0)
					tv.setBackgroundColor(getResources().getColor(R.color.semitransperent_background));
				
				ll.addView(tv);
			}
			linearValues.addView(ll,paramsL);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(this.getClass().getName(), "onKeyDown");
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Intent intent = new Intent(context, ProductsDashboardActivity.class);
			startActivity(intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_rate_the_products:
			Intent intent = new Intent(context, ProductsDashboardActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}	
}