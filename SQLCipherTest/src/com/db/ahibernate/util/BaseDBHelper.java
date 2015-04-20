package com.db.ahibernate.util;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ns.bean.Env;
import com.ns.utils.LogUtils;

/**
 * @author pfy extends sqlLiteOpenHelper 数据库连接类
 */
public class BaseDBHelper extends SQLiteOpenHelper
{

    @SuppressWarnings("rawtypes")
    private static Class TAG_LOG = BaseDBHelper.class;
    
    /**
     * 返回BaseDBHelper实例
     * 
     * @param context
     * @param databaseName
     * @param databaseVersion
     * @param modelClasses
     * @return
     */
    
    private String dbName;

    public static BaseDBHelper getBaseDBIntence(Context context, String databaseName, int databaseVersion, Class<?>[] modelClasses)
    {
        LogUtils.logDebug(TAG_LOG, "databaseName  is:" + databaseName);
        if(!new File(databaseName).exists()){
            
            return null;
        }
        return new BaseDBHelper(context, databaseName, null, databaseVersion, modelClasses);
    }

    private BaseDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, Class<?>[] modelClasses)
    {
        super(context, databaseName, factory, databaseVersion);
        dbName = databaseName;
    }

    public void onCreate(SQLiteDatabase db)
    {
    	
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        
    	onCreate(db);
        //LogUtils.logError(this.getClass(), "tag--->" + "执行了onupgrade方法。 ");
        String onUpgradeSql = "CREATE TABLE if not exists [T_CUSTOMER_BANK] ([CUSTOMER_ID] VARCHAR(30) NOT NULL, " + "[BANK_CODE] VARCHAR(10) NOT NULL,  [BANK_ACC_NO] VARCHAR(30) NOT NULL, " + "[BANK_NAME] VARCHAR(60), [IS_VALID] VARCHAR(3) NOT NULL DEFAULT ('Y'));";
        try
        {
            if((Env.DB_PATH + Env.DEFAULT_DB_NAME).equals(dbName))
            {
                if(!new File(Env.DB_PATH+Env.DEFAULT_DB_NAME).exists()){
                    return;
                }
                
                db.execSQL(onUpgradeSql);
                LogUtils.logError(this.getClass(), "tag--->" + "新表创建了。 ");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
}