package com.epam.java.selenium.tests;

import com.epam.java.selenium.entities.Email;
import com.epam.java.selenium.entities.EmailBuilder;
import com.epam.java.selenium.pages.*;
import com.epam.java.selenium.pages.factory.Factory;
import com.epam.java.selenium.pages.factory.SentMailPageFactory;
import com.epam.java.selenium.utils.Utils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class NewMailTest extends BaseTest {

    private Utils utils = new Utils();
    private HomePage homePage;

    @Test
    public void newMail() throws InterruptedException {
        String subject = utils.genTimestamp();
        homePage = login();
        Email email = new EmailBuilder().setBody("send by selenium").setTo("module7.epam@gmail.com").setSubject(subject).build();
        homePage.createDraftMail(email);
        DraftMailPage draftMailPage = homePage.openDraftMailPage(driver);
        WebElement mail = draftMailPage.getLatestMail();
        String text = draftMailPage.getMailText(mail);
        assertTrue(text.contains(subject));
        draftMailPage.sendMail(mail);
        Factory sentMailPageFactory = new SentMailPageFactory();
        MailPage sentMailPage = sentMailPageFactory.getMailPage(driver);
        mail = sentMailPage.getLatestMail();
        text = sentMailPage.getMailText(mail);
        assertTrue(text.contains(subject));
        logout();
    }

}
