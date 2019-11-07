package com.mab.test.runners;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features", tags = {"@Sanity"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/runwebat",
        "json:target/cucumber-report/single/cucumber.json",
        "rerun:target/cucumber-report/single/rerun.txt"},
        glue = "com.mab.test")
public class RunSingleSuite extends AbstractTestNGCucumberTests {
}