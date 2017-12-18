/**    
* @Title: UUIDUtil.java
* @Package com.frame.core.commons.utils.UUIDUtil
* @Description: uuid工具类
* @author: shizh
* @date 2016年10月26日 下午2:31:52
* @version V1.0
*/
package com.frame.core.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDUtil {
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	public static String get64UUID() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String date = sdf.format(new Date());
		String uuid = UUID.fromString(UUID.randomUUID().toString()).toString();
		Integer unidLength = uuid.split("-").length;
		String unidStr = uuid.trim().replaceAll("-", "") + date + uuid.split("-")[2] + uuid.split("-")[1] + uuid.split("-")[unidLength-1];
		return unidStr;
	}
}
