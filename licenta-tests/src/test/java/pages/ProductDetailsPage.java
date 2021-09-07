package pages;

import org.openqa.selenium.By;
import tests.BaseTest;

public class ProductDetailsPage extends BaseTest {

    private By sizeSelect = By.className("nice-select");
    private String sizeSelector = "//li[contains(@class,'option') and text()='%s']";
    private By addToCartButton = By.className("btn_add_to_cart");
    private By shoeName = By.id("shoe_name");
    private By shoeDescription = By.className("prod_info");
    private By price = By.className("new_price");
    private static ProductDetailsPage instance = null;

    public static ProductDetailsPage getInstance() {
        if (instance == null) {
            instance = new ProductDetailsPage();
        }
        return instance;
    }

    public boolean isSizeSelectDisplayed(){
        return driver.isDisplayed(sizeSelect);
    }

    public void clickSizeSelect(){
        driver.clickElementUsingActions(sizeSelect);
    }

    public boolean checkSizeIsInList(String sizeName){
        return driver.isPresent(By.xpath(String.format(sizeSelector,sizeName)));
    }

    public boolean isAddToCartDisplayed(){
        return driver.isDisplayed(addToCartButton);
    }

    public boolean isShoeDescriptionPresent(){
        return driver.isDisplayed(shoeDescription);
    }


    public String getShoeName(){
        return driver.getElementText(shoeName);
    }

    public String getShoePrice(){
        return driver.getElementText(price);
    }
}
