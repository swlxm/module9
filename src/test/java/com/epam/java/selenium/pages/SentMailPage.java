package com.epam.java.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SentMailPage extends MailPage {
    public SentMailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void sendMail(WebElement element) {

    }

    @Override
    public void discardMail(WebElement element) {

    }
}
