/**    
* @Title: ExcelAnnotation.java
* @Package com.frame.tobaCase.utils
* @Description: 用于Excel导出时给每个pojo对象的字段添加字段名称，作为excel的表头
* @author: shizh
* @date 2017年3月4日 上午10:41:02
* @version V1.0
*/
package com.frame.tobaCase.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAnnotation {

    /**
     * 
    * @Description: excel导入导出时标题对应字段的名字，如果没有设置注解属性，将无法导入导出
    * @param @return
    * @author shizh
    * @date 2017年3月4日 上午11:10:00
    * @throws
     */
    public String exportName();
}
