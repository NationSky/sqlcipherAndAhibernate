package com.ns.service;

import android.content.Context;

public class GenericService {

	public Context context;
	
	public GenericService(Context context){
		
		this.context = context;
	}
	
	/**
	 * ¼ì²é×Ö·û´®ÊÇ·ñÎª¿Õ
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
