package ParaBank.TestRunner.ParaBankHooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ParaBank_Hooks {

    public static WebDriver driver;
    public static ExtentTest test;
    public static ExtentReports extent = new ExtentReports();
    public static ExtentSparkReporter spark;
    public static String scenarioName;
    public static String methodName;
    public static String strDate;
    public static String name;

    @Before
    public static void paraBankBeforeScenario(Scenario scenario) {
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        strDate = sdf.format(cal.getTime());
        scenarioName = scenario.getName();
    }

    @After
    public static void paraBankAfterScenario() {
        String name = scenarioName.concat("-" + methodName + "-").concat(strDate).concat(".html");
        spark = new ExtentSparkReporter(new File("testOutPut/ParaBankReports/".concat(name)));
        extent.attachReporter(spark);
        extent.flush();
        driver.quit();
    }
}
