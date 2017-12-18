/**    
* @Title: ZtreeNode.java
* @Package com.frame.core.entity
* @Description: TODO(用一句话描述该文件做什么)
* @author: yuyf
* @date 2017年3月9日 下午3:52:57
* @version V1.0
*/
package com.frame.core.entity;

public class ZtreeNode {
	
	private String id;
    private String pId;
    private String name;
    private String code;
    
    
	public ZtreeNode(String id, String pId, String name, String code) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
