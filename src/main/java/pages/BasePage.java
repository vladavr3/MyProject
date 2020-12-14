package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Random;
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


    protected void halfSecondWait() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected WebElement findElement(By locator){
        WebElement element = null;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            element = driver.findElement(locator);
            logger.info("PASSED - Locator " + element.toString() + " has been found");
        }catch (TimeoutException e){
            logger.info("FAILED - Element could not be found By locator [" +locator.toString()+ "] in specified time.");
            softAssert.fail();
            e.printStackTrace();
        }catch (NoSuchElementException e){
            logger.info("FAILED - Element could not be found By locator [" +locator.toString()+ "] in specified time.");
            softAssert.fail();
            e.printStackTrace();
        }catch (Exception e){
            logger.info("FAILED - Element could not be found By locator [" +locator.toString()+ "] in specified time.");
            softAssert.fail();
            e.printStackTrace();
        }
        return element;
    }

    protected WebElement findElementByList(By locator) {
        List<WebElement> element = driver.findElements(locator);
        try {
            if (!element.isEmpty()) {
                logger.info("PASSED - element [" + element.get(0).toString() + "] + has been found");
                return element.get(0);
            }
        } catch (NoSuchElementException e) {
            softAssert.fail("FAILED - NoSuchElementException [" + locator.toString() + "] in specified time.");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            softAssert.fail("FAILED - IndexOutOfBoundsException [" + locator.toString() + "] in specified time.");
            e.printStackTrace();
        }
        return null;
    }


    protected void clickWhenReady(By locator) {
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                Actions action = new Actions(driver);
                action.moveToElement(element)
                        .build()
                        .perform();
                element.click();
                logger.info("PASSED - Clicking [" + locator + "] was successful!");
            } catch (ElementClickInterceptedException ex) {
                //retry in case
                logger.warn("WARNING - Tried to CLICK [" + locator + "] but ElementClickInterceptedException happened -> RETRY");
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                Actions action = new Actions(driver);
                action.moveToElement(element)
                        .build()
                        .perform();
                element.click();
                ex.printStackTrace();
            } catch (StaleElementReferenceException ste) {
                //retry in case
                logger.warn("WARNING - Tried to CLICK [" + locator + "] but StaleElementReferenceException happened -> RETRY");
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                Actions action = new Actions(driver);
                action.moveToElement(element)
                        .build()
                        .perform();
                element.click();
                ste.printStackTrace();
            }catch (Exception e){
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
                logger.warn("WARNING - Tried to CLICK with JavascriptExecutor[" + locator + "] but " + e.getMessage() + " happened.");
                e.printStackTrace();
            }
        } else {
            logger.error("FAILED - Element could not be found By locator [" + locator.toString() + "] in specified time.");
            softAssert.fail("FAILED - Element could not be found By locator [" + locator.toString() + "] in specified time.");
        }
    }

    protected void typeIn(By locator, String inputText) {
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                element.clear();
                element.sendKeys(inputText);
                logger.info("PASSED - Text [" + inputText + "] HAS BEEN WRITTEN in element [" + locator + "]");
            } catch (Exception e) {
                logger.info("FAILED - Element [" + locator.toString() + "] is present but not clickable in given time");
                softAssert.fail("FAILED - Element [" + locator.toString() + "] is present but not clickable in given time");
                e.printStackTrace();
            }
        } else {
            logger.error("FAILED - Element could not be found By locator [" + locator.toString() + "] in specified time.");
            softAssert.fail("FAILED - Element could not be found By locator [" + locator.toString() + "] in specified time.");
        }
    }

    protected void elementContainsExactText(By locator, String expectedText) {
        WebElement element = findElement(locator);
        if (element != null) {
            wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
            if (element.getText().trim().equals(expectedText.trim())) {
                logger.info("PASSED - Text found in element [" + element.getText() + "] CONTAINS expected text [" + expectedText + "]");
            }else {
                logger.error("FAILED - Text found in element [" + element.getText() + "] DOES NOT CONTAIN expected value [" + expectedText + "]");
                softAssert.fail("FAILED - Text found in element [" + element.getText() + "] DOES NOT CONTAIN expected value [" + expectedText + "]");
            }
        } else {
            logger.error("FAILED - Element could not be found By locator [" + locator.toString() + "] in specified time.");
            softAssert.fail("FAILED - Element could not be found By locator [" + locator.toString() + "] in specified time.");
        }
    }

    protected boolean isElementPresent(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        if (!elements.isEmpty()) {
            logger.info("PASSED - element [" + elements.get(0).toString() + "] IS PRESENT on page");
            return true;
        }else {
            logger.warn("FAILED - element [" + locator.toString() + "] is NOT present on page.");
            softAssert.fail("FAILED - element [" + locator.toString() + "] is NOT present on page.");
            return false;
        }
    }

    protected void clickRandomElement(By locator) {
        Random random = new Random();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        List<WebElement> list = driver.findElements(locator);
        int numberOfElements = list.size();
        int randomElement = random.nextInt(numberOfElements);
        //wait.until(ExpectedConditions.elementToBeClickable(list.get(randomElement)));
        list.get(randomElement).click();
    }

    protected void clearText(WebElement field){
        field.sendKeys(" \b");
        halfSecondWait();
        field.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
    }

    protected void makeAction(Keys inputAction){
        //Actions action = new Actions(driver);
        driver.findElement(By.cssSelector("body")).sendKeys(inputAction);
        //action.sendKeys(inputAction).build().perform();
    }

    protected void scrollDown(int x_Pixels, int y_Pixels){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        try {
            js.executeScript("window.scrollBy("+x_Pixels+","+y_Pixels+")","");
            halfSecondWait();
        }catch (Exception e){
            js.executeScript("window.scroll("+x_Pixels+","+y_Pixels+");");
            halfSecondWait();
            e.printStackTrace();
        }

    }

    protected String getTextFromElement(By locator) {
        return findElement(locator).getText();
    }

    protected boolean isCheckboxChecked(WebElement element) {
        //return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checked", element);
        return element.isSelected() ? true : false;
    }

    protected void checkboxChecked(By locator){
        WebElement element = findElement(locator);
        if(element != null){
            if(isCheckboxChecked(element)){
                logger.info("PASSED - element ["+locator.toString()+"] is CHECKED as Expected");
            } else {
                logger.info("FAILED - element ["+locator.toString()+"] is UNCHECKED. Expected is to be CHECKED.");
                softAssert.fail("FAILED - element ["+locator.toString()+"] is UNCHECKED. Expected is to be CHECKED.");
            }
        } else{
            logger.info("FAILED - Element could not be found By locator [" +locator.toString()+ "] in specified time.");
            softAssert.fail("FAILED - Element could not be found By locator [" +locator.toString()+ "] in specified time.");
        }
    }


}//end class
