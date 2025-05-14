package AutomationExercise.TestRunner.AssertionsValidation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static AutomationExercise.TestRunner.AutomationExerciseData.AutomationExercise_DataPreparation.*;
import static AutomationExercise.TestRunner.AutomationExerciseHooks.AutomationExercise_Hooks.*;

public class MessagesValidations {

    public static String accountCreationMessage = "ACCOUNT CREATED!";
    public static String signUpSuccessfulMessage = "Congratulations! Your new account has been successfully created!";
    public static String memberPrivilegesMessage = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
    public static String loggedInAs = "Logged in as " + signUpName;

    public static boolean validation (By locator, String message){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator).getText().equalsIgnoreCase(message);
    }

    public static boolean multipleValidation (By locator1, By locator2, String message){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator1));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator2));
        return (driver.findElement(locator1).getText() + driver.findElement(locator2).getText()).equalsIgnoreCase(message);
    }

    public static void softAssert (By locator, String expected){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        softAssert.assertEquals(driver.findElement(locator).getText(), expected);
    }
}