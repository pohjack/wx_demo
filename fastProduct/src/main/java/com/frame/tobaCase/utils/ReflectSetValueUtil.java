package com.frame.tobaCase.utils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.frame.core.commons.utils.DateUtil;

public class ReflectSetValueUtil {
    // 通过map匹配对象给对象赋值
    public static Object reflectSetValue(Object obj, Map<String, Object> map) throws Exception {
	if (obj != null && map.size() != 0) {
	    String strName = java.lang.String.class.getName();
	    String intName = java.lang.Integer.class.getName();
	    String douName = java.lang.Double.class.getName();
	    String datName = java.util.Date.class.getName();
	    String type = "";
	    Field[] fields = obj.getClass().getDeclaredFields();
	    Set<String> keys = map.keySet();
	    for (String key : keys) {
		for (int i = 0; i < fields.length; i++) {
		    if (fields[i].getName().equals(key)) {
			Field field = obj.getClass().getDeclaredField(key);
			field.setAccessible(true);
			type = fields[i].getType().getName();
			if (type.equals(strName)) {
			    field.set(obj, map.get(key));
			    break;
			} else if (type.equals(intName) || type.equals("int")) {
			    int in = Integer.parseInt(map.get(key).toString());
			    field.setInt(obj, in);
			    break;
			    // key = key.substring(0, 1).toUpperCase() +
			    // key.substring(1);
			    // obj.getClass().getDeclaredMethod("set"+key,
			    // int.class).invoke(obj, new Integer(in));
			} else if (type.equals(douName) || type.equals("double")) {
			    double d = Double.parseDouble(map.get(key).toString());
			    field.setDouble(obj, d);
			    break;
			} else if (type.equals(datName)) {
			    Date date = DateUtil.str2Date(map.get(key).toString());
			    field.set(obj, date);
			    break;
			}
		    }
		}
	    }
	}
	return obj;
    }

}
