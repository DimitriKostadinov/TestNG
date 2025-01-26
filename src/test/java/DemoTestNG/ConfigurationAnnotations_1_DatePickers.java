package DemoTestNG;

import org.testng.annotations.*;

public class ConfigurationAnnotations_1_DatePickers {
    @Test(groups = "smoke") // When the @Test annotation is for each method the test group is also for each method
    public void test1_BootstrapDatePicker(){
        System.out.println("Test Method 1: Bootstrap Date Picker");
    }

    @Test(groups = {"regression", "e2e"})
    public void test2_JQueryDatePicker(){
        System.out.println("Test Method 2: JQuery Date Picker");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Execute before each test method");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("Execute after each test method \n");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Execute before Class: Date Pickers");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Execute after Class: Date Pickers");
    }
}
