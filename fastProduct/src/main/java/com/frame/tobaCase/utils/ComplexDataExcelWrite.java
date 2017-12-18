/**    
* @Title: SimpleExcelWrite.java
* @Package com.excel.utils
* @Description: 多类型数据excel导出
* @author: Chensy
* @date 2017年02月21日 下午19:19:010
* @version V1.0
*/
package com.frame.tobaCase.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ComplexDataExcelWrite {
    /**
     * 创建多参数excel工作簿
    * os.close(); 流关闭方法结束后在调用方法下行执行
     * @param headers 表头行列
     * @param obj 数据行列
     * @param os 流对象
     * @param sheetName 工作簿名称
     * @param sheetNo 工作簿位置由0开始
     * @param dateType 转换日期类型
     * @throws Exception 
     */
    public void createExcel(Map<String, String> headers, List<Map<String, Object>> obj, OutputStream os,
	    String sheetName, int sheetNo, String dateType) throws Exception {

	// 创建工作薄
	WritableWorkbook workbook = Workbook.createWorkbook(os);
	// 创建新的一页
	WritableSheet sheet = workbook.createSheet(sheetName, sheetNo);
	setSheet(sheet, headers, obj, 0, dateType);
	workbookClose(workbook);
    }

    /**
     * 简单数据excel
    * os.close(); 流关闭方法结束后在调用方法下行执行
     * @param headers 表头行列
     * @param obj 数据行列
     * @param os 流对象
     * @param sheetName 工作簿名称
     * @param sheetNo 工作簿位置由0开始
     * @throws Exception 
     */
    public void createExcel(Map<String, String> headers, List<Map<String, Object>> obj, OutputStream os, int sheetNo,
	    String sheetName, String dateType) throws Exception {

	// 创建工作薄
	WritableWorkbook workbook = Workbook.createWorkbook(os);
	// 创建新的一页
	WritableSheet sheet = workbook.createSheet(sheetName, sheetNo);
	// 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
	setSheet(sheet, headers, obj, 0, dateType);

	workbookClose(workbook);
    }

    /**
     * //把创建的内容写入到输出流中，并关闭输出流
     * @param workbook
     * @throws IOException
     * @throws WriteException
     */
    private void workbookClose(WritableWorkbook workbook) throws IOException, WriteException {
	workbook.write();
	workbook.close();
    }

    private void setSheet(WritableSheet sheet, Map<String, String> headers, List<Map<String, Object>> obj, int startRow,
	    String dateType) throws Exception {
	int topColumn = 0;
	// 输出表头
	WritableFont headerFont = new WritableFont(WritableFont.COURIER, 12, WritableFont.BOLD, false,
		UnderlineStyle.NO_UNDERLINE, Colour.BLACK);

	WritableCellFormat headerCellFormat = new WritableCellFormat(headerFont);
	headerCellFormat.setBackground(Colour.AQUA);
	headerCellFormat.setAlignment(Alignment.CENTRE);// 文字居中
	headerCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中
	headerCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
	for (String key : headers.keySet()) {
	    sheet.addCell(new Label(topColumn, startRow, headers.get(key), headerCellFormat));
	    CellView navCellView = new CellView();
	    navCellView.setAutosize(true); // 设置自动大小
	    navCellView.setSize(14);
	    sheet.setColumnView(topColumn, navCellView);
	    topColumn++;
	}
	// 输出表内容
	WritableFont contextFont = new WritableFont(WritableFont.COURIER, 12, WritableFont.NO_BOLD, false,
		UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
	WritableCellFormat contextCellFormat = new WritableCellFormat(contextFont);
	contextCellFormat.setAlignment(Alignment.CENTRE);// 文字居中
	contextCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中
	contextCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
	for (Map<String, Object> map : obj) {
	    startRow++;
	    int i = 0;
	    for (String key : headers.keySet()) {
		sheet.addCell(new Label(i, startRow, ObjectToData.objToStr(map.get(key), dateType).toString(),
			contextCellFormat));
		// sheet.insertColumn(ObjectToData.objToStr(map.get(key),
		// dateType).toString().length());
		i++;
	    }
	}
    }
}