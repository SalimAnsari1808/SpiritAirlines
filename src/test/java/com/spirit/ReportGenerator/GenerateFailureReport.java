package com.spirit.ReportGenerator;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ExcelUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class GenerateFailureReport extends BaseClass {
    /******************************************************************************
     ***********************Set-Up / Configurations********************************
     ******************************************************************************/

    // 1) input the Name og Html file from jenkins; example: "Nav01_16_07_2019_04_58_42.html"
    String htmlReportFileName = "Nav01_02_10_2019_08_23_54.html";
    // 2) Make sure that a blank .xlsx file named "failure_report" is in the /src/main/resources/excelSpreadSheets/ directory
    String FileName = "failure_report.xlsx";
    // 3) Make sure that the first sheet on the failure_report spreadsheet is called "Sheet1"

    /******************************************************************************
     *************************Set-Up / Configurations*******************************
     ******************************************************************************/



    @Parameters({"platform"})
    @Test
    public void Generate_Failure_Report(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("", true);
        }
        openBrowser(platform);
        getDriver().get("C:/Users/anthonycar/Downloads/Nav01_26_12_2019_09_31_20.html");
        WaitUtil.untilTimeCompleted(3000);
//        //username
//        getDriver().findElement(By.id("j_username")).sendKeys("admin");
//        //password
//        getDriver().findElement(By.name("j_password")).sendKeys("Spirit123");
//        getDriver().findElement(By.xpath("//input[@value='Sign in']")).click();
//        //Find the Job
//        getDriver().findElement(By.xpath("(//tr[@id='job_EPIC 1.0 Execution']//a)[2]")).click();
//        WaitUtil.untilTimeCompleted(1200);
//        //click on workspace
//        getDriver().findElement(By.xpath("//tbody//a[contains(text(),'Workspace')]")).click();
//        WaitUtil.untilTimeCompleted(1200);
//        //click on Report
//        getDriver().findElement(By.xpath("//tbody//a[contains(text(),'reports')]")).click();
//        WaitUtil.untilTimeCompleted(1200);
        //Open report in new tab and switch to the tab
//        JSExecuteUtil.openNewTabWithGivenURL(getDriver(), getDriver().findElement(By.xpath("//table[@class='fileList']//tbody//a[contains(text(),'" + htmlReportFileName + "')]")).getAttribute("href"));
//        TestUtil.switchToWindow(getDriver(), 1);
//        System.out.println(getDriver().getCurrentUrl());
        WaitUtil.untilTimeCompleted(2000);
        //Click on Status
        getDriver().findElement(By.xpath("//section[@id='controls']//a[@data-activates='tests-toggle']")).click();
        WaitUtil.untilTimeCompleted(2000);
        //Click on Failed Status Search
        getDriver().findElement(By.xpath("//section[@id='controls']//ul//li//a[contains(text(),'Fail')]")).click();
        List<WebElement> failedTests = getDriver().findElements(By.xpath("//li[@class='test fail displayed']"));
        ExcelUtil report = new ExcelUtil(FileName);
        WaitUtil.untilTimeCompleted(2000);
        //Set the columns in the spreadsheet
        report.addColumn("Sheet1", "Test_Name");
        report.addColumn("Sheet1", "Test_ID");
        report.addColumn("Sheet1", "Browser");
        report.addColumn("Sheet1", "Fail_Reason" );
        report.addColumn("Sheet1", "Code_Block");
        for (int i = 0; i <= failedTests.size(); i++)
        {
            WaitUtil.untilTimeCompleted(1200);
            report.setCellData("Sheet1", "Test_Name"    , i+2,    Testname());
            report.setCellData("Sheet1", "Test_ID"      , i+2,    testCase());
            report.setCellData("Sheet1", "Browser"      , i+2,    browserType());
            report.setCellData("Sheet1", "Fail_Reason"  , i+2,    firstFailStep());
            report.setCellData("Sheet1", "Code_Block"   , i+2,    codeError());
            System.out.println(i);
            failedTests.get(i).click();
        }
    }
    public String Testname() {
        return getDriver().findElement(By.xpath("(//div[@class='subview-right left'])[1]//h5")).getText().trim();
    }
    public String testCase() {
        String FirstStep = getDriver().findElement(By.xpath("((//div[@class='subview-right left'])[1]//div[@class='test-steps']//td[@class='step-details'])[1]")).getText().trim();
        return FirstStep.substring(FirstStep.indexOf("ID"), FirstStep.indexOf("ID") + 11);
    }
    public String browserType() {
        String FirstStep = getDriver().findElement(By.xpath("((//div[@class='subview-right left'])[1]//div[@class='test-steps']//td[@class='step-details'])[1]")).getText().trim();
        return FirstStep.substring(FirstStep.indexOf("on ") + 3, FirstStep.indexOf("Browser") - 1);
    }
    public String firstFailStep() {
        return getDriver().findElement(By.xpath("((//div[@class='subview-right left'])[1]//div[@class='test-steps']//tr[@status='fail'])[1]//td[@class='step-details']")).getText().trim();
    }
    public String codeError() {
        String value = null;
        try
        {
            value = getDriver().findElement(By.xpath("((//div[@class='subview-right left'])[1]//div[@class='test-steps']//tr[@status='fail'])//td//textarea")).getText().trim();
        }
        catch (Exception e)
        {
            value = getDriver().findElement(By.xpath("((//div[@class='subview-right left'])[1]//div[@class='test-steps']//tr[@status='fail'])[1]//td[@class='step-details']")).getText().trim();
        }
        return value;
    }
}
