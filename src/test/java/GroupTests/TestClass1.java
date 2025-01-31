package GroupTests;

import org.testng.annotations.Test;
@Test(groups = {"all"})
public class TestClass1 {

    @Test(groups = {"smoke"})
    public void test1(){
        System.out.println("test1");
    }

    @Test(groups = {"smoke","functional"})
    public void test2(){
        System.out.println("test2");
    }

    @Test(groups = {"functional","regression"})
    public void test3(){
        System.out.println("test3");
    }

    //the test are runs from the 'testng_group_test.xml' file
}
