package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class SearchMenu extends BasePage{

    public SearchMenu(WebDriver driver) { super(driver);}

    private static final Logger logger = LogManager.getLogger(SearchMenu.class.getName());

    By categoryHotels = By.cssSelector("a[data-name='hotels']");
    By categoryFlights = By.cssSelector("a[data-name='flights']");
    By categoryBoats = By.cssSelector("a[data-name='boats']");
    By categoryRentals = By.cssSelector("a[data-name='rentals']");
    By categoryTours = By.cssSelector("a[data-name='tours']");
    By categoryCars = By.cssSelector("a[data-name='cars']");
    By categoryVisa = By.cssSelector("a[data-name='visa']");

    public void clickFlights(){
        findElement(categoryFlights).click();
    }



}// end class
