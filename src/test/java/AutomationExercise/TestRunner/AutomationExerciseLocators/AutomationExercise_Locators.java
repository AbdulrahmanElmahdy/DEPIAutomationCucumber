package AutomationExercise.TestRunner.AutomationExerciseLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static AutomationExercise.TestRunner.AutomationExerciseHooks.AutomationExercise_Hooks.*;

public class AutomationExercise_Locators {

    public static By SignUpLoginButton = By.xpath("//a[contains(text(),' Signup / Login')]");
    public static By SigUpName = By.xpath("//input[@placeholder=\"Name\"]");
    public static By SignUpEmail = By.xpath("//input[@data-qa=\"signup-email\"]");
    public static By SignUpButton = By.xpath("//button[text()=\"Signup\"]");
    public static By titleGenderMr = By.xpath("//input[@value=\"Mr\"]");
    public static By titleGenderMrs = By.xpath("//input[@value=\"Mrs\"]");
    public static By infoName = By.id("name");
    public static By infoEmail = By.id("email");
    public static By passwordField = By.xpath("//input[@id=\"password\"]");
    public static By selectDay = By.id("days");
    public static By selectMonth = By.id("months");
    public static By selectYear = By.id("years");
    public static By signUpNewsLetter = By.xpath("//input[@id=\"newsletter\"]");
    public static By receiveSpecialOffer = By.xpath("//input[@id=\"optin\"]");
    public static By firstNameField = By.id("first_name");
    public static By lastNameField = By.id("last_name");
    public static By addressField = By.id("address1");
    public static By selectCountry = By.id("country");
    public static By city = By.id("city");
    public static By stateField = By.id("state");
    public static By zipCodeField = By.id("zipcode");
    public static By mobileNumberField = By.id("mobile_number");
    public static By createAccount = By.xpath("//button[text()=\"Create Account\"]");
    public static By accountCreatedMessage = By.xpath("//h2[@class=\"title text-center\"]");
    public static By successMessage = By.xpath("//h2[@class=\"title text-center\"]//following::p[1]");
    public static By privilegesMessage = By.xpath("//h2[@class=\"title text-center\"]//following::p[2]");
    public static By continueButton = By.xpath("//a[text()=\"Continue\"]");
    public static By loggedInWord = By.xpath("//a[text()=\" Logged in as \"]");
    public static By loggedInUserName = By.xpath("//a[text()=\" Logged in as \"]//following::b");


    public static WebElement findElement (By locator){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public static WebElement findElementClickable (By locator){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }

    public static List<WebElement> findElements (By locator) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }

    public static Select select (By locator) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return new Select(driver.findElement(locator));
    }
}