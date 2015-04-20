package com.ns.view;

import android.content.Context;
import android.util.AttributeSet;
/**
 * �Զ���spinner���ṩ�˸���Key��ѡ���ض�����Ŀ�ķ���
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
	 * ����Key��ѡ���ض�����Ŀ�ķ���
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
	 * ��ȡspinnerѡ��ѡ���ֵ
	 * @return
	 */
	public String getValue(){
	    return ((BaseItem)getSelectedItem()).getKey();
	}
}
