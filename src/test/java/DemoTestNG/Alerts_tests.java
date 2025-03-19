package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Alerts_tests {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    // Javascript Alerts
    @Test
    public void testDefaultAlert(){
        driver.findElement(By.linkText("Javascript Alerts")).click();
        driver.findElement(By.xpath("//div[@class='mt-30 rounded'][1]//child::button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        alert.accept(); // Click on the 'ok' button on the alert
    }

    @Test
    public void testConfirmBox(){
        driver.findElement(By.linkText("Javascript Alerts")).click();
        driver.findElement(By.xpath("//div[@class='mt-30 rounded'][2]//child::button")).click();
        Alert confBox = driver.switchTo().alert();
        String alertText = confBox.getText();
        System.out.println(alertText);
        confBox.accept(); // ok button
        //confBox.dismiss(); // Cancel button
        String confBtn = driver.findElement(By.id("confirm-demo")).getText();
        System.out.println(confBtn);
        Assert.assertTrue(confBtn.contains("You pressed OK!"),"The pressed button is not 'ok'");
    }

    @Test
    public void testPromptBox(){
        driver.findElement(By.linkText("Javascript Alerts")).click();
        driver.findElement(By.xpath("//div[@class='mt-30 rounded'][3]//child::button")).click();
        Alert promBox = driver.switchTo().alert();
        String alertText = promBox.getText();
        System.out.println(alertText);
        promBox.sendKeys("Dimitri");
        promBox.accept(); // ok button
        //promBox.dismiss(); // Cancel button
        String confBtn = driver.findElement(By.id("prompt-demo")).getText();
        System.out.println(confBtn);
        if(confBtn.isEmpty()){
            Assert.assertTrue(confBtn.isEmpty(),"The pressed button is 'Cancel'");
        }
        else{
            Assert.assertTrue(confBtn.contains("You have entered "),"The pressed button is not 'ok'");
        }
    }

    // Bootstrap Alerts

    @Test
    public void testAutoClose(){
        driver.findElement(By.linkText("Bootstrap Alerts")).click();
        driver.findElement(By.xpath("//div[@class='w-6/12'][1]//child::button[1]")).click();
        WebElement alertMessage = driver.findElement(By.xpath("//div[@class='w-6/12 px-15'][1]//child::div[1]"));
        System.out.println(alertMessage.getText());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        boolean isInvisible = wait.until(ExpectedConditions.invisibilityOf(alertMessage));
        Assert.assertTrue(isInvisible, "Елементът не стана невидим след 5 секунди.");
    }

    @Test
    public void testNormalMessageBox(){
        driver.findElement(By.linkText("Bootstrap Alerts")).click();
        driver.findElement(By.xpath("//div[@class='w-6/12'][1]//child::button[2]")).click();
        WebElement alertMessage = driver.findElement(By.xpath("//*[@id=\"__next\"]/section[3]" +
                "/div/div/div/div/div[2]/div[2]"));
        System.out.println(alertMessage.getText());
        driver.findElement(By.xpath("//*[@id=\"__next\"]/section[3]/div/div/div/div/div[2]/div[2]")).click();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
