package org.testframework.utilities;

import java.io.FileInputStream;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author sriva
 *
 */
public class ExcelReader {
	private static Logger log = Logger.getLogger(ExcelReader.class);
	private static LinkedHashMap<String, String> excelData = new LinkedHashMap<String, String>();

	/**
	 * @param sheetName
	 * @param scenarioName
	 * @return
	 * 
	 *         Reads the entire row in the current sheet selected and searches the
	 *         for the scenario requested. If the scenario is found, it scans the
	 *         entire row and sends a ordered map of first row and test data.
	 * 
	 */
	public LinkedHashMap<String, String> readExcelData(String sheetName, String scenarioName) {
		String fileName = FileReaderManager.getInstance().getTestDataPath() + Constants.TestData.EXCEL_FILE_NAME;

		int rowNum = 0;

		try {
			InputStream fileInput = new FileInputStream(fileName);
			HSSFWorkbook workbook = new HSSFWorkbook(fileInput);
			HSSFSheet sheet = workbook.getSheet(sheetName.trim());
			excelData.clear();

			rowNum = findRow(sheet, scenarioName);
			if (rowNum != 0)
				output(sheet, rowNum);
			else
				System.out.println("Scenario requested is not available in the spreadsheet");
			workbook.close();
			fileInput.close();

		} catch (IOException ioException) {
			ioException.printStackTrace();
			log.error(ioException.getMessage());
		}

		return excelData;
	}

	/**
	 * @param sheet
	 * @param rowNum
	 * @param evaluator
	 * @return
	 * 
	 *         Scans the entire row and sends across the ordered Map of the first
	 *         row and value (test data). This method is executed only if the
	 *         scenario is found.
	 * 
	 */
	private LinkedHashMap<String, String> output(HSSFSheet sheet, int rowNum) {

		DataFormatter dataformater = new DataFormatter();
		HSSFRow row = sheet.getRow(rowNum);
		HSSFRow firstRow = sheet.getRow(0);
		for (int column = 1; column < row.getLastCellNum(); column++) {
			String key = dataformater.formatCellValue(firstRow.getCell(column));
			String value = dataformater.formatCellValue(row.getCell(column));
			if (!key.isEmpty() && !value.isEmpty())
				excelData.put(key, value);
		}

		return excelData;
	}

	/**
	 * @param sheet
	 * @param scenarioName
	 * @return
	 * 
	 *         Method to find the row where the scenario being requested is located.
	 * 
	 */
	private int findRow(HSSFSheet sheet, String scenarioName) {

		DataFormatter dataformater = new DataFormatter();
		for (Row row : sheet) {
			for (org.apache.poi.ss.usermodel.Cell cell : row) {
				if (dataformater.formatCellValue(cell).trim().equalsIgnoreCase(scenarioName))
					return row.getRowNum();
			}
		}
		return 0;
	}
}
