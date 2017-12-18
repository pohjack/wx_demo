/**   
*     
* 类名称：DataSourceAdvice   
* 类描述：   
* 创建人：jekoll  
* 创建时间：2016年3月24日 下午6:47:10   
* 修改人：jekoll   
* 修改时间：2016年3月24日 下午6:47:10   
* 修改备注：   
* @version  v1.0 
*    
*/
package com.frame.core.commons.sqlUtils;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import com.frame.core.commons.Datasource;
import com.frame.core.commons.utils.PropertiesUtil;

public class DataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String isMultipleDataSource = PropertiesUtil.getInstance().getSysConfigProperty("isMultipleDataSource"); // 是否开启多数据源
		if ("1".equals(isMultipleDataSource)) {// 开启
			Datasource dataSource = method.getAnnotation(Datasource.class);
			String methodName = method.getName();
			if (dataSource != null) {// 添加了注解
				String source = dataSource.value();
				if ("master".equals(source)) {// 主数据库
					DataSourceHolder.setMaster();
				} else {// 从数据库
					DataSourceHolder.setSlave();
				}
			} else {
				// TODO 后续完善
			}
		}
	}

	@Override
	public void afterReturning(Object arg0, Method method, Object[] args, Object target) throws Throwable {

	}

}
