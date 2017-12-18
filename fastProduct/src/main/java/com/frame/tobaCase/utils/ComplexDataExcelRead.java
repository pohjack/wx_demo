/**    
* @Title: ComplexDataExcelRead.java
* @Package com.excel.utils
* @Description: 多类型数据excel导入
* @author: Chensy
* @date 2017年02月22日 下午10:59:010
* @version V1.0
*/
package com.frame.tobaCase.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ComplexDataExcelRead {
    /**
     * 将excel转成map
     * @param is excel文件对象
     * @return
     * @throws BiffException
     * @throws IOException
     */
    public List<Map<String, Object>> readExcel(InputStream is) throws BiffException, IOException {
	Workbook workbook = Workbook.getWorkbook(is);
	// Sheet的下标是从0开始获取第一张Sheet表
	Sheet readsheet = workbook.getSheet(0);
	// 获取Sheet表中所包含的总列数
	int rsColumns = readsheet.getColumns();

	// 获取Sheet表中所包含的总行数
	int rsRows = readsheet.getRows();

	// 表头
	String[] headers = new String[rsColumns];
	// 获取指定单元格的对象引用
	for (int i = 0; i < rsColumns; i++) {
	    headers[i] = readsheet.getCell(i, 0).getContents();
	}
	List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();

	for (int i = 1; i < rsRows; i++) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    for (int j = 0; j < rsColumns; j++) {
		map.put(headers[j], readsheet.getCell(j, i).getContents());
	    }
	    datas.add(map);
	}
	return datas;
    }

    public static void main(String[] args) {
	try {
	    // OutputStream os = response.getOutputStream();
	    List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
	    List<Object> obj;
	    List<Map<String, Object>> datas = new ComplexDataExcelRead()
		    .readExcel(new FileInputStream("C:/Users/Chensy/Desktop/批量模版.xls"));
	    for (Map<String, Object> map : datas) {
		for (String key : map.keySet()) {
		    String cName = "";
		    if ("文件名称,若为空,则使用默认".equals(key)) {
			cName = "A";
		    } else if ("是否使用短地址(是,否),默认否".equals(key)) {
			cName = "B";
		    } else if ("type=url".equals(key)) {
			cName = "C";
		    } else if ("网络地址".equals(key)) {
			cName = "D";
		    }
		    System.out.print(cName + "|" + map.get(key) + "\t");
		}
		System.out.println();
	    }
	} catch (IOException | BiffException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
