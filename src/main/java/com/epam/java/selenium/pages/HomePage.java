package com.epam.java.selenium.pages;

import com.epam.java.selenium.elements.Home;
import com.epam.java.selenium.entities.Email;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class HomePage {

    protected static WebDriver driver;
    private Home home;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public void createDraftMail(Email mail) throws InterruptedException {
        home.createDraftMail(mail);
    }

    public DraftMailPage getDraftMailPage() throws InterruptedException {
        home.gotoDraftPage();
        return new DraftMailPage(driver);
    }

    public SentMailPage getSentMailPage() throws InterruptedException {
        home.gotoSentPage();
        return new SentMailPage(driver);
    }

}
