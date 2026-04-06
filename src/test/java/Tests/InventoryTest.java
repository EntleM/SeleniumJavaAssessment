package Tests;

import Utils.ReadFromFile;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InventoryTest extends Base {

    @BeforeClass
    public void setupFlow() {
        PageFactory.initElements(driver, inventoryPage);

        homePage.clickLoginButton();
        loginPage.enterEmail(ReadFromFile.email);
        loginPage.enterPassword(ReadFromFile.password);
        loginPage.clickLoginButton();
    }

    @Test(priority = 1)
    public void testNavigationToForm() {
        homePage.navigateToInventoryForm();

        Assert.assertTrue(inventoryPage.isHeaderDisplayed(), "Header missing!");
        System.out.println("Flow: Navigated to Form.");
    }

    @Test(priority = 2, dependsOnMethods = "testNavigationToForm")
    public void testSelectDevice() throws InterruptedException {
        inventoryPage.selectDeviceType("Phone");
        Thread.sleep(1000);

        Assert.assertTrue(inventoryPage.isBrandDropdownEnabled(), "Brand dropdown locked!");
        System.out.println("Flow: Device selected.");
    }

    @Test(priority = 3, dependsOnMethods = "testSelectDevice")
    public void testSelectBrandAndPreview() throws InterruptedException {
        inventoryPage.selectBrand("Apple");
        Thread.sleep(3000);

        Assert.assertTrue(inventoryPage.isPreviewVisible(), "Preview missing!");
        Assert.assertEquals(inventoryPage.getPreviewBrandText(), "Apple");
        System.out.println("Flow: Brand selected and Preview verified.");
    }

    @Test(priority = 4, dependsOnMethods = "testSelectBrandAndPreview")
    public void testSelectStorage() throws InterruptedException {
        inventoryPage.selectStorage128();
        Thread.sleep(2000);

        String actualPrice = inventoryPage.getUnitPrice();
        Assert.assertTrue(actualPrice.contains("R480.00"),
                "Expected price to be R480.00 but found: " + actualPrice);
        System.out.println("128GB selected and Unit Price is correct!");
    }

    @Test(priority = 5, dependsOnMethods = "testSelectStorage")
    public void testColorSelection() throws InterruptedException {
        inventoryPage.selectColor("Blue");
        Thread.sleep(2000);

        String actualColor = inventoryPage.getPreviewColorText();

        Assert.assertTrue(actualColor.contains("blue"),
                "Preview color text did not update to Blue! Found: " + actualColor);
        System.out.println("Color 'Blue' selected and preview updated.");
    }

    @Test(priority = 6, dependsOnMethods = "testColorSelection")
    public void testQuantityAndSubtotal() throws InterruptedException {
        inventoryPage.enterQuantity("2");
        Thread.sleep(2000);

        String actualSubtotal = inventoryPage.getSubtotalText();
        String expectedSubtotal = "R960.00";
        Assert.assertTrue(actualSubtotal.contains(expectedSubtotal),
                "Subtotal did not update to R960.00! Found: " + actualSubtotal);
        System.out.println("Quantity '2' entered and Subtotal is correct.");
    }
}