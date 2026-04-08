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

    @Test(priority = 7, dependsOnMethods = "testQuantityAndSubtotal")
    public void testAddressEntry() throws InterruptedException {
        String expectedAddress = "123 Test Street";
        inventoryPage.enterAddress(expectedAddress);
        Thread.sleep(500);

        String actualAddress = inventoryPage.getEnteredAddress();
        Assert.assertEquals(actualAddress, expectedAddress,
                "Address field was not filled correctly!");
        System.out.println("Address field contains: " + actualAddress);
    }

    @Test (priority = 8, dependsOnMethods = "testAddressEntry")
    public void testClickNext() throws InterruptedException {
        inventoryPage.clickNext();
        Thread.sleep(2000);

        Assert.assertTrue(inventoryPage.isOrderPreviewDisplayed(),
                "Order preview section (Review) was not displayed after clicking Next!");
        System.out.println("Successfully navigated to Order Preview screen.");
    }

    @Test(priority = 9, dependsOnMethods = "testClickNext")
    public void testExpressShipping() throws InterruptedException {
        inventoryPage.selectExpressShipping();

        Thread.sleep(1500);

        String actualShipping = inventoryPage.getShippingAmount();
        Assert.assertTrue(actualShipping.contains("R25.00"),
                "Shipping line item did not show R25.00! Found: " + actualShipping);
        String actualTotal = inventoryPage.getTotalAmount();
        Assert.assertTrue(actualTotal.contains("R985.00"),
                "Total did not update to R985.00! Found: " + actualTotal);
        System.out.println("Shipping (R25.00) displayed and Total updated to " + actualTotal);
    }

    @Test(priority = 10, dependsOnMethods = "testExpressShipping")
    public void testOneYearWarranty() throws InterruptedException {
        inventoryPage.selectOneYearWarranty();
        Thread.sleep(1500);

        String actualWarrantyFee = inventoryPage.getWarrantyAmount();
        Assert.assertTrue(actualWarrantyFee.contains("R49.00"),
                "Warranty line item did not show R49.00! Found: " + actualWarrantyFee);
        String actualTotal = inventoryPage.getTotalAmount();
        Assert.assertTrue(actualTotal.contains("R1034.00"),
                "Total did not update to R1034.00 Found: " + actualTotal);
        System.out.println("1 Year Warranty (+R49.00) added. Total: " + actualTotal);
    }

    @Test(priority = 11, dependsOnMethods = "testOneYearWarranty")
    public void testApplyDiscount() throws InterruptedException {
        inventoryPage.applyDiscountCode("SAVE10");
        Thread.sleep(2000);

        String actualDiscount = inventoryPage.getDiscountAmount();
        Assert.assertTrue(actualDiscount.contains("103.40"),
                "Discount line item did not show -R103.40! Found: " + actualDiscount);
        String actualTotal = inventoryPage.getTotalAmount();
        Assert.assertTrue(actualTotal.contains("R930.60"),
                "Total did not update to R930.60! Found: " + actualTotal);
        System.out.println("Discount applied. Final Total is R930.60.");
    }

    @Test(priority = 12, dependsOnMethods = "testApplyDiscount")
    public void testConfirmPurchase() throws InterruptedException {
        inventoryPage.clickConfirmPurchase();
        Thread.sleep(1000);

        Assert.assertTrue(inventoryPage.isSuccessToastDisplayed(),
                "Success pop-up did not appear!");
        String toastText = inventoryPage.getToastMessageText();
        Assert.assertTrue(toastText.contains("Order Details"),
                "Pop-up appeared but 'Order Details' section is missing!");
        System.out.println("Order confirmed and success toast is visible.");
    }

    @Test(priority = 13, dependsOnMethods = "testConfirmPurchase")
    public void testViewInvoiceHistory() throws InterruptedException {
        inventoryPage.clickViewInvoice();
        Thread.sleep(1500);

        Assert.assertTrue(inventoryPage.isInvoiceHistoryPanelDisplayed(),
                "Invoice History panel did not appear after clicking View Invoice!");
        System.out.println("Invoice History panel is visible.");
    }

    @Test(priority = 14, dependsOnMethods = "testViewInvoiceHistory")
    public void testVerifyFinalInvoice() throws InterruptedException {
        String mainWindow = driver.getWindowHandle();
        inventoryPage.clickViewInHistory();
        Thread.sleep(3000);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        Assert.assertTrue(inventoryPage.isInvoiceDocumentDisplayed(),
                "The final invoice document did not display in the new tab!");
        System.out.println("Invoice opened successfully in a new tab.");
    }
}