package com.spirit.testcasesGoldFinger;


import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC381905
//Description: Flight Flex Packaging Verbiage - Hub Packaging - Validate verbiage for Flight Flex on the Hub Page
//Created By: Salim Ansari
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC381905 extends BaseClass {

    @Parameters("platform")
    @Test(groups = {"BookPath" , "Flight" , "RoundTrip","DomesticDomestic" , "Outside21Days" , "Adult" , "NonStop" , "BookIt" ,
            "NoBags" , "NoSeats" , "FlightFlex", "OptionalUI"})
    public void Flight_Flex_Packaging_Verbiage_Hub_Packaging_Validate_verbiage_for_Flight_Flex_on_the_Hub_Page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC381905 under GoldFinger Suite on " + platform + " Browser", true);
        }

        /******************************************************************************
         ****************************Navigate to Passenger Info Page*****************
         ******************************************************************************/
        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String JOURNEY_TYPE 		    = "Flight";
        final String TRIP_TYPE 			    = "RoundTrip";
        final String DEP_AIRPORTS 		    = "AllLocation";
        final String DEP_AIRPORT_CODE 	    = "FLL";
        final String ARR_AIRPORTS 		    = "AllLocation";
        final String ARR_AIRPORT_CODE 	    = "LAS";
        final String DEP_DATE 			    = "93";
        final String ARR_DATE 			    = "95";
        final String ADULT				    = "1";
        final String CHILD				    = "0";
        final String INFANT_LAP			    = "0";
        final String INFANT_SEAT		    = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT             = "Connecting";
        final String RET_FLIGHT             = "Connecting";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BookIt";

//Step1--Land on current test environment.
        openBrowser( platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//STEP--2 Input the following: RT | DOM_DOM | Any Date 3 months in the future | 2 PAX and click SEARCH FLIGHTS
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//STEP--3 Select any flight and proceed to the Options page with BOOK IT | Fill Passenger Info. |  No Bags | No Seats
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep",DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret",RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Page Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//STEP--4 Scroll down to the Options title and verify the following verbiage: Modify your flight once for free!
        final String FLIGHT_FLEX_BODY_CARD_TEXT = "Flight Flex - Modify your flight once for free!";

        ValidationUtil.validateTestStep("Verify Flight Flex body card verbiage on Option Page",
                pageObjectManager.getOptionsPage().getFlightFlexCardBodyText().getText(),FLIGHT_FLEX_BODY_CARD_TEXT);

//STEP--5 Click the tool tip next to the verbiage with the Flight Flex tile
        pageObjectManager.getOptionsPage().getFlightFlexCardToolTipLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//STEP--6 Verify the following verbiage is within the Flight Flex modal:
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


