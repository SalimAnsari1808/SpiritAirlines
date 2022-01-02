package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.*;
import com.spirit.dataType.MemberEnrollmentData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

// **********************************************************************************************
// Test Case ID: TC91443
// TestCase   : CP_BP_Payment Page_9DFC_saved card update link
// Description: Validate that 9FC member is able to request miles for previously flown flight
// Created By : Alex Rodriguez
// Created On : 9-April-2019
// Reviewed By: Salim Ansari
// Reviewed On: 18-Apr-2019
// **********************************************************************************************

public class TC91443 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Within21Days" , "Adult" ,
            "NineDFC" , "Connecting" , "NoBags" , "BookIt" ,"CheckInOptions", "NoSeats" , "MasterCard", "PaymentUI" , "AccountProfileUI","OutOfExecution"})
    public void CP_BP_Payment_Page_9DFC_saved_card_update_link(@Optional("NA") String platform) {
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91443 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String LOGIN_ACCOUNT 	        = "AccountNineDFCEmail";
        final String JOURNEY_TYPE 		    = "Flight";
        final String TRIP_TYPE 			    = "RoundTrip";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LGA";
        final String DEP_DATE 			    = "10";
        final String ARR_DATE 			    = "13";
        final String ADULTS				    = "1";
        final String CHILDREN			    = "0";
        final String INFANT_LAP			    = "0";
        final String INFANT_SEAT		    = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		    = "AutoSelect";
        final String ARR_Flight 		    = "AutoSelect";
        final String FARE_TYPE			    = "Member";
        final String UPGRADE_VALUE	  	    = "BookIt";

        //Option Page Constant Values
        final String OPTIONS_VALUE		    = "CheckInOption:MobileFree";

        //Payment page Constant values
        final String TRAVEL_GUARD		    = "NotRequired";
        final String CARD_TYPE			    = "MasterCard";


        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seat Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Option method
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //***************************************
        //*********Navigate to Payment Page******
        //***************************************

        //Payment Page Method
//Step 3
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);

//Step 4

        for(int counter=0;counter<5;counter++){
            try{
                //click on update card link
                pageObjectManager.getPaymentPage().getPaymentSectionUpdateCreditCardLink().click();

                //wait for 2 sec
                WaitUtil.untilTimeCompleted(2000);

                break;
            }catch(Exception e){
                if(TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getPaymentSectionCancelChangesButton())){
                    break;
                }
            }
        }

        //get member data
        MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("ContactTab");

        //clear and enter new address
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPaymentPage().getAddressTextbox());
        pageObjectManager.getPaymentPage().getAddressTextbox().sendKeys("Updated" + memberEnrollmentData.address1);

        //fill State
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPaymentPage().getStateDropdown(),memberEnrollmentData.state);

        //clear and enter city
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPaymentPage().getCityTextbox());
        pageObjectManager.getPaymentPage().getCityTextbox().sendKeys(memberEnrollmentData.city);

        //clear and enter zip
        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getPaymentPage().getZipCodeTextbox());
        pageObjectManager.getPaymentPage().getZipCodeTextbox().sendKeys(memberEnrollmentData.zipCode);

        //wait for 2sec
        WaitUtil.untilTimeCompleted(2000);

        //click on update button
        pageObjectManager.getPaymentPage().getPaymentSectionUpdateButton().click();

        ValidationUtil.validateTestStep("User updated Card Billing information on Payment Page", true);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Reach to Confirmation page
        pageMethodManager.getPaymentPageMethods().fillCVVDetailsModifyPath(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //Close Rocket popup and Navigate to Account profile
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //navigate to my account drop down menu
        WaitUtil.untilPageLoadComplete(getDriver(), (long) 300);
        pageObjectManager.getHeader().getUserDropDown().click();

        //Click on my account
        pageObjectManager.getHeader().getMyAccountUserLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //click on account profile Billing information
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getAccountProfilePage().getLeftMenuBillingInformationLink());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("User Navigated to Account Profile Billing Information", true);
        //********************************************************
        //************Validate Card info was Saved****************
        //********************************************************
        String newBillingAddress = pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardBillingAddressText().getText();

        ValidationUtil.validateTestStep("User validates new billing address was updated on the Billing Information page", newBillingAddress, "Updated");

        //********************************************************
        //*********Re-enter old address for rerun purposes********
        //********************************************************
        pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardEditLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getAccountProfilePage().getBillingInformationUseSameAddressLabel().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getAccountProfilePage().getBillingInformationBillingAddressTextBox());
        pageObjectManager.getAccountProfilePage().getBillingInformationBillingAddressTextBox().sendKeys(memberEnrollmentData.address1);

        pageObjectManager.getAccountProfilePage().getBillingInformationSaveButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("User Enter the correct Billing Information on Account Profile Page", true);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        String oldBillingAddress = pageObjectManager.getAccountProfilePage().getBillingInformationPrimaryCardBillingAddressText().getText();

        //Verify modify address is removed from billing address
       ValidationUtil.validateTestStep("User validates old billing address was updated for retesting purpose", !oldBillingAddress.contains("Updated"));
    }
}
