package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ElementsTest extends BaseTest{

    @Test
    public void elementsTest(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(productsListPage().isLogoDisplayed(), "The logo is not displayed");
        productsListPage().revealFiltersButton();

        softAssert.assertTrue(productsListPage.isFilterButtonDisplayed(), "The filter is not displayed");
        softAssert.assertTrue(productsListPage.isResetFilterButtonDisplayed(), "The reset filter is not displayed");
        softAssert.assertTrue(productsListPage.isFiltersInSection("Soccer "), "The filter Soccer is not displayed");
        softAssert.assertTrue(productsListPage.isFiltersInSection("Black "), "The filter Black is not displayed");
        softAssert.assertTrue(productsListPage.isFiltersInSection("Adibas "), "The filter Adibas is not displayed");
        softAssert.assertTrue(productsListPage.isFiltersInSection("$0 - $50 "), "The filter $0 - $50 is not displayed");

        productsListPage().closeFilters();
        productsListPage().revealSearchField();
        softAssert.assertTrue(productsListPage().checkSearchSection(),"The search field is not displayed");

        String product = productsListPage.getRandomProduct();
        softAssert.assertTrue(productsListPage.checkAddToCartButton(product),"The add to cart button is not present");
        softAssert.assertTrue(productsListPage.checkAddToCompareButton(product),"The add to compare button is not present");
        softAssert.assertTrue(productsListPage.checkAddToFavoriteButton(product),"The add to favorites button is not present");
        softAssert.assertAll();
    }
}
