package PageObjectModelExample;

import org.testng.annotations.Test;

public class POMTestHomePage extends TestNgBase{

    @Test
    public void testHomePage() {
        POMHomePage homePage = new POMHomePage(driver); // create Page Factory object
        homePage.verifyHomePageURL();
        homePage.clickOnElements();
    }
}
