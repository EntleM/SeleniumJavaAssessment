package Tests;

import Utils.ReadFromFile;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InventoryTest extends Base {

    @BeforeMethod
    public void prepareInventoryTest() {
        homePage.verifyHomePageIsDisplayed();
        homePage.clickLoginButton();

        loginPage.enterEmail(ReadFromFile.email);
        loginPage.enterPassword(ReadFromFile.password);
        loginPage.clickLoginButton();
    }

    @Test
    public void testNavigationToInventoryForm() {
        homePage.navigateToInventoryForm();

        Assert.assertTrue(inventoryPage.isHeaderDisplayed(),
                "Navigation Failed: The 'Inventory Form' header was not found!");

        Assert.assertTrue(inventoryPage.isDeviceTypeLabelVisible(),
                "Navigation Failed: Form elements (Device Type) are missing from the page.");

        System.out.println("Success: Navigated to Inventory Form and verified page content.");
    }

    @Test
    public void testNavigationAndDeviceSelection() throws InterruptedException {
        homePage.navigateToInventoryForm();
        inventoryPage.selectDeviceType("Phone");
        Assert.assertTrue(inventoryPage.isBrandDropdownEnabled(),
                "ASSERTION FAILED: Brand dropdown did not enable after selecting Phone!");
        System.out.println("Selected Phone successfully.");
    }

    @Test
    public void testStep4BrandPreview() throws InterruptedException {
        homePage.navigateToInventoryForm();
        inventoryPage.selectDeviceType("Phone");
        inventoryPage.selectBrand("Apple");

        Thread.sleep(5000);
        Assert.assertTrue(inventoryPage.isPreviewVisible(), "Preview card not displayed!");
        Assert.assertEquals(inventoryPage.getPreviewBrandText(), "Apple", "Preview text mismatch!");

        System.out.println("Apple selected and Preview Card is visible.");
    }
}