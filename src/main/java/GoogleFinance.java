import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleFinance {
    private WebDriver driver;

    public GoogleFinance(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
