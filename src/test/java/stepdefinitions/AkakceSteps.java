package stepdefinitions;

import driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.mobile.HomePage;
import pages.mobile.ProductDetailsPage;
import pages.mobile.SearchResultsPage;

public class AkakceSteps {

    AppiumDriver appiumDriver;

    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ProductDetailsPage productDetailsPage;

    public AkakceSteps() {
        this.appiumDriver = DriverManager.getAppiumDriver();
        this.homePage = new HomePage(appiumDriver);
        this.searchResultsPage = new SearchResultsPage(appiumDriver);
        this.productDetailsPage = new ProductDetailsPage(appiumDriver);
    }

    @Given("I open the Akakçe mobile app and continue without signing in")
    public void iOpenTheAkakçeMobileAppAndContinueWithoutSigningIn() {
        System.out.println("Navigate to Akakce via driver instance");
        homePage.closeNotificationButtonIfPresent();
        homePage.closeLoginPopupIfPresent();
    }

    @When("I search for {string} using the search bar")
    public void iSearchForUsingTheSearchBar(String item) {
        homePage.searchForItem(item);
    }

    @And("I filter the results to show {string} products")
    public void iFilterTheResultsToShowProducts(String subCategory) {
        searchResultsPage.selectSubCategory();
    }

    @And("I sort the results by {string}")
    public void iSortTheResultsBy(String sortFilter) {
        searchResultsPage.applyHighestPriceFilter();
    }

    @And("I tap on the {int}th product in the results")
    public void iTapOnTheThProductInTheResults(int index) {
        searchResultsPage.selectProductByIndex(index);
    }

    @And("I open the product's details page")
    public void iOpenTheProductSDetailsPage() {
        searchResultsPage.clickGoToProductButton();
    }

    @Then("I should see the Go to Seller button")
    public void iShouldSeeTheGoToSellerButton() {
        Assertions.assertTrue(productDetailsPage.isGoToSellerButtonVisible());
    }
}
