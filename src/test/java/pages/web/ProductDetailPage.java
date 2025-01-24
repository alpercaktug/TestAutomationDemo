package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.Page;

public class ProductDetailPage extends WebBasePage {

    private final By addToCartButton = By.xpath("//button[@data-test-id='addToCart']");
    private final By navigateToCartButton = By.xpath("//button[text()='Sepete git']");
    private final By productTitle = By.xpath("//h1[@data-test-id='title']");
    private final By productPrice = By.xpath("//div[@data-test-id='default-price']//span");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToCart(){
        clickElement(addToCartButton);
    }

    public void clickNavigateToCart() {
        clickElement(navigateToCartButton);
    }

    public String getProductTitle(){
        return getText(productTitle);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }
}
