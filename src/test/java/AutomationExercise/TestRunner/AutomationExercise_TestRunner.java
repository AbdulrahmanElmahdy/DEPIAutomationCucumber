package AutomationExercise.TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/resources/AutomationExerciseFeatures"},
        glue = {"AutomationExercise.TestRunner.AutomationExerciseHooks", "AutomationExercise.TestRunner.AutomationExerciseStepDefin", "AutomationExercise.TestRunner.AutomationExerciseData", "AutomationExercise.TestRunner.AutomationExerciseLocators"},
        tags = "@Smoke or @Regression",
        plugin = {"pretty", "junit:target/cucumber-results.xml", "html:target/cucumber.html", "json:target/cucumber.json", "html:target/site/cucumber-pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true
)
public class AutomationExercise_TestRunner extends AbstractTestNGCucumberTests {
}
