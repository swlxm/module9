package com.epam.java.selenium.tests;

import com.epam.java.selenium.driver.DriverSingleton;
import com.epam.java.selenium.entities.User;
import com.epam.java.selenium.pages.HomePage;
import com.epam.java.selenium.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    public static String email = null;
    public static String password = null;

    @Name("Sign out")
    @FindBy(linkText = "Sign out")
    private Link signout;

    @Name("Logout icon")
    @FindBy(xpath = "//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/mail&service=mail']")
    private Link logout;

    @Name("Choose an account button")
    @FindBy(xpath = "//span[text()='Choose an account']")
    private Button chooseAccountBtn;

    @BeforeClass
    @Parameters({"env", "browser"})
    public void init(String env, String browser) throws IOException {
        Properties envProp = new Properties();
        envProp.load(this.getClass().getResourceAsStream("/" + env + ".properties"));
        email = envProp.getProperty("email");
        password = envProp.getProperty("password");
        driver = DriverSingleton.getDriver(env, browser);
        driver.get("http://mail.google.com");
    }

    public HomePage login() throws InterruptedException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user);
        return homePage;
    }

    public void logout() throws InterruptedException {
//        driver.findElement(By.xpath("//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/mail&service=mail']")).click();
        logout.click();
        Thread.sleep(2*1000);
//        driver.findElement(By.linkText("Sign out")).click();
        signout.click();
        chooseAccountBtn.exists();
//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Choose an account']")));
    }

    @AfterClass
    public void tearDown() {
        DriverSingleton.close();
    }

}
