/**
 * 
 */
package com.db.ahibernate.model;

import java.io.Serializable;

import com.db.ahibernate.annotation.Column;

/**
 * ����Ϊ֧���ַ���d  ��֧������  
 * @author jiang
 *��������ԭ�ͻ���  
 */
@SuppressWarnings("serial")
public class Model implements Serializable{
    
  
	@Column(name = "ID")
	private String  id = "";

	/**��������**/
	@Column(name = "CREATE_TIME")
	private String createTime;
	/**��������**/
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
