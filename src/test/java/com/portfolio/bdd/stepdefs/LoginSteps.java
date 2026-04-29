package com.portfolio.bdd.stepdefs;

import com.portfolio.bdd.driver.DriverFactory;
import com.portfolio.bdd.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    // Public automation-testing target. Override with -Dbase.url=... in CI
    // if pointing at a different system under test.
    private static final String DEFAULT_LOGIN_URL = "https://the-internet.herokuapp.com/login";

    private LoginPage loginPage;

    @Given("the user is on the login page")
    public void open_login() {
        DriverFactory.get().get(System.getProperty("base.url", DEFAULT_LOGIN_URL));
        loginPage = new LoginPage();
    }

    @When("the user logs in with username {string} and password {string}")
    public void login(String user, String pwd) {
        loginPage.login(user, pwd);
    }

    @Then("the dashboard is displayed")
    public void dashboard_displayed() {
        // the-internet.herokuapp.com redirects authenticated users to /secure.
        assertTrue(
            DriverFactory.get().getCurrentUrl().contains("/secure"),
            "Expected redirect to /secure after successful login, got: "
                + DriverFactory.get().getCurrentUrl()
        );
    }

    @Then("a login error is shown")
    public void error_shown() {
        assertTrue(loginPage.isError(), "Expected an error flash banner to be visible");
    }
}
