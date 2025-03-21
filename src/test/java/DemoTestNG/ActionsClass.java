package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;

public class ActionsClass {
// Keyboard interface methods:
// SendKeys() //Used to send text to web element
// KeyDown() //Send the key press without a release
// KeyUp() //Helps in releasing key

// Mouse interface methods:
// Click() // Simply clicks on the element - one's left mouse button click
// DoubleClick() // Double left mouse button click
// ContextClick() // Performs right click operation
// ClickAndHold() // Clicks at the current mouse location without releasing it
// DragAndDrop() // involkes ClickAndHold at source location moves to the target without releasing the mouse
// DragAndDropBy()// It performs ClickAndHold at the source location and shifts
// according to the given offset horizontally or vertically (X and Y axes)
// MoveByOffset()// Helps in shifting the mouse from current position to the given offset
// MoveToElement() Helps to shift the mouse location to the centre of the page or center of the element

    WebDriver driver;


    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void test1(){

        driver.get("https://www.edureka.co/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Actions builder = new Actions(driver);
        // Navigate to the dropdown
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'category_dropdown__3JMD2')]" +
                        "//span[@class='category_category_drop__27kLI']")
        ));
        builder.moveToElement(dropdown).perform();

        // Navigate to "Software Testing"
        WebElement softwareTesting = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("Software Testing")));

        builder.moveToElement(softwareTesting).perform();
        softwareTesting.click();

        // Find "Selenium Course"
        WebElement seleniumCourse = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@data-title,'Selenium Course')]")));
        seleniumCourse.click();

        // SearchBox
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='navbar_search_bx__1IWnI']")));
        builder.moveToElement(searchBox).click().perform();

        WebElement searchBox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("search-input")));
        builder.moveToElement(searchBox2).sendKeys("Rest Assured").perform();

        WebElement BackBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'searchelasticoverlay_close_btn__1ffHV')]")));
        BackBtn.click();
    }

    @Test
    public void testDragAndDrop() throws InterruptedException {
        driver.get("http://jqueryui.com/droppable/");
        driver.switchTo().frame(0);
        WebElement SourceElement = driver.findElement(By.id("draggable"));
        WebElement TargetElement = driver.findElement(By.id("droppable"));
        System.out.println(TargetElement.getText());
        Actions actions = new Actions(driver);
        actions.dragAndDrop(SourceElement,TargetElement).build().perform();
        //actions.clickAndHold(SourceElement).moveToElement(TargetElement).release().build().perform(); //Alternative way
        System.out.println(TargetElement.getText());
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
