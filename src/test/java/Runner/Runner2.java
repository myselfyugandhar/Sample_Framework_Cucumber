package Runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Feature",glue={"stepDef"},tags="@searchCheGuevara")
public class Runner2 extends AbstractTestNGCucumberTests{

}
