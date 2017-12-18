/**   
*     
* 类名称：ReflectionUtils   
* 类描述：   反射工具类.提供访问私有变量,获取泛型类型Class,<br>
* 提取集合中元素的属性, 转换字符串到对象等Util函数
* 创建人：jekoll  
* 创建时间：2016年3月20日 下午11:59:02   
* 修改人：jekoll   
* 修改时间：2016年3月20日 下午11:59:02   
* 修改备注：   
* @version  v1.0 
*    
*/
package com.frame.core.commons;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@SuppressWarnings("rawtypes")
public class ReflectionUtils {

	/**  
	* @Title: getClassGenricType
	* @Description: 获得参数化类型的泛型类型，取第一个参数的泛型类型，（默认取的第一个）
	* @param @param clazz 参数化类型
	* @param @return    泛型类型  
	* @return Class    
	* @throws  
	*/
	public static Class getClassGenricType(final Class clazz) {
		return getClassGenricType(clazz, 0);
	}

	/**  
	* @Title: getClassGenricType  
	* @Description: 根据参数索引获得参数化类型的泛型类型，（通过索引取得）  
	* @param @param clazz 数化类型
	* @param @param index 参数索引    
	* @return Class    泛型类型  
	* @throws  
	*/
	public static Class getClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
}
