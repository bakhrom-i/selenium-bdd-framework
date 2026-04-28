package com.portfolio.bdd.pages;

import com.portfolio.bdd.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private final WebDriver driver = DriverFactory.get();

    @FindBy(id = "username") private org.openqa.selenium.WebElement usernameInput;
    @FindBy(id = "password") private org.openqa.selenium.WebElement passwordInput;
    @FindBy(css = "button[type=submit]") private org.openqa.selenium.WebElement submitButton;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void login(String user, String pwd) {
        usernameInput.sendKeys(user);
        passwordInput.sendKeys(pwd);
        submitButton.click();
    }

    public boolean isError() {
        return !driver.findElements(By.cssSelector(".alert-error")).isEmpty();
    }
}
