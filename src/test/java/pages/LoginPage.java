package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By emailInput = By.name("email");
    private By passwordInput = By.name("password");

    // ✅ Complex XPath used here
    private By loginButton = By.xpath("//form//button[@type='submit' and @name='action' and contains(text(), 'Login')]");

    private By logoutButton = By.cssSelector("button.logout-btn");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        click(loginButton);
    }

    public boolean isLoginSuccessful() {
        return isVisible(logoutButton);
    }

    public void logoutIfLoggedIn() {
        if (isVisible(logoutButton)) {
            click(logoutButton);
        }
    }
}
