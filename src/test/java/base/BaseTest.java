package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    public WebDriver driver;


    @AfterMethod
    public void tearDown(){
        WebDriverFactory.getInstance().quitDriver();
    }


}//end class
