package com.epam.java.selenium;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.epam.java.selenium.pages.HomePage;
import com.epam.java.selenium.pages.LoginPage;
import com.epam.java.selenium.pages.MailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NewMailTest {

    private WebDriver driver = null;
    private Utils utils = new Utils();
    private ChromeOptions options;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        options = new ChromeOptions();
        options.addArguments("--lang=en-us");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("http://mail.google.com");
    }

    @Test
    public void newMail2() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        String subject = utils.genTimestamp();
        HomePage homePage = loginPage.login("module7.epam@gmail.com", "Epam_886633!");
        homePage.createDraftMail("samuel_shen@epam.com", subject, "send by selenium");
        MailPage mailMainPage = homePage.getDraftMailPage();
        WebElement mail = mailMainPage.getLatestMail();
        String text = mailMainPage.getMailText(mail);
        assertTrue(text.contains(subject));
        mailMainPage.sendMail(mail);
        mailMainPage = homePage.getSentMailPage();
        mail = mailMainPage.getLatestMail();
        text = mailMainPage.getMailText(mail);
        assertEquals(text.split("\n")[2], subject);
        homePage.logout();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
