package com.portfolio.bdd.stepdefs;

import com.portfolio.bdd.driver.DriverFactory;
import com.portfolio.bdd.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("the user is on the login page")
    public void open_login() {
        DriverFactory.get().get(System.getProperty("base.url", "https://example.com/login"));
        loginPage = new LoginPage();
    }

    @When("the user logs in with username {string} and password {string}")
    public void login(String user, String pwd) {
        loginPage.login(user, pwd);
    }

    @Then("the dashboard is displayed")
    public void dashboard_displayed() {
        assertTrue(DriverFactory.get().getCurrentUrl().contains("/dashboard"));
    }

    @Then("a login error is shown")
    public void error_shown() {
        assertTrue(loginPage.isError());
    }
}
