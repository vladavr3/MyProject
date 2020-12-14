package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setDriver(){
        driver = WebDriverFactory.getInstance().getDriver();
    }

//    @AfterMethod
//    public void tearDown(){
//        WebDriverFactory.getInstance().quitDriver();
//    }


}//end class
