package PageObjectModelExample;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
public class POMElementsPage extends TestNgBase{
    WebDriver driver;

    // Constructor
    public POMElementsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // create Page Factory object
    }
    @FindBy(xpath = "//*[@id=\"item-0\"]") WebElement TextBox;
    @FindBy(id = "userName") WebElement Username;
    @FindBy(id = "userEmail") WebElement email;
    @FindBy(id = "currentAddress") WebElement curAddress;
    @FindBy(id = "permanentAddress") WebElement perAddress;

    public void fillTheForm(){
        TextBox.click();
        Username.sendKeys("Dimitri Kostadinov");
        email.sendKeys("test_email@gmail.com");
        curAddress.sendKeys("Bulgaria, Sofia");
        perAddress.sendKeys("Bulgaria, Sofia - City");
    }

    public void verticalScroll(){
        JavascriptExecutor js = (JavascriptExecutor) driver; // create JavascriptExecutor object
        js.executeScript("window.scrollBy(0,300)");// it will scroll with 300 pixels vertically
    }

    @FindBy(id = "submit") WebElement submitBtn;
    public void submitTheForm(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        element.click();
    }
    @FindBy(xpath = "//div[@class='mt-4 row']") WebElement output;
    public void VerifyTheSubmit(){
        String outputContent = output.getText().trim();
        Assert.assertFalse(outputContent.isEmpty(),"The form isn't submitted successfully");
    }

}
