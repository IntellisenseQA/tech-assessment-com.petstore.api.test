import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/cucumber-reports/TestReport.html" },features = "src/test/features",tags = "@apiTest")

public class CucumberRunner {
}
