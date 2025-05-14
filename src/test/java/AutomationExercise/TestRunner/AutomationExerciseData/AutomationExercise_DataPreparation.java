package AutomationExercise.TestRunner.AutomationExerciseData;

import com.github.javafaker.Faker;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class AutomationExercise_DataPreparation {

    public static XSSFSheet sheet;
    public static XSSFWorkbook workbook;
    public static XSSFRow rows;
    public static int rowID;
    public static Cell cell;
    public static FileOutputStream out;
    public static Map<String, Object[]> data;
    public static Faker faker;

    public static String fullAddress;
    public static String state;
    public static String cityName;
    public static String zipcode;
    public static String phone;
    public static String lastName;
    public static String firstName;
    public static String signUpName;
    public static String signUpEmail;
    public static String password;

    public static void setAutomationExerciseData () throws IOException {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("AutomationExercise_data");
        // Add data to the sheet
        data = new TreeMap<>();
        data.put("1", new Object[]{"name", "email", "password", "firstName", "lastName", "full address", "state", "city", "zipcode", "phone"});

        faker = new Faker();
        String password = faker.internet().password();
        String userName = faker.name().username();
        data.put("2", new Object[]{userName, userName + "@gmail.com",password,faker.name().firstName(),faker.name().lastName(),faker.address().fullAddress(),faker.address().state(),faker.address().cityName(),faker.address().zipCode(),faker.phoneNumber().cellPhone()});

        rowID = 0;

        for (String key : data.keySet()) {
            rows = sheet.createRow(rowID++);
            Object[] arrRows = data.get(key);
            int cellID = 0;
            for (Object obj : arrRows) {
                cell = rows.createCell(cellID++);
                cell.setCellValue((String) obj);
            }
        }

        out = new FileOutputStream("D:\\AquaProjects\\DEPIAutomationCucumber\\src\\test\\java\\AutomationExercise\\TestRunner\\AutomationExerciseData\\AutomationExercise_data.xlsx");
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

    public static String getAutomationExerciseData(int rowNo, int cellNo) throws IOException {
        workbook = new XSSFWorkbook("D:\\AquaProjects\\DEPIAutomationCucumber\\src\\test\\java\\AutomationExercise\\TestRunner\\AutomationExerciseData\\AutomationExercise_data.xlsx");
        sheet = workbook.getSheet("AutomationExercise_data");
        return sheet.getRow(rowNo).getCell(cellNo).getStringCellValue();
    }

    public static void getDataFromSheet() throws IOException {
        signUpName = getAutomationExerciseData(1, 0);
        signUpEmail = getAutomationExerciseData(1, 1);
        password = getAutomationExerciseData(1, 2);
        firstName = getAutomationExerciseData(1, 3);
        lastName = getAutomationExerciseData(1, 4);
        fullAddress = getAutomationExerciseData(1, 5);
        state = getAutomationExerciseData(1,6);
        cityName = getAutomationExerciseData(1,7);
        zipcode = getAutomationExerciseData(1, 8);
        phone = getAutomationExerciseData(1, 9);
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
