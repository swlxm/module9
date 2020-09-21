package com.epam.java.selenium.tests;

import com.epam.java.selenium.pages.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DeleteMailTest extends BaseTest {

    private HomePage homePage;

    @Test
    public void deleteMail() throws InterruptedException {
        homePage = login();
        Factory sentMailPageFactory = new SentMailPageFactory();
        MailPage mailMainPage = sentMailPageFactory.getMailPage(driver);
        WebElement mail = mailMainPage.getLatestMail();
        String text = mailMainPage.getMailText(mail);
        String subject = text.split("\n")[2];
        mailMainPage.deleteMail(mail);
        mail = mailMainPage.getLatestMail();
        assertNotEquals(mailMainPage.getMailText(mail).split("\n")[2], subject);
        logout();
    }
}
