package PageObjectModelExample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class POMHomePage {

    WebDriver driver;

    // Constructor
    public POMHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // create Page Factory object
    }

    // Check the URL
    public void verifyHomePageURL() {
        driver.get("https://demoqa.com/");
        String currURL = driver.getCurrentUrl();
        Assert.assertTrue(currURL.contains("https://demoqa.com/"), "\n Wrong redirect!\n");
    }

    @FindBy(xpath = "//div[@class='category-cards']//div[@class='card mt-4 top-card'][1]")
    WebElement elementsCard;

    public void clickOnElements() {
        elementsCard.click();
    }
}
