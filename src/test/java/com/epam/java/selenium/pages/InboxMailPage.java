package com.epam.java.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InboxMailPage extends MailPage {
    public InboxMailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void sendMail(WebElement element) throws InterruptedException {

    }

    @Override
    public void discardMail(WebElement element) throws InterruptedException {

    }

}
