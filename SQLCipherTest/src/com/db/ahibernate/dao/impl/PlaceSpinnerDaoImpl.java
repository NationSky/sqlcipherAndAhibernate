/**
 * 
 */
package com.db.ahibernate.dao.impl;

import java.util.List;

import android.content.Context;

import com.db.ahibernate.util.BaseDBHelper;
import com.ns.bean.Env;
import com.ns.bean.ItemView;
import com.ns.view.PlaceItemView;


/**
 * @author pfy
 * 
 */
public class PlaceSpinnerDaoImpl extends BaseDaoImpl<PlaceItemView> {

	private final static String dbName = "t_address.sqlite";
	private static String DBNAME = Env.DB_PATH + dbName;
	private static final Class<?>[] clazz = { ItemView.class };

	/**
	 * @param dbHelper
	 */
	public PlaceSpinnerDaoImpl(Context context) {
		super(BaseDBHelper.getBaseDBIntence(context, DBNAME, DBVERSION, clazz));
	}

	public List<PlaceItemView> getSpinnerContent(String selectionSql) {
		List<PlaceItemView> items = rawQuery(selectionSql, null);
		return items;
	}
	
}