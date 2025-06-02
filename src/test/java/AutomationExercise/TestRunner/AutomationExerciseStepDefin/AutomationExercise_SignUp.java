package AutomationExercise.TestRunner.AutomationExerciseStepDefin;

import AutomationExercise.TestRunner.AutomationExercisePageObjects.HomePage;
import AutomationExercise.TestRunner.AutomationExercisePageObjects.SignUpPage;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

import static AutomationExercise.TestRunner.AutomationExerciseData.AutomationExercise_DataPreparation.*;
import static AutomationExercise.TestRunner.AutomationExerciseData.AutomationExercise_Screenshots.AutomationExercise_failureScreenshots;
import static AutomationExercise.TestRunner.AutomationExerciseData.AutomationExercise_Screenshots.AutomationExercise_passScreenshots;
import static AutomationExercise.TestRunner.AssertionsValidation.MessagesValidations.*;
import static AutomationExercise.TestRunner.AutomationExerciseHooks.AutomationExercise_Hooks.*;

public class AutomationExercise_SignUp {

    private HomePage homePage;
    private SignUpPage signUpPage;

    // Constructor to initialize Page Objects
    public AutomationExercise_SignUp() {
        // The driver is static in AutomationExercise_Hooks, so we can access it directly
        // This might need adjustment if driver management changes
        if (driver != null) {
            this.homePage = new HomePage(driver);
            this.signUpPage = new SignUpPage(driver);
        }
    }

    @Given("User navigate to sign up screen")
    public void userSignUp() {
        homePage.signUpLoginButton.click();
    }

    @And("User insert signup name and email")
    public void userInsertSignUpNameAndEmail() throws IOException {
        getDataFromSheet();

        if (test != null) { // Ensure test object is initialized
            test.info("Test Data - Sign Up Name: " + signUpName);
            test.info("Test Data - Sign Up Email: " + signUpEmail);
        } else {
            System.err.println("ExtentTest object is null in userInsertSignUpNameAndEmail. Cannot log test data.");
        }

        signUpPage.sigUpName.sendKeys(signUpName);
        signUpPage.signUpEmail.sendKeys(signUpEmail);
    }

    @When("User click on sign up button")
    public void userClickOnSignUpButton() {
        signUpPage.signUpButton.click();
    }

    @And("User insert personal details")
    public void userInsertPersonalDetails() throws IOException {
        getDataFromSheet(); // This populates static fields like password, firstName, etc.
        
        signUpPage.titleGenderMr.click(); // This could also be part of fillAccountInformation if title is always selected this way

        // Values for DOB and Country are currently hardcoded here as per instruction,
        // but could be moved to data sheet or config in future.
        String dobDay = "16";
        String dobMonth = "3";
        String dobYear = "1998";
        String countryName = "United States";

        signUpPage.fillAccountInformation(
                password, dobDay, dobMonth, dobYear,
                firstName, lastName, fullAddress,
                countryName, state, cityName,
                zipcode, phone
        );
    }

    @Then("Click on create account button and Sign up successful message is displayed")
    public void clickOnCreateAccountAndSignUpSuccessfulMessageDisplayed() throws IOException {
        signUpPage.createAccountButton.click();
        methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println();
        // The validation method needs to be updated if it relies on By locators
        // Assuming validation method can take WebElements or will be refactored separately.
        // For now, passing the WebElement from the Page Object.
        if (validation(signUpPage.accountCreatedMessage, accountCreationMessage) && validation(signUpPage.successMessage, signUpSuccessfulMessage) && validation(signUpPage.privilegesMessage, memberPrivilegesMessage)) {
            test.log(Status.PASS, "All the required information has been successfully filled out and account creation was successful");
            AutomationExercise_passScreenshots(AutomationExercise_Hooks.scenarioName, methodName);
        } else if (validation(signUpPage.accountCreatedMessage, accountCreationMessage) && validation(signUpPage.successMessage, signUpSuccessfulMessage)) {
            test.log(Status.WARNING, "Account created without member privileges");
            // Decide if this warning case should also have a screenshot, and what type
            // AutomationExercise_passScreenshots(AutomationExercise_Hooks.scenarioName, methodName + "_Warning"); 
        } else {
            test.log(Status.FAIL, "Sign up is failed");
            AutomationExercise_failureScreenshots(AutomationExercise_Hooks.scenarioName, methodName);
        }

        softAssert(signUpPage.accountCreatedMessage, accountCreationMessage);
        softAssert(signUpPage.successMessage, signUpSuccessfulMessage);
        softAssert(signUpPage.privilegesMessage, memberPrivilegesMessage);

        signUpPage.continueButton.click();
    }

    @And("Main screen with logged in account")
    public void validateLoggedIn() throws IOException { // Made non-static
        methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        // Assuming validation method can take WebElements or will be refactored separately.
        if (validation(homePage.loggedInWord, loggedInAs)) {
            test.log(Status.PASS, "User logged in successfully");
            AutomationExercise_passScreenshots(AutomationExercise_Hooks.scenarioName, methodName);
        } else {
            test.log(Status.FAIL, "User failed to log in");
            AutomationExercise_failureScreenshots(AutomationExercise_Hooks.scenarioName, methodName);
        }
        softAssert(homePage.loggedInWord, loggedInAs);
        // The original code asserted loggedInUserName against signUpName.
        // This assumes signUpName is accessible here.
        // If signUpName is loaded via getDataFromSheet in other steps, ensure it's available or re-loaded.
        // For now, assuming it's available at class level or through data preparation.
        softAssert(homePage.loggedInUserName, signUpName);
    }
}
