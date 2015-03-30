package com.product_rate.ui.product;

import com.product_rate.common.R;
import com.product_rate.model.product.ProductDTO;
import com.product_rate.model.product.SQLiteProductDAO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class RateProductActivity extends Activity implements OnClickListener{

	ProductDTO productDTO;
	TextView tvRateValue, tvSubmit, tvViewResult;
	SeekBar seekbarRate;
	EditText edtComment;
	RateProductActivity context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rating_product);
		context=this;

		seekbarRate=(SeekBar) findViewById(R.id.rating_bar);
		
		edtComment=(EditText) findViewById(R.id.edt_comment);
		
		tvRateValue=(TextView) findViewById(R.id.tv_rate_value);
		
		tvSubmit=(TextView) findViewById(R.id.btn_submit);
		tvSubmit.setOnClickListener(this);
		
		tvViewResult=(TextView) findViewById(R.id.btn_view_results);
		tvViewResult.setOnClickListener(this);

		tvRateValue.setText("1");

		seekbarRate.setProgress(1);
		
		seekbarRate.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if(progress!=0)
					tvRateValue.setText(""+progress);	
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		productDTO=(ProductDTO)getIntent().getSerializableExtra("product");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_submit:
			SQLiteProductDAO dao=new SQLiteProductDAO(context);
			int rate = seekbarRate.getProgress();
			if(rate==0)
				rate=1;
			productDTO.setRate(""+rate);
			
			String comment = edtComment.getText().toString();
			if(comment==null || comment.equalsIgnoreCase("null"))
				comment="";

			productDTO.setComment(comment);

			dao.insertRate(productDTO,"1");
			Toast.makeText(context, getString(R.string.rating_submitted_success), Toast.LENGTH_LONG).show();
			break;

		case R.id.btn_view_results:
			Intent intent = new Intent(context, ResultActivity.class);
			startActivity(intent);
			finish();
			break;
			
		default:
			break;
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
}