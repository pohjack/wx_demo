/**   
*     
* 类名称：DateConvert   
* 类描述：   
* 创建人：jekoll  
* 创建时间：2016年3月24日 下午9:32:33   
* 修改人：jekoll   
* 修改时间：2016年3月24日 下午9:32:33   
* 修改备注：   
* @version  v1.0 
*    
*/
package com.frame.core.commons.utils;

import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

public class DateConvert implements Converter{
    
    public Object convert(@SuppressWarnings("rawtypes") Class arg0, Object arg1){
        String p = (String) arg1;
        if ((p == null) || (p.trim ().length () == 0))
            return null;
        try{
            SimpleDateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
            return df.parse (p.trim ());
        }
        catch (Exception e){
        }
        return null;
    }
}
