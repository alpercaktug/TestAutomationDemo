package org.alpercaktug.pages.mobile;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ProductDetailsPage extends BasePage {

    By goToSellerButton = By.xpath("//android.widget.TextView[@text='Satıcıya Git']");

    public ProductDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isGoToSellerButtonVisible() {
        return isElementVisible(goToSellerButton);
    }
}
