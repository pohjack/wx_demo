/**    
* @Title: ObjectToData.java
* @Package com.excel.utils
* @Description: object转换
* @author: Chensy
* @date 2017年02月22日 下午10:59:010
* @version V1.0
*/
package com.frame.tobaCase.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONObject;

public class ObjectToData {
    /**
     * object转换对应对象并返回String
     * @param obj 转换的参数
     * @param dateType 转换日期类型
     * @return
     */
    public static String objToStr(Object obj, String dateType) throws Exception {
	if (obj != null) {
	    if (obj instanceof Integer) {
		return obj.toString();
	    } else if (obj instanceof Float) {
		return Float.parseFloat(obj.toString()) + "";
	    } else if (obj instanceof Double) {
		return Double.parseDouble(obj.toString()) + "";
	    } else if (obj instanceof Date) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateType);
		return sdf.format(obj);
	    } else {
		return obj.toString();
	    }
	}
	return "";
    }

    /**
     * obj转map
     * @param obj 对象
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objToMap(Object obj) throws Exception {
	if (obj == null)
	    return null;
	Map<String, Object> map = new HashMap<String, Object>();
	map = new BeanMap(obj);
	return map;
    }
}
