package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class XpathFunctions {

    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ebay.com/");
    }

    // info https://www.lambdatest.com/blog/complete-guide-for-using-xpath-in-selenium-with-examples/
    //Function: Contains()
    @Test
    public void testContainsFunc() {
        driver.findElement(By.xpath("//*[@id=\"s0-1-0-53-1-2-5-15-0[1]-10-" +
                "@match-media-0-@ebay-carousel-list\"]/li[3]/a/div")).click();
        driver.findElement(By.xpath("//a[contains(@class,'brw-eventbanner')]")).click();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("https://export.ebay.com/en/"),"\n Wrong redirect!\n");
    }

    //XPath using logical operators (OR & AND)
    @Test
    public void testLogicalOperators() {
        // or will find the element if one of the parameter exist
        // and will find the element if both of the parameter exist
        //IMPORTANT (or) and (and) conditions must be written only in lowercase!
        driver.findElement(By.xpath("//input[@name=\"_nkw\" or contains(@aria-label, 'Search for anything')]")).
                sendKeys("Rolls Royce");
        //driver.findElement(By.xpath("//input[@type=\"_nkw\" and contains(@aria-label, 'Search for anything')]")).click();
        WebElement selectElement = driver.findElement(By.id("gh-cat"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("Art");
        driver.findElement(By.id("gh-search-btn")).click();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
