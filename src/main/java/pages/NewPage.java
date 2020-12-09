package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class NewPage extends BasePage{


    public NewPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LogManager.getLogger(NewPage.class.getName());
    Select select;

    By selectcar = By.id("carselect");

    public NewPage selectCar(){
        WebElement cars = driver.findElement(selectcar);
        select = new Select(cars);
        wait.until(ExpectedConditions.elementToBeClickable(selectcar));
        cars.click();
        select.selectByVisibleText("Honda");
        String selectedOption = select.getFirstSelectedOption().getText();
        if (selectedOption.equals("Honda")){
            logger.info("PASS");
        }else {
            logger.warn("FAILED");
            softAssert.fail("FAIL");
        }
        return this;
    }




}//end class
