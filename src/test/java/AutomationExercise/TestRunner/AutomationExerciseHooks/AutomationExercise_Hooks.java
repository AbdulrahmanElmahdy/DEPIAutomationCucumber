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
import java.io.InputStream;
import java.util.Properties;

import static AutomationExercise.TestRunner.AutomationExerciseData.AutomationExercise_DataPreparation.*;

public class AutomationExercise_Hooks {

    public static WebDriver driver;
    public static ExtentTest test; // Represents the current test scenario
    public static ExtentReports extent; // Represents the overall report
    public static String scenarioName; // To store the current scenario name

    static {
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            InputStream inputStream = AutomationExercise_Hooks.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                System.err.println("Property file '" + propFileName + "' not found in the classpath. Using default report path.");
            }
            
            String reportPath = prop.getProperty("extent.report.path", "testOutput/AutomationExerciseReports/AutomationExerciseReport.html");
            File reportFile = new File(reportPath);
            // Ensure parent directory exists
            if (!reportFile.getParentFile().exists()) {
                reportFile.getParentFile().mkdirs();
            }

            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);
            
            // Reporter Configurations
            spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
            spark.config().setDocumentTitle("Automation Exercise Test Report");
            spark.config().setReportName("Automation Exercise - Functional Test Execution Report");
            
            extent.attachReporter(spark);

            // System Information
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Browser", "Chrome"); // Hardcoded as ChromeDriver is used directly
            extent.setSystemInfo("Application URL", "https://automationexercise.com/"); // Hardcoded base URL

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (extent != null) {
                    extent.flush();
                }
            }));

        } catch (IOException e) {
            e.printStackTrace();
            // Fallback if properties cannot be loaded - though ExtentReports might fail to init
            extent = new ExtentReports(); // Basic initialization to prevent NullPointer all over
            ExtentSparkReporter spark = new ExtentSparkReporter("testOutput/AutomationExerciseReports/FallbackReport.html");
            extent.attachReporter(spark);
        }
    }

    @Before
    public void automationExerciseBeforeScenario(Scenario scenario) throws IOException { // Made non-static for better test runner integration if needed
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();
        scenarioName = scenario.getName(); // Capture scenario name
        test = extent.createTest(scenarioName); // Create ExtentTest for the scenario
        setAutomationExerciseData();
    }

    @After
    public void automationExerciseAfterScenario() { // Made non-static
        if (driver != null) {
            driver.quit();
        }
        // extent.flush(); // Moved to shutdown hook
    }
}
