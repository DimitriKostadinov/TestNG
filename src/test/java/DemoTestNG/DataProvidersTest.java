package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvidersTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @DataProvider
    public Object [][] ajaxData(){
        Object[][] data = new Object[2][2];
        data[0][0] = "Joe Doe"; data[0][1] = "Tester Joe Doe";
        data[1][0] = "Jane Doe"; data[1][1] = "Tester Jane Doe";

        return data;
    }

    @Test(dataProvider = "ajaxData") // When the DataProvider doesn't have a name we use the name of the method
    public void testAjaxForm(String name, String comment){
        System.out.println("Name: " + name);
        System.out.println("Comment: " + comment);
        driver.get("https://www.lambdatest.com/selenium-playground/ajax-form-submit-demo");
        driver.findElement(By.id("title")).sendKeys(name);
        driver.findElement(By.id("description")).sendKeys(comment);
        driver.findElement(By.id("btn-submit")).click();
    }

    @Test(dataProviderClass = DataProviderOnly.class, dataProvider = "input-provider")// Called the DataProvider by name
    public void testInputFields(String name,String email,String password,String company,String website,
                                String country,String city, String address1, String address2, String state, int zipCode){
        System.out.println("Name: " + name + " " + "Email: " + email + " "  + "Password: " + password + " "  +
                "Company: " + company + " "  + "Website: " + website + " "  + "Country: " + country + " "  + "City: " +
                city + " "  + "Address 1: " + address1 + " "  + "Address 2: " + address2 + " "  + "State: " + state +
                " "  + "Zip code: " + zipCode + " ");

        driver.get("https://www.lambdatest.com/selenium-playground/input-form-demo");
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("inputEmail4")).sendKeys(email);
        driver.findElement(By.id("inputPassword4")).sendKeys(password);
        driver.findElement(By.id("company")).sendKeys(company);
        driver.findElement(By.xpath("//*[@id=\"websitename\"]")).sendKeys(website);
        driver.findElement(By.xpath("//*[@id=\"seleniumform\"]/div[3]/div[1]/select")).sendKeys(country);
        driver.findElement(By.id("inputCity")).sendKeys(city);
        driver.findElement(By.name("address_line1")).sendKeys(address1);
        driver.findElement(By.id("inputAddress2")).sendKeys(address2);
        driver.findElement(By.id("inputState")).sendKeys(state);
        driver.findElement(By.id("inputZip")).sendKeys(String.valueOf(zipCode));
        driver.findElement(By.xpath("//*[@id=\"seleniumform\"]/div[6]/button")).click();

        By formSubmit = By.xpath("//*[@id=\"__next\"]/div/section[2]/div/div/div/div/p");
        String actualMassage = driver.findElement(formSubmit).getText();
        Assert.assertEquals(actualMassage,"Thanks for contacting us, we will get back to you shortly.",
                "\n The input form has not been submitted successfully !");
    }

    @AfterMethod
    public void tearDown(){
        //driver.quit(); // close the opened Chrome tab from the test
    }

}
