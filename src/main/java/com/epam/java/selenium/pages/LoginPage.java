package com.epam.java.selenium.pages;

import com.epam.java.selenium.entities.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    protected static WebDriver driver;

    private By usernameBy = By.xpath("//input[@type='email']");
    private By idNextBtnBy = By.id("identifierNext");
    private By passwordBy = By.xpath("//input[@type='password']");
    private By pwdNextBtnBy = By.id("passwordNext");
    private By composeBy = By.xpath("//div[text()='Compose']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage login(User user) throws InterruptedException {
        driver.findElement(usernameBy).sendKeys(user.getUsername());
        driver.findElement(idNextBtnBy).click();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(passwordBy));
        driver.findElement(passwordBy).sendKeys(user.getPassword());
        driver.findElement(pwdNextBtnBy).click();
        wait.until(ExpectedConditions.elementToBeClickable(composeBy));
        Thread.sleep(1000*2);
        return new HomePage(driver);
    }
}
