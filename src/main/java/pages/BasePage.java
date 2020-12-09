package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public SoftAssert softAssert;


    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,15);
        softAssert = new SoftAssert();
    }



}//end class
