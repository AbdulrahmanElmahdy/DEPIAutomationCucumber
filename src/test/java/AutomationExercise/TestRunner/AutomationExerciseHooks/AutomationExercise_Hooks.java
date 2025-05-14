package AutomationExercise.TestRunner.AutomationExerciseHooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static AutomationExercise.TestRunner.AutomationExerciseData.AutomationExercise_DataPreparation.*;

public class AutomationExercise_Hooks {

    public static WebDriver driver;
    public static ExtentTest test;
    public static ExtentReports extent = new ExtentReports();
    public static ExtentSparkReporter spark;
    public static String scenarioName;
    public static String methodName;
    public static String strDate;
    public static String name;

    @Before
    public static void automationExerciseBeforeScenario(Scenario scenario) throws IOException {
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        strDate = sdf.format(cal.getTime());
        scenarioName = scenario.getName();
        setAutomationExerciseData();
    }

    @After
    public static void automationExerciseAfterScenario() {
        String name = scenarioName.concat("-" + methodName + "-").concat(strDate).concat(".html");
        spark = new ExtentSparkReporter(new File("testOutPut/AutomationExerciseReports/".concat(name)));
        extent.attachReporter(spark);
        extent.flush();
        driver.quit();
    }
}
