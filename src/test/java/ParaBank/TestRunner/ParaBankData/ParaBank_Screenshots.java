package ParaBank.TestRunner.ParaBankData;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static ParaBank.TestRunner.ParaBankHooks.ParaBank_Hooks.*;

public class ParaBank_Screenshots {
    public static String filePath;

    public static void ParaBank_failureScreenshots() throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String strDate = sdf.format(cal.getTime());
        String name = scenarioName.concat("-" + methodName + "-" + "FAIL" + "-").concat(strDate).concat(".png");
        File destination = new File("D:\\AquaProjects\\DEPIAutomationCucumber\\testOutPut\\ParaBankScreenshots\\".concat(name));
        filePath = destination.getAbsolutePath();
        FileUtils.copyFile(screenshotFile, destination);
        test.addScreenCaptureFromPath(filePath);
    }

    public static void ParaBank_passScreenshots() throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String strDate = sdf.format(cal.getTime());
        String name = scenarioName.concat("-" + methodName + "-" + "PASS" + "-").concat(strDate).concat(".png");
        File destination = new File("D:\\AquaProjects\\DEPIAutomationCucumber\\testOutPut\\ParaBankScreenshots\\".concat(name));
        filePath = destination.getAbsolutePath();
        FileUtils.copyFile(screenshotFile, destination);
        test.addScreenCaptureFromPath(filePath);
    }
}
