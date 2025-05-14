package ParaBank.TestRunner.ParaBankLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static ParaBank.TestRunner.ParaBankHooks.ParaBank_Hooks.driver;

public class ParaBank_Locators {

    public static By SignUpButton = By.xpath("//*[contains(text(),'Register')]");
    public static By firstNameField = By.xpath("//td/input[@id=\"customer.firstName\"]");
    public static By lastNameField = By.xpath("//td/input[@id=\"customer.lastName\"]");
    public static By addressField = By.xpath("//td/input[@id=\"customer.address.street\"]");
    public static By cityField = By.xpath("//td/input[@id=\"customer.address.city\"]");
    public static By stateField = By.xpath("//td/input[@id=\"customer.address.state\"]");
    public static By zipCodeField = By.xpath("//td/input[@id=\"customer.address.zipCode\"]");
    public static By phoneNumberField = By.xpath("//td/input[@id=\"customer.phoneNumber\"]");
    public static By SNNField = By.xpath("//td/input[@id=\"customer.ssn\"]");
    public static By usernameField = By.xpath("//td/input[@id=\"customer.username\"]");
    public static By passwordField = By.xpath("//td/input[@id=\"customer.password\"]");
    public static By confirmPasswordField = By.xpath("//td/input[@id=\"repeatedPassword\"]");
    public static By submitButton = By.xpath("//td/input[@type=\"submit\"]");
    public static By successMessage = By.xpath("//*[@id=\"rightPanel\"]/p");
    public static By signOut = By.xpath("//*[text()=\"Log Out\"]");
    public static By signIn = By.xpath("//div/input[@type=\"submit\"]");
    public static By username = By.xpath("//input[@name=\"username\"]");
    public static By password = By.xpath("//input[@name=\"password\"]");
    public static By TransferSuccessMassage = By.xpath("//*[text()=\"Transfer Complete!\"]");
    public static By login = By.xpath("//input[@value=\"Log In\"]");
    public static By TransferFund = By.xpath("//a[text()=\"Transfer Funds\"]");
    public static By Amount = By.xpath("//input[@name=\"input\"]");
    public static By Transfer = By.xpath("//input[@value=\"Transfer\"]");
    public static By selectFromAccount = By.id("fromAccountId");
    public static By selectToAccount = By.id("toAccountId");
    public static By option1 = By.cssSelector("#fromAccountId > option:nth-child(1)");
    public static By option2 = By.cssSelector("#toAccountId > option:nth-child(2)");
    public static By Messages = By.xpath("//h1[@class=\"title\"]");

    public static WebElement findElement (By locator){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(2000));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public static WebElement findClickableElement (By locator){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }

    public static List<WebElement> findElements (By locator) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(2000));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }

    public static Select select (By locator) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return new Select(driver.findElement(locator));
    }

}
