package com.epam.java.selenium;

import com.epam.java.selenium.pages.HomePage;
import com.epam.java.selenium.pages.LoginPage;
import com.epam.java.selenium.pages.MailPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DeleteMailTest {

    private WebDriver driver = null;
    private Utils utils = new Utils();

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en-us");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("http://mail.google.com");
    }

    @Test
    public void deleteMail() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login("module7.epam@gmail.com", "Epam_886633!");
        MailPage mailMainPage = homePage.getSentMailPage();
        WebElement mail = mailMainPage.getLatestMail();
        String text = mailMainPage.getMailText(mail);
        String subject = text.split("\n")[2];
        mailMainPage.deleteMail(mail);
        mail = mailMainPage.getLatestMail();
        assertNotEquals(mailMainPage.getMailText(mail).split("\n")[2], subject);
        homePage.logout();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
