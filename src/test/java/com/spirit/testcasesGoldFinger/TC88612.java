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
//Test Case ID: TC88612
//Description: Flight Flex Packaging Verbiage (Spanish) - Hub Packaging - Validate verbiage for Flight Flex on the Hub Page
//Created By: Salim Ansari
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC88612  extends BaseClass {
    @Parameters("platform")
    @Test(groups = {"BookPath" , "Flight" , "RoundTrip","DomesticDomestic" , "Outside21Days" , "Adult" , "NonStop" , "BundleIt" ,
            "CarryOn" , "CheckedBags" , "Standard", "FlightFlex", "OptionalUI"})
    public void Flight_Flex_Packaging_Verbiage_Spanish_Flight_Car_Validate_verbiage_for_Flight_Flex_on_the_Hub_Page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC88612 under GoldFinger Suite on " + platform + " Browser", true);
        }

        /***************************Navigate to Passenger Info Page*************************/
        //Home Page Constant Values
        final String LANGUAGE 			    = "English";
        final String LANGUAGE1 			    = "Spanish";
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
        final String DEP_FLIGHT             = "NonStop";
        final String RET_FLIGHT             = "NonStop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_VALUE          = "BundleIt";

        //Bags Page constant values
        final String DEP_BAGS 			    = "Carry_1|Checked_0";

        //Seats Page Constant values
        final String DEP_SEATS              = "Standard";
        final String RET_SEATS              = "Standard";

        //Option page constant values
        final String OPTION_TEXT_SPANISH	= "SELECCIONADO";

//Step1--Land on current test environment.
        //open browser
        openBrowser( platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//STEP--2 Input the following: RT | DOM_DOM (The city pair need to have the Thrill Combo Offer, actual BUNDLE IT) | Any Date 3 months in the future | 2 PAX and click SEARCH FLIGHTS
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();


//STEP--3 Select any flight and proceed to the next page by clicking the Continue button on the Standard box.
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep",DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret",RET_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//STEP--4 Select the Bundle it option
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//STEP--5 Populate all the required information on the Passenger Information page and click Continue
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//STEP--6 Leave the default selection on the bags page for the Bundle it option and click the Continue button.
        pageMethodManager.getBagsPageMethods().selectBagsFare("Standard");

//STEP--7 Select Regular Seats for each passenger and click Continue.
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

//STEP--8 On the Options page, click Espanol
        for(WebElement appLanguage: pageObjectManager.getHomePage().getSelectedLanguage()) {
            //check for displayed language link on Home Page
            if(appLanguage.isDisplayed()) {

                //change to Spanish language
                JSExecuteUtil.clickOnElement(getDriver(), appLanguage);

                //wait till page is stable
                WaitUtil.untilPageLoadComplete(getDriver());

                //validate Spanish language is selected
                ValidationUtil.validateTestStep("User selected Spanish Language on Home Page",
                        appLanguage.getText().trim().equalsIgnoreCase("English"));

                break;
            }
        }


//STEP--9 Scroll down to the Flight Flex Opciones title and verify the following verbiage:
        final String FLIGHT_FLEX_BODY_CARD_TEXT = "¡Modifique su vuelo una vez gratis!";

        ValidationUtil.validateTestStep("Verify Flight Flex body card verbiage on Option Page",
                pageObjectManager.getOptionsPage().getFlightFlexCardBodyText().getText(),FLIGHT_FLEX_BODY_CARD_TEXT);

//STEP--10 Verify the following verbiage is within the Flight Flex modal:
        pageObjectManager.getOptionsPage().getFlightFlexCardToolTipLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//STEP--13 Verify the following verbiage is within the Flight Flex modal:
        final String FlightFlexToolTipPopUpTEXT1 = "El Flight Flex está disponible solamente al hacer la reservación inicial en spirit.com";
        final String FlightFlexToolTipPopUpTEXT2 = "El Flight Flex debe ser comprado para todos los pasajeros en la reservación. Solo se puede eximir un cargo por modificación cuando se compra el Flight Flex.";
        final String FlightFlexToolTipPopUpTEXT3 = "Solo puede modificar la hora, fecha y/o el origen/destino de la reservación. La nueva hora, fecha u origen/destino debe conocerse al momento de hacer el cambio.";
        final String FlightFlexToolTipPopUpTEXT4 = "Todas las modificaciones deben hacerse en línea en spirit.com con más de 24 horas de anticipación a su vuelo de salida o regreso programado.";
        final String FlightFlexToolTipPopUpTEXT5 = "Los cambios que no se hagan con más de 24 horas de anticipación estarán sujetos a los cambios por modificación estándar.";
        final String FlightFlexToolTipPopUpTEXT6 = "Por favor considere que usted todavía será responsable por cualquier diferencia en tarifa aérea y servicios opcionales por la(s) fecha(s) alterna(s) o vuelo(s) solicitado(s), como también por cualquier diferencia en los impuestos y cargos gubernamentales.";
        final String FlightFlexToolTipPopUpTEXT7 = "Podrían aplicar cargos de terceros por modificación y/o cancelación (p.ej. hotel/auto/actividad)";

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
