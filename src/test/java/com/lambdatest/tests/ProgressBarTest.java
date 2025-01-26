package com.lambdatest.tests;

import com.lambdatest.pages.BootstrapProgressBarPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProgressBarTest extends BaseTest{
    SoftAssert softAssert = new SoftAssert();
    BootstrapProgressBarPage progressBarPage = new BootstrapProgressBarPage();

    @Test
    public void testProgressBarPercentage(){
        progressBarPage = homePage.clickBootstrapProgressBar();
        progressBarPage.clickStartDownloadButton();
        String actualMessage = progressBarPage.getCompletedMessage();
        String actualPercentage = progressBarPage.getProgressBarPecentage();
        String expectedMessage = "Download completed!";
        String expectedPercentage = "100%";
        softAssert.assertEquals(actualMessage,expectedMessage,
                "\n The message is not complete or correct \n");
        softAssert.assertEquals(actualPercentage,expectedPercentage,"" +
                "\n Percentage is not 100%");
        softAssert.assertAll();// This method is important when use soft assert, because if not is not added all test will pass, if they are fail.
    }
}
