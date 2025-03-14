package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavascriptExecutorAndCSSSelector {

    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ebay.com/");
    }

    @Test
    public void test1(){
        driver.findElement(By.cssSelector("#gh-ac")).sendKeys("philips 27M1F5800/00");
        driver.findElement(By.cssSelector("#gh-search-btn")).click();//right-click on the element copy selector
        JavascriptExecutor js = (JavascriptExecutor) driver; // create JavascriptExecutor object
        js.executeScript("window.scrollBy(0,300)");// it will scroll with 300 pixels vertically
        boolean isItemExist = false;
        try {
            isItemExist = driver.findElement(By.xpath("//*[@id=\"srp-river-results\"]/div[1]/div/div[1]/h3")).isDisplayed();
            if(isItemExist){
                isItemExist = false;
            }
            Assert.assertTrue(isItemExist,"\n The item doesn't exist in ebay! \n");

        } catch (NoSuchElementException e) {}
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}

