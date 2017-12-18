/**    
* @Title: ExcelUtil.java
* @Package com.frame.tobaCase.utils
* @Description: Excel导入导出工具类
* @author: shizh
* @date 2017年3月4日 上午10:38:37
* @version V1.0
*/
package com.frame.tobaCase.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.frame.core.commons.utils.DateUtil;
import com.frame.tobaCase.entity.CaseCigar;

public class ExcelUtil<T> {

    Class<T> clazz;

    public ExcelUtil(Class<T> clazz) {
	this.clazz = clazz;
    }

    public ExcelUtil() {
	super();
	// TODO Auto-generated constructor stub

    }

    /**
     * 
    * @Description: 从Excel导入，返回一个list集合
    * @param @param file Excel文件
    * @param @param startRow 标题开始行数
    * @param @param suffix 文件后缀
    * @param @param pattern 模板，如格式化时间模板等
    * @param @return
    * @param @throws Exception
    * @author shizh
    * @date 2017年3月4日 上午10:43:26
    * @throws
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Collection<T> importExcel(InputStream file, int startRow, String suffix, String... pattern)
	    throws Exception {
	Collection<T> dist = new ArrayList<T>();
	try {
	    /**
	     * 类反射得到调用方法
	     */
	    // 得到目标目标类的所有的字段列表
	    Field filed[] = clazz.getDeclaredFields();
	    // 将所有标有Annotation的字段，也就是允许导入数据的字段,放入到一个map中
	    Map fieldmap = new HashMap();
	    // 循环读取所有字段
	    for (int i = 0; i < filed.length; i++) {
		Field f = filed[i];
		// 得到单个字段上的Annotation
		ExcelAnnotation exa = f.getAnnotation(ExcelAnnotation.class);
		// 如果标识了Annotationd的话
		if (exa != null) {
		    // 构造标注了Annotation字段的Setter方法
		    String fieldname = f.getName();
		    String setMethodName = "set" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
		    // 构造需要调用的method
		    Method setMethod = clazz.getMethod(setMethodName, new Class[] { f.getType() });
		    // 将这个method以Annotaion的名字为key来存入
		    fieldmap.put(exa.exportName(), setMethod);
		}
	    }
	    /**
	     * excel的解析开始
	     */
	    // 得到工作表
	    Workbook book = null;
	    if (suffix.equals(".xls")) {
		book = new HSSFWorkbook(file);
	    } else if (suffix.equals(".xlsx")) {
		book = new XSSFWorkbook(file);
	    } else {
		System.out.println("文件格式有误");
	    }
	    // 得到第一页
	    Sheet sheet = book.getSheetAt(0);
	    // 得到第一张工作表的所有行
	    Iterator<Row> row = sheet.rowIterator();
	    int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();

	    /**
	     * 标题解析
	     */
	    // 得到第一行，也就是标题行
	    Row tempRow = null;
	    for (int r = 0; r < startRow; r++) {
		tempRow = row.next();
	    }
	    Row title = tempRow;
	    // 得到第一行的所有列
	    Iterator<Cell> cellTitle = title.cellIterator();
	    // 将标题的文字内容放入到一个map中。
	    Map titlemap = new HashMap();
	    // 从标题第一列开始
	    int i = 0;
	    // 循环标题所有的列
	    while (cellTitle.hasNext()) {
		Cell cell = cellTitle.next();
		String value = cell.getStringCellValue();
		value = value.trim();
		titlemap.put(i, value);
		i = i + 1;
	    }
	    /**
	     * 解析内容行
	     */
	    // 用来格式化日期的DateFormat
	    SimpleDateFormat sf;
	    if (pattern.length < 1) {
		sf = new SimpleDateFormat("yyyy/MM/dd");
	    } else
		sf = new SimpleDateFormat(pattern[0]);
	    while (row.hasNext()) {
		Row rown = row.next();// 标题下的第一行
		Iterator<Cell> cellbody = rown.cellIterator();// 获取行的所有列
		int cells = fieldmap.size();
		T tObject = clazz.newInstance();// 得到传入类的实例
		int k = 0, flag = 1;
		while (cellbody.hasNext()) {// 遍历一行的列
		    Cell cell = cellbody.next();
		    String titleString = (String) titlemap.get(k);// 得到此列的对应的标题
		    if (fieldmap.containsKey(titleString)) {// 如果这一列的标题和类中的某一列的Annotation相同，那么则调用此类的的set方法，进行设值
			Method setMethod = (Method) fieldmap.get(titleString);
			Type[] ts = setMethod.getGenericParameterTypes();// 得到setter方法的参数
			String xclass = ts[0].toString();// 只要一个参数
			// 判断参数类型
			try {
			    switch (cell.getCellType()) {
			    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
				if (xclass.equals("class java.lang.String")) {
				    if ((cell.getNumericCellValue() + "").indexOf(".") > 0) {
					setMethod.invoke(tObject, (cell.getNumericCellValue() + "").substring(0,
						(cell.getNumericCellValue() + "").lastIndexOf(".")));
				    }
				} else if (xclass.equals("class java.lang.Integer")) {
				    setMethod.invoke(tObject, (int) cell.getNumericCellValue());
				} else if (xclass.equals("int")) {
				    setMethod.invoke(tObject, (int) cell.getNumericCellValue());
				} else if (xclass.equals("class java.util.Date")) {
				    setMethod.invoke(tObject, cell.getDateCellValue());
				} else if (xclass.equals("double")) {
				    setMethod.invoke(tObject, cell.getNumericCellValue());
				}
				break;
			    case HSSFCell.CELL_TYPE_STRING: // 字符串
				if (xclass.equals("class java.lang.Integer")) {
				    setMethod.invoke(tObject, Integer.parseInt(cell.getStringCellValue()));
				} else if (xclass.equals("class java.lang.String")) {
				    setMethod.invoke(tObject, cell.getStringCellValue().trim());
				} else if (xclass.equals("int")) {
				    int temp = Integer.parseInt(cell.getStringCellValue());
				    setMethod.invoke(tObject, temp);
				} else if (xclass.equals("Date")) {

				} else if (xclass.equals("class java.lang.Double")) {
				    setMethod.invoke(tObject, Double.parseDouble(cell.getStringCellValue()));
				}
				break;
			    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
				Boolean boolname = true;
				if (cell.getStringCellValue().equals("否")) {
				    boolname = false;
				}
				setMethod.invoke(tObject, boolname);
				break;
			    case HSSFCell.CELL_TYPE_FORMULA: // 公式
				System.out.print(cell.getCellFormula() + "   ");
				break;
			    case HSSFCell.CELL_TYPE_BLANK: // 空值
				flag++;
				System.out.println(" ");
				break;
			    case HSSFCell.CELL_TYPE_ERROR: // 故障
				System.out.println(" ");
				break;
			    default:
				System.out.print("未知类型   ");
				break;
			    }
			} catch (Exception e) {// 转换出错
			    e.printStackTrace();

			}
		    }
		    // 下一列
		    k = k + 1;
		}
		if (flag >= cells)
		    continue;
		dist.add(tObject);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    // 将异常抛出去
	    throw e;
	}
	return dist;
    }

    // 格式化日期
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public HSSFCellStyle setCellStyle(HSSFCellStyle cellStyle) {
	cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
	cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
	cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
	cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
	return cellStyle;

    }

    public void exportcigarExcel(String title, String fileName, Map<String, Object> map, HttpServletResponse response)
	    throws IOException {

	OutputStream out = response.getOutputStream();// 取得输出流
	response.reset();// 清空输出流
	response.setContentType("application/ms-excel;charset=GB2312");
	response.setHeader("Content-disposition",
		"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1") + ".xls");
	// 声明一个工作薄
	try {
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    // 首先检查数据看是否是正确的
	    if (map == null || map.size() == 0 || title == null || out == null) {
		throw new Exception("传入的数据不对！");
	    }
	    // 获取有效数据
	    List<CaseCigar> ts = (List<CaseCigar>) map.get("cigarList");
	    String outDate = (String) map.get("outDate");
	    String row2 = (String) map.get("regi_no");
	    Date date = (Date) map.get("crime_date");
	    String dateTime = date == null ? "" : sdf.format(date);

	    HSSFSheet sheet = workbook.createSheet(title);
	    // 创建表头 5行
	    for (int i = 0; i < 5; i++) {
		HSSFRow titleRow = sheet.createRow(i);
		for (int j = 0; j < 9; j++) {
		    HSSFCell cellHead = titleRow.createCell(j);
		}
	    }

	    // 设置列宽
	    sheet.setColumnWidth(0, 900);
	    sheet.setColumnWidth(1, 5100);
	    sheet.setColumnWidth(2, 2100);
	    sheet.setColumnWidth(3, 2100);
	    sheet.setColumnWidth(4, 2814);
	    sheet.setColumnWidth(5, 2214);
	    sheet.setColumnWidth(6, 2250);
	    sheet.setColumnWidth(7, 2250);
	    sheet.setColumnWidth(8, 2514);
	    // 合并从第rowFrom行columnFrom列 到rowTo行columnTo的区域
	    Region region = new Region((short) 0, (short) 0, (short) 0, (short) 5);
	    sheet.addMergedRegion(region);
	    Region region2 = new Region((short) 1, (short) 0, (short) 1, (short) 5);
	    sheet.addMergedRegion(region2);
	    Region region3 = new Region((short) 2, (short) 0, (short) 4, (short) 0);
	    sheet.addMergedRegion(region3);
	    Region region4 = new Region((short) 2, (short) 1, (short) 4, (short) 1);
	    sheet.addMergedRegion(region4);
	    Region region5 = new Region((short) 2, (short) 2, (short) 2, (short) 3);
	    sheet.addMergedRegion(region5);
	    Region region6 = new Region((short) 2, (short) 4, (short) 2, (short) 5);
	    sheet.addMergedRegion(region6);
	    Region region7 = new Region((short) 3, (short) 2, (short) 3, (short) 3);
	    sheet.addMergedRegion(region7);
	    Region region8 = new Region((short) 3, (short) 4, (short) 3, (short) 5);
	    sheet.addMergedRegion(region8);
	    Region region9 = new Region((short) 3, (short) 6, (short) 3, (short) 8);
	    sheet.addMergedRegion(region9);
	    Region region10 = new Region((short) 3, (short) 6, (short) 4, (short) 6);
	    sheet.addMergedRegion(region10);
	    Region region11 = new Region((short) 3, (short) 7, (short) 4, (short) 7);
	    sheet.addMergedRegion(region11);
	    Region region12 = new Region((short) 3, (short) 8, (short) 4, (short) 8);
	    sheet.addMergedRegion(region12);
	    // 得到所有区域
	    sheet.getNumMergedRegions();
	    HSSFFont font = workbook.createFont();
	    font.setFontName("宋体");
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
	    font.setFontHeightInPoints((short) 24);// 设置字体大小

	    HSSFFont font2 = workbook.createFont();
	    font2.setFontName("仿宋_GB2312");
	    font2.setFontHeightInPoints((short) 12);

	    HSSFCell cell = sheet.getRow(0).getCell(0);
	    cell.setCellValue(title);
	    // cell.getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cell.getCellStyle().setFont(font);
	    HSSFCell cell2 = sheet.getRow(1).getCell(0);
	    cell2.setCellValue(row2);
	    cell2.getCellStyle().setFont(font2);
	    HSSFCell cell3 = sheet.getRow(2).getCell(0);
	    cell3.setCellValue("编号");
	    HSSFCell cell4 = sheet.getRow(2).getCell(1);
	    cell4.setCellValue("物品名称");
	    HSSFCell cell5 = sheet.getRow(2).getCell(2);
	    cell5.setCellValue("入库");
	    HSSFCell cell6 = sheet.getRow(2).getCell(4);
	    cell6.setCellValue("出库");
	    HSSFCell cell7 = sheet.getRow(2).getCell(6);
	    cell7.setCellValue("库存（计量单位：条）");
	    HSSFCell date1 = sheet.getRow(3).getCell(2);
	    date1.setCellValue(dateTime);
	    HSSFCell date2 = sheet.getRow(3).getCell(4);
	    date2.setCellValue(outDate);
	    HSSFCell cell20 = sheet.getRow(3).getCell(6);
	    cell20.setCellValue("假");
	    HSSFCell cell21 = sheet.getRow(3).getCell(7);
	    cell21.setCellValue("私");
	    HSSFCell cell22 = sheet.getRow(3).getCell(8);
	    cell22.setCellValue("备注");
	    HSSFCell cell23 = sheet.getRow(4).getCell(2);
	    cell23.setCellValue("数量");
	    HSSFCell cell24 = sheet.getRow(4).getCell(3);
	    cell24.setCellValue("送检");
	    HSSFCell cell25 = sheet.getRow(4).getCell(4);
	    cell25.setCellValue("数量");
	    HSSFCell cell26 = sheet.getRow(4).getCell(5);
	    cell26.setCellValue("备注");
	    // 循环整个list
	    String name = "";
	    int number = 0;
	    int inspectNum = 0;
	    int returnNum = 0;
	    int inAccount = 0;
	    int inspectAccount = 0;
	    int outAccount = 0;
	    int size = ts.size();
	    // 创建body单元格（数据+合计+备注）
	    for (int i = 0; i < size + 4; i++) {
		HSSFRow titleRow = sheet.createRow(i + 5);
		for (int j = 0; j < 9; j++) {
		    HSSFCell cigarCell = titleRow.createCell(j);
		}
	    }

	    for (int i = 0; i < size; i++) {
		sheet.getRow(i + 5).setHeight((short) (25 * 20));// 设置行高
		name = ts.get(i).getName();
		number = ts.get(i).getNumber();
		inspectNum = ts.get(i).getInspectNum();
		returnNum = ts.get(i).getReturnNum();
		inAccount += number;
		inspectAccount += inspectNum;
		outAccount += returnNum;
		/*
		 * for (int j = 0; j < 9; j++) {// 边框 HSSFCell cellFir =
		 * sheet.getRow(i + 5).getCell(j);
		 * setCellStyle(cellFir.getCellStyle()); }
		 */
		sheet.getRow(i + 5).getCell(0).setCellValue(i + 1);// 编号
		sheet.getRow(i + 5).getCell(1).setCellValue(name);// 物品名称
		sheet.getRow(i + 5).getCell(2).setCellValue(number);// 入库数量
		sheet.getRow(i + 5).getCell(3).setCellValue(inspectNum);// 送检
		sheet.getRow(i + 5).getCell(4).setCellValue(returnNum);// 出库数量

	    }
	    /*
	     * for (int j = 0; j < 9; j++) {// 边框 HSSFCell cellSec =
	     * sheet.getRow(size + 5).getCell(j);
	     * setCellStyle(cellSec.getCellStyle());
	     * 
	     * }
	     */
	    sheet.getRow(size + 5).getCell(1).setCellValue("总计");
	    sheet.getRow(size + 5).getCell(2).setCellValue(inAccount);
	    sheet.getRow(size + 5).getCell(3).setCellValue(inspectAccount);
	    sheet.getRow(size + 5).getCell(4).setCellValue(outAccount);
	    Region region31 = new Region((short) size + 6, (short) 0, (short) size + 8, (short) 0);// 合并从第rowFrom行columnFrom列
	    Region region32 = new Region((short) size + 6, (short) 1, (short) size + 8, (short) 8);
	    sheet.addMergedRegion(region31);// 到rowTo行columnTo的区域
	    sheet.addMergedRegion(region32);
	    // 得到所有区域
	    sheet.getNumMergedRegions();
	    /*
	     * for (int j = 0; j < 2; j++) {// 边框 HSSFCell cellTh =
	     * sheet.getRow(size + 6).getCell(j);
	     * setCellStyle(cellTh.getCellStyle());
	     * 
	     * }
	     */
	    sheet.getRow(size + 6).getCell(0).setCellValue("备注");
	    workbook.write(out);
	    out.flush();
	    out.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    /**
     * 
    * @Description: 导出到excel
    * @param @param title  excel的工作表名
    * @param @param fileName excel 文件名
    * @param @param dataset 导出的数据集合
    * @param @param response 
    * @param @throws IOException
    * @author shizh
    * @date 2017年3月4日 上午10:59:05
    * @throws
     */
    @SuppressWarnings({ "static-access" })
    public void exportExcel(String title, String fileName, List<T> dataset, HttpServletResponse response)
	    throws IOException {
	OutputStream out = response.getOutputStream();// 取得输出流
	response.reset();// 清空输出流
	response.setContentType("application/ms-excel;charset=GB2312");
	response.setHeader("Content-disposition",
		"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1") + ".xls");
	// 声明一个工作薄
	try {
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    // 首先检查数据看是否是正确的
	    if (dataset == null || dataset.size() == 0 || title == null || out == null) {
		throw new Exception("传入的数据不对！");
	    }
	    // 取得实际泛型类
	    T ts = (T) dataset.get(0);
	    Class<?> tCls = ts.getClass();
	    // 生成一个表格
	    HSSFSheet sheet = workbook.createSheet(title);
	    // 设置表格默认列宽度为15个字节
	    sheet.setDefaultColumnWidth(15);
	    // 生成一个样式
	    HSSFCellStyle style = workbook.createCellStyle();
	    // 设置标题样式
	    style = this.setHeadStyle(workbook, style);

	    // 得到所有字段

	    Field filed[] = ts.getClass().getDeclaredFields();
	    // 标题
	    List<String> exportfieldtile = new ArrayList<String>();
	    // 导出的字段的get方法
	    List<Method> methodObj = new ArrayList<Method>();
	    // 遍历整个filed
	    for (int i = 0; i < filed.length; i++) {
		Field f = filed[i];
		ExcelAnnotation exa = f.getAnnotation(ExcelAnnotation.class);
		// 如果设置了annottion
		if (exa != null) {
		    String exprot = exa.exportName();
		    // 添加到标题
		    exportfieldtile.add(exprot);
		    // 添加到需要导出的字段的方法
		    String fieldname = f.getName();
		    String getMethodName = "get" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);

		    Method getMethod = tCls.getMethod(getMethodName, new Class[] {});

		    methodObj.add(getMethod);
		}
	    }
	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, exportfieldtile.size() - 1));
	    HSSFRow titleRow = sheet.createRow(0);
	    HSSFCell titleCell = titleRow.createCell(0);
	    titleCell.setCellValue(title);
	    titleCell.setCellStyle(this.setTitleStyle(workbook, workbook.createCellStyle()));
	    // 产生表格标题行
	    HSSFRow row = sheet.createRow(1);
	    for (int i = 0; i < exportfieldtile.size(); i++) {
		HSSFCell cell = row.createCell(i);
		cell.setCellStyle(style);
		HSSFRichTextString text = new HSSFRichTextString(exportfieldtile.get(i));
		cell.setCellValue(text);
	    }

	    int index = 1;

	    // 循环整个list
	    for (int j = 0; j < dataset.size(); j++) {
		// 从第二行开始写，第一行是标题
		T t = (T) dataset.get(j);
		row = sheet.createRow(index + 1);
		for (int k = 0; k < methodObj.size(); k++) {
		    HSSFCell cell = row.createCell(k);
		    Method getMethod = methodObj.get(k);
		    Object value = getMethod.invoke(t, new Object[] {});
		    String textValue = getValue(value);
		    cell.setCellValue(textValue);
		}
		index++;
	    }
	    workbook.write(out);
	    out.flush();
	    out.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @SuppressWarnings({ "static-access" })
    public void exportExcel2(String modelNo, String title, String fileName, List<T> dataset,
	    HttpServletResponse response) throws IOException {
	OutputStream out = response.getOutputStream();// 取得输出流
	response.reset();// 清空输出流
	response.setContentType("application/ms-excel;charset=GB2312");
	response.setHeader("Content-disposition",
		"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1") + ".xls");
	// 声明一个工作薄
	try {
	    // excel模板路径
	    String path = GetServerRealPath.getRootPath() + File.separatorChar + "statics" + File.separatorChar
		    + "excelTemplate" + File.separatorChar + "demo" + modelNo + ".xls";

	    File fi = new File(path);
	    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
	    // 读取excel模板
	    HSSFWorkbook workbook = new HSSFWorkbook(fs);
	    // 首先检查数据看是否是正确的
	    if (dataset == null || dataset.size() == 0 || title == null || out == null) {
		throw new Exception("传入的数据不对！");
	    }
	    // 取得实际泛型类
	    T ts = (T) dataset.get(0);
	    Class<?> tCls = ts.getClass();
	    // 生成一个表格
	    HSSFSheet sheet = workbook.getSheetAt(0);

	    // 生成一个样式
	    HSSFCellStyle style = workbook.createCellStyle();
	    HSSFFont fontNum = workbook.createFont();
	    fontNum.setFontHeightInPoints((short) 9);// 设置字体大小
	    fontNum.setFontName("宋体");

	    style.setFont(fontNum);
	    this.setCellStyle(style);
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	    style.setFont(fontNum);
	    style.setWrapText(true); // 自动换行
	    // 得到所有字段

	    Field filed[] = ts.getClass().getDeclaredFields();
	    // 标题
	    List<String> exportfieldtile = new ArrayList<String>();
	    // 导出的字段的get方法
	    List<Method> methodObj = new ArrayList<Method>();
	    // 遍历整个filed
	    for (int i = 0; i < filed.length; i++) {
		Field f = filed[i];
		ExcelAnnotation exa = f.getAnnotation(ExcelAnnotation.class);
		// 如果设置了annottion
		if (exa != null) {
		    String exprot = exa.exportName();
		    // 添加到标题
		    exportfieldtile.add(exprot);
		    // 添加到需要导出的字段的方法
		    String fieldname = f.getName();
		    String getMethodName = "get" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);

		    Method getMethod = tCls.getMethod(getMethodName, new Class[] {});

		    methodObj.add(getMethod);
		}
	    }
	    int index = 1;
	    sheet.getRow(0).getCell(0).setCellValue(title);
	    // 循环整个list
	    for (int j = 0; j < dataset.size(); j++) {
		// 从第二行开始写，第一行是标题
		T t = (T) dataset.get(j);
		HSSFRow row = sheet.createRow(index + 1);
		for (int k = 0; k < methodObj.size(); k++) {
		    HSSFCell cell = row.createCell(k);
		    row.getCell(k).setCellStyle(style);// 样式
		    Method getMethod = methodObj.get(k);
		    Object value = getMethod.invoke(t, new Object[] {});
		    String textValue = getValue(value);
		    cell.setCellValue(textValue);
		}
		index++;
	    }
	    response.setHeader("Content-disposition", "attachment; filename="
		    + new String((fileName + DateUtil.getYearDay()).getBytes("gb2312"), "iso8859-1") + ".xls");
	    workbook.write(out);
	    out.flush();
	    out.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public String getValue(Object value) {
	String textValue = "";
	if (value == null)
	    return textValue;

	if (value instanceof Boolean) {
	    boolean bValue = (Boolean) value;
	    textValue = "是";
	    if (!bValue) {
		textValue = "否";
	    }
	} else if (value instanceof Date) {
	    Date date = (Date) value;

	    textValue = sdf.format(date);
	} else
	    textValue = value.toString();

	return textValue;
    }

    /**
     * 初始化导出的excel标题的样式
     * */
    public static HSSFCellStyle setTitleStyle(HSSFWorkbook workbook, HSSFCellStyle style) {

	style.setFillForegroundColor(HSSFColor.WHITE.index);
	style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	// 生成字体
	HSSFFont font = workbook.createFont();
	font.setColor(HSSFColor.BLACK.index);
	font.setFontHeightInPoints((short) 16);
	font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	// 把字体应用到当前的样样式
	style.setFont(font);
	return style;

    }

    /**
     * 统计部分的样式
     * @param workbook
     * @param style
     * @return
     */
    public static HSSFCellStyle setStatStyle(HSSFWorkbook workbook, HSSFCellStyle style) {

	style.setFillForegroundColor(HSSFColor.WHITE.index);
	style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	style.setBorderBottom(HSSFCellStyle.BORDER_NONE);
	style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
	style.setBorderRight(HSSFCellStyle.BORDER_NONE);
	style.setBorderTop(HSSFCellStyle.BORDER_NONE);
	style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	// 生成字体
	HSSFFont font = workbook.createFont();
	font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	// 把字体应用到当前的样样式
	style.setFont(font);
	return style;

    }

    /**
     * 初始化导出的excel样式
     * */
    public static HSSFCellStyle setHeadStyle(HSSFWorkbook workbook, HSSFCellStyle style) {

	style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	// 生成字体
	HSSFFont font = workbook.createFont();
	font.setColor(HSSFColor.VIOLET.index);
	font.setFontHeightInPoints((short) 12);
	font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	// 把字体应用到当前的样样式
	style.setFont(font);
	return style;

    }

    public static HSSFCellStyle setbodyStyle(HSSFWorkbook workbook, HSSFCellStyle style2) {
	style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	// 生成字体
	HSSFFont font2 = workbook.createFont();
	font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	// 把字体应用到当前的样样式
	style2.setFont(font2);
	return style2;
    }
}