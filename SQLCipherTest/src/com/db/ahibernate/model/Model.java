/**
 * 
 */
package com.db.ahibernate.model;

import java.io.Serializable;

import com.db.ahibernate.annotation.Column;

/**
 * 这是为支持字符串d  不支持自增  
 * @author jiang
 *定义所有原型基类  
 */
@SuppressWarnings("serial")
public class Model implements Serializable{
    
  
	@Column(name = "ID")
	private String  id = "";

	/**创建日期**/
	@Column(name = "CREATE_TIME")
	private String createTime;
	/**更新日期**/
	@Column(name = "UPDATE_TIME")
	private String  updateTime;
	

	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
