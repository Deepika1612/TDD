package com.mab.test.runners;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features", tags = {"@Smoke"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/runwebat", "html:output/html-report",
        "json:target/cucumber-report/runwebat/cucumber.json",
        "rerun:target/cucumber-report/runwebat/rerun.txt"},
        glue = "com.mab.test")
public class RunWebATSuite extends AbstractTestNGCucumberTests {


}

