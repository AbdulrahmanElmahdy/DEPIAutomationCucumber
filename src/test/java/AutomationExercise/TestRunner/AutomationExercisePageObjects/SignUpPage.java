package AutomationExercise.TestRunner.AutomationExercisePageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder=\"Name\"]")
    public WebElement sigUpName;

    @FindBy(xpath = "//input[@data-qa=\"signup-email\"]")
    public WebElement signUpEmail;

    @FindBy(xpath = "//button[text()=\"Signup\"]")
    public WebElement signUpButton;

    @FindBy(xpath = "//input[@value=\"Mr\"]")
    public WebElement titleGenderMr;

    @FindBy(xpath = "//input[@value=\"Mrs\"]")
    public WebElement titleGenderMrs;

    @FindBy(id = "name")
    public WebElement infoName;

    @FindBy(id = "email")
    public WebElement infoEmail;

    @FindBy(xpath = "//input[@id=\"password\"]")
    public WebElement passwordField;

    @FindBy(id = "days")
    public WebElement selectDay;

    @FindBy(id = "months")
    public WebElement selectMonth;

    @FindBy(id = "years")
    public WebElement selectYear;

    @FindBy(xpath = "//input[@id=\"newsletter\"]")
    public WebElement signUpNewsLetter;

    @FindBy(xpath = "//input[@id=\"optin\"]")
    public WebElement receiveSpecialOffer;

    @FindBy(id = "first_name")
    public WebElement firstNameField;

    @FindBy(id = "last_name")
    public WebElement lastNameField;

    @FindBy(id = "address1")
    public WebElement addressField;

    @FindBy(id = "country")
    public WebElement selectCountry;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "state")
    public WebElement stateField;

    @FindBy(id = "zipcode")
    public WebElement zipCodeField;

    @FindBy(id = "mobile_number")
    public WebElement mobileNumberField;

    @FindBy(xpath = "//button[text()=\"Create Account\"]")
    public WebElement createAccountButton; // Renamed from createAccount to avoid conflict with a potential method

    @FindBy(xpath = "//h2[@class=\"title text-center\"]")
    public WebElement accountCreatedMessage;

    @FindBy(xpath = "//h2[@class=\"title text-center\"]//following::p[1]")
    public WebElement successMessage;

    @FindBy(xpath = "//h2[@class=\"title text-center\"]//following::p[2]")
    public WebElement privilegesMessage;

    @FindBy(xpath = "//a[text()=\"Continue\"]")
    public WebElement continueButton;

    // Add WebElements and methods for interacting with the sign up page here

    public void fillAccountInformation(String password, String day, String month, String year,
                                       String firstName, String lastName, String address,
                                       String country, String stateName, String cityName,
                                       String zipcodeNum, String mobileNum) {
        passwordField.sendKeys(password);
        new org.openqa.selenium.support.ui.Select(selectDay).selectByValue(day);
        new org.openqa.selenium.support.ui.Select(selectMonth).selectByValue(month);
        new org.openqa.selenium.support.ui.Select(selectYear).selectByValue(year);
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        addressField.sendKeys(address);
        new org.openqa.selenium.support.ui.Select(selectCountry).selectByValue(country);
        stateField.sendKeys(stateName);
        city.sendKeys(cityName);
        zipCodeField.sendKeys(zipcodeNum);
        mobileNumberField.sendKeys(mobileNum);
    }
}
