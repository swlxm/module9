package com.epam.java.selenium.pages;

import org.openqa.selenium.WebDriver;

public abstract class Factory {

    protected static WebDriver driver;

    public abstract MailPage getMailPage(WebDriver driver) throws InterruptedException;
}
