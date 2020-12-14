package com.paradocx96.hrm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLogin {

    private WebDriver webDriver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    private WebDriver beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("http://hrm.pragmatictestlabs.com");
        return webDriver;
    }

    @AfterMethod
    private void afterMethod() {
        //Close the browser
        webDriver.close();
    }

    /*
     *Testing valid user login
     */

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
        Assert.assertEquals(welcome, "Welcome Admin");
    }

    @Test
    public void testValidUserLoginWithEnterKeyPress() {

        //Type username
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type password and Press Enter key
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321" + Keys.ENTER);

        //Verify welcome message
        String welcome = webDriver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(welcome, "Welcome Admin");
    }

    @Test
    public void testValidUserLoginSubmitMethod() {

        //Type username
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type password and Call Submit Method
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        webDriver.findElement(By.id("txtPassword")).submit();

        //Verify welcome message
        String welcome = webDriver.findElement(By.id("welcome")).getText();
        Assert.assertEquals(welcome, "Welcome Admin");
    }

    /*
     *Testing Invalid user login
     */

    @Test
    public void testUserLoginWithEmptyUsernamePassword() {

        //Type username
        webDriver.findElement(By.id("txtUsername")).clear();

        //Type password and Call Submit Method
        webDriver.findElement(By.id("txtPassword")).clear();
        webDriver.findElement(By.id("txtPassword")).submit();

        //Verify message
        String message = webDriver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(message, "Username cannot be empty");
    }

    @Test
    public void testUserLoginWithEmptyPassword() {

        //Type username
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type password and Call Submit Method
        webDriver.findElement(By.id("txtPassword")).clear();
        webDriver.findElement(By.id("txtPassword")).submit();

        //Verify message
        String message = webDriver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(message, "Password cannot be empty");
    }

    @Test
    public void testUserLoginWithEmptyUsername() {

        //Type username
        webDriver.findElement(By.id("txtUsername")).clear();

        //Type password and Call Submit Method
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        webDriver.findElement(By.id("txtPassword")).submit();

        //Verify message
        String message = webDriver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(message, "Username cannot be empty");
    }

    @Test
    public void testUserLoginWithWrongUsername() {

        //Type username
        webDriver.findElement(By.id("txtUsername")).sendKeys("Customer");

        //Type password and Call Submit Method
        webDriver.findElement(By.id("txtPassword")).sendKeys("Ptl@#321");
        webDriver.findElement(By.id("txtPassword")).submit();

        //Verify message
        String message = webDriver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(message, "Invalid credentials");
    }

    @Test
    public void testUserLoginWithWrongPassword() {

        //Type username
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type password and Call Submit Method
        webDriver.findElement(By.id("txtPassword")).sendKeys("Password");
        webDriver.findElement(By.id("txtPassword")).submit();

        //Verify message
        String message = webDriver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(message, "Invalid credentials");
    }

    @Test
    public void testUserLoginWithCaseSensitivityPassword() {

        //Type username
        webDriver.findElement(By.id("txtUsername")).sendKeys("Admin");

        //Type password and Call Submit Method
        webDriver.findElement(By.id("txtPassword")).sendKeys("pTL@#321");
        webDriver.findElement(By.id("txtPassword")).submit();

        //Verify message
        String message = webDriver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(message, "Invalid credentials");
    }
}
