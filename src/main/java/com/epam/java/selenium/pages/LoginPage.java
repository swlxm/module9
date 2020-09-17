package com.epam.java.selenium.pages;

import com.epam.java.selenium.elements.Login;
import com.epam.java.selenium.entities.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class LoginPage {

    protected static WebDriver driver;
    private Login login;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public HomePage login(User user) throws InterruptedException {
        login.login(user);
        return new HomePage(driver);
    }
}
