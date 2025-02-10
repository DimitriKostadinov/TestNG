package com.apachepoiexcelfiles;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class CreateWriteReadExcel {

    public static void createExcel() throws IOException {
        // run main method in org.example package
        // old excel version
       /* HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("From JAVA");
        sheet.createRow(0);
        sheet.getRow(0).createCell(0).setCellValue("Hello");
        sheet.getRow(0).createCell(1).setCellValue("World");

        sheet.createRow(1);
        sheet.getRow(1).createCell(0).setCellValue("Excel");
        sheet.getRow(1).createCell(1).setCellValue("Maven");

        File file = new File("K:\\JAVAProjects\\TestNG\\resources\\ExcelFiles\\Test.xls");
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        workbook.close();*/

        // new excel version
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("From JAVA");
        sheet.createRow(0);
        sheet.getRow(0).createCell(0).setCellValue("Hello"); // Write in cell in the Excel file
        sheet.getRow(0).createCell(1).setCellValue("World");

        sheet.createRow(1);
        sheet.getRow(1).createCell(0).setCellValue("Excel");
        sheet.getRow(1).createCell(1).setCellValue("Maven");

        File file = new File("K:\\JAVAProjects\\TestNG\\resources\\ExcelFiles\\Test.xlsx");
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        workbook.close();
    }

    public static void ReadExcel() throws IOException {
        File file = new File("K:\\JAVAProjects\\TestNG\\resources\\ExcelFiles\\TestDataForRead.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0); // The sheets in an Excel file is counted like items in an array from 0
       // String cellValue = sheet.getRow(0).getCell(0).getStringCellValue();
       // System.out.println(cellValue);
        int rowCount = sheet.getPhysicalNumberOfRows();
        //getPhysicalNumberOfRows - Returns the number of physically defined rows (the row that is NOT EMPTY)

        for (int i=0; i<rowCount; i++){
            XSSFRow row = sheet.getRow(i);
            int cellCount = row.getPhysicalNumberOfCells();
            //getPhysicalNumberOfCells - Returns the number of physically defined cells (the cells that is NOT EMPTY)
            for (int j=0;j<cellCount;j++){
                XSSFCell cell = row.getCell(j);
                String cellVaue = getCellValue(cell);
                System.out.print("||" + cellVaue);
            }
            System.out.println();
        }
        workbook.close();
        fis.close();
    }

    public static String getCellValue(XSSFCell cell){
        switch (cell.getCellType()){
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case STRING:
                return String.valueOf(cell.getStringCellValue());

            default:
                return cell.getStringCellValue();
        }
    }
}
