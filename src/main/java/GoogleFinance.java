import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleFinance {
    private WebDriver driver;
    private List<String> symsList;

    public GoogleFinance(WebDriver driver) {
        this.driver = driver;
        this.symsList = new ArrayList<>();
        // Get the current "You may be interested in" stock symbols
        String sectionClass = "sbnBtf";
        String symbolClass = "COaKTb";
        WebElement section = driver.findElement(By.className(sectionClass));
        List<WebElement> elements = section.findElements(By.className(symbolClass));
        for (int i = 0; i < elements.size(); i++) {
            this.symsList.add(elements.get(i).getText());
        }
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<String> getInterestedSymbols() {
        return symsList;
    }

    /*
    This function does 3 things:
    - Returns true if all the expected symbols are on the current page, false if not
    - Prints the expected symbols NOT on the page
    - Prints the page symbols NOT in the expected list
     */
    public boolean areExpectedSymbolsPresent(List<String> expectedSyms) {

        int count = 0;

        // Keep track of what to print
        boolean[] printActual = new boolean[symsList.size()];
        Arrays.fill(printActual, true);
        boolean[] printExpected = new boolean[expectedSyms.size()];
        Arrays.fill(printExpected, true);

        //
        for (int i = 0; i < expectedSyms.size(); i++) {
            for (int j = 0; j < symsList.size(); j++) {
                if (expectedSyms.get(i).equals(symsList.get(j))) {
                   printActual[j] = false;
                   printExpected[i] = false;
                }
            }
        }

        // Print symbols that weren't expected
        System.out.println("List of symbols that weren't expected: ");
        for (int i = 0; i < symsList.size(); i++) {
            if (printActual[i]) {
                 System.out.print(symsList.get(i) + " ");
            }
        }
        System.out.println();

        // Print expected symbols that weren't found
        System.out.println("List of expected symbols that weren't found: ");
        for (int i = 0; i < expectedSyms.size(); i++) {
            if (printExpected[i]) {
                System.out.print(expectedSyms.get(i) + " ");
            }
            else {
                count++;
            }
        }
        System.out.println();

        System.out.println(count);
        return count == expectedSyms.size();
    }
}
