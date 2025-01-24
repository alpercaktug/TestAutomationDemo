package pages.web;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Page;

import java.time.Duration;
import java.util.Set;

public class WebBasePage extends Page {

    public WebBasePage(WebDriver driver) {
        super(driver);
    }

    public void handleNewTab() {
        String originalTab = driver.getWindowHandle();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.getWindowHandles().size() > 1);

        Set<String> allTabs = driver.getWindowHandles();

        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                System.out.println("Switched to new tab: " + driver.getTitle());
                break;
            }
        }
    }
}
