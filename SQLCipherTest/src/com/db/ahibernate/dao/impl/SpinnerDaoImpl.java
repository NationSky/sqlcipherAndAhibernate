package com.db.ahibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.db.ahibernate.util.BaseDBHelper;
import com.ns.bean.Env;
import com.ns.bean.ItemView;


/**
 * @author pfy 创建数据库
 *
 */
public class SpinnerDaoImpl extends BaseDaoImpl<ItemView>
{

    private final static String dbName = "t_code.sqlite";

    private static String DBNAME = Env.DB_PATH + dbName;

    private static final Class<?>[] clazz = {ItemView.class};

    /**
     * @param dbHelper
     */
    public SpinnerDaoImpl(Context context)
    {
        super(BaseDBHelper.getBaseDBIntence(context, DBNAME, DBVERSION, clazz));
    }

    public List<ItemView> getContent(String selectionArgs)
    {

        String sql = "select * from t_code where code_type=" + "'" + selectionArgs + "'" + "order by code asc";
        List<ItemView> items = rawQuery(sql, null);

        return items;
    }
    public List<ItemView> getSpinnerContentOrder(String selectionArgs)
    {

        String sql = "select code,code_name from t_code where code_type=" + "'" + selectionArgs + "'" + "order by sort asc";
        List<ItemView> items = rawQuery(sql, null);
        if(null == items)
        {
            items = new ArrayList<ItemView>();
        }

        ItemView fristItem = new ItemView();
        fristItem.setKey("");
        fristItem.setValue("请选择...");
        items.add(0, fristItem);

        return items;
    }

    /**
     * 查找关系的反向关系代码
     * 
     * @param selectionArgs
     * @param code
     * @return
     */
    public String getReverseCode(String selectionArgs, String code)
    {

        String sql = "select RESERVE from t_code where code_type=" + "'" + selectionArgs + "'" + " and CODE = '" + code + "'";
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try
        {

            db = dbHelper.getWritableDatabase();
            cursor = db.rawQuery(sql, null);

            if(cursor.moveToNext())
            {

                return cursor.getString(0);
            }
        }
        finally
        {

            if(cursor != null)
            {
                cursor.close();
            }

            if(db != null)
            {
                db.close();
            }
        }

        return null;
    }

}
