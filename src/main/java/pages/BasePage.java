package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public SoftAssert softAssert;


    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,15);
        softAssert = new SoftAssert();
    }

    private static final Logger logger = LogManager.getLogger(BasePage.class.getName());

    public void genericLocator(By...locator) {
        WebElement element;
        String locatorType = locator.toString();
        try {
            if (locatorType.contains("id")) {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator[0]));
                element = driver.findElement(locator[0]);
            } else if (locatorType.contains("name")) {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator[0]));
                element = driver.findElement(locator[0]);
            } else if (locatorType.contains("xpath")) {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator[0]));
                element = driver.findElement(locator[0]);
            } else if (locatorType.contains("css")) {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator[0]));
                element = driver.findElement(locator[0]);
            } else if (locatorType.contains("class")) {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator[0]));
                element = driver.findElement(locator[0]);
            } else if (locatorType.contains("tag")) {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator[0]));
                element = driver.findElement(locator[0]);
            } else if (locatorType.contains("link")) {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator[0]));
                element = driver.findElement(locator[0]);
            } else if (locatorType.contains("partiallink")) {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator[0]));
                element = driver.findElement(locator[0]);
            } else {
                logger.warn("Locator type not supported");
            }
        } catch (Exception e) {
            logger.warn("By type not found with: " + locatorType);
            e.printStackTrace();
        }
    }



    protected void findElement(By locator){
        WebElement element = null;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            element = driver.findElement(locator);
        }catch (TimeoutException e){
            logger.info("FAILED - Element could not be found By locator [" +locator.toString()+ "] in specified time.");
            e.printStackTrace();
        }catch (NoSuchElementException e){
            logger.info("FAILED - Element could not be found By locator [" +locator.toString()+ "] in specified time.");
            e.printStackTrace();
        }catch (Exception e){
            logger.info("FAILED - Element could not be found By locator [" +locator.toString()+ "] in specified time.");
            e.printStackTrace();
        }
    }






}//end class
