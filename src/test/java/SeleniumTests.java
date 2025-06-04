import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.*;

public class SeleniumTests {
    private WebDriver driver;

    @BeforeMethod
    void setup() {
        // Chrome driver path, replace with path to YOUR chrome web driver .exe
        String chromedriver_path = "C:\\WebDrivers\\chromedriver-win64\\chromedriver.exe";

        // Create new headless instance of chromedriver
        System.setProperty("webdriver.chrome.driver", chromedriver_path);
        ChromeOptions options = new ChromeOptions();

        // Remove to see the browser
        options.addArguments("--headless");

        // Open the Google Finance page
        driver = new ChromeDriver(options);
        driver.get("https://www.google.com/finance");
    }

    @Test
    void googleFinanceTitle() {
        // Assert that we're on the Google Finance page
        String expected_title = "Google Finance - Stock Market Prices, Real-time Quotes & Business News";
        GoogleFinance financePage = new GoogleFinance(driver);
        Assert.assertEquals(financePage.getTitle(), expected_title);
    }

    @Test
    void googleFinanceSymbols() {
        // Assert we got six stock symbols and no more
        GoogleFinance financePage = new GoogleFinance(driver);
        List<String> stockSymbols = financePage.getInterestedSymbols();
        System.out.println("List of Stock Symbols on Google Finance \"You may be interested in\":");
        for (int i = 0; i < stockSymbols.size(); i++) {
            System.out.print(stockSymbols.get(i) + " ");
        }
        System.out.println();
        Assert.assertEquals(stockSymbols.size(), 6);
    }

    @Test
    void unexpectedGoogleFinanceSymbols() {
        GoogleFinance financePage = new GoogleFinance(driver);
        List<String> mockExpectedSymbols = Arrays.asList("AAPL","INDEX");
        Assert.assertTrue(financePage.areExpectedSymbolsPresent(mockExpectedSymbols));
    }

    @AfterMethod
    void teardown() {
        driver.quit();
    }
}
