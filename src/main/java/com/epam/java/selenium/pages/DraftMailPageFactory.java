package com.epam.java.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DraftMailPageFactory extends Factory {

    private By draftBy = By.linkText("Drafts");

    @Override
    public MailPage getMailPage(WebDriver driver) throws InterruptedException {
        driver.findElement(draftBy).click();
        Thread.sleep(10*1000);
        return new DraftMailPage(driver);
    }
}
