package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RobotClass {
    // KeyPress() robot.keyPress(KeyEvent.VK_DOWN) // Simulate press key 'down arrow' button on the keyboard
    // KeyRelease() robot.keyRelease(KeyEvent.VK_DOWN)
    // MousePress() robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
    // MouseRelease() robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK)
    // MouseMove() robot.mouseMove(point.getX(),point.getY())

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @Test
    public void test1() throws AWTException{
        driver.findElement(By.linkText("Input Form Submit")).click();
        Robot robot = new Robot();
        driver.findElement(By.id("name")).sendKeys("Dimitri");
        robot.keyPress(KeyEvent.VK_TAB); // Simulate press key 'Tab' button on the keyboard
        driver.findElement(By.id("inputEmail4")).sendKeys("d.kostadinov_@abv.bg");
        robot.keyPress(KeyEvent.VK_TAB);
        driver.findElement(By.id("inputPassword4")).sendKeys("ThisIsStrongPassword!");
        driver.findElement(By.id("company")).sendKeys("Philips");
        driver.findElement(By.id("websitename")).sendKeys("https://www.philips.bg/");
        WebElement selectElement = driver.findElement(By.xpath("//div[@class='flex smtablet:block mt-20']//select"));
        Select select = new Select(selectElement); // Using the selenium Select class
        select.selectByVisibleText("Bulgaria");
        driver.findElement(By.id("inputCity")).sendKeys("Sofia");
        driver.findElement(By.id("inputAddress1")).sendKeys("Test address 1");
        driver.findElement(By.id("inputAddress2")).sendKeys("Test address 2");
        driver.findElement(By.id("inputState")).sendKeys("Sofia City");
        driver.findElement(By.id("inputZip")).sendKeys("1000");
        WebElement submitBtn = driver.findElement(By.xpath("//div[@class='text-right mt-20']//button"));
        Point point = submitBtn.getLocation(); // Get the location on the Submit button
        int x = point.getX();
        int y = point.getY();
        robot.mouseMove(x+80,y+150);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // Left mouse btn. press down
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);  // Left mouse btn. release
        WebElement formSubmit = driver.findElement(By.xpath("//p[@class='success-msg hidden']"));
        Assert.assertTrue(formSubmit.isDisplayed(),"The form isn't submit successfully !");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
