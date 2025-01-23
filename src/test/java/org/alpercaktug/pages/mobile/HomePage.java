package org.alpercaktug.pages.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    By loginPopupCloseButton = By.id("com.akakce.akakce:id/continueWithoutRegister");
    By searchBar = By.id("com.akakce.akakce:id/searchTextView");
    By searchBarTextField = By.xpath("(//android.widget.EditText[@resource-id='com.akakce.akakce:id/searchTextView'])[2]");
    By allowNotificationButton = By.id("com.android.permissioncontroller:id/permission_allow_button");

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public void closeNotificationButtonIfPresent() {
        if (isElementVisible(allowNotificationButton)) {
            click(allowNotificationButton);
        }
    }

    public void closeLoginPopupIfPresent() {
        if (isElementVisible(loginPopupCloseButton)) {
            click(loginPopupCloseButton);
        }
    }

    public void searchForItem(String item) {
        click(searchBar);
        enterText(searchBarTextField, item);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }
}
