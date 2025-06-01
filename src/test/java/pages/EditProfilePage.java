package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EditProfilePage extends BasePage {

    private By editProfileLink = By.cssSelector("a[href='/edit']");
    private By aboutMeTextarea = By.id("category_about_me");

    // Updated selector excludes the logout button by negating its class
    private By saveAllButton = By.cssSelector("button[type='submit'][name='submit']:not(.logout-btn)");

    public EditProfilePage(WebDriver driver) {
        super(driver);
    }

    // Clicks edit profile link and waits for the edit page to load
    public EditProfilePage goToEditProfile() {
        click(editProfileLink);
        wait.until(ExpectedConditions.urlContains("/edit"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(aboutMeTextarea));
        return this;
    }

    public void updateAboutMe(String text) {
        type(aboutMeTextarea, text);
    }

    public void saveChanges() {
        click(saveAllButton);
        // Wait for page to reload or confirmation message - adjust if needed
        wait.until(ExpectedConditions.urlContains("/edit"));
    }

    public boolean isStillOnEditPage() {
        return driver.getCurrentUrl().contains("/edit");
    }
}
