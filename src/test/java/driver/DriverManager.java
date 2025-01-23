package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverManager {

    private static WebDriver webDriver;
    private static AppiumDriver appiumDriver;

    // Singleton WebDriver instance
    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--start-maximized");
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver(options);
        }
        return webDriver;
    }

    // Singleton AppiumDriver instance for Android
    public static AppiumDriver getAppiumDriver() {
        if (appiumDriver == null) {
            try {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("platformName", "Android");
                caps.setCapability("deviceName", "Medium Phone API 35");
                caps.setCapability("udid", "emulator-5554");
                caps.setCapability("platformVersion", "15.0");
                caps.setCapability("noReset", false);
                caps.setCapability("newCommandTimeout", 600);
                caps.setCapability("automationName", "UiAutomator2");
                caps.setCapability("appPackage", "com.akakce.akakce");
                caps.setCapability("appActivity", "com.akakce.akakce.ui.splash.SplashActivity");

                URL appiumServerURL = new URL("http://127.0.0.1:4723/wd/hub");

                appiumDriver = new AndroidDriver(appiumServerURL, caps);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appiumDriver;
    }

    public static void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
