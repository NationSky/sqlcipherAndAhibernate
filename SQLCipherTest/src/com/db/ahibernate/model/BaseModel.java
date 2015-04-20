/**
 * 
 */
package com.db.ahibernate.model;

import java.io.Serializable;

import com.db.ahibernate.annotation.Column;
import com.db.ahibernate.annotation.Id;


/**
 * @author jiang
 *��������ԭ�ͻ���  
 */
@SuppressWarnings("serial")
public class BaseModel implements Serializable{
   
	/**�����Ķ��� ��ʼֵΪ-1 ��������ı�**/
	@Id
	@Column(name = "ID")
	private Long  id =-1L;

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

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

   
}
