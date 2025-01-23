package org.alpercaktug.pages.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchResultsPage extends BasePage {
    private final By filterButton = By.id("com.akakce.akakce:id/filterArea");
    private final By subCategory = By.xpath("//android.widget.TextView[@text='Bilgisayar, Donanım']");
    private final By showProductsButton = By.id("com.akakce.akakce:id/applyFilterBtn");
    private final By sortButton = By.id("com.akakce.akakce:id/selectedSortText");
    private final By highestPriceOption = By.xpath("(//android.widget.ImageView[@resource-id='com.akakce.akakce:id/sort_icon'])[3]");
    private final By goToProduct = By.id("com.akakce.akakce:id/detailBtnLayout");

    public SearchResultsPage(AppiumDriver driver) {
        super(driver);
    }

    public void selectSubCategory() {
        click(filterButton);
        click(subCategory);
        click(showProductsButton);
    }

    public void applyHighestPriceFilter() {
        click(sortButton);
        click(highestPriceOption);
    }

    public void selectProductByIndex(int index) {
        Set<String> uniqueTexts = new HashSet<>();
        List<String> orderedTexts = new ArrayList<>();
        int ieterator = 1;

        try {
            while (uniqueTexts.size() < index) {
                String dynamicXPath = getDynamicXPath(ieterator);
                try {
                    WebElement element = driver.findElement(By.xpath(dynamicXPath));
                    String text = element.getText();

                    if (uniqueTexts.add(text)) {
                        orderedTexts.add(text);
                        System.out.println(ieterator + ". Collected text: " + text);
                    }
                    ieterator++;
                } catch (WebDriverException e) {
                    scrollDown();
                    ieterator = 1;
                }
            }
            clickLastCollectedElement(orderedTexts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickGoToProductButton() {
        scrollToElement("Ürüne Git");
        click(goToProduct);
    }

    private String getDynamicXPath(int index) {
        return String.format("(//android.widget.TextView[@resource-id='com.akakce.akakce:id/name'])[%d]", index);
    }

    private void scrollDown() {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;

        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.2);

        new AndroidTouchAction((PerformsTouchActions) driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    private void clickLastCollectedElement(List<String> orderedTexts) {
        if (orderedTexts.size() == 10) {
            String lastText = orderedTexts.get(orderedTexts.size() - 1);
            System.out.println("Clicking on the last collected text: " + lastText);

            String finalXPath = String.format(
                    "//android.widget.TextView[@resource-id='com.akakce.akakce:id/name' and @text='%s']",
                    lastText
            );
            WebElement lastElement = driver.findElement(By.xpath(finalXPath));
            lastElement.click();
        }
    }
}