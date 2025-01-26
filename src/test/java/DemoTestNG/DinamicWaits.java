package DemoTestNG;

import com.google.common.base.Function;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DinamicWaits {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @Test
    public void testExplicitWait(){
        driver.findElement(By.linkText("Dynamic Data Loading")).click();
        driver.findElement(By.id("save")).click();
        By image = By.xpath("//*[@id=\"loading\"]/img");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(image)); // the test will wait 5 seconds or until the image is shown.
        boolean isImageDisplayed = driver.findElement(image).isDisplayed();
        Assert.assertTrue(isImageDisplayed,"\n Image is not displayed. \n");
    }

    @Test
    public void testFluentWait(){
        driver.findElement(By.linkText("JQuery Download Progress bars")).click();
        driver.findElement(By.id("downloadButton")).click();
        // Waiting 30 seconds for an element to be present on the page, checking for this presence once every 100 milliseconds.
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30L))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>(){
            public WebElement apply(WebDriver driver) {
               //WebElement progress = driver.findElement(By.xpath("//div[@id='dialog']//div[@class='progress-label']"));
               WebElement progress = driver.findElement(By.xpath("//*[@id='dialog']/div[1]"));
               String progressBarText = progress.getText();

               if (progressBarText.equals("Complete!")){
                   System.out.println("Progress is complete!");
                   return progress;
               }
               else {
                   System.out.println(progress.getText());
                   return null;
               }
            }
        });
    }

    @Test
    public void testImplicitWait(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://the-internet.herokuapp.com/dynamic_loading");
        driver.findElement(By.partialLinkText("Example 2")).click();
        driver.findElement(By.xpath("//*[@id=\"start\"]/button")).click();
        By helloWord = By.xpath("//*[@id=\"finish\"]/h4");
        String actualMassage = driver.findElement(helloWord).getText();
        Assert.assertEquals(actualMassage,"Hello World!","\n Message is not Hello World!");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit(); // close the opened Chrome tab from the test
    }
}
