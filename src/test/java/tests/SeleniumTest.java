package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.DriverManager;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EditProfilePage;

import java.time.Duration;

public class SeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private DriverManager driverManager;
    private HomePage homePage;
    private LoginPage loginPage;
    private SearchPage searchPage;
    private EditProfilePage editProfilePage;

    @BeforeClass
    public void setup() throws Exception {
        driverManager = new DriverManager();
        driver = driverManager.setup();
        wait = new WebDriverWait(driver, Duration.ofSeconds(
                Integer.parseInt(ConfigReader.getProperty("default.timeout", "15"))));
        driver.get(ConfigReader.getProperty("url"));

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        editProfilePage = new EditProfilePage(driver); // ✅ add this line
    }

    @Test(priority = 1)
    public void testHomePageLoads() {
        String title = homePage.getPageTitle();
        System.out.println("Page title: " + title);
        Assert.assertTrue(title.toLowerCase().contains("spacehey"), "Title should contain 'SpaceHey'");
    }

    @Test(priority = 2)
    public void testNavigateToLoginPage() {
        homePage.clickLogin();
        wait.until(ExpectedConditions.urlContains("/login"));

        WebElement loginForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        Assert.assertTrue(loginForm.isDisplayed(), "Login form should be visible.");
    }

    @Test(priority = 3)
    public void testValidLogin() {
        homePage.clickLogin();
        wait.until(ExpectedConditions.urlContains("login"));

        loginPage.login("yisexev783@buides.com", "S!f&:&XjxP2ZR?J");

        if (!loginPage.isLoginSuccessful()) {
            System.out.println("DEBUG: Login failed. Dumping page HTML:");
            System.out.println(driver.getPageSource());
        }

        Assert.assertTrue(loginPage.isLoginSuccessful(), "Expected successful login.");
    }

    @Test(priority = 4)
    public void testLogout() {
        logoutIfLoggedIn();
        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/login']")));
        Assert.assertTrue(loginLink.isDisplayed(), "Login link should appear after logout.");
    }

    @Test(priority = 5)
    public void testInvalidLogin() {
        homePage.clickLogin();
        wait.until(ExpectedConditions.urlContains("login"));

        loginPage.login("Watashiwagroku@hotmail.com", "aaydajhdbah!@#$2635");
        Assert.assertFalse(loginPage.isLoginSuccessful(), "Login should fail with invalid credentials.");
    }

    @AfterClass
    public void tearDown() {
        driverManager.quitDriver();
    }

    private void logoutIfLoggedIn() {
        try {
            WebElement logoutBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("button.logout-btn")));
            logoutBtn.click();
            // Wait for redirect or login page to appear
            wait.until(ExpectedConditions.urlContains("/login"));
        } catch (Exception e) {
            System.out.println("DEBUG: Logout button not found or already logged out.");
        }
    }
    @Test(priority = 6)
    public void testSearchFunctionality() {
        homePage.clickHome(); // ✅ ensure we're on home
        homePage.search("aaaaaa");

        wait.until(ExpectedConditions.urlContains("/search?q="));
        Assert.assertTrue(searchPage.isResultsLoaded(), "Search results should be loaded.");
    }
    @Test(priority = 7)
    public void testEditProfileAboutMe() {
        homePage.clickLogin();
        loginPage.login("yisexev783@buides.com", "S!f&:&XjxP2ZR?J");
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login should succeed before editing profile.");

        editProfilePage.goToEditProfile()
                .updateAboutMe("This is a test bio written by Selenium.");
        editProfilePage.saveChanges();

        Assert.assertTrue(editProfilePage.isStillOnEditPage(), "Should stay on profile edit page after save.");
    }



}