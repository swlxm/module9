package com.epam.java.selenium.tests;

import com.epam.java.selenium.entities.Email;
import com.epam.java.selenium.pages.*;
import com.epam.java.selenium.utils.Utils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NewMailTest extends BaseTest {

    private Utils utils = new Utils();
    private HomePage homePage;

    @Test
    public void newMail() throws InterruptedException {
        String subject = utils.genTimestamp();
        homePage = login();
        Email email = new Email();
        email.setBody("send by selenium");
        email.setSubject(subject);
        email.setTo("samuel_shen@epam.com");
        homePage.createDraftMail(email);
        DraftMailPage draftMailPage = homePage.getDraftMailPage();
        WebElement mail = draftMailPage.getLatestMail();
        String text = draftMailPage.getMailText(mail);
        assertTrue(text.contains(subject));
        draftMailPage.sendMail(mail);
        SentMailPage sentMailPage = homePage.getSentMailPage();
        mail = sentMailPage.getLatestMail();
        text = sentMailPage.getMailText(mail);
        assertEquals(text.split("\n")[2], subject);
        logout();
    }

}
