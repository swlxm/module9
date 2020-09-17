package com.epam.java.selenium.elements;

import com.epam.java.selenium.entities.User;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@Name("Login form")
@FindBy(id = "initialView")
public class Login extends HtmlElement {
    @Name("Email")
    @FindBy(xpath = "//input[@type='email']")
    private TextInput email;

    @Name("Password")
    @FindBy(xpath = "//input[@type='password']")
    @Timeout(60)
    private TextInput password;

    @Name("ID next button")
    @FindBy(id = "identifierNext")
    private Button idNextBtn;

    @Name("Password next button")
    @FindBy(id = "passwordNext")
    private Button pwdNextBtn;

    @Name("Compose button")
    @FindBy(xpath = "//div[text()='Compose']")
    @Timeout(60)
    private Button composeBtn;

    public void login(User user) throws InterruptedException {
        email.sendKeys(user.getEmail());
        idNextBtn.click();
//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        wait.until(ExpectedConditions.elementToBeClickable(passwordBy));
        password.sendKeys(user.getPassword());
        pwdNextBtn.click();
//        wait.until(ExpectedConditions.elementToBeClickable(composeBy));
        Thread.sleep(1000*2);

    }
}
