package com.product_rate.dialog;

import com.product_rate.common.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class CustomDailog {

	//Alert Dialog with 
	public static void displayDialog(int resId,int btnid,Context context)
		 {
			  AlertDialog.Builder builder = new AlertDialog.Builder(context);
			  
			  builder.setTitle(R.string.app_name)
			  		 .setMessage(resId)
			  		 .setCancelable(true)
			         .setNegativeButton(btnid, new DialogInterface.OnClickListener() {
			             public void onClick(DialogInterface dialog, int id) {
			            	 dialog.dismiss();
			             }
			         });
			 builder.show();
		 }
	
	public static void displayDialog(String message,Context context)
	 {
		  AlertDialog.Builder builder = new AlertDialog.Builder(context);
		  
		  builder.setTitle(R.string.app_name)
		  		 .setMessage(message)
		  		 .setCancelable(true)
		         .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
		             public void onClick(DialogInterface dialog, int id) {
		            	 dialog.dismiss();
		             }
		         });
		 builder.show();
	 }	
}
