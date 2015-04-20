package com.ns.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.TextView;

import com.db.ahibernate.dao.impl.BankTextViewDaoImpl;
import com.ns.utils.LogUtils;
import com.ns.view.BankItemView;

public class BankTextViewService extends GenericService{


    // 从数据库中查询spinner的内容
    private BankTextViewDaoImpl textViewImpl;
    private Context context;

    /**
     * @param context
     */
    public BankTextViewService(Context context)
    {
        super(context);
        textViewImpl = new BankTextViewDaoImpl(context);
        this.context = context;
    }
    
    /**
     * 设置spinner的item
     * @param spinner
     * @param list
     */

    public void setTextViewContent(TextView textView, String bankCode)
    {
        List<BankItemView> list = null;
        String sql = null;
        sql = "select bank_name from t_bank where bank_code = '" + bankCode + "'"; 
       
        LogUtils.logDebug(PlaceSpinnerService.class, "bank sql "+sql);
        list = getTextContent(sql);
        
        textView.setText(list.get(0).getValue());
    }
    
    public String findPlaceFullName(String codeName,String code,String upPlaceCode)
    {
        List<BankItemView> list = null;
        String sql = "select place_code,place_name from t_address where place_type = '"+codeName+"'  and up_place_code = '"+upPlaceCode+"' and  place_code='"+code+"' order by place_code asc"; 
       
        list = textViewImpl.getTextViewContent(sql);
        if(list!=null&&!list.isEmpty()){
            return list.get(0).getValue();
        }
        return  "";
    }
    /**
     * 提供spinner的list
     * @return
     */
    public List<BankItemView> getTextContent(String selectionSql) {

        List<BankItemView> items = null;
        if(null != selectionSql){
            items =  textViewImpl.getTextViewContent(selectionSql);
        }
        
        if(null == items){
            items = new ArrayList<BankItemView>();
        }
        
        return items;
    }

}
