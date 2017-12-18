/**   
*     
* 类名称：Datasource   
* 类描述：   多数据源切换注解,如syscongif.properties中isMultipleDataSource值为1生效
* 创建人：jekoll  
* 创建时间：2016年3月23日 下午8:10:39   
* 修改人：jekoll   
* 修改时间：2016年3月23日 下午8:10:39   
* 修改备注：   
* @version  v1.0 
*    
*/
package com.frame.core.commons;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Datasource {
	String value();
}
