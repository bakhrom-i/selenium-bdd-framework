package com.portfolio.bdd.pages;

import com.portfolio.bdd.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object for https://the-internet.herokuapp.com/login — a public,
 * automation-friendly demo login form. Selectors match its DOM:
 *   - inputs are #username / #password
 *   - submit is the only <button type="submit"> on the page
 *   - flash messages render as <div id="flash" class="flash error">...
 *     for failures or <div id="flash" class="flash success">... for success
 */
public class LoginPage {
    private final WebDriver driver = DriverFactory.get();

    @FindBy(id = "username") private WebElement usernameInput;
    @FindBy(id = "password") private WebElement passwordInput;
    @FindBy(css = "button[type=submit]") private WebElement submitButton;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void login(String user, String pwd) {
        usernameInput.clear();
        usernameInput.sendKeys(user);
        passwordInput.clear();
        passwordInput.sendKeys(pwd);
        submitButton.click();
    }

    /** True when the-internet.herokuapp.com renders an error flash banner. */
    public boolean isError() {
        return !driver.findElements(By.cssSelector("#flash.error")).isEmpty();
    }
}
