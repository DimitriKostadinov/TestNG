package DemoTestNG;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class DataProviderExcel {

    @DataProvider(name = "get-data-excel")
    public Object[][] GetDataExcel() throws IOException {
        File file = new File("resources/ExcelFiles/TestDataForRead.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0); // The sheets in an Excel file is counted like items in an array from 0
        int rowCount = sheet.getPhysicalNumberOfRows();
        //getPhysicalNumberOfRows - Returns the number of physically defined rows (the row that is NOT EMPTY)
        int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();
        //getPhysicalNumberOfCells - Returns the number of physically defined cells (the cells that is NOT EMPTY)

        String[][] data = new String[rowCount][cellCount];
        DataFormatter df = new DataFormatter();

        for (int i=0; i<rowCount; i++){
            for (int j=0;j<cellCount;j++){
                data[i][j] = df.formatCellValue(sheet.getRow(i).getCell(j));
            }
        }

        workbook.close();
        fis.close();

        return data;
    }
}
