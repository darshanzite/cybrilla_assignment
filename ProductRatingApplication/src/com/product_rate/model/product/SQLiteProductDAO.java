package com.product_rate.model.product;

import java.util.ArrayList;
import java.util.Currency;

import com.product_rate.db.DBhelper;
import com.product_rate.model.result.ResultDTO;
import com.product_rate.utils.HelperFunctions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class SQLiteProductDAO extends DBhelper{

	public SQLiteProductDAO(Context _context) {
		// TODO Auto-generated constructor stub
		super(_context);
	}

	public void insertProducts(ProductDTO DTO) {
		try {
			db=open();
			db.setLockingEnabled(false);
			ContentValues args=new ContentValues();
			args.put("product_name", ""+DTO.getProduct_name());
			db.insertOrThrow(TABLE_PRODUCT, null, args);
			closeDatabase(db);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDatabase(db);
		}
	}

	public void insertRate(ProductDTO DTO, String user_id) {
		int count=0;
		try {
			db=open();
			db.setLockingEnabled(false);

			String query="select count(1) as 'count' from "+TABLE_RATE+" where dt_rate_on='"+HelperFunctions.getDateForDatabase()+"' and fk_product_id="+DTO.getProduct_id();
			
			Cursor curser = db.rawQuery(query, null);
			Cursor cursor = db.rawQuery(query, null);
			while (cursor.moveToNext()) {
				count = count + cursor.getInt(cursor.getColumnIndex("count"));
			}
			cursor.close();
			closeDatabase(db);
			
			ContentValues args=new ContentValues();
			if(count==0){
				db=open();
				db.setLockingEnabled(false);
				//pk_rating_id INTEGER PRIMARY KEY, fk_user_id INTEGER, 
				//fk_product_id INTEGER, rate INTEGER, dt_rate_on DATE
				args.put("fk_user_id", ""+user_id);
				args.put("fk_product_id", ""+DTO.getProduct_id());
				args.put("rate", ""+DTO.getRate());
				args.put("user_comment", ""+DTO.getComment());
				args.put("dt_rate_on", ""+HelperFunctions.getDateForDatabase());
				db.insertOrThrow(TABLE_RATE, null, args);
				Log.d("INSERT RATE SUCCESS", "INSERT RATE SUCCESS");
				closeDatabase(db);
			}else if(count>0)
			{
				db=open();
				db.setLockingEnabled(false);
				args.put("rate", ""+DTO.getRate());
				args.put("user_comment", ""+DTO.getComment());
				String where="fk_user_id="+user_id+" and fk_product_id="+DTO.getProduct_id()+" and dt_rate_on='"+HelperFunctions.getDateForDatabase()+"'";
				int update = db.update(TABLE_RATE, args, where, null);
				
				Log.d("UPDATE RATE COUNT", ""+update);

				if(db.inTransaction())
					db.setTransactionSuccessful();

				if (db.inTransaction()) {
					db.endTransaction();
				}
				closeDatabase(db);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDatabase(db);
		}
	}

	public ArrayList<ProductDTO> getProducts()
	{
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();

		try {
			db=open();
			String query="select * from "+TABLE_PRODUCT;

			Cursor curser = db.rawQuery(query, null);
			//curser.moveToFirst();

			while (curser.moveToNext()) {
				ProductDTO DTO = new ProductDTO();
				DTO.setProduct_id(""+curser.getInt(0));
				DTO.setProduct_name(curser.getString(1));
				list.add(DTO);
			}
			curser.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			closeDatabase(db);
		}

		return list;
	}

	
	public ArrayList<ResultDTO> getUserRates()
	{
		ArrayList<ResultDTO> list = new ArrayList<ResultDTO>();
		try {
			db=open();
			
			String query="select pk_product_id as 'id',product_name 'name' from productm";
			

			Cursor curser = db.rawQuery(query, null);
			//curser.moveToFirst();
			//int cnt=0;
			while (curser.moveToNext()) {
				ResultDTO DTO = new ResultDTO();
				int _id =curser.getInt(curser.getColumnIndex("id"));
				DTO.setResult_id(_id);
				DTO.setProduct_name(""+curser.getString(curser.getColumnIndex("name")));
				
				String queryMTD="SELECT avg(rate) as 'mtd' FROM rating, productm where rating.fk_product_id=productm.pk_product_id " +
						"and rating.fk_product_id="+_id+" and dt_rate_on BETWEEN datetime('now', 'start of month') AND datetime('now', 'localtime')";
				Cursor curserMTD = db.rawQuery(queryMTD, null);
				if(curserMTD.moveToNext())
				{
					DTO.setAvg_mtd_rate(curserMTD.getDouble(curserMTD.getColumnIndex("mtd")));	
				}else{
					DTO.setAvg_mtd_rate(0.0);
				}
				
				String queryYTD="SELECT avg(rate) as 'ytd' FROM rating, productm where rating.fk_product_id=productm.pk_product_id " +
						"and rating.fk_product_id="+_id+" and dt_rate_on BETWEEN datetime('now', 'start of year') AND datetime('now', 'localtime')";
				
				Cursor curserYTD = db.rawQuery(queryYTD, null);
				
				if(curserYTD.moveToNext())
				{
					DTO.setAvg_ytd_rate(curserYTD.getDouble(curserYTD.getColumnIndex("ytd")));	
				}else{
					DTO.setAvg_ytd_rate(0.0);
				}	
				list.add(DTO);
				curserMTD.close();
				curserYTD.close();
			}
			curser.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			closeDatabase(db);
		}

		return list;
	}

	public int isProductExist() {
		int count=0;
		try {
			db = open();
			String query = "select count(1) as 'count' from "+TABLE_PRODUCT;
			Cursor cursor = db.rawQuery(query, null);
			while (cursor.moveToNext()) {
				count = count+ cursor.getInt(cursor.getColumnIndex("count"));
			}
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDatabase(db);
		}
		return count;
	}
}
