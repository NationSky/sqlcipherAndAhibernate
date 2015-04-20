package com.ns.view;

import android.content.Context;
import android.util.AttributeSet;
/**
 * 自定义spinner，提供了根据Key来选中特定的条目的方法
 * @author wll
 *
 */
public class Spinner extends android.widget.Spinner{
	
    public Spinner(Context context){
		super(context);
	}
	public Spinner(Context context,AttributeSet aSet)
	{
		super(context,aSet);
	}
	/**
	 * 根据Key来选中特定的条目的方法
	 * @param key
	 */
	public void setSelection(String key){
		try
		{
			for(int i =0; i < this.getCount();i ++)
			{
				Object item = this.getItemAtPosition(i);
				String itemKey = ((BaseItem)item).getKey();
				if((!(itemKey == null||itemKey.equals("")) && !(key == null || key.equals(""))) && itemKey.equals(key))
				{
					this.setSelection(i);
					break;
				}
					
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 获取spinner选中选项的值
	 * @return
	 */
	public String getValue(){
	    return ((BaseItem)getSelectedItem()).getKey();
	}
}
