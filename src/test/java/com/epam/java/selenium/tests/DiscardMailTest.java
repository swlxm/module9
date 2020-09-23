package com.epam.java.selenium.tests;

import com.epam.java.selenium.entities.Email;
import com.epam.java.selenium.entities.EmailBuilder;
import com.epam.java.selenium.pages.DraftMailPage;
import com.epam.java.selenium.pages.factory.Factory;
import com.epam.java.selenium.pages.HomePage;
import com.epam.java.selenium.utils.Utils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DiscardMailTest extends BaseTest {

    private Utils utils = new Utils();
    private HomePage homePage;

    @Test
    public void discardMail() throws InterruptedException {
        String subject = utils.genTimestamp();
        homePage = login();
        Email email = new EmailBuilder().setBody("send by selenium").setTo("module7.epam@gmail.com").setSubject(subject).createEmail();
//        email.setBody("send by selenium");
//        email.setSubject(subject);
//        email.setTo("module7.epam@gmail.com");
        homePage.createDraftMail(email);
        DraftMailPage draftMailPage = homePage.openDraftMailPage(driver);
        WebElement mail = draftMailPage.getLatestMail();
        String text = draftMailPage.getMailText(mail);
        assertTrue(text.contains(subject));
        mail = draftMailPage.getLatestMail();
        text = draftMailPage.getMailText(mail);
        subject = text.split("\n")[1];
        draftMailPage.discardMail(mail);
        mail = draftMailPage.getLatestMail();
        assertFalse(draftMailPage.getMailText(mail).contains(subject));
        logout();
    }

}
