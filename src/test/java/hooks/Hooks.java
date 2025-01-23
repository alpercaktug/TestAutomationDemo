package hooks;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("(Before Scenario) Execute Scenario Tags: " + scenario.getSourceTagNames());

        if (scenario.getSourceTagNames().contains("@browser")) {
            System.out.println("This is an UI Test");
        } else if (scenario.getSourceTagNames().contains("@mobile")) {
            System.out.println("This is a Mobile Test");
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("(After Scenario)");
        if (scenario.getSourceTagNames().contains("@browser")) {
            System.out.println("(After Scenario) Quit Driver");
            DriverManager.quitDriver();
        }
    }
}
