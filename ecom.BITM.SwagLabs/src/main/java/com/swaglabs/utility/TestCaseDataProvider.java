package com.swaglabs.utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class TestCaseDataProvider {

    @DataProvider(name = "testCases")
    public static Object[][] getTestCases() throws IOException {

        try (FileInputStream fis = new FileInputStream("./src/main/resources/TestCasesName.xlsx");
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet("TestCases");
            DataFormatter formatter = new DataFormatter();

            int lastRow = sheet.getLastRowNum();   // row 0 is the header
            int columns = 11;

            Object[][] data = new Object[lastRow][columns];

            for (int i = 1; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < columns; j++) {
                    Cell cell = (row == null) ? null : row.getCell(j);
                    data[i - 1][j] = formatter.formatCellValue(cell);
                }
            }
            return data;
        }
    }
}
