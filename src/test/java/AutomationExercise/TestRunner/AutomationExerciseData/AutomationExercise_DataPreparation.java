package AutomationExercise.TestRunner.AutomationExerciseData;

import com.github.javafaker.Faker;
import common.utils.ExcelUtil; // Import the new ExcelUtil

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class AutomationExercise_DataPreparation {

    // Configured Excel file path
    private static final String EXCEL_FILE_PATH;
    private static final String SHEET_NAME = "AutomationExercise_data"; // Define sheet name as a constant

    // Static initializer to load configuration
    static {
        Properties prop = new Properties();
        String propFileName = "config.properties";
        String pathFromConfig = "src/test/java/AutomationExercise/TestRunner/AutomationExerciseData/AutomationExercise_data.xlsx"; // Default
        try (InputStream inputStream = AutomationExercise_DataPreparation.class.getClassLoader().getResourceAsStream(propFileName)) {
            if (inputStream != null) {
                prop.load(inputStream);
                pathFromConfig = prop.getProperty("excel.datapath", pathFromConfig);
            } else {
                System.err.println("Property file '" + propFileName + "' not found in the classpath. Using default Excel path: " + pathFromConfig);
            }
        } catch (IOException e) {
            System.err.println("Error loading '" + propFileName + "'. Using default Excel path: " + pathFromConfig + ". Error: " + e.getMessage());
        }
        EXCEL_FILE_PATH = pathFromConfig;
    }

    // Static fields for holding test data, populated by getDataFromSheet
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
    // Removed static POI objects: sheet, workbook, rows, rowID, cell
    // Faker and data map can be local to setAutomationExerciseData
    // public static Map<String, Object[]> data; // Made local
    // public static Faker faker; // Made local

    public static void setAutomationExerciseData() throws IOException {
        ExcelUtil excelUtil = new ExcelUtil();
        // excelUtil.createOrOpenWorkbook(EXCEL_FILE_PATH); // Ensures workbook is initialized for writing

        Map<String, Object[]> dataToWrite = new TreeMap<>();
        dataToWrite.put("1", new Object[]{"name", "email", "password", "firstName", "lastName", "full address", "state", "city", "zipcode", "phone"});

        Faker faker = new Faker();
        String fakePassword = faker.internet().password();
        String userName = faker.name().username();
        dataToWrite.put("2", new Object[]{userName, userName + "@gmail.com", fakePassword, faker.name().firstName(), faker.name().lastName(), faker.address().fullAddress(), faker.address().state(), faker.address().cityName(), faker.address().zipCode(), faker.phoneNumber().cellPhone()});

        int rowNum = 0;
        for (String key : dataToWrite.keySet()) {
            Object[] arrRows = dataToWrite.get(key);
            int cellNum = 0;
            for (Object obj : arrRows) {
                excelUtil.setCellData(SHEET_NAME, rowNum, cellNum++, (String) obj);
            }
            rowNum++;
        }
        excelUtil.writeAndCloseWorkbook(EXCEL_FILE_PATH);
    }

    // This method is specific to AutomationExercise, so it stays in this class.
    // It now uses ExcelUtil.
    private static String getCellDataFromSheet(ExcelUtil util, int rowNo, int cellNo) throws IOException {
        // String data = util.getCellData(SHEET_NAME, rowNo, cellNo);
        // if (data == null) {
        //     System.err.println("Warning: Null data received from Excel for row " + rowNo + ", col " + cellNo + " in sheet " + SHEET_NAME);
        //     return ""; // Return empty string or throw error, based on how missing data should be handled
        // }
        // return data;
        // The openWorkbook is now called once in getDataFromSheet
        return util.getCellData(SHEET_NAME, rowNo, cellNo);
    }


    public static void getDataFromSheet() throws IOException {
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.openWorkbook(EXCEL_FILE_PATH); // Open workbook once

        signUpName = excelUtil.getCellData(SHEET_NAME,1, 0);
        signUpEmail = excelUtil.getCellData(SHEET_NAME,1, 1);
        password = excelUtil.getCellData(SHEET_NAME,1, 2);
        firstName = excelUtil.getCellData(SHEET_NAME,1, 3);
        lastName = excelUtil.getCellData(SHEET_NAME,1, 4);
        fullAddress = excelUtil.getCellData(SHEET_NAME,1, 5);
        state = excelUtil.getCellData(SHEET_NAME,1,6);
        cityName = excelUtil.getCellData(SHEET_NAME,1,7);
        zipcode = excelUtil.getCellData(SHEET_NAME,1, 8);
        phone = excelUtil.getCellData(SHEET_NAME,1, 9);
        
        // excelUtil.closeWorkbook(); // If ExcelUtil implements a separate close for read operations
    }

    // The old getAutomationExerciseData(row, cell) is effectively replaced by 
    // direct calls to excelUtil.getCellData within getDataFromSheet,
    // or could be a private helper like getCellDataFromSheet if preferred.
    // For now, removed the public static String getAutomationExerciseData(int, int)
}
