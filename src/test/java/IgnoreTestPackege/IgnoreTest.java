package IgnoreTestPackege;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class IgnoreTest {

    @Test
    public void test1(){
        System.out.println("test1");
    }

    @Ignore // Will not execute this test when running the tests from 'testng_ignore_test.xml' or from the class
    @Test
    public void test2(){
        System.out.println("test2");
    }

    @Test
    public void test3(){
        System.out.println("test3");
    }
}
