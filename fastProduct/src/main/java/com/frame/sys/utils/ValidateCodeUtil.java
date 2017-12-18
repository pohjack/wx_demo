/**    
* @Title: ValidateCodeUtil.java
* @Package com.frame.sys.utils
* @Description: 验证码生成器类，可生成数字、大写、小写字母及三者混合类型的验证码。
* 支持自定义验证码字符数量，图片大小，需排除的特殊字符，干扰线的数量以及图文颜色
* @author: Shizh
* @date 2016年11月1日 上午10:17:47
* @version V1.0
*/
package com.frame.sys.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ValidateCodeUtil{
	/**
	 * 验证码类型为仅数字 0~9
	 */
	public static final int TYPE_NUM_ONLY = 0;

	/**
	 * 验证码类型为仅字母，即大写、小写字母混合
	 */
	public static final int TYPE_LETTER_ONLY = 1;

	/**
	 * 验证码类型为数字、大写字母、小写字母混合
	 */
	public static final int TYPE_ALL_MIXED = 2;

	/**
	 * 验证码类型为数字、大写字母混合
	 */
	public static final int TYPE_NUM_UPPER = 3;

	/**
	 * 验证码类型为数字、小写字母混合
	 */
	public static final int TYPE_NUM_LOWER = 4;

	/**
	 * 验证码类型为仅大写字母
	 */
	public static final int TYPE_UPPER_ONLY = 5;

	/**
	 * 验证码类型为仅小写字母
	 */
	public static final int TYPE_LOWER_ONLY = 6;

	private ValidateCodeUtil(){
	}

	/**
	 * 
	* @Description: 生成验证码字符串
	* @param @param type 验证码类型，参见本类的静态属性
	* @param @param length  验证码长度，大于0的整数
	* @param @param exChars 需排除的特殊字符（仅对数字、字母混合型验证码有效，无需排除则为null）
	* @param @return 验证码字符串
	* @author: Shizh
	* @date 2016年11月1日 上午10:20:16
	* @throws
	 */
	public static String generateTextCode(int type, int length, String exChars){
		if(length <= 0)
			return "";
		StringBuffer code = new StringBuffer();
		int i = 0;
		Random r = new Random();
		switch(type){
			// 仅数字
			case TYPE_NUM_ONLY:
				while(i < length){
					int t = r.nextInt(10);
					// 不显示数字0、1
					if(t != 48 && t != 49){
						if(exChars == null || exChars.indexOf(t + "") < 0){// 排除特殊字符
							code.append(t);
							i++;
						}
					}
				}
				break;
			// 仅字母（即大写字母、小写字母混合）
			case TYPE_LETTER_ONLY:
				while(i < length){
					int t = r.nextInt(123);
					if(t != 73 && t != 79 && t != 105 && t != 108 && t != 111){
						if((t >= 97 || (t >= 65 && t <= 90)) && (exChars == null || exChars.indexOf((char) t) < 0)){
							// 不显示小写i、o、l 数字0、1
							if(t != 105 && t != 108 && t != 111 && t != 48 && t != 49){
								code.append((char) t);
								i++;
							}
						}
					}
				}
				break;
			// 数字、大写字母、小写字母混合
			case TYPE_ALL_MIXED:
				while(i < length){
					int t = r.nextInt(123);
					if((t >= 97 || (t >= 65 && t <= 90) || (t >= 48 && t <= 57)) && (exChars == null || exChars.indexOf((char) t) < 0)){
						// 不显示大写I、O小写i、o、l 数字0、1
						if(t != 73 && t != 79 && t != 105 && t != 108 && t != 111 && t != 48 && t != 49){
							code.append((char) t);
							i++;
						}
					}
				}
				break;
			// 数字、大写字母混合
			case TYPE_NUM_UPPER:
				while(i < length){
					int t = r.nextInt(91);
					if((t >= 65 || (t >= 48 && t <= 57)) && (exChars == null || exChars.indexOf((char) t) < 0)){
						// 不显示大写I、O 数字0、1
						if(t != 73 && t != 79 && t != 48 && t != 49){
							code.append((char) t);
							i++;
						}
					}
				}
				break;
			// 数字、小写字母混合
			case TYPE_NUM_LOWER:
				while(i < length){
					int t = r.nextInt(123);
					if((t >= 97 || (t >= 48 && t <= 57)) && (exChars == null || exChars.indexOf((char) t) < 0)){
						// 不显示小写i、o、l 数字0、1
						if(t != 105 && t != 108 && t != 111 && t != 48 && t != 49){
							code.append((char) t);
							i++;
						}
					}
				}
				break;
			// 仅大写字母
			case TYPE_UPPER_ONLY:
				while(i < length){
					int t = r.nextInt(91);
					if((t >= 65) && (exChars == null || exChars.indexOf((char) t) < 0)){
						// 不显示大写I、O
						if(t != 73 && t != 79){
							code.append((char) t);
							i++;
						}
					}
				}
				break;
			// 仅小写字母
			case TYPE_LOWER_ONLY:
				while(i < length){
					int t = r.nextInt(123);
					if((t >= 97) && (exChars == null || exChars.indexOf((char) t) < 0)){
						// 不显示小写i、o、l
						if(t != 105 && t != 108 && t != 111){
							code.append((char) t);
							i++;
						}
					}
				}
				break;
		}
		return code.toString();
	}

	/**
	 * 
	 * @Description: 已有验证码，生成验证码图片
	 * @param @param textCode  文本验证码
	 * @param @param width  图片宽度
	 * @param @param height 图片高度
	 * @param @param interLine 图片中干扰线的条数
	 * @param @param randomLocation 每个字符的高低位置是否随机
	 * @param @param backColor 图片颜色，若为null，则采用随机颜色
	 * @param @param foreColor 字体颜色，若为null，则采用随机颜色
	 * @param @param lineColor 干扰线颜色，若为null，则采用随机颜色
	 * @param @return 图片缓存对象
	 * @author: Shizh
	 * @date 2016年11月1日 上午10:21:16
	 * @throws
	 */
	public static BufferedImage generateImageCode(String textCode, int width, int height, int interLine, boolean randomLocation, Color backColor,
		Color foreColor, Color lineColor){
		BufferedImage bim = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bim.getGraphics();
		// 画背景图
		g.setColor(backColor == null ? getRandomColor() : backColor);
		g.fillRect(0, 0, width, height);
		// 画干扰线
		Random r = new Random();
		if(interLine > 0){
			int x = 0, y = 0, x1 = width, y1 = 0;
			for(int i = 0; i < interLine; i++){
				g.setColor(lineColor == null ? getRandomColor() : lineColor);
				y = r.nextInt(height);
				y1 = r.nextInt(height);
				g.drawLine(x, y, x1, y1);
			}
		}
		// 写验证码
		// g.setColor(getRandomColor());
		// g.setColor(isSimpleColor?Color.BLACK:Color.WHITE);
		// 字体大小为图片高度的80%
		int fsize = (int) (height * 0.55);
		int fx = -30;
		int fy = -5;
		g.setFont(new Font("Default", Font.PLAIN, fsize));
		g.translate(width / 2, height / 2);
		// 写验证码字符
		for(int i = 0; i < textCode.length(); i++){
			fy = randomLocation ? (int) ((Math.random() * 0.1) * height) : fy;// 每个字符高低是否随机
			g.setColor(foreColor == null ? getRandomColor() : foreColor);
			g.rotate((fx + 20) * (Math.PI - Math.random()) / 800);
			g.setPaint(foreColor);
			g.drawString(textCode.charAt(i) + "", fx, 0);
			fx += fsize * 1;// 字左右距离
		}
		g.dispose();
		return bim;
	}

	/**
	 * 
	* @Description: 生成图片验证码
	* @param @param type 验证码类型，参见本类的静态属性
	* @param @param length 验证码字符长度，大于0的整数
	* @param @param exChars 需排除的特殊字符
	* @param @param width 图片宽度
	* @param @param height 图片高度
	* @param @param interLine 图片中干扰线的条数
	* @param @param randomLocation 每个字符的高低位置是否随机
	* @param @param backColor 图片颜色，若为null，则采用随机颜色
	* @param @param foreColor 字体颜色，若为null，则采用随机颜色
	* @param @param lineColor 干扰线颜色，若为null，则采用随机颜色
	* @param @return 图片缓存对象
	* @author: Shizh
	* @date 2016年11月1日 上午10:22:48
	* @throws
	 */
	public static BufferedImage generateImageCode(int type, int length, String exChars, int width, int height, int interLine, boolean randomLocation,
		Color backColor, Color foreColor, Color lineColor){
		String textCode = generateTextCode(type, length, exChars);
		BufferedImage bim = generateImageCode(textCode, width, height, interLine, randomLocation, backColor, foreColor, lineColor);
		return bim;
	}

	/**
	 * 
	* @Description: 产生随机颜色
	* @param @return
	* @author: Shizh
	* @date 2016年11月1日 上午10:23:58
	* @throws
	 */
	private static Color getRandomColor(){
		Random r = new Random();
		Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		return c;
	}
}
