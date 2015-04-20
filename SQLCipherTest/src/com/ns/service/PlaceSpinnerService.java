package com.ns.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.db.ahibernate.dao.impl.PlaceSpinnerDaoImpl;
import com.example.sqlciphertest.R;
import com.ns.cst.CodeTypeCst;
import com.ns.utils.LogUtils;
import com.ns.view.PlaceItemView;
import com.ns.view.Spinner;


public class PlaceSpinnerService extends GenericService
{

    // 从数据库中查询spinner的内容
    private PlaceSpinnerDaoImpl spinnerImpl;
    private Context context;

    /**
     * @param context
     */
    public PlaceSpinnerService(Context context)
    {
        super(context);
        spinnerImpl = new PlaceSpinnerDaoImpl(context);
        this.context = context;
    }
    
    /**
     * 设置spinner的item
     * @param spinner
     * @param list
     */

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void setSpinnerContent(Spinner spinner,String codeName,String upPlaceCode)
    {
        List<PlaceItemView> list = null;
        String sql = null;
        //省
        if(codeName.equals(CodeTypeCst.PROVINCE)){
            sql = "select place_code,place_name from t_address where place_type = '"+codeName+"' order by place_code asc"; 
            //市/县
        }else if(codeName.equals(CodeTypeCst.CITY) || codeName.equals(CodeTypeCst.COUNTY)){
            if(isNotNull(upPlaceCode)){
                sql = "select place_code,place_name from t_address where place_type = '"+codeName+"' and up_place_code = '"+upPlaceCode+"' order by place_code asc"; 
            }
        }else{
            LogUtils.logDebug(PlaceSpinnerService.class, "传入的代码有误");
        }
       
        LogUtils.logDebug(PlaceSpinnerService.class, "place spinner sql "+sql);
        list = getSpinnerContent(sql);
        
        ArrayAdapter adapter = new ArrayAdapter(context, R.layout.simple_spinner_item, list);
        spinner.setAdapter(adapter);
    }
    
    public String findPlaceFullName(String codeName,String code,String upPlaceCode)
    {
        List<PlaceItemView> list = null;
        String sql = null;
        //省
        if(codeName.equals(CodeTypeCst.PROVINCE)){
            sql = "select place_code,place_name from t_address where place_type = '"+codeName+"' and  place_code='"+code+"' order by place_code asc"; 
            //市/县
        }else if(codeName.equals(CodeTypeCst.CITY) || codeName.equals(CodeTypeCst.COUNTY)){
            if(isNotNull(upPlaceCode)){
                sql = "select place_code,place_name from t_address where place_type = '"+codeName+"'  and up_place_code = '"+upPlaceCode+"' and  place_code='"+code+"' order by place_code asc"; 
            }
        }else{
            LogUtils.logDebug(PlaceSpinnerService.class, "传入的代码有误");
        }
       
        list = spinnerImpl.getSpinnerContent(sql);
        if(list!=null&&!list.isEmpty()){
            return list.get(0).getValue();
        }
        return  "";
    }
    /**
     * 提供spinner的list
     * @return
     */
    public List<PlaceItemView> getSpinnerContent(String selectionSql) {

        List<PlaceItemView> items = null;
        if(null != selectionSql){
            items =  spinnerImpl.getSpinnerContent(selectionSql);
        }
        
        if(null == items){
            items = new ArrayList<PlaceItemView>();
        }
        PlaceItemView fristItem = new PlaceItemView();
        fristItem.setKey("");
        fristItem.setValue("请选择...");
        items.add(0, fristItem);
        
        return items;
    }
}
