package com.mab.test.runners;
import cucumber.api.CucumberOptions;
import com.cucumber.listener.Reporter;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.junit.AfterClass;
import java.io.File;
//Junit Runner to be integrated with VSTS Builds
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", tags = {"@Smoke"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/runwebat","com.cucumber.listener.ExtentCucumberFormatter:src/test/resources/testreports/CucumberReports/DATE(dd-MM-yyyy)+report.html",
        "json:target/cucumber-report/single/cucumber.json",
        "rerun:target/cucumber-report/single/rerun.txt"},
        glue = "com.mab.test")
public class JunitSuiteRunner {

    @AfterClass
    public static void setup() {

        //Reporter.loadXMLConfig(new File("target/cucumber-report/extent-report/config.xml"));
        Reporter.setSystemInfo("OS", "Windows10");
        Reporter.setTestRunnerOutput("Harvester Online booking");
        Reporter.setSystemInfo("Browser", "Chrome");
        Reporter.setSystemInfo("Selenium", "v3.7.1");
    }

}



