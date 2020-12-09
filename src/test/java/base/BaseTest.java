package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

public class BaseTest {

    public WebDriver driver;
    protected String baseURL;

//    @BeforeMethod(alwaysRun = true)
//    public void setup(){
//        baseURL = Constants.URL;
//        driver.get(baseURL);
//    }

    @AfterMethod
    public void tearDown(){
        WebDriverFactory.getInstance().quitDriver();
    }


}//end class
