package com.epam.java.selenium.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.epam.java.selenium.utils.Utils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NewMailSelenideTest {

    private Utils utils = new Utils();

    @Test
    public void newMail() throws InterruptedException {
        String subject = utils.genTimestamp();
        open("http://mail.google.com");
        $(By.xpath("//input[@type='email']")).sendKeys("module7.epam@gmail.com");
        $(By.id("identifierNext")).click();
        $(By.xpath("//input[@type='password']")).waitUntil(Condition.enabled, 60*1000);
        $(By.xpath("//input[@type='password']")).click();
        $(By.xpath("//input[@type='password']")).sendKeys("Epam_886633!");
        $(By.id("passwordNext")).click();
        $(By.xpath("//div[text()='Compose']")).waitUntil(Condition.enabled, 60*1000);
        $(By.xpath("//div[text()='Compose']")).click();
        Thread.sleep(3000);
        $(By.name("to")).waitUntil(Condition.enabled, 60*1000);
        $(By.name("to")).sendKeys("samuel_shen@epam.com");
        $(By.name("subjectbox")).sendKeys(subject);
        $(By.xpath("//div[@aria-label='Message Body']")).sendKeys("send by selenium");
        $(By.xpath("//img[@alt='Close']")).click();
        Thread.sleep(1000);
        $(By.linkText("Drafts")).click();
        Thread.sleep(10*1000);
        ElementsCollection mailRecords = $$(By.xpath("//div[@role='main']//table[@role='grid'][1]//tr"));
        mailRecords.get(0).shouldHave(Condition.text(subject));
        mailRecords.get(0).click();
        $(By.xpath("//div[text()='Send']")).click();
        $(By.linkText("Sent")).click();
        Thread.sleep(10*1000);
        mailRecords = $$(By.xpath("//div[@role='main']//table[@role='grid'][1]//tr"));
        mailRecords.get(0).should(Condition.matchesText(subject));
        $(By.xpath("//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/mail&service=mail']")).click();
        $(By.linkText("Sign out")).click();
//        $(By.xpath("//span[text()='Choose an account']")).waitUntil(Condition.visible, 60*1000);
    }

}
