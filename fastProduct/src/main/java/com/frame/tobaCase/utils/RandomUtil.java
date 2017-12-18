/**    
* @Title: RandomUtil.java
* @Package com.frame.tobaCase.utils
* @Description: 随机生成
* @author: Chensy
* @date 2017年02月22日 下午16:19:10
* @version V1.0
*/
package com.frame.tobaCase.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomUtil {
    /**
     * 随机生成5位数+时间
     * @return
     */
    public static String getRandomFileName() {
	SimpleDateFormat simpleDateFormat;
	simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
	Date date = new Date();
	String str = simpleDateFormat.format(date);
	Random random = new Random();
	int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
	return rannum + str;// 当前时间
    }

    /**
     * 
    * @Description: 生成 随机姓名 
    * @param @return
    * @author: liy
    * @date 2017年3月6日 上午11:44:11
    * @throws
     */
    public static String getRandomUserName() {
	String name = "";
	for (int i = 0; i < 5; i++) { // 获取随机5位 字符
	    int intValue = (int) (Math.random() * 26 + 97);
	    name = name + (char) intValue;
	}
	Random random = new Random();
	int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
	return name + rannum;
    }
}
