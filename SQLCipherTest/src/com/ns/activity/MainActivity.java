package com.ns.activity;

import com.example.sqlciphertest.R;
import com.ns.cst.CodeTypeCst;
import com.ns.service.BankTextViewService;
import com.ns.service.PlaceSpinnerService;
import com.ns.view.Spinner;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private SQLiteDatabase	db;
	private MyDatabaseHelper dbHelper; 
	private static String SECRET_KEY="99090978933";
	
    /**
     * 查询地址spinner的内容
     */
    private PlaceSpinnerService placeService;
    private BankTextViewService bankService;
    private Spinner homeCity;
	private TextView	bank_code;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SQLiteDatabase.loadLibs(this);
		dbHelper = new MyDatabaseHelper(this);
		placeService = new PlaceSpinnerService(this);
		bankService = new BankTextViewService(this);
		
		Button addData = (Button) findViewById(R.id.add_data);
		Button queryData = (Button) findViewById(R.id.query_data);
		Button cipher_sqlite = (Button) findViewById(R.id.cipher_sqlite);
		bank_code = (TextView)findViewById(R.id.bank_code);
		bankService.setTextViewContent(bank_code, "2201");
		initSpinner();
		addData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					db = dbHelper.getWritableDatabase(SECRET_KEY); //密钥若不一样，打不开数据库,记住要及时关闭数据库
					ContentValues values = new ContentValues();
					values.put("name", "达芬奇密码");
					values.put("pages", 566);
					long k = db.insert("Book", null, values);
					Log.e("pfy", "k-->" + k);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					db.close();
				}
				
			}
		});
		queryData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Cursor cursor = null;
				try {
					db = dbHelper.getWritableDatabase(SECRET_KEY);
					cursor = db.query("Book", null, null, null, null, null, null);
					if (cursor != null) {
						while (cursor.moveToNext()) {
							String name = cursor.getString(cursor.getColumnIndex("name"));
							int pages = cursor.getInt(cursor.getColumnIndex("pages"));
							Log.e("pfy", "book name is " + name);
							Log.e("pfy", "book pages is " + pages);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if(cursor != null)
					    cursor.close();
					if(db != null)
					    db.close();
				}
				
			}
		});
	}
	 /**
     * 填充每一个spinner的值
     */
    private void initSpinner()
    {
    	 homeCity = (Spinner)findViewById(R.id.customer_homeCity);
         placeService.setSpinnerContent(homeCity, CodeTypeCst.PROVINCE, null);
    }
}
