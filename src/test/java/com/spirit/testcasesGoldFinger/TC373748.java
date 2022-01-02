package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:14803]GoldFinger MVP1: CP: BP:  Vacation: Flight-Car: Payment Page: Pick-Up time for car is before flight time
//Test Case ID: TC373748
//Description: Task 27845: TC373998- 007 - CP - Price Display Shopping Cart - Vacation Path - Flight + Hotel + Car display
//Created By: Gabriela
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373748 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug","BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult",
                    "Guest", "BookIt", "DynamicShoppingCartUI"})
    public void CP_Price_Display_Shopping_Cart_Vacation_Path_Flight_Hotel_Car_display(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373748 under GoldFinger Suite on " + platform + " Browser", true);
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

        //Options Page Constant Values:
        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Constant Values
        final String TERSM_CONDITIONS   = "RENTALCARSAREAVAILABLETODRIVERS21YEARSOFAGEANDOLDERWITHAVALIDCREDITCARDANDVALIDDRIVERSLICENSEBOTHINTHENAMEOFTHEDRIVER.INCERTAINSTATESDRIVERSMUSTBE25YEARSOFAGEOROLDER.DRIVERSUNDERTHEAGEOF25MAYBESUBJECTTOADDITIONALSURCHARGESWHICHARENOTINCLUDEDINQUOTEDRATESANDAREPAYABLEDIRECTLYTOTHERENTALCARCOMPANY.CUSTOMERSMAYBESUBJECTTOACREDITCHECK,CREDITCARD(MUSTBEINDRIVERSNAME)ANDAGEVERIFICATION.FAILURETOCOMPLYMAYRESULTINCARRENTALREFUSAL.MANYRENTALCARLOCATIONSDONOTACCEPTDEBITCARDSFORCARRENTALORMAYIMPOSELARGERDEPOSITREQUIREMENTSINTHEEVENTTHEYDOACCEPTTHEM.PLEASECONTACTSPECIFICCARPICK-UPLOCATIONTODETERMINEIFTHEYWILLACCEPTADEBITCARDANDTHEASSOCIATEDRESTRICTIONS.REMEMBERTHATADEPOSITAMOUNTMAYBEREQUIREDFORARENTALCAR.SOMELOCATIONSMAYREQUIREAPRINTEDVOUCHERINORDERTOPICKUPYOURRENTALCAR.";


//- Step 1: go to the Goldfinger testing environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: create a RT DOM-DOM 1ADT direct flight / book it /no bags /no seats and land on options
        //*** Home Page Methods **/
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //*** Flight Availability Page Methods **/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        //*** Passenger Information Page Methods **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        //*** Bags Page Methods **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //*** Seats Page Methods **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        //*** Options Page Methods **/
//- Step 3: on the options select view all cars and book an Thrifty car
        pageMethodManager.getCarPageMethods().selectCarOnOptionPage("Thrifty","NA");
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 4: verify flight and select car info displays correct, scroll down to terms and condition and validate correct verbiage for Rentals displays :
        //TODO: [IN:14803]GoldFinger MVP1: CP: BP:  Vacation: Flight-Car: Payment Page: Pick-Up time for car is before flight time
//        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        for (int i = 0; i < getDriver().findElements(By.xpath("((//p[contains(text(),'Terms and Conditions')])[2]//following::div)[2]//div")).size(); i ++)
        {
            if (getDriver().findElements(By.xpath("((//p[contains(text(),'Terms and Conditions')])[2]//following::div)[2]//div")).get(i).getText().equals("2.2.3 Rental Cars"))
            {
                System.out.println("term displayed: " + getDriver().findElements(By.xpath("((//p[contains(text(),'Terms and Conditions')])[2]//following::div)[2]//div")).get(i+1).getText());
                ValidationUtil.validateTestStep("",
                        getDriver().findElements(By.xpath("((//p[contains(text(),'Terms and Conditions')])[2]//following::div)[2]//div")).get(i+1).getText() +
                                getDriver().findElements(By.xpath("((//p[contains(text(),'Terms and Conditions')])[2]//following::div)[2]//div")).get(i+2).getText(),TERSM_CONDITIONS);
            }
        }
    }
}