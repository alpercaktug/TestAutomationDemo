package pages.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import pages.Page;

public class HomePage extends MobileBasePage {

    private final By loginPopupCloseButton = By.id("com.akakce.akakce:id/continueWithoutRegister");
    private final By searchBar = By.id("com.akakce.akakce:id/searchTextView");
    private final By searchBarTextField = By.xpath("(//android.widget.EditText[@resource-id='com.akakce.akakce:id/searchTextView'])[2]");
    private final By allowNotificationButton = By.id("com.android.permissioncontroller:id/permission_allow_button");

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public void closeNotificationButtonIfPresent() {
        if (isElementVisible(allowNotificationButton)) {
            clickElement(allowNotificationButton);
        }
    }

    public void closeLoginPopupIfPresent() {
        if (isElementVisible(loginPopupCloseButton)) {
            clickElement(loginPopupCloseButton);
        }
    }

    public void searchForItem(String item) {
        clickElement(searchBar);
        enterText(searchBarTextField, item);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }
}
