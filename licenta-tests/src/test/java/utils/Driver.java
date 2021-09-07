package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;


import java.net.MalformedURLException;
import java.net.URL;

import java.util.concurrent.TimeUnit;


public class Driver {

    private static Driver instance = null;
    public RemoteWebDriver webDriver;

    public final static String gridUrl = String.format("http://localhost:5555/wd/hub");

    private Driver() {
        setNewDriver();
    }

    public static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }


    public void setNewDriver() {
        try {
            if (webDriver != null) {
                webDriver.close();
                webDriver.quit();
            }
        } catch (Exception e) {

        } finally {
            webDriver = null;
        }
        try {
            String browserType = BaseTest.browser;
            if (browserType == null) {
                return;
            }

            String browserVersion = "any";

            switch (browserType.toLowerCase()) {
                case "chrome":
                    /*
                     * Initializing Chrome remote driver
                     */

                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
                    chromeOptions.addArguments(String.format("window-size=%s,%s", BaseTest.width, BaseTest.height));
                    webDriver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
                    webDriver.manage().window().setSize(new Dimension(BaseTest.width, BaseTest.height));
                    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    webDriver.manage().deleteAllCookies();
                    break;

                case "firefox":
                    /*
                     * Initializing Firefox remote driver
                     */
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
                    firefoxOptions.addArguments(String.format("window-size=%s,%s", BaseTest.width, BaseTest.height));
                    webDriver = new RemoteWebDriver(new URL(gridUrl), firefoxOptions);
                    webDriver.manage().window().setSize(new Dimension(BaseTest.width, BaseTest.height));
                    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    webDriver.manage().deleteAllCookies();

                    break;

                case "edge":
                    /*
                     * Initializing Edge chromium remote driver
                     * Could not find any other way to make edge start using a grid like firefox and chrome
                     */
                    System.setProperty("webdriver.edge.driver", "/Users/mihai.barbul/Documents/workspace/msedgedriver");
                    webDriver = new EdgeDriver();
                    webDriver.manage().window().setSize(new Dimension(BaseTest.width, BaseTest.height));
                    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    webDriver.manage().deleteAllCookies();

                    break;
                default:

                    break;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
    }


    public void exit() {
        try {
            if (webDriver != null) {
                webDriver.close();
                webDriver.quit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            webDriver = null;
            instance = null;
        }
    }

    public void navigateTo(String URL) {
        webDriver.navigate().to(URL);
    }

    public boolean isDisplayed(By by){
        try{
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return webDriver.findElement(by).isDisplayed();
        }
        catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean isPresent(By by){
        try{
            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return webDriver.findElement(by) != null;
        }
        catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

    public void click(By by){
        scrollToElement(by);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        webDriver.findElement(by).click();
    }

    public String getElementText(By by){
       return webDriver.findElement(by).getText();
    }

    public void hoverOverElement(By by){
        scrollToElement(by);
        Actions hover = new Actions(webDriver);
        hover.moveToElement(webDriver.findElement(by)).build().perform();
    }

    public void clickElementUsingActions(By by){
        scrollToElement(by);
        Actions click = new Actions(webDriver);
        click.moveToElement(webDriver.findElement(by)).click().build().perform();
    }

    public void scrollToElement(By by){
        try{
        WebElement element = webDriver.findElement(by);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);}
        catch (Exception e){
        }
    }
}
