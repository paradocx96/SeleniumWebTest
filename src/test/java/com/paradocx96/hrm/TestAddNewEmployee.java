package com.paradocx96.hrm;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestAddNewEmployee {

    private static final By TXT_USERNAME = By.id("txtUsername");
    private static final By TXT_PASSWORD = By.id("txtPassword");
    private static final By BTN_LOGIN = By.id("btnLogin");
    private static final By MENU_PIM= By.id("menu_pim_viewPimModule");
    private static final By MENU_ADD_EMPLOYEE= By.id("menu_pim_addEmployee");
    private static final By TXT_FIRSTNAME= By.id("firstName");
    private static final By TXT_LASTNAME= By.id("lastName");
    private static final By TXT_EMP_ID= By.id("employeeId");
    private static final By PROFILE_PHOTO = By.id("photofile");
    private static final By BTN_SAVE = By.id("btnSave");
    private static final By CHECK_LOGIN = By.id("chkLogin");
    private static final By LST_STATUS = By.id("status");
    private static final By TXT_LOGIN_USERNAME = By.id("user_name");
    private static final By TXT_LOGIN_PASSWORD = By.id("user_password");
    private static final By TXT_LOGIN_PASSWORD_CONFIRM = By.id("re_password");

    private WebDriver webDriver;
    private Faker faker;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    private WebDriver beforeMethod() {
        webDriver = new ChromeDriver();
        faker = new Faker();
        webDriver.get("http://hrm.pragmatictestlabs.com");
        Login();
        navigateToAddEmployeeWebPage();
        return webDriver;
    }

    @AfterMethod
    private void afterMethod() {
        //Close the browser
        webDriver.close();
    }

    private void Login() {
        //Type username
        webDriver.findElement(TXT_USERNAME).sendKeys("Admin");

        //Type password
        webDriver.findElement(TXT_PASSWORD).sendKeys("Ptl@#321");

        //Click the login button
        webDriver.findElement(BTN_LOGIN).click();
    }

    private void navigateToAddEmployeeWebPage() {
        //Click on PIM in main menu
        webDriver.findElement(MENU_PIM).click();

        //Click on Add Employee
        //webDriver.findElement(MENU_ADD_EMPLOYEE).click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(MENU_ADD_EMPLOYEE)).click();
    }

    /*
     *Testing Add New Employee
     */

    @Test
    public void testAddNewEmployeeWithMinimumInfo() {
        //Type First Name
        webDriver.findElement(TXT_FIRSTNAME).sendKeys("Navinda1");

        //Type Last Name
        webDriver.findElement(TXT_LASTNAME).sendKeys("Lankesh1");

        //Click on Save Button
        webDriver.findElement(BTN_SAVE).click();

        //Verify Employee Registration
        String FirstName = webDriver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value");
        Assert.assertEquals(FirstName, "Navinda1");

        String LastName = webDriver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value");
        Assert.assertEquals(LastName, "Lankesh1");
    }

    @Test
    public void testAddNewEmployeeWithProfilePicture() {
        //Type First Name
        webDriver.findElement(TXT_FIRSTNAME).sendKeys("Navinda2");

        //Type Last Name
        webDriver.findElement(TXT_LASTNAME).sendKeys("Lankesh2");

        //Select Profile Photo
        webDriver.findElement(PROFILE_PHOTO).sendKeys("C:\\Users\\PARADOCX\\Desktop\\IntelliJ IDEA\\data\\photo.jpg");

        //Click on Save Button
        webDriver.findElement(BTN_SAVE).click();

        //Verify Employee Registration
        String FirstName = webDriver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value");
        Assert.assertEquals(FirstName, "Navinda2");

        String LastName = webDriver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value");
        Assert.assertEquals(LastName, "Lankesh2");
    }

    @Test
    public void testAddNewEmployeeWithLoginInfo() throws InterruptedException {
        //Type First Name
        webDriver.findElement(TXT_FIRSTNAME).sendKeys("Navinda4");

        //Type Last Name
        webDriver.findElement(TXT_LASTNAME).sendKeys("Lankesh4");

        //Select Profile Photo
        webDriver.findElement(PROFILE_PHOTO).sendKeys("C:\\Users\\PARADOCX\\Desktop\\IntelliJ IDEA\\data\\photo.jpg");

        //Check on Create Login Details
        webDriver.findElement(CHECK_LOGIN).click();

        //Type Username
        webDriver.findElement(TXT_LOGIN_USERNAME).sendKeys("Navinda4");

        //Type Password
        //webDriver.findElement(TXT_LOGIN_PASSWORD).sendKeys("Ptl@#321");

        //Type Password with 0.5 Second delay
        String password = "Ptl@#321";
        for (Character character : password.toCharArray()) {
            webDriver.findElement(TXT_LOGIN_PASSWORD).sendKeys(character.toString());
            Thread.sleep(500);
        }

        //Type Confirm Password
        webDriver.findElement(TXT_LOGIN_PASSWORD_CONFIRM).sendKeys("Ptl@#321");

        //Select Status type
        Select status = new Select(webDriver.findElement(LST_STATUS));
        status.selectByValue("Disabled");

        //Click on Save Button
        webDriver.findElement(BTN_SAVE).click();

        //Verify Employee Registration
        String FirstName = webDriver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value");
        Assert.assertEquals(FirstName, "Navinda4");

        String LastName = webDriver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value");
        Assert.assertEquals(LastName, "Lankesh4");
    }


}
