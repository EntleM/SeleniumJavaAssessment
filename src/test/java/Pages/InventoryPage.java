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

    @FindBy(id = "storage-128GB")
    private WebElement storage128RadioButton;

    @FindBy(xpath = "//*[@id=\"unit-price-value\"]")
    private WebElement unitPriceValue;

    public void selectStorage128() {
        storage128RadioButton.click();
    }

    public String getUnitPrice() {
        return unitPriceValue.getText();
    }

    @FindBy(id = "color")
    private WebElement colorDropdown;

    @FindBy(xpath = "//*[@id=\"device-preview\"]/div/div/div[3]")
    private WebElement previewColorText;

    public void selectColor(String colorName) {
        Select select = new Select(colorDropdown);
        select.selectByVisibleText(colorName);
    }

    public String getPreviewColorText() {
        return previewColorText.getText();
    }

    @FindBy(id = "quantity")
    private WebElement quantityInput;

    @FindBy(id = "subtotal-label")
    private WebElement subtotalAmount;

    public void enterQuantity(String qty) {
        quantityInput.clear();
        quantityInput.sendKeys(qty);
    }

    public String getSubtotalText() {
        return subtotalAmount.getText().trim();
    }

    @FindBy(id = "address")
    private WebElement addressField;

    @FindBy(id = "inventory-next-btn")
    private WebElement nextButton;

    @FindBy(id = "review-sections-container") // Use an element that appears after clicking Next
    private WebElement orderPreviewSection;

    public void enterAddress(String address) {
        addressField.clear();
        addressField.sendKeys(address);
    }

    public String getEnteredAddress() {
        return addressField.getAttribute("value");
    }

    public void clickNext() {
        nextButton.click();
    }

    public boolean isOrderPreviewDisplayed() {
        return orderPreviewSection.isDisplayed();
    }

    @FindBy(id = "shipping-express")
    private WebElement expressShippingBtn;

    @FindBy(id = "breakdown-shipping-value")
    private WebElement expressShippingAmount;

    @FindBy(id = "breakdown-total-value")
    private WebElement totalAmountLabel;

    public void selectExpressShipping() {
        expressShippingBtn.click();
    }

    public String getShippingAmount() {
        return expressShippingAmount.getText().trim();
    }

    public String getTotalAmount() {
        return totalAmountLabel.getText().trim();
    }
}