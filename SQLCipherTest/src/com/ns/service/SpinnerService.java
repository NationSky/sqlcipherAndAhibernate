package com.ns.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.db.ahibernate.dao.impl.SpinnerDaoImpl;
import com.example.sqlciphertest.R;
import com.ns.bean.ItemView;
import com.ns.cst.CodeTypeCst;
import com.ns.cst.SexCst;
import com.ns.utils.LogUtils;


public class SpinnerService extends GenericService
{
    // 从数据库中查询spinner的内容
    private SpinnerDaoImpl spinnerImpl;

    private Context context;

    public boolean hasChoice = true;

    /**
     * @param context
     */
    public SpinnerService(Context context)
    {
        super(context);
        spinnerImpl = new SpinnerDaoImpl(context);
        this.context = context;
    }

    /**
     * 设置spinner的item,同时还可以删除一些不需要的条目
     * 
     * @param spinner
     * @param list
     */

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void hideItems(Spinner spinner, String codeName, String keys)
    {

        List<ItemView> list = getContent(codeName);

        if(isNotNull(keys) && null != list && list.size() > 0)
        {
            if(keys.contains(","))
            {
                String key[] = keys.split(",");
                for(String tag: key)
                {
                    for(int i = 0; i < list.size(); i++)
                    {
                        ItemView item = list.get(i);
                        LogUtils.logDebug(SpinnerService.class, "item.getKey() tag>>>" + tag);
                        if(tag.equals(item.getKey()))
                        {
                            LogUtils.logDebug(SpinnerService.class, "item.getKey()>>>" + item.getKey());
                            list.remove(i);
                            break;
                        }
                    }
                }

            }
            else
            {

                for(int i = 0; i < list.size(); i++)
                {

                    ItemView item = list.get(i);

                    if(keys.equals(item.getKey()))
                    {
                        list.remove(i);
                    }
                }
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(context, R.layout.simple_spinner_item, list);
        spinner.setAdapter(adapter);

    }

    /**
     * 设置spinner的item
     * 
     * @param spinner
     * @param list
     */

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void setSpinnerContent(Spinner spinner, String codeName)
    {
        List<ItemView> list = getContent(codeName);
        ArrayAdapter adapter = new ArrayAdapter(context, R.layout.simple_spinner_item, list);
        spinner.setAdapter(adapter);

    }

    public String findValueByKey(String key)
    {

        String selectionArgs = CodeTypeCst.VISIT_CONTENT;
        String value = "";
        if(isNotNull(key))
        {

            String sql = "select CODE_NAME from t_code where code_type=" + "'" + selectionArgs + "'" + " and ";

            String selection = new String(" ( ");

            String keys[] = key.split(",");
            for(int i = 0; i < keys.length; i++)
            {

                selection = selection + " CODE = '" + keys[i] + "' or";
            }
            selection = selection.substring(0, selection.length() - 2);
            selection = selection + " )";
            LogUtils.logDebug(SpinnerService.class, "selection>>" + selection);
            sql = sql + selection;
            LogUtils.logDebug(SpinnerService.class, "sql>>" + sql);
            List<ItemView> items = spinnerImpl.rawQuery(sql, null);

            if(items != null)
            {
                for(int i = 0; i < items.size(); i++)
                {
                    ItemView item = items.get(i);
                    value = value + "," + item.getValue();
                }
                value = value.substring(1);
            }
            else
            {

                value = value.equals("") ? "请选择..." : value;
            }
        }

        LogUtils.logDebug(SpinnerService.class, value);
        return value;
    }

    public String findCodeNameByKey(String selectionArgs, String key)
    {

        String value = "";
        if(isNotNull(key))
        {

            String sql = "select code_name from t_code where code_type='" +selectionArgs+ "'  and code='"+key+"' order by code asc";
            LogUtils.logDebug(SpinnerService.class, "sql>>"+sql);
            List<ItemView> items = spinnerImpl.rawQuery(sql, null);
            if(items != null && !items.isEmpty())
            {
                return items.get(0).getValue();
            }

        }
        return value;
    }

    /**
     * 提供spinner的list
     * 
     * @return
     */

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<ItemView> getContent(String codeName)
    {
        List<ItemView> list = spinnerImpl.getContent(codeName);
        if(list == null)
        {
            list = new ArrayList();
        }

        if(hasChoice)
        {

            ItemView item = new ItemView();
            item.setKey("");
            item.setValue("请选择...");
            list.add(0, item);
        }

        return list;
    }

    /**
     * 提供spinner的list
     * 
     * @return
     */
    public List<ItemView> getArrayListOrder(String codeName)
    {
        return spinnerImpl.getSpinnerContentOrder(codeName);
    }

    /**
     * 提供spinner的list
     * 
     * @return
     */
    public List<ItemView> getAccountContent(String codeName)
    {
        return spinnerImpl.getContent(codeName);
    }

    /**
     * 查找关系的反向关系代码
     * 
     * @param selectionArgs
     * @param code
     * @return
     */
    public String getReverseCode(String selectionArgs, String code, String sex)
    {

        String reverseCode = spinnerImpl.getReverseCode(selectionArgs, code);
        if(null == reverseCode)
        {
            return null;
        }

        String codeArray[] = reverseCode.split(":");

        if(codeArray.length == 1)
        {
            return reverseCode;
        }
        else
        {

            if(SexCst.FEMALE.equals(sex))
            {

                return codeArray[1];
            }
            else
            {

                return codeArray[0];
            }
        }
    }
}
