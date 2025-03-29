package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class CrossBrowser {

    // Run from testng_cross_browser.xml
    WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser) throws Exception{

        if(browser.equalsIgnoreCase("Firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }

        else if(browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        else if(browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }

        else{
            throw new Exception("Browser is not correct");
        }
    }

    @Test
    public void testParameterWithXML() throws AWTException{
        Robot robot = new Robot();
        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.findElement(By.linkText("Table Data Search")).click();
        driver.findElement(By.id("task-table-filter")).click();
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_T);
        robot.keyPress(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_E);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);
        String personName = driver.findElement(By.xpath("//table[@id='task-table']//child::tr[2]//child::td[3]")).getText();
        System.out.println("The person name is : " + personName);
        Assert.assertTrue(personName.contains("Halima Werknesh"),"\n Wrong search criteria ! \n");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
