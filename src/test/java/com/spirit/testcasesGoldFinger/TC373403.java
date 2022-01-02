package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.enums.Context;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//TODO:  [IN:14803] -GoldFinger MVP1: CP: BP:  Vacation: Flight-Car: Payment Page: Pick-Up time for car is before flight time
//**********************************************************************************************
//Test Case ID  : TC373403
//Description   : Task:27949 | TC373403 -006 - CP - Car Detail Content - Validate the Facade for Car Details on a booking with an Infant on Lap
//Created By    : Anthony Cardona
//Created On    : 26-Nov-2019
//Reviewed By   : Gabriela
//Reviewed On   : 1-21-2020
//**********************************************************************************************
public class TC373403 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug","BookPath","RoundTrip","DomesticDomestic","Outside21Days","Adult","Guest","NonStop",
                    "BookIt","NoBags","NoSeats", "Cars","CarsUI","OptionalUI", "PaymentUI"})
    public void CP_Vacation_Flight_Car_Validate_the_Facade_for_a_booking_with_an_Infant_on_Lap(@Optional("NA") String platform) {
        //**********************Navigate to Payment Page************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373403 under GOLDFINGER Suite on " + platform + " Browser", true);
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
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options page methods
        final String CAR_VERBAGE1           = "Vehicle Description";
        final String VEHICLE_DESCRIPTION1   = "Please Note";
        final String VEHICLE_DESCRIPTION2   = "A valid major credit card in the renter’s name must be presented with available credit in the deposit amount (if applicable). At select locations, major debit cards may be used to secure your rental. At most Airport Locations debit cards will be accepted to qualify for rental when the customer provides proof of return airline flight to coincide with the rental and two (2) valid forms of identification. For specific information related to debit card acceptance or other policies, contact your rental location.";
        final String VEHICLE_DESCRIPTION3   = "The following exceptions apply:";
        final String VEHICLE_DESCRIPTION4   = "Debit cards may not be accepted at time of rental in the following cities and surrounding areas, please contact the local office for details: New York Metropolitan (New York, New Jersey, Connecticut), Hartford, Connecticut; Philadelphia, Pennsylvania; Boston, Massachusetts; Manchester, New Hampshire; and Detroit, Michigan.";
        final String VEHICLE_DESCRIPTION5   = "may conduct a review of your driver's license status and driving record.";
        final String VEHICLE_DESCRIPTION6   = "can deny rental based on your driving history. Car make or model is not guaranteed and a similar or larger car may be substituted. Please check with the rental car facility in regards to any additional restrictions or optional services. Unused days of rental are not eligible for refund. Additional rental days may require additional payment upon return of vehicle.";
        final String MINIMUM_AGE_NOTE       = "In certain states drivers must be 25 years of age or older. When allowed, drivers under the age of 25 may be subject to additional surcharges payable directly to the rental car supplier at the time of rental.";

        final String LOCATION_HOURS         = "Location hours";

        final String OPTIONS_PAGE           = "/book/options";

        //Payment Page Constant values
        final String PAYMENT_PAGE           = "/book/payment";



//Step 1: Open GoldFinger environment on consumer portal
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//Step 2: Create a RT DOM | 1 ADT (military) | 1 checked bag 1 carry-on | no seats | 3 months out and click on Search flights
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step 3: Input infant DOB for lap child and click "CONTINUE"
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//Step 4: Select flight, and select "CONTINUE" in the STANDARD box
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//Step 5: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//Step 6: Fill in passenger Information and select "CONTINUE"
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//Step 7 & 8: Select "CONTINUE WITHOUT ADDING BAGS"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//Step 9: Continue without seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//Step 12: Search for a car and click on "ADD CAR"
        WaitUtil.untilPageLoadComplete(getDriver());
        String carModel = pageObjectManager.getCarPage().getCarCardNameText().get(0).getText();
        System.out.println("getCarCardNameText: " +pageObjectManager.getCarPage().getCarCardNameText().get(0).getText());
        String carSeat = pageObjectManager.getCarPage().getCarCardSeatsText().get(0).getText();

        //pageObjectManager.getCarPage().getCarsCarouselAddCarButton().get(0).click();
        pageObjectManager.getCarPage().getCarCardNameText().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 11: Validate car supplier name, car make and model, car capacity on car pop up

        ValidationUtil.validateTestStep("Validating car capacity information",
                carSeat, pageObjectManager.getCarPage().getCarPopUpSeatText().getText());

        ValidationUtil.validateTestStep("Validating car model information",
                carModel, pageObjectManager.getCarPage().getCarPopUpCompanyMakeAndModelText().getText());

//Step 12: Validate Vehicle Description:
        ValidationUtil.validateTestStep("Verify " + CAR_VERBAGE1 + " is displayed on Car Modal PopUp",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab().getText(), CAR_VERBAGE1);


        //verify the vehicle description under more info link


        ValidationUtil.validateTestStep("Validating first Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(0).getText(),VEHICLE_DESCRIPTION1);

        ValidationUtil.validateTestStep("Validating second Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(1).getText(),VEHICLE_DESCRIPTION2);

        ValidationUtil.validateTestStep("Validating third Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(2).getText(),VEHICLE_DESCRIPTION3);

        ValidationUtil.validateTestStep("Validating fourth Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(3).getText(),VEHICLE_DESCRIPTION4);

        ValidationUtil.validateTestStep("Validating fifth Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(4).getText(),VEHICLE_DESCRIPTION5);

//Step 13: Validate Policies:
        pageObjectManager.getCarPage().getCarPageMoreInfoPoliciesLink().click();
        WaitUtil.untilTimeCompleted(3000);

//        ValidationUtil.validateTestStep("What's Included Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesWhatsIncludesText().getText(), "What's Included");

        ValidationUtil.validateTestStep("Rental inclusions Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalInclusionsText().getText(), "Rental inclusions");

        ValidationUtil.validateTestStep("Rental exclusions Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalExclusionsText().getText(), "Rental exclusions");

        ValidationUtil.validateTestStep("Rental rules Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalRulesText().getText(), "Rental rules");

        ValidationUtil.validateTestStep("Child Seats Required by Law Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(0).getText(), "Child Seats Required by Law");

        ValidationUtil.validateTestStep("Child Seats Required by Law \"Yes\" Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(1).getText(), "Yes");

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

//Step 12: Validate location hours, address, and phone number is shown under the Locations tab
        pageObjectManager.getCarPage().getCarPopUpLocationsTab().click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Location Hours is displayed under Location tab",
                pageObjectManager.getCarPage().getCarPopUpLocationsTabLocationHoursText().getText(), LOCATION_HOURS);


//Step 13: Select driver from drop down menu and click ADD CAR
        // primary driver selected by default
        pageObjectManager.getCarPage().getCarPopUpExitIconButton().click();

        pageMethodManager.getCarPageMethods().selectCarOnOptionPage("NA" , "NA");
        System.out.println(scenarioContext.getContext(Context.CAR_DETAILS));

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(), (OPTIONS_PAGE));

        pageMethodManager.getCarPageMethods().verifySelectedCarOptionPage();

//Step 14: Select check-in free and continue
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(), (PAYMENT_PAGE));

//Step 15: Fill out proper payment information and complete booking
      //TODO:  [IN:14803] -GoldFinger MVP1: CP: BP:  Vacation: Flight-Car: Payment Page: Pick-Up time for car is before flight time
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();

    }
}