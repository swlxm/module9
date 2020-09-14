package com.epam.java.selenium;

import com.epam.java.selenium.pages.HomePage;
import com.epam.java.selenium.pages.MailPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DeleteMailTest extends BaseTest {

    private HomePage homePage;

    @Test
    public void deleteMail() throws InterruptedException {
        homePage = login();
        MailPage mailMainPage = homePage.getSentMailPage();
        WebElement mail = mailMainPage.getLatestMail();
        String text = mailMainPage.getMailText(mail);
        String subject = text.split("\n")[2];
        mailMainPage.deleteMail(mail);
        mail = mailMainPage.getLatestMail();
        assertNotEquals(mailMainPage.getMailText(mail).split("\n")[2], subject);
        logout();
    }
}
