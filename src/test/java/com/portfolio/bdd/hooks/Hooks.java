package com.portfolio.bdd.hooks;

import com.portfolio.bdd.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] png = ((TakesScreenshot) DriverFactory.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(png, "image/png", scenario.getName());
        }
        DriverFactory.quit();
    }
}
