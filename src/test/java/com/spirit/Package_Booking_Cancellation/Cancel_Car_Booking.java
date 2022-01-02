package com.spirit.Package_Booking_Cancellation;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


public class Cancel_Car_Booking extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = "CarBookingCancellation")
    public void Cancel_Car_Booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case Cancel_Car_Booking " + platform + " Browser", true);
        }
        //open browser
        openBrowser( platform);

        final int CAR_PROVIDER_TAB  = 1;
        final String CARNECT_LOGIN  = "CarnetLogin";
        final String USER_NAME      = "Test";

        //open new tab
        JSExecuteUtil.openNewTabWithGivenURL(getDriver(), FileReaderManager.getInstance().getConfigReader().getCarProviderURL());

        //switch to car provider Tab
        TestUtil.switchToWindow(getDriver(),CAR_PROVIDER_TAB);

        //get login detail sof Carnect application
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(CARNECT_LOGIN);

        //Enter UserName
        pageObjectManager.getTriseptREZAgentPage().getCarnetUserNameTextBox().sendKeys(loginCredentialsData.userName);

        //Enter Password
        pageObjectManager.getTriseptREZAgentPage().getCarnetPasswordTextBox().sendKeys(loginCredentialsData.password);

        //Click on Sign In button
        pageObjectManager.getTriseptREZAgentPage().getCarnetSignInButton().click();

        //wait for Carnect Home Page appear
        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);

        //verify home page appear on carnect Application
        ValidationUtil.validateTestStep("User verify login to carnect application is successful",
                getDriver().findElements(By.xpath(pageObjectManager.getTriseptREZAgentPage().xpath_CarnetAvailabilitySearchLink)).size()>0);

        //Retrieve button
        pageObjectManager.getTriseptREZAgentPage().getCarnetRetrieveLink().click();
        System.out.println("User click on Retrieve button");
        WaitUtil.untilJavaScriptPageLoadComplete(getDriver());

        //Filtering By User Name
        pageObjectManager.getTriseptREZAgentPage().getCarnetRetrieveByFamilyNameText().click();
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getTriseptREZAgentPage().getCarnetRetrieveByFamilyNameText().sendKeys(USER_NAME);
        System.out.println("Filtering Test as Passenger's last name");
        WaitUtil.untilTimeCompleted(1200);

        //Filtering by Confirmed Status only
        pageObjectManager.getTriseptREZAgentPage().getCarnetRetrieveByReservationStateTextBox().click();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getTriseptREZAgentPage().getCarnetRetrieveByReservationStateText().click();
        System.out.println("User select on Confirmed option from the Reservation Status dropdown");

        //Filtering by Affiliate
        pageObjectManager.getTriseptREZAgentPage().getCarnetRetrieveByAffiliateTextBox().click();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getTriseptREZAgentPage().getCarnetRetrieveByAffiliateText().click();
        System.out.println("User select Spirit Airline API from Select Affiliate dropdown");

        //Click on Retrieve Reservation button
        pageObjectManager.getTriseptREZAgentPage().getCarnetRetrieveReservationSubmitButton().click();
        WaitUtil.untilPageLoadComplete(getDriver(),(long)10);
        WaitUtil.untilTimeCompleted(200);

            for (int i = 0; i < pageObjectManager.getTriseptREZAgentPage().getCarnetRetrievedReservationPanel().size(); i++) {
//                while (pageObjectManager.getTriseptREZAgentPage().getCarnetRetrievedReservationPanel().size()>=0) {
                if (pageObjectManager.getTriseptREZAgentPage().getCarnetRetrievedNameListedText().get(i).getText().contains("AUTOMATION")) {
                    System.out.println("Automation customer found");
                    String reservation_Code = pageObjectManager.getTriseptREZAgentPage().getCarnetRetrievedCarNumberText().get(0).getText();
                    System.out.println("Customer attempting to cancel " + reservation_Code);
                    pageObjectManager.getTriseptREZAgentPage().getCarnetRetrieveResultsPassengerReservationNameText().get(0).click();

                    WaitUtil.untilJavaScriptPageLoadComplete(getDriver());
                    WaitUtil.untilElementIsClickable(getDriver(), pageObjectManager.getTriseptREZAgentPage().getCarnetCancelReservationButton());
                    pageObjectManager.getTriseptREZAgentPage().getCarnetCancelReservationButton().click();

                    WaitUtil.untilElementIsClickable(getDriver(), pageObjectManager.getTriseptREZAgentPage().getCarnetConfirmYesButton());
                    WaitUtil.untilTimeCompleted(1200);
                    pageObjectManager.getTriseptREZAgentPage().getCarnetConfirmYesButton().click();

                    WaitUtil.untilElementIsClickable(getDriver(), getDriver().findElement(By.xpath("//p[contains(text(),'The reservation was successfully cancelled!')]")));
                    System.out.println("Validating the reservation " + reservation_Code + " was successfully cancelled");

                    WaitUtil.untilElementIsClickable(getDriver(), pageObjectManager.getTriseptREZAgentPage().getCarnetRetrieveButton());

                    //Retrieve button
                    pageObjectManager.getTriseptREZAgentPage().getCarnetRetrieveButton().click();
                    WaitUtil.untilPageLoadComplete(getDriver(), (long) 5);
                    WaitUtil.untilTimeCompleted(200);

                    //Submit new search
                    pageObjectManager.getTriseptREZAgentPage().getCarnetRetrieveReservationSubmitButton().click();
                    WaitUtil.untilPageLoadComplete(getDriver(), (long) 5);
                    WaitUtil.untilTimeCompleted(200);
                }
            }
//        }
    }
}


///********************TriseptREZAgentPage
//    public WebElement getCarnetRetrieveReservationSubmitButton() {
//        return btn_CarnetRetrieveReservationSubmit;
//    }
