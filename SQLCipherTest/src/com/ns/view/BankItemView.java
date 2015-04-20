package com.ns.view;

import com.db.ahibernate.annotation.Column;

public class BankItemView extends BaseItem{

	@Column(name = "BANK_CODE")
	private String	key;
	@Column(name = "BANK_NAME")
	private String	value;
	public BankItemView() {

	}
	public BankItemView(String key, String value) {
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
