package AutomationExercise.TestRunner.AutomationExerciseStepDefin;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static AutomationExercise.TestRunner.AutomationExerciseData.AutomationExercise_DataPreparation.*;
import static AutomationExercise.TestRunner.AutomationExerciseData.AutomationExercise_Screenshots.AutomationExercise_failureScreenshots;
import static AutomationExercise.TestRunner.AutomationExerciseData.AutomationExercise_Screenshots.AutomationExercise_passScreenshots;
import static AutomationExercise.TestRunner.AssertionsValidation.MessagesValidations.*;
import static AutomationExercise.TestRunner.AutomationExerciseHooks.AutomationExercise_Hooks.*;
import static AutomationExercise.TestRunner.AutomationExerciseLocators.AutomationExercise_Locators.*;

public class AutomationExercise_SignUp {

    @Given("User navigate to sign up screen")
    public static void userSignUp() {
        findElementClickable(SignUpLoginButton).click();
    }

    @And("User insert signup name and email")
    public static void userInsertSignUpNameAndEmail() throws IOException {
        getDataFromSheet();
        findElement(SigUpName).sendKeys(signUpName);
        findElement(SignUpEmail).sendKeys(signUpEmail);
    }

    @When("User click on sign up button")
    public static void userClickOnSignUpButton() {
        findElementClickable(SignUpButton).click();
    }

    @And("User insert personal details")
    public void userInsertPersonalDetails() throws IOException {
        getDataFromSheet();
        findElementClickable(titleGenderMr).click();
        if (infoName == null) {
            findElement(SigUpName).sendKeys(signUpName);
        }
        if (infoEmail == null) {
            findElement(SignUpEmail).sendKeys(signUpEmail);
        } else {
            findElement(passwordField).sendKeys(password);
            select(selectDay).selectByValue("16");
            select(selectMonth).selectByValue("3");
            select(selectYear).selectByValue("1998");
            findElement(firstNameField).sendKeys(firstName);
            findElement(lastNameField).sendKeys(lastName);
            findElement(addressField).sendKeys(fullAddress);
            select(selectCountry).selectByValue("United States");
            findElement(stateField).sendKeys(state);
            findElement(city).sendKeys(cityName);
            findElement(zipCodeField).sendKeys(zipcode);
            findElement(mobileNumberField).sendKeys(phone);
        }
    }

    @Then("Click on create account button and Sign up successful message is displayed")
    public void clickOnCreateAccountAndSignUpSuccessfulMessageDisplayed() throws IOException {
        test = extent.createTest("Create Account", "User able to create a new account");
        findElementClickable(createAccount).click();
        methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println();
        if (validation(accountCreatedMessage, accountCreationMessage) && validation(successMessage, signUpSuccessfulMessage) && validation(privilegesMessage, memberPrivilegesMessage)) {
            test.log(Status.PASS, "All the required information has been successfully filled out and account creation was successful");
            AutomationExercise_passScreenshots();
        } else if (validation(accountCreatedMessage, accountCreationMessage) && validation(successMessage, signUpSuccessfulMessage)) {
            test.log(Status.WARNING, "Account created without member privileges");
        } else {
            test.log(Status.FAIL, "Sign up is failed");
            AutomationExercise_failureScreenshots();
        }

        softAssert(accountCreatedMessage, accountCreationMessage);
        softAssert(successMessage, signUpSuccessfulMessage);
        softAssert(privilegesMessage, memberPrivilegesMessage);

        findElementClickable(continueButton).click();
    }

    @And("Main screen with logged in account")
    public static void validateLoggedIn() throws IOException {
        test = extent.createTest("Account Logged In", "Account logged in successfully");
        methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        if (validation(loggedInWord, loggedInAs)) {
            test.log(Status.PASS, "User logged in successfully");
            AutomationExercise_passScreenshots();
        } else {
            test.log(Status.FAIL, "User failed to log in");
            AutomationExercise_failureScreenshots();
        }
        softAssert(loggedInWord, loggedInAs);
        softAssert(loggedInUserName, signUpName);
    }
}
