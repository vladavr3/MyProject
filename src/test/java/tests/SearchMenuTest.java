package tests;

import core.Constants;
import base.BaseTest;
import base.WebDriverFactory;
import org.testng.annotations.BeforeMethod;

public class SearchMenuTest extends BaseTest {




    @BeforeMethod
    public void setup(){
        driver = WebDriverFactory.getInstance().getDriver();
        driver.get(Constants.URL);
    }








}//end class
