package ParaBank.TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/resources/ParaBankFeatures"},
        glue = {"ParaBank.TestRunner.ParaBankHooks", "ParaBank.TestRunner.ParaBankStepDefin", "ParaBank.TestRunner.ParaBankData", "ParaBank.TestRunner.ParaBankLocators"},
        tags = "@Smoke or @Regression",
        plugin = {"pretty", "junit:target/cucumber-results.xml", "html:target/cucumber.html", "json:target/cucumber.json", "html:target/site/cucumber-pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true
)
public class ParaBank_TestRunner extends AbstractTestNGCucumberTests {
}