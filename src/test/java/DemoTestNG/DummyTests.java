package DemoTestNG;

import com.listeners.ItestListeners;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ItestListeners.class)
//@Listeners({ItestListeners.class,Listener2.class,Listener3.class}) // Array of listeners (Listener2.class,Listener3.class don't exist they are example)
public class DummyTests {

    @Test
    public void test1(){
        System.out.println("This is test1");
    }

    @Test
    public void test2(){
        System.out.println("This is test2");
        Assert.assertTrue(false); // it will fail the test
    }

    @Test(timeOut = 1000)
    public void test3() throws Exception{
        System.out.println("This is test3");
        Thread.sleep(2000); // it will fail the test, because the test timeOut is 1 sec. and the thread wait 2 sec.
    }

    @Test(dependsOnMethods = "test3")
    public void test4(){ // it will be skipped, because test3 is failed
        System.out.println("This is test4");
    }

}
