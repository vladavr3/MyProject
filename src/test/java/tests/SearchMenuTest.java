package tests;

import core.Constants;
import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.SearchMenu;

public class SearchMenuTest extends BaseTest {

    SearchMenu searchMenu;


    @BeforeMethod
    public void setup(){
        driver.get(Constants.URL);
        searchMenu = new SearchMenu(driver);
    }

    @Test
    public void testSelectHotels(){
        searchMenu.clickHotels()
                .selectDestination()
                .checkIn()
                .softAssert.assertAll();
    }






}//end class
