package api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.alibaba.fastjson.JSON;

import org.apache.poi.ss.usermodel.DateUtil;

public class OpExcel {
	/**
	 * 读取Excel测试，兼容 Excel 2003/2007/2010
	 */
	public Map<String, String> readExcel(String docAddress, int sheetID, int colNum) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> map = new HashMap<>();
		try {
			// 同时支持Excel 2003、2007
			File excelFile = new File(docAddress); // 创建文件对象
			FileInputStream is = new FileInputStream(excelFile); // 文件流
			Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel
															// 2003/2007/2010
															// 都是可以处理的
			// int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
			// **************************************************************************遍历每个Sheet
			// 注意点
			// for (int s = 3; s < 4; s++) {
			Sheet sheet = workbook.getSheetAt(sheetID);
			int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
			System.out.println("----" + rowCount);
			// 遍历每一行
			for (int r = 1; r < rowCount; r++) {
				Row row = sheet.getRow(r);
				// int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
				// *************************************************************************************遍历每一列
				// 注意点
				Cell cell = row.getCell(colNum);
				int cellType = cell.getCellType();
				String cellValue = null;
				switch (cellType) {
				case Cell.CELL_TYPE_STRING: // 文本
					cellValue = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC: // 数字、日期
					if (DateUtil.isCellDateFormatted(cell)) {
						cellValue = fmt.format(cell.getDateCellValue()); // 日期型
					} else {
						DecimalFormat df = new DecimalFormat("#.#########");
						cellValue = df.format(cell.getNumericCellValue()); // 去掉小数点
						// cellValue =
						// String.valueOf(cell.getNumericCellValue()); //数字
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN: // 布尔型
					cellValue = String.valueOf(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_BLANK: // 空白
					cellValue = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_ERROR: // 错误
					cellValue = "错误";
					break;
				case Cell.CELL_TYPE_FORMULA: // 公式
					cellValue = "错误";
					break;
				default:
					cellValue = "错误";
				}
				// System.out.println(cellValue + " ");

				map.put("data_" + r, cellValue);
			}
			// } //sheet
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 向固定行 插入id
	 */
	public void writeExcel(int sheetNum, int colNum, String docAddress, Map<String, String> map) throws Exception {
		File excelFile = new File(docAddress); // 创建文件对象
		FileInputStream is = new FileInputStream(excelFile); // 文件流
		Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel
														// 2003/2007/2010
														// 都是可以处理的
		Sheet sheet = workbook.getSheetAt(sheetNum);
		for (int i = 1; i <= map.size(); i++) {
			Row row = sheet.getRow(i);
			Cell cell0 = row.createCell(colNum);
			if (map.get("data_" + i) == null) {
				cell0.setCellValue("");
			} else {
				cell0.setCellValue(map.get("data_" + i).toString());
			}
			// System.out.println("*******"+map.get("data_"+i));
		}
		OutputStream os = new FileOutputStream(excelFile);
		workbook.write(os);
		os.close();
	}

	public Map<String, Map<String, String>> readManyExcel(String docAddress, int sheetID, int mincolNum,
			int maxcolNum) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Map<String, String>> rowMap = new HashMap<>();
		try {
			// 同时支持Excel 2003、2007
			File excelFile = new File(docAddress); // 创建文件对象
			FileInputStream is = new FileInputStream(excelFile); // 文件流
			Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel
															// 2003/2007/2010
															// 都是可以处理的
			// int sheetCount = workbook.getNumberOfSheets(); //Sheet的数量
			// 遍历每个Sheet
			// for (int s = 0; s < sheetCount; s++) {
			Sheet sheet = workbook.getSheetAt(sheetID);
			int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
			System.out.println("----rowCount---" + rowCount);
			// 遍历每一行
			for (int r = 1; r < rowCount; r++) {
				Map<String, String> colMap = new HashMap<>();
				Row row = sheet.getRow(r);
				// int cellCount = row.getPhysicalNumberOfCells(); //获取总列数
				// 遍历每一列
				for (int c = mincolNum; c <= maxcolNum; c++) {
					Cell cell = row.getCell(c);
					int cellType = cell.getCellType();
					String cellValue = null;
					switch (cellType) {
					case Cell.CELL_TYPE_STRING: // 文本
						cellValue = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC: // 数字、日期
						if (DateUtil.isCellDateFormatted(cell)) {
							cellValue = fmt.format(cell.getDateCellValue()); // 日期型
						} else {
							DecimalFormat df = new DecimalFormat("#.#########");
							cellValue = df.format(cell.getNumericCellValue()); // 去掉小数点
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN: // 布尔型
						cellValue = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_BLANK: // 空白
						// cellValue = cell.getStringCellValue();
						cellValue = "";
						break;
					case Cell.CELL_TYPE_ERROR: // 错误
						cellValue = "错误";
						break;
					case Cell.CELL_TYPE_FORMULA: // 公式
						cellValue = "错误";
						break;
					default:
						cellValue = "错误";
					}
					// System.out.println(cellValue + " ");
					colMap.put("col_" + c, cellValue);
				} // 遍历列
				rowMap.put("row_" + r, colMap);
			} // 遍历行
				// }

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rowMap;
	}

	public static void main(String[] args) {
		OpExcel opExcel = new OpExcel();
		String docAddress = "C:/Users/luco/Desktop/外勤系统/数据调整/合作医疗主数据20161124.xlsx";
		// 单行单列操作
		Map<String, String> storeName = opExcel.readExcel(docAddress, 1, 15);
		System.out.println(JSON.toJSONString(storeName));

		// 多行多列操作
		/*
		 * Map rowMap = opExcel.readManyExcel(docAddress, 8, 1, 2); for (int i =
		 * 1; i <= rowMap.size(); i++) { Object ob = (rowMap.get("row_" + i));
		 * Map colMap = (Map) ob; for (int j = 2; j <= 2; j++) {
		 * System.out.print("--" + colMap.get("col_" + j).toString()); }
		 * System.out.println(""); }
		 */
	}
}
