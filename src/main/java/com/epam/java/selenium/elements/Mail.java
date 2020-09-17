package com.epam.java.selenium.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Table;

import java.util.List;

public class Mail extends HtmlElement {

    @Name("Mail table")
    @FindBy(xpath = "//div[@role='main']//table[@role='grid'][1]")
    private Table mailTable;

    @Name("Delete button")
    @FindBy(xpath = "//div[@aria-label='Delete']")
    private Button deleteBtn;

    @Name("Send button")
    @FindBy(xpath = "//div[text()='Send']")
    private Button sendBtn;

    public List<WebElement> getLatestMail() {
        List<WebElement> mailRecords = mailTable.getRows().get(0);
        return mailRecords;
    }

    public String getMailText(List<WebElement> elements) {
        StringBuilder sb = new StringBuilder();
        for(WebElement we:elements) {
            sb.append(we.getText());
        }
        return sb.toString();
    }

    public void deleteMail(WebDriver driver, List<WebElement> elements) throws InterruptedException {
        Actions action = new Actions(driver);
        action.moveToElement(elements.get(0)).perform();
        action.contextClick(elements.get(0)).perform();
        Thread.sleep(1000);
//        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        deleteBtn.click();
        Thread.sleep(1000);
    }

    public void sendMail(List<WebElement> element) throws InterruptedException {
        element.get(0).click();
        sendBtn.click();
        Thread.sleep(10*1000);

    }


}
