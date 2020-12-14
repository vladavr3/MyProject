package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

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

    public WebElement genericLocator(int waitForSeconds, String css, String xpath) {
        driver.manage().timeouts().implicitlyWait(waitForSeconds, TimeUnit.SECONDS);
        return  driver.findElement(new ByAll(By.cssSelector(css), By.xpath(xpath)));
    }



    protected WebElement findElement(By locator){
        WebElement element = null;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            element = driver.findElement(locator);
            logger.info("PASSED - Locator " + element.toString() + " has been found");
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
        return element;
    }






}//end class
