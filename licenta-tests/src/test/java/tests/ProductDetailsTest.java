package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.annotation.Priority;

public class ProductDetailsTest extends BaseTest{

    public SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void checkFilters(){
        productsListPage().revealFiltersButton();

        softAssert.assertTrue(productsListPage.isFilterButtonDisplayed(), "The filter is not displayed");
        softAssert.assertTrue(productsListPage.isResetFilterButtonDisplayed(), "The reset filter is not displayed");
        softAssert.assertTrue(productsListPage.isFiltersInSection("Soccer "), "The filter Soccer is not displayed");
        softAssert.assertTrue(productsListPage.isFiltersInSection("Black "), "The filter Black is not displayed");
        softAssert.assertTrue(productsListPage.isFiltersInSection("Adibas "), "The filter Adibas is not displayed");
        softAssert.assertTrue(productsListPage.isFiltersInSection("$0 - $50 "), "The filter $0 - $50 is not displayed");

    }

    @Test
    public void productDetailsTest(){

        productsListPage().selectFilterNamed("Black ");
        productsListPage().clickFilterButton();
        String expectedPrice = productsListPage().getProductPrice("Appli Air x Night");
        productsListPage().selectProduct("Appli Air x Night");

        softAssert.assertEquals(productDetailsPage().getShoeName(),"Appli Air x Night","Shoe name is different from the selected one");
        softAssert.assertEquals(productDetailsPage().getShoePrice(), expectedPrice,"Shoe price is different from the selected one");
        softAssert.assertTrue(productDetailsPage.isSizeSelectDisplayed(),"Shoe size select is not displayed");
        productDetailsPage.clickSizeSelect();
        softAssert.assertTrue(productDetailsPage.checkSizeIsInList("Small (S)"),"Shoe size Small is not present");
        softAssert.assertTrue(productDetailsPage.checkSizeIsInList("M"),"Shoe size M is not present");
        softAssert.assertTrue(productDetailsPage.checkSizeIsInList("L"),"Shoe size L is not present");
        softAssert.assertTrue(productDetailsPage.checkSizeIsInList("XL"),"Shoe size XL is not present");
        softAssert.assertTrue(productDetailsPage.isShoeDescriptionPresent(),"Shoe description is not displayed");
        softAssert.assertTrue(productDetailsPage.isAddToCartDisplayed(),"Add to cart button is not displayed");
        softAssert.assertAll();
    }
}
