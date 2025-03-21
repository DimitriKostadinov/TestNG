package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ItestListeners implements ITestListener {
// run tests from testng_listeners.xml
    @Override
    public void onTestStart(ITestResult result){
        System.out.println("ITestListener - onTestStart");
    }

    @Override
    public void onTestSuccess(ITestResult result){
        System.out.println("ITestListener - onTestSuccess");
    }

    @Override
    public void onTestFailure(ITestResult result){
        System.out.println("ITestListener - onTestFailure");
    }

    @Override
    public void onTestSkipped(ITestResult result){
        System.out.println("ITestListener - onTestSkipped");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result){
        System.out.println("ITestListener - onTestFailedWithTimeout");
    }

    @Override
    public void onStart(ITestContext context){
        System.out.println("ITestListener - onStart");
    }

    @Override
    public void onFinish(ITestContext context){
        System.out.println("ITestListener - onFinish");
    }

    // Most useful listeners in TestNG
    //IAnnotationTransformer
    //IAnnotationTransformer2
    //IHookable
    //IInvokedMethodListener
    //IMethodInterceptor
    //IReporter
    //ISuiteListener
    //ITestListener

}
