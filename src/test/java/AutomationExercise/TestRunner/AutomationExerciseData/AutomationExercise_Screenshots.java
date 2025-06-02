package AutomationExercise.TestRunner.AutomationExerciseData;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import static AutomationExercise.TestRunner.AutomationExerciseHooks.AutomationExercise_Hooks.driver;
import static AutomationExercise.TestRunner.AutomationExerciseHooks.AutomationExercise_Hooks.test; // Assuming 'test' is for ExtentReports

public class AutomationExercise_Screenshots {
    public static String filePath; // This will store the path of the last screenshot taken
    private static String screenshotBasePath;

    static {
        try (InputStream input = AutomationExercise_Screenshots.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.err.println("Sorry, unable to find config.properties. Using default screenshot path: testOutput/AutomationExerciseScreenshots/");
                screenshotBasePath = "testOutput/AutomationExerciseScreenshots/";
            } else {
                prop.load(input);
                screenshotBasePath = prop.getProperty("screenshot.basepath", "testOutput/AutomationExerciseScreenshots/");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            screenshotBasePath = "testOutput/AutomationExerciseScreenshots/";
        }
    }

    private static void takeScreenshot(String scenarioNameParam, String stepNameParam, String status) throws IOException {
        if (driver == null) {
            System.err.println("WebDriver instance is null. Skipping screenshot.");
            return;
        }
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String strDate = sdf.format(cal.getTime());

        String scenario = (scenarioNameParam != null && !scenarioNameParam.isEmpty()) ? scenarioNameParam : "UnknownScenario";
        String step = (stepNameParam != null && !stepNameParam.isEmpty()) ? stepNameParam : "UnknownStep";

        // Sanitize scenario and step names to be filename-friendly
        scenario = scenario.replaceAll("[^a-zA-Z0-9-_]+", "_");
        step = step.replaceAll("[^a-zA-Z0-9-_]+", "_");
        
        String name = scenario.concat("-").concat(step).concat("-").concat(status).concat("-").concat(strDate).concat(".png");
        
        File destinationDir = new File(screenshotBasePath);
        if (!destinationDir.exists()) {
            destinationDir.mkdirs();
        }
        
        File destination = new File(destinationDir, name);
        filePath = destination.getAbsolutePath(); // Set static filePath for ExtentReports
        
        try {
            FileUtils.copyFile(screenshotFile, destination);
            if (test != null) { // 'test' is the ExtentTest object
                test.addScreenCaptureFromPath(filePath);
            } else {
                System.err.println("ExtentTest object ('test') is null. Screenshot not added to report: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error saving screenshot: " + filePath);
            throw e;
        }
    }

    public static void AutomationExercise_failureScreenshots(String scenarioName, String stepName) throws IOException {
        takeScreenshot(scenarioName, stepName, "FAIL");
    }

    public static void AutomationExercise_passScreenshots(String scenarioName, String stepName) throws IOException {
        takeScreenshot(scenarioName, stepName, "PASS");
    }
}
