package ParaBank.TestRunner.ParaBankStepDefin;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.io.IOException;

import static ParaBank.TestRunner.ParaBankData.ParaBank_Screenshots.*;
import static ParaBank.TestRunner.ParaBankHooks.ParaBank_Hooks.*;
import static ParaBank.TestRunner.ParaBankLocators.ParaBank_Locators.*;

public class ParaBank_test {

    @Given("User logged in")
    public void userIsLoggedIn() {
        // Write code to log in
        findElement(username).sendKeys("wilson.kuphal");
        findElement(password).sendKeys("23n5kq09v4ka6");
        findElement(login).click();
    }

    @Then("User do an action")
    public void theCustomerShouldBeRedirectedToTheFundsConfirmationPage() throws IOException, InterruptedException {
        test = extent.createTest("Transfer Funds", "Customer Transfer funds");

        findElement(TransferFund).click();
        findElement(Amount).sendKeys("50");

        findElement(selectFromAccount).click();
        findElement(option1).click();
        findElement(selectToAccount).click();
        findElement(option2).click();

        findElement(Transfer).click();

        Thread.sleep(2000);
        String messageNeeded = findElements(Messages).get(1).getText();
        System.out.println(messageNeeded);

        boolean TransferSuccessMessage = findElement(TransferSuccessMassage).getText().equalsIgnoreCase("Transfer Complete!");
        if (TransferSuccessMessage) {
            test.log(Status.PASS, "Transfer Complete! successfully will be displayed");
            ParaBank_passScreenshots();

        } else {
            test.log(Status.FAIL, "Transfer Complete! successfully will not be displayed");
            ParaBank_failureScreenshots();
        }

        Assert.assertEquals("Transfer Complete!", findElement(TransferSuccessMassage).getText());

    }
}
