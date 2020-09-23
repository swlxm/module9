package com.epam.java.selenium.pages.decorator;

import com.epam.java.selenium.pages.MailPage;
import org.openqa.selenium.WebDriver;

public class MailPageDecorator extends MailPage {

    protected MailPage mailPage;

    public MailPageDecorator(WebDriver driver, MailPage mailPage) {
        super(driver);
        this.mailPage = mailPage;
    }
}
