package com.epam.java.selenium.pages;

import com.epam.java.selenium.entities.Email;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    protected static WebDriver driver;

    private By composeBy = By.xpath("//div[text()='Compose']");
    private By toBy = By.name("to");
    private By subjectBy = By.name("subjectbox");
    private By bodyBy = By.xpath("//div[@aria-label='Message Body']");
    private By closeBtnBy = By.xpath("//img[@alt='Close']");
    private By draftBy = By.linkText("Drafts");
    private By sentBy = By.linkText("Sent");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void createDraftMail(Email email) throws InterruptedException {
        driver.findElement(composeBy).click();
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(toBy));
        driver.findElement(toBy).sendKeys(email.getTo());
        driver.findElement(subjectBy).sendKeys(email.getSubject());
        driver.findElement(bodyBy).sendKeys(email.getBody());
        driver.findElement(closeBtnBy).click();
        Thread.sleep(1000);
    }

    public DraftMailPage getDraftMailPage() throws InterruptedException {
        driver.findElement(draftBy).click();
        Thread.sleep(10*1000);
        return new DraftMailPage(driver);
    }

    public SentMailPage getSentMailPage() throws InterruptedException {
        driver.findElement(sentBy).click();
        Thread.sleep(10*1000);
        return new SentMailPage(driver);
    }

}
