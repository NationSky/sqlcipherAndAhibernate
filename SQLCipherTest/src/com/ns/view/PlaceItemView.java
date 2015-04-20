package com.ns.view;

import com.db.ahibernate.annotation.Column;

/**
 * 地址spinner的条目内容
 * 
 * @author wll
 * 
 */
public class PlaceItemView extends BaseItem {

	@Column(name = "PLACE_CODE")
	private String	key;
	@Column(name = "PLACE_NAME")
	private String	value;
	public PlaceItemView() {

	}
	public PlaceItemView(String key, String value) {
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		return this.getValue();
	}
}