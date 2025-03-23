package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;


public class WindowHandleClass {
    // Get.WindowHandle() - gets the details of the current window
    // Get.WindowHandles() - gets the details of the all the windows
    // Set<String> command = driver.get.WindowHandles()
    // SwitchTo - helps in switching between the windows
    // Action - command used to perform actions on the current window

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/browser-windows");
    }

    @Test
    public void test1() throws InterruptedException{
        WebElement clickElement = driver.findElement(By.id("windowButton"));
        String parentWindowHandle = driver.getWindowHandle(); // gets the details of the current window
        System.out.println("Parent window's handle -> " + parentWindowHandle); // Will print the unique window id

        for (int i = 0; i < 3; i++) {
            clickElement.click();
            Thread.sleep(1000);
        }

        Set<String> allWindwHandles = driver.getWindowHandles(); // gets the details of the all the windows
        for (String handle : allWindwHandles) {
            System.out.println("Window handle -> " + handle); // Will print the unique window id
        }
    }

    @Test
    public void testSwitchTo() throws InterruptedException {
        WebElement newWindowButton = driver.findElement(By.id("windowButton"));
        String parentWindowHandle = driver.getWindowHandle(); // Основен прозорец
        System.out.println("Основен прозорец: " + parentWindowHandle);

        for (int i = 0; i < 3; i++) {

            newWindowButton.click();
            Thread.sleep(1000); // Изчакване за новия прозорец

            // Изчакваме новия прозорец да се появи
            Set<String> allWindows = driver.getWindowHandles();

            // Търсим новия прозорец
            String newWindowHandle = allWindows.stream()
                    .filter(handle -> !handle.equals(parentWindowHandle))
                    .findFirst()
                    .orElse(null);

            if (newWindowHandle != null) {
                driver.switchTo().window(newWindowHandle);
                Thread.sleep(1000);

                try {
                    WebElement heading = driver.findElement(By.id("sampleHeading"));
                    System.out.println("Нов прозорец: " + newWindowHandle);
                    System.out.println("Текст от прозореца: " + heading.getText());
                } catch (Exception e) {
                    System.out.println("Елементът 'sampleHeading' не беше намерен.");
                }

                driver.close();  // Затваряме новия прозорец
                driver.switchTo().window(parentWindowHandle); // Връщаме се към основния прозорец
            } else {
                System.out.println("Нов прозорец не беше открит.");
            }
        }
        driver.quit(); // Затваряме всичко
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
