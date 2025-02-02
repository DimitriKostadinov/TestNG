package DependecyTests;

import org.testng.annotations.Test;

public class TestDependecy {

    static String trackingNumber = null;

    @Test
    public void CreateShipment() throws Exception {
        trackingNumber = "ABC12YHB";
        //trackingNumber = null;

        if(trackingNumber != null) {
            System.out.println("CreateShipment");
        }
        else {
            throw new Exception("Invalid tracking number");
        }
    }
    // The TestNG will first execute the test with which the other test has a dependency. If the first(main) test pass
    // will execute the other test if it does not pass it will Skip the other tests!
    // If the tests have priority and dependency the priority of the test will be IGNORED
    // and the tests will be executed on the flow of the dependencies.
    // @Test(dependsOnMethods = {"CreateShipment"}, alwaysRun = true) alwaysRun = true - Will execute the test if the dependency is failed
    // If the dependent (CreateShipment) test is ignored,skipped or deleted the other test will not be executed.
    // @Test(dependsOnMethods = {"CreateShipment"},ignoreMissingDependencies = true)
    //If the 'CreateShipment' is deleted the tests that depend on 'CreateShipment' will b execute if has this parameter.
    @Test(dependsOnMethods = {"CreateShipment"}) // In this array, we pass the names of the tests that have dependencies with this test.
    // It can pass more than one test name. dependsOnMethods = {"CreateShipment","Test2","Test 3"}
    public void TrackShipment() throws Exception {
        if(trackingNumber != null) {
            System.out.println("TrackShipment");
        }
        else {
            throw new Exception("Invalid tracking number");
        }
    }

    @Test(dependsOnMethods = {"TrackShipment"})
    public void CancelShipment() throws Exception {
        if(trackingNumber != null) {
            System.out.println("CancelShipment");
        }
        else {
            throw new Exception("Invalid tracking number");
        }
    }
}
