package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickLogin() {
        // It's an <a> link, not a button
        driver.findElement(By.linkText("LogIn")).click();
    }

    public void search(String query) {
        // ✅ Uses complex XPath to locate input and button
        driver.findElement(By.xpath("//input[@id='q' and @name='q']")).sendKeys(query);
        driver.findElement(By.xpath("//button[@type='submit' and text()='Search']")).click();
    }
    public void goHome() {
        driver.findElement(By.xpath("//a[text()='Home']")).click();
    }
    public void clickHome() {
        driver.findElement(By.linkText("Home")).click();
    }

}
