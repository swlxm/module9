package com.epam.java.selenium.tests;

import com.epam.java.selenium.pages.*;
import com.epam.java.selenium.pages.decorator.MailPageDecorator;
import com.epam.java.selenium.pages.decorator.SentMailPageDecorator;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;

public class DeleteMail_DecoratorTest extends BaseTest {

    private HomePage homePage;

    @Test
    public void deleteMail() throws InterruptedException {
        homePage = login();
//        Factory sentMailPageFactory = new SentMailPageFactory();
//        MailPage mailMainPage = sentMailPageFactory.getMailPage(driver);
        homePage.openSentMailPage();
        MailPageDecorator sentMailPage = new SentMailPageDecorator(driver, new SentMailPage(driver));
        WebElement mail = sentMailPage.getLatestMail();
        String text = sentMailPage.getMailText(mail);
//        String subject = text.split("\n")[3];
        sentMailPage.deleteMail(mail);
        mail = sentMailPage.getLatestMail();
        assertFalse(sentMailPage.getMailText(mail).equals(text));
        logout();
    }
}
