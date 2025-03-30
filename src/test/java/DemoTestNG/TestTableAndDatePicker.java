package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TestTableAndDatePicker {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testTable(){
        driver.get("https://www.lambdatest.com/selenium-playground/");
        boolean isSecondPage = false;
        driver.findElement(By.linkText("Table Sort & Search")).click();
        driver.findElement(By.id("example_next")).click();
        String name = null,position,office,age;
        WebElement currPage = driver.findElement(By.xpath("//div[@id='example_paginate']//span//a[2]"));
        String className = currPage.getAttribute("class");
        if (className.equals("paginate_button current")) {
                isSecondPage = true;
                WebElement nameElement = driver.findElement(By.xpath("//table[@id='example']//tbody//tr[3]//td[1]"));
                name = nameElement.getText();
                WebElement positionElement = driver.findElement(By.xpath("//table[@id='example']//tbody//tr[3]//td[2]"));
                position = positionElement.getText();
                WebElement officeElement = driver.findElement(By.xpath("//table[@id='example']//tbody//tr[3]//td[3]"));
                office = officeElement.getText();
                WebElement ageElement = driver.findElement(By.xpath("//table[@id='example']//tbody//tr[3]//td[4]"));
                age = ageElement.getText();
                System.out.println("The person data are:" + " Name: " + name + ", Position: " + position + ", Office: " + office + ", Age: " + age);
                Assert.assertTrue(isSecondPage && name.equals("H. Chandler"),"Wrong person !");
        }
        else {
            Assert.assertTrue(isSecondPage,"Wrong page !");
        }
    }

    @Test
    public void testDatePickerTypeInput(){
        driver.get("https://demoqa.com/date-picker");
        WebElement getDate = driver.findElement(By.id("datePickerMonthYearInput"));
        getDate.sendKeys(Keys.CONTROL + "a"); // Select all entered data in the input element
        getDate.sendKeys(Keys.DELETE); // Delete all entered data in the input element
        getDate.sendKeys("03/24/2025");
        String selectedDate = getDate.getAttribute("value");
        System.out.println(selectedDate);
        Assert.assertEquals(selectedDate, "03/24/2025", "The selected date is not the same as the entered date.");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
