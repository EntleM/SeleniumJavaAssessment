package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

    WebDriver driver;

    @FindBy(id = "overview-hero")
    WebElement verify_homePage;

    // This one works, so we keep it!
    @FindBy(xpath = "//*[@id=\"app-root\"]/nav/div[1]/div[3]/button/span[2]")
    WebElement loginButton;

    // Improved XPaths: Looking for the specific text inside the span
    @FindBy(xpath = "//span[text()='Learn']")
    WebElement learnMenu;

    @FindBy(xpath = "//span[text()='Learning Materials']")
    WebElement learningMaterialsItem;

    @FindBy(xpath = "//*[@id=\"tab-btn-web\"]/span[2]")
    WebElement webAutomationTab;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void verifyHomePageIsDisplayed() {
        verify_homePage.isDisplayed();
    }

    public void navigateToInventoryForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(learnMenu)).click();

        wait.until(ExpectedConditions.elementToBeClickable(learningMaterialsItem)).click();

        wait.until(ExpectedConditions.elementToBeClickable(webAutomationTab)).click();
    }
}