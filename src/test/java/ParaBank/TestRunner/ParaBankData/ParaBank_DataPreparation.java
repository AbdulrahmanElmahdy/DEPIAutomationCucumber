package ParaBank.TestRunner.ParaBankData;

import com.github.javafaker.Faker;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class ParaBank_DataPreparation {

    public static XSSFSheet sheet;
    public static XSSFWorkbook workbook;
    public static XSSFRow rows;
    public static Cell cell;
    public static FileOutputStream out;
    public static Faker faker;


    public static void setParaBankData () throws IOException {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("ParaBank_data");
        // Add data to the sheet
        Map<String, Object[]> data = new TreeMap<>();
        data.put("1", new Object[]{"firstName", "lastName", "address","city","state","zipcode","phone","SNN","username","password","confirmPassword"});

        faker = new Faker();
        String password = faker.internet().password();
        data.put("2", new Object[]{faker.name().firstName(),faker.name().lastName(),faker.address().streetAddress(),faker.address().cityName(),faker.address().state(),faker.address().zipCode(),faker.phoneNumber().cellPhone(),faker.code().asin(),faker.name().username(),password,password});

        int rowID = 0;

        for (String key : data.keySet()) {
            rows = sheet.createRow(rowID++);
            Object[] arrRows = data.get(key);
            int cellID = 0;
            for (Object obj : arrRows) {
                cell = rows.createCell(cellID++);
                cell.setCellValue((String) obj);
            }
        }

        out = new FileOutputStream("D:\\AquaProjects\\DEPIAutomationCucumber\\src\\test\\java\\ParaBank\\TestRunner\\ParaBankData\\ParaBank_data.xlsx");
        workbook.write(out);
        out.close();
    }

//    public static void setDataByValue (String value, int rowNo, int cellNo) throws IOException {
//
//        sheet = workbook.createSheet("DataValues");
//        rows = sheet.createRow(rowNo);
//        cell = rows.createCell(cellNo);
//        cell.setCellValue(value);
//
//        out = new FileOutputStream("D:\\AquaProjects\\DEPIAutomationCucumber\\src\\test\\java\\ParaBank.TestRunner.Data\\dataValues.xlsx");
//        workbook.write(out);
//        out.close();
//    }

    public static String getParaBankData(int rowNo, int cellNo) throws IOException {
        workbook = new XSSFWorkbook("D:\\AquaProjects\\DEPIAutomationCucumber\\src\\test\\java\\ParaBank\\TestRunner\\ParaBankData\\ParaBank_data.xlsx");
        sheet = workbook.getSheet("ParaBank_data");
        return sheet.getRow(rowNo).getCell(cellNo).getStringCellValue();
    }

//    public static void getAllData (){
//        int r = sheet.getLastRowNum() - sheet.getFirstRowNum();
//
//        for (int i = 0; i < r ; i++){
//            for (int j = 0; j < 11 ; j++){
//                String value = sheet.getRow(i).getCell(j).getStringCellValue();
//                System.out.println(value);
//            }
//        }
//    }
}
