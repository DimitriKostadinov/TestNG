package InvocationCount_ThreadPoolSize;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.time.Duration;

public class InvCount_ThreadPoolSize {

//ThreadLocal<WebDriver> driver = new ThreadLocal<>(); -
// Creates a ThreadLocal variable that will store the WebDriver instance for each thread.

//driver.set(webDriver);: Sets the WebDriver instance for the current thread.
//driver.get();: Retrieves the WebDriver instance for the current thread.
//driver.remove();: Removes the WebDriver instance from the current thread to prevent memory leaks.

    // ThreadLocal for WebDriver instances
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.randomuser.me/");
        driverThread.set(webDriver);
    }

    // return the current driverThread instance
    public WebDriver getDriver() {
        return driverThread.get();
    }

    @Test(invocationCount = 6, threadPoolSize = 2)// invocationCount - will execute the test six times
    // threadPoolSize will execute the number of invocationCount in the number of thread parallel
    public void test1() throws Exception {

        WebDriver webDriver = driverThread.get();
        webDriver.findElement(By.xpath("//*[@id=\"values_list\"]/li[1]")).click();

        // the test will wait 3 seconds or until the image is shown.
        By image = By.xpath("//*[@id=\"user_photo\"]/img");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(image));

        String name = webDriver.findElement(By.id("user_value")).getText();

        System.out.println("Name: " + name);

        webDriver.findElement(By.xpath("//*[@id=\"values_list\"]/li[2]")).click();
        String email = webDriver.findElement(By.id("user_value")).getText();

        System.out.println("Email is: " + email);

        createExcel(name, email);
    }

    @AfterMethod
    public void tearDown() {
        // Quit the WebDriver and remove ThreadLocal
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }

    public static synchronized void createExcel(String username, String email) throws IOException {
        Workbook workbook;
        Sheet sheet;
        File file = new File("K:\\JAVAProjects\\TestNG\\resources\\ExcelFiles\\InvCount.xlsx");

        // If the file exists, we open it, otherwise we create a new one
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = WorkbookFactory.create(fis);
            fis.close();
            sheet = workbook.getSheetAt(0);
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Data");

            // Creating a row for titles
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Name");
            header.createCell(1).setCellValue("Email");
        }

        // We find the last line
        int lastRowNum = sheet.getLastRowNum();
        Row newRow = sheet.createRow(lastRowNum + 1);
        newRow.createCell(0).setCellValue(username);
        newRow.createCell(1).setCellValue(email);

        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        workbook.close();
    }
}
