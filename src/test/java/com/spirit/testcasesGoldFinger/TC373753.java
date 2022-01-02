package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
//**********************************************************************************************
//TODO: [IN:25127] 	GoldFinger R1: CP: BP: F+C: Options Page: Incorrect car Pick Up information displayed.
//TODO: [IN:25610]	PROD: GoldFinger R1: Payment Page PMT: Car Upsell: Pick-Up and Drop-Of time information on Car section, it's displaying 1 hour before that expected
//Test CaseID: TC373753
//Title      : Task 27291: TC373753 - US 20590 - 018 - CP - Verbiage Car Payment T+C - Thrifty - Validate that the correct T+C verbiage displays for a Booking Upsell
//Created By : Alex Rodriguez
//Created On : 27-Nov-2019
//Reviewed By: Gabriela
//Reviewed On: 5-Dec-2019
//**********************************************************************************************
public class TC373753 extends BaseClass {
  @Parameters({"platform"})
  @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Flight", "Outside21Days", "Adult", "Guest","NonStop", "BookIt", "NoBags", "NoSeats", "Cars", "PaymentUI", "ActiveBug"})
  public void CP_Price_Display_Shopping_Cart_Vacation_Path_Flight_Hotel_Car_display(@Optional("NA") String platform) {
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC373753 under GoldFinger Suite on " + platform + " Browser", true);
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
    final String TERMS_CONDITIONS   = "RENTALCARSAREAVAILABLETODRIVERS21YEARSOFAGEANDOLDERWITHAVALIDCREDITCARDANDVALIDDRIVERSLICENSEBOTHINTHENAMEOFTHEDRIVER.INCERTAINSTATESDRIVERSMUSTBE25YEARSOFAGEOROLDER.DRIVERSUNDERTHEAGEOF25MAYBESUBJECTTOADDITIONALSURCHARGESWHICHARENOTINCLUDEDINQUOTEDRATESANDAREPAYABLEDIRECTLYTOTHERENTALCARCOMPANY.CUSTOMERSMAYBESUBJECTTOACREDITCHECK,CREDITCARD(MUSTBEINDRIVERSNAME)ANDAGEVERIFICATION.FAILURETOCOMPLYMAYRESULTINCARRENTALREFUSAL.MANYRENTALCARLOCATIONSDONOTACCEPTDEBITCARDSFORCARRENTALORMAYIMPOSELARGERDEPOSITREQUIREMENTSINTHEEVENTTHEYDOACCEPTTHEM.PLEASECONTACTSPECIFICCARPICK-UPLOCATIONTODETERMINEIFTHEYWILLACCEPTADEBITCARDANDTHEASSOCIATEDRESTRICTIONS.REMEMBERTHATADEPOSITAMOUNTMAYBEREQUIREDFORARENTALCAR.SOMELOCATIONSMAYREQUIREAPRINTEDVOUCHERINORDERTOPICKUPYOURRENTALCAR.";

    //- Step 1: go to the Goldfinger testing environment.
    openBrowser(platform);
    pageMethodManager.getHomePageMethods().launchSpiritApp();
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    //- Step 2: create a RT DOM-DOM 1ADT direct flight / book it /no bags /no seats and land on options
    //Home Page Methods
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();
    // Flight Availability Page Methods
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
    // Passenger Information Page Methods
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();
    // Bags Page Methods
    pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
    // Seats Page Methods
    pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
    // Options Page Methods
    //- Step 3: on the options select view all cars and book an Thrifty car
    pageMethodManager.getCarPageMethods().selectCarOnOptionPage("Thrifty","NA");
    pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
    pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
    //- Step 4: verify flight and select car info displays correct, scroll down to terms and condition and validate correct verbiage for Rentals displays :
    //TODO: [IN:25127] 	GoldFinger R1: CP: BP: F+C: Options Page: Incorrect car Pick Up information displayed.
    //TODO: [IN:25610]	PROD: GoldFinger R1: Payment Page PMT: Car Upsell: Pick-Up and Drop-Of time information on Car section, it's displaying 1 hour before that expected
    pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
    WebElement rentalCarsTerms = getDriver().findElement(By.xpath("//span[contains(text(), ' Rental Cars')]//following::span[1]"));
    ValidationUtil.validateTestStep("User verifies verbiage in the Terms and Conditions for Rental Cars",TERMS_CONDITIONS, rentalCarsTerms.getText());
  }
}
