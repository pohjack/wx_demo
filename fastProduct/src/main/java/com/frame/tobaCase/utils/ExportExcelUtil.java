/**    
* @Title: ExportExcelUtil.java
* @Package com.frame.tobaCase.utils
* @Description: 文书模板导出工具类
* @author: shizh
* @date 2017年3月7日 下午1:59:43
* @version V1.0
*/
package com.frame.tobaCase.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;

import com.frame.core.commons.utils.DateUtil;
import com.frame.tobaCase.entity.CaseCigar;
import com.frame.tobaCase.entity.CaseGangYin;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.entity.CaseInfoSlave;
import com.frame.tobaCase.entity.NonCigar;
import com.frame.tobaCase.entity.NonExcel;

public class ExportExcelUtil {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
    public static SimpleDateFormat sdfTime = new SimpleDateFormat("HH时mm分");

    /**
     * 
     * @Description: 文书模板导出方法 @param @param modelNo 文书模板ID @param @param
     *               caseInfo 案件信息 @param @param template 模板内容 @param @throws
     *               FileNotFoundException @param @throws IOException @author
     *               shizh @date 2017年3月7日 下午2:04:46 @throws
     */
    public void TempExport(String modelNo, Map<String, Object> caseInfo, Map<String, String> template,
	    HttpServletResponse response) throws FileNotFoundException, IOException {
	OutputStream out = response.getOutputStream();// 取得输出流
	response.reset();// 清空输出流
	response.setContentType("application/ms-excel;charset=GB2312");
	response.setHeader("Content-disposition", "attachment; filename="
		+ new String(String.valueOf(System.currentTimeMillis()).getBytes("gb2312"), "iso8859-1") + ".xls");
	// excel模板路径
	String path = GetServerRealPath.getRootPath() + File.separatorChar + "statics" + File.separatorChar
		+ "excelTemplate" + File.separatorChar + "demo" + modelNo + ".xls";

	File fi = new File(path);
	POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
	// 读取excel模板
	HSSFWorkbook wb = new HSSFWorkbook(fs);
	// HSSFCellStyle cellStyle = wb.createCellStyle();
	// cellStyle.setWrapText(false);
	// 读取了模板内所有sheet内容
	String temp = template.get("tag1");
	String fileName = "";
	switch (modelNo) {
	case "2":// 检查笔录
	    wb = inspectTemp(wb, caseInfo, temp);
	    fileName = "检查（勘验）笔录";
	    break;
	case "1":// 立案报告
	    wb = caseReportTemp(wb, caseInfo, template);
	    fileName = "立案报告";
	    break;
	case "3":// 鉴定结论告知书
	    wb = inspectResTemp(wb, caseInfo, temp);
	    fileName = "鉴定结论告知书";
	    break;
	case "4":// 案件调查终结报告
	    wb = inspectEndTemp(wb, caseInfo, template);
	    fileName = "案件调查终结报告";
	    break;
	case "5":// 案件处理审批模板
	    wb = caseDealTemp(wb, caseInfo, template);
	    fileName = "案件处理审批模板";
	    break;
	case "6":// 行政处罚事先告知书
	    wb = punnishToKonwnTemp(wb, caseInfo, temp);
	    fileName = "行政处罚事先告知书";
	    break;
	case "7":// 处罚决定书
	    wb = punnishDecisionTemp(wb, caseInfo, temp);
	    fileName = "处罚决定书";
	    break;
	case "8":// 结案报告
	    wb = caseEndTemp(wb, caseInfo, template);
	    fileName = "结案报告";
	    break;
	case "9":// 结案报告
	    wb = fengYeTemp(wb, caseInfo, template);
	    fileName = "案卷封页";
	    break;
	case "10":// 卷烟价值估算
	    wb = cigarWorthTemp(wb, caseInfo, temp);
	    fileName = "卷烟价值估算";
	    break;
	case "11":// 估价明细表
	    wb = estimateDatail(wb, caseInfo, temp);
	    fileName = "估价明细表";
	    break;
	case "12":// 12涉案烟草专卖品核价表
	    wb = cigarPricing(wb, caseInfo, temp);
	    fileName = "涉案烟草专卖品核价表";
	    break;
	case "13":// 13没收财物清单
	    wb = seizureList(wb, caseInfo, temp);
	    fileName = "没收财物清单";
	    break;
	case "14":// 涉案烟卷进出存帐
	    wb = cigarAccount(wb, caseInfo, temp);
	    fileName = "进出存帐";
	    break;
	case "21":// 先行登记保存证据处理通知书
	    wb = proof1(wb, caseInfo, temp);
	    fileName = "广东省潮州市烟草专卖局先行登记保存证据处理通知书";
	    break;
	case "22":// 涉案烟草专卖品鉴定结论告知书
	    wb = proof2(wb, caseInfo, temp);
	    fileName = "广东省潮州市烟草专卖局涉案烟草专卖品鉴定结论告知书";
	    break;
	case "23":// 行政处罚事先告知书
	    wb = proof3(wb, caseInfo, temp);
	    fileName = "广东省潮州市烟草专卖局行政处罚事先告知书";
	    break;
	case "24":// 行政处罚决定书
	    wb = proof4(wb, caseInfo, temp);
	    fileName = "广东省潮州市烟草专卖局行政处罚决定书";
	    break;
	case "32":// 走私钢印表
	    wb = gangYin(wb, caseInfo, temp);
	    fileName = "走私钢印表";
	    break;
	case "33":// 涉案卷烟清单
	    wb = cigarList(wb, caseInfo, temp);
	    fileName = "涉案卷烟清单";
	    break;
	case "34":// 非烟条码明细
	    wb = nonDetail(wb, caseInfo, temp);
	    fileName = "非烟条码";
	    break;
	case "35":// 非烟上报表
	    wb = nonExcel(wb, caseInfo, temp);
	    fileName = "非烟上报表";
	    break;
	default:
	    break;
	}
	response.setHeader("Content-disposition", "attachment; filename="
		+ new String((fileName + DateUtil.getYearDay()).getBytes("gb2312"), "iso8859-1") + ".xls");
	// 修改模板内容导出新模板
	// FileOutputStream out = new FileOutputStream("D:/export.xls");
	wb.write(out);
	out.flush();
	out.close();
    }

    private HSSFWorkbook fengYeTemp(HSSFWorkbook wb, Map<String, Object> caseInfo, Map<String, String> template) {
	HSSFSheet sheet = wb.getSheetAt(0);
	String value = (String) caseInfo.get("regi_no");
	String fengNo = value == null ? ""
		: value.substring((value.indexOf("[") + 1), value.indexOf("]")) + " 年度 " + value.substring(0, 2) + " "
			+ value.substring((value.indexOf("]") + 1));
	sheet.getRow(2).getCell(0).setCellValue(fengNo);
	String cause = (String) caseInfo.get("case_cause");
	if (cause.contains("涉嫌")) {
	    cause = cause.replace("涉嫌", "");
	}
	sheet.getRow(3).getCell(1).setCellValue(cause == null ? "" : cause);
	String value3 = (String) caseInfo.get("per_respon");
	sheet.getRow(4).getCell(1).setCellValue(value3 == null ? "" : value3);
	String value4 = (String) caseInfo.get("user_name");;
	sheet.getRow(5).getCell(1).setCellValue(value4 == null ? "" : value4.replace(",", "、"));
	Date enddate = (Date) caseInfo.get("end_case");
	Date archive_date = (Date) caseInfo.get("archive_date");
	Date date = (Date) caseInfo.get("crime_date");
	sheet.getRow(7).getCell(1).setCellValue(date == null ? "" : sdf.format(date));
	sheet.getRow(7).getCell(3).setCellValue(enddate == null ? "" : sdf.format(enddate));
	sheet.getRow(8).getCell(1).setCellValue(archive_date == null ? "" : sdf.format(archive_date));
	sheet.getRow(6).getCell(1).setCellValue(template.get("tag1"));
	return wb;
    }

    private HSSFWorkbook nonExcel(HSSFWorkbook wb, Map<String, Object> caseInfo, String temp) {
	HSSFSheet sheet = wb.getSheetAt(0);
	HSSFFont fontNum = wb.createFont();
	fontNum.setFontHeightInPoints((short) 9);// 设置字体大小
	fontNum.setFontName("宋体");
	HSSFCellStyle cellStyle = wb.createCellStyle();
	this.cellStyle(cellStyle);
	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle.setFont(fontNum);
	cellStyle.setWrapText(true); // 自动换行
	List<NonExcel> list = (List<NonExcel>) caseInfo.get("licenceInfos");
	int size = list.size();
	String regiNo = "";
	String perRespon = "";
	String caseAddr = "";
	String summary = "";
	int nonSmokeNum = 0;
	int kindNo = 0;
	double nonPrice = 0.0;
	Date crimeDate = new Date();

	for (int i = 0; i < size; i++) {
	    HSSFRow row = sheet.createRow(i + 1);
	    // 设置行高
	    for (int j = 0; j < 14; j++) {
		HSSFCell cigarCell = row.createCell(j);
		row.getCell(j).setCellStyle(cellStyle);// 样式
	    }
	    row.setHeight((short) (51.5 * 20));
	    if (list.size() > 0) {
		regiNo = list.get(i).getRegiNo();
		perRespon = list.get(i).getPerRespon();
		caseAddr = list.get(i).getCaseAddr();
		summary = list.get(i).getSummary();
		nonSmokeNum = list.get(i).getNonSmokeNum();
		kindNo = list.get(i).getKindNo();
		crimeDate = list.get(i).getCrimeDate();
		nonPrice = list.get(i).getNonPrice() / 10000;
		sheet.getRow(i + 1).getCell(0).setCellValue(regiNo == null ? "" : regiNo);// 编号
		sheet.getRow(i + 1).getCell(2).setCellValue(perRespon == null ? "" : perRespon);// 当事人
		sheet.getRow(i + 1).getCell(4).setCellValue(crimeDate == null ? "" : sdf.format(crimeDate));// 案发时间
		sheet.getRow(i + 1).getCell(5).setCellValue(caseAddr == null ? "" : caseAddr);// 案发地点
		sheet.getRow(i + 1).getCell(6).setCellValue("潮州");// 发运地
		if (nonPrice != 0) {
		    sheet.getRow(i + 1).getCell(8).setCellValue(nonPrice);// 案值（万元）
		}
		sheet.getRow(i + 1).getCell(9).setCellValue(crimeDate == null ? "" : sdf.format(crimeDate));// 立案时间
		sheet.getRow(i + 1).getCell(10).setCellValue(summary == null ? "" : summary);// 案情摘要

		if (nonSmokeNum != 0) {
		    sheet.getRow(i + 1).getCell(12).setCellValue(nonSmokeNum);// 数量
		}
		if (kindNo != 0) {
		    sheet.getRow(i + 1).getCell(13).setCellValue(kindNo);// 数量
		}
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		HSSFClientAnchor a = new HSSFClientAnchor(0, 0, 0, 0, (short) 1, i + 1, (short) 2, i + 2);
		HSSFSimpleShape shape1 = patriarch.createSimpleShape(a);
		shape1.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
		shape1.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID);
		HSSFClientAnchor b = new HSSFClientAnchor(0, 0, 0, 0, (short) 3, i + 1, (short) 4, i + 2);
		HSSFSimpleShape shape2 = patriarch.createSimpleShape(b);
		shape2.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
		shape2.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID);
		HSSFClientAnchor c = new HSSFClientAnchor(0, 0, 0, 0, (short) 7, i + 1, (short) 8, i + 2);
		HSSFSimpleShape shape3 = patriarch.createSimpleShape(c);
		shape3.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
		shape3.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID);
		HSSFClientAnchor d = new HSSFClientAnchor(0, 0, 0, 0, (short) 11, i + 1, (short) 12, i + 2);
		HSSFSimpleShape shape4 = patriarch.createSimpleShape(d);
		shape4.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
		shape4.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID);
	    }
	}
	return wb;
    }

    private HSSFWorkbook nonDetail(HSSFWorkbook wb, Map<String, Object> caseInfo, String temp) {
	HSSFSheet sheet = wb.getSheetAt(0);
	HSSFFont fontNumS = wb.createFont();
	fontNumS.setFontHeightInPoints((short) 10);// 设置字体大小
	fontNumS.setFontName("宋体");
	HSSFFont fontNumM = wb.createFont();
	fontNumM.setFontHeightInPoints((short) 11);// 设置字体大小
	fontNumM.setFontName("宋体");
	HSSFFont fontNumL = wb.createFont();
	fontNumL.setFontHeightInPoints((short) 14);// 设置字体大小
	fontNumL.setFontName("宋体");
	HSSFCellStyle cellStyleOu = wb.createCellStyle();
	cellStyleOu.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
	cellStyleOu.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
	cellStyleOu.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
	cellStyleOu.setFont(fontNumS);
	cellStyleOu.setWrapText(true); // 自动换行
	cellStyleOu.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyleOu.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

	HSSFCellStyle cellStyleJi = wb.createCellStyle();
	cellStyleJi.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
	cellStyleJi.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
	cellStyleJi.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
	cellStyleJi.setFont(fontNumS);
	cellStyleJi.setWrapText(true); // 自动换行
	cellStyleJi.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyleJi.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

	HSSFCellStyle remarkStyle = wb.createCellStyle();
	remarkStyle.setFont(fontNumM);
	remarkStyle.setWrapText(true); // 自动换行

	HSSFCellStyle dateStyle = wb.createCellStyle();
	dateStyle.setFont(fontNumL);
	dateStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居右
	HSSFCellStyle dStyle = wb.createCellStyle();
	dStyle.setFont(fontNumL);

	String string = (String) caseInfo.get("per_respon");
	sheet.getRow(1).getCell(0).setCellValue("当事人：" + (string == null ? "" : string));
	CaseInfo caseI = (CaseInfo) caseInfo.get("caseInfo");
	String regiNo = caseI.getRegiNo();
	sheet.getRow(1).getCell(3).setCellValue("案件编号：" + (regiNo == null ? "" : regiNo));
	List<NonCigar> list = (List<NonCigar>) caseInfo.get("nonCigars");
	int size = list.size();
	String name = "";
	String code1 = "";
	String code2 = "";
	int number = 0;
	int rowCount = ((size % 2 == 0) ? size / 2 : (size / 2 + 1));
	List<NonCigar> left = new ArrayList<NonCigar>();
	List<NonCigar> right = new ArrayList<NonCigar>();
	for (int j = 0; j < size; j++) {
	    if (j % 2 == 0) {
		left.add(list.get(j));
	    } else {
		right.add(list.get(j));
	    }
	}
	int rows = 0;
	if (rowCount < 10) {
	    rows = 10;
	} else {
	    rows = rowCount;
	}
	for (int i = 0; i < rows * 2 + 3; i++) {
	    HSSFRow row = sheet.createRow(i + 3);
	    for (int j = 0; j < 6; j++) {
		HSSFCell cigarCell = row.createCell(j);
		if (i < rows * 2) {
		    if (i % 2 == 0) {
			row.getCell(j).setCellStyle(cellStyleOu);// 偶数行样式
		    } else {
			row.getCell(j).setCellStyle(cellStyleJi);// 奇数行样式
		    }
		} else if (i == rows * 2) {
		    row.getCell(j).setCellStyle(remarkStyle);
		    row.setHeight((short) (27 * 20));
		} else {
		    if (j == 4 || j == 5) {
			row.getCell(j).setCellStyle(dStyle);
			row.setHeight((short) (27 * 20));
		    } else {
			row.getCell(j).setCellStyle(dateStyle);
			row.setHeight((short) (27 * 20));
		    }
		}

	    }
	}
	for (int h = 0; h < rows * 2; h = h + 2) {
	    Region regionL = new Region((short) h + 3, (short) 0, (short) h + 4, (short) 0);
	    sheet.addMergedRegion(regionL);
	    Region regionNL = new Region((short) h + 3, (short) 2, (short) h + 4, (short) 2);
	    sheet.addMergedRegion(regionNL);
	    Region regionR = new Region((short) h + 3, (short) 3, (short) h + 4, (short) 3);
	    sheet.addMergedRegion(regionR);
	    Region regionNR = new Region((short) h + 3, (short) 5, (short) h + 4, (short) 5);
	    sheet.addMergedRegion(regionNR);
	    sheet.getNumMergedRegions();
	}

	Region region = new Region((short) rows * 2 + 3, (short) 0, (short) rows * 2 + 3, (short) 5);
	sheet.addMergedRegion(region);
	Region region1 = new Region((short) rows * 2 + 4, (short) 2, (short) rows * 2 + 4, (short) 3);
	sheet.addMergedRegion(region1);
	Region region2 = new Region((short) rows * 2 + 5, (short) 4, (short) rows * 2 + 5, (short) 5);
	sheet.addMergedRegion(region2);
	// 得到所有合并区域
	sheet.getNumMergedRegions();
	for (int k = 0; k < rowCount; k++) {

	    if (left.size() > 0 && k < left.size()) {
		name = left.get(k).getName();
		code1 = left.get(k).getCode1();
		code2 = left.get(k).getCode2();
		number = left.get(k).getNumber();
		sheet.getRow(k * 2 + 3).getCell(0).setCellValue(name == null ? "" : name);
		sheet.getRow(k * 2 + 3).getCell(1).setCellValue(code1 == null ? "" : code1);
		sheet.getRow(k * 2 + 3 + 1).getCell(1).setCellValue(code2 == null ? "" : code2);
		if (number != 0) {
		    sheet.getRow(k * 2 + 3).getCell(2).setCellValue(number);
		}

	    }
	    if (right.size() > 0 && k < right.size()) {
		name = right.get(k).getName();
		code1 = right.get(k).getCode1();
		code2 = right.get(k).getCode2();
		number = right.get(k).getNumber();
		sheet.getRow(k * 2 + 3).getCell(3).setCellValue(name == null ? "" : name);
		sheet.getRow(k * 2 + 3).getCell(4).setCellValue(code1 == null ? "" : code1);
		sheet.getRow(k * 2 + 3 + 1).getCell(4).setCellValue(code2 == null ? "" : code2);
		if (number != 0) {
		    sheet.getRow(k * 2 + 3).getCell(5).setCellValue(number);
		}
	    }

	}

	sheet.getRow(rows * 2 + 3).getCell(0).setCellValue("备注：无法识别的数字或字母以\"*\"代替；条盒打码被破坏的，数字或字母以“-”代替。");
	sheet.getRow(rows * 2 + 4).getCell(2).setCellValue("当事人签名:");
	sheet.getRow(rows * 2 + 5).getCell(3).setCellValue("时间:");
	sheet.getRow(rows * 2 + 5).getCell(4).setCellValue("     年    月    日");

	return wb;

    }

    private HSSFWorkbook nonDetailno(HSSFWorkbook wb, Map<String, Object> caseInfo, String temp) {
	HSSFSheet sheet = wb.getSheetAt(0);
	HSSFFont fontNum = wb.createFont();
	fontNum.setFontHeightInPoints((short) 10);// 设置字体大小
	fontNum.setFontName("宋体");
	HSSFFont fontNum2 = wb.createFont();
	fontNum2.setFontHeightInPoints((short) 11);// 设置字体大小
	fontNum2.setFontName("宋体");
	HSSFFont fontNum3 = wb.createFont();
	fontNum3.setFontHeightInPoints((short) 14);// 设置字体大小
	fontNum3.setFontName("宋体");

	HSSFCellStyle cellStyle = wb.createCellStyle();
	this.cellStyle(cellStyle);
	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle.setFont(fontNum);
	cellStyle.setWrapText(true); // 自动换行

	HSSFCellStyle cellStyle2 = wb.createCellStyle();
	cellStyle2.setFont(fontNum2);
	cellStyle2.setWrapText(true); // 自动换行
	cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直

	HSSFCellStyle cellStyle3 = wb.createCellStyle();
	cellStyle3.setFont(fontNum3);
	cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle3.setWrapText(true); // 自动换行
	cellStyle3.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居右
	HSSFCellStyle cellStyle4 = wb.createCellStyle();
	cellStyle4.setFont(fontNum3);
	cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle4.setWrapText(true); // 自动换行
	cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

	String string = (String) caseInfo.get("per_respon");
	sheet.getRow(1).getCell(0).setCellValue("当事人：" + (string == null ? "" : string));
	CaseInfo caseI = (CaseInfo) caseInfo.get("caseInfo");
	String regiNo = caseI.getRegiNo();
	sheet.getRow(1).getCell(2).setCellValue("案件编号：" + (regiNo == null ? "" : regiNo));
	List<NonCigar> list = (List<NonCigar>) caseInfo.get("nonCigars");
	int size = list.size();
	String name = "";
	String code1 = "";
	String code2 = "";
	int number = 0;
	for (int i = 0; i < size + 3; i++) {
	    HSSFRow row = sheet.createRow(i + 3);
	    // 设置行高
	    for (int j = 0; j < 5; j++) {
		HSSFCell cigarCell = row.createCell(j);
		if (i == size) {
		    row.getCell(j).setCellStyle(cellStyle2);// 样式
		} else if (i == size + 1 || i == size + 2) {
		    if (j == 2) {
			row.getCell(j).setCellStyle(cellStyle3);// 样式
		    } else {
			row.getCell(j).setCellStyle(cellStyle4);// 样式
		    }
		} else {
		    row.getCell(j).setCellStyle(cellStyle);// 样式
		}
	    }
	    if (i < size) {
		if (size > 0) {
		    code1 = list.get(i).getCode1();
		    name = list.get(i).getName();
		    code2 = list.get(i).getCode2();
		    number = list.get(i).getNumber();
		    sheet.getRow(i + 3).getCell(0).setCellValue(i + 1);// 编号
		    sheet.getRow(i + 3).getCell(1).setCellValue(name == null ? "" : name);// 品牌规格
		    sheet.getRow(i + 3).getCell(2).setCellValue(code1 == null ? "" : code1);// 品牌规格
		    sheet.getRow(i + 3).getCell(3).setCellValue(code2 == null ? "" : code2);// 品牌规格

		    if (number != 0) {
			sheet.getRow(i + 3).getCell(4).setCellValue(number);// 数量
		    }
		}
	    }

	}

	sheet.getRow(size + 3).setHeight((short) (27 * 20));
	Region region = new Region((short) size + 3, (short) 0, (short) size + 3, (short) 5);
	sheet.addMergedRegion(region);
	Region region1 = new Region((short) size + 4, (short) 2, (short) size + 4, (short) 3);
	sheet.addMergedRegion(region1);
	Region region2 = new Region((short) size + 5, (short) 4, (short) size + 5, (short) 5);
	sheet.addMergedRegion(region2);
	// 得到所有合并区域
	sheet.getNumMergedRegions();
	sheet.getRow(size + 3).getCell(0).setCellValue("备注：无法识别的数字或字母以\"*\"代替；条盒打码被破坏的，数字或字母以“-”代替。");
	sheet.getRow(size + 4).getCell(2).setCellValue("当事人签名:");
	sheet.getRow(size + 5).getCell(3).setCellValue("时间:");
	sheet.getRow(size + 5).getCell(4).setCellValue("     年    月    日");
	return wb;

    }

    private HSSFWorkbook gangYin(HSSFWorkbook wb, Map<String, Object> caseInfo, String temp) {
	HSSFSheet sheet = wb.getSheetAt(0);
	HSSFFont fontNum = wb.createFont();
	fontNum.setFontHeightInPoints((short) 12);// 设置字体大小
	fontNum.setFontName("宋体");
	HSSFCellStyle cellStyle = wb.createCellStyle();
	this.cellStyle(cellStyle);
	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle.setFont(fontNum);
	cellStyle.setWrapText(true); // 自动换行
	List<CaseGangYin> list = (List<CaseGangYin>) caseInfo.get("gangYins");
	int size = list.size();
	String customerCode = "";
	String name = "";
	String company = "";
	int number = 0;
	if (size >= 10) {
	    for (int i = 0; i < size; i++) {
		HSSFRow row = sheet.createRow(i + 1);
		// 设置行高
		for (int j = 0; j < 5; j++) {
		    HSSFCell cigarCell = row.createCell(j);
		    sheet.getRow(i + 1).getCell(j).setCellStyle(cellStyle);// 样式
		}

		if (list.size() > 0) {
		    customerCode = list.get(i).getCustomerCode();
		    name = list.get(i).getName();
		    company = list.get(i).getCompany();
		    number = list.get(i).getNumber();
		    sheet.getRow(i + 1).getCell(0).setCellValue(i + 1);// 编号
		    sheet.getRow(i + 1).getCell(1).setCellValue(customerCode == null ? "" : customerCode);// 客户代码
		    sheet.getRow(i + 1).getCell(2).setCellValue(name == null ? "" : name);// 品牌规格
		    sheet.getRow(i + 1).getCell(3).setCellValue(company == null ? "" : company);// 所属企业

		    if (number != 0) {
			sheet.getRow(i + 1).getCell(4).setCellValue(number);// 数量
		    }
		}
	    }
	} else {
	    for (int i = 0; i < 10; i++) {
		HSSFRow row = sheet.createRow(i + 1);
		// 设置行高
		for (int j = 0; j < 5; j++) {
		    HSSFCell cigarCell = row.createCell(j);
		    sheet.getRow(i + 1).getCell(j).setCellStyle(cellStyle);// 样式
		}

		if (size > 0 && i < size) {
		    customerCode = list.get(i).getCustomerCode();
		    name = list.get(i).getName();
		    company = list.get(i).getCompany();
		    number = list.get(i).getNumber();
		    sheet.getRow(i + 1).getCell(0).setCellValue(i + 1);// 编号
		    sheet.getRow(i + 1).getCell(1).setCellValue(customerCode == null ? "" : customerCode);// 客户代码
		    sheet.getRow(i + 1).getCell(2).setCellValue(name == null ? "" : name);// 品牌规格
		    sheet.getRow(i + 1).getCell(3).setCellValue(company == null ? "" : company);// 所属企业

		    if (number != 0) {
			sheet.getRow(i + 1).getCell(4).setCellValue(number);// 数量
		    }
		}
	    }

	}

	return wb;
    }

    private HSSFWorkbook cigarList(HSSFWorkbook wb, Map<String, Object> caseInfo, String temp) {
	HSSFSheet sheet = wb.getSheetAt(0);
	HSSFFont fontNum = wb.createFont();
	fontNum.setFontHeightInPoints((short) 12);// 设置字体大小
	fontNum.setFontName("宋体");
	HSSFCellStyle cellStyle = wb.createCellStyle();
	this.cellStyle(cellStyle);
	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle.setFont(fontNum);
	cellStyle.setWrapText(true); // 自动换行
	HSSFCellStyle cellStyle2 = wb.createCellStyle();
	cellStyle2.setFont(fontNum);
	this.cellStyle(cellStyle2);
	cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle2.setWrapText(true); // 自动换行
	String string = (String) caseInfo.get("per_respon");
	sheet.getRow(2).getCell(0).setCellValue("当事人：" + (string == null ? "" : string));
	CaseInfo caseI = (CaseInfo) caseInfo.get("caseInfo");
	String regiNo = caseI.getRegiNo();
	sheet.getRow(2).getCell(3).setCellValue("案件编号：" + (regiNo == null ? "" : regiNo));
	List<CaseCigar> list = (List<CaseCigar>) caseInfo.get("cigarList");
	int size = list.size();
	String name = "";
	int number = 0;
	String barcode = "";
	double retailPrice = 0.00;
	double totalValue = 0.00;
	for (int i = 0; i < size; i++) {
	    HSSFRow row = sheet.createRow(i + 4);
	    // 设置行高
	    for (int j = 0; j < 7; j++) {
		HSSFCell cigarCell = row.createCell(j);
		if (j == 3 || j == 5) {
		    sheet.getRow(i + 4).getCell(j).setCellStyle(cellStyle2);
		} else {
		    sheet.getRow(i + 4).getCell(j).setCellStyle(cellStyle);// 样式
		}
	    }

	    if (list.size() > 0) {
		name = list.get(i).getName();
		number = list.get(i).getNumber();
		barcode = list.get(i).getBarCode();
		retailPrice = list.get(i).getRetailPrice();
		totalValue = list.get(i).getTotalValue();
		sheet.getRow(i + 4).getCell(0).setCellValue(i + 1);// 编号
		sheet.getRow(i + 4).getCell(1).setCellValue(barcode == null ? "" : barcode);// 条形码
		sheet.getRow(i + 4).getCell(2).setCellValue(name == null ? "" : name);// 品牌规格
		sheet.getRow(i + 4).getCell(3).setCellValue(retailPrice);// 单价（元）

		if (number != 0) {
		    sheet.getRow(i + 4).getCell(4).setCellValue(number);// 数量
		}
		sheet.getRow(i + 4).getCell(5).setCellValue(totalValue);// 合计（元）
	    }
	}
	return wb;
    }

    private HSSFWorkbook estimateDatail(HSSFWorkbook wb, Map<String, Object> caseInfo, String temp) {
	HSSFSheet sheet = wb.getSheetAt(0);
	HSSFFont fontNum = wb.createFont();
	fontNum.setFontHeightInPoints((short) 12);// 设置字体大小
	fontNum.setFontName("宋体");
	HSSFCellStyle cellStyle = wb.createCellStyle();
	this.cellStyle(cellStyle);
	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle.setFont(fontNum);
	cellStyle.setWrapText(true); // 自动换行
	HSSFCellStyle cellStyle2 = wb.createCellStyle();
	cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle2.setFont(fontNum);
	cellStyle2.setWrapText(true); // 自动换行
	HSSFCellStyle cellStyle3 = wb.createCellStyle();
	cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle3.setFont(fontNum);
	cellStyle3.setWrapText(true); // 自动换行
	List<CaseCigar> list = (List<CaseCigar>) caseInfo.get("cigarList");
	int total = (int) caseInfo.get("total");
	int size = list.size();
	String name = "";
	int number = 0;
	String barcode = "";
	if (size >= 10) {
	    // 创建body单元格（数据+）
	    for (int i = 0; i < size + 2; i++) {
		HSSFRow row = sheet.createRow(i + 1);

		// 设置行高
		for (int j = 0; j < 8; j++) {
		    HSSFCell cigarCell = row.createCell(j);
		    if (i == size + 1) {
			if (j > 3) {
			    sheet.getRow(i + 1).getCell(j).setCellStyle(cellStyle3);// 样式
			} else if (j <= 3) {
			    sheet.getRow(i + 1).getCell(j).setCellStyle(cellStyle2);// 样式
			}
		    } else {
			sheet.getRow(i + 1).getCell(j).setCellStyle(cellStyle);// 样式
		    }
		}
		if (i < size) {
		    if (list.size() > 0) {
			name = list.get(i).getName();
			number = list.get(i).getNumber();
			barcode = list.get(i).getBarCode();
			sheet.getRow(i + 1).getCell(0).setCellValue(i + 1);// 编号
			sheet.getRow(i + 1).getCell(1).setCellValue(barcode == null ? "" : barcode);// 条形码
			sheet.getRow(i + 1).getCell(2).setCellValue(name == null ? "" : name);// 品牌规格
			if (number != 0) {
			    sheet.getRow(i + 1).getCell(3).setCellValue(number);// 数量
			}
			sheet.getRow(i + 1).getCell(5).setCellValue("元");//
		    }
		}
	    }
	    sheet.getRow(size + 1).setHeight((short) (29 * 20));
	    sheet.getRow(size + 1).getCell(0).setCellValue("合计");
	    if (total != 0) {
		sheet.getRow(size + 1).getCell(3).setCellValue(total);// 合计
	    }
	    sheet.getRow(size + 2).setHeight((short) (25.25 * 20));
	    Region region = new Region((short) size + 2, (short) 0, (short) size + 2, (short) 3);
	    sheet.addMergedRegion(region);
	    Region region1 = new Region((short) size + 2, (short) 4, (short) size + 2, (short) 7);
	    sheet.addMergedRegion(region1);
	    // 得到所有合并区域
	    sheet.getNumMergedRegions();
	    String perSon = (String) caseInfo.get("catchpole_name");
	    sheet.getRow(size + 2).getCell(0).setCellValue("    联系人：" + (perSon == null ? "" : perSon));
	    sheet.getRow(size + 2).getCell(4).setCellValue("联系电话：0768-2210293");
	} else if (size < 10) {
	    // 创建body单元格（数据+）
	    for (int i = 0; i < 12; i++) {
		HSSFRow row = sheet.createRow(i + 1);

		// 设置行高
		for (int j = 0; j < 8; j++) {
		    HSSFCell cigarCell = row.createCell(j);
		    if (i == 11) {
			if (j > 3) {
			    sheet.getRow(i + 1).getCell(j).setCellStyle(cellStyle3);// 样式
			} else if (j <= 3) {
			    sheet.getRow(i + 1).getCell(j).setCellStyle(cellStyle2);// 样式
			}
		    } else {
			sheet.getRow(i + 1).getCell(j).setCellStyle(cellStyle);// 样式
		    }
		}
		if (i < size) {
		    if (list.size() > 0) {
			name = list.get(i).getName();
			number = list.get(i).getNumber();
			barcode = list.get(i).getBarCode();
			sheet.getRow(i + 1).getCell(0).setCellValue(i + 1);// 编号
			sheet.getRow(i + 1).getCell(1).setCellValue(barcode == null ? "" : barcode);// 条形码
			sheet.getRow(i + 1).getCell(2).setCellValue(name == null ? "" : name);// 品牌规格
			if (number != 0) {
			    sheet.getRow(i + 1).getCell(3).setCellValue(number);// 数量
			}
			sheet.getRow(i + 1).getCell(5).setCellValue("元");//
		    }
		}
	    }
	    sheet.getRow(11).setHeight((short) (29 * 20));
	    sheet.getRow(11).getCell(0).setCellValue("合计");
	    if (total != 0) {
		sheet.getRow(11).getCell(3).setCellValue(total);// 合计
	    }
	    sheet.getRow(12).setHeight((short) (25.25 * 20));
	    Region region = new Region((short) 12, (short) 0, (short) 12, (short) 3);
	    sheet.addMergedRegion(region);
	    Region region1 = new Region((short) 12, (short) 4, (short) 12, (short) 7);
	    sheet.addMergedRegion(region1);
	    // 得到所有合并区域
	    sheet.getNumMergedRegions();
	    String perSon = (String) caseInfo.get("catchpole_name");
	    sheet.getRow(12).getCell(0).setCellValue("    联系人：" + (perSon == null ? "" : perSon));
	    sheet.getRow(12).getCell(4).setCellValue("联系电话：0768-2210293");
	}

	return wb;
    }

    private HSSFWorkbook proof4(HSSFWorkbook wb, Map<String, Object> caseInfo, String temp) {
	HSSFSheet sheet = wb.getSheetAt(0);
	CaseInfo caseI = (CaseInfo) caseInfo.get("caseInfo");
	String regiNo = caseI.getRegiNo();
	String songNo = regiNo.replace("立", "送");
	songNo = songNo.replace("号", "-4号");
	String chuNo = regiNo.replace("立", "处");
	String place = (String) caseInfo.get("place");
	String way = (String) caseInfo.get("way");
	String perS = (String) caseInfo.get("per_respon");
	CaseInfoSlave slave = (CaseInfoSlave) caseInfo.get("caseSlave");
	sheet.getRow(2).getCell(0).setCellValue(songNo == null ? "" : songNo);
	sheet.getRow(3).getCell(3).setCellValue(chuNo == null ? "" : chuNo);
	sheet.getRow(5).getCell(1).setCellValue(perS == null ? "" : perS);
	sheet.getRow(6).getCell(1).setCellValue(place == null ? "" : place);
	sheet.getRow(7).getCell(1).setCellValue(way == null ? "" : way);
	sheet.getRow(16).getCell(2)
		.setCellValue(slave.getPenaltyDate() == null ? "" : sdf.format(slave.getPenaltyDate()));
	return wb;

    }

    private HSSFWorkbook proof3(HSSFWorkbook wb, Map<String, Object> caseInfo, String temp) {
	HSSFSheet sheet = wb.getSheetAt(0);
	CaseInfo caseI = (CaseInfo) caseInfo.get("caseInfo");
	String regiNo = caseI.getRegiNo();
	String songNo = regiNo.replace("立", "送");
	songNo = songNo.replace("号", "-3号");
	String chuNo = regiNo.replace("立", "处");
	String perS = (String) caseInfo.get("per_respon");
	String place = (String) caseInfo.get("place");
	String way = (String) caseInfo.get("way");
	CaseInfoSlave slave = (CaseInfoSlave) caseInfo.get("caseSlave");
	sheet.getRow(2).getCell(0).setCellValue(songNo == null ? "" : songNo);
	sheet.getRow(3).getCell(3).setCellValue(chuNo == null ? "" : chuNo);
	sheet.getRow(5).getCell(1).setCellValue(perS == null ? "" : perS);
	sheet.getRow(6).getCell(1).setCellValue(place == null ? "" : place);
	sheet.getRow(7).getCell(1).setCellValue(way == null ? "" : way);
	sheet.getRow(16).getCell(2)
		.setCellValue(slave.getInInformDate() == null ? "" : sdf.format(slave.getInInformDate()));
	return wb;

    }

    private HSSFWorkbook proof2(HSSFWorkbook wb, Map<String, Object> caseInfo, String temp) {
	HSSFSheet sheet = wb.getSheetAt(0);
	CaseInfo caseI = (CaseInfo) caseInfo.get("caseInfo");
	String regiNo = caseI.getRegiNo();
	String songNo = regiNo.replace("立", "送");
	songNo = songNo.replace("号", "-2号");
	String perS = (String) caseInfo.get("per_respon");
	String place = (String) caseInfo.get("place");
	String way = (String) caseInfo.get("way");
	String chuNo = (String) caseInfo.get("wenhao");
	sheet.getRow(2).getCell(0).setCellValue(songNo == null ? "" : songNo);
	sheet.getRow(3).getCell(3).setCellValue(chuNo == null ? "" : chuNo);
	sheet.getRow(5).getCell(1).setCellValue(perS == null ? "" : perS);
	sheet.getRow(6).getCell(1).setCellValue(place == null ? "" : place);
	sheet.getRow(7).getCell(1).setCellValue(way == null ? "" : way);
	sheet.getRow(16).getCell(2).setCellValue("年     月     日");
	return wb;

    }

    private HSSFWorkbook proof1(HSSFWorkbook wb, Map<String, Object> caseInfo, String temp) {
	HSSFSheet sheet = wb.getSheetAt(0);
	CaseInfo caseI = (CaseInfo) caseInfo.get("caseInfo");
	String regiNo = caseI.getRegiNo();
	String songNo = regiNo.replace("立", "送");
	songNo = songNo.replace("号", "-1号");
	String chuNo = regiNo.replace("立", "处");
	String perS = (String) caseInfo.get("per_respon");
	String place = (String) caseInfo.get("place");
	String way = (String) caseInfo.get("way");

	sheet.getRow(2).getCell(0).setCellValue(songNo == null ? "" : songNo);
	sheet.getRow(3).getCell(3).setCellValue(chuNo == null ? "" : chuNo);
	sheet.getRow(5).getCell(1).setCellValue(perS == null ? "" : perS);
	sheet.getRow(6).getCell(1).setCellValue(place == null ? "" : place);
	sheet.getRow(7).getCell(1).setCellValue(way == null ? "" : way);
	sheet.getRow(16).getCell(2).setCellValue("年     月     日");
	return wb;
    }

    /**
     * 
     * @Description: 导出卷烟价值估算表 @param @param wb @param @param
     *               caseInfo @param @param template @param @return @author
     *               shizh @date 2017年3月8日 下午3:15:51 @throws
     */
    private HSSFWorkbook cigarWorthTemp(HSSFWorkbook wb, Map<String, Object> caseInfo, String template) {
	HSSFSheet sheet = wb.getSheetAt(0);
	HSSFCell date = sheet.getRow(1).getCell(3);
	HSSFCell caseCause = sheet.getRow(2).getCell(1);
	HSSFCell caseNo = sheet.getRow(2).getCell(3);
	HSSFCell caseDesc = sheet.getRow(3).getCell(1);
	Date evalDate = (Date) caseInfo.get("eval_date");
	date.setCellValue(evalDate == null ? "" : sdf.format(evalDate));
	String value = (String) caseInfo.get("case_cause");
	caseCause.setCellValue(value == null ? "" : value);
	String value2 = (String) caseInfo.get("regi_no");
	caseNo.setCellValue(value2 == null ? "" : value2);
	caseDesc.setCellValue(template);
	return wb;
    }

    /**
     * 
     * @Description: 导出结案报告表 @param @param wb @param @param
     *               caseInfo @param @param template @param @return @author
     *               shizh @date 2017年3月8日 下午3:16:21 @throws
     */
    private HSSFWorkbook caseEndTemp(HSSFWorkbook wb, Map<String, Object> caseInfo, Map<String, String> template) {
	HSSFSheet sheet = wb.getSheetAt(0);
	String value = (String) caseInfo.get("case_cause");
	sheet.getRow(2).getCell(1).setCellValue(value == null ? "" : value);
	Date date = (Date) caseInfo.get("crime_date");
	sheet.getRow(3).getCell(1).setCellValue(date == null ? "" : sdf.format(date));
	String value2 = (String) caseInfo.get("catchpole_name");
	sheet.getRow(3).getCell(3).setCellValue(value2 == null ? "" : value2);
	String value3 = (String) caseInfo.get("per_respon");
	sheet.getRow(4).getCell(1).setCellValue(value3 == null ? "" : value3);
	String value4 = (String) caseInfo.get("premises");
	sheet.getRow(4).getCell(3).setCellValue(value4 == null ? "" : value4);
	sheet.getRow(5).getCell(1).setCellValue(template.get("tag1"));
	sheet.getRow(8).getCell(1).setCellValue(template.get("tag2"));
	sheet.getRow(9).getCell(1).setCellValue(template.get("tag3"));
	return wb;
    }

    /**
     * 
     * @Description: 导出处罚决定表 @param @param wb @param @param
     *               caseInfo @param @param template @param @return @author
     *               shizh @date 2017年3月8日 下午3:16:42 @throws
     */
    private HSSFWorkbook punnishDecisionTemp(HSSFWorkbook wb, Map<String, Object> caseInfo, String template) {
	HSSFSheet sheet = wb.getSheetAt(0);
	String regiNo = (String) caseInfo.get("regi_no");
	String chuNo = regiNo.replace("立", "处");
	sheet.getRow(2).getCell(0).setCellValue(chuNo == null ? "" : chuNo);
	String string = (String) caseInfo.get("per_respon");
	sheet.getRow(3).getCell(0).setCellValue("当事人：" + (string == null ? "" : string));
	String string2 = (String) caseInfo.get("id_card");
	sheet.getRow(3).getCell(2).setCellValue("有效证件及号码：身份证号码" + (string2 == null ? "" : string2));
	Object sexvalue = caseInfo.get("sex");
	if (sexvalue == null) {
	    sexvalue = 0;
	}
	sheet.getRow(4).getCell(0).setCellValue("性别：" + ((int) sexvalue == 0 ? "女" : "男"));
	String bone = IdCardUtils.getBirthByIdCard(string2 == null ? "" : string2);
	if (bone != "") {
	    StringBuilder sb = new StringBuilder();

	    sb.append(bone.substring(0, 4));
	    sb.append("年");
	    sb.append(bone.substring(4, 6));
	    sb.append("月");
	    sb.append(bone.substring(6));
	    sb.append("日");
	    bone = sb.toString();
	}
	sheet.getRow(4).getCell(2).setCellValue("出生年月：" + bone);
	String string3 = (String) caseInfo.get("id_card_addr");
	sheet.getRow(5).getCell(0).setCellValue("住址：" + (string3 == null ? "" : string3));
	sheet.getRow(6).getCell(0).setCellValue(template);
	return wb;

    }

    /**
     * 
     * @Description: 导出行政处罚事先告知书 @param @param wb @param @param
     *               caseInfo @param @param template @param @return @author
     *               shizh @date 2017年3月8日 下午3:17:05 @throws
     */
    private HSSFWorkbook punnishToKonwnTemp(HSSFWorkbook wb, Map<String, Object> caseInfo, String template) {
	HSSFSheet sheet = wb.getSheetAt(0);
	sheet.getRow(2).getCell(0).setCellValue(caseInfo.get("regi_no").toString());
	String per = (String) caseInfo.get("per_respon");
	sheet.getRow(3).getCell(0).setCellValue(per + ":\r\n" + template);
	sheet.getRow(5).getCell(0).setCellValue("联系人：　　　　　　　　" + "          联系电话：" + "\r\n地址：潮州市新洋路中段烟草大厦" + "\r\n"
		+ "\r\n                               广东省潮州市烟草专卖局 ");
	Date date = (Date) caseInfo.get("insp_date");
	String dateTime = date == null ? "" : sdf.format(date);
	sheet.getRow(6).getCell(1).setCellValue(dateTime);
	return wb;

    }

    /**
     * 
     * @Description: 导出案件处理审批表 @param @param wb @param @param
     *               caseInfo @param @param template @param @return @author
     *               shizh @date 2017年3月8日 下午3:17:22 @throws
     */
    private HSSFWorkbook caseDealTemp(HSSFWorkbook wb, Map<String, Object> caseInfo, Map<String, String> template) {
	HSSFSheet sheet = wb.getSheetAt(0);
	String value = (String) caseInfo.get("case_cause");
	sheet.getRow(2).getCell(1).setCellValue(value == null ? "" : value);
	String value2 = (String) caseInfo.get("regi_no");
	sheet.getRow(3).getCell(1).setCellValue(value2 == null ? "" : value2);
	Date date = (Date) caseInfo.get("crime_date");
	sheet.getRow(3).getCell(3).setCellValue(date == null ? "" : sdf.format(date));
	String value3 = (String) caseInfo.get("corp_name");
	sheet.getRow(4).getCell(2).setCellValue(value3 == null ? "" : value3);
	String value4 = (String) caseInfo.get("case_addr");
	sheet.getRow(5).getCell(2).setCellValue(value4 == null ? "" : value4);
	String value5 = (String) caseInfo.get("per_respon");
	sheet.getRow(6).getCell(2).setCellValue(value5 == null ? "" : value5);
	Object sexvalue = caseInfo.get("sex");
	if (sexvalue == null) {
	    sexvalue = 0;
	}
	sheet.getRow(6).getCell(4).setCellValue((int) sexvalue == 0 ? "女" : "男");
	String value6 = (String) caseInfo.get("nation");
	sheet.getRow(6).getCell(6).setCellValue(value6 == null ? "" : value6);
	String string = (String) caseInfo.get("id_card");
	sheet.getRow(6).getCell(8).setCellValue("身份证号码：\r\n" + (string == null ? "" : string));
	String value7 = (String) caseInfo.get("id_card_addr");
	sheet.getRow(7).getCell(2).setCellValue(value7 == null ? "" : value7);
	String value8 = (String) caseInfo.get("phone");
	sheet.getRow(7).getCell(8).setCellValue(value8 == null ? "" : value8);
	sheet.getRow(9).getCell(1).setCellValue(template.get("tag1"));
	sheet.getRow(11).getCell(1).setCellValue(template.get("tag2"));
	sheet.getRow(12).getCell(1).setCellValue(template.get("tag3"));
	return wb;

    }

    /**
     * 
     * @Description: 导出调查终结报告 @param @param wb @param @param
     *               caseInfo @param @param template @param @return @author
     *               shizh @date 2017年3月8日 下午3:17:38 @throws
     */
    private HSSFWorkbook inspectEndTemp(HSSFWorkbook wb, Map<String, Object> caseInfo, Map<String, String> template) {
	HSSFSheet sheet = wb.getSheetAt(0);
	String value = (String) caseInfo.get("case_cause");
	sheet.getRow(2).getCell(1).setCellValue(value == null ? "" : value);
	Date date = (Date) caseInfo.get("crime_date");
	sheet.getRow(3).getCell(1).setCellValue(date == null ? "" : sdf.format(date));
	String value2 = (String) caseInfo.get("catchpole_name");
	sheet.getRow(3).getCell(3).setCellValue(value2 == null ? "" : value2);
	String value3 = (String) caseInfo.get("per_respon");
	sheet.getRow(4).getCell(1).setCellValue(value3 == null ? "" : value3);
	String string = (String) caseInfo.get("id_card");
	sheet.getRow(4).getCell(4).setCellValue("身份证号码：\r\n" + (string == null ? "" : string));
	sheet.getRow(5).getCell(1).setCellValue(template.get("tag1"));
	sheet.getRow(8).getCell(1).setCellValue(value == null ? "" : "    " + (value.toString().replace("涉嫌", "")));
	sheet.getRow(9).getCell(1).setCellValue(template.get("tag2"));
	sheet.getRow(10).getCell(1).setCellValue(template.get("tag3"));
	return wb;

    }

    /**
     * 
     * @Description: 导出鉴定结论表 @param @param wb @param @param
     *               caseInfo @param @param template @param @return @author
     *               shizh @date 2017年3月8日 下午3:18:00 @throws
     */
    private HSSFWorkbook inspectResTemp(HSSFWorkbook wb, Map<String, Object> caseInfo, String template) {
	HSSFSheet sheet = wb.getSheetAt(0);
	String per = (String) caseInfo.get("per_respon");
	sheet.getRow(2).getCell(1).setCellValue(per + ":\r\n" + template);
	Date date = (Date) caseInfo.get("insp_repo_date");
	String dateTime = date == null ? "" : sdf.format(date);
	sheet.getRow(5).getCell(2).setCellValue(dateTime);
	return wb;

    }

    /**
     * 
     * @Description: 导出立案报告表 @param @param wb @param @param
     *               caseInfo @param @param template @param @return @author
     *               shizh @date 2017年3月8日 下午3:18:36 @throws
     */
    private HSSFWorkbook caseReportTemp(HSSFWorkbook wb, Map<String, Object> caseInfo, Map<String, String> template) {
	HSSFSheet sheet = wb.getSheetAt(0);
	Date date = (Date) caseInfo.get("crime_date");
	Date date2 = (Date) caseInfo.get("crime_start_time");
	String dateTime = (date == null ? "" : sdf.format(date)) + (date2 == null ? "" : sdfTime.format(date2));
	String value = (String) caseInfo.get("regi_no");
	sheet.getRow(2).getCell(0).setCellValue(value == null ? "" : value);
	String value2 = (String) caseInfo.get("case_cause");

	sheet.getRow(3).getCell(1).setCellValue(value2 == null ? "" : value2);
	String value3 = (String) caseInfo.get("case_source");
	sheet.getRow(4).getCell(1).setCellValue(value3 == null ? "" : value3);
	sheet.getRow(5).getCell(1).setCellValue(dateTime);
	String value4 = (String) caseInfo.get("case_addr");
	sheet.getRow(5).getCell(3).setCellValue(value4 == null ? "" : value4);
	String value5 = (String) caseInfo.get("per_respon");
	sheet.getRow(6).getCell(1).setCellValue(value5 == null ? "" : value5);
	String string = (String) caseInfo.get("id_card");
	sheet.getRow(6).getCell(3).setCellValue("身份证号码：\r\n" + (string == null ? "" : string));
	String value6 = (String) caseInfo.get("id_card_addr");
	sheet.getRow(7).getCell(1).setCellValue(value6 == null ? "" : value6);
	sheet.getRow(8).getCell(1).setCellValue(template.get("tag1"));
	sheet.getRow(11).getCell(1).setCellValue(template.get("tag2"));
	return wb;

    }

    /**
     * 
     * @Description: 导出检查笔录表 @param @param wb @param @param
     *               caseInfo @param @param template @param @return @author
     *               shizh @date 2017年3月7日 下午2:59:17 @throws
     */
    public HSSFWorkbook inspectTemp(HSSFWorkbook wb, Map<String, Object> caseInfo, String template) {
	HSSFSheet sheet = wb.getSheetAt(0);
	// 在相应的单元格进行赋值
	Date inaStDate = (Date) caseInfo.get("inqu_start_date");
	Date inaEndDate = (Date) caseInfo.get("inqu_end_date");
	Date inaStTime = (Date) caseInfo.get("inqu_start_time");
	Date inaEndTime = (Date) caseInfo.get("inqu_end_time");
	String insTime = (inaStDate == null ? "" : sdf.format(inaStDate))
		+ (inaStTime == null ? "" : sdfTime.format(inaStTime)) + "至"
		+ (inaEndDate == null ? "" : sdf.format(inaEndDate))
		+ (inaEndTime == null ? "" : sdfTime.format(inaEndTime));
	HSSFCell time = sheet.getRow(3).getCell(1);
	time.setCellValue(insTime);
	HSSFCell caseAddr = sheet.getRow(3).getCell(7);
	String value = (String) caseInfo.get("case_addr");
	caseAddr.setCellValue(value == null ? "" : value);
	HSSFCell party = sheet.getRow(4).getCell(1);
	String value2 = (String) caseInfo.get("per_respon");
	party.setCellValue(value2 == null ? "" : value2);
	HSSFCell sex = sheet.getRow(5).getCell(1);
	HSSFCell age = sheet.getRow(5).getCell(3);
	HSSFCell job = sheet.getRow(5).getCell(5);
	HSSFCell idcard = sheet.getRow(5).getCell(7);
	HSSFCell phone = sheet.getRow(6).getCell(1);
	HSSFCell addr = sheet.getRow(7).getCell(1);
	HSSFCell cont = sheet.getRow(8).getCell(1);
	Object sexvalue = caseInfo.get("sex");
	if (sexvalue == null) {
	    sexvalue = 0;
	}
	sex.setCellValue((int) sexvalue == 0 ? "女" : "男");
	Object agevalue = caseInfo.get("age");
	if (agevalue == null) {
	    agevalue = 0;
	}
	age.setCellValue((int) agevalue);
	String value3 = (String) caseInfo.get("jobs");
	job.setCellValue(value3 == null ? "" : value3);
	String string = (String) caseInfo.get("id_card");
	idcard.setCellValue("身份证号码：" + (string == null ? "" : string));
	String value4 = (String) caseInfo.get("phone");
	phone.setCellValue(value4 == null ? "" : value4);
	String value5 = (String) caseInfo.get("id_card_addr");
	addr.setCellValue(value5 == null ? "" : value5);
	// cont.setCellStyle(cellStyle);
	cont.setCellValue(template);
	return wb;
    }

    /**
     * 
     * @Description: 导出进出存帐表 @param @param wb @param @param
     *               caseInfo @param @param template @param @return @author
     *               yuyf @date 2017年3月24日 下午2:59:17 @throws
     */
    public HSSFWorkbook cigarAccount(HSSFWorkbook wb, Map<String, Object> caseInfo, String template) {
	HSSFSheet sheet = wb.getSheetAt(0);
	HSSFCellStyle cellStyle = wb.createCellStyle();
	this.cellStyle(cellStyle);
	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	HSSFFont fontNum = wb.createFont();
	fontNum.setFontHeightInPoints((short) 12);// 设置字体大小
	fontNum.setFontName("宋体");
	cellStyle.setFont(fontNum);
	cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.0"));
	cellStyle.setWrapText(true); // 自动换行

	HSSFCellStyle cellStyle2 = wb.createCellStyle(); //
	this.cellStyle(cellStyle2);
	cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	HSSFFont fontCh = wb.createFont();
	fontCh.setFontName("宋体");
	fontCh.setFontHeightInPoints((short) 11);// 设置字体大小
	cellStyle2.setFont(fontCh);
	// HSSFSheet sheet = wb.createSheet();
	// 在相应的单元格进行赋值
	String caseNo = (String) caseInfo.get("case_no");
	String regiNo = (String) caseInfo.get("regi_no");

	List<CaseCigar> list = (List<CaseCigar>) caseInfo.get("cigarList");
	Date date = (Date) caseInfo.get("crime_date");

	Date outDate = (Date) caseInfo.get("cigar_outdate");
	String dateTime = date == null ? "" : sdf.format(date);
	sheet.getRow(0).getCell(8).setCellValue(caseNo == null ? "" : caseNo);
	sheet.getRow(1).getCell(0).setCellValue(regiNo == null ? "" : regiNo);
	sheet.getRow(3).getCell(2).setCellValue(dateTime);
	sheet.getRow(3).getCell(4).setCellValue(outDate == null ? "" : sdf.format(outDate));
	String name = "";
	int number = 0;
	int inspectNum = 0;
	int returnNum = 0;
	int inAccount = 0;
	int inspectAccount = 0;
	int outAccount = 0;
	int jiaCun = 0;
	int siCun = 0;
	int feiCun = 0;
	int size = list.size();
	// 创建body单元格（数据+合计+备注）
	if (size < 10) {
	    size = 10;
	}
	for (int i = 0; i < size + 4; i++) {
	    HSSFRow row = sheet.createRow(i + 5);
	    if (i > size) {
		sheet.getRow(size + 6).setHeight((short) (14.25 * 20));// 设置行高
	    } else {
		sheet.getRow(i + 5).setHeight((short) (22 * 20));
	    }
	    for (int j = 0; j < 9; j++) {
		HSSFCell cigarCell = row.createCell(j);
		if (i < size) {

		    if (j == 0 || j > 1) {
			sheet.getRow(i + 5).getCell(j).setCellStyle(cellStyle);// 样式
		    }
		    if (j == 1) {
			sheet.getRow(i + 5).getCell(j).setCellStyle(cellStyle2);// 样式
		    }
		} else {
		    sheet.getRow(i + 5).getCell(j).setCellStyle(cellStyle);// 样式
		}

	    }
	}

	for (int i = 0; i < list.size(); i++) {
	    name = list.get(i).getName();
	    number = list.get(i).getNumber();
	    inspectNum = list.get(i).getInspectNum();
	    returnNum = list.get(i).getReturnNum();
	    String inspectResult = list.get(i).getInspectResult();
	    inAccount += number;
	    inspectAccount += inspectNum;
	    outAccount += returnNum;
	    int cun = number - inspectNum + returnNum;
	    sheet.getRow(i + 5).getCell(0).setCellValue(i + 1);// 编号
	    sheet.getRow(i + 5).getCell(1).setCellValue(name);// 物品名称
	    if (number != 0) {
		sheet.getRow(i + 5).getCell(2).setCellValue(number);// 入库数量
	    }
	    if (inspectNum != 0) {
		sheet.getRow(i + 5).getCell(3).setCellValue(inspectNum);// 送检
	    }

	    if (cun != 0) {
		if ("假".equals(inspectResult)) {
		    sheet.getRow(i + 5).getCell(6).setCellValue(cun);
		    jiaCun += cun;
		} else if ("私".equals(inspectResult)) {
		    sheet.getRow(i + 5).getCell(7).setCellValue(cun);
		    siCun += cun;
		} else if ("非".equals(inspectResult)) {
		    sheet.getRow(i + 5).getCell(4).setCellValue(cun);
		    feiCun += cun;
		}
	    }

	}
	sheet.getRow(size + 5).getCell(1).setCellValue("总计");
	sheet.getRow(size + 5).getCell(2).setCellValue(inAccount);
	sheet.getRow(size + 5).getCell(3).setCellValue(inspectAccount);
	sheet.getRow(size + 5).getCell(4).setCellValue(feiCun);
	sheet.getRow(size + 5).getCell(6).setCellValue(jiaCun);
	sheet.getRow(size + 5).getCell(7).setCellValue(siCun);
	Region region = new Region((short) size + 6, (short) 0, (short) size + 8, (short) 0);// 合并从第rowFrom行columnFrom列
	Region region2 = new Region((short) size + 6, (short) 1, (short) size + 8, (short) 8);
	sheet.addMergedRegion(region);// 到rowTo行columnTo的区域
	sheet.addMergedRegion(region2);
	// 得到所有合并区域
	sheet.getNumMergedRegions();
	sheet.getRow(size + 6).getCell(0).setCellValue("备注");

	return wb;
    }

    private HSSFWorkbook cigarPricing(HSSFWorkbook wb, Map<String, Object> caseInfo, String temp) {
	HSSFSheet sheet = wb.getSheetAt(0);
	HSSFFont fontNum = wb.createFont();
	fontNum.setFontHeightInPoints((short) 14);// 设置字体大小
	fontNum.setFontName("宋体");
	HSSFFont fontNum2 = wb.createFont();
	fontNum2.setFontHeightInPoints((short) 12);// 设置字体大小
	fontNum2.setFontName("宋体");
	HSSFCellStyle cellStyle = wb.createCellStyle();
	cellStyle.setFont(fontNum);
	this.cellStyle(cellStyle);
	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle.setFont(fontNum2);
	cellStyle.setWrapText(true); // 自动换行

	HSSFCellStyle cellStyle2 = wb.createCellStyle();
	this.cellStyle(cellStyle2);
	cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle2.setFont(fontNum2);
	cellStyle2.setWrapText(true); // 自动换行
	cellStyle2.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));

	HSSFCellStyle cellStyle3 = wb.createCellStyle();
	cellStyle3.setFont(fontNum);
	cellStyle3.setWrapText(true); // 自动换行
	cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直

	HSSFCellStyle cellStyle4 = wb.createCellStyle();
	cellStyle4.setFont(fontNum);
	cellStyle4.setWrapText(true); // 自动换行
	cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

	String regiNo = (String) caseInfo.get("regi_no");// 获取数据
	Date date = (Date) caseInfo.get("crime_date");
	List<CaseCigar> list = (List<CaseCigar>) caseInfo.get("cigarList");
	CaseInfoSlave caseSlave = (CaseInfoSlave) caseInfo.get("caseSlave");
	int size = list.size();
	String name = "";
	int number = 0;
	double retailPrice = 0.00;
	double totalValue = 0.00;
	if (size >= 10) {
	    // 创建body单元格（数据+）
	    for (int i = 0; i < size + 6; i++) {
		HSSFRow row = sheet.createRow(i + 4);

		// 设置行高
		if (i < size) {
		    sheet.getRow(i + 4).setHeight((short) (26 * 20));
		    for (int j = 0; j < 6; j++) {
			HSSFCell cigarCell = row.createCell(j);
			if (j == 3 || j == 4) {
			    sheet.getRow(i + 4).getCell(j).setCellStyle(cellStyle2);// 样式
			} else {
			    sheet.getRow(i + 4).getCell(j).setCellStyle(cellStyle);// 样式
			}
		    }
		    if (list.size() > 0) {
			name = list.get(i).getName();
			number = list.get(i).getNumber();
			retailPrice = list.get(i).getRetailPrice();
			totalValue = list.get(i).getTotalValue();
			sheet.getRow(i + 4).getCell(0).setCellValue(i + 1);// 编号
			sheet.getRow(i + 4).getCell(1).setCellValue(name);// 品种规格
			sheet.getRow(i + 4).getCell(2).setCellValue(number);// 数量（条）
			sheet.getRow(i + 4).getCell(3).setCellValue(retailPrice);// 单价（元）
			sheet.getRow(i + 4).getCell(4).setCellValue(totalValue);// 合计（元）
		    }

		}

	    }

	    sheet.getRow(size + 4).setHeight((short) (41.5 * 20));

	    sheet.getRow(size + 5).setHeight((short) (36 * 20));

	    sheet.getRow(size + 6).setHeight((short) (31.25 * 20));

	    sheet.getRow(size + 7).setHeight((short) (21.5 * 20));

	    sheet.getRow(size + 8).setHeight((short) (19.25 * 20));

	    for (int i = size + 4; i < size + 9; i++) {
		for (int j = 0; j < 6; j++) {
		    HSSFCell cigarCell = sheet.getRow(i).createCell(j);
		    if (i == size + 7 || i == size + 8) {
			sheet.getRow(i).getCell(j).setCellStyle(cellStyle4);// 样式
		    } else {
			sheet.getRow(i).getCell(j).setCellStyle(cellStyle3);// 样式
		    }
		}
	    }
	    Region region = new Region((short) size + 4, (short) 0, (short) size + 4, (short) 5);
	    Region region2 = new Region((short) size + 5, (short) 0, (short) size + 5, (short) 1);
	    Region region3 = new Region((short) size + 6, (short) 0, (short) size + 6, (short) 1);
	    Region region4 = new Region((short) size + 7, (short) 3, (short) size + 7, (short) 5);
	    Region region5 = new Region((short) size + 8, (short) 3, (short) size + 8, (short) 5);
	    sheet.addMergedRegion(region);
	    sheet.addMergedRegion(region2);
	    sheet.addMergedRegion(region3);
	    sheet.addMergedRegion(region4);
	    sheet.addMergedRegion(region5);
	    // 得到所有合并区域
	    sheet.getNumMergedRegions();
	    // 写入数据
	    sheet.getRow(2).getCell(0).setCellValue(
		    "   潮州市烟草专卖局" + regiNo + "(" + (date == null ? "" : sdf.format(date)) + ")查获的涉案烟草专卖品价格如下：");
	    sheet.getNumMergedRegions();
	    sheet.getRow(size + 4).getCell(0).setCellValue("   依据广东烟草潮州市有限责任公司涉案烟草专卖品估价管理小组出具的《涉案烟草专卖品价格证明》。");
	    sheet.getRow(size + 5).getCell(0).setCellValue("   经办人：");
	    sheet.getRow(size + 6).getCell(0).setCellValue("   当事人：");
	    sheet.getRow(size + 7).getCell(3).setCellValue("潮州市烟草专卖局");
	    if (caseSlave != null) {
		Date evalDate = caseSlave.getEvalDate();
		sheet.getRow(size + 8).getCell(3).setCellValue(evalDate == null ? "" : sdf.format(evalDate));
	    }
	}
	if (size < 10) {
	    for (int i = 0; i < size; i++) {
		HSSFRow row = sheet.createRow(i + 4);

		// 设置行高
		if (i < size) {
		    sheet.getRow(i + 4).setHeight((short) (26 * 20));
		    for (int j = 0; j < 6; j++) {
			HSSFCell cigarCell = row.createCell(j);
			if (j == 3 || j == 4) {
			    sheet.getRow(i + 4).getCell(j).setCellStyle(cellStyle2);// 样式
			} else {
			    sheet.getRow(i + 4).getCell(j).setCellStyle(cellStyle);// 样式
			}
		    }
		    if (list.size() > 0) {
			name = list.get(i).getName();
			number = list.get(i).getNumber();
			retailPrice = list.get(i).getRetailPrice();
			totalValue = list.get(i).getTotalValue();
			sheet.getRow(i + 4).getCell(0).setCellValue(i + 1);// 编号
			sheet.getRow(i + 4).getCell(1).setCellValue(name);// 品种规格
			sheet.getRow(i + 4).getCell(2).setCellValue(number);// 数量（条）
			sheet.getRow(i + 4).getCell(3).setCellValue(retailPrice);// 单价（元）
			sheet.getRow(i + 4).getCell(4).setCellValue(totalValue);// 合计（元）
		    }

		}

	    }

	    for (int i = 0; i < 10 - size; i++) {
		HSSFRow row = sheet.createRow(size + 4 + i);
		sheet.getRow(size + 4 + i).setHeight((short) (26 * 20));
		for (int j = 0; j < 6; j++) {
		    HSSFCell cigarCell = row.createCell(j);
		    if (j == 3 || j == 4) {
			sheet.getRow(size + 4 + i).getCell(j).setCellStyle(cellStyle2);// 样式
		    } else {
			sheet.getRow(size + 4 + i).getCell(j).setCellStyle(cellStyle);// 样式
		    }
		}
	    }
	    for (int i = 0; i < 6; i++) {
		HSSFRow row = sheet.createRow(14 + i);

	    }
	    sheet.getRow(14).setHeight((short) (41.5 * 20));

	    sheet.getRow(15).setHeight((short) (36 * 20));

	    sheet.getRow(16).setHeight((short) (31.25 * 20));

	    sheet.getRow(17).setHeight((short) (21.5 * 20));

	    sheet.getRow(18).setHeight((short) (19.25 * 20));

	    for (int i = 14; i < 19; i++) {
		for (int j = 0; j < 6; j++) {
		    HSSFCell cigarCell = sheet.getRow(i).createCell(j);
		    if (i == 17 || i == 18) {
			sheet.getRow(i).getCell(j).setCellStyle(cellStyle4);// 样式
		    } else {
			sheet.getRow(i).getCell(j).setCellStyle(cellStyle3);// 样式
		    }
		}
	    }
	    Region region = new Region((short) 14, (short) 0, (short) 14, (short) 5);
	    Region region2 = new Region((short) 15, (short) 0, (short) 15, (short) 1);
	    Region region3 = new Region((short) 16, (short) 0, (short) 16, (short) 1);
	    Region region4 = new Region((short) 17, (short) 3, (short) 17, (short) 5);
	    Region region5 = new Region((short) 18, (short) 3, (short) 18, (short) 5);
	    sheet.addMergedRegion(region);
	    sheet.addMergedRegion(region2);
	    sheet.addMergedRegion(region3);
	    sheet.addMergedRegion(region4);
	    sheet.addMergedRegion(region5);
	    // 得到所有合并区域
	    sheet.getNumMergedRegions();
	    // 写入数据
	    sheet.getRow(2).getCell(0).setCellValue(
		    "   潮州市烟草专卖局" + regiNo + "(" + (date == null ? "" : sdf.format(date)) + ")查获的涉案烟草专卖品价格如下：");
	    sheet.getNumMergedRegions();
	    sheet.getRow(14).getCell(0).setCellValue("   依据广东烟草潮州市有限责任公司涉案烟草专卖品估价管理小组出具的《涉案烟草专卖品价格证明》。");
	    sheet.getRow(15).getCell(0).setCellValue("   经办人：");
	    sheet.getRow(16).getCell(0).setCellValue("   当事人：");
	    sheet.getRow(17).getCell(3).setCellValue("潮州市烟草专卖局");
	    if (caseSlave != null) {
		Date evalDate = caseSlave.getEvalDate();
		sheet.getRow(18).getCell(3).setCellValue(evalDate == null ? "" : sdf.format(evalDate));
	    }

	}

	return wb;

    }

    private HSSFWorkbook seizureList(HSSFWorkbook wb, Map<String, Object> caseInfo, String temp) {
	HSSFSheet sheet = wb.getSheetAt(0);
	HSSFFont fontNum = wb.createFont();
	fontNum.setFontHeightInPoints((short) 11);// 设置字体大小
	fontNum.setFontName("宋体");
	HSSFFont fontNum2 = wb.createFont();
	fontNum2.setFontHeightInPoints((short) 12);// 设置字体大小
	fontNum2.setFontName("宋体");

	HSSFCellStyle cellStyle = wb.createCellStyle();
	cellStyle.setFont(fontNum);
	this.cellStyle(cellStyle);
	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle.setFont(fontNum);
	cellStyle.setWrapText(true); // 自动换行

	HSSFCellStyle cellStyle2 = wb.createCellStyle();
	this.cellStyle(cellStyle2);
	cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle2.setFont(fontNum2);
	cellStyle2.setWrapText(true); // 自动换行

	HSSFCellStyle cellStyle3 = wb.createCellStyle();
	this.cellStyle(cellStyle3);
	cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
	cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle3.setFont(fontNum);
	cellStyle3.setWrapText(true); // 自动换行
	cellStyle3.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.0"));

	HSSFCellStyle cellStyle4 = wb.createCellStyle();
	cellStyle4.setFont(fontNum2);
	cellStyle4.setWrapText(true); // 自动换行
	cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
	cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

	HSSFCellStyle cellStyle5 = wb.createCellStyle();
	cellStyle5.setFont(fontNum2);
	String perSon = (String) caseInfo.get("per_respon");
	CaseInfo caseI = (CaseInfo) caseInfo.get("caseInfo");
	List<CaseCigar> jiaCigar = (List<CaseCigar>) caseInfo.get("jia");
	List<CaseCigar> siCigar = (List<CaseCigar>) caseInfo.get("si");

	String regiNo = caseI.getRegiNo();
	int rows = (int) caseInfo.get("rows");
	if (rows < 10) {
	    rows = 10;
	}
	for (int i = 0; i < rows + 8; i++) {
	    HSSFRow row = sheet.createRow(i + 6);
	    // 设置行高
	    if (i < rows) {
		for (int j = 0; j < 6; j++) {
		    HSSFCell cigarCell = row.createCell(j);
		}
		sheet.getRow(i + 6).setHeight((short) (27 * 20));
		sheet.getRow(i + 6).getCell(0).setCellStyle(cellStyle);// 样式

		sheet.getRow(i + 6).getCell(1).setCellStyle(cellStyle2);// 样式
		sheet.getRow(i + 6).getCell(2).setCellStyle(cellStyle3);// 样式
		sheet.getRow(i + 6).getCell(3).setCellStyle(cellStyle);// 样式
		sheet.getRow(i + 6).getCell(4).setCellStyle(cellStyle2);// 样式
		sheet.getRow(i + 6).getCell(5).setCellStyle(cellStyle3);// 样式

	    }
	}
	sheet.getRow(rows + 6).setHeight((short) (8.25 * 20));
	sheet.getRow(rows + 7).setHeight((short) (14.25 * 20));
	sheet.getRow(rows + 8).setHeight((short) (17.25 * 20));
	sheet.getRow(rows + 9).setHeight((short) (38.25 * 20));
	sheet.getRow(rows + 10).setHeight((short) (14.25 * 20));
	sheet.getRow(rows + 11).setHeight((short) (13.5 * 20));
	sheet.getRow(rows + 12).setHeight((short) (14.25 * 20));
	for (int h = rows + 6; h < rows + 13; h++) {
	    for (int j = 0; j < 6; j++) {
		HSSFCell cigarCell = sheet.getRow(h).createCell(j);
		if (h == rows + 10) {
		    sheet.getRow(h).getCell(j).setCellStyle(cellStyle5);// 样式

		} else {
		    sheet.getRow(h).getCell(j).setCellStyle(cellStyle4);// 样式
		}
	    }
	}
	for (int i = 0; i < jiaCigar.size(); i++) {
	    String jname = jiaCigar.get(i).getName();
	    String format = jiaCigar.get(i).getFormat();
	    int num = jiaCigar.get(i).getNumber();
	    sheet.getRow(i + 6).getCell(0).setCellValue(jname == null ? "" : jname);
	    sheet.getRow(i + 6).getCell(1).setCellValue(format == null ? "" : format);
	    /* sheet.getRow(i+6).getCell(2).setCellValue(a-b); */
	    sheet.getRow(i + 6).getCell(2).setCellValue(num);
	}
	for (int i = 0; i < siCigar.size(); i++) {
	    String sname = siCigar.get(i).getName();
	    int num = siCigar.get(i).getNumber();
	    String format = siCigar.get(i).getFormat();
	    sheet.getRow(i + 6).getCell(3).setCellValue(sname == null ? "" : sname);
	    sheet.getRow(i + 6).getCell(4).setCellValue(format == null ? "" : format);
	    sheet.getRow(i + 6).getCell(5).setCellValue(num);
	}
	Region region = new Region((short) rows + 8, (short) 3, (short) rows + 8, (short) 5);
	Region region2 = new Region((short) rows + 10, (short) 0, (short) rows + 10, (short) 1);
	Region region3 = new Region((short) rows + 12, (short) 0, (short) rows + 12, (short) 1);
	Region region4 = new Region((short) rows + 10, (short) 3, (short) rows + 10, (short) 4);
	Region region5 = new Region((short) rows + 12, (short) 3, (short) rows + 12, (short) 4);
	sheet.addMergedRegion(region);
	sheet.addMergedRegion(region2);
	sheet.addMergedRegion(region3);
	sheet.addMergedRegion(region4);
	sheet.addMergedRegion(region5);
	// 得到所有合并区域
	sheet.getNumMergedRegions();
	sheet.getRow(2).getCell(0).setCellValue("当事人：" + (perSon == null ? "" : perSon));
	sheet.getRow(2).getCell(3).setCellValue("案件编号：" + (regiNo == null ? "" : regiNo));
	sheet.getRow(rows + 8).getCell(3).setCellValue("广东省潮州市烟草专卖局");
	sheet.getRow(rows + 10).getCell(0).setCellValue("案件当事人签名：");
	sheet.getRow(rows + 10).getCell(3).setCellValue("案件承办人签名：");
	sheet.getRow(rows + 12).getCell(0).setCellValue("年 　月　 日");
	sheet.getRow(rows + 12).getCell(3).setCellValue("年 　月　 日");

	return wb;

    }

    public void cellStyle(HSSFCellStyle cellStyle) {
	cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
	cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
	cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
	cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

    }

}
