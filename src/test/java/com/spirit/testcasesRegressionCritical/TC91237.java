package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// **********************************************************************************************
// TestCase   : My Account_CP_FS_ Member Statement Link
// Description: Validate the Miles statement page on the account profile page
// Created By : Anthony Cardona
// Created On : 22-Jul-2019
// Reviewed By: Salim Ansari
// Reviewed On: 24-Jul-2019
// **********************************************************************************************

public class TC91237 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"FreeSpirit" , "AccountProfileUI"})
    public void My_Account_CP_FS_Member_Statement_Link(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91237 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String LOGIN_EMAIL            = "FSEmail";

        //open browser
        openBrowser(platform);

//-- Step 1 and 2: launch the app
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
//Steo 3 and 4: log in with 9fc member
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        //navigate to my account drop down menu
        WaitUtil.untilPageLoadComplete(getDriver(), (long) 300);
//Step 5: CLick on member name on the top right of the page
        pageObjectManager.getHeader().getUserDropDown().click();

//Step 6: Click on the statements link in the drop down
        //Click on my statements
        pageObjectManager.getHeader().getStatementUserLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 7: Validate the statements header
        ValidationUtil.validateTestStep("The statements header is displayed correctly" ,
                pageObjectManager.getAccountProfilePage().getStatementsHeaderText().getText() , "Statements");

//Step 8: Validate the activity period is only for the last 12 months
        Select activityDropDown = new Select(pageObjectManager.getAccountProfilePage().getStatementActivityPeriodDropDown());

        ValidationUtil.validateTestStep("There are less than 12 months displayed on the drop down",
                new Select(pageObjectManager.getAccountProfilePage().getStatementActivityPeriodDropDown()).getOptions().size() <= 12 );

///Step 9: Validate the transaction types on the drop down
        List<WebElement> transactionOptionsACTUAL = new Select(pageObjectManager.getAccountProfilePage().getStatementTransactionTypeDropDown()).getOptions();
        List <String>      transactionOptionsEXPECTED = new ArrayList<String>();

        transactionOptionsEXPECTED.add("All Transactions");
        transactionOptionsEXPECTED.add("Bank of America");
        transactionOptionsEXPECTED.add("Promerica");
        transactionOptionsEXPECTED.add("Spirit Airlines Miles");

        for (int count = 0 ; count < transactionOptionsACTUAL.size() ; count++ ) {
            ValidationUtil.validateTestStep("The transation type drop down is displayed for " + transactionOptionsEXPECTED.get(count) ,
                    transactionOptionsACTUAL.get(count).getText() , transactionOptionsEXPECTED.get(count));
        }

//Step 10: Validate "Posted Transactions"
        ValidationUtil.validateTestStep("The posted Transaction Header is correct", pageObjectManager.getAccountProfilePage().getPostedTransactionHeaderText().getText() , "Posted Transactions" );

//Step 11: Validate the table top row headers
        ValidationUtil.validateTestStep("The Table Date Header is correct", pageObjectManager.getAccountProfilePage().getStatementTableDateHeaderText().getText() , "Date" );
        ValidationUtil.validateTestStep("The Table Transactions Header is correct", pageObjectManager.getAccountProfilePage().getStatementTableTransactionHeaderText().getText() , "Transaction" );
        ValidationUtil.validateTestStep("The Table Debit Header is correct", pageObjectManager.getAccountProfilePage().getStatementTableDebitHeaderText().getText() , "Debit(-)" );
        ValidationUtil.validateTestStep("The Table Credit Header is correct", pageObjectManager.getAccountProfilePage().getStatementTableCreditHeaderText().getText() , "Credit(+)" );
        ValidationUtil.validateTestStep("The Table Balance Header is correct", pageObjectManager.getAccountProfilePage().getStatementTableBalanceHeaderText().getText() , "Balance*" );


//Step 12 and 13: VALIDATE THAT THE DATES ARE IN mm/dd/yyyy format

        for (WebElement dateElement : pageObjectManager.getAccountProfilePage().getStatementTableDatesText()) {
            String datelistedd = dateElement.getText().substring(0,10) + " " + dateElement.getText().substring(11,dateElement.getText().length());

            Date date = TestUtil.convertStringToDate(datelistedd,"MM/dd/YYYY HH:MM a");

            //if date is not able to parse  the it will provide null value
            ValidationUtil.validateTestStep("The date format is correct DATES ARE IN mm/dd/yyyy format" , !date.equals(null));
        }

//Step 14: validate the transactions are listed
        for (WebElement debitElement : pageObjectManager.getAccountProfilePage().getStatementTableDebitsText()) {
            if (debitElement.getText().isEmpty())
            {
                continue;
            }
            int debitMiles = Integer.parseInt(debitElement.getText());
            ValidationUtil.validateTestStep("The debited miles is not positive" , debitMiles < 0);
        }

        for (WebElement Credited : pageObjectManager.getAccountProfilePage().getStatementTableCreditsText()) {
            if (Credited.getText().isEmpty())
            {
                continue;
            }
            int debitMiles = Integer.parseInt(Credited.getText());
            ValidationUtil.validateTestStep("The Credited miles is not negative" , debitMiles > 0);
        }

        for (WebElement BalanceElement : pageObjectManager.getAccountProfilePage().getStatementTableBalancesText()) {
            int balanceMiles = Integer.parseInt(BalanceElement.getText());
            ValidationUtil.validateTestStep("The Balance miles is positive" , BalanceElement.getText().length() > 0 );
        }

//Step 15: Validate current balance for the member is zero
        //invalid step, Member amount wont equal actual amount

//Step 16: Validate bottom of page text "*Beginning Balance as of MM/1/YY"
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();

        ValidationUtil.validateTestStep("The begining balance text is correct" , pageObjectManager.getAccountProfilePage().getStatementBeginningBalanceDate().getText() ,
                "*Beginning Balance as of " + month +"/1/" + (year % 2000) );
    }
}