package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public abstract class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final int TIMEOUT = 30;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public Page(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void clickElement(By locator) {
        waitForElementToBeClickable(locator).click();
    }

    public String getText(By locator) {
        return waitForElementToBeVisible(locator).getText().trim();
    }

    public void enterText(By locator, String text) {
        waitForElementToBeVisible(locator).sendKeys(text);
    }

    public boolean isElementVisible(By locator) {
        return waitForElementToBeVisible(locator).isDisplayed();
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
