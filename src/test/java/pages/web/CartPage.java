package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.Page;

public class CartPage extends WebBasePage {

    private final By productPriceLabel = By.xpath("//div[@class='product_price_uXU6Q']");
    private final By productName = By.xpath("//div[@class='product_name_2Klj3']");
    private final By totalPriceLabel = By.xpath("//span[@class='total_price_3V-CM']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductPriceText(){
        return getText(productPriceLabel);
    }

    public String getProductNameText(){
        return getText(productName);
    }

    public String getTotalPriceLabel(){
        return getText(totalPriceLabel).trim().replaceAll("\\s+", " ");
    }
}
