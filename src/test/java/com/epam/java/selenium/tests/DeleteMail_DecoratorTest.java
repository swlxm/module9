package com.epam.java.selenium.tests;

import com.epam.java.selenium.pages.*;
import com.epam.java.selenium.pages.decorator.MailPageDecorator;
import com.epam.java.selenium.pages.decorator.SentMailPageDecorator;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

public class DeleteMail_DecoratorTest extends BaseTest {

    private HomePage homePage;

    @Test
    public void deleteMail() throws InterruptedException {
        homePage = login();
        homePage.openSentMailPage();
        MailPageDecorator sentMailPage = new SentMailPageDecorator(driver, new SentMailPage(driver));
        WebElement mail = sentMailPage.getLatestMail();
        String text = sentMailPage.getMailText(mail);
        sentMailPage.deleteMail(mail);
        mail = sentMailPage.getLatestMail();
        assertFalse(sentMailPage.getMailText(mail).equals(text));
        logout();
    }
}
