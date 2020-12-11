package com.paradocx96.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoSelenium {

    public static void main(String[] args) {

        //Setup browser driver
        WebDriverManager.chromedriver().setup();

        //create an instance of the browser
        WebDriver driver = new ChromeDriver();

        //Access the webpage
        driver.get("http://hrm.pragmatictestlabs.com");

        //Close the browser
        driver.close();
    }
}
