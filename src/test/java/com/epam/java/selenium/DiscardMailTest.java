package com.epam.java.selenium;

import com.epam.java.selenium.pages.HomePage;
import com.epam.java.selenium.pages.LoginPage;
import com.epam.java.selenium.pages.MailPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DiscardMailTest {

    private WebDriver driver = null;
    private Utils utils = new Utils();
    private MailPage mailMainPage;
    private HomePage homePage;

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
    public void newDraftMail() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        String subject = utils.genTimestamp();
        homePage = loginPage.login("module7.epam@gmail.com", "Epam_886633!");
        homePage.createDraftMail("samuel_shen@epam.com", subject, "send by selenium");
        mailMainPage = homePage.getDraftMailPage();
        WebElement mail = mailMainPage.getLatestMail();
        String text = mailMainPage.getMailText(mail);
        assertEquals(text.split("\n")[1], subject);
    }

    @Test(dependsOnMethods = {"newDraftMail"})
    public void discardMail() throws InterruptedException {
        WebElement mail = mailMainPage.getLatestMail();
        String text = mailMainPage.getMailText(mail);
        String subject = text.split("\n")[1];
        mailMainPage.discardMail(mail);
        mail = mailMainPage.getLatestMail();
        assertNotEquals(mailMainPage.getMailText(mail).split("\n")[1], subject);
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        homePage.logout();
        driver.close();
    }
}
