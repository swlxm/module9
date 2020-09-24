package com.epam.java.selenium.tests;

import com.epam.java.selenium.entities.Email;
import com.epam.java.selenium.entities.EmailBuilder;
import com.epam.java.selenium.pages.HomePage;
import com.epam.java.selenium.pages.InboxMailPage;
import com.epam.java.selenium.pages.SentMailPage;
import com.epam.java.selenium.pages.decorator.InboxMailPageDecorator;
import com.epam.java.selenium.pages.decorator.MailPageDecorator;
import com.epam.java.selenium.pages.decorator.SentMailPageDecorator;
import com.epam.java.selenium.utils.Utils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ReplyMail_DecoratorTest extends BaseTest {

    private Utils utils = new Utils();
    private HomePage homePage;
    private String subject;

    @Test
    public void replyMail() throws InterruptedException {
        subject = utils.genTimestamp();
        homePage = login();
        Email email = new EmailBuilder().setBody("send by selenium").setTo("module7.epam@gmail.com").setSubject(subject).build();
        homePage.sendMail(email);
        homePage.openInboxPage();
        MailPageDecorator inboxPage = new InboxMailPageDecorator(driver, new InboxMailPage(driver));
        WebElement mail = inboxPage.getLatestMail();
        String text = inboxPage.getMailText(mail);
        String subject = text.split("\n")[1];
        assertTrue(text.contains(subject));
        email.setBody("reply by selenium");
        inboxPage.reply(mail, email.getBody());
        homePage.openSentMailPage();
        MailPageDecorator sentMailPage = new SentMailPageDecorator(driver, new SentMailPage(driver));
        mail = sentMailPage.getLatestMail();
        text = sentMailPage.getMailText(mail);
        assertTrue(text.contains(subject));
        logout();
    }

}
