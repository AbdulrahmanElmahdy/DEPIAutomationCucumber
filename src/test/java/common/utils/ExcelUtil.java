package common.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private String currentFilePath; // To store the path for write operations

    public ExcelUtil() {
        // Constructor can be empty or initialize workbook if a default behavior is needed
    }

    public void openWorkbook(String filePath) throws IOException {
        this.currentFilePath = filePath; // Store for potential future save operations on this instance
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            System.err.println("Error opening workbook: " + filePath + " - " + e.getMessage());
            throw e;
        }
    }
    
    // Overloaded openWorkbook to create a new one if it doesn't exist, or for writing
    public void createOrOpenWorkbook(String filePath) throws IOException {
        this.currentFilePath = filePath;
        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
            }
        } else {
            workbook = new XSSFWorkbook();
        }
    }


    public String getCellData(String sheetName, int rowNum, int colNum) {
        try {
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.err.println("Sheet '" + sheetName + "' not found.");
                return null;
            }
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                // System.err.println("Row " + rowNum + " not found in sheet '" + sheetName + "'.");
                return null; // Or handle as an error, depending on expected behavior
            }
            Cell cell = row.getCell(colNum);
            if (cell == null) {
                // System.err.println("Cell " + colNum + " not found in row " + rowNum + " in sheet '" + sheetName + "'.");
                return null; // Or handle as an error
            }
            return cell.getStringCellValue();
        } catch (Exception e) {
            System.err.println("Error getting cell data from sheet '" + sheetName + "', row " + rowNum + ", col " + colNum + " - " + e.getMessage());
            // e.printStackTrace();
            return null;
        }
    }

    public int getRowCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            System.err.println("Sheet '" + sheetName + "' not found.");
            return 0;
        }
        return sheet.getLastRowNum() + 1; // getLastRowNum is 0-based
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) {
        if (workbook == null) {
            // This case implies createOrOpenWorkbook should have been called for writing.
            // If we want to allow setting data on a newly created workbook instance without explicit open:
            workbook = new XSSFWorkbook(); 
        }
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
        }
        cell.setCellValue(data);
    }

    public void writeAndCloseWorkbook(String filePath) throws IOException {
        if (workbook == null) {
            throw new IOException("Workbook is not initialized. Call openWorkbook() or createOrOpenWorkbook() first.");
        }
        // Ensure parent directory exists
        File file = new File(filePath);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        } finally {
            if (workbook != null) {
                // workbook.close(); // Closing workbook also closes streams if opened by it, good practice.
                // For XSSFWorkbook, close() releases resources.
                // However, if we opened with FileInputStream, that stream is already closed by try-with-resources.
                // If workbook was created new, this is fine.
            }
        }
    }
    
    // Optional: separate close method if needed, though writeAndClose is often sufficient
    public void closeWorkbook() throws IOException {
        if (workbook != null) {
            // workbook.close(); // XSSFWorkbook specific close
            // For now, relying on try-with-resources for read, and writeAndClose for write.
        }
    }
}
