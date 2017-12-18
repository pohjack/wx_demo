/**    
* @Title: ToJson.java
* @Package com.frame.biz.util.ToJson
* @Description: 对象转换json格式工具
* @author: chensy
* @date 2016年9月6日 下午5:31:52
* @version V1.0
*/
package com.frame.sys.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ToJson{
	/**
	 * 
	* @Description: 将list转成json数组
	* @param @param list 须转换值
	* @param @return JSONArray
	* @author: Chensy
	* @date 2016年9月6日 下午5:31:52
	* @throws
	 */
	public JSONArray outJsonArray(List<?> list) throws Exception{
		return JSONArray.fromObject(list);
	}

	/**
	 * 
	* @Description: 将Set转成json数组
	* @param @param set 须转换值
	* @param @return
	* @param @throws Exception
	* @author: Chensy
	* @date 2016年9月6日 下午5:36:21
	* @throws
	 */
	public JSONArray outJsonArray(Set<?> set) throws Exception{
		return JSONArray.fromObject(set);
	}

	/**
	 * 
	* @Description: 将object数组转成json数组
	* @param @param objArray 须转换值
	* @param @return
	* @param @throws Exception
	* @author: Chensy
	* @date 2016年9月6日 下午5:36:21
	* @throws
	 */
	public JSONArray outJsonArray(Object[] objArray) throws Exception{
		return JSONArray.fromObject(objArray);
	}

	/**
	 * 
	* @Description: 将map转成json对象
	* @param @param map 须转换值
	* @param @return JSONObject
	* @author: Chensy
	* @date 2016年9月6日 下午5:34:21
	* @throws
	 */
	public JSONObject outJson(Map<?, ?> map) throws Exception{
		return JSONObject.fromObject(map);
	}

	/**
	 * 
	* @Description: 将obj转成json对象
	* @param @param obj 须转换值
	* @param @return JSONObject
	* @author: Chensy
	* @date 2016年9月6日 下午5:34:21
	* @throws
	 */
	public JSONObject outJson(Object obj) throws Exception{
		return JSONObject.fromObject(obj);
	}
}
