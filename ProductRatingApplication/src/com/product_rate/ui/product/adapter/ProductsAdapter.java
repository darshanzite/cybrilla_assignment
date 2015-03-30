package com.product_rate.ui.product.adapter;

import java.util.ArrayList;
import com.product_rate.common.R;
import com.product_rate.model.product.ProductDTO;
import com.product_rate.ui.product.RateProductActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductsAdapter extends BaseAdapter{
	
	Activity _activity;
	ArrayList<ProductDTO> _productDTOList;
	LayoutInflater inflater;
	public ProductsAdapter(Activity activity, ArrayList<ProductDTO> productDTOList) {
		// TODO Auto-generated constructor stub
		_activity=activity;
		_productDTOList=productDTOList;
		inflater=LayoutInflater.from(_activity);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _productDTOList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _productDTOList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null)
		{
			convertView = inflater.inflate(R.layout.row_product, null);
		}

		final ImageView imgProduct=(ImageView) convertView.findViewById(R.id.img_product);
		TextView tvRateProduct=(TextView) convertView.findViewById(R.id.tv_rate_the_product);
		
		tvRateProduct.setText(""+_productDTOList.get(position).getProduct_name());
		
		imgProduct.setTag(_productDTOList.get(position));
		imgProduct.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ProductDTO product = (ProductDTO)imgProduct.getTag();
				Intent intent=new Intent(_activity, RateProductActivity.class);
				intent.putExtra("product", product);
				_activity.startActivity(intent);
				_activity.finish();
			}
		});
		
		return convertView;
	}
}
