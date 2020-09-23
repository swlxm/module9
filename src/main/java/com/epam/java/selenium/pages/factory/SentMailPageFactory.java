package com.epam.java.selenium.pages.factory;

import com.epam.java.selenium.pages.MailPage;
import com.epam.java.selenium.pages.SentMailPage;
import com.epam.java.selenium.pages.factory.Factory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Samuel_Shen
 */
public class SentMailPageFactory extends Factory {

    private By sentBy = By.linkText("Sent");

    @Override
    public MailPage getMailPage(WebDriver driver) throws InterruptedException {
        driver.findElement(sentBy).click();
        Thread.sleep(10*1000);
        return new SentMailPage(driver);
    }
}
