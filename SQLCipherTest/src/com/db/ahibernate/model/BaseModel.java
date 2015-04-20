/**
 * 
 */
package com.db.ahibernate.model;

import java.io.Serializable;

import com.db.ahibernate.annotation.Column;
import com.db.ahibernate.annotation.Id;


/**
 * @author jiang
 *定义所有原型基类  
 */
@SuppressWarnings("serial")
public class BaseModel implements Serializable{
   
	/**主建的定义 初始值为-1 请勿随意改变**/
	@Id
	@Column(name = "ID")
	private Long  id =-1L;

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

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

   
}
