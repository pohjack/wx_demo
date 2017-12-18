/**
 * @Title: Result.java
 * @Package com.frame.core.commons.result
 * @Description: 操作结果集
 * @author: lzl
 * @date 2016年7月7日 下午6:54:15
 * @version V1.0
 */
package com.frame.core.commons.result;

import java.io.Serializable;

public class Result implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /**
    * @ SUCCESS : 成功状态
    */
    public static final int SUCCESS = 1;
    
    /**
    * @ FAILURE : 失败状态
    */
    public static final int FAILURE = -1;
    
    /**
    * @ success : 成功
    */
    private boolean success = false;
    
    /**
    * @ msg : 返回消息
    */
    private String msg = "";
    
    /**
    * @ obj : 返回对象
    */
    private Object obj = null;
    
    public boolean isSuccess(){
        return success;
    }
    
    public void setSuccess(boolean success){
        this.success = success;
    }
    
    public String getMsg(){
        return msg;
    }
    
    public void setMsg(String msg){
        this.msg = msg;
    }
    
    public Object getObj(){
        return obj;
    }
    
    public void setObj(Object obj){
        this.obj = obj;
    }
}
