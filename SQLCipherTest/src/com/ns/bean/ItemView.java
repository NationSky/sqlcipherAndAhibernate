package com.ns.bean;

import com.db.ahibernate.annotation.Column;
import com.ns.view.BaseItem;


/**
 * spinner中每一个条目的内容
 * 
 * @author pfy
 * 
 */
public class ItemView extends BaseItem
{
    @Column(name = "CODE")
    private String key;

    @Column(name = "CODE_NAME")
    private String value;

    @Column(name = "RESERVE")
    private String reserve;

    public String getReserve()
    {
        return reserve;
    }

    public void setReserve(String reserve)
    {
        this.reserve = reserve;
    }

    public ItemView()
    {

    }

    public ItemView(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return this.getValue();
    }
}