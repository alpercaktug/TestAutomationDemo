package org.alpercaktug.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BasePage{

    By addToCartButton = By.xpath("//button[@data-test-id='addToCart']");
    By navigateToCartButton = By.xpath("//button[text()='Sepete git']");
    By productTitle = By.xpath("//h1[@data-test-id='title']");
    By productPrice = By.xpath("//div[@data-test-id='default-price']//span");

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
