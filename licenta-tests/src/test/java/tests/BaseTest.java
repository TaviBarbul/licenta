package tests;


import org.testng.annotations.*;

import pages.ProductDetailsPage;
import pages.ProductsListPage;
import utils.Driver;



public class BaseTest {

    public static Driver driver = null;
    public ProductsListPage productsListPage = null;
    public ProductDetailsPage productDetailsPage = null;
    public static String browser;
    public static int width;
    public static int height;

    public ProductDetailsPage productDetailsPage() {
        if (productDetailsPage == null) {
            productDetailsPage = ProductDetailsPage.getInstance();
        }
        return productDetailsPage;
    }

    public ProductsListPage productsListPage() {
        if (productsListPage == null) {
            productsListPage = ProductsListPage.getInstance();
        }
        return productsListPage;
    }

    public Driver driver() {
        if (driver == null) {
            driver = Driver.getInstance();
        }
        return driver;
    }

    
    @BeforeMethod
    public void setup() {
        BaseTest.width = 1400;
        BaseTest.height = 1200;
        BaseTest.browser = "chrome";
        driver().navigateTo("http://demo.applitools.com/gridHackathonV1.html");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null)
            driver.exit();
        driver = null;
    }
}
