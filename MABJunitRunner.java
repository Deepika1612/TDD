package com.mab.test.runners.harvester;
import cucumber.api.CucumberOptions;
import com.cucumber.listener.Reporter;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.junit.AfterClass;
import java.io.File;
//Junit Runner to be integrated with VSTS Builds
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", tags = {"@Smoke"}, monochrome = true, plugin = {
		"pretty", "html:target/cucumber-report/runwebat","com.cucumber.listener.ExtentCucumberFormatter:src/test/resources/testreports/CucumberReports/report.html",
		"json:target/cucumber-report/single/cucumber.json",
		"rerun:target/cucumber-report/single/rerun.txt"},
		glue = "com.mab.test")
public class MABJunitRunner {

	@AfterClass
	public static void setup() {

			Reporter.loadXMLConfig(new File("target/cucumber-report/extent-report/config.xml"));
			Reporter.setSystemInfo("user", System.getProperty("user.name"));
			Reporter.setSystemInfo("os", "Windows10");
			Reporter.setTestRunnerOutput("Harvester Online booking");
		}

	}



