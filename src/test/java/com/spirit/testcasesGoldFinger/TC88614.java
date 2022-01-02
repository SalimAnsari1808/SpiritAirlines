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
//Test Case ID: TC88614
//Description: Flight Flex Packaging Verbiage (Spanish) - Flight + Car - Validate verbiage for Flight Flex on the Hub Page
//Created By: Salim Ansari
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC88614 extends BaseClass {
    @Parameters("platform")
    @Test(groups = {"BookPath" , "FlightCar" , "DomesticDomestic" , "Outside21Days" , "Adult" , "NonStop" , "BundleIt" ,
                    "CarryOn" , "CheckedBags" , "Standard", "FlightFlex", "OptionalUI"})
    public void Flight_Flex_Packaging_Verbiage_Spanish_Flight_Car_Validate_verbiage_for_Flight_Flex_on_the_Hub_Page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC88614 under GoldFinger Suite on " + platform + " Browser", true);
        }

        /***************************Navigate to Passenger Info Page**************************************/
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
        final String UPGRADE_VALUE      = "BundleIt";

        //Bags Page constant values
        final String DEP_BAGS 			= "Carry_1|Checked_0";

        //Seats Page Constant values
        final String DEP_SEATS          = "Standard|Standard|Standard||Standard|Standard|Standard";
        final String RET_SEATS          = "Standard|Standard|Standard||Standard|Standard|Standard";

        //Option page constant values
        final String OPTION_TEXT_SPANISH	= "SELECCIONADO";

//Step1--Land on current test environment.
        //open browser
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

 //STEP--4 Select any car and proceed to the Next page
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("NA","NA");


//STEP--5 Select the Bundle it option in the pop up
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//STEP--6 Populate all the required information on the Passenger Information page and click Continue
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().selectPrimaryDriver();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//STEP--7 Scroll down and select Continue without changes.
        pageMethodManager.getBagsPageMethods().selectBagsFare("Standard");

//STEP--8 Select regular Seats for each passenger.
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

//STEP--9 On the Options page, click Espanol
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

//STEP--10 Verify that Flight Flex is displayed SELECTED under the Options section in the Options page.
        ValidationUtil.validateTestStep("Validating Flight Flex is preselected on Options Page",
                pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel().getText().toUpperCase().trim(),OPTION_TEXT_SPANISH);

//STEP--11 Scroll down to the Flight Flex Opciones title and verify the following verbiage:
        final String FLIGHT_FLEX_BODY_CARD_TEXT = "¡Modifique su vuelo una vez gratis!";

         ValidationUtil.validateTestStep("Verify Flight Flex body card verbiage on Option Page",
                 pageObjectManager.getOptionsPage().getFlightFlexCardBodyText().getText(),FLIGHT_FLEX_BODY_CARD_TEXT);

//STEP--12 Click the tool tip next to the verbiage with the Flight Flex tile
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



