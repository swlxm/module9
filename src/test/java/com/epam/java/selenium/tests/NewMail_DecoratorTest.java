package com.epam.java.selenium.tests;

import com.epam.java.selenium.entities.Email;
import com.epam.java.selenium.entities.EmailBuilder;
import com.epam.java.selenium.pages.*;
import com.epam.java.selenium.pages.decorator.MailPageDecorator;
import com.epam.java.selenium.pages.decorator.SentMailPageDecorator;
import com.epam.java.selenium.pages.factory.Factory;
import com.epam.java.selenium.utils.Utils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NewMail_DecoratorTest extends BaseTest {

    private Utils utils = new Utils();
    private HomePage homePage;

    @Test
    public void newMail() throws InterruptedException {
        String subject = utils.genTimestamp();
        homePage = login();
        Email email = new EmailBuilder().setBody("send by selenium").setTo("module7.epam@gmail.com").setSubject(subject).createEmail();
//        email.setBody("send by selenium");
//        email.setSubject(subject);
//        email.setTo("samuel_shen@epam.com");
        homePage.createDraftMail(email);
        DraftMailPage draftMailPage = homePage.openDraftMailPage(driver);
        WebElement mail = draftMailPage.getLatestMail();
        String text = draftMailPage.getMailText(mail);
        assertTrue(text.contains(subject));
        draftMailPage.sendMail(mail);
//        Factory sentMailPageFactory = new SentMailPageFactory();
//        SentMailPage sentMailPage = (SentMailPage) sentMailPageFactory.getMailPage(driver);
        homePage.openSentMailPage();
        MailPageDecorator sentMailPage = new SentMailPageDecorator(driver, new SentMailPage(driver));
        mail = sentMailPage.getLatestMail();
        text = sentMailPage.getMailText(mail);
        assertEquals(text.split("\n")[2], subject);
        logout();
    }

}
