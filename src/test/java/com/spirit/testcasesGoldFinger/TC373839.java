package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC373839
//Description: Task 27239: TC373839 - 007 - CP - Car Verbiage - Dollar - Flight + Car - Validate correct verbiage displays - 5 ADT
//Created By: Gabriela
//Created On: 26-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373839 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "FlightCar", "Outside21Days", "Adult", "Guest","CarsUI","FlightAvailabilityUI","Spanish"})
    public void CP_Car_Verbiage_Dollar_Flight_Car_Validate_correct_verbiage_displays_5_ADT(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373839 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        final String ADULT              = "5";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "25+";

        //Flight Availability Page Constant Values
        final String F_C_URL                = "/book/flights-cars";
        final String CAR_VERBAGE1           = "Descripción del Vehículo";
        final String VEHICLE_DESCRIPTION1   ="Por favor tenga en cuenta";
        final String VEHICLE_DESCRIPTION2   ="La persona que alquilará debe presentar una tarjeta de crédito principal válida a su nombre con un crédito disponible en la cantidad del depósito (si aplica). En algunas ubicaciones, las tarjetas de débito principales se pueden usar para asegurar su alquiler. En la mayoría de las ubicaciones en los aeropuertos, se aceptan tarjetas de débito para calificar para alquilar cuando el cliente provee evidencia de un vuelo de regreso que coincida con el alquiler y (2) formas válidas de identificación. Para información específica relacionada con la aceptación de tarjetas de débito y otras políticas, comuníquese con su ubicación de alquiler.";
        final String VEHICLE_DESCRIPTION3   ="Aplican las siguientes excepciones:";
        final String VEHICLE_DESCRIPTION4   ="Es posible que las tarjetas de débito no se acepten al momento de alquilar en los siguientes ciudades y áreas circundantes, por favor comuníquese con la oficina local para detalles: el área metropolitana de Nueva York (Nueva York, New Jersey, Connecticut), Hartford, Connecticut; Philadelphia, Pennsylvania; Boston, Massachusetts; Manchester, New Hampshire; y Detroit, Michigan.";
        final String VEHICLE_DESCRIPTION5   ="Se requiere una licencia de conducir válida para alquilar. DOLLAR podría verificar el estado de su licencia de conducir y su historial de conductor. DOLLAR podría denegar el alquiler basado en su historial de conductor. La marca o modelo del auto no se puede garantizar y se podría sustituir por un auto similar o más grande. Por favor verifique con las instalaciones de alquiler de autos acerca de restricciones adicionales o servicios opcionales. Los días de alquiler que no sean usados no son elegibles para un reembolso. Un pago adicional podría ser necesario de solicitarse días de alquiler adicionales al devolver el vehículo.";
        final String MINIMUM_AGE_NOTE       = "En algunos estados, los conductores deben tener 25 años de edad o más. Cuando esté permitido, los conductores menores de 25 años de edad pueden estar sujetos a recargos pagaderos directamente al suplidor de autos para alquiler al momento de alquilar.";

//- Step 1: Using Google Chrome, access Spirit home page in test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Click on Vacation tab, and click on Flight+Car
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);

//- Step 3: Book DOM-DOM | 3 months out | 5 ADT | Driver 25+ | Select "Search Vacation"
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verify user land on F + H Page",
                getDriver().getCurrentUrl(),F_C_URL);

//- Step 4: Select Espanol and verify page changes to Spanish
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHomePage().getSelectedLanguage().get(0));
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 5: Search for a Dollar.  Click "Mas" link.
        pageMethodManager.getCarPageMethods().filterCarByRentalAgency("Dollar");
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Verify "+CAR_VERBAGE1+" is displayed on Car Modal PopUp",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab().getText(),CAR_VERBAGE1);

        //verify the vehicle description under more infor link
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


//- Step 6: Click the "Políticas" tab and Verify top to bottom:
        pageObjectManager.getCarPage().getCarPopUpPoliciesTab().click();
        WaitUtil.untilTimeCompleted(1000);

//        ValidationUtil.validateTestStep("What's Included Text is displayed on Car PopUp under Policy Section",
//                pageObjectManager.getCarPage().getCarPopUpPoliciesWhatsIncludesText().getText(),"Lo que está incluido");

        ValidationUtil.validateTestStep("Rental inclusions Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalInclusionsText().getText(),"Incluido en el alquiler");

        ValidationUtil.validateTestStep("Rental exclusions Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalExclusionsText().getText(),"No está incluido en el alquiler");

        ValidationUtil.validateTestStep("Rental rules Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRentalRulesText().getText(),"Reglas del alquiler");

        ValidationUtil.validateTestStep("Child Seats Required by Law Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(0).getText(),"Asientos de seguridad para niños requeridos por la ley");

        ValidationUtil.validateTestStep("Child Seats Required by Law \"Yes\" Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(1).getText(),"Sí");

        ValidationUtil.validateTestStep("Check with the rental counter or state to verify requirements Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(2).getText(),
                "Preguntar en el mostrador de alquiler o el estado para verificar los requisitos.");

        ValidationUtil.validateTestStep("Minimum Age to Rent Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().get(0).getText(),
                "Edad mínima para alquilar");

        ValidationUtil.validateTestStep("Minimum Age to Rent \"21\" Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().get(1).getText(), "21");

        ValidationUtil.validateTestStep("Minimum Age to Rent Note Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().get(2).getText(), MINIMUM_AGE_NOTE);

        ValidationUtil.validateTestStep("Refund for Unused Rental Days/Hours Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRefundForUnusedText().get(0).getText(), "Reembolso por días/horas de alquiler no usadas");

        ValidationUtil.validateTestStep("Refund for Unused Rental Days/Hours \"NO\" Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRefundForUnusedText().get(1).getText(), "No");

        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().get(0).getText(), "Se requiere una licencia de conducir válida");

//- Step 7: Click on the "Ubicacion" tab, verify top to bottom:
        pageObjectManager.getCarPage().getCarPopUpLocationsTab().click();
        //Location verification from CARNECT need to be implemented

    }
}