package com.epam.java.selenium.tests;

import com.epam.java.selenium.entities.Email;
import com.epam.java.selenium.pages.DraftMailPage;
import com.epam.java.selenium.pages.DraftMailPageFactory;
import com.epam.java.selenium.pages.Factory;
import com.epam.java.selenium.pages.HomePage;
import com.epam.java.selenium.utils.Utils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DiscardMailTest extends BaseTest {

    private Utils utils = new Utils();
    private DraftMailPage draftMailPage;
    private HomePage homePage;

    @Test
    public void newDraftMail() throws InterruptedException {
        String subject = utils.genTimestamp();
        homePage = login();
        Email email = new Email();
        email.setBody("send by selenium");
        email.setSubject(subject);
        email.setTo("samuel_shen@epam.com");
        homePage.createDraftMail(email);
        Factory draftMailPageFactory = new DraftMailPageFactory();
        draftMailPage = (DraftMailPage) draftMailPageFactory.getMailPage(driver);
        WebElement mail = draftMailPage.getLatestMail();
        String text = draftMailPage.getMailText(mail);
        assertEquals(text.split("\n")[1], subject);
    }

    @Test(dependsOnMethods = {"newDraftMail"})
    public void discardMail() throws InterruptedException {
        WebElement mail = draftMailPage.getLatestMail();
        String text = draftMailPage.getMailText(mail);
        String subject = text.split("\n")[1];
        draftMailPage.discardMail(mail);
        mail = draftMailPage.getLatestMail();
        assertNotEquals(draftMailPage.getMailText(mail).split("\n")[1], subject);
    }

}
