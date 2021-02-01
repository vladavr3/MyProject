package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
public class SearchMenu extends BasePage{

    public SearchMenu(WebDriver driver) { super(driver);}


    private static final Logger logger = LogManager.getLogger(SearchMenu.class.getName());

    By categoryHotels = By.cssSelector("a[data-name='hotels''"); //false locator
    By getCategoryHotels2 = By.cssSelector("text-center hotels active");
    By getCategoryHotels3 = By.xpath("//a[@class='text-center hotels active']");

    By categoryFlights = By.cssSelector("a[data-name='flights']");
    By categoryBoats = By.cssSelector("a[data-name='boats']");
    By categoryRentals = By.cssSelector("a[data-name='rentals']");
    By categoryTours = By.cssSelector("a[data-name='tours']");
    By categoryCars = By.cssSelector("a[data-name='cars']");
    By categoryVisa = By.cssSelector("a[data-name='visa']");

    By destinationField = By.cssSelector(".select2-container.form-control.hotelsearch.locationlistHotels");
    By inputDestination = By.xpath("//div[@id='select2-drop-mask']/following-sibling::div/div/input");
    By selectCheckIn = By.cssSelector("input[id='checkin']");
    By selectCheckOut = By.cssSelector("input[id='checkout']");
    By dropdownResult = By.cssSelector(".select2-result-label");

    public SearchMenu clickFlights(){
        findElement(categoryFlights).click();
        return this;
    }

    public SearchMenu clickBoats(){
        findElement(categoryBoats).click();
        return this;
    }

    public SearchMenu clickRentals(){
        findElement(categoryRentals).click();
        return this;
    }

    public SearchMenu clickHotels(){
        //findElement(categoryHotels).click();
        genericLocator(categoryHotels, getCategoryHotels2, getCategoryHotels3).click();
        return this;
    }

    public SearchMenu selectDestination(){
        clickWhenReady(destinationField);
        typeIn(inputDestination,"ist");
        clickRandomElement(dropdownResult);
        return this;
    }

    public SearchMenu checkIn(){
        clickWhenReady(selectCheckIn);
        return this;
    }

    public SearchMenu checkOut(){
        return this;
    }






}// end class
