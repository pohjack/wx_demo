/**   
*     
* 类名称：BeanUtil   
* 类描述：   
* 创建人：jekoll  
* 创建时间：2016年3月24日 下午9:36:38   
* 修改人：jekoll   
* 修改时间：2016年3月24日 下午9:36:38   
* 修改备注：   
* @version  v1.0 
*    
*/
package com.frame.core.commons.utils;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanUtil {
	private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

	public static boolean getBoolean(Object bean, String param) {
		return getBoolean(bean, param, false);
	}

	public static boolean getBoolean(Object bean, String param, boolean defaultValue) {
		Boolean beanValue = null;

		if (bean != null) {
			try {
				beanValue = (Boolean) PropertyUtils.getSimpleProperty(bean, param);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		if (beanValue == null) {
			return defaultValue;
		}

		return beanValue.booleanValue();
	}

	public static double getDouble(Object bean, String param) {
		return getDouble(bean, param, 0.0D);
	}

	public static double getDouble(Object bean, String param, double defaultValue) {
		Double beanValue = null;

		if (bean != null) {
			try {
				beanValue = (Double) PropertyUtils.getSimpleProperty(bean, param);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		if (beanValue == null) {
			return defaultValue;
		}

		return beanValue.doubleValue();
	}

	public static int getInteger(Object bean, String param) {
		return getInteger(bean, param, 0);
	}

	public static int getInteger(Object bean, String param, int defaultValue) {
		Integer beanValue = null;

		if (bean != null) {
			try {
				beanValue = (Integer) PropertyUtils.getSimpleProperty(bean, param);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		if (beanValue == null) {
			return defaultValue;
		}

		return beanValue.intValue();
	}

	public static long getLong(Object bean, String param) {
		return getLong(bean, param, 0L);
	}

	public static long getLong(Object bean, String param, long defaultValue) {
		Long beanValue = null;

		if (bean != null) {
			try {
				beanValue = (Long) PropertyUtils.getSimpleProperty(bean, param);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		if (beanValue == null) {
			return defaultValue;
		}

		return beanValue.longValue();
	}

	public static Object getObject(Object bean, String param) {
		Object beanValue = null;

		if (bean != null) {
			try {
				beanValue = PropertyUtils.getSimpleProperty(bean, param);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		return beanValue;
	}

	public static String getString(Object bean, String param) {
		return getString(bean, param, "");
	}

	public static String getString(Object bean, String param, String defaultValue) {
		String beanValue = null;

		if (bean != null) {
			try {
				beanValue = (String) PropertyUtils.getSimpleProperty(bean, param);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		if (beanValue == null) {
			return defaultValue;
		}

		return beanValue;
	}
}
