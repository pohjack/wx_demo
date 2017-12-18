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

public class TreeNode{
	private String id;

	private String pid;

	private String name;

	private String remark;
	
	private int sort;
	
	private int status;

	private ArrayList<TreeNode> children = new ArrayList<TreeNode>();
	
	public TreeNode(String id,String name, String pid, String remark, Integer sort, Integer status){
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.remark = remark;
		this.sort = sort;
		this.status = status;
	}
	
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getPid(){
		return pid;
	}

	public void setPid(String pid){
		this.pid = pid;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public ArrayList<TreeNode> getChildren(){
		return children;
	}

	public void setChildren(ArrayList<TreeNode> children){
		this.children = children;
	}
	
	public String toString() {
		if(remark != null) {
			return "{id:'" +id +"',pId:'" +pid +"',name:\"" +name +"\",remark:'" +remark +"',sort:" +sort +",status:" +status  +"}";
		} else {
			return "{id:'" +id +"',pId:'" +pid +"',name:\"" +name +"\",sort:" +sort +",status:" +status +"}";
		}
	}

	public int getSort(){
		return sort;
	}

	public void setSort(int sort){
		this.sort = sort;
	}

	public int getStatus(){
		return status;
	}

	public void setStatus(int status){
		this.status = status;
	}
	
}
