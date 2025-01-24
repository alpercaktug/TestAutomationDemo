package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.Page;

import java.util.List;

public class ProductPage extends WebBasePage {

    private final By priceElements = By.xpath("//div[@data-test-id='price-current-price']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickFilterByText(String filterName, String filterText) {
        By checkboxLocator = By.xpath("//label[input[@name='" + filterName + "']]//a[normalize-space(text())='" + filterText + "']/ancestor::label/input");
        WebElement checkbox = waitForElementToBeClickable(checkboxLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);

        Actions actions = new Actions(driver);
        actions.moveToElement(checkbox).click().perform();

        driver.navigate().refresh();
    }

    public List<WebElement> getPriceElements() {
        waitForElementToBeVisible(priceElements);
        return driver.findElements(priceElements);
    }

    public WebElement findMaxPriceElement() {
        // Locate all elements with the given XPath
        List<WebElement> priceElements = getPriceElements();

        // Initialize variables for maximum price and its corresponding element
        int maxPrice = Integer.MIN_VALUE;
        WebElement maxPriceElement = null;

        // Loop through all price elements
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText(); // Get the text
            // System.out.println(priceText);
            priceText = priceText.replaceAll("[^\\d]", ""); // Remove non-numeric characters

            if (!priceText.isEmpty()) {
                int price = Integer.parseInt(priceText); // Convert to integer
                if (price > maxPrice) {
                    maxPrice = price; // Update maximum price
                    maxPriceElement = priceElement; // Store the corresponding element
                }
            }
        }

        System.out.println("(Product Page) The maximum price is: " + maxPrice);
        return maxPriceElement;
    }

    public void clickMaxPriceElement(){
        WebElement element = findMaxPriceElement();
        element.click();
    }
}
