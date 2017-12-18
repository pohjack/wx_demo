/**    
* @Title: DateUtil.java
* @Package com.frame.core.commons.utils.DateUtil
* @Description: 时间工具
* @author: Shizh
* @date 2016年9月18日 上午9:41:54
* @version V1.0
*/
package com.frame.core.commons.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfYearDay =  new SimpleDateFormat("yyyyMMddHHmmssSSS");
	/**
	 * 
	* @Description: 获取YYYY格式
	* @param @return
	* @author: Shizh
	* @date 2016年9月18日 下午2:48:42
	* @throws
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 
	* @Description: 获取YYYY-MM-DD格式
	* @param @return
	* @author: Shizh
	* @date 2016年9月18日 下午2:48:32
	* @throws
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 
	* @Description: 获取YYYYMMDD格式
	* @param @return
	* @author: Shizh
	* @date 2016年9月18日 下午2:48:20
	* @throws
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 
	* @Description: 获取YYYY-MM-DD HH:mm:ss格式
	* @param @return
	* @author: Shizh
	* @date 2016年9月18日 下午2:48:09
	* @throws
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * 
	* @Description: 获取yyyyMMddHHmmssSSS格式
	* @param @return
	* @author: Shizh
	* @date 2016年9月18日 下午2:47:15
	* @throws
	 */
	public static String getYearDay() {
		return sdfYearDay.format(new Date());
	}
	
	/**
	 * 
	* @Description: 日期比较，如果s>=e 返回true 否则返回false
	* @param @param s 日期1
	* @param @param e 日期2
	* @param @return
	* @author: Shizh
	* @date 2016年9月18日 下午2:50:03
	* @throws
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 
	* @Description: 格式化日期
	* @param @param date 要格式化的日期
	* @param @return
	* @author: Shizh
	* @date 2016年9月18日 下午2:50:36
	* @throws
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	* @Description: 校验日期是否合法
	* @param @param s 待校验数据
	* @param @return
	* @author: Shizh
	* @date 2016年9月18日 下午2:51:05
	* @throws
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	/**
	 * 
	* @Description: 获取两个日期相隔多少年
	* @param @param startTime 开始日期
	* @param @param endTime	结束日期
	* @param @return
	* @author: Shizh
	* @date 2016年9月18日 下午2:55:43
	* @throws
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
    /**
     * 
    * @Description: 获取两个日期之间相隔的天数
    * @param @param beginDateStr 开始时间
    * @param @param endDateStr	结束时间
    * @param @return
	* @author: Shizh
	* @date 2016年9月18日 下午2:56:18
    * @throws
	 */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
      
        return day;
    }
    
    /**
     * 
    * @Description: 得到n天之后的日期时间
    * @param @param days 天数
    * @param @return
    * @author: Shizh
    * @date 2016年9月18日 下午2:58:56
    * @throws
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 
    * @Description: 得到n天之后是周几
    * @param @param days 天数
    * @param @return
    * @author: Shizh
    * @date 2016年9月18日 下午2:59:36
    * @throws
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    
    /**
     * 
    * @Description: 按照指定格式，日期转字符串
    * @param @param date 日期
    * @param @param format 指定格式
    * @param @return
    * @author: Shizh
    * @date 2016年9月18日 下午3:07:08
    * @throws
     */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
    
	/**
	 * 
	* @Description: 获取指定时间距离当前是几天几小时几分几秒
	* @param @param StrDate 时间
	* @param @return
	* @author: Shizh
	* @date 2016年9月18日 下午3:08:40
	* @throws
	 */
	public static String getTimes(String StrDate){
		String resultTimes = "";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date now;
	    
	    try {
	    	now = new Date();
	    	java.util.Date date=df.parse(StrDate);
	    	long times = now.getTime()-date.getTime();
	    	long day  =  times/(24*60*60*1000);
	    	long hour = (times/(60*60*1000)-day*24);
	    	long min  = ((times/(60*1000))-day*24*60-hour*60);
	    	long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);
	        
	    	StringBuffer sb = new StringBuffer();
	    	if(day > 0){
	    		sb.append(day + "天");
	    	}
	    	if(hour>0 ){
	    		sb.append(hour+"小时前");
	    	} else if(min>0){
	    		sb.append(min+"分钟前");
	    	} else{
	    		sb.append(sec+"秒前");
	    	}
	    		
	    	resultTimes = sb.toString();
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    return resultTimes;
	}
	
	/**
	 * 
	* @Description:按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	* @param @param date
	* @param @return
	* @author: Shizh
	* @date 2016年9月18日 下午3:19:43
	* @throws
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 
	* @Description: 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	* @param @param date
	* @param @return
	* @author: Shizh
	* @date 2016年9月18日 下午3:20:51
	* @throws
	 */
	public static Date str2Date(String date){
		if(StringUtils.isNotEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
    public static void main(String[] args) {
    	System.out.println(getDays());
    	System.out.println(getTimes("2016-09-17 10:00:00"));
    }

    /**
	* @Description: 日期加减
	* @param @param option 加减年/月/日的日期
	* @param @param _date 传入的日期 yyyy-mm-dd格式
	* @param @param dateRange 需要加减的类型，day为日，month为月，year为年
	* @param @return
	* @author: lpy
	* @date 2017年3月8日 上午10:29:08
	* @throws
	 */
	public static String checkOptionDay(int option, String _date, String dateRange) {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cl = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = (Date) sdf.parse(_date);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        cl.setTime(date);  
        switch(dateRange){
			case "year":
				cl.add(Calendar.YEAR, option);  
				break;
			case "month":
			 	cl.add(Calendar.MONTH, option);  
				break;
			case "day":
			 	cl.add(Calendar.DAY_OF_MONTH, option);  
				break;
		}
        date = cl.getTime();  
        return sdf.format(date);  
    }  
	
	/**
	* @Description: 日期加减
	* @param @param option 加减年/月/日的日期
	* @param @param _date 传入日期
	* @param @param dateRange 需要加减的类型，day为日，month为月，year为年
	* @param @param dateFormat 设置的日期格式
	* @param @return
	* @author: lpy
	* @date 2017年3月8日 上午10:35:06
	* @throws
	 */
	public static String checkOptionDay(int option, String _date, String dateRange, String dateFormat) {  
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);  
        Calendar cl = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = (Date) sdf.parse(_date);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        cl.setTime(date);  
        if (option < 0) {  
            switch(dateRange){
				case "year":
					cl.add(Calendar.YEAR, -1);  
					break;
				case "month":
				 	cl.add(Calendar.MONTH, -1);  
					break;
				case "day":
				 	cl.add(Calendar.DAY_OF_MONTH, -1);  
					break;
			}
        } else if (option > 0) {  
        	switch(dateRange){
				case "year":
					cl.add(Calendar.YEAR, 1);  
					break;
				case "month":
				 	cl.add(Calendar.MONTH, 1);  
					break;
				case "day":
				 	cl.add(Calendar.DAY_OF_YEAR, -1);  
					break;
			}
        }
        date = cl.getTime();  
        return sdf.format(date);  
    }  
}
