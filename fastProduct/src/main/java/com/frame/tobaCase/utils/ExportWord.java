package com.frame.tobaCase.utils;

import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import com.frame.core.commons.utils.DateUtil;
import com.frame.tobaCase.entity.CaseCigar;

public class ExportWord {
    public void createSimpleTable(String fileName, Map<String, Object> caseInfo, HttpServletResponse response)
	    throws Exception {
	OutputStream out = response.getOutputStream();// 取得输出流
	response.reset();// 清空输出流
	response.setContentType("application/x-msdownloadoctet-stream;charset=GB2312");
	response.setHeader("Content-Disposition", "attachment; filename="
		+ new String(String.valueOf(System.currentTimeMillis()).getBytes("gb2312"), "iso8859-1") + ".docx");
	XWPFDocument xdoc = new XWPFDocument();
	XWPFParagraph xp = xdoc.createParagraph();
	xp.setAlignment(ParagraphAlignment.CENTER);
	XWPFRun r1 = xp.createRun();
	r1.setText("估价明细表");
	r1.setFontFamily("宋体");
	r1.setFontSize(16);
	r1.setTextPosition(10);
	r1.setBold(true);
	List<CaseCigar> list = (List<CaseCigar>) caseInfo.get("cigarList");
	int row = 10;
	if (list.size() >= 10) {
	    row = list.size();
	}
	XWPFTable xTable = xdoc.createTable(row + 2, 8);
	int i = 0;
	CTTbl ttbl = xTable.getCTTbl();
	CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
	CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
	CTJc cTJc = tblPr.addNewJc();
	cTJc.setVal(STJc.Enum.forString("center"));
	tblWidth.setW(new BigInteger("8600"));
	tblWidth.setType(STTblWidth.DXA);

	xTable.getRow(i).setHeight(380);
	setCellText(xdoc, xTable.getRow(i).getCell(0), "序号", "", getCellWidth(0));
	setCellText(xdoc, xTable.getRow(i).getCell(1), "条包条形码", "", getCellWidth(1));
	setCellText(xdoc, xTable.getRow(i).getCell(2), "品牌规格", "", getCellWidth(2));
	setCellText(xdoc, xTable.getRow(i).getCell(3), "数量", "", getCellWidth(3));
	setCellText(xdoc, xTable.getRow(i).getCell(4), "建议零售价格", "", getCellWidth(4));
	setCellText(xdoc, xTable.getRow(i).getCell(5), "价格单位", "", getCellWidth(5));
	setCellText(xdoc, xTable.getRow(i).getCell(6), "金额", "", getCellWidth(6));
	setCellText(xdoc, xTable.getRow(i).getCell(7), "备注", "", getCellWidth(7));
	i++;
	setData(i, caseInfo, xTable, xdoc);
	XWPFParagraph p = xdoc.createParagraph();
	XWPFRun pRun = getOrAddParagraphFirstRun(p, false, false);
	pRun = getOrAddParagraphFirstRun(p, false, false);
	String perSon = (String) caseInfo.get("catchpole_name");
	setParagraphRunFontInfo(p, pRun, "   填表人：" + perSon + "                          联系电话：0768-2210293", "宋体", null,
		12, false, false, false, false, null, null, 0, 6, 0);

	response.setHeader("Content-Disposition", "attachment; filename="
		+ new String((fileName + DateUtil.getYearDay()).getBytes("gb2312"), "iso8859-1") + ".docx");
	// FileOutputStream fos = new FileOutputStream(fileName);
	xdoc.write(out);
	out.flush();
	out.close();

    }

    public void setParagraphRunFontInfo(XWPFParagraph p, XWPFRun pRun, String content, String cnFontFamily,
	    String enFontFamily, int fontSize, boolean isBlod, boolean isItalic, boolean isStrike, boolean isShd,
	    String shdColor, STShd.Enum shdStyle, int position, int spacingValue, int indent) {
	CTRPr pRpr = getRunCTRPr(p, pRun);
	if (StringUtils.isNotBlank(content)) {
	    // pRun.setText(content);
	    if (content.contains("\n")) {// System.properties("line.separator")
		String[] lines = content.split("\n");
		pRun.setText(lines[0], 0); // set first line into XWPFRun
		for (int i = 1; i < lines.length; i++) {
		    // add break and insert new text
		    pRun.addBreak();
		    pRun.setText(lines[i]);
		}
	    } else {
		pRun.setText(content, 0);
	    }
	}

	pRun.setFontFamily(cnFontFamily);
	pRun.setFontSize(fontSize);

	// 设置字体样式
	// 加粗
	if (isBlod) {
	    pRun.setBold(isBlod);
	}
	// 倾斜
	if (isItalic) {
	    pRun.setItalic(isItalic);
	}
	// 删除线
	if (isStrike) {
	    pRun.setStrike(isStrike);
	}
	if (isShd) {
	    // 设置底纹
	    CTShd shd = pRpr.isSetShd() ? pRpr.getShd() : pRpr.addNewShd();
	    if (shdStyle != null) {
		shd.setVal(shdStyle);
	    }
	    if (shdColor != null) {
		shd.setColor(shdColor);
		shd.setFill(shdColor);
	    }
	}

	// 设置文本位置
	if (position != 0) {
	    pRun.setTextPosition(position);
	}

    }

    public CTRPr getRunCTRPr(XWPFParagraph p, XWPFRun pRun) {
	CTRPr pRpr = null;
	if (pRun.getCTR() != null) {
	    pRpr = pRun.getCTR().getRPr();
	    if (pRpr == null) {
		pRpr = pRun.getCTR().addNewRPr();
	    }
	} else {
	    pRpr = p.getCTP().addNewR().addNewRPr();
	}
	return pRpr;
    }

    public XWPFRun getOrAddParagraphFirstRun(XWPFParagraph p, boolean isInsert, boolean isNewLine) {
	XWPFRun pRun = null;
	if (isInsert) {
	    pRun = p.createRun();
	} else {
	    if (p.getRuns() != null && p.getRuns().size() > 0) {
		pRun = p.getRuns().get(0);
	    } else {
		pRun = p.createRun();
	    }
	}
	if (isNewLine) {
	    pRun.addBreak();
	}
	return pRun;
    }

    private void setData(int i, Map<String, Object> caseInfo, XWPFTable xTable, XWPFDocument xdoc) {
	List<CaseCigar> list = (List<CaseCigar>) caseInfo.get("cigarList");
	int total = (int) caseInfo.get("total");
	int size = list.size();
	String name = "";
	int number = 0;
	String barcode = "";
	for (int i2 = i; i2 <= size; i2++) {
	    name = list.get(i2 - 1).getName();
	    number = list.get(i2 - 1).getNumber();
	    barcode = list.get(i2 - 1).getBarCode();
	    setCellText(xdoc, xTable.getRow(i2).getCell(0), Integer.toString(i2), null, getCellWidth(0));
	    setCellText(xdoc, xTable.getRow(i2).getCell(1), barcode == null ? "" : barcode, null, getCellWidth(1));
	    setCellText(xdoc, xTable.getRow(i2).getCell(2), name == null ? "" : name, null, getCellWidth(2));
	    setCellText(xdoc, xTable.getRow(i2).getCell(3), Integer.toString(number), null, getCellWidth(3));
	    setCellText(xdoc, xTable.getRow(i2).getCell(4), " ", null, getCellWidth(4));
	    setCellText(xdoc, xTable.getRow(i2).getCell(5), "元", null, getCellWidth(5));
	    setCellText(xdoc, xTable.getRow(i2).getCell(6), " ", null, getCellWidth(6));
	    setCellText(xdoc, xTable.getRow(i2).getCell(7), " ", null, getCellWidth(7));
	}
	int rowNo = 0;
	int blank = 0;
	if (size >= 10) {
	    rowNo = size + i;
	} else {
	    rowNo = 10 + i;
	    blank = 10 - size;
	}
	if (blank > 0) {
	    for (int b = blank; b > 0; b--) {
		setCellText(xdoc, xTable.getRow(10 - b + i).getCell(0), "", null, getCellWidth(0));
		setCellText(xdoc, xTable.getRow(10 - b + i).getCell(1), "", null, getCellWidth(1));
		setCellText(xdoc, xTable.getRow(10 - b + i).getCell(2), " \r\n", null, getCellWidth(2));
		setCellText(xdoc, xTable.getRow(10 - b + i).getCell(3), " ", null, getCellWidth(3));
		setCellText(xdoc, xTable.getRow(10 - b + i).getCell(4), " ", null, getCellWidth(6));
		setCellText(xdoc, xTable.getRow(10 - b + i).getCell(5), " ", null, getCellWidth(7));
		setCellText(xdoc, xTable.getRow(10 - b + i).getCell(6), " ", null, getCellWidth(6));
		setCellText(xdoc, xTable.getRow(10 - b + i).getCell(7), " ", null, getCellWidth(7));
	    }

	}
	setCellText(xdoc, xTable.getRow(rowNo).getCell(0), "合计", null, getCellWidth(0));
	setCellText(xdoc, xTable.getRow(rowNo).getCell(1), "", null, getCellWidth(1));
	setCellText(xdoc, xTable.getRow(rowNo).getCell(2), " \r\n", null, getCellWidth(2));
	setCellText(xdoc, xTable.getRow(rowNo).getCell(3), Integer.toString(total), null, getCellWidth(3));
	setCellText(xdoc, xTable.getRow(rowNo).getCell(4), " ", null, getCellWidth(6));
	setCellText(xdoc, xTable.getRow(rowNo).getCell(5), " ", null, getCellWidth(7));
	setCellText(xdoc, xTable.getRow(rowNo).getCell(6), " ", null, getCellWidth(6));
	setCellText(xdoc, xTable.getRow(rowNo).getCell(7), " ", null, getCellWidth(7));
    }

    private static void setCellText(XWPFDocument xDocument, XWPFTableCell cell, String text, String bgcolor,
	    int width) {
	CTTc cttc = cell.getCTTc();
	CTTcPr cellPr = cttc.addNewTcPr();
	cellPr.addNewTcW().setW(BigInteger.valueOf(width));
	CTVerticalJc va = cellPr.addNewVAlign();
	va.setVal(STVerticalJc.CENTER);
	/*
	 * cell.setColor(bgcolor);
	 * cell.setVerticalAlignment(XWPFVertAlign.CENTER); CTTcPr ctPr =
	 * cttc.addNewTcPr(); ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
	 * cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
	 * cell.setText(text);
	 */

	CTP ctp = CTP.Factory.newInstance();
	XWPFParagraph p = new XWPFParagraph(ctp, cell);
	p.setAlignment(ParagraphAlignment.CENTER);
	XWPFRun run = p.createRun();
	run.setColor("000000");
	run.setFontFamily("宋体");
	run.setFontSize(12);
	run.setText(text);
	cell.setParagraph(p);

    }

    private static int getCellWidth(int index) {
	int cwidth = 160;
	if (index == 0) {
	    cwidth = 550;
	} else if (index == 1) {
	    cwidth = 2000;
	} else if (index == 2) {
	    cwidth = 1900;
	} else if (index == 3) {
	    cwidth = 850;
	} else if (index == 4) {
	    cwidth = 1040;
	} else if (index == 5) {
	    cwidth = 610;
	} else if (index == 6) {
	    cwidth = 1040;
	} else if (index == 7) {
	    cwidth = 610;
	}
	return cwidth;
    }
}