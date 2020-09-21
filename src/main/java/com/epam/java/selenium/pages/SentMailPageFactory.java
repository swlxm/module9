package com.epam.java.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SentMailPageFactory extends Factory {

    private By sentBy = By.linkText("Sent");

    @Override
    public MailPage getMailPage(WebDriver driver) throws InterruptedException {
        driver.findElement(sentBy).click();
        Thread.sleep(10*1000);
        return new SentMailPage(driver);
    }
}
