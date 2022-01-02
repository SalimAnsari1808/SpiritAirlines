package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373712
//Description : Task 27141: TC373712 - US 17238 - 001 - CP - Vacation Car - Flight + Hotel + Car - Validate Facade and page format for Cars on a Standard booking
//Created By  : Anthony Cardona
//Created On  : 20-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 9-Dec-2019
//**********************************************************************************************
public class TC373712 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "FlightHotelCar", "DomesticDomestic", "Guest", "Outside21Days", "Adult", "NoBags" ,
            "NoSeats" , "Hotels" , "CarsUI","CheckInOptions","Visa"})
    public void Vacation_Car_Flight_Hotel_Car_Validate_Facade_and_page_format_for_Cars_on_a_Standard_booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373712 under GoldFinger suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "IAH";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "136";
        final String ADULT              = "5";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE         = "25+";
        final String ROOMS_VALUE        = "2 Rooms";

        // Hotel Page Constant Values
        final String HOTEL_BOOK_NAME    = "Universal";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

// Step 1:  Access Spirit home page in test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

// Step 2: Start Vacation Flight + Hotel + Car booking, departure in 3 months out for 5 passengers, 2 rooms and driver age +25
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//Step 3: Scroll to the bottom and verify there is not an option to continue without Hotel selection
//        try
//        {
            ValidationUtil.validateTestStep("Validating the user cannot continue without hotel selection",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getContinueWithoutHotelButton()));
//        }catch (Exception e){}

//Step 6 7: Scroll through the hotels, and click on SELECT ROOM on a hotel
        WaitUtil.untilTimeCompleted(2000);
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage(HOTEL_BOOK_NAME, "NA");

// Step 8: Scroll to the bottom and verify there is not an option to continue without car selection
//        try
//        {
            ValidationUtil.validateTestStep("Validating the user cannot continue without selecting cars",
                    !TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton()));
//        }catch (Exception e){}

//Step 9: Validate the first tile is outlined blue and labeled "DEAL OF THE DAY"
        //invalid test step

//Step 10: Validate each car tile contains the Car Supplier Name such as:
        String[] rentalCompanyName = {"Enterprise", "Alamo", "National", "Hertz", "Dollar", "Thrifty"};

        for(WebElement rentalCompanyFilter :  pageObjectManager.getCarPage().getRentalAgencyFilterOptionListButton())
        {
            String currentRentalCompany = rentalCompanyFilter.getText();
            boolean currentAgencyIsCorrect =  currentRentalCompany.contains(rentalCompanyName[0]) ||
                    currentRentalCompany.contains(rentalCompanyName[1]) ||
                    currentRentalCompany.contains(rentalCompanyName[2]) ||
                    currentRentalCompany.contains(rentalCompanyName[3]) ||
                    currentRentalCompany.contains(rentalCompanyName[4]) ||
                    currentRentalCompany.contains(rentalCompanyName[5]) ;
            ValidationUtil.validateTestStep("The Rental agency is one of the listed" , currentAgencyIsCorrect);
        }


//Step 11: Validate each car tile contains the Car Make and Model and the verbiage "or similar"
        for (int i = 0; i < pageObjectManager.getCarPage().getCarsPageCarsPanel().size(); i++)
        {
            ValidationUtil.validateTestStep("The car make and model is displayed" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageCarModelText().get(i)));

            //Step 12: Validate each car tile contains the following icons:
            ValidationUtil.validateTestStep("The car seat count is displayed" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardNumberOfSeatsText().get(i)));
            ValidationUtil.validateTestStep("The car Bag count is displayed" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardNumberOfBagsText().get(i)));
            ValidationUtil.validateTestStep("The car Transmission type is displayed" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardTransmissionTypeText().get(i)));
            ValidationUtil.validateTestStep("The car Mileage is displayed" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardMileageLimitText().get(i)));

            //Step 13: Validate each icon highlights when you hover
            //out of scope

            //Step 14: Validate each tile has a "More" link with a chevron pointing down, then click on it.
            ValidationUtil.validateTestStep("The \"More link\" is pointing down" , getDriver().findElements(By.xpath(pageObjectManager.getCarPage().xpath_CarsPageMoreInfoLink + "//i")).get(i).getAttribute("class"),"down");
            pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(i).click();
            ValidationUtil.validateTestStep("The \"More link\" is changed to less and is pointing up" , getDriver().findElement(By.xpath(pageObjectManager.getCarPage().xpath_CarsPageLessInfoLink + "//i")).getAttribute("class"),"up");

            ValidationUtil.validateTestStep("Vehicle Description is displayed" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageMoreInfoVehicleDescriptionLink()));
            ValidationUtil.validateTestStep("Vehicle Policies is displayed" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageMoreInfoPoliciesLink()));
            ValidationUtil.validateTestStep("Vehicle Location is displayed" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPageMoreInfoLocationsLink()));
            pageObjectManager.getCarPage().getCarsPageLessInfoLink().click();

            //Step 15: Validate each car tile contains 2 different pricing:
            ValidationUtil.validateTestStep("The car Total Price per day text is displayed" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(i)));
            ValidationUtil.validateTestStep("The car Total Price is displayed" , TestUtil.verifyElementDisplayed(carTotalPrice().get(i)));

            //Step 17: Validate each car tile has a BOOK button.
            ValidationUtil.validateTestStep("The car Book Button is displayed" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageBookButton().get(i)));
        }

//Step 18: Select a car and click on BOOK button
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA", "NA");
        //Cannot verify that there us a black border since the user is taken to the passenger information page

// Step 19: Click BOOK IT
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//Step 20: Complete the booking and record the PNR information | Booking completed satisfactory and PNR recorded
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(),pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value")+" "+pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"));
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //bags
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Hotel
        pageMethodManager.getPaymentPageMethods().verifyHotelSectionDetails();
        pageMethodManager.getPaymentPageMethods().verifyCarSectionDetails();
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);

        //TODO: Due the validation after payment is on SkySpeed, we are not completing the booking
//        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();


//        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
//        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
//                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);

//        pageMethodManager.getConfirmationPageMethods().verifyCarSectionDetails();
//        pageMethodManager.getConfirmationPageMethods().verifyHotelSectionDetails();

//        //Cancel Hotel & Car
//        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
//        pageMethodManager.getCommonPageMethods().cancelCarnetCarBooking();

    }

    public List<WebElement> carTotalPrice()
    {
        return getDriver().findElements(By.xpath("(//app-car-list-item/div/div[3]//p[2])"));
    }
}
