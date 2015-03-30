package com.product_rate.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper{

	private static String TAG = "dbhelper";
	public static Context mContext;
	String mTable;
	public SQLiteDatabase db;

	public static final String DATABASE_NAME = "rateproduct.db";
	public static final int DATABASE_VERSION = 1;

	public static final String TABLE_USER = "userm";
	public static final String TABLE_PRODUCT = "productm";
	public static final String TABLE_RATE = "rating";
	
	private static final String CREATE_TABLE_LEARNER = "create table "
			+ TABLE_USER
			+ " (google_userid TEXT, tx_offlinephotopath TEXT, pk_userid INTEGER PRIMARY KEY, tx_name TEXT, tx_email TEXT, gender TEXT, dt_lastloggedon TEXT, tx_onlinephotopath TEXT, tx_birthday TEXT)";

	private static final String CREATE_TABLE_PRODUCT = "create table "
			+ TABLE_PRODUCT
			+ " (pk_product_id INTEGER PRIMARY KEY, product_name TEXT)";

	private static final String CREATE_TABLE_RATE = "create table "
			+ TABLE_RATE
			+ " (pk_rating_id INTEGER PRIMARY KEY, fk_user_id INTEGER, fk_product_id INTEGER, rate INTEGER, user_comment TEXT, dt_rate_on DATE)";
	
	
	private static final String DELETE_TABLE_USER = "delete from "
			+ TABLE_USER + ";";

	public DBhelper(Context _context) throws SQLException {
		super(_context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = _context;
		try {
			db=null;
			try{
				//db = open();
				closeDatabase(db);
				db=getWritableDatabase();
				db.setLockingEnabled(false);

				Cursor cursor = db.query("sqlite_master", new String[] { "name" },
						"type='table' and name='" + TABLE_USER + "'", null,
						null, null, null);
				int numRows = cursor.getCount();
				if (numRows < 1) {
					CreateDatabase(db);
				}
				cursor.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e1) {
			//Log.e("", "" + e1);
			e1.printStackTrace();
		}finally {
			closeDatabase(db);
		}
	}

	public void CreateDatabase(SQLiteDatabase db) {
		try {
				db.execSQL(CREATE_TABLE_LEARNER);
				db.execSQL(CREATE_TABLE_PRODUCT);
				db.execSQL(CREATE_TABLE_RATE);
			} catch (Exception e) {
			Log.e(TAG,""+ e.getMessage());
		}finally{
			//closeDatabase(db);
		}
	}
	
	/**
	 * Checks if table: <code>tableName</code> exists in database.
	 * 
	 * @param db
	 * @param tableName
	 * @return true if exist, false otherwise.
	 */
	boolean isTableExists(SQLiteDatabase db, String tableName) {
		if (tableName == null || db == null || !db.isOpen()) {
			return false;
		}
		Cursor cursor = db
				.rawQuery(
						"SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?",
						new String[] { "table", tableName });
		if (!cursor.moveToFirst()) {
			return false;
		}
		int count = cursor.getInt(0);
		cursor.close();
		return count > 0;
	}

	public void deleteDatabase(SQLiteDatabase db) {
		try {
			//db = open();
			db=getWritableDatabase();
			db.execSQL(DELETE_TABLE_USER);
		} catch (Exception e) {
			Log.e(TAG, ""+e.getMessage());
		} finally {
			closeDatabase(db);
		}
	}

	/**
	 * Open database connection
	 */
	public SQLiteDatabase open() {
		try {
			/*if(db!=null)
			if(db.isDbLockedByCurrentThread())*/
			//db = open();
			
			closeDatabase(db);
			
			db=getWritableDatabase();
			//Log.d(TAG, " db.open ");
		} catch (Exception e) {
			//Log.e(TAG,""+ e.getMessage());
			e.printStackTrace();
			try{
				db=getWritableDatabase();
			}catch (Exception e1) {
				// TODO: handle exception
			}
		}
		return db;
	}

	
	
	/**
	 * Close database connection
	 */
	public void closeDatabase(SQLiteDatabase db) {
		try {
			if(db!=null)
			{	if (db.isOpen())
					db.close();
				db.releaseReference();
			}
			//Log.d(TAG, "  db.close() ");
		} catch (Exception e) {
			//Log.e(TAG, e.getMessage());
			try {
				if (db.isOpen())
					db.close();
				db.releaseReference();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		CreateDatabase(db);					
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		deleteDatabase(db);
		onCreate(db);
	}
}
