package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTimeoutAndExpectedExceptions {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @Test(timeOut = 4300, expectedExceptions = {NoSuchElementException.class, TimeoutException.class})
    // The timeOut works with milliseconds so 2000 is 2 seconds
    // if the test didn't finish within the time-out of 2000(2 seconds) will fail with this exception:
    // org.testng.internal.thread.ThreadTimeoutException:
    // Method DemoTestNG.TestTimeoutAndExpectedExceptions.testTableDataSearch() didn't finish within the time-out 2000
    // Use expectedExceptions when you need to write negative and regression tests
    public void testTableDataSearch(){
        driver.findElement(By.linkText("Table Data Search")).click();
        driver.findElement(By.id("task-table-filter")).sendKeys("Timothe Wintle");
        //driver.findElement(By.id("NoSuchID")).click();
        //org.testng.TestException:
        //Method TestTimeoutAndExpectedExceptions.testTableDataSearch()
        // [pri:0, instance:DemoTestNG.TestTimeoutAndExpectedExceptions@34c01041]
        // should have thrown an exception of any of types
        // [class org.openqa.selenium.NoSuchElementException, class org.openqa.selenium.TimeoutException]
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
