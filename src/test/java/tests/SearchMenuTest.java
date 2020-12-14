package tests;

import core.Constants;
import base.BaseTest;
import base.WebDriverFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SearchMenu;

public class SearchMenuTest extends BaseTest {

    SearchMenu searchMenu;


    @BeforeMethod
    public void setup(){
        driver = WebDriverFactory.getInstance().getDriver();
        driver.get(Constants.URL);
        searchMenu = new SearchMenu(driver);
    }

    @Test
    public void testMethod(){
        searchMenu.clickFlights()
                .clickRentals()
                .clickBoats()
                .softAssert.assertAll();
    }






}//end class
