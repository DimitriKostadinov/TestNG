package DemoTestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.*;


public class DataProviderTypes {

    @Test(dataProvider = "dp1")
    public void TestDp1(Object s) throws Exception{ // When the DataProvider type is Object the
         // method parameter must be Object as well
        System.out.println(s + "\n");
    }

    @Test(dataProvider = "dp2")
    public void TestDp2(Object[] s) throws Exception{
        System.out.println(s[0]+ " - " + s[1]);
    }

    @Test(dataProvider = "DpIndices")
    public void TestDpIndices(String s) throws Exception{
        System.out.println(s);
    }

    // The type Object in java can handle any type of data String, int, double etc.
    // Single array
    // Two-dimensional array MATRIX see classes 'DataProvidersTest.java' and 'DataProviderOnly.java'

    // Classes 'DataProvidersTest.java' and 'DataProviderOnly.java'
    // are example using Data Provider in another class not in test class
    @DataProvider()
    public Object[] dp1(){
        Object[] data = new Object[]{
               "data1",2,"data3"
        };
        return data;
    }
    //An Iterator is an object that can be used to loop through collections, like ArrayList and HashSet.
    //It is called an "iterator" because "iterating" is the technical term for looping.
    //To use an Iterator, you must import it from the java.util package.
    @DataProvider()
    public Iterator<Object[]> dp2(){
        Set<Object[]> data = new HashSet<>();
        data.add(new Object[] {"Iterator data 1","Iterator data 2"});
        data.add(new Object[] {3.14,"Iterator data 4"});
        data.add(new Object[] {5,"Iterator data 6"});
        return data.iterator();
    }

    // Whit Indices we can use part of the data in the data provider,
    // by specifying which element in an array we want with the item index.

    @DataProvider(indices = {0,2})
    public String[] DpIndices(){
        String[] data = new String[]{
                "Ilian", "Martin", "Kamen", "Mariana","Simona"
        };

        return data; // Will return the item in array with index [0] and [2]
    }
}
