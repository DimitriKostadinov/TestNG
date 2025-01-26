package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParallelTesting2 {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test(threadPoolSize = 3, invocationCount = 4) //@Test(threadPoolSize=x) annotation defines the number of threads to be used while running a test method.
    //@Test(invocationCount=x) When you wish to run the same tests several times, you use invocation count. This one will run 4 times
    public void test3_BootstrapAlerts(){
        driver.get("https://www.lambdatest.com/selenium-playground/bootstrap-alert-messages-demo");
        System.out.println(Thread.currentThread().getId() + ": Bootstrap Alert Message Page");
    }

    @Test
    public void test4_DragDropRRangeSlider(){
        driver.get("https://www.lambdatest.com/selenium-playground/drag-and-drop-demo");
        System.out.println(Thread.currentThread().getId() + ": Drag And Drop Range Slider Page");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit(); // This will only be called if the driver is not null. if driver is null: The code inside the if block
                               // will not be executed. This prevents attempts to use an uninitialized object, which would result in a NullPointerException.
            } catch (Exception e) {
                System.out.println("Error while quitting the driver: " + e.getMessage());
            }
        }
    }
}
