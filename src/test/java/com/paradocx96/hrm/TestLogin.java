package com.paradocx96.hrm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLogin {

    private WebDriver webDriver;

    @BeforeMethod
    private WebDriver beforeMethod() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("http://hrm.pragmatictestlabs.com");
        return webDriver;
    }

    @Test
    public void testValidUserLogin() {

        //Type username
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type password
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");

        //Click the login button
        webDriver.findElement(By.id("btnLogin")).click();

        //Verify welcome message
        String welcome = webDriver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(welcome,"Welcome Admin");
    }

    @Test
    public void testValidUserLoginWithEnterKeyPress() {

        //Type username
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type password and Press Enter key
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321" + Keys.ENTER);

        //Verify welcome message
        String welcome = webDriver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(welcome,"Welcome Admin");
    }

    @Test
    public void testValidUserLoginSubmitMethod() {

        //Type username
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type password and Press Enter key
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        webDriver.findElement(By.id("txtPassword")).submit();

        //Verify welcome message
        String welcome = webDriver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(welcome,"Welcome Admin");
    }

    @AfterMethod
    private void afterMethod() {
        //Close the browser
        webDriver.close();
    }
}
