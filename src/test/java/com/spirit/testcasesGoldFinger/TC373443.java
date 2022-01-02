package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373443
//Description:  003 - CP - Payment Declined - Flight + Car - Free Spirit Mastercard
//Created By : Anthony Cardona
//Created On : 27-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC373443 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"OutOfExecution","ActiveBug" , "BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "MasterCard" , "PaymentUI" , "Cars"})
    public void Payment_Declined_Flight_Car_Free_Spirit_Mastercard(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373443 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE = "English";

        final String LOGIN_ACCOUNT = "FSMCEmail";
        final String JOURNEY_TYPE = "Vacation";
        final String TRIP_TYPE = "Flight+Car";
        final String DEP_AIRPORTS = "AllLocation";
        final String DEP_AIRPORT_CODE = "FLL";
        final String ARR_AIRPORTS = "AllLocation";
        final String ARR_AIRPORT_CODE = "MCO";
        final String DEP_DATE = "101";
        final String ARR_DATE = "103";
        final String ADULT = "2";
        final String CHILD = "0";
        final String INFANT_LAP = "0";
        final String INFANT_SEAT = "0";
        final String DRIVER_AGE = "25+";

        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        final String DECLINED_VISA 	    = "DeclinedMaster";

        final String DECLINED_CARD_ERROR   = "Were sorry, an unknown error has occurred. Your card has not been charged. Please check that all payment information below matches what is printed on your card (including cardholder name) or try another card.";

//- Step 1: Access GoldFinger test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//Step 2: Login as a FS Mastercard holder member with a Declined Free Spirit Mastercard
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);

//Step 3: Go to Vacation tab select F+C
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

///Step 4: Create a DOM to DOM booking, 2 ADT, age 25+, outside 3 months and select search
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step 5: Under review your flights, make sure you have a departing and returning flight preselected
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Departing journey is selected ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getSelectedDepatureFlightBlockPanel()));

//Step 6: Under choose your car, book a car
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA" , "NA");
        ValidationUtil.validateTestStep("The Bundle and save pop-up is displayed" , pageObjectManager.getFlightAvailabilityPage().getBundleItButton().size() > 0);


//Step 7: Select Book It
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade("BookIt");

//Step 8: Enter all information for other passenger(s), select primary driver and continue
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();


        pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown().click();

        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(), "FSMC AUTOMATION");
        WaitUtil.untilPageLoadComplete(getDriver());

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //click on primary passenger checkbox
        pageObjectManager.getPassengerInfoPage().getPrimaryPassengerIstheContactPersonCheckBox().click();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//Step 9 & 10: Click on continue without bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//Step 11: Click on continue without selecting seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

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
