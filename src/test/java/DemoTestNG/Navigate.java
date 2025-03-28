package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Navigate {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @Test
    public void testNavigateTo(){
        driver.navigate().to("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");
        driver.findElement(By.cssSelector("#select-demo")).sendKeys("Friday");
        String selectedDay = driver.findElement(By.xpath("//div[@class='px-10 pt-10 pb-5']//p")).getText();
        Assert.assertTrue(selectedDay.contains("Friday"),"The selected day is not Friday !");
    }

    @Test
    public void testNavigateRefresh(){
        driver.findElement(By.linkText("Checkbox Demo")).click();
        driver.findElement(By.id("isAgeSelected")).click();
        driver.navigate().refresh();
        WebElement element = driver.findElement(By.id("txtAge")); // find element is after refresh, because the session on the page is new, and
        // we get an error when try to find an element that is from the old session.
        element.isDisplayed();
        Assert.assertFalse(element.isDisplayed(),"The checkbox is clicked !");
    }

    @Test
    public void testForwardAndBack(){
        boolean backURL = false, forwardURL = false;
        driver.findElement(By.linkText("Status Codes")).click();
        driver.navigate().back();
        String mainURL = driver.getCurrentUrl();
        if (mainURL.equals("https://www.lambdatest.com/selenium-playground/")) {
            backURL = true;
        }

        driver.navigate().forward();
        String statusCodeURL = driver.getCurrentUrl();
        if (statusCodeURL.equals("https://www.lambdatest.com/selenium-playground/status-code")) {
            forwardURL = true;
        }

        Assert.assertTrue(forwardURL && backURL, "Something is wrong with the redirection !");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
