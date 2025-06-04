# Overview

This is an example project demonstrating some basic use of Java, TestNG, and Selenium. 
It accesses the Google Finance page and performs a small test to check the stock 
symbols on the front page in the ***"You may be interested in"*** section

 # Project Structure

There are two important files in this project that perform the browser automation and testing:

- `src/main/java/GoogleFinance.java`- A class for the GoogleFinance page and contains a set of actions to run on the page.
  - `symsList` - A private variable that contains the 6 stock symbols included in the "You may be interested in section".  Created in the class constructor
  - `getTitle` - Returns the title of the page
  - `getInterestedSymbols` - Returns the symbols in the "You may be interested in section"
  - `areExpectedSymbolsPresent` - Given an input `List <String>` of expected stock symbols, performs 3 major actions
    - Prints out the stocks present on the page that aren't in the expected list.
    - Prints out the stock in the expected list that aren't on the page. 
    - Returns `true` if all the expected stocks were found, `false` if not.
- `src/test/java/SeleniumTests.java` - A class to run TestNG test cases
  - `setup` - Sets the webdriver options and starts the webdriver
  - `googleFinanceTitle` - Tests `getTitle` and asserts that the title obtained is correct and that the web driver is on the Google Finance page
  - `googleFinanceSymbols` - Tests `getInterestedSymbols` and asserts that the number of symbols obtained from the "interested" section is correct
  - `unexpectedSymbols` - Tests `areExpectedSymbolsPresent` and asserts that all the expected symbols were found.  Printoutputs can be seen in the stdout.