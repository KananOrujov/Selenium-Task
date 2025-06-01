package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    private WebDriver driver;

    public WebDriver setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Modify browser options here:
        options.addArguments("--disable-notifications");        // Disable browser notifications
        options.addArguments("--disable-infobars");              // Disable info bars
        options.addArguments("--start-maximized");               // Start maximized (optional, already done by manage().window())
        options.addArguments("--incognito");                      // Open in incognito mode
        // options.addArguments("--headless");                    // Uncomment to run Chrome in headless mode

        driver = new ChromeDriver(options);

        // Optionally, you can also set window size manually:
        // driver.manage().window().setSize(new Dimension(1920, 1080));

        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
