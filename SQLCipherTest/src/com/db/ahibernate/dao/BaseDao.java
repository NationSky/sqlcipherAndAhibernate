package com.db.ahibernate.dao;

import java.util.List;
import java.util.Map;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @author pfy
 * @param <T>
 *        数据库dao接口
 */
public interface BaseDao<T> {

	public SQLiteOpenHelper getDbHelper();

	public abstract long insert(T entity);

	public abstract int delete(long id);

	public abstract void delete(Integer... ids);

	public abstract boolean update(T entity);

	public abstract T get(int id);

	public abstract List<T> rawQuery(String sql, String[] selectionArgs);

	public abstract List<T> find();

	public abstract int findRecordTotalCount(String sql);

	public abstract List<T> find(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

	public abstract boolean isExist(String sql, String[] selectionArgs);

	/**
	 * 将查询的结果保存为名值对map.
	 * 
	 * @param sql
	 *        查询sql
	 * @param selectionArgs
	 *        参数值
	 * @return 返回的Map中的key全部是小写形式.
	 */

	public List<Map<String, String>> query2MapList(String sql, String[] selectionArgs);

	/**
	 * 封装执行sql代码.
	 * 
	 * @param sql
	 * @param selectionArgs
	 */
	public boolean execSql(String sql, Object[] selectionArgs);

}