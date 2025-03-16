package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

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

    //Function: text()
    @Test
    public void testTextFunc(){
        driver.findElement(By.xpath("//a[text()='Electronics']")).click();
        String currURL = driver.getCurrentUrl();
        Assert.assertTrue(currURL.contains("https://www.ebay.com/b/Electronics/"),"\n Wrong redirect!\n");
    }

    //Function: start-with() and mouse hover on element
    @Test
    public void testStartWithFunc(){
        WebElement element = driver.findElement(By.xpath("//a[starts-with(@_sp,'p4375194.m1379.l3250')]"));

        // Instantiate the Action class
        Actions actions = new Actions(driver);
        // Perform the mouse hover action
        actions.moveToElement(element).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable
        (By.xpath("//*[@id=\"vl-flyout-nav\"]/ul/li[3]/div[2]/div[1]/nav[1]/ul/li[1]")));
        element2.click();

        String currURL = driver.getCurrentUrl();
        Assert.assertTrue(currURL.contains("https://www.ebay.com/b/Cell-Phones-Smart-"), "\n Wrong redirect!\n");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
