package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParallelTesting1 {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void test1_JQueryDatePicker(){
        driver.get("https://www.lambdatest.com/selenium-playground/jquery-date-picker-demo");
        System.out.println(Thread.currentThread().getId() + ": JQuery Date Picker Page");
    }

    @Test
    public void test2_DataListFilter(){
        driver.get("https://www.lambdatest.com/selenium-playground/data-list-filter-demo");
        System.out.println(Thread.currentThread().getId() + ": Data List Filter Page");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Error while quitting the driver: " + e.getMessage());
            }
        }
    }
}
