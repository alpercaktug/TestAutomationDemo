package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.Page;


public class HomePage extends WebBasePage {

    private final By acceptCookie = By.xpath("//button[@id='onetrust-accept-btn-handler']");

    Actions actions = new Actions(driver);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void navigateTo(String url) {
        super.navigateTo(url);
        acceptCookie();
    }

    public void acceptCookie() {
        clickElement(acceptCookie);
    }

    public void hoverOverMenuByText(String menuText) {
        By dynamicMenu = By.xpath("//span[contains(@class, 'sf-MenuItems')]/span[text()='" + menuText + "']");
        WebElement element = waitForElementToBeVisible(dynamicMenu);
        actions.moveToElement(element).perform();
    }

    public void hoverOverSubMenuByText(String subMenuText) {
        By dynamicSubMenu = By.xpath("//ul[contains(@class, 'sf-ChildMenuItems')]//a[text()='" + subMenuText + "']");
        WebElement element = waitForElementToBeVisible(dynamicSubMenu);
        actions.moveToElement(element).perform();
    }

    public void clickSubMenuByText(String subMenuText){
        By dynamicSubMenu = By.xpath("//ul[contains(@class, 'sf-ChildMenuItems')]//a[text()='" + subMenuText + "']");
        clickElement(dynamicSubMenu);
    }
}
