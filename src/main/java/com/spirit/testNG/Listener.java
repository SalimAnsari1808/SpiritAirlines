package com.spirit.testNG;

import java.io.IOException;
import java.util.Iterator;

import com.spirit.managers.ExtentReportManager;
import com.spirit.managers.FileReaderManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;

public class Listener extends BaseClass implements ITestListener, ISuiteListener, IInvokedMethodListener{

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		//setExtTest(rep.startTest(result.getName().toUpperCase()));
		//startTest(result.getMethod().getMethodName(),getTestMethodName(result) + " Test Case Started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		/*getExtText().log(LogStatus.PASS, result.getName().toUpperCase() + " PASS");
		rep.endTest(getExtText());
		rep.flush();*/

	}

	@Override
	public void onTestFailure(ITestResult result) {
		/*getExtText().log(LogStatus.FAIL, result.getName().toUpperCase() + " FAIl with exception " + result.getThrowable());
		try {
			getExtText().log(LogStatus.FAIL, getExtText().addScreenCapture(TestUtil.takeScreenshotAtEndOfTest(new BaseClass().getDriver())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rep.endTest(getExtText());
		rep.flush();*/

		//Get driver from BaseTest and assign to local webdriver variable.
//		Object testClass = result.getInstance();
//
//		//Take base64Screenshot screenshot.
//		String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)getDriver()).
//				getScreenshotAs(OutputType.BASE64);



		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//getExtText().log(LogStatus.SKIP, result.getName().toUpperCase() + "SKIP with exception " + result.getThrowable());
		//rep.endTest(getExtText());
		//rep.flush();


	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {

		String suiteName, appURL = null;
		//get the suite name from xml file
		suiteName = context.getCurrentXmlTest().getSuite().getName();

		//check the
		if(FileReaderManager.getInstance().getConfigReader().getApplicationURL().contains("qaepic")){
			appURL = "_QAEpic01_";
		}else if(FileReaderManager.getInstance().getConfigReader().getApplicationURL().contains("nav")) {
			appURL = "_Nav01_";
		}



        Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();
        while (skippedTestCases.hasNext()) {
            ITestResult skippedTestCase = skippedTestCases.next();
            ITestNGMethod method = skippedTestCase.getMethod();
            if (context.getSkippedTests().getResults(method).size() > 0) {
                System.out.println("Removing:" + skippedTestCase.getTestClass().toString());
                skippedTestCases.remove();
            }
        }
		
	}

}
