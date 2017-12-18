/**   
*     
* 类名称：BeanMapUtil   
* 类描述：   
* 创建人：jekoll  
* 创建时间：2016年3月24日 下午9:30:57   
* 修改人：jekoll   
* 修改时间：2016年3月24日 下午9:30:57   
* 修改备注：   
* @version  v1.0 
*    
*/
package com.frame.core.commons.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;


public class BeanMapUtil {
	public static Object Map2Bean(@SuppressWarnings("rawtypes") Class type, Map<String, Object> map)
			throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
		ConvertUtils.register(new DateConvert(), Date.class);

		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		Object obj = type.newInstance();

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

		for (int i = 0; i < propertyDescriptors.length; ++i) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!(map.containsKey(propertyName.toUpperCase())))
				continue;
			try {
				Object value = map.get(propertyName.toUpperCase());
				BeanUtils.setProperty(obj, propertyName, value);
			} catch (Exception e) {
			}
		}
		return obj;
	}

	public static Map<String, Object> bean2Map(Object bean)
			throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		@SuppressWarnings("rawtypes")
        Class type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; ++i) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!(propertyName.equals("class"))) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null)
					returnMap.put(propertyName, result);
				else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}
}
