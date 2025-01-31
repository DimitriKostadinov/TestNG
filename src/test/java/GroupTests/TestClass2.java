package GroupTests;

import org.testng.annotations.Test;

public class TestClass2 {

    @Test(groups = {"smoke"})
    public void test4(){
        System.out.println("test4");
    }

    @Test(groups = {"functional"})
    public void test5(){
        System.out.println("test5");
    }

    @Test
    public void test6(){
        System.out.println("test6");
    } // Will not be executed because is not a part of a group

    //the test are runs from the 'testng_group_test.xml' file
}
