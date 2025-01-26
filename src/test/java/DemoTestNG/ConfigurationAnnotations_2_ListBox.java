package DemoTestNG;

import org.testng.annotations.*;

@Test(groups = "smoke") // When the @Test annotation is like this for all methods in the class the test group is also for all methods
public class ConfigurationAnnotations_2_ListBox {

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
        System.out.println("Execute before Class: List Box");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Execute after Class: List Box");
    }

    public void test3_BootstrapListBox(){
        System.out.println("Test Method 3: Bootstrap List Box");
    }

    public void test4_JQueryListBox(){
        System.out.println("Test Method 4: JQuery List Box");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Execute before each test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("Execute after each test");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Execute before each suite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Execute after each suite");
    }

    @BeforeGroups(groups = {"regression","smoke"})
    public void beforeGroups() {
        System.out.println("Execute before Group");
    }

    @AfterGroups(groups = {"regression","smoke"})
    public void afterGroups() {
        System.out.println("Execute after Group");
    }

}
