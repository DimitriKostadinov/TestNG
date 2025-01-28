package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

public class GeoLocation_Selenium4Feature {
    ChromeDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void SeleniumEmulateGeoLocation(){
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(Optional.of(42.6958), // Latitude
                                                       Optional.of(23.3327), // Longitude
                                                       Optional.of(1))); // Accuracy
        driver.get("https://my-location.org/");

        By location = By.xpath("//*[@id=\"address\"]");
        String currentLocation = driver.findElement(location).getText();
        Assert.assertEquals(currentLocation,"The Patriarchal Cathedral \"Saint Alexander Nevsky\", Sofia, Bulgaria",
                "\n The coordinates are not correct!");

    }

    @AfterMethod
    public void tearDown(){
        //driver.quit(); // close the opened Chrome tab from the test
    }

}
