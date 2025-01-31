package IgnoreTestPackege;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore // it wll ignore all test in the class
public class IgnoreTest2 {

    @Test
    public void test4(){
        System.out.println("test4");
    }

    @Test
    public void test5(){
        System.out.println("test5");
    }

    @Test
    public void test6(){
        System.out.println("test6");
    }
}
