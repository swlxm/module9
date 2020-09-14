package com.epam.java.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public abstract class MailPage {

    protected static WebDriver driver;

    private By mailListBy = By.xpath("//div[@role='main']//table[@role='grid'][1]//tr");
    private By deleteBy = By.xpath("//div[@aria-label='Delete']");

    public MailPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getLatestMail() {
        List<WebElement> mailRecords = driver.findElements(mailListBy);
        return mailRecords.get(0);
    }

    public String getMailText(WebElement element) {
        String text = element.getText();
        return text;
    }

    public void deleteMail(WebElement element) throws InterruptedException {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        action.contextClick(element).perform();
//        Thread.sleep(1000);
//        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        driver.findElement(deleteBy).click();
//        Thread.sleep(1000);
    }

//    public static MailPage getInstance(Enum page) {
//        if(page.name().equals("INBOX"))
//            return new InboxMailPage(driver);
//        else if(page.name().equals("DRAFT"))
//            return new DraftMailPage(driver);
//        else if(page.name().equals("SENT"))
//            return new SentMailPage(driver);
//        else
//            return new InboxMailPage(driver);
//    }
}
