package com.epam.java.selenium.pages.factory;

import com.epam.java.selenium.pages.InboxMailPage;
import com.epam.java.selenium.pages.MailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Samuel_Shen
 */
public class InboxMailPageFactory extends Factory {

    private By inboxBy = By.linkText("Inbox");

    @Override
    public MailPage getMailPage(WebDriver driver) throws InterruptedException {
        driver.findElement(inboxBy).click();
        Thread.sleep(10*1000);
        return new InboxMailPage(driver);
    }
}
