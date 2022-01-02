package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.swing.*;

//**********************************************************************************************
//Test CaseID: TC90897
//Title      : Reservation Summary_CP_MT_Page Links Test scenarios
//Created By : Kartik Chauhan
//Created On : 07-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************
//TODO: BUG 26571 CP: MT: Reservation Summary: When a user has not added a KTN or Redress Number, they are seeing the "EDIT" button instead of "ADD"
public class TC90897 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug" , "MyTrips" , "OneWay" , "DomesticInternational" , "WithIn7Days" , "Adult" ,"Guest", "NonStop" , "BookIt" , "NoBags" , "NoSeats" ,"CheckInOptions", "Visa" , "ReservationUI" ,"ItineraryReceiptUI", "ChangeFlight", "AddEditPassportInfo" , "PassportUI" , "CancelReservationUI"})
    public void  Reservation_Summary_CP_MT_Page_Links_Test_scenarios (@Optional("NA") String platform) {
        /******************************************************************************
         ***************************Navigate to Reservation Summary Page***************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90897 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String JOURNEY_TYPE       = "Flight";
        final String LANGUAGE           = "English";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page constant values
        final String OPTION_VALUE       = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "VisaCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //Confirmation Page Constant Values
        final String FILE_NAME          = "spirit";
        final String FILE_EXTENTION     = "ics";

        //open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        /******************************************************************************
         **************************Reservation Credit Section**************************
         ******************************************************************************
         //****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().selectOneWayInternationalPopup();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page methods
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //my trip page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().loginToMyTrip();
//STEP--1
        //Validate that the add rip to calendar button is displayed
        ValidationUtil.validateTestStep("The add to trip button is displayed" ,
                pageObjectManager.getConfirmationPage().getAddTripToCalendarButton().isDisplayed());
        pageObjectManager.getConfirmationPage().getAddTripToCalendarButton().click();

        WaitUtil.untilTimeCompleted(5000);

        ValidationUtil.validateTestStep("Click on Add trip my calendar spirit.ics file is downloaded",
                TestUtil.verifyFileDownload(FILE_NAME,FILE_EXTENTION));
//STEP--2
        //click on the PRINT RECEIPT link.
        pageObjectManager.getReservationSummaryPage().getPrintReceiptButton().click();

        //Constant Values to validate
        final String ITINERARY_URL    = "my-trips/itinerary";

        //verify user on Navigated to Itinerary Receipt page
        ValidationUtil.validateTestStep("Verify user navigated to Manage Travel Reservation Summary Page,",
                getDriver().getCurrentUrl(), ITINERARY_URL);

        //verify The title of the page is Your Itinerary Receipt
        ValidationUtil.validateTestStep("verify The title of the page is Your Itinerary Receipt",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getYourItineraryReceiptText()));

        //Verify Right aligned to the header of the page the  PRINT CONFIRMATION button is displayed.
        ValidationUtil.validateTestStep("verify The title of the page is Your Itinerary Receipt",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getPrintItineraryButton()));

        //Verify When clicked the button, user should be redirected to their computer's printer settings.
        /**Print Itinerary Button doesnt work**/

        //navigate back to the page
        getDriver().navigate().back();

        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--3
        //Click on Change FLight Button
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();

        WaitUtil.untilTimeCompleted(1500); //wait for pop-up

        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepEditLabel().click();

        WaitUtil.untilPageLoadComplete(getDriver());

        //validate that the Origin and destination
        ValidationUtil.validateTestStep("Verify Departure city drop down is visible on Change Flight Popup on Reservation Summary page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepFromCityDropDown()));

        ValidationUtil.validateTestStep("Verify Return city drop down is visible on Change Flight Popup on Reservation Summary page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepToCityDropDown()));

        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupCloseButton().click();

//**********************************As per new functionality, pop-up will display instead of Redirecting the page
//STEP--4

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify Add/Edit Passport Link
        ValidationUtil.validateTestStep("Verify Add/Edit Passport Link on Reservation Summary page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getReservationSummaryPage().getAddEditPassportInformationLink()));

        //Add edit passport info
        pageObjectManager.getReservationSummaryPage().getAddEditPassportInformationLink().click();

        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Constant Values to validate
        final String PASSPORT_URL    = "my-trips/travel-docs";

        //verify user on Navigated to Itinerary Receipt page
        ValidationUtil.validateTestStep("Verify user navigated to Passport Information Page,",
                getDriver().getCurrentUrl(), PASSPORT_URL);

        //navigate back to the page
        getDriver().navigate().back();
//STEP--5
        //wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //TODO: BUG 26571 CP: MT: Reservation Summary: When a user has not added a KTN or Redress Number, they are seeing the "EDIT" button instead of "ADD"
        ValidationUtil.validateTestStep("Verify Add KTN link is displaying on Reservation Summary page",
                pageObjectManager.getReservationSummaryPage().getPassengerSectionKTNAddButton().get(0).isDisplayed());

        pageObjectManager.getReservationSummaryPage().getPassengerSectionKTNAddButton().get(0).click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Verify Add KTN Textbox is displaying
        ValidationUtil.validateTestStep("Verify Add KTN Textbox is displaying on Reservation Summary page",
                pageObjectManager.getReservationSummaryPage().getPassengerSectionKTNTextBox().get(0).isDisplayed());

        //click on KTN  Add button
        pageObjectManager.getReservationSummaryPage().getPassengerSectionKTNAddButton().get(0).click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--6
        //Verify Additional Info caret
        ValidationUtil.validateTestStep("Verify Additional Info caret is displaying on Reservation Summary page",
                pageObjectManager.getReservationSummaryPage().getPassengerSectionAdditionalInfoAddButton().get(0).isDisplayed());

        //STEP--7
        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Click on Additional Info caret
        pageObjectManager.getReservationSummaryPage().getPassengerSectionAdditionalInfoAddButton().get(0).click();

        //Select Hearing Impaired option
        pageObjectManager.getReservationSummaryPage().getHearingImpairedListCheckBox().click();

        //Wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //Verify Hearing Imparied option is selected
        ValidationUtil.validateTestStep("Verify Hearing Impaired option is selected now on Reservation Summary page",
                pageObjectManager.getReservationSummaryPage().getHearingImpairedListCheckBox().isEnabled());

        //Select Hearing Impaired option
        pageObjectManager.getReservationSummaryPage().getHearingImpairedListCheckBox().click();

        //Verify Hearing Imparied option is not selected
        ValidationUtil.validateTestStep("Verify Hearing Impaired option is not selected on Reservation Summary page",
                JSExecuteUtil.getElementAfterProperty(getDriver(),pageObjectManager.getReservationSummaryPage().getHearingImpairedListCheckBox(),"background-color"),"rgba(0, 0, 0, 0)");

//STEP--8
        //wait till page load complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //verify Special Service Verbiage
        ValidationUtil.validateTestStep("Special Service Verbiage is displaying on Reservation Summary page",
                pageObjectManager.getReservationSummaryPage().getSpecialServicesVerbiage().isDisplayed());

        //verify Special Service Notification
        pageObjectManager.getReservationSummaryPage().getSpecialServicesIcon().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Special Service-More Info Link is displaying on Reservation Summary page",
                pageObjectManager.getReservationSummaryPage().getSpecialServicesMoreInfoLink().isDisplayed());

        //click on Special Service-More Info Link
        pageObjectManager.getReservationSummaryPage().getSpecialServicesMoreInfoLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //create constant
        final String SPECIAL_NEED_URL    = "https://customersupport.spirit.com/hc/en-us/sections/200427118-Traveling-with-Special-Needs";

        //Validate the user land on Traveling with Special Needs
        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("Validating the user land on Traveling with Special Needs",
                getDriver().getCurrentUrl(),SPECIAL_NEED_URL);

        getDriver().close();

        TestUtil.switchToWindow(getDriver(),0);

        //wait till page load complete
        WaitUtil.untilPageLoadComplete(getDriver());
//STEP--9
        //click on Emotional animal Notification
        pageObjectManager.getReservationSummaryPage().getEmotionalSupportAnimalListIcon().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on Emotional animal notification Moe Info link
        pageObjectManager.getReservationSummaryPage().getEmotionalSupportAnimalListMoreInfoLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //create constant
        final String EMOTIONAL_ANIMAL_MORE_INFO_URL      = "https://customersupport.spirit.com/hc/en-us/articles/202096816-Can-I-bring-my-service-emotional-support-or-psychiatric-service-animal-on-my-flight-";

        //Validate the user land on Traveling with Special Needs
        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("Validating the user land on 'Can i bring...'",
                getDriver().getCurrentUrl(),EMOTIONAL_ANIMAL_MORE_INFO_URL);

        getDriver().close();

        TestUtil.switchToWindow(getDriver(),0);


        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

//STEP-10
        //click on Emotional animal Notification
        pageObjectManager.getReservationSummaryPage().getPortableOxygenContainerListIcon().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on Emotional animal notification Moe Info link
        pageObjectManager.getReservationSummaryPage().getPortableOxygenContainerListMoreInfoLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //create constant
        final String PORTABLE_OXYGEN_MORE_INFO_URL      = "https://customersupport.spirit.com/hc/en-us/articles/202096766-Can-I-bring-my-Portable-Oxygen-Concentrator-on-board";

        //Validate the user land on Traveling with Special Needs
        TestUtil.switchToWindow(getDriver(),1);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Validating the user land on 'Can i bring my Portable oxygen...'",
                getDriver().getCurrentUrl(),PORTABLE_OXYGEN_MORE_INFO_URL);

        getDriver().close();

        TestUtil.switchToWindow(getDriver(),0);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP-11
        //click on Emotional animal Notification
        pageObjectManager.getReservationSummaryPage().gettherDisabilityListIcon().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on Emotional animal notification Moe Info link
        pageObjectManager.getReservationSummaryPage().getOtherDisabilityListMoreInfoLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //create constant
        final String OTHER_MORE_INFO_URL      = "https://customersupport.spirit.com/hc/en-us/sections/200427118-Traveling-with-Special-Needs?_ga=2.144338037.1561155806.1521743382-175626876.1517490878";

        //Validate the user land on Traveling with Special Needs
        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("Validating the user land on 'Traveling with Special need...'",
                getDriver().getCurrentUrl(),OTHER_MORE_INFO_URL);

        getDriver().close();

        TestUtil.switchToWindow(getDriver(),0);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

//STEP--12
        pageObjectManager.getReservationSummaryPage().getWheelchairIHaveMyOwnListCheckBox().click();

        ValidationUtil.validateTestStep("Validating the WheelChair Dropdown is displaying'",
                pageObjectManager.getReservationSummaryPage().getWheelChairTypeOfWheelChairDropDown().isDisplayed());
//STEP--13
        pageObjectManager.getReservationSummaryPage().getDisabilitySeatingLabel().get(0).click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--14
        //click on other disability Label
        pageObjectManager.getReservationSummaryPage().getOtherDisabilityLabel().get(0).click();

        //Validating the Other Disability Text box
        ValidationUtil.validateTestStep("Validating the Other Disability Text box is displaying'",
                pageObjectManager.getReservationSummaryPage().getOtherDisabilityTextbox().isDisplayed());

        //send alphanumeric input
        pageObjectManager.getReservationSummaryPage().getOtherDisabilityTextbox().sendKeys("abc123");
//STEPP--15
        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on Voluntary Provision Certify Notification
        pageObjectManager.getReservationSummaryPage().getVoluntaryProvisionofEmergencyServicesProgramListIcon().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //click on Voluntary Provision Certify Notification More info link
        pageObjectManager.getReservationSummaryPage().getVoluntaryProvisionofEmergencyServicesProgramListMoreInfoLink().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //create constant
        final String VOLUNTARY_PROVISION_MORE_INFO_URL      = "https://customersupport.spirit.com/hc/en-us/articles/202097026-Voluntary-Provision-of-Emergency-Services-Program-VPESP-";

        //Validate the user land on Traveling with Special Needs
        TestUtil.switchToWindow(getDriver(),1);

        ValidationUtil.validateTestStep("Validating the user land on 'Voluntary Provision of Emergency Services Program...'",
                getDriver().getCurrentUrl(),VOLUNTARY_PROVISION_MORE_INFO_URL);

        getDriver().close();

        TestUtil.switchToWindow(getDriver(),0);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--16
        //SSR cancel button
        pageObjectManager.getReservationSummaryPage().getSSRCancelButton().click();

        ValidationUtil.validateTestStep("Validating Additional Info screen is closed after clicking on Cancel button",
                pageObjectManager.getReservationSummaryPage().getPassengerSectionAdditionalInfoAddButton().get(0).isDisplayed());
//STEP--17
        //Click on Additional Info caret
        pageObjectManager.getReservationSummaryPage().getPassengerSectionAdditionalInfoAddButton().get(0).click();

        ValidationUtil.validateTestStep("Validating Disability Seat is still enable",
                !pageObjectManager.getReservationSummaryPage().getHearingImpairedListCheckBox().isSelected());

//STEP--18
        //create constant
        final String MYTRIP_BAG   = "Bags";

        //click on add Bags and select any available seat
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_BAG);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //create constant
        final String MY_TRIP_BAG_URL      = "my-trips/bags";

        //verify user land on Bags page
        ValidationUtil.validateTestStep("Validating the user land on 'Add Bags Now and Save...'",
                getDriver().getCurrentUrl(),MY_TRIP_BAG_URL);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--19
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        final String MYTRIP_SEAT_POPUP          = "DontPurchase";
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(MYTRIP_SEAT_POPUP);
//STEP--20

        //Wait till page load
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        final String MYTRIP_SEAT   = "Seats";

        //click on add Bags and select any available seat
        pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection(MYTRIP_SEAT);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //create constant
        final String MY_TRIP_SEAT_URL      = "my-trips/seats";

        //verify user land on Bags page
        ValidationUtil.validateTestStep("Validating the user land on 'Choose Your Seat...'",
                getDriver().getCurrentUrl(),MY_TRIP_SEAT_URL);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP--21

        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Wait till page load
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        final String MYTRIP_BAGS_POPUP          = "DontPurchase";
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup(MYTRIP_BAGS_POPUP);

        //Wait till page load
        WaitUtil.untilPageLoadComplete(getDriver());

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

//STEP-22
        pageObjectManager.getReservationSummaryPage().getContactSectionEditButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //create constant
        final String MY_TRIP_CHANGE_INFO_URL      = "my-trips/passenger";

        //verify user land on Bags page
        ValidationUtil.validateTestStep("Validating the user land on 'Change Information' Page",
                getDriver().getCurrentUrl(),MY_TRIP_CHANGE_INFO_URL);

        //navigate back
        getDriver().navigate().back();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
//STEP-23
        //click on Cancel itinerary
        pageObjectManager.getReservationSummaryPage().getCancelItineraryButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //create constant
        final String MY_TRIP_CANCEL_ITINERARY_URL      = "my-trips/cancel-reservation";

        //verify user land on Bags page
        ValidationUtil.validateTestStep("Validating the user land on 'Cancellation Confirmation' Page",
                getDriver().getCurrentUrl(),MY_TRIP_CANCEL_ITINERARY_URL);
    }
}