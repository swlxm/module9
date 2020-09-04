package com.epam.java.selenium;

import com.epam.java.selenium.pages.HomePage;
import com.epam.java.selenium.pages.LoginPage;
import com.epam.java.selenium.pages.MailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NewMailTest {

    private WebDriver driver = null;
    private Utils utils = new Utils();

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en-us");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("http://mail.google.com");
    }

//    @Test
    public void newMail() throws InterruptedException {
//        String subject = utils.genTimestamp();
//        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("module7.epam@gmail.com");
//        driver.findElement(By.id("identifierNext")).click();
//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")));
//        driver.findElement(By.xpath("//input[@type='password']")).click();
//        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Epam_886633!");
//        driver.findElement(By.id("passwordNext")).click();
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Compose']")));
//        driver.findElement(By.xpath("//div[text()='Compose']")).click();
//        Thread.sleep(3000);
//        wait.until(ExpectedConditions.elementToBeClickable(By.name("to")));
//        driver.findElement(By.name("to")).sendKeys("samuel_shen@epam.com");
//        driver.findElement(By.name("subjectbox")).sendKeys(subject);
//        driver.findElement(By.xpath("//div[@aria-label='Message Body']")).sendKeys("send by selenium");
//        driver.findElement(By.xpath("//img[@alt='Close']")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.linkText("Drafts")).click();
//        Thread.sleep(10*1000);
//        List<WebElement> mailRecords = driver.findElements(By.xpath("//div[@role='main']//table[@role='grid'][1]//tr"));
//        String text = mailRecords.get(0).getText();
//        assertTrue(text.contains(subject));
//        mailRecords.get(0).click();
//        driver.findElement(By.xpath("//div[text()='Send']")).click();
//        driver.findElement(By.linkText("Sent")).click();
//        Thread.sleep(10*1000);
//        mailRecords = driver.findElements(By.xpath("//div[@role='main']//table[@role='grid'][1]//tr"));
//        text = mailRecords.get(0).getText();
//        assertTrue(text.contains(subject));
//        driver.findElement(By.xpath("//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/mail&service=mail']")).click();
//        driver.findElement(By.linkText("Sign out")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Choose an account']")));
    }

    @Test
    public void newMail2() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        String subject = utils.genTimestamp();
        HomePage homePage = loginPage.login("module7.epam@gmail.com", "Epam_886633!");
        homePage.createDraftMail("samuel_shen@epam.com", subject, "send by selenium");
        MailPage mailMainPage = homePage.getDraftMailPage();
        WebElement mail = mailMainPage.getLatestMail();
        String text = mailMainPage.getMailText(mail);
        assertTrue(text.contains(subject));
        mailMainPage.sendMail(mail);
        mailMainPage = homePage.getSentMailPage();
        mail = mailMainPage.getLatestMail();
        text = mailMainPage.getMailText(mail);
        assertEquals(text.split("\n")[2], subject);
        homePage.logout();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
