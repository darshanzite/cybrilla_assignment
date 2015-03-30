package com.product_rate.ui.product;

import com.product_rate.common.R;
import com.product_rate.model.product.SQLiteProductDAO;
import com.product_rate.ui.product.adapter.ProductsAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;

public class ProductsDashboardActivity extends Activity implements OnClickListener{
	ProductsDashboardActivity context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products_dashboard);
		context=this;
		ImageView btnViewResults=(ImageView) findViewById(R.id.btn_view_results);
		btnViewResults.setOnClickListener(this);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		GridView gridProducts =(GridView) findViewById(R.id.grid_products);

		SQLiteProductDAO dao=new SQLiteProductDAO(context);
		ProductsAdapter adapter=new ProductsAdapter(context, dao.getProducts());
		gridProducts.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
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
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}