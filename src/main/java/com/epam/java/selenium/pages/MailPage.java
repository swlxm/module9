package com.epam.java.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Basic class for different mail pages, inbox/sent/draft...
 * @author Samuel_Shen
 */
public abstract class MailPage {

    protected static WebDriver driver;

    private By mailListBy = By.xpath("//div[@role='main']//table[@role='grid'][1]//tr");
    private By deleteBy = By.xpath("//div[@aria-label='Delete']");
    private By bodyBy = By.xpath("//div[@aria-label='Message Body']");
    private By sendBy = By.xpath("//div[text()='Send']");


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
        Thread.sleep(1000);
        driver.findElement(deleteBy).click();
        Thread.sleep(1000);
    }

    public void reply(WebElement element, String body) throws InterruptedException {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        action.contextClick(element).perform();
        Thread.sleep(1000);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);
        driver.findElement(bodyBy).sendKeys(body);
        driver.findElement(sendBy).click();
        Thread.sleep(10*1000);
    }

}
