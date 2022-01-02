package com.spirit.testNG;

import java.util.concurrent.atomic.AtomicInteger;


import com.spirit.baseClass.BaseClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer extends BaseClass implements IRetryAnalyzer{

	public static int MAX_RETRY_COUNT = 0;

	  // to avoid the thread safe issue, use the AtomicInteger class, instead of Integer
	  AtomicInteger count = new AtomicInteger(MAX_RETRY_COUNT);

	  public boolean isRetryAvailable() {
	    return (count.intValue() > 0);
	  }

	  /**
	   * retry a failed test
	   *
	  // * @param ITestResult result - the test result of current test case
	   * 
	   * @return Returns true if the test method has to be retried, false otherwise.
	   * 
	   * @author Salim Ansari
	   */


	  @Override
	  public boolean retry(ITestResult result) {
	    boolean retry = false;
	    if (isRetryAvailable()) {
	      System.out.println("Going to retry test case: " + result.getMethod() + ", "
	          + (MAX_RETRY_COUNT - count.intValue() + 1) + " out of " + MAX_RETRY_COUNT);
	      retry = true;
	      // --count
	      count.decrementAndGet();
	    }
	    return retry;
	  }

}
