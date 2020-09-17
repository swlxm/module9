package com.epam.java.selenium.elements;

import com.epam.java.selenium.entities.Email;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.*;

public class Home extends HtmlElement {

    @Name("Compose button")
    @FindBy(xpath = "//div[text()='Compose']")
    private Button composeBtn;

    @Name("To input box")
    @FindBy(name = "to")
    @Timeout(60)
    private TextInput to;

    @Name("Email body")
    @FindBy(xpath = "//div[@aria-label='Message Body']")
    private TextBlock body;

    @Name("Subject")
    @FindBy(name = "subjectbox")
    private TextInput subject;

    @Name("CloseBtn")
    @FindBy(xpath = "//img[@alt='Close']")
    private Image closeBtn;

    @Name("Drafts link")
    @FindBy(linkText = "Drafts")
    private Link draftsLnk;

    @Name("Sent link")
    @FindBy(linkText = "Sent")
    private Link sentLnk;

    public void createDraftMail(Email email) throws InterruptedException {
        composeBtn.click();
        Thread.sleep(3000);
//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        wait.until(ExpectedConditions.elementToBeClickable(toBy));
        to.sendKeys(email.getTo());
        subject.sendKeys(email.getSubject());
        body.sendKeys(email.getBody());
        closeBtn.click();
        Thread.sleep(1000);
    }

    public void gotoDraftPage() throws InterruptedException {
        draftsLnk.click();
        Thread.sleep(10*1000);
    }

    public void gotoSentPage() throws InterruptedException {
        sentLnk.click();
        Thread.sleep(10*1000);
    }
}
