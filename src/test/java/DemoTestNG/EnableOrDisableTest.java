package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class EnableOrDisableTest {
    SoftAssert softAssert = new SoftAssert(); // Important to create SoftAssert object/instance
    WebDriver driver;

    @Parameters({"URL"}) // Pass parameters from the 'testng_enable_disable_test'.xml file
    @BeforeClass
    public void setUp(String url){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Parameters("Option")
    @Test
    public void selectFromDropdown(String option){
        driver.findElement(By.linkText("Select Dropdown List")).click();
        WebElement selectElement = driver.findElement(By.id("select-demo"));
        Select select = new Select(selectElement); // Using the selenium Select class
        select.selectByValue(option);
        By actualOption = By.xpath("//*[@id=\"__next\"]/div/section[2]/div/div/div/div[1]/div[2]/p");
        String getOption = driver.findElement(actualOption).getText();
        Assert.assertEquals(getOption,"Day selected :- Thursday",
                "\n The selected option is not correct!");
    }

    @Parameters({"firstOption","lastOption"})
    @Test(enabled = false)// if you want to disable test it should be disabled in the class and in the xml file
    public void multiSelectOption(String firstOption,String lastOption){
        driver.findElement(By.linkText("Select Dropdown List")).click();
        WebElement selectElement = driver.findElement(By.id("multi-select"));
        Select select = new Select(selectElement); // Using the selenium Select class
        select.selectByValue(firstOption);
        driver.findElement(By.id("printMe")).click(); // First Selected btn.
        select.selectByValue(lastOption);
        driver.findElement(By.id("printAll")).click(); // Get Last Selected btn.
        String firstSelected = driver.findElement(By.xpath("" +
                "//*[@id=\"__next\"]/div/section[2]/div/div/div/div[2]/div[2]/div/div[2]/p[1]/span")).getText();
        String lastSelected = driver.findElement(By.xpath("" +
                "//*[@id=\"__next\"]/div/section[2]/div/div/div/div[2]/div[2]/div/div[2]/p[2]/span")).getText();
        softAssert.assertEquals(firstSelected, firstOption,"\n The first selected option is not correct! \n");
        softAssert.assertEquals(lastSelected, lastOption,"\n The last selected option is not correct! \n");
        softAssert.assertAll();
        // When use soft assert you should create an object from SoftAssert class and use
        // assertAll() method to show the asserts in the console.
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

}
