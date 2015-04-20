package com.db.ahibernate.dao.impl;

import java.lang.reflect.Field;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.db.ahibernate.annotation.Column;
import com.db.ahibernate.annotation.Id;
import com.db.ahibernate.annotation.Table;
import com.db.ahibernate.dao.BaseDao;
import com.db.ahibernate.util.TableHelper;
import com.ns.utils.LogUtils;
import com.ns.utils.StringUtils;

/**
 * AHibernate��Ҫ
 * (һ)֧�ֹ���: 1.�Զ�����,֧���������Լ̳���:�ɸ���ע���Զ���ɽ���,���Ҷ��ڼ̳����е�ע���ֶ�Ҳ֧���Զ�����. 
 * 2.�Զ�֧����ɾ��,����֧�ֶ��󻯲���:��ɾ�������ݿ�������������Ԫ,�����ظ�д��Щ��ɾ�ĵĴ���,
 * 	   �������Ӻ͸���֧��������hibernate�еĶ��󻯲���.
 * 3.��ѯ��ʽ���:֧��android����ṩ�ķ�ʽ,Ҳ֧��ԭ��sql��ʽ.
 * 4.��ѯ�������:���ڲ�ѯ������Զ���װΪʵ�����,������hibernate���.
 * 5.��ѯ������:��ѯ���֧�ֶ���,Ҳ֧�ֽ��ΪList<Map<String,String>>��ʽ,���������ʵ����Ŀ�к�ʵ��,��Ч�ʸ���Щ.
 * 6.��־����ϸ:��Ϊandroid������֧���Ȳ������,���б���ʱ�ɸ�����־����λ����,�������Լ�������Android�Ĵ���. 
 * (��)����֮��:
 * 1.id��ʱֻ֧��int����,��֧��uuid,��sqlite�в�������uuid.
 * 2.����ÿ���������Լ������͹ر�����,��ʱ����֧����һ�����������������Ȼ��ͳһ�ύ����.
 */
/**
 * ʵ�����ݿ������ɾ�ķ����� ����ahibernate
 * 
 * @author pfy
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T>
{
    private String TAG = "AHibernate";

    public SQLiteOpenHelper dbHelper;

    protected String tableName;

    private String idColumn;

    private Class<T> clazz;

    private List<Field> allFields;

    public static final int DBVERSION = 3;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl(SQLiteOpenHelper dbHelper)
    {
        this.dbHelper = dbHelper;

        this.clazz = ((Class<T>)((java.lang.reflect.ParameterizedType)super.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);

        if(this.clazz.isAnnotationPresent(Table.class))
        {
            Table table = (Table)this.clazz.getAnnotation(Table.class);
            this.tableName = table.name();
        }

        // ���������ֶ�

        Map<String, Field> map = new HashMap<String, Field>();
        for(Class<T> classes = (Class<T>)this.clazz; classes != Object.class; classes = (Class<T>)classes.getSuperclass())
        {
            try
            {
                TableHelper.joinMapFields(map, classes.getDeclaredFields());
            }
            catch(Exception e)
            {

            }
        }
        this.allFields = TableHelper.joinListFields(map);
        // �ҵ�����
        for(Field field: this.allFields)
        {
            if(field.isAnnotationPresent(Id.class))
            {
                Column column = (Column)field.getAnnotation(Column.class);
                this.idColumn = column.name();
                break;
            }
        }

    }

    public SQLiteOpenHelper getDbHelper()
    {
        return dbHelper;
    }

    public T get(int id)
    {

        String selection = this.idColumn + " = ?";
        String[] selectionArgs = {Integer.toString(id)};
         LogUtils.logDebug(TAG, "[get]: select * from " + this.tableName + " where " + this.idColumn + " = '" + id + "'");
        List<T> list = find(null, selection, selectionArgs, null, null, null, null);
        if((list != null) && (list.size() > 0))
        {
            return (T)list.get(0);
        }
        return null;
    }

    public T get(String id)
    {

        String selection = "ID" + " = ?";
        String[] selectionArgs = {id};
        List<T> list = find(null, selection, selectionArgs, null, null, null, null);
        if((list != null) && (list.size() > 0))
        {
            return (T)list.get(0);
        }
        return null;
    }

    public List<T> rawQuery(String sql, String[] selectionArgs)
    {

        List<T> list = new ArrayList<T>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try
        {
            db = this.dbHelper.getReadableDatabase();
            cursor = db.rawQuery(sql, selectionArgs);

            getListFromCursor(list, cursor);
        }
        catch(Exception e)
        {
            LogUtils.logError(this.TAG, "[rawQuery] from DB Exception.");
            e.printStackTrace();
            return null;
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

        return list;
    }

    public boolean isExist(String sql, String[] selectionArgs)
    {

        SQLiteDatabase db = null;
        Cursor cursor = null;
        try
        {
            db = this.dbHelper.getReadableDatabase();
            cursor = db.rawQuery(sql, selectionArgs);
             LogUtils.logDebug(TAG, "[cursor.getCount() ]: " + cursor.getCount());
            if(cursor.getCount() > 0)
            {
                cursor.getCount();
                return true;
            }
        }
        catch(Exception e)
        {
            LogUtils.logError(this.TAG, "[isExist] from DB Exception.");
            e.printStackTrace();
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
        return false;
    }

    public List<T> find()
    {
        return find(null, null, null, null, null, null, null);
    }

    public List<T> find(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
    {

        List<T> list = new ArrayList<T>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try
        {

            db = this.dbHelper.getReadableDatabase();

            cursor = db.query(this.tableName, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

            getListFromCursor(list, cursor);
        }
        catch(Exception e)
        {
            LogUtils.logError(this.TAG, "[find] from DB Exception");

            e.printStackTrace();
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

        return list;
    }

    private void getListFromCursor(List<T> list, Cursor cursor) throws IllegalAccessException, InstantiationException
    {

        while(cursor.moveToNext())
        {
            T entity = this.clazz.newInstance();

            for(Field field: this.allFields)
            {
                Column column = null;
                if(field.isAnnotationPresent(Column.class))
                {
                    column = (Column)field.getAnnotation(Column.class);

                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();

                    int c = cursor.getColumnIndex(column.name());

                    if(c < 0)
                    {
                        continue; // ���������ѭ���¸�����ֵ
                    }
                    else if((Integer.TYPE == fieldType) || (Integer.class == fieldType))
                    {
                        field.set(entity, cursor.getInt(c));
                    }
                    else if(String.class == fieldType)
                    {
                        field.set(entity, cursor.getString(c));
                    }
                    else if((Long.TYPE == fieldType) || (Long.class == fieldType))
                    {
                        field.set(entity, Long.valueOf(cursor.getLong(c)));
                    }
                    else if((Float.TYPE == fieldType) || (Float.class == fieldType))
                    {
                        field.set(entity, Float.valueOf(cursor.getFloat(c)));
                    }
                    else if((Short.TYPE == fieldType) || (Short.class == fieldType))
                    {
                        field.set(entity, Short.valueOf(cursor.getShort(c)));
                    }
                    else if((Double.TYPE == fieldType) || (Double.class == fieldType))
                    {
                        field.set(entity, Double.valueOf(cursor.getDouble(c)));
                    }
                    else if(Blob.class == fieldType)
                    {
                        field.set(entity, cursor.getBlob(c));
                    }
                    else if(Character.TYPE == fieldType)
                    {
                        String fieldValue = cursor.getString(c);

                        if((fieldValue != null) && (fieldValue.length() > 0))
                        {
                            field.set(entity, Character.valueOf(fieldValue.charAt(0)));
                        }
                    }
                }
            }

            list.add((T)entity);
        }
    }
/**
 * ����-1��Ϊ������
 */
    public long insert(T entity)
    {
        SQLiteDatabase db = null;
        try
        {
            db = this.dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            setContentValues(entity, cv, "create");
            long row = db.insert(this.tableName, null, cv);
            return row;
        }
        catch(Exception e)
        {
             LogUtils.logDebug(this.TAG, "[insert] into DB Exception.");
            e.printStackTrace();
        }
        finally
        {
            if(db != null)
            {
                db.close();
            }
        }
        return -1L;
    }

    /**
     * �����ԣ��÷���ֵΪ1ʱ����ɾ���ɹ���Ϊ0ʱ��ɾ��ʧ��
     */
    public int delete(long id)
    { SQLiteDatabase db = this.dbHelper.getWritableDatabase();
    try
    {
        String sql = "delete from  " + this.tableName + " where ID=" + id ;
        db.execSQL(sql);

        return 1;
    }
    catch(Exception e)
    {
        LogUtils.logError(this.TAG, "[update] DB Exception.");
        e.printStackTrace();
        return 0;
    }
    finally
    {
        if(db != null)
            db.close();
    }}
/**
	���� 1 ��ʾɾ���ɹ�������0��ʾɾ��ʧ��
 * @param id
 * @return
 */
    public int delete(String id)
    {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        try
        {
            String sql = "delete from  " + this.tableName + " where ID='" + id + "'";
            db.execSQL(sql);

            return 1;
        }
        catch(Exception e)
        {
            LogUtils.logError(this.TAG, "[update] DB Exception.");
            e.printStackTrace();
            return 0;
        }
        finally
        {
            if(db != null)
                db.close();
        }
    }

    public void delete(Integer... ids)
    {

        if(ids.length > 0)
        {
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < ids.length; i++)
            {
                sb.append('?').append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            SQLiteDatabase db = this.dbHelper.getWritableDatabase();
            String sql = "delete from " + this.tableName + " where " + this.idColumn + " in (" + sb + ")";

            db.execSQL(sql, (Object[])ids);
            db.close();
        }
    }


    /**
     * 
     * return the number of rows affected
     */
    public boolean update(T entity)
    {
        SQLiteDatabase db = null;
        try
        {
            db = this.dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            setContentValues(entity, cv, "update");

            if(StringUtils.isNullOrEmpty(this.idColumn))
            {
                String value = cv.get("ID").toString();
                cv.remove("ID");
                 LogUtils.logDebug(TAG, "[update]: update " + this.tableName + " where  ID=" +value);
                if(db.update(this.tableName, cv, "ID='" + value + "'", null) > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                String where = this.idColumn + " = ?";
                int id = Integer.parseInt(cv.get(this.idColumn).toString());
                cv.remove(this.idColumn);


                String[] whereValue = {Integer.toString(id)};
                
                if(db.update(this.tableName, cv, where, whereValue) > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        catch(Exception e)
        {
             LogUtils.logDebug(this.TAG, "[update] DB Exception.");
            e.printStackTrace();
            return false;
        }
        finally
        {
            if(db != null)
                db.close();
        }
    }

    public void setContentValues(T entity, ContentValues cv, String type) throws IllegalAccessException
    {

        for(Field field: this.allFields)
        {
            if(!field.isAnnotationPresent(Column.class))
            {
                continue;
            }
            Column column = (Column)field.getAnnotation(Column.class);

            field.setAccessible(true);
            Object fieldValue = field.get(entity);
            if(fieldValue == null)
                continue;
            if(("create".equals(type)) && (field.isAnnotationPresent(Id.class)))
            {
                continue;
            }
            cv.put(column.name(), fieldValue.toString());
        }
    }

    /**
     * ����ѯ�Ľ������Ϊ��ֵ��map.
     * 
     * @param sql ��ѯsql
     * @param selectionArgs ����ֵ
     * @return ���ص�Map�е�keyȫ����Сд��ʽ.
     */
    public List<Map<String, String>> query2MapList(String sql, String[] selectionArgs)
    {
         LogUtils.logDebug(TAG, "[query2MapList]: " + sql);
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
        try
        {
            db = this.dbHelper.getReadableDatabase();
            cursor = db.rawQuery(sql, selectionArgs);
            while(cursor.moveToNext())
            {
                Map<String, String> map = new HashMap<String, String>();
                for(String columnName: cursor.getColumnNames())
                {
                    LogUtils.logDebug(BaseDaoImpl.class, "columnName>>>>"+columnName.toLowerCase());
                    map.put(columnName.toLowerCase(), cursor.getString(cursor.getColumnIndex(columnName)));
                }
                retList.add(map);
            }
        }
        catch(Exception e)
        {
            LogUtils.logError(TAG, "[query2MapList] from DB exception");
            e.printStackTrace();
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

        return retList;
    }

    /**
     * ��װִ��sql����.
     * 
     * @param sql
     * @param selectionArgs
     */
    public boolean execSql(String sql, Object[] selectionArgs)
    {
        SQLiteDatabase db = null;

        try
        {
            db = this.dbHelper.getWritableDatabase();
            if(selectionArgs == null)
            {
                db.execSQL(sql);
            }
            else
            {
                db.execSQL(sql, selectionArgs);
            }
        }
        catch(Exception e)
        {
            LogUtils.logError(TAG, "[execSql] DB exception.");
            e.printStackTrace();
            return false;
        }
        finally
        {
            if(db != null)
            {
                db.close();
            }
        }
        
        return true;
    }

    /**
     * ��ѯ���м�¼����
     */

    @Override
    public int findRecordTotalCount(String sql)
    {

        int count = 0;


        SQLiteDatabase db = null;
        Cursor cursor = null;
        try
        {

            db = this.dbHelper.getReadableDatabase();
            cursor = db.rawQuery(sql, null);

            if(cursor.moveToNext())
            {
                count = cursor.getInt(0);
            }

        }
        catch(Exception e)
        {

            LogUtils.logError(BaseDaoImpl.class, "[findRecordTotalCount] from DB exception");
            e.printStackTrace();
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
        return count;
    }
}