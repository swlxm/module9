package com.epam.java.selenium.pages;

import com.epam.java.selenium.elements.Mail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

public abstract class MailPage {

    protected static WebDriver driver;
    private Mail mail;

    public MailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public List<WebElement> getLatestMail() {
        return mail.getLatestMail();
    }

    public String getMailText(List<WebElement> elements) {
        return mail.getMailText(elements);
    }

    public void deleteMail(List<WebElement> elements) throws InterruptedException {
        mail.deleteMail(driver, elements);
    }
//    public static MailPage getInstance(Enum page) {
//        if(page.name().equals("INBOX"))
//            return new InboxMailPage(driver);
//        else if(page.name().equals("DRAFT"))
//            return new DraftMailPage(driver);
//        else if(page.name().equals("SENT"))
//            return new SentMailPage(driver);
//        else
//            return new InboxMailPage(driver);
//    }
}
