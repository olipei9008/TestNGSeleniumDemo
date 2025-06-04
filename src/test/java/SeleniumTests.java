import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumTests {
    private WebDriver driver;

    @BeforeMethod
    void setup() {
        String chromedriver_path = "C:\\WebDrivers\\chromedriver-win64\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromedriver_path);
        driver = new ChromeDriver();
        driver.get("https://www.google.com/finance");
    }

    @Test
    void googleFinanceTitle() {
        String expected_title = "Google Finance - Stock Market Prices, Real-time Quotes & Business News";
        GoogleFinance financepage = new GoogleFinance(driver);
        Assert.assertEquals(financepage.getTitle(), expected_title);
    }

    @AfterMethod
    void teardown() {
        driver.quit();
    }
}
