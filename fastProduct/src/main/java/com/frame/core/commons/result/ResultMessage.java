/**
 * @Title: ResultMessage.java
 * @Package com.frame.core.commons.result
 * @Description: 成功返回信息
 * @author: lzl
 * @date 2016年7月8日 下午3:56:59
 * @version V1.0
 */
package com.frame.core.commons.result;

import org.springframework.stereotype.Component;

@Component
public class ResultMessage{
	
    
    /**
     * ajax失败
     * @param msg 失败的消息
     * @return {Object}
     */
    public Object renderError(String msg){
        Result result = new Result();
        result.setMsg(msg);
        //将失败信息放入request中，便于日志和异常处理
       // request.setAttribute("fail", msg);
        return result;
    }
    
    /**
     * ajax成功
     * @return {Object}
     */
    public Object renderSuccess(){
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }
    
    /**
     * ajax成功
     * @param msg 消息
     * @return {Object}
     */
    public Object renderSuccess(String msg){
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }
    
    /**
     * ajax成功
     * @param obj 成功时的对象
     * @return {Object}
     */
    public Object renderSuccess(Object obj){
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(obj);
        return result;
    }
    
   /**
    * 
   * @Description: 操作数据库之后返回成功或失败的结果
   * @param @param obj
   * @param @return
   * @author: Shizh
   * @date 2016年10月27日 下午5:38:02
   * @throws
    */
    public Object renderSuccess(Integer obj){
        Result result = new Result();
        if(obj > 0){
        	result.setSuccess(true);
        }else{
        	result.setSuccess(false);
        }
        return result;
    }
}
