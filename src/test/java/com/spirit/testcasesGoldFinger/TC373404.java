package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373404
//Description: Task 27862: TC373404 - 007 - CP - Car Detail Content - Validate the Facade for Car Details on a booking with an Active Duty Military Passenger
// Created By: Gabriela
//Created On: 24-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373404 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Military", "NonStop", "BookIt", "CarryOn", "CheckedBags", "NoSeats", "Cars", "OptionalUI", "CheckInOptions", "Discover"})
    public void CP_Car_Detail_Content_Validate_the_Facade_for_Car_Details_on_a_booking_with_an_Active_Duty_Military_Passenger(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373404 under GoldFinger Suite on " + platform + " Browser", true);
        }
    //Home Page Constant Values
    final String LANGUAGE           = "English";
    final String JOURNEY_TYPE       = "Flight";
    final String TRIP_TYPE          = "RoundTrip";
    final String DEP_AIRPORTS       = "AllLocation";
    final String DEP_AIRPORT_CODE   = "FLL";
    final String ARR_AIRPORTS       = "AllLocation";
    final String ARR_AIRPORT_CODE   = "MCO";
    final String DEP_DATE           = "132";
    final String ARR_DATE           = "133";
    final String ADULT              = "1";
    final String CHILD              = "0";
    final String INFANT_LAP         = "0";
    final String INFANT_SEAT        = "0";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT         = "NonStop";
    final String ARR_Flight         = "NonStop";
    final String FARE_TYPE          = "Standard";
    final String UPGRADE_VALUE      = "BookIt";

    //Bags Page Constant Values
    final String DEP_BAGS           = "Carry_1|Checked_1";

    //Options page methods
    final String CAR_VERBAGE1           = "Vehicle Description";
    final String VEHICLE_DESCRIPTION1   = "Please Note";
    final String VEHICLE_DESCRIPTION2   = "A valid major credit card in the renter’s name must be presented with available credit in the deposit amount (if applicable). At select locations, major debit cards may be used to secure your rental. At most Airport Locations debit cards will be accepted to qualify for rental when the customer provides proof of return airline flight to coincide with the rental and two (2) valid forms of identification. For specific information related to debit card acceptance or other policies, contact your rental location.";
    final String VEHICLE_DESCRIPTION3   = "Debit cards may not be accepted at time of rental in the following cities and surrounding areas, please contact the local office for details: New York Metropolitan (New York, New Jersey, Connecticut), Hartford, Connecticut; Philadelphia, Pennsylvania; Boston, Massachusetts; Manchester, New Hampshire; and Detroit, Michigan.";
    final String MINIMUM_AGE_NOTE       = "In certain states drivers must be 25 years of age or older. When allowed, drivers under the age of 25 may be subject to additional surcharges payable directly to the rental car supplier at the time of rental.";
    final String LOCATION_HOURS         = "Location hours";

    final String OPTIONS_PAGE           = "/book/options";
    final String OPTIONS_VALUE		    = "CheckInOption:MobileFree";

    //Payment Page Constant values
    final String TRAVEL_GUARD 		= "NotRequired";
    final String CARD_DETAIL 		= "DiscoverCard2";
    final String PAYMENT_PAGE       = "/book/payment";

    //Confirmation Page Constant Values
    final String CONFIRMATION_URL   = "/book/confirmation";


//- Step 1: Open GoldFinger environment on consumer portal
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        /*** Home Page **/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Create a RT DOM | 1 ADT (military) | 1 checked bag 1 carry-on | no seats | 3 months out and click on Search flights
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Select flights and click continue
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 4: If bundle pop up is displayed, click on Book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 5: Fill out passenger info, check the Active Duty U.S. Military Personnel and continue
        pageMethodManager.getPassengerInfoMethods().fillMilitaryPassengerMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 6: Select 1 carry-on and 1 checked bag and continue
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

//- Step 7: Continue without seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 8: Search for a car and click on "ADD CAR"
        WaitUtil.untilPageLoadComplete(getDriver());
        String carModel = pageObjectManager.getCarPage().getCarCardNameText().get(0).getText();
        String carSeat = pageObjectManager.getCarPage().getCarCardSeatsText().get(0).getText();

        //pageObjectManager.getCarPage().getCarsCarouselAddCarButton().get(0).click();
        pageObjectManager.getCarPage().getCarCardNameText().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 9: Vaildate car supplier name, car make and model, car capacity on car pop up
        ValidationUtil.validateTestStep("Validating car capacity information",
              carSeat, pageObjectManager.getCarPage().getCarPopUpSeatText().getText());

        ValidationUtil.validateTestStep("Validating car capacity information",
                carModel,pageObjectManager.getCarPage().getCarPopUpCompanyMakeAndModelText().getText());

//- Step 10: Validate Vehicle Description:
        ValidationUtil.validateTestStep("Verify "+CAR_VERBAGE1+" is displayed on Car Modal PopUp",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab().getText(),CAR_VERBAGE1);

        //verify the vehicle description under more infor link
        ValidationUtil.validateTestStep("Validating first Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(0).getText(),VEHICLE_DESCRIPTION1);

        ValidationUtil.validateTestStep("Validating second Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(1).getText(),VEHICLE_DESCRIPTION2);

        ValidationUtil.validateTestStep("Validating third Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(3).getText(),VEHICLE_DESCRIPTION3);

//- Step 11: Validate Policies:
        pageObjectManager.getCarPage().getCarPageMoreInfoPoliciesLink().click();
        WaitUtil.untilTimeCompleted(3000);

//        ValidationUtil.validateTestStep("What's Included Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesWhatsIncludesText().getText(),"What's Included");

        ValidationUtil.validateTestStep("Rental inclusions Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalInclusionsText().getText(),"Rental inclusions");

        ValidationUtil.validateTestStep("Rental exclusions Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalExclusionsText().getText(),"Rental exclusions");

        ValidationUtil.validateTestStep("Rental rules Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalRulesText().getText(),"Rental rules");

        ValidationUtil.validateTestStep("Child Seats Required by Law Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(0).getText(),"Child Seats Required by Law");

        ValidationUtil.validateTestStep("Child Seats Required by Law \"Yes\" Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(1).getText(),"Yes");

        ValidationUtil.validateTestStep("Check with the rental counter or state to verify requirements Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(2).getText(),
                "Check with the rental counter or state to verify requirements.");

        ValidationUtil.validateTestStep("Minimum Age to Rent Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().get(0).getText(),
                "Minimum Age to Rent");

        ValidationUtil.validateTestStep("Minimum Age to Rent \"21\" Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().get(1).getText(), "21");

        ValidationUtil.validateTestStep("Minimum Age to Rent Note Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().get(2).getText(), MINIMUM_AGE_NOTE);

        ValidationUtil.validateTestStep("Refund for Unused Rental Days/Hours Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRefundForUnusedText().get(0).getText(), "Refund for Unused Rental Days/Hours ");

        ValidationUtil.validateTestStep("Refund for Unused Rental Days/Hours \"NO\" Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRefundForUnusedText().get(1).getText(), "No");

        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().get(0).getText(), "Valid Driver's License Required");

        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().get(1).getText(), "Yes");

        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().get(2).getText(), "The lead name on the reservation must match the primary driver and credit card holder.");

//- Step 12: Validate location hours, address, and phone number is shown under the Locations tab
        pageObjectManager.getCarPage().getCarPopUpLocationsTab().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Location Hours is displayed under Location tab",
                pageObjectManager.getCarPage().getCarPopUpLocationsTabLocationHoursText().getText(),LOCATION_HOURS);

       //TODO: Car information received from Carnect verification method need to be implemented

//- Step 13: Select driver from drop down menu and click ADD CAR
       // primary driver selected by default
        //pageObjectManager.getCarPage().getBookCarButton().get(0).click();
        pageObjectManager.getCarPage().getCarPopUpAddCar().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(OPTIONS_PAGE));

       // pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();

//- Step 14: Select check-in free and continue
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(PAYMENT_PAGE));

//- Step 15: Fill out proper payment information and complete booking
        pageMethodManager.getPaymentPageMethods().verifyMilitaryPassengerLoginDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
//
//        /*** Confirmation Page**/
//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

//        ValidationUtil.validateTestStep("The user redirected to the correct page",
//                getDriver().getCurrentUrl(),(CONFIRMATION_URL));
//
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();
    }
}

//*******************************************************************
//*******************Cars Container Options Page*********************
//*******************************************************************
//public final String xpath_CarsCarouselAddCarButton = "//app-car//app-ancillary-item//button[contains(text(),'Add Car') or contains(text(),'Añadir auto')]";
//    @FindBy(xpath = xpath_CarsCarouselAddCarButton)
//    private List<WebElement> btn_CarsCarouselAddCar;
//
//public List<WebElement> getCarsCarouselAddCarButton(){return btn_CarsCarouselAddCar;}

//*******************************************************************
//************Pick Car Pop-Up Options Page***************************
//*******************************************************************
//public final String xpath_CarPopUpCompanyMakeAndModelText = "//div[contains(@class,'modal-header')]/div/div[2]/h3";
//    @FindBy(xpath = xpath_CarPopUpCompanyMakeAndModelText)
//    private WebElement txt_CarPopUpCompanyMakeAndModel;

//public final String xpath_CarPopUpLocationsTabLocationHoursText = "//div[@id='locations-panel']//div[contains(text(),'Location hours') or contains(text(),'Horas de la Ubicación')]";
//    @FindBy(xpath=xpath_CarPopUpLocationsTabLocationHoursText)
//    private WebElement txt_CarPopUpLocationsTabLocationHours;
//
////public WebElement getCarPopUpCompanyMakeAndModelText(){return txt_CarPopUpCompanyMakeAndModel;}
//public WebElement getCarPopUpLocationsTabLocationHoursText() {return txt_CarPopUpLocationsTabLocationHours;}
