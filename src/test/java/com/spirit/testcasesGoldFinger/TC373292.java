package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC373292
//Description:  CP - Payment Declined - Flight + Car - Saved Form of Payment
//Created By : Sunny Sok
//Created On : 20-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373292 extends BaseClass{
    @Parameters({"platform"})
    @Test (groups = {"OutOfExecution","BookPath" , "RoundTrip" , "DomesticDomestic" , "FlightCar" , "Outside21Days" , "Adult" , "Guest" ,
            "NonStop" , "BookIt" , "NoBags" , "NoSeats" , "CheckInOptions" , "VisaCard" , "PaymentUI"})
    public void CP_Payment_Declined_Flight_Car_Saved_Form_of_Payment(@Optional("NA") String platform) {
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373292 under GoldFinger Suite on " + platform + " Browser" , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_ACCOUNT      = "InvalidSavedCard9fc";
        final String JOURNEY_TYPE 		= "Vacation";
        final String TRIP_TYPE 			= "flight+car";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LGA";
        final String DEP_DATE 			= "90";
        final String ARR_DATE 			= "100";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String DRIVERS_AGE        = "25+";

        //Flight Availability Page Constant Values
        final String UPGRADE_FARE		= "BookIt";

        //Options Page constant values
        final String OPTION_VALUE 	    = "CheckInOption:MobileFree";

        //Payment Page constant values
        final String TRAVEL_GUARD       = "NotRequired";
        final String INVALID_CARD       = "XXXXXXXXXXXX1198";
        final String SECURITY_CODE      = "123";

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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVERS_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        //Recording Departing Journey Information
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();
        WaitUtil.untilTimeCompleted(2500);

        //Declaring Lists to store flight info
        WaitUtil.untilTimeCompleted(1000);
        List<String> depCityName    = new ArrayList<>();
        List<String> arrCityName    = new ArrayList<>();
        List<String> depTime        = new ArrayList<>();
        List<String> arrTime        = new ArrayList<>();
        List<String> nkInfo         = new ArrayList<>();

        //Storing Flight duration
        String depDurationInfo = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightDurationTimeText().get(0).getText();

        //Storing Departure Cities Name for 1st journey
        depCityName.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText().trim());

        //Storing Arrival Cities Name for 1st journey
        arrCityName.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText().trim());

        //Storing Departure Cities Time for 1st journey
        depTime.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText().get(0).getText().trim());

        //Storing Arrival Cities Time for 1st journey
        arrTime.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText().get(0).getText().trim());

        //Storing NK Number for 1st journey
        nkInfo.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().replace("Flight ","").replace(" ",""));

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

        //Recording Departing Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSelectedReturningFlightNatureButton().get(0).click();
        WaitUtil.untilTimeCompleted(2500);

        //Storing Flight duration
        String retDurationInfo = pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightDurationTimeText().get(0).getText();

        //Storing Departure Cities Name for 1st journey
        depCityName.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText().get(0).getText().trim());

        //Storing Arrival Cities Name for 1st journey
        arrCityName.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText().get(0).getText().trim());

        //Storing Departure Cities Time for 1st journey
        depTime.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText().get(0).getText().trim());

        //Storing Arrival Cities Time for 1st journey
        arrTime.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText().get(0).getText().trim());

        //Storing NK Number for 1st journey
        nkInfo.add(pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().get(0).getText().replace("Flight ","").replace(" ",""));

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Na", "Na");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //wait until Bags page appear on web
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page method
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //options
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Constant Values to validate
        final String PAYMENT_URL            = "/payment";
        final String DECLINED_CARD_ERROR    = "Were sorry, an unknown error has occurred. Your card has not been charged. Please check that all payment information below matches what is printed on your card (including cardholder name) or try another card.";

        ValidationUtil.validateTestStep("User verify Navigated to Payment page", getDriver().getCurrentUrl(),PAYMENT_URL);

        //Storing flight info for validation
        List<String> depCityNamePaymentNew = getDisplayedText(pageObjectManager.getPaymentPage().getDepartingFlightCityNameText());
        List<String> arrCityNamePaymentNew = getDisplayedText(pageObjectManager.getPaymentPage().getArriveFlightCityNameText());

        //Validating departing flight info
        ValidationUtil.validateTestStep("Validating Cities and Time ", depCityNamePaymentNew.get(0), depCityName.get(0) + " " + depTime.get(0));

        //Validating Arrival info displayed
        ValidationUtil.validateTestStep("Validating Cities and Time Arriving", arrCityNamePaymentNew.get(0), arrCityName.get(0) + " " + arrTime.get(0));

        //Validating departing flight info for return
        ValidationUtil.validateTestStep("Validating Cities and Time ", depCityNamePaymentNew.get(1), depCityName.get(1) + " " + depTime.get(1));

        //Validating Arrival info displayed for return
        ValidationUtil.validateTestStep("Validating Cities and Time Arriving", arrCityNamePaymentNew.get(1), arrCityName.get(1) + " " + arrTime.get(1));

        //Click on Flight details chevron
        pageObjectManager.getPaymentPage().getYourItineraryArrowIcon().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

        //Storing Flight Number information and Duration for validation
        List<String> nk = getDisplayedText(pageObjectManager.getPaymentPage().getFlightSectionFlightNumberText());

        //Validating Flight Number information and Duration
        ValidationUtil.validateTestStep("Validating NK time right information", nk.get(0),nkInfo.get(0));

        ValidationUtil.validateTestStep("Validating right Duration ifo is displayed ",
                pageObjectManager.getPaymentPage().getDurationTimeText().get(0).getText(),depDurationInfo);

        //Validating flight number and Duration for return
        ValidationUtil.validateTestStep("Validating NK time right information", nk.get(1),nkInfo.get(1));

        ValidationUtil.validateTestStep("Validating right Duration ifo is displayed ",
                pageObjectManager.getPaymentPage().getDurationTimeText().get(1).getText(),retDurationInfo);

        //verify Car selected
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();

        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        WaitUtil.untilTimeCompleted(2000);

        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPaymentPage().getSelectCardDropDown(), INVALID_CARD);
        pageObjectManager.getMemberEnrollmentPage().getSecurityCodeTextBox().sendKeys(SECURITY_CODE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        try {
            WaitUtil.untilPageLoadComplete(getDriver());

            String cardError = pageObjectManager.getCommon().getIBlockVerbiageText().getText();
            cardError = cardError.replaceAll("[^\\p{ASCII}]", "");
            ValidationUtil.validateTestStep("User Verify error message for Hot Card", cardError, DECLINED_CARD_ERROR);

            pageObjectManager.getCommon().getIBlockCloseButton().click();
            WaitUtil.untilTimeCompleted(1000);
        }//This catch block will catch any Validation/Assertion errors encountered after Payment and still cancel reservations
        catch(AssertionError fail)
        {
            pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
            pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
            pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();

            ValidationUtil.validateTestStep("Test case failed after Payment: " + fail.getMessage() , false );
        }

    }

    private List<String> getDisplayedText(List<WebElement> elementList){

        ArrayList<String> arrayList = new ArrayList<>();//empty

        for (WebElement element:elementList) {
            if(element.isDisplayed()){

                arrayList.add(element.getText().trim());
            }
        }
        return arrayList;
    }
}