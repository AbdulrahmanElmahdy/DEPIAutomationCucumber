package ParaBank.TestRunner.ParaBankStepDefin;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.io.IOException;

import static ParaBank.TestRunner.ParaBankData.ParaBank_DataPreparation.*;
import static ParaBank.TestRunner.ParaBankData.ParaBank_Screenshots.*;
import static ParaBank.TestRunner.ParaBankLocators.ParaBank_Locators.*;
import static ParaBank.TestRunner.ParaBankHooks.ParaBank_Hooks.*;

public class ParaBank_SignUp {

    @Given("User navigate to sign up page")
    public static void userSignUp() throws IOException {
        setParaBankData();
        // Write code to navigate to sign up page
        findClickableElement(SignUpButton).click();
    }

    @And("User fill the details in the sign up page")
    public static void userFillTheDetailsInTheSignUpPage() throws IOException {
        // Write code to fill the details in the sign-up page
        findElement(firstNameField).sendKeys(getParaBankData(1, 0));
        findElement(lastNameField).sendKeys(getParaBankData(1, 1));
        findElement(addressField).sendKeys(getParaBankData(1, 2));
        findElement(cityField).sendKeys(getParaBankData(1, 3));
        findElement(stateField).sendKeys(getParaBankData(1, 4));
        findElement(zipCodeField).sendKeys(getParaBankData(1, 5));
        findElement(phoneNumberField).sendKeys(getParaBankData(1, 6));
        findElement(SNNField).sendKeys(getParaBankData(1, 7));
        findElement(usernameField).sendKeys(getParaBankData(1, 8));
        findElement(passwordField).sendKeys(getParaBankData(1, 9));
        findElement(confirmPasswordField).sendKeys(getParaBankData(1, 10));
    }

    @When("User click on register button")
    public static void userClickOnSignUpButton() {
        // Write code to click on sign up button
        findClickableElement(submitButton).click();
    }

    @Then("Sign up successful message will be displayed")
    public static void signUpSuccessfulMessageWillBeDisplayed() throws IOException {
        test = extent.createTest("Registration", "User register");
        boolean registerSuccess = findElement(successMessage).getText().equals("Your account was created successfully. You are now logged in.");
        methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        if (registerSuccess) {
            ParaBank_passScreenshots();
            test.log(Status.PASS, "Register successful message will be displayed");
        } else {
            ParaBank_failureScreenshots();
            test.log(Status.FAIL, "Register is failed", MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
        }
        Assert.assertTrue(registerSuccess, "Your account was created successfully. You are now logged in.");
    }
}
