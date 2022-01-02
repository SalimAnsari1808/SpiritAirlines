package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373442
//Description:  002 - CP - Payment Declined - Car Upsell on Booking Path - Free Spirit Mastercard
//Created By : Anthony Cardona
//Created On : 29-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373442 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"OutOfExecution", "ActiveBug" , "BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "PaymentUI" , "Cars"})
    public void Payment_Declined_Flight_Car_Free_Spirit_Mastercard(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373442 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE = "English";

        final String LOGIN_ACCOUNT  = "FSMCEmail";
        final String JOURNEY_TYPE   = "Flight";
        final String TRIP_TYPE      = "RoundTrip";
        final String DEP_AIRPORTS   = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORTS   = "AllLocation";
        final String ARR_AIRPORT_CODE = "MCO";
        final String DEP_DATE       = "132";
        final String ARR_DATE       = "133";
        final String ADULT          = "1";
        final String CHILD          = "0";
        final String INFANT_LAP     = "0";
        final String INFANT_SEAT    = "0";

        final String DEP_FLIGHT     = "NonStop";
        final String RET_FLIGHT     = "NonStop";


        final String FARE_TYPE      = "Standard";

        final String UPGRADE_VALUE  = "BookIt";

        final String OPTIONS_VALUE	= "CheckInOption:MobileFree";

        final String DECLINED_VISA 	= "DeclinedMaster";

        final String DECLINED_CARD_ERROR   = "Were sorry, an unknown error has occurred. Your card has not been charged. Please check that all payment information below matches what is printed on your card (including cardholder name) or try another card.";

//- Step 1: Access GoldFinger test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//Step 2: Login as a FS Mastercard holder member with a Declined Free Spirit Mastercard
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

///Step 3: Create a RT DOM booking 1 ADT - outside 3 months and select search
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step 4: Choose any flight(s) and continue
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 5: Click on BOOK IT
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 6: Fill out customer info
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 7 & 8: Click on Continue without bags & Click on I dont need bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 9: click on Continue without selecting seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 10 and 11: Validate the hotels section is not displayed on the Options page; Select Primary driver and then click ADD CAR
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getCarPageMethods().selectCarOnOptionPage("NA" , "NA");
//        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(), "FSMC AUTOMATION");
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 12: Scroll down and select I'll check in at Spirit/Mobile for free and continue with booking
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//Step 13: On payment page, verify flight and car info are correct, enter declined Mastercard credit card and book reservation
        pageMethodManager.getPaymentPageMethods().selectTravelGuard("NotRequired");
        WaitUtil.untilTimeCompleted(2000);

        if (TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getSelectCardDropDown()))
        {
            TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPaymentPage().getSelectCardDropDown(), "Use Another Card");
            WaitUtil.untilTimeCompleted(2000);
        }
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(DECLINED_VISA);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        WaitUtil.untilPageLoadComplete(getDriver());

        String cardError = pageObjectManager.getCommon().getIBlockVerbiageText().getText();
        cardError = cardError.replaceAll("[^\\p{ASCII}]", "");
        ValidationUtil.validateTestStep("User Verify error message for Hot Card", cardError, DECLINED_CARD_ERROR);

        pageObjectManager.getCommon().getIBlockCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);
    }

    /*
    Cannot Automate Steps 14- 21
     */
}
