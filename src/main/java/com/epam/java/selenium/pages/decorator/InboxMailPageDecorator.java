package com.epam.java.selenium.pages.decorator;

import com.epam.java.selenium.pages.MailPage;
import org.openqa.selenium.WebDriver;

public class InboxMailPageDecorator extends MailPageDecorator {
    public InboxMailPageDecorator(WebDriver driver, MailPage mailPage) {
        super(driver, mailPage);
    }
}
