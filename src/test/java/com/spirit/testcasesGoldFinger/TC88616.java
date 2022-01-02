package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC88616
//Description: Task 27833: TC88616- 006 - CP - Flight Flex Packaging Verbiage (Spanish) - Flight + Hotel - Validate verbiage for Flight Flex on the Hub Page
//Created By: Gabriela
//Created On: 21-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC88616 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"Spanish", "BookPath", "RoundTrip", "DomesticDomestic", "FlightHotel", "Outside21Days", "Adult",
                    "Guest", "BundleIt", "CarryOn","CheckedBags", "Standard","FlightFlex", "ShortCutBoarding","OptionalUI"})
    public void CP_Flight_Flex_Packaging_Verbiage_Spanish_Flight_Hotel_Validate_verbiage_for_Flight_Flex_on_the_Hub_Page(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC88616 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "130";
        final String ARR_DATE           = "133";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String UPGRADE_VALUE      = "BundleIt";

        //Seats Page Constant Values
        final String DEP_SEATS          = "Standard|Standard";
        final String RET_SEATS          = "Standard|Standard";


        //Options Page Constant Values
        final String FLIGT_FLEX_INFO    = "Flight Flex - ¡Modifique su vuelo una vez gratis!";
        final String POPUPBODY          = "El Flight Flex está disponible solamente al hacer la reservación inicial en spirit.com\n" +
                "El Flight Flex debe ser comprado para todos los pasajeros en la reservación. Solo se puede eximir un cargo por modificación cuando se compra el Flight Flex.\n" +
                "Solo puede modificar la hora, fecha y/o el origen/destino de la reservación. La nueva hora, fecha u origen/destino debe conocerse al momento de hacer el cambio.\n" +
                "Todas las modificaciones deben hacerse en línea en spirit.com con más de 24 horas de anticipación a su vuelo de salida o regreso programado.\n" +
                "Los cambios que no se hagan con más de 24 horas de anticipación estarán sujetos a los cambios por modificación estándar.\n" +
                "Por favor considere que usted todavía será responsable por cualquier diferencia en tarifa aérea y servicios opcionales por la(s) fecha(s) alterna(s) o vuelo(s) solicitado(s), como también por cualquier diferencia en los impuestos y cargos gubernamentales.\n" +
                "Podrían aplicar cargos de terceros por modificación y/o cancelación (p.ej. hotel/auto/actividad)";

//- Step 14: Land on current test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 1: Click on Vacation tab and select Flight + Hotel
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 2: Input the following: DOM_DOM | Any Date 3 months in the future | 2 PAX | 1 Room and click SEARCH VACATION
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Select any Hotel and  Book a Room, then click Continue.
        pageMethodManager.getFlightAvailabilityMethods().storeFlightDetailsForVacationBooking();
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA" , "NA");

//- Step 4: Scroll down and select CONTINUE WITHOUT CAR
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();

//- Step 5: Select the Bundle it option
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //*** Passenger Information Page Methods **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 7: Leave the default selection on the bags page for the Bundle it option and click the Continue button.
        pageMethodManager.getBagsPageMethods().continueWithOutChangesBag();

//- Step 8: Select Regular Seats for each passenger and click Continue.
        pageMethodManager.getSeatsPageMethods().selectDepartureSeats(DEP_SEATS);
        pageMethodManager.getSeatsPageMethods().selectReturningSeats(RET_SEATS);
        pageMethodManager.getSeatsPageMethods().continueWithSeats();

//- Step 9: On the Options page, click Español
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHomePage().getSelectedLanguage().get(0));
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 10: Verify that Flight Flex is displayed SELECTED under the Options section in the Options page.
        ValidationUtil.validateTestStep("Verifying Flight Flex is displayed as selected on Options page",
                TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardSelectedLabel()));

//- Step 11: Scroll down to the Flight Flex Opciones title and verify the following verbiage: ¡Modifique su vuelo una vez gratis!
        ValidationUtil.validateTestStep("Validating Right Spanish verbiage on Flight Flex card",
                pageObjectManager.getOptionsPage().getFlightFlexCardBodyText().getText(),FLIGT_FLEX_INFO);

//- Step 12: Click the tool tip next to the verbiage within the Flight Flex tile
        pageObjectManager.getOptionsPage().getFlightFlexCardToolTipLink().click();
        WaitUtil.untilTimeCompleted(1000);

//- Step 13: Verify the following verbiage is within the Flight Flex modal:
        ValidationUtil.validateTestStep("Validating Spanish translation on the Flight Flex Pop Up Information ",
                pageObjectManager.getOptionsPage().getFlightFlexToolTipPopUpBodyText().getText(),POPUPBODY);
    }
}

//*************** XPATH Options Page ********************/
//*******************************************************************
//*******************Flight Flex Options Page************************
//*******************************************************************
//public final String xpath_FlightFlexToolTipPopUpTitleText = "//app-branded-modal//h2";
//    @FindBy(xpath=xpath_FlightFlexToolTipPopUpTitleText)
//    private WebElement txt_FlightFlexToolTipPopUpTitle;

//public final String xpath_FlightFlexToolTipPopUpCloseButton = "//app-branded-modal//button[@class='close']";
//    @FindBy(xpath=xpath_FlightFlexToolTipPopUpCloseButton)
//    private WebElement btn_FlightFlexToolTipPopUpClose;

//public final String xpath_FlightFlexToolTipPopUpBodyText = "//app-branded-modal//h2//following::div//ul";
//    @FindBy(xpath=xpath_FlightFlexToolTipPopUpBodyText)
//    private WebElement txt_FlightFlexToolTipPopUpBody;

//public final String xpath_FlightFlexToolTipPopUpOkGotItButton = "//app-branded-modal//h2//following::div//button";
//    @FindBy(xpath=xpath_FlightFlexToolTipPopUpOkGotItButton)
//    private WebElement btn_FlightFlexToolTipPopUpOkGotIt;

//public WebElement getFlightFlexToolTipPopUpTitleText() {return txt_FlightFlexToolTipPopUpTitle;}

//public WebElement getFlightFlexToolTipPopUpCloseButton() {return btn_FlightFlexToolTipPopUpClose;}

//public WebElement getFlightFlexToolTipPopUpBodyText() {return txt_FlightFlexToolTipPopUpBody;}

//public WebElement getFlightFlexToolTipPopUpOkGotItButton() {return btn_FlightFlexToolTipPopUpOkGotIt;}