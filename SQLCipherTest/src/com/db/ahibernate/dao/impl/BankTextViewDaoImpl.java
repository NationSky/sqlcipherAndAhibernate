package com.db.ahibernate.dao.impl;

import java.util.List;

import android.content.Context;

import com.db.ahibernate.util.BaseDBHelper;
import com.ns.bean.Env;
import com.ns.bean.ItemView;
import com.ns.view.BankItemView;

public class BankTextViewDaoImpl  extends BaseDaoImpl<BankItemView>{

	private final static String dbName = "t_bank.sqlite";
	private static String DBNAME = Env.DB_PATH + dbName;
	private static final Class<?>[] clazz = { ItemView.class };

	/**
	 * @param dbHelper
	 */
	public BankTextViewDaoImpl(Context context) {
		super(BaseDBHelper.getBaseDBIntence(context, DBNAME, DBVERSION, clazz));
	}

	public List<BankItemView> getTextViewContent(String selectionSql) {
		List<BankItemView> items = rawQuery(selectionSql, null);
		return items;
	}
	
}
