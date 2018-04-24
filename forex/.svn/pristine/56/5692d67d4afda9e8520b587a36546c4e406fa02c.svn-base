package com.talent.forex.util;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReadExcelFileUtil {
	String[][] returnArray;
	public static  ReadExcelFileUtil instance = new ReadExcelFileUtil();
	public  String[][] readExcelFile(InputStream in){
	  try{	
			BufferedInputStream is = new BufferedInputStream(in);
			// 打开HSSFWorkbook
			POIFSFileSystem fs = new POIFSFileSystem(is);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFCell cell = null;
			List<String[]> result = new ArrayList<String[]>();
			int rowSize = 0;
			for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
	            HSSFSheet st = wb.getSheetAt(sheetIndex);
				for (int rowIndex = 0; rowIndex <= st.getLastRowNum(); rowIndex++) {
	            	HSSFRow row = st.getRow(rowIndex);
					if (row == null) {
						continue;
					}
					int tempRowSize = row.getLastCellNum();
					if (tempRowSize > rowSize) {
						rowSize = tempRowSize;
					}
					String[] values = new String[rowSize];
					Arrays.fill(values, "");
					boolean hasValue = false;
	                for (short columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {//循环一行里面的所有列
	                    String value = "";
						cell = row.getCell(columnIndex);
						if (cell != null) {
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_STRING://String类型
								value = cell.getStringCellValue();
								break;
							case HSSFCell.CELL_TYPE_NUMERIC://数字类型
								if (HSSFDateUtil.isCellDateFormatted(cell)) {//判断是不是日期类型
									Date date = cell.getDateCellValue();//获取单元格的日期值
									if (date != null) {
										value = new SimpleDateFormat("yyyy-MM-dd").format(date);//格式化日期
									} else {
										value = "";
									}
								} else {
									value = new DecimalFormat("0").format(cell.getNumericCellValue());//将double类型的转换成String类型
								}
								break;
							case HSSFCell.CELL_TYPE_FORMULA:
								
								if (!cell.getStringCellValue().equals("")) {
									value = cell.getStringCellValue();
								} else {
									value = cell.getNumericCellValue() + "";
								}
								break;
							case HSSFCell.CELL_TYPE_BLANK:
								break;
							case HSSFCell.CELL_TYPE_ERROR:
								value = "";
								break;
							case HSSFCell.CELL_TYPE_BOOLEAN:
								value = (cell.getBooleanCellValue() == true ? "Y" : "N");
								break;
							default:
								value = "";
							}
						}
						if (columnIndex == 0 && value.trim().equals("")) {
							break;
						}
						values[columnIndex] = rightTrim(value).trim();
						hasValue = true;
					}
					if (hasValue) {
						result.add(values);
					}
				}
			}
			is.close();
			returnArray = new String[result.size()][rowSize];
			for (int i = 0; i < returnArray.length; i++) {
				returnArray[i] = (String[]) result.get(i);//二维数组
			}
	    }
	    catch (Exception e) {
			e.printStackTrace();
		}
	    return returnArray;
	}
	
	
	
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}
}
