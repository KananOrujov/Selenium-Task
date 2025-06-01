package pages;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isResultsLoaded() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img.pfp-fallback")));
            return true;
        } catch (TimeoutException e) {
            System.out.println("DEBUG: Search results not found. Dumping HTML:");
            System.out.println(driver.getPageSource());
            return false;
        }
    }

}
