package AutomationExercise.TestRunner.AutomationExerciseData;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static AutomationExercise.TestRunner.AutomationExerciseHooks.AutomationExercise_Hooks.*;

public class AutomationExercise_Screenshots {
    public static String filePath;

    public static void AutomationExercise_failureScreenshots() throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String strDate = sdf.format(cal.getTime());
        String name = scenarioName.concat("-" + methodName + "-" + "FAIL" + "-").concat(strDate).concat(".png");
        File destination = new File("D:\\AquaProjects\\DEPIAutomationCucumber\\testOutPut\\AutomationExerciseScreenshots\\".concat(name));
        filePath = destination.getAbsolutePath();
        FileUtils.copyFile(screenshotFile, destination);
        test.addScreenCaptureFromPath(filePath);
    }

    public static void AutomationExercise_passScreenshots() throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String strDate = sdf.format(cal.getTime());
        String name = scenarioName.concat("-" + methodName + "-" + "PASS" + "-").concat(strDate).concat(".png");
        File destination = new File("D:\\AquaProjects\\DEPIAutomationCucumber\\testOutPut\\AutomationExerciseScreenshots\\".concat(name));
        filePath = destination.getAbsolutePath();
        FileUtils.copyFile(screenshotFile, destination);
        test.addScreenCaptureFromPath(filePath);
    }
}
