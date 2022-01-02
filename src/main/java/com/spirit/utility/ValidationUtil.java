package com.spirit.utility;



import org.testng.Assert;
import org.testng.SkipException;
import com.spirit.baseClass.BaseClass;

/**********************************************************************************************
 * 
 * Class is used to provide the Wait of automation steps. 
 * 01.Method mark test case Pass/Fail depending upon the status flag or actual expected values.
 * 02.Method mark test case Pass/Warning depending upon the status flag or actual expected values.
 * 03.Method mark test case Pass/Skip depending upon the status flag or actual expected values.
 * 04.Methods also include log file printing method to print the test  passed as argument
 *  
 ***********************************************************************************************/

public class ValidationUtil{
	
	//**********************************************************************************************
	//Method Name:validateTestStep
	//Description: Method is used to mark test step Pass/Fail based on status flag.
	//			   Test Description will be printed in log file prefix with Pass/Fail depending on
	//             status flag passed as other argument.
	//Input Arguments:1.TestDesc  ->Description to be print into log file.
	//				  2.statusFlag->Status Flag of test step state
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 12-Feb-2018
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static boolean validateTestStep(String TestDesc, boolean statusFlag) {
		BaseClass baseClass = new BaseClass();

		//check the status flag of test step
		if(statusFlag) {
			//update extent report
			baseClass.getExtText().pass(TestDesc);

			//mark test as pass
			Assert.assertTrue(true);
		}else{
			System.out.println(TestDesc + ": FAILED");

			//update extent report
			baseClass.getExtText().fail(TestDesc);

			//mark test as fail
			Assert.assertTrue(false);
		}
		//need to add log file here to print all test description in log
		System.out.println(TestDesc);
		return statusFlag;
	}
	
	//**********************************************************************************************
	//Method Name:validateTestStep
	//Description: Method is used to mark test step Pass/Fail based on status flag.
	//			   Test Description will be printed in log file prefix with Pass/Fail depending on
	//             actual and expected string comparison.
	//Input Arguments:1.TestDesc  ->Description to be print into log file.
	//				  2.expectedValue->expected value of string
	//				  3.actualValue->actual value of string
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 12-Feb-2018
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void validateTestStep(String TestDesc, String expectedValue, String actualValue) {
		BaseClass baseClass = new BaseClass();

		//remove space and convert to upper case before comparision
		//expectedValue = expectedValue.trim().toUpperCase().replaceAll(" ","");
		//actualValue   = actualValue.trim().toUpperCase().replaceAll(" ","");

		expectedValue = TestUtil.removeNonAlphaNumericCharacterFromString(expectedValue.trim().toUpperCase().replaceAll(" ",""));
		actualValue   = TestUtil.removeNonAlphaNumericCharacterFromString(actualValue.trim().toUpperCase().replaceAll(" ",""))	;

		//need to add log file here to print all test description in log

		//check the status flag of test step
		if(expectedValue.equalsIgnoreCase(actualValue) || expectedValue.contains(actualValue)) {
			System.out.println(TestDesc);
			//update extent report
			baseClass.getExtText().pass(TestDesc);

			//mark test as pass
			Assert.assertTrue(true);
		}else{
			//Print Expected and Actual String value results on the machine console
			String consoleExpected = TestDesc + " Expected: ";
			String consoleActual	= TestDesc + " Actual: " ;
			int length = consoleExpected.length() + 1;//get length of expected string to use in formatted println
			System.out.println(String.format("%-"+ length+ "s%s" , consoleExpected, expectedValue ));
			System.out.println(String.format("%-"+ length+ "s%s" , consoleActual , actualValue ));

			//update extent report
			baseClass.getExtText().fail(TestDesc + "<br/> Expected: "+ expectedValue + "<br/> Actual: " + actualValue);

			//mark test as fail
			Assert.assertTrue(false);
		}	
	}
	
	//**********************************************************************************************
	//Method Name:warningTestStep
	//Description: Method is used to mark test step Pass/Warning based on status flag.
	//			   Test Description will be printed in log file prefix with Pass/Warning depending on
	//             status flag passed as other argument. Test step will mark as fail however test case
	//             will not terminate and continue till end.
	//Input Arguments:1.TestDesc  ->Description to be print into log file.
	//				  2.statusFlag->Status Flag of test step state
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 12-Feb-2018
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void warningTestStep(String TestDesc, boolean statusFlag) {
		//BaseClass baseClass = new BaseClass();
		//need to add log file here to print all test description in log
		
		//check the status flag of test step
		if(statusFlag) {
			//mark test as pass
			
			//update extent report
			//baseClass.getExtText().log(LogStatus.PASS, TestDesc);
		}else {
			//mark test as fail however continue test case
			
			//update extent report
			//baseClass.getExtText().log(LogStatus.FAIL, TestDesc);
		}	
	}
	
	//**********************************************************************************************
	//Method Name:warningTestStep
	//Description: Method is used to mark test step Pass/Warning based on status flag.
	//			   Test Description will be printed in log file prefix with Pass/Warning depending on
	//             actual and expected string comparison. Test step will mark as fail however test case
	//             will not terminate and continue till end.
	//Input Arguments:1.TestDesc  ->Description to be print into log file.
	//				  2.expectedValue->expected value of string
	//				  3.actualValue->actual value of string
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 12-Feb-2018
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void warningTestStep(String TestDesc, String expectedValue, String actualValue) {
		//BaseClass baseClass = new BaseClass();
		//need to add log file here to print all test description in log
		
		//check the status flag of test step
		if(expectedValue.equalsIgnoreCase(actualValue) | expectedValue.contains(actualValue)) {
			//mark test as pass
			//LazyAssert.assertTrue(true);	
			
			//update extent report
			//baseClass.getExtText().log(LogStatus.PASS, TestDesc);
		}else {
			//mark test as fail however continue test case
			//LazyAssert.assertTrue(false);
			
			//update extent report
			//baseClass.getExtText().log(LogStatus.FAIL, TestDesc);
		}	
	}
	
	//**********************************************************************************************
	//Method Name:skipTestCase
	//Description: Method is used to mark test case Pass/Skip based on status flag.
	//			   Test Description will be printed in log file prefix with Pass/Skip depending on
	//             status flag passed as other argument. Test Case will mark as Skip instead of 
	//             Pass/Fail in result file. No-Retry will occur after test case is skipped.
	//Input Arguments:1.TestDesc  ->Description to be print into log file.
	//				  2.statusFlag->Status Flag of test step state
	//Return: NA
	//Created By : Salim Ansari
	//Created On : 12-Feb-2018
	//Reviewed By:
	//Reviewed On:
	//**********************************************************************************************
	public static void skipTestCase(String TestDesc, boolean statusFlag) {

		//check the status flag of test step
		if(statusFlag) {
			//skip test
			throw new SkipException(TestDesc);
		}else {
			//do nothing
		}	
	}

}
