/**
 * @Title: DateTablesResult.java
 * @Package com.frame.core.commons.result
 * @Description: jquery dateTables插件返回工具类
 * @author: lzl
 * @date 2016年7月26日 下午3:32:22
 * @version V1.0
 */
package com.frame.core.commons.result;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.frame.core.commons.SysConstant;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class DateTablesResult<T> {
    
    /**
    * @ iTotalRecords : 过滤前总记录数
    */
    private long recordsTotal;
    
    /**
    * @ iTotalDisplayRecords :  过滤后总记录数
    */
    private long recordsFiltered;
    
    private int start = SysConstant.TABLE_DATA_START;// 起止位置'0'
    
    private int length = SysConstant.TABLE_DATA_LENGTH; // 数据长度'10'
    
    private int draw = 0 ;// 第几次请求
    
    private List<T> aaData;
    
    private List<String> orgs;
    
    public List<String> getOrgs(){
		return orgs;
	}

	public void setOrgs(List<String> orgs){
		this.orgs = orgs;
	}

	/**
    * @ condition : 查询条件
    */
    private Map<String,Object> condition = new HashMap<String,Object>();
    
    public DateTablesResult(){
    }
    
    @SuppressWarnings("rawtypes")
	public DateTablesResult(
        HttpServletRequest request){
    	
    	if (StringUtils.isNotBlank(request.getParameter("aoData"))) {  
            JSONArray jsonarray = JSONArray.fromObject(request  
                    .getParameter("aoData"));  
            for (int i = 0; i < jsonarray.size(); i++) {  
                JSONObject obj = (JSONObject) jsonarray.get(i);  
                if (obj.get("name").equals("sEcho"))  
                    this.setDraw(obj.getInt("value"));  
  
                if (obj.get("name").equals("iDisplayStart"))  
                    this.setStart(obj.getInt("value"));  
  
                if (obj.get("name").equals("iDisplayLength"))  
                    this.setLength(obj.getInt("value"));   
                if (obj.get("name").equals("iSortCol_0"))
               	 this.condition.put(obj.getString("name"),  
                        obj.get("value"));
                if (obj.get("name").equals("sSortDir_0"))
               	 this.condition.put(obj.getString("name"),  
                        obj.get("value"));
                if(obj.get("name").equals("searchDate")){       //获取前端自定义搜索参数
                	/*System.out.println(obj.getString("value"));*/
                	JSONObject jsonObject = JSONObject.fromObject(obj.get("value"));
                    Iterator iterator = jsonObject.keys();
                    while(iterator.hasNext()){
                    	String  key = (String) iterator.next();
                         String  value = jsonObject.getString(key);
                    	 this.condition.put(key, value);  
                    }             
                }            
            }  
        }     	
    }
    
    public long getRecordsTotal(){
        return recordsTotal;
    }
    
    public void setRecordsTotal(long recordsTotal){
        this.recordsTotal = recordsTotal;
    }
    
    public long getRecordsFiltered(){
        return recordsFiltered;
    }
    
    public void setRecordsFiltered(long recordsFiltered){
        this.recordsFiltered = recordsFiltered;
    }
    
    public int getDraw(){
        return draw;
    }
    
    public void setDraw(int draw){
        this.draw = draw;
    }
    
    public List<T> getAaData(){
        return aaData;
    }
    
    public void setAaData(List<T> aaData){
        this.aaData = aaData;
    }
    
    public int getStart(){
        return start;
    }
    
    public void setStart(int start){
        this.start = start;
    }
    
    public int getLength(){
        return length;
    }
    
    public void setLength(int length){
        this.length = length;
    }
    
    public Map<String,Object> getCondition(){
        return condition;
    }
    
    public void setCondition(Map<String,Object> condition){
        this.condition = condition;
    }
}
