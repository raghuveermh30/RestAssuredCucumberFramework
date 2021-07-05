package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "D:\\Automation Repos\\MasterChanges\\RestAssuredWithCucumber\\src\\test\\java\\features",
        glue = {"stepdefnitaion"},
        dryRun = false,
        monochrome = true,
       // tags = "@AddPlace"
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"}
)
public class TestRunner {


}
