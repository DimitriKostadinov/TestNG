package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class ParallelDataProvider_and_Iframe_element_test {

    //ThreadLocal<WebDriver> driver = new ThreadLocal<>(); -
    // Създава се ThreadLocal променлива, която ще съхранява WebDriver инстанцията за всяка нишка.

    //driver.set(webDriver);: Задава се WebDriver инстанцията за текущата нишка.
    //driver.get();: Извлича WebDriver инстанцията за текущата нишка.
    //driver.remove();: Премахва WebDriver инстанцията от текущата нишка, за да предотврати изтичане на памет.

    // ThreadLocal за WebDriver инстанциите
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_trigger_button_enter");
        driver.set(webDriver);
    }

    @DataProvider(parallel = true) // will execute all tests at the same time by default is false
    public String[] CarBrands(){
        String[] data = new String[3];
        data[0] = "Rolls Royce";
        data[1]= "Renault";
        data[2]= "Nissan";

        return data;
    }

    @Test(dataProvider = "CarBrands")
    public void testParallelDP(String carBrand){
        WebDriver webDriver = driver.get();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        if(webDriver.findElements(By.className("sn-inner")).size() != 0) { // Check if cookie modal exist
            WebElement cookies = webDriver.findElement(By.id("accept-choices")); // Accept cookies btn.
            new Actions(webDriver).click(cookies).perform();
        }

        webDriver.switchTo().frame("iframeResult"); //Switch to iframe by name or ID for example use id
        WebElement element = webDriver.findElement(By.id("myInput"));
        element.clear(); // clear the value in the element
        element.sendKeys(carBrand);

        Actions actions = new Actions(webDriver);
        actions.sendKeys(element, Keys.ENTER).perform(); // Simulate press enter key on keyboard

        String alertText = webDriver.switchTo().alert().getText(); // Get the text from a simple alert.
        Assert.assertEquals(alertText, "Hello World!",
                "The alert message is not Hello World!");
        webDriver.switchTo().alert().accept(); // clicks the 'ok' button on the alert
    }

    @AfterMethod
    public void tearDown() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            try {
                webDriver.quit();
            } catch (Exception e) {
                System.out.println("Error while quitting the driver: " + e.getMessage());
            } finally {
                driver.remove();
            }
        }
    }

}
