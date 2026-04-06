package Utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {

    static WebDriver driver;


    public static WebDriver startBrowser(String browserChoice, String url, boolean headless) {

        if (browserChoice.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
            }else{
                System.out.println("No headless");
            }

            driver = new ChromeDriver(options);

        } else if (browserChoice.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserChoice.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browserChoice.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            driver = new InternetExplorerDriver();
        }

        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.get(url);
        return driver;
    }

//    @Test
//    public void lauchBroswer(){
//        startBrowser("EdGe","https://ndosisimplifiedautomation.vercel.app/");
//    }
}
