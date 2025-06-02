package AutomationExercise.TestRunner.AutomationExercisePageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),' Signup / Login')]")
    public WebElement signUpLoginButton;

    @FindBy(xpath = "//a[text()=\" Logged in as \"]")
    public WebElement loggedInWord;

    @FindBy(xpath = "//a[text()=\" Logged in as \"]//following::b")
    public WebElement loggedInUserName;

    // Add WebElements and methods for interacting with the home page here
}
