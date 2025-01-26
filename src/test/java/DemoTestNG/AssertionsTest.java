package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionsTest {

    SoftAssert softAssert = new SoftAssert();
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @Test
    public void testSingleCheckbox(){
        driver.findElement(By.linkText("Checkbox Demo")).click();
        driver.findElement(By.id("isAgeSelected")).click();
        String actualMessage = driver.findElement(By.id("txtAge")).getText();
        Assert.assertTrue(actualMessage.contains("Checked"),"\n Message doesn't contain 'Checked' \n"); // The message will be printed in the console if the test fails.
    }

    @Test
    public void testRadioButtons(){
        driver.findElement(By.linkText("Radio Buttons Demo")).click();
        driver.findElement(By.xpath("//input[@value='Other']")).click();
        driver.findElement(By.xpath("//input[@value='5 - 15']")).click();
        driver.findElement(By.xpath("//button[text()='Get values']")).click();
        String actualGender = driver.findElement(By.cssSelector(".genderbutton")).getText();
        String actualAgeGroup = driver.findElement(By.cssSelector(".groupradiobutton")).getText();

        /*Assert.assertEquals(actualGender, "Male","\n Actual gender is not correct \n");
        Assert.assertTrue(actualAgeGroup.contains("34"),"Actual age group is not correct");*/

        // Assert (Hard Assert) is for situations where the test cannot continue if a check in the test fails. Use it when you want to check one condition in test.
        // Soft Assert is useful for cases where you want to check multiple conditions in the same test without to end the test on the first failure. Use it when you want to check multiple condition in test.

        softAssert.assertEquals(actualGender, "Male","\n Actual gender is not correct \n");
        softAssert.assertTrue(actualAgeGroup.contains("15"),"Actual age group is not correct");
        softAssert.assertAll(); // When use soft assert you should create an object from SoftAssert class and use assertAll() method to show the asserts in the console.
    }

}
