package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test CaseID: TC373828
//Title      : 002 - CP - Car Verbiage - Alamo - Flight + Hotel + Car - Validate correct verbiage displays 5 ADT
//Created By : Kartik Chauhan
//Created On : 08-Nov-2019
//Reviewed By: Salim Ansari
//Reviewed On: 18-Nov-2019
//**********************************************************************************************

public class TC373828 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups = {"BookPath","RoundTrip","FlightHotelCar","Guest","DomesticDomestic","WithIn21Days","Adult","FlightAvailabilityUI",
                    "OutOfExecution", "CarsUI"})
    public void CP_Car_Verbiage_Dollar_Flight_Hotel_Car_Validate_correct_verbiage_displays(@Optional("NA") String platform) {

        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373828 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Reservation Credit Path Constant variables
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "109";
        final String ADULTS             = "5";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";
        final String HOTELROOM          = "2 Rooms";

        //
        final String CAR_COMPANY        = "ALAMO";
        final String MINIMUM_AGE_NOTE   = "In certain states drivers must be 25 years of age or older. When allowed, drivers under the age of 25 may be subject to additional surcharges payable directly to the rental car supplier at the time of rental.";
        final String CAR_VERBAGE1       = "Vehicle Description";
        final String CAR_VERBAGE2       = "Please Note";
        final String CAR_VERBAGE3       = "A valid major credit card in the renter’s name must be presented with available credit in the deposit amount (if applicable). When renting in the US, Debit/Check cards may be accepted for customers with proof they have deplaned or disembarked at the rental location. Customers must have proof of a ticketed return or outbound trip from the location where the vehicle will be returned on an airline, cruise ship or train." +
                "A valid driver’s license is required to rent. ALAMO may conduct a review of your driver's license status and driving record. ALAMO can deny rental based on your driving history. Car make or model is not guaranteed and a similar or larger car may be substituted. Please check with the rental car facility in regards to any additional restrictions or optional services. Unused days of rental are not eligible for refund. Additional rental days may require additional payment upon return of vehicle.";

        //open browser
        openBrowser(platform);
//Step--1
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
//Step--2/3/4
        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectHotelRoom(HOTELROOM);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step--4
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("MGM","NA");

//Step5-6
        pageMethodManager.getCarPageMethods().clickCarMoreLinkPage(CAR_COMPANY, "NA");
        /////////////////////////////ALAMO cars are not available*************************

//************

        ValidationUtil.validateTestStep("Verify "+CAR_VERBAGE1+" is displayed on Car Modal PopUp",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab().getText(),CAR_VERBAGE1);

//        ValidationUtil.validateTestStep("Verify "+CAR_VERBAGE1+" is displayed on Car Modal PopUp",
//                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().getText(),CAR_VERBAGE2);
//
//        ValidationUtil.validateTestStep("Verify "+CAR_VERBAGE1+" is displayed on Car Modal PopUp",
//                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().getText(),CAR_VERBAGE3);

        pageObjectManager.getCarPage().getCarPopUpPoliciesTab().click();

        WaitUtil.untilTimeCompleted(2000);

//        ValidationUtil.validateTestStep("What's Included Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesWhatsIncludesText().getText(),"What's Included");

        ValidationUtil.validateTestStep("Rental inclusions Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalInclusionsText().getText(),"Rental inclusions");

        ValidationUtil.validateTestStep("Rental exclusions Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalExclusionsText().getText(),"Rental exclusions");

        ValidationUtil.validateTestStep("Rental rules Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalRulesText().getText(),"Rental rules");

//        ValidationUtil.validateTestStep("Child Seats Required by Law Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().getText(),"Child Seats Required by Law");
//
//        ValidationUtil.validateTestStep("Child Seats Required by Law \"Yes\" Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().getText(),"Yes");
//
//        ValidationUtil.validateTestStep("Check with the rental counter or state to verify requirements Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().getText(),
//                "Check with the rental counter or state to verify requirements.");
//
//        ValidationUtil.validateTestStep("Minimum Age to Rent Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().getText(),
//                "Minimum Age to Rent");
//
//        ValidationUtil.validateTestStep("Minimum Age to Rent \"21\" Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().getText(), "21");
//
//        ValidationUtil.validateTestStep("Minimum Age to Rent Note Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().getText(), MINIMUM_AGE_NOTE);
//
//        ValidationUtil.validateTestStep("Refund for Unused Rental Days/Hours Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesRefundForUnusedText().getText(), "Refund for Unused Rental Days/Hours ");
//
//        ValidationUtil.validateTestStep("Refund for Unused Rental Days/Hours \"NO\" Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesRefundForUnusedText().getText(), "No");
//
//        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().getText(), "Valid Driver's License Required Yes");

    }
}