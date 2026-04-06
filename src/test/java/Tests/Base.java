package Tests;

import Pages.HomePage;
import Pages.InventoryPage;
import Pages.LoginPage;
import Utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class Base {

    protected WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;

    @BeforeClass
    public void setUp() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        driver = BrowserFactory.startBrowser("chrome","https://ndosisimplifiedautomation.vercel.app/",headless);

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

        PageFactory.initElements(driver, homePage);
        PageFactory.initElements(driver, loginPage);

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


//public class Base {
//
//    BrowserFactory browserFactory = new BrowserFactory();
//
//    final WebDriver driver = browserFactory.startBrowser("chrome","https://ndosisimplifiedautomation.vercel.app/");
//
//    HomePage homePage = PageFactory.initElements(driver, HomePage.class);
//    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
//    TakeScreenshots takeScreenshots = new TakeScreenshots();
//}
