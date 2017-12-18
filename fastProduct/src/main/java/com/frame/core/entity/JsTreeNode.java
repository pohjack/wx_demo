/**    
* @Title: TreeNode.java
* @Package com.frame.core.entity
* @Description: 树结构形数据实体
* @author: Shizh
* @date 2016年10月25日 上午9:49:55
* @version V1.0
*/
package com.frame.core.entity;

import java.util.ArrayList;

public class JsTreeNode{
	private String id;

	private String pId;

	private String name;


	public JsTreeNode(String id, String pId, String name) {
		super();
		this.id = id;
		/*if(parent != null) {
			this.parent = parent;
		}else{
			this.parent = "#";
			
		}*/
		this.pId = pId;
		this.name = name;
		
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






	
}
