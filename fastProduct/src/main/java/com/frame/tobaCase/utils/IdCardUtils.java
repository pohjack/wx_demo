/**    
* @Title: IdCardUtils.java
* @Package com.frame.tobaCase.utils
* @Description: 身份证工具类
* @author: shizh
* @date 2017年3月8日 下午5:53:12
* @version V1.0
*/
package com.frame.tobaCase.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class IdCardUtils extends StringUtils {

    /** 中国公民身份证号码最小长度。 */
    public static final int CHINA_ID_MIN_LENGTH = 15;

    /** 中国公民身份证号码最大长度。 */
    public static final int CHINA_ID_MAX_LENGTH = 18;

    /** 省、直辖市代码表 */
    public static final String cityCode[] = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34",
	    "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64",
	    "65", "71", "81", "82", "91" };

    /** 每位加权因子 */
    public static final int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

    /** 第18位校检码 */
    public static final String verifyCode[] = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
    /** 最低年限 */
    public static final int MIN = 1930;
    public static Map<String, String> cityCodes = new HashMap<String, String>();
    /** 台湾身份首字母对应数字 */
    public static Map<String, Integer> twFirstCode = new HashMap<String, Integer>();
    /** 香港身份首字母对应数字 */
    public static Map<String, Integer> hkFirstCode = new HashMap<String, Integer>();
    static {
	cityCodes.put("11", "北京");
	cityCodes.put("12", "天津");
	cityCodes.put("13", "河北");
	cityCodes.put("14", "山西");
	cityCodes.put("15", "内蒙古");
	cityCodes.put("21", "辽宁");
	cityCodes.put("22", "吉林");
	cityCodes.put("23", "黑龙江");
	cityCodes.put("31", "上海");
	cityCodes.put("32", "江苏");
	cityCodes.put("33", "浙江");
	cityCodes.put("34", "安徽");
	cityCodes.put("35", "福建");
	cityCodes.put("36", "江西");
	cityCodes.put("37", "山东");
	cityCodes.put("41", "河南");
	cityCodes.put("42", "湖北");
	cityCodes.put("43", "湖南");
	cityCodes.put("44", "广东");
	cityCodes.put("45", "广西");
	cityCodes.put("46", "海南");
	cityCodes.put("50", "重庆");
	cityCodes.put("51", "四川");
	cityCodes.put("52", "贵州");
	cityCodes.put("53", "云南");
	cityCodes.put("54", "西藏");
	cityCodes.put("61", "陕西");
	cityCodes.put("62", "甘肃");
	cityCodes.put("63", "青海");
	cityCodes.put("64", "宁夏");
	cityCodes.put("65", "新疆");
	cityCodes.put("71", "台湾");
	cityCodes.put("81", "香港");
	cityCodes.put("82", "澳门");
	cityCodes.put("91", "国外");
	twFirstCode.put("A", 10);
	twFirstCode.put("B", 11);
	twFirstCode.put("C", 12);
	twFirstCode.put("D", 13);
	twFirstCode.put("E", 14);
	twFirstCode.put("F", 15);
	twFirstCode.put("G", 16);
	twFirstCode.put("H", 17);
	twFirstCode.put("J", 18);
	twFirstCode.put("K", 19);
	twFirstCode.put("L", 20);
	twFirstCode.put("M", 21);
	twFirstCode.put("N", 22);
	twFirstCode.put("P", 23);
	twFirstCode.put("Q", 24);
	twFirstCode.put("R", 25);
	twFirstCode.put("S", 26);
	twFirstCode.put("T", 27);
	twFirstCode.put("U", 28);
	twFirstCode.put("V", 29);
	twFirstCode.put("X", 30);
	twFirstCode.put("Y", 31);
	twFirstCode.put("W", 32);
	twFirstCode.put("Z", 33);
	twFirstCode.put("I", 34);
	twFirstCode.put("O", 35);
	hkFirstCode.put("A", 1);
	hkFirstCode.put("B", 2);
	hkFirstCode.put("C", 3);
	hkFirstCode.put("R", 18);
	hkFirstCode.put("U", 21);
	hkFirstCode.put("Z", 26);
	hkFirstCode.put("X", 24);
	hkFirstCode.put("W", 23);
	hkFirstCode.put("O", 15);
	hkFirstCode.put("N", 14);
    }

    /**
     * 
    * @Description: 将十五位身份证号码转换为十八位
    * @param @param idCard 十五位身份证号码
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:33:14
    * @throws
     */
    public static String conver15CardTo18(String idCard) {
	String idCard18 = "";
	if (idCard.length() != CHINA_ID_MIN_LENGTH) {
	    return null;
	}
	if (isNum(idCard)) {
	    // 获取出生年月日
	    String birthday = idCard.substring(6, 12);
	    Date birthDate = null;
	    try {
		birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
	    Calendar cal = Calendar.getInstance();
	    if (birthDate != null)
		cal.setTime(birthDate);
	    // 获取出生年(完全表现形式,如：2010)
	    String sYear = String.valueOf(cal.get(Calendar.YEAR));
	    idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
	    // 转换字符数组
	    char[] cArr = idCard18.toCharArray();
	    if (cArr != null) {
		int[] iCard = converCharToInt(cArr);
		int iSum17 = getPowerSum(iCard);
		// 获取校验位
		String sVal = getCheckCode18(iSum17);
		if (sVal.length() > 0) {
		    idCard18 += sVal;
		} else {
		    return null;
		}
	    }
	} else {
	    return null;
	}
	return idCard18;
    }

    /**
     * 
    * @Description: 验证身份证号码是否合法
    * @param @param idCard
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:34:46
    * @throws
     */
    public static boolean validateCard(String idCard) {
	String card = idCard.trim();
	if (validateIdCard18(card)) {// 18位身份证验证
	    return true;
	}
	if (validateIdCard15(card)) {// 15位身份证验证
	    return true;
	}
	String[] cardval = validateIdCard10(card);// 10位身份证验证
	if (cardval != null) {
	    if (cardval[2].equals("true")) {
		return true;
	    }
	}
	return false;
    }

    /**
     * 
    * @Description: 18位身份证合法性验证
    * 
    * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码
    * 是特征组合码，由十七位数字本体码和一位数字校验码组成。 
    * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。 
    * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码； 
    * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码； 
    * 6.第17位数字表示性别：奇数表示男性，偶数表示女性； 
    * 7.第18位数字是校检码：一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
    * 第十八位数字(校验码)的计算方法为：
    * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 
    * 2.将这17位数字和系数相乘的结果相加。 
    * 3.用加出来和除以11，看余数是多少 
    * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。 
    * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。 
    * @param @param idCard 身份证号码
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:36:12
    * @throws
     */
    public static boolean validateIdCard18(String idCard) {
	boolean bTrue = false;
	if (idCard.length() == CHINA_ID_MAX_LENGTH) {
	    // 前17位
	    String code17 = idCard.substring(0, 17);
	    // 第18位
	    String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
	    if (isNum(code17)) {
		char[] cArr = code17.toCharArray();
		if (cArr != null) {
		    int[] iCard = converCharToInt(cArr);
		    int iSum17 = getPowerSum(iCard);
		    // 获取校验位
		    String val = getCheckCode18(iSum17);
		    if (val.length() > 0) {
			if (val.equalsIgnoreCase(code18)) {
			    bTrue = true;
			}
		    }
		}
	    }
	}
	return bTrue;
    }

    /**
     * 
    * @Description: 15位身份证合法性验证
    * @param @param idCard
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:36:46
    * @throws
     */
    public static boolean validateIdCard15(String idCard) {
	if (idCard.length() != CHINA_ID_MIN_LENGTH) {
	    return false;
	}
	if (isNum(idCard)) {
	    String proCode = idCard.substring(0, 2);
	    if (cityCodes.get(proCode) == null) {
		return false;
	    }
	    String birthCode = idCard.substring(6, 12);
	    Date birthDate = null;
	    try {
		birthDate = new SimpleDateFormat("yy").parse(birthCode.substring(0, 2));
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
	    Calendar cal = Calendar.getInstance();
	    if (birthDate != null)
		cal.setTime(birthDate);
	    if (!valiDate(cal.get(Calendar.YEAR), Integer.valueOf(birthCode.substring(2, 4)),
		    Integer.valueOf(birthCode.substring(4, 6)))) {
		return false;
	    }
	} else {
	    return false;
	}
	return true;
    }

    /**
     * 
    * @Description: 验证10位身份编码是否合法
    * @param @param idCard 身份证号码
    * @param @return 返回身份证信息数组，[0] - 台湾、澳门、香港 [1] - 性别(男M,女F,未知N) [2] - 是否合法(合法true,不合法false)
    *    若不是身份证件号码则返回null
    * @author shizh
    * @date 2017年3月8日 下午5:41:31
    * @throws
     */
    public static String[] validateIdCard10(String idCard) {
	String[] info = new String[3];
	String card = idCard.replaceAll("[\\(|\\)]", "");
	if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
	    return null;
	}
	if (idCard.matches("^[a-zA-Z][0-9]{9}$")) { // 台湾
	    info[0] = "台湾";
	    System.out.println("11111");
	    String char2 = idCard.substring(1, 2);
	    if (char2.equals("1")) {
		info[1] = "M";
		System.out.println("MMMMMMM");
	    } else if (char2.equals("2")) {
		info[1] = "F";
		System.out.println("FFFFFFF");
	    } else {
		info[1] = "N";
		info[2] = "false";
		System.out.println("NNNN");
		return info;
	    }
	    info[2] = validateTWCard(idCard) ? "true" : "false";
	} else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$")) { // 澳门
	    info[0] = "澳门";
	    info[1] = "N";
	} else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) { // 香港
	    info[0] = "香港";
	    info[1] = "N";
	    info[2] = validateHKCard(idCard) ? "true" : "false";
	} else {
	    return null;
	}
	return info;
    }

    /**
     * 
    * @Description: 台湾身份证合法性验证
    * @param @param idCard
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:42:59
    * @throws
     */
    public static boolean validateTWCard(String idCard) {
	String start = idCard.substring(0, 1);
	String mid = idCard.substring(1, 9);
	String end = idCard.substring(9, 10);
	Integer iStart = twFirstCode.get(start);
	Integer sum = iStart / 10 + (iStart % 10) * 9;
	char[] chars = mid.toCharArray();
	Integer iflag = 8;
	for (char c : chars) {
	    sum = sum + Integer.valueOf(c + "") * iflag;
	    iflag--;
	}
	return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end) ? true : false;
    }

    /**
     * 
    * @Description: 香港身份证验证
    * @param @param idCard
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:43:36
    * @throws
     */
    public static boolean validateHKCard(String idCard) {
	String card = idCard.replaceAll("[\\(|\\)]", "");
	Integer sum = 0;
	if (card.length() == 9) {
	    sum = (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 9
		    + (Integer.valueOf(card.substring(1, 2).toUpperCase().toCharArray()[0]) - 55) * 8;
	    card = card.substring(1, 9);
	} else {
	    sum = 522 + (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 8;
	}
	String mid = card.substring(1, 7);
	String end = card.substring(7, 8);
	char[] chars = mid.toCharArray();
	Integer iflag = 7;
	for (char c : chars) {
	    sum = sum + Integer.valueOf(c + "") * iflag;
	    iflag--;
	}
	if (end.toUpperCase().equals("A")) {
	    sum = sum + 10;
	} else {
	    sum = sum + Integer.valueOf(end);
	}
	return (sum % 11 == 0) ? true : false;
    }

    /**
     * 
    * @Description: 将字符数组转换成数字数组
    * @param @param ca
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:43:54
    * @throws
     */
    public static int[] converCharToInt(char[] ca) {
	int len = ca.length;
	int[] iArr = new int[len];
	try {
	    for (int i = 0; i < len; i++) {
		iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
	    }
	} catch (NumberFormatException e) {
	    e.printStackTrace();
	}
	return iArr;
    }

    /**
     * 
    * @Description: 将身份证的每位和对应位的加权因子相乘之后，再得到和值
    * @param @param iArr
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:44:08
    * @throws
     */
    public static int getPowerSum(int[] iArr) {
	int iSum = 0;
	if (power.length == iArr.length) {
	    for (int i = 0; i < iArr.length; i++) {
		for (int j = 0; j < power.length; j++) {
		    if (i == j) {
			iSum = iSum + iArr[i] * power[j];
		    }
		}
	    }
	}
	return iSum;
    }

    /**
     * 
    * @Description: 将power和值与11取模获得余数进行校验码判断
    * @param @param iSum
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:44:23
    * @throws
     */
    public static String getCheckCode18(int iSum) {
	String sCode = "";
	switch (iSum % 11) {
	case 10:
	    sCode = "2";
	    break;
	case 9:
	    sCode = "3";
	    break;
	case 8:
	    sCode = "4";
	    break;
	case 7:
	    sCode = "5";
	    break;
	case 6:
	    sCode = "6";
	    break;
	case 5:
	    sCode = "7";
	    break;
	case 4:
	    sCode = "8";
	    break;
	case 3:
	    sCode = "9";
	    break;
	case 2:
	    sCode = "x";
	    break;
	case 1:
	    sCode = "0";
	    break;
	case 0:
	    sCode = "1";
	    break;
	}
	return sCode;
    }

    /**
     * 
    * @Description: 根据身份编号获取年龄
    * @param @param idCard
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:44:38
    * @throws
     */
    public static int getAgeByIdCard(String idCard) {
	int iAge = 0;
	if (idCard.length() == CHINA_ID_MIN_LENGTH) {
	    idCard = conver15CardTo18(idCard);
	}
	String year = idCard.substring(6, 10);
	Calendar cal = Calendar.getInstance();
	int iCurrYear = cal.get(Calendar.YEAR);
	iAge = iCurrYear - Integer.valueOf(year);
	return iAge;
    }

    /**
     * 
    * @Description: 根据身份编号获取生日(yyyyMMdd)
    * @param @param idCard
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:44:51
    * @throws
     */
    public static String getBirthByIdCard(String idCard) {
	Integer len = idCard.length();
	if (len < CHINA_ID_MIN_LENGTH) {
	    return null;
	} else if (len == CHINA_ID_MIN_LENGTH) {
	    idCard = conver15CardTo18(idCard);
	}
	return idCard.substring(6, 14);
    }

    /**
     * 
    * @Description: 根据身份编号获取生日年
    * @param @param idCard
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:45:20
    * @throws
     */
    public static Short getYearByIdCard(String idCard) {
	Integer len = idCard.length();
	if (len < CHINA_ID_MIN_LENGTH) {
	    return null;
	} else if (len == CHINA_ID_MIN_LENGTH) {
	    idCard = conver15CardTo18(idCard);
	}
	return Short.valueOf(idCard.substring(6, 10));
    }

    /**
     * 
    * @Description: 根据身份编号获取生日月
    * @param @param idCard
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:45:39
    * @throws
     */
    public static Short getMonthByIdCard(String idCard) {
	Integer len = idCard.length();
	if (len < CHINA_ID_MIN_LENGTH) {
	    return null;
	} else if (len == CHINA_ID_MIN_LENGTH) {
	    idCard = conver15CardTo18(idCard);
	}
	return Short.valueOf(idCard.substring(10, 12));
    }

    /**
     * 
    * @Description: 根据身份编号获取生日天
    * @param @param idCard
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:45:52
    * @throws
     */
    public static Short getDateByIdCard(String idCard) {
	Integer len = idCard.length();
	if (len < CHINA_ID_MIN_LENGTH) {
	    return null;
	} else if (len == CHINA_ID_MIN_LENGTH) {
	    idCard = conver15CardTo18(idCard);
	}
	return Short.valueOf(idCard.substring(12, 14));
    }

    /**
     * 
    * @Description: 根据身份编号获取性别
    * @param @param idCard
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:46:03
    * @throws
     */
    public static String getGenderByIdCard(String idCard) {
	String sGender = "N";
	if (idCard.length() == CHINA_ID_MIN_LENGTH) {
	    idCard = conver15CardTo18(idCard);
	}
	String sCardNum = idCard.substring(16, 17);
	if (Integer.parseInt(sCardNum) % 2 != 0) {
	    sGender = "M";
	} else {
	    sGender = "F";
	}
	return sGender;
    }

    /**
     * 
    * @Description: 根据身份编号获取户籍省份
    * @param @param idCard
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:46:16
    * @throws
     */
    public static String getProvinceByIdCard(String idCard) {
	int len = idCard.length();
	String sProvince = null;
	String sProvinNum = "";
	if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
	    sProvinNum = idCard.substring(0, 2);
	}
	sProvince = cityCodes.get(sProvinNum);
	return sProvince;
    }

    /**
     * 
    * @Description: 数字验证
    * @param @param val
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:54:02
    * @throws
     */
    public static boolean isNum(String val) {
	return val == null || "".equals(val) ? false : val.matches("^[0-9]*$");
    }

    /**
     * 
    * @Description: 验证小于当前日期 是否有效
    * @param @param iYear 待验证日期(年)
    * @param @param iMonth 待验证日期(月 1-12)
    * @param @param iDate 待验证日期(日)
    * @param @return
    * @author shizh
    * @date 2017年3月8日 下午5:54:15
    * @throws
     */
    public static boolean valiDate(int iYear, int iMonth, int iDate) {
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int datePerMonth;
	if (iYear < MIN || iYear >= year) {
	    return false;
	}
	if (iMonth < 1 || iMonth > 12) {
	    return false;
	}
	switch (iMonth) {
	case 4:
	case 6:
	case 9:
	case 11:
	    datePerMonth = 30;
	    break;
	case 2:
	    boolean dm = ((iYear % 4 == 0 && iYear % 100 != 0) || (iYear % 400 == 0)) && (iYear > MIN && iYear < year);
	    datePerMonth = dm ? 29 : 28;
	    break;
	default:
	    datePerMonth = 31;
	}
	return (iDate >= 1) && (iDate <= datePerMonth);
    }

    public static void main(String[] args) {
	String birthByIdCard = getBirthByIdCard("440520510830141");
	System.out.println(birthByIdCard);
    }
}
