package com.product_rate.model.user;

import com.product_rate.db.DBhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class SQLiteUserDAO extends DBhelper {

	public SQLiteUserDAO(Context _context) {
		// TODO Auto-generated constructor stub
		super(_context);
	}
	
	public boolean insertUser(UserDTO user) {
		try {
			db = open();

			//tx_offlinephotopath TEXT, pk_userid INTEGER PRIMARY KEY, tx_name TEXT, 
			//tx_email TEXT, gender TEXT, dt_lastloggedon TEXT, tx_onlinephotopath TEXT,
			//tx_birthday TEXT

			String query = "insert into "+TABLE_USER+" (google_userid , tx_offlinephotopath,tx_name," +
					"tx_email,gender,dt_lastloggedon,"
					+ "tx_onlinephotopath,tx_birthday"
					+")"       
					+ " values ('"
					+ user.google_userid
					+ "','"
					+ user.offlinephotopath
					+ "','"
					+ user.name
					+ "','"
					+ user.email
					+ "','"
					+ user.gender
					+ "','"
					+ user.dt_lastloggedon
					+ "','"
					+ user.onlinephotopath
					+ "','"
					+ user.birthday
					+ "')";
			
			Cursor cursor = db.rawQuery(query, null);
			cursor.moveToNext();
			cursor.close();
			closeDatabase(db);
		} catch (Exception e) {
			e.printStackTrace();
			closeDatabase(db);
		} finally {
			closeDatabase(db);
		}
		return false;
	}

	public UserDTO getUserDetails() {
		UserDTO userDTO = new UserDTO();
		if (db != null) {
			if (!db.isOpen()) {
				db = open();
			}
		} else {
			db = open();
		}
		//db = open();
		
		try {

			//google_userid TEXT, tx_offlinephotopath TEXT, pk_userid INTEGER PRIMARY KEY, tx_name TEXT, 
			//tx_email TEXT, gender TEXT, dt_lastloggedon TEXT, tx_onlinephotopath TEXT,
			//tx_birthday TEXT
			
			String sqlQuery = "select * from "+TABLE_USER;
			Cursor cursor = db.rawQuery(sqlQuery, null);
			while (cursor.moveToNext()) {
				userDTO.google_userid = cursor.getString(0);
				userDTO.offlinephotopath = cursor.getString(1);
				userDTO.userid = cursor.getString(2);
				userDTO.name= cursor.getString(3);
				userDTO.email = Integer.toString(cursor.getInt(4));
				userDTO.gender = cursor.getString(5);
				userDTO.dt_lastloggedon = cursor.getString(6);
				userDTO.onlinephotopath = cursor.getString(7);
				userDTO.birthday = cursor.getString(8);	
			}
			cursor.close();
		}catch (Exception e) {
			e.printStackTrace();
			Log.e("", "" + e.getMessage());
			closeDatabase(db);
		} finally {
			closeDatabase(db);
		}
		return userDTO;
	}

	public boolean updateUser(UserDTO user) {
		try {
			db = open();
			db.beginTransaction(); db.setLockingEnabled(false);

			ContentValues args = new ContentValues();
			args.put("tx_offlinephotopath", user.offlinephotopath);
			int rowsAffected = db.update(DBhelper.TABLE_USER, args,null, null);
			db.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("SQLiteUserDAO==>updateUser", "" + e.getMessage());
		} finally {
			if(db.inTransaction()) {
				db.endTransaction();
			}
			closeDatabase(db);
		}
		return false;
	}
}