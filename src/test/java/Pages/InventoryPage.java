package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"inventory-title\"]")
    WebElement inventoryHeader;

    @FindBy(xpath = "//*[@id=\"deviceType\"]")
    WebElement deviceTypeLabel;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isHeaderDisplayed() {
        return inventoryHeader.isDisplayed();
    }

    public boolean isDeviceTypeLabelVisible() {
        return deviceTypeLabel.isDisplayed();
    }

    @FindBy(id = "deviceType")
    private WebElement deviceTypeDropdown;

    @FindBy(id = "brand")
    private WebElement brandDropdown;

    public void selectDeviceType(String typeName) {
        Select select = new Select(deviceTypeDropdown);
        select.selectByVisibleText(typeName);
    }

    public boolean isBrandDropdownEnabled() {
        return brandDropdown.isEnabled();
    }

    @FindBy(xpath = "//*[@id=\"device-preview\"]/div/img")
    private WebElement devicePreviewCard;

    @FindBy(xpath = "//*[@id=\"device-preview\"]/div/div/div[2]")
    private WebElement previewBrandName;

    public void selectBrand(String brandName) {
        Select select = new Select(brandDropdown);
        select.selectByVisibleText(brandName);
    }

    public boolean isPreviewVisible() {
        return devicePreviewCard.isDisplayed();
    }

    public String getPreviewBrandText() {
        return previewBrandName.getText();
    }
}