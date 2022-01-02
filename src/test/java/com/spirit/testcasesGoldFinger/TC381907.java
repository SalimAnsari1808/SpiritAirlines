package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC381907
//Description: Flight Flex Packaging Verbiage - Flight + Car - Validate verbiage for Flight Flex on the Hub Page
//Created By: Salim Ansari
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC381907 extends BaseClass {

    @Parameters("platform")
    @Test(groups = {"BookPath" , "FlightCar" , "DomesticDomestic" , "Outside21Days" , "Adult" , "NonStop" , "BookIt" ,
            "CarryOn" , "CheckedBags" , "Standard", "FlightFlex", "OptionalUI"})
    public void Flight_Flex_Packaging_Verbiage_Spanish_Flight_Car_Validate_verbiage_for_Flight_Flex_on_the_Hub_Page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC381907 under GoldFinger Suite on " + platform + " Browser", true);
        }

        /******************************************************************************
         ****************************Navigate to Passenger Info Page*****************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LANGUAGE1 			= "Spanish";
        final String JOURNEY_TYPE 		= "Vacation";
        final String TRIP_TYPE 			= "Flight+Car";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "93";
        final String ARR_DATE 			= "95";
        final String ADULT				= "3";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";
        final String DRIVER_AGE 		= "21-24";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BookIt";

//Step1--Land on current test environment.
        openBrowser( platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//STEP--2 Click on Vacation tab and select Flight + Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//STEP--3 Input the following: DOM_DOM | Any Date 3 months in the future | 3 PAX | Driver 21-24 and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//STEP--4 Select any car and proceed to the Options page with BOOK IT | Fill Passenger Info; Select Primary Driver |  No Bags | No Seats
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//STEP--5 Verify that Flight Flex is Not Available:(Not Applicable)


//STEP--6 Scroll down to the Options title and verify the following verbiage: Modify your flight once for free!
        final String FLIGHT_FLEX_BODY_CARD_TEXT = "Flight Flex - Modify your flight once for free!";

        ValidationUtil.validateTestStep("Verify Flight Flex body card verbiage on Option Page",
                pageObjectManager.getOptionsPage().getFlightFlexCardBodyText().getText(),FLIGHT_FLEX_BODY_CARD_TEXT);

//STEP--7 Click the tool tip next to the verbiage within the Flight Flex tile
        pageObjectManager.getOptionsPage().getFlightFlexCardToolTipLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//STEP--13 erify the following verbiage is within the Flight Flex modal:
        final String FlightFlexToolTipPopUpTEXT1 = "Flight Flex is only available during initial booking at spirit.com";
        final String FlightFlexToolTipPopUpTEXT2 = "Flight Flex must be purchased for all the passengers on the booking.  Only one modification charge can be waived when Flight Flex is purchased.";
        final String FlightFlexToolTipPopUpTEXT3 = "You can only modify the time, date, and/or the origin/destination of the booking.  The new time, date, or origin/destination must be known at time of change.";
        final String FlightFlexToolTipPopUpTEXT4 = "All modifications must be done online on spirit.com more than 24 hours in advance of their scheduled outbound or return departure.";
        final String FlightFlexToolTipPopUpTEXT5 = "Changes not made more than 24 hours in advance will be subject to our standard modification charges.";
        final String FlightFlexToolTipPopUpTEXT6 = "Please note that you will still be responsible for any difference in airfare and optional services for the alternate requested date(s) or flight(s), as well as any difference in government taxes and fees.";
        final String FlightFlexToolTipPopUpTEXT7 = "3rd (i.e. hotel/car/activity) modification and/or cancellation charges may apply";

        //get all the elements of flight flex tool tip popup
        List<WebElement> flightFlexToolTipBodyText = pageObjectManager.getOptionsPage().getFlightFlexToolTipPopUpBodyText().findElements(By.tagName("li"));

        ValidationUtil.validateTestStep("Verify Flight Flex Body Card Tool Tip Popup text1 on Option Page ",
                flightFlexToolTipBodyText.get(0).getText(),FlightFlexToolTipPopUpTEXT1);

        ValidationUtil.validateTestStep("Verify Flight Flex Body Card Tool Tip Popup text2 on Option Page ",
                flightFlexToolTipBodyText.get(1).getText(),FlightFlexToolTipPopUpTEXT2);

        ValidationUtil.validateTestStep("Verify Flight Flex Body Card Tool Tip Popup text3 on Option Page ",
                flightFlexToolTipBodyText.get(2).getText(),FlightFlexToolTipPopUpTEXT3);

        ValidationUtil.validateTestStep("Verify Flight Flex Body Card Tool Tip Popup text4 on Option Page ",
                flightFlexToolTipBodyText.get(3).getText(),FlightFlexToolTipPopUpTEXT4);

        ValidationUtil.validateTestStep("Verify Flight Flex Body Card Tool Tip Popup text5 on Option Page ",
                flightFlexToolTipBodyText.get(4).getText(),FlightFlexToolTipPopUpTEXT5);

        ValidationUtil.validateTestStep("Verify Flight Flex Body Card Tool Tip Popup text6 on Option Page ",
                flightFlexToolTipBodyText.get(5).getText(),FlightFlexToolTipPopUpTEXT6);

        ValidationUtil.validateTestStep("Verify Flight Flex Body Card Tool Tip Popup text7 on Option Page ",
                flightFlexToolTipBodyText.get(6).getText(),FlightFlexToolTipPopUpTEXT7);
    }

}
