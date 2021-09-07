package pages;

import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import tests.BaseTest;

import java.util.List;

public class ProductsListPage extends BaseTest {

    private By searchField = By.xpath("//div[@class='custom-search-input']/input[contains(@id,'INPUTtext')]");
    private By mobileSearchField = By.xpath("//div[contains(@class,'search_mob')]/input[contains(@id,'INPUTtext')]");
    private By revealSearchFieldButton = By.className("btn_search_mob");
    private String filterOptionSelector = "//label[@class='container_check' and text()='%s']";
    private String expectedItemsNumber =  filterOptionSelector+"/small";
    private By revealFiltersButton = By.id("ti-filter");
    private By filterButton = By.id("filterBtn");
    private By resetFiltersButton = By.id("resetBtn");
    private By closeFiltersButton = By.cssSelector(".open_filters .ti-close");
    private String productSelector = "//a[contains(@href,'ProductDetails')]/../../a/h3[text()='%s']";
    private String addToFavoriteButton = productSelector + "/../../ul/li/a[(@title='Add to favorites' or @data-original-title='Add to favorites')]";
    private String addToCartButton = productSelector + "/../../ul/li/a[(@title='Add to cart' or @data-original-title='Add to cart')]";
    private String addToCompareButton = productSelector + "/../../ul/li/a[(@title='Add to compare' or @data-original-title='Add to compare')]";
    private String productPrice = productSelector + "/../..//span[@class='new_price']";
    private By pageLogo = By.id("logo");
    private By productListSelector = By.xpath("//a[contains(@href,'ProductDetails')]/../../a/h3");
    private static ProductsListPage instance = null;

    public static ProductsListPage getInstance() {
        if (instance == null) {
            instance = new ProductsListPage();
        }
        return instance;
    }

    public void revealSearchField(){
        if(driver.isDisplayed(revealSearchFieldButton))
            driver.click(revealSearchFieldButton);
    }

    public void revealFiltersButton(){
        if(driver.isDisplayed(revealFiltersButton))
            driver.click(revealFiltersButton);
    }

    public void clickFilterButton(){
        driver.click(filterButton);
    }

    public void closeFilters(){
        if(driver.isDisplayed(closeFiltersButton))
        driver.click(closeFiltersButton);
    }

    public void selectProduct(String productName){
        driver.click(By.xpath(String.format(productSelector, productName)));
    }

    public boolean isLogoDisplayed(){
        return driver.isDisplayed(pageLogo);
    }

    public void selectFilterNamed(String filterName){
        driver.click(By.xpath(String.format(filterOptionSelector, filterName)));
    }

    public String getExpectedFilteredItemsNumber(String filterName){
        return driver.getElementText(By.xpath(String.format(expectedItemsNumber, filterName)));
    }

    public boolean isFilterButtonDisplayed(){
        return driver.isDisplayed(filterButton);
    }

    public boolean isResetFilterButtonDisplayed(){
        return driver.isDisplayed(resetFiltersButton);
    }

    public boolean isFiltersInSection(String filterName){
        return driver.isDisplayed(By.xpath(String.format(filterOptionSelector,filterName)));
    }

    public boolean checkSearchSection(){
       return (driver.isDisplayed(searchField) || driver.isDisplayed(mobileSearchField));
    }

    public List<WebElement> getAllProducts(){
       List<WebElement> list =  driver.webDriver.findElements(productListSelector);
       return list;
    }

    public String getRandomProduct(){
        List<WebElement> list = getAllProducts();
        WebElement e = list.get(RandomUtils.nextInt(1, list.size()));
        return e.getText();
    }

    public boolean checkAddToFavoriteButton(String productName){
        driver.hoverOverElement(By.xpath(String.format(productPrice, productName)));
        return driver.isPresent(By.xpath(String.format(addToFavoriteButton, productName)));
    }

    public boolean checkAddToCartButton(String productName){
        driver.hoverOverElement(By.xpath(String.format(productPrice, productName)));
        return driver.isPresent(By.xpath(String.format(addToCartButton, productName)));
    }

    public boolean checkAddToCompareButton(String productName){
        driver.hoverOverElement(By.xpath(String.format(productPrice, productName)));
        return driver.isPresent(By.xpath(String.format(addToCompareButton, productName)));
    }

    public String getProductPrice(String productName){
        return driver.getElementText(By.xpath(String.format(productPrice,productName)));
    }
}
