package tests;

import core.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.NewPage;
import base.BaseTest;
import base.WebDriverFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewPageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(NewPageTest.class.getName());

    NewPage cars;

    @BeforeMethod
    public void setup(){
        driver = WebDriverFactory.getInstance().getDriver();
        baseURL = Constants.URL;
        driver.get(baseURL);
        cars = new NewPage(driver);
    }



    @Test
    public void carSelectiontest(){
        cars.selectCar();
        cars.softAssert.assertAll();
    }




}//end class
