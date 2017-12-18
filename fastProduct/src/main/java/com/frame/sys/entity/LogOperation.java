/**    
* @Title: LogOperation.java
* @Package com.frame.core.entity
* @Description: 异常日志对象类
* @author: chensy
* @date 2016年10月13日 下午17:40:00
* @version V1.0
*/
package com.frame.sys.entity;

import java.util.HashMap;
import java.util.Map;

import com.frame.core.commons.utils.DateUtil;
import com.frame.core.entity.BizBaseEntity;

public class LogOperation extends BizBaseEntity{
    
	private static final long serialVersionUID = 1L;
	
	private String type;			//操作类型
    private String objOperation;	//操作对象
    private String objName;			//对象名称
    private String objKey;			//对象主键
    private String oldValue;		//原数据值
    private String newValue;		//操作数据
    private String operationUrl;   //操作路径
    
	public String getOperationUrl() {
		return operationUrl;
	}
	public void setOperationUrl(String operationUrl) {
		this.operationUrl = operationUrl;
	}
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getObjOperation(){
		return objOperation;
	}
	public void setObjOperation(String objOperation){
		this.objOperation = objOperation;
	}
	public String getObjName(){
		return objName;
	}
	public void setObjName(String objName){
		this.objName = objName;
	}
	public String getObjKey(){
		return objKey;
	}
	public void setObjKey(String objKey){
		this.objKey = objKey;
	}
	public String getOldValue(){
		return oldValue;
	}
	public void setOldValue(String oldValue){
		this.oldValue = oldValue;
	}
	public String getNewValue(){
		return newValue;
	}
	public void setNewValue(String newValue){
		this.newValue = newValue;
	}
	public Map<String, Object> getParameterMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.id);
		map.put("creator", this.creator);
		map.put("created", DateUtil.date2Str(this.created));
		map.put("type", this.type);
		map.put("objOperation", this.objOperation);
		map.put("objName", this.objName);
		map.put("objKey", this.objKey);
		map.put("oldValue", this.oldValue);
		map.put("newValue", this.newValue);
		return map;
	}
}
