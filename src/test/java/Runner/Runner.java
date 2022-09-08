package Runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Feature",glue={"stepDef"},tags=" @searchYugandhar")
public class Runner extends AbstractTestNGCucumberTests{

}
