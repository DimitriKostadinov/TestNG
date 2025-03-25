package PageObjectModelExample;

import org.testng.annotations.Test;

public class POMTestElementsPage extends TestNgBase{

    @Test
    public void testElementsPage(){
        driver.get("https://demoqa.com/elements");
        POMElementsPage elementsPage = new POMElementsPage(driver);
        elementsPage.fillTheForm();
        elementsPage.verticalScroll();
        elementsPage.submitTheForm();
        elementsPage.VerifyTheSubmit();
    }
}
