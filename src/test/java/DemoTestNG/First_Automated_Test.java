package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class First_Automated_Test {
    WebDriver driver;

    /*@BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }*/

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1) // The lowest number is the higher priority (priority can be a negative number and be executed before 1)
    public void testTableDataSearch(){
        driver.findElement(By.linkText("Table Data Search")).click();
        driver.findElement(By.id("task-table-filter")).sendKeys("Timothe Wintle");
    }

    @Test(priority = 2)
    public void testBootstrapDatePicker(){
        driver.findElement(By.linkText("Bootstrap Date Picker")).click();
        driver.findElement(By.name("birthday")).sendKeys("11.01.2025 г.");
        //driver.navigate().back();//return to the previous page
    }
}
