package PageObjectModelExample;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WithoutPOMAndPageFactory extends TestNgBase {

    @Test
    public void testWithoutPageFactory(){

        driver.get("https://demoqa.com/");
        driver.findElement(By.xpath("//div[@class='category-cards']" +
                "//div[@class='card mt-4 top-card'][1]")).click();
        driver.findElement(By.xpath("//ul[@class='menu-list']" +
                "//li[@class='btn btn-light '][1]")).click();
        driver.findElement(By.id("userName")).sendKeys("Dimitri Kostadinov");
        driver.findElement(By.id("userEmail")).sendKeys("test_email@gmail.com");
        driver.findElement(By.id("currentAddress")).sendKeys("Bulgaria, Sofia");
        driver.findElement(By.id("permanentAddress")).sendKeys("Bulgaria, Sofia - City");

        JavascriptExecutor js = (JavascriptExecutor) driver; // create JavascriptExecutor object
        js.executeScript("window.scrollBy(0,300)");// it will scroll with 300 pixels vertically

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        element.click();

        WebElement output = driver.findElement(By.id("output"));
        String outputContent = output.getText();
        Assert.assertFalse(outputContent.isEmpty(),"The form isn't submitted successfully");
    }
}
