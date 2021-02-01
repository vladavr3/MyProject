package base;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import static core.Constants.*;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    private static final WebDriverFactory instance = new WebDriverFactory();

    private WebDriverFactory(){

    }

    public static WebDriverFactory getInstance(){
        return instance;
    }

    private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<>();
    Dotenv dotenv;

    /***
     * Get driver instance based on the browser type
     * @return
     */
    public WebDriver getDriver(){
        WebDriver driver;
        dotenv = Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
        String browser = dotenv.get("BROWSER");
        if (threadedDriver.get() == null){
            try {
                if (browser.equalsIgnoreCase(CHROME)){
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    threadedDriver.set(driver);
                }else if (browser.equalsIgnoreCase(FIREFOX)){
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    threadedDriver.set(driver);
                }else if (browser.equalsIgnoreCase(IEXPLORER)){
                    WebDriverManager.iedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    driver = new InternetExplorerDriver();
                    threadedDriver.set(driver);
                }else if (browser.equalsIgnoreCase(HEADLESS)){
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("headless");
                    options.addArguments("window-size=1920,1200");
                    options.addArguments("disable-gpu");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    threadedDriver.set(driver);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            threadedDriver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            threadedDriver.get().manage().window().maximize();
        }
        return threadedDriver.get();
    }

    public void quitDriver() {
        threadedDriver.get().quit();
        threadedDriver.set(null);
    }



}//end class
