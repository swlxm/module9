package com.epam.java.selenium.pages;

import com.epam.java.selenium.elements.Mail;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DraftMailPage extends MailPage {

    private Mail mail;

    public DraftMailPage(WebDriver driver) {
        super(driver);
    }

    public void sendMail(List<WebElement> element) throws InterruptedException {
        mail.sendMail(element);
    }

    public void discardMail(List<WebElement> element) throws InterruptedException {
        By discardBy = By.xpath("//div[text()='Discard drafts']");
        Actions action = new Actions(driver);
        action.moveToElement(element.get(0)).perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        action.contextClick(element.get(0)).perform();
        Thread.sleep(1000);
        WebElement discardBtn = driver.findElement(discardBy);
        js.executeScript("discardBtn = arguments[0];" +
                "original_style = discardBtn.getAttribute('style');" +
                "discardBtn.setAttribute('style', original_style + \";" +
                " border: 2px solid red;\");" +
                "setTimeout(function(){discardBtn.setAttribute('style', original_style);}, 3000);", discardBtn);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);
    }

}
