package com.ns.service;

import android.content.Context;

public class GenericService {

	public Context context;
	
	public GenericService(Context context){
		
		this.context = context;
	}
	
	/**
	 * ����ַ����Ƿ�Ϊ��
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNotNull(String str) {

		if (null != str && !"".equals(str)) {

			return true;
		}
		return false;
	}

}
