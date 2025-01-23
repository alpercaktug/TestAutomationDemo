package stepdefinitions;

import context.ScenarioContext;
import driver.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.web.CartPage;
import pages.web.HomePage;
import pages.web.ProductDetailPage;
import pages.web.ProductPage;

public class HepsiburadaSteps {

    private final ScenarioContext scenarioContext;

    WebDriver driver = DriverManager.getWebDriver();

    HomePage homePage = new HomePage(driver);
    ProductPage productPage = new ProductPage(driver);
    ProductDetailPage productDetailPage = new ProductDetailPage(driver);
    CartPage cartPage = new CartPage(driver);

    public HepsiburadaSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I visit {string}")
    public void iVisit(String url) throws InterruptedException {
        homePage.navigateTo(url);
    }

    @When("I navigate to {string} > {string} > {string}")
    public void iNavigateTo(String menu, String childMenu, String product) {
        homePage.hoverOverMenuByText(menu);
        homePage.hoverOverSubMenuByText(childMenu);
        homePage.clickSubMenuByText(product);
    }

    @And("I filter by {string} as {string}")
    public void iFilterByAsAndAs(String filterName, String filterText) throws InterruptedException {
        Thread.sleep(1000);
        productPage.clickFilterByText(filterName, filterText);
    }

    @And("I select the highest priced product from the search results")
    public void iSelectTheHighestPricedProductFromTheSearchResults() {
        productPage.clickMaxPriceElement();
        productPage.handleNewTab();

    }

    @And("I add to cart product on the product detail page")
    public void iAddToCartProductOnTheProductDetailPage() {
        scenarioContext.set("productTitle", productDetailPage.getProductTitle());
        scenarioContext.set("productPrice", productDetailPage.getProductPrice());
        System.out.println("(Product Detail Page) Product Title: " + scenarioContext.get("productTitle"));
        System.out.println("(Product Detail Page) Product Price: " + scenarioContext.get("productPrice"));
        productDetailPage.clickAddToCart();
    }

    @Then("I should see the product in my cart with the same price as the product detail page")
    public void iShouldSeeTheProductInMyCartWithTheSamePriceAsTheProductDetailPage(){
        productDetailPage.clickNavigateToCart();
        System.out.println("(Cart Page) Product Price: " + cartPage.getProductPriceText());
        System.out.println("(Cart Page) Product Title: " + cartPage.getProductNameText());
        System.out.println("(Cart Page) Total Price: " + cartPage.getTotalPriceLabel());
        Assert.assertEquals(scenarioContext.get("productPrice"), cartPage.getTotalPriceLabel());
        Assert.assertEquals(scenarioContext.get("productTitle"), cartPage.getProductNameText());
    }
}