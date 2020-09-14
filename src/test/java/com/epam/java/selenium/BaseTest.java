package com.epam.java.selenium;

import com.epam.java.selenium.driver.DriverSingleton;
import com.epam.java.selenium.entities.User;
import com.epam.java.selenium.pages.HomePage;
import com.epam.java.selenium.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    public static String username = null;
    public static String password = null;

    @BeforeClass
    public void init() throws IOException {
        Properties env = new Properties();
        env.load(this.getClass().getResourceAsStream("/qa.properties"));
        username = env.getProperty("username");
        password = env.getProperty("password");
        driver = DriverSingleton.getDriver();
        driver.get("http://mail.google.com");
    }

    public HomePage login() throws InterruptedException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user);
        return homePage;
    }

    public void logout() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/mail&service=mail']")).click();
//        Thread.sleep(2*1000);
        driver.findElement(By.linkText("Sign out")).click();
//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Choose an account']")));
    }

    @AfterClass
    public void tearDown() {
        DriverSingleton.close();
    }

}
