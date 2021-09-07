package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ShoppingExperienceTest extends BaseTest {

    @Test
    public void shoppingExperienceTest(){
        SoftAssert softAssert = new SoftAssert();
        productsListPage().revealFiltersButton();

        softAssert.assertTrue(productsListPage.isFilterButtonDisplayed(), "The filter is not displayed");
        softAssert.assertTrue(productsListPage.isResetFilterButtonDisplayed(), "The reset filter is not displayed");
        softAssert.assertTrue(productsListPage.isFiltersInSection("Soccer "), "The filter Soccer is not displayed");
        softAssert.assertTrue(productsListPage.isFiltersInSection("Black "), "The filter Black is not displayed");
        softAssert.assertTrue(productsListPage.isFiltersInSection("Adibas "), "The filter Adibas is not displayed");
        softAssert.assertTrue(productsListPage.isFiltersInSection("$0 - $50 "), "The filter $0 - $50 is not displayed");

        productsListPage().selectFilterNamed("Black ");
        String expected = productsListPage().getExpectedFilteredItemsNumber("Black ");
        productsListPage().clickFilterButton();
        softAssert.assertEquals(Integer.valueOf(productsListPage().getAllProducts().size()), Integer.valueOf(expected),"Found items number is not the same as the expected one");

        String product = productsListPage.getRandomProduct();
        softAssert.assertTrue(productsListPage.checkAddToCartButton(product),"The add to cart button is not present");
        softAssert.assertTrue(productsListPage.checkAddToCompareButton(product),"The add to compare button is not present");
        softAssert.assertTrue(productsListPage.checkAddToFavoriteButton(product),"The add to favorites button is not present");
        softAssert.assertAll();
    }
}
