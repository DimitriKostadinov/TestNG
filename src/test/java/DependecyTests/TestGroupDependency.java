package DependecyTests;

import org.testng.annotations.Test;

public class TestGroupDependency {

    @Test (groups = "Smoke")
    public void test1(){
        System.out.println("Smoke1");
    }

    @Test (groups = "Smoke")
    public void test2(){
        System.out.println("Smoke2");
    }

    @Test (groups = "Smoke")
    public void test3(){
        System.out.println("Smoke3");
    }
    @Test (groups = "Sanity")
    public void test4(){
        System.out.println("Sanity1");
    }

    @Test (groups = "Sanity")
    public void test5(){
        System.out.println(5/0);
    }

    @Test (groups = "Sanity")
    public void test6(){
        System.out.println("Sanity3");
    }
    @Test (groups = "Regression")
    public void test7(){
        System.out.println("regression1");
    }

    @Test (groups = "Regression")
    public void test8(){
        System.out.println("regression2");
    }

    @Test (groups = "Regression")
    public void test9(){
        System.out.println("regression3");
    }

    /*@Test(dependsOnGroups = {"Smoke","Regression"}) // This test will be executed when
    // all tests from the dependency group pass! testMethod -> testGroup
    public void test0(){
        System.out.println("Main test");
    }*/
}
