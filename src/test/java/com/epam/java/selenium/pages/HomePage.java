package com.epam.java.selenium.pages;

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

    public void createDraftMail(String to, String subject, String body) throws InterruptedException {
        driver.findElement(composeBy).click();
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(toBy));
        driver.findElement(toBy).sendKeys(to);
        driver.findElement(subjectBy).sendKeys(subject);
        driver.findElement(bodyBy).sendKeys(body);
        driver.findElement(closeBtnBy).click();
        Thread.sleep(1000);
    }

    public MailPage getDraftMailPage() throws InterruptedException {
        driver.findElement(draftBy).click();
        Thread.sleep(10*1000);
        return new DraftMailPage(driver);
    }

    public MailPage getSentMailPage() throws InterruptedException {
        driver.findElement(sentBy).click();
        Thread.sleep(10*1000);
        return new SentMailPage(driver);
    }

    public void logout() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/mail&service=mail']")).click();
        Thread.sleep(2*1000);
        driver.findElement(By.linkText("Sign out")).click();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Choose an account']")));
    }
}
