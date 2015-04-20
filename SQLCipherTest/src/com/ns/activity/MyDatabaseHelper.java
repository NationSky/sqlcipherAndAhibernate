package com.ns.activity;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabase.CursorFactory;
import net.sqlcipher.database.SQLiteOpenHelper;

@SuppressLint("SdCardPath")
public class MyDatabaseHelper extends SQLiteOpenHelper {

	public static final String CREATE_TABLE = "create table Book(name text, pages integer)";
	private static final String DB_NAME = "/mnt/sdcard/com.cnooc.appmanage/database/book";
	private static final int DB_VERSION = 1;

	@SuppressLint("SdCardPath")
	public MyDatabaseHelper(Context context) {
		this(context, DB_NAME, null, DB_VERSION);
		
		// 创建自定义路径
		File file = new File("/mnt/sdcard/com.cnooc.appmanage/database");
		if (!file.exists()) {
			file.mkdir();
		}
			
	}

	public MyDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
	}

}
