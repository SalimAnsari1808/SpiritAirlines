package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//TODO: IN:24709 - PROD: CP: BP: FA: Taxes and fees modal is missing "Most fares include Fuel Pass Through" in the details
//Test Case ID: TC91072
//Description: Task 24810: 35239 Flight Availability_CP_BP_Flight Only_RT-Early departure time-Standard-The Thrills Combo
//Created By : Gabriela
//Created On : 29-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 14-Aug-2019
//**********************************************************************************************

public class TC91072 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug","BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" ,
                    "Connecting" ,"RedEye", "BundleIt" , "FlightAvailabilityUI"})
    public void Flight_Availability_CP_BP_Flight_Only_RT_Early_departure_time_Standard_The_Thrills_Combo(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91072 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE                   = "English";
        final String JOURNEY_TYPE               = "Flight";
        final String TRIP_TYPE 	                = "RoundTrip";
        final String DEP_AIRPORTS 	            = "AllLocation";
        final String DEP_AIRPORT_CODE           = "FLL";
        final String ARR_AIRPORTS               = "AllLocation";
        final String ARR_AIRPORT_CODE 	        = "LAS";
        final String DEP_DATE                   = "3";
        final String ARR_DATE                   = "6";
        final String ADULTS	                    = "1";
        final String CHILDREN 	                = "0";
        final String INFANT_LAP	                = "0";
        final String INFANT_SEAT                = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                 = "Connecting";
        final String ARR_Flight                 = "Connecting";
        final String FARE_TYPE                  = "Standard";

        final String UNDER_TITLE_TEXT           = " Select your flight time and fare below. Once you select a flight" +
                " you'll be able to add on other options like bags, seats and extras. Additional charges for baggage," +
                " advance seats assignments and any changes apply only if you add these options.\n";

        final String UNDER_TITLE_TEXT1          = "Fares listed are per person, are non-refundable and include all taxes and fees";
        final String BLUE_COLOR 	            = "0, 115, 230";
        final String PAGE_HEADER                = "Choose Your Flight";
        final String FONT_BLACK                 = "0, 0, 0";
        final String FA_URL                     = "/book/flight";
        final String REFUNDABILITY_HEADER       = "Refundability";
        final String REFUNDABILITY_INFO         = "REFUNDS ARE ALLOWED FOR RESERVATIONS MADE ONE WEEK OR MORE PRIOR TO YOUR DEPARTURE, PROVIDED THAT YOU MAKE THE REFUND REQUEST WITHIN 24 HOURS OF YOUR INITIAL RESERVATION.";
        final String TAXES_AND_FEES_HEADER      = "Taxes And Fees";
        final String TAXES_AND_FEES_INFO        = "AIRTRAVELWITHINTHEUNITEDSTATESQUOTEDFAREINCLUDESTHEBASEFAREPLUSANYAPPLICABLETAXES,FEESANDSURCHARGES.THEFOLLOWINGGOVERNMENTTAXESANDFEESAPPLYTOYOURDOMESTICAIRTRAVEL:A7.5%FEDERALEXCISETAX(EXCEPTFORAWARDBOOKINGS)SEPTEMBER11THSECURITYFEE:ASEPTEMBER11THSECURITYFEEOF$5.60APPLIESPERPERSONEACHWAYFORTRAVELORIGINATINGATAU.S.AIRPORT.FEEIMPOSEDPERROUND-TRIPSHALLNOTEXCEED$11.20DOMESTICSEGMENTTAX:ASEGMENTTAXOF$4.20PERU.S.DOMESTICFLIGHTSEGMENT(AFLIGHTSEGMENTISDEFINEDASONETAKEOFFANDLANDING)(EXCEPTFORAWARDBOOKINGS)PASSENGERFACILITYCHARGES:UPTO$4.50PERFLIGHTSEGMENTWITHAMAXIMUMOFTWOPFCSCHARGEDONAONE-WAYTRIPORFOURPFCSONAROUNDTRIP,FORAMAXIMUMOF$18.00,INCERTAINPFC-APPROVEDU.S.AIRPORTS(EXCEPTFORAWARDBOOKINGS)THEFOLLOWINGCARRIERFEESAPPLYTOYOURDOMESTICAIRTRAVEL(EXCEPTFORAWARDBOOKINGS):PASSENGERUSAGEFEE:ANON-REFUNDABLEPASSENGERUSAGECHARGEOF$22.99PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.ALOWERFEEMAYAPPLYTOCERTAINDISCOUNTFARES.NOFEEAPPLIESTOBOOKINGSCOMPLETEDATSPIRITAIRLINES'AIRPORTLOCATIONS.REGULATORYCOMPLIANCECHARGEFEE:REGULATORYCOMPLIANCECHARGEFEEOF$7.00PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.NOFEEWILLAPPLYTOCERTAINDISCOUNTFARES.FUELCHARGE:FUELCHARGEOF$12.00PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.THISSURCHARGEWILLNOTAPPLYTOCERTAINDISCOUNTFARES.INTERNATIONALAIRTRAVEL(INCLUDINGPUERTORICOANDTHEU.S.VIRGINISLANDS)QUOTEDFAREINCLUDESTHEBASEFAREPLUSANYAPPLICABLETAXES,FEES,ANDSURCHARGES.THEFOLLOWINGGOVERNMENTTAXESANDFEESAPPLYTOYOURINTERNATIONALAIRTRAVEL:INTERNATIONALARRIVAL/DEPARTURETAXOF$18.60FOREACHARRIVALANDDEPARTURETOANDFROMTHEU.S.(EXCEPTFORAWARDBOOKINGS)SEPTEMBER11THSECURITYFEE:ASEPTEMBER11THSECURITYFEEOF$5.60APPLIESPERPERSONEACHWAYFORTRAVELORIGINATINGATAU.S.AIRPORT.FEEIMPOSEDPERROUND-TRIPSHALLNOTEXCEED$11.20.APHISUSERFEEOF$3.96PERTRAVELINGCUSTOMERUPONARRIVALTOTHEU.S.IMMIGRATIONUSERFEEOF$7.00PERTRAVELINGCUSTOMERUPONARRIVALTOTHEU.S.CUSTOMSUSERFEEOF$5.77PERTRAVELINGCUSTOMERUPONARRIVALTOTHEU.S.PASSENGERFACILITYCHARGES(PFC):UPTOPER$4.50PERFLIGHTSEGMENTWITHAMAXIMUMOFTWOPFCSCHARGEDONAONE-WAYTRIPORFOURPFCSONAROUNDTRIP,FORAMAXIMUMOF$18.00,INCERTAINPFC-APPROVEDU.S.AIRPORTS(EXCEPTFORAWARDBOOKINGS)NON-U.S.GOVERNMENTTAXESANDFEES:CERTAINFOREIGNCOUNTRIESMAYCHARGEADDITIONALTAXESANDFEESTHATARECOLLECTEDDIRECTLYBYTHELOCALGOVERNMENTORCOMPETENTAIRPORTAUTHORITY.THEFOLLOWINGCARRIERFEESAPPLYTOYOURINTERNATIONALAIRTRAVEL(EXCEPTFORAWARDBOOKINGS):PASSENGERUSAGECHARGE:ANON-REFUNDABLEPASSENGERUSAGECHARGEOF$22.99PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.ALOWERFEEMAYAPPLYTOCERTAINDISCOUNTFARES.NOFEEAPPLIESTOBOOKINGSCOMPLETEDATSPIRITAIRLINESAIRPORTLOCATIONS.REGULATORYCOMPLIANCECHARGEFEE:REGULATORYCOMPLIANCECHARGEFEEOF$7.00PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.NOFEEWILLAPPLYTOCERTAINDISCOUNTFARES.FUELCHARGE:FUELCHARGEOF$12.00PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.THISSURCHARGEWILLNOTAPPLYTOCERTAINDISCOUNTFARESALLAIRTRAVELALLFARESARESUBJECTTOCHANGEUNTILPURCHASEDSUBJECTTOCERTAINEXCEPTIONSAND/ORRESTRICTIONSSETFORTHINSPIRITAIRLINESCONTRACTOFCARRIAGE,ALLRESERVATIONSARENON-REFUNDABLEANDNON-TRANSFERABLE.REFUNDSAREALLOWEDFORRESERVATIONSMADE7DAYSORMOREPRIORTODEPARTUREANDPROVIDEDTHATAREFUNDREQUESTISMADEWITHIN24HOURSOFINITIALRESERVATION.CHANGESORCANCELLATIONSMADETOITINERARIESWILLBESUBJECTTOCHANGEFEES,ANYFAREDIFFERENTIAL,ANYDIFFERENCESINGOVERNMENTTAXESANDFEESAND,WITHTHEEXCEPTIONOFFEESFORCARRY-ONBAGSANDFIRSTANDSECONDCHECKED-INBAGS,ANYDIFFERENCEINCARRIERSANCILLARYCHARGES.FORACOMPLETELISTOFRULESANDREGULATIONSPLEASEREFERTOSPIRITAIRLINES'CONTRACTOFCARRIAGEPACKAGES/CARRENTALSRATESINCLUDEFLIGHTANDHOTELTAXESANDFEES.ADDITIONALMANDATORYRESORTFEESMAYBECHARGEDDIRECTLYBYTHEHOTEL.CARRENTALRATESDONOTINCLUDETAXES,AIRPORTFEES,INSURANCE,OPTIONS,UNDERAGEDRIVERCHARGES(IFAPPLICABLE)ORFUELCOSTS,ALLOFWHICHMUSTBEPAIDDIRECTLYTOTHERENTALCARCOMPANYANDARESUBJECTTOCHANGEBYEACHINDIVIDUALSTATEAND/ORLOCATION.OTHERFEESADDITIONALCHARGESFORBAGGAGE,ADVANCESEATASSIGNMENTSANDANYCHANGES,APPLYONLYIFYOUADDTHESEOPTIONS.SPIRITMAY,FROMTIMETOTIME,CONTRACTWITHTHIRDPARTIESTOPROVIDECERTAINADDITIONALPRODUCTSANDSERVICESTOITSCUSTOMERSINCLUDING,BUTNOTLIMITEDTO,TRAVELINSURANCE,HOTELS,ANDCARRENTALS.SPIRITMAYRECEIVECOMPENSATIONFROMSUCHTHIRDPARTIESINCONNECTIONWITHTHESEADDITIONALPRODUCTSANDSERVICES.ADVICETOPASSENGERSFEDERALLAWFORBIDSTHECARRIAGEOFHAZARDOUSMATERIALSABOARDAIRCRAFTINYOURLUGGAGEORONYOURPERSON.AVIOLATIONCANRESULTINFIVEYEARS'IMPRISONMENTANDPENALTIESOF$250,000ORMORE(49U.S.C.5124).HAZARDOUSMATERIALSINCLUDEEXPLOSIVES,COMPRESSEDGASES,FLAMMABLELIQUIDSANDSOLIDS,OXIDIZERS,POISONS,CORROSIVESANDRADIOACTIVEMATERIALS.EXAMPLES:PAINTS,LIGHTERFLUID,FIREWORKS,TEARGASES,OXYGENBOTTLES,ANDRADIO-PHARMACEUTICALS.THEREARESPECIALEXCEPTIONSFORSMALLQUANTITIES(UPTO70OUNCESTOTAL)OFMEDICINALANDTOILETARTICLESCARRIEDINYOURLUGGAGEANDCERTAINSMOKINGMATERIALSCARRIEDONYOURPERSON.FORFURTHERINFORMATIONCONTACTYOURAIRLINEREPRESENTATIVE.E-CIGARETTESANDOTHERBATTERY-POWEREDSMOKINGDEVICESAREPROHIBITEDFORUSEABOARDSPIRITAIRCRAFT.ADDITIONALLY,THESEDEVICESARENOTPERMITTEDINCHECKEDBAGGAGE.E-CIGARETTESANDOTHERBATTERY-POWEREDSMOKINGDEVICESAREPERMITTEDINCARRY-ONBAGGAGEONLY;HOWEVER,SOMECOUNTRIESPROHIBITTHECARRIAGEOFTHESEDEVICESINCARRY-ONBAGGAGE,CHECKEDBAGGAGE,AND/ORONONESPERSON,INWHICHCASESPIRITWILLABIDEBYSUCHPROHIBITION(S).SPARELITHIUMBATTERIESCANNOTBECARRIEDINCHECKEDBAGGAGE.";
        final String FUEL_PASS_THROUGH_URL      = "/howmuchismyfuel.html";
        final String CONTRACT_CARRIAGE_URL      = "/Documents/Contract_of_Carriage.pdf";
        final String BAGGAGE_URL                = "/optional-services#bags";
        final String SEAT_ASSIGNMENTS_URL       = "/optional-services#seats";
        final String ANY_CHANGES_URL            = "/optional-services#other";

        final String SORT_BY                    = "Price";

        final String DEP_FLIGHT_1               = "214";
        final String ARR_Flight_1               = "NonStop";
        final String UPGRADE_VALUE	            = "BundleIt";

        //Passenger Information Page Constant Values
        final String PASSENGER_INFO_URL         = "/book/passenger";

        //open browser
        openBrowser( platform);

        //***********************Home Page Methods********************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //***********************Flight Availability Methods********************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);

//-- Step 1: Validating verbiage displayed under Page Title
        ValidationUtil.validateTestStep("Validating verbiage displayed under Page Title",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageSubHeaderText().getText(),UNDER_TITLE_TEXT);

        ValidationUtil.validateTestStep("Validating Legal verbiage displayed under Page Title",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageLegalVerbiageText().getText(),UNDER_TITLE_TEXT1);


        //Defining variable for color comparision
        String baggageColor     = JSExecuteUtil.getElementBeforeProperty(getDriver(),pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityBaggageLink(), "color");
        String advColor         = JSExecuteUtil.getElementBeforeProperty(getDriver(),pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityAdvanceSeatsLink(), "color");
        String changeColor      = JSExecuteUtil.getElementBeforeProperty(getDriver(),pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityAnyChangesLink(), "color");
        String nonRefColor      = JSExecuteUtil.getElementBeforeProperty(getDriver(),pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityNonRefundableLink(), "color");
        String taxesColor       = JSExecuteUtil.getElementBeforeProperty(getDriver(),pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityTaxesAndFeesLink(), "color");

        //baggage link color
        ValidationUtil.validateTestStep("Verify baggage link color", baggageColor,BLUE_COLOR);

        //advance seats assignments link color
        ValidationUtil.validateTestStep("Verify advance seats assignments link color",advColor,BLUE_COLOR);

        //any changes link color
        ValidationUtil.validateTestStep("Verify any changes link color",changeColor,BLUE_COLOR);

        //non refundable link color
        ValidationUtil.validateTestStep("Verify any changes link color",nonRefColor,BLUE_COLOR);

        //taxes and fees link color
        ValidationUtil.validateTestStep("Verify any changes link color",taxesColor,BLUE_COLOR);

//-- Step 2: On Flight Availability page, verify the page title displays left aligned "CHOOSE YOUR FLIGHT" in black bold text
        ValidationUtil.validateTestStep("The page title is correct \"Choose Your Flight\" on Check-In Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText().getText(),PAGE_HEADER);

        ValidationUtil.validateTestStep("Verify Font is black",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText().getCssValue("color"), FONT_BLACK);

        ValidationUtil.validateTestStep("Flight Availability page URL validation", getDriver().getCurrentUrl(),FA_URL);

//-- Step 3: Click the non-refundable link
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityNonRefundableLink().click();
        WaitUtil.untilTimeCompleted(2000);
        ValidationUtil.validateTestStep("Validating refundability pop up header",
                pageObjectManager.getFlightAvailabilityPage().getRefundabilityPopUpHeaderText().getText(),REFUNDABILITY_HEADER);

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating refundability pop up info ",
                pageObjectManager.getFlightAvailabilityPage().getRefundabilityPopUpBodyInfoText().getText(),REFUNDABILITY_INFO);

//-- Step 4: Close popup by either clicking outside the popup, clicking the X, or clicking the CLOSE button
        pageObjectManager.getFlightAvailabilityPage().getRefundabilityPopUpCloseButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Flight Availability page URL validation", getDriver().getCurrentUrl(),FA_URL);

//-- Step 5: Click the "taxes and fees" link
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityTaxesAndFeesLink().click();

        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Validating taxes and fees pop up header",
                pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpHeaderText().getText(),TAXES_AND_FEES_HEADER);

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating refundability pop up info ",
                pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpBodyInfoText().getText().replace("CLOSE WINDOW", ""),TAXES_AND_FEES_INFO);

//-- Step 6: Close popup by either clicking outside the popup, clicking the X, or clicking the CLOSE button (Ignoring step due links inside pop up verification is required)

//-- Step 7: Click on the Fuel Pass Through link located on the first bullet under All Air Travel header and verify it redirects properly
        //TODO: IN:24709 - PROD: CP: BP: FA: Taxes and fees modal is missing "Most fares include Fuel Pass Through" in the details
        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpFuelPassThroughLink().click();
//        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(),1);
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(FUEL_PASS_THROUGH_URL));

//-- Step 8: Go back to the popup and click on Contract of Carriage link located on the last bullet under All Air Travel headed and verify it redirects properly
        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);
//        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpContractOfCarriageLink().click();
//        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(),1);
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(CONTRACT_CARRIAGE_URL));

//-- Step 9: Go back to the popup and click on baggage link located under OTHER FEES header and verify it redirects properly
        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);
//        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpBaggageLink().click();
//        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(),1);
//        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(BAGGAGE_URL));

//-- Step 10: Go back to the popup and click on advance seat assignments links located under OTHER FEES header and verify it redirects properly
        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);
//        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getFlightAvailabilityPage().getlnk_TaxesAndFeesPopUpSeatAssignmentLink().click();
//        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(),1);
//        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(SEAT_ASSIGNMENTS_URL));

//-- Step 11: Go back to the popup and click on any changes link located under OTHER FEES header and verify it redirects properly
        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);
//        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpAnyChangesLink().click();
//        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(),1);
//        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(ANY_CHANGES_URL));

        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);
//        WaitUtil.untilTimeCompleted(1200);

//-- Step 12: Close popup by either clicking the X, or clicking the CLOSE button
        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpCloseButton().click();
//        WaitUtil.untilTimeCompleted(12000);

        ValidationUtil.validateTestStep("Validating Flight Availability Page URL",
                getDriver().getCurrentUrl(), FA_URL);

//-- Step 13: On the Carousel, click the right facing arrow to move forward 5 weeks, then left facing arrow to move
// back 2 weeks and then select the date with the cheapest flight price available
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingJourneyEditButton().get(1).click();
//        WaitUtil.untilTimeCompleted(2000);

        //Moving forward 5 weeks
        for (int count = 0; count <= 4; count ++) {
            pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselRightButton().get(0).click();
            WaitUtil.untilPageLoadComplete(getDriver());
        }

        //Moving 2 weeks back
        for (int count = 0; count <= 1; count ++) {
            pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselLeftButton().get(0).click();
            WaitUtil.untilPageLoadComplete(getDriver());
        }

        for (int count = 0; count < pageObjectManager.getFlightAvailabilityPage().getDepartingWeekEachTilesGrid().size(); count ++) {
            if (pageObjectManager.getFlightAvailabilityPage().getDepartingWeekEachTilesGrid().get(count).isDisplayed()) {
                pageObjectManager.getFlightAvailabilityPage().getDepartingWeekEachTilesGrid().get(count).click();
                break;
            }
        }

//-- Step 14:  Click the Sort by drop down menu and select "Price"
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getFlightAvailabilityPage().getArrivalFlightEditButton().get(0).click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);

//-- Step 15: Visually select a flight with departure time between 12AM-4AM and click the number of stops link.
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNumberType("Dep", DEP_FLIGHT_1);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret",ARR_Flight_1);

        //Recording Departing Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();

        //Declaring Lists to store flight info
        WaitUtil.untilTimeCompleted(1000);
        List<String> depCityName    = new ArrayList<>();
        List<String> arrCityName    = new ArrayList<>();
        List<String> depTime        = new ArrayList<>();
        List<String> arrTime        = new ArrayList<>();
        List<String> nkInfo         = new ArrayList<>();

        //Storing Departure Cities Name for 1st journey
        for(WebElement depCity:pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText()){
            depCityName.add(depCity.getText().trim());
        }

        //Storing Arrival Cities Name for 1st journey
        for(WebElement arrcity: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText()){
            arrCityName.add(arrcity.getText().trim());
        }

        //Storing Departure Cities Time for 1st journey
        for(WebElement depTim: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText()){
            depTime.add(depTim.getText().trim());
        }

        //Storing Arrival Cities Time for 1st journey
        for (WebElement arTime: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText()){
            arrTime.add(arTime.getText().trim());
        }

        //Storing NK Number for 1st journey
        for (WebElement flightNum: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText()){
            nkInfo.add(flightNum.getText().trim());
        }

        //Closing Flight Info Pop Up
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

        //Recording Returning Journey Information
        pageObjectManager.getFlightAvailabilityPage().getSelectedReturningFlightNatureButton().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

        //Storing Departure Cities Name for 2nd journey
        for(WebElement depCity:pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureAirportsText()){
            depCityName.add(depCity.getText().trim());
        }

        //Storing Arrival Cities Name for 2nd journey
        for(WebElement arrcity: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalAirportsText()){
            arrCityName.add(arrcity.getText().trim());
        }

        //Storing Departure Cities Time for 2nd journey
        for(WebElement depTim: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpDepartureTimeText()){
            depTime.add(depTim.getText().trim());
        }

        //Storing Arrival Cities Time for 2nd journey
        for (WebElement arTime: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpArrivalTimeText()){
            arrTime.add(arTime.getText().trim());
        }

        //Storing NK Number for 2nd journey
        for (WebElement flightNum: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText()){
            nkInfo.add(flightNum.getText().trim());
        }

//-- Step 16: Close the popup and click on View Seat Map link to verify the appropriate seat map is displaying
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getFlightAvailabilityPage().getSelectedDepatingFlightViewSeatMapButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating seat map per leg displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getFlightLegSeatMapLabel()));


//-- Step 17: Capture the seat map image for later comparison on the Seats page
        WaitUtil.untilTimeCompleted(1000);
        //Closing seat map pop up
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getFlightAvailabilityPage().getCloseSeatMapButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 18: Select the standard price flight by clicking the radio button.
        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating 'Early Departure' pop up displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getEarlyDepartureHeaderText()));

//-- Step 19: Close popup by either clicking the X, or clicking the CLOSE button
        pageObjectManager.getFlightAvailabilityPage().getEarlyDepartureCloseButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 20:  Select a returning flight (Returning flight selected on Step 15)

//-- Step 21: Locate the black content block with left aligned white font verbiage FLIGHTS TOTAL and verify the price is accurate with the one selected.
        double depFlightPrice = Double.parseDouble(pageObjectManager.getFlightAvailabilityPage().getDepartureFlightBlockPriceText().getText().replace("$", ""));

        double retFlightPrice = Double.parseDouble(pageObjectManager.getFlightAvailabilityPage().getArrivalFlightBlockPriceText().getText().replace("$", ""));

        Double totalFlight =  (retFlightPrice + depFlightPrice);
        String totalFlightPrice = totalFlight.toString();
        ValidationUtil.validateTestStep("Validating total price is accurate",
                totalFlightPrice,pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText().replace("$",""));

//-- Step 22: Click on the Caret/Down arrow to expand the flight total section
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().click();
        WaitUtil.untilTimeCompleted(12000);
        ValidationUtil.validateTestStep("Validating Total Break Down is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getFlightTotalBreakDownCityPairText()));

//-- Step 23: Verify there are two tiles under the FLIGHTS TOTAL section
        ValidationUtil.validateTestStep("Validating 2 tiles are displayed",
                pageObjectManager.getFlightAvailabilityPage().getFareContainerPanel().size()==2);

        ValidationUtil.validateTestStep("Standard price validation",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getStandardFarePriceText()));

        ValidationUtil.validateTestStep("9FC price validation",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getMemberFarePriceText()));

//-- Step 24: Click "Continue" on the option from the pre-requisite (Standard or $9FC)
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        WaitUtil.untilTimeCompleted(2000);

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating 'Treat yourself to a bundle and save' pop up displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getBundleFareHeaderText()));

//-- Step 25: Verify there is a savings amount for "Thrills Combo" on the upper right side "Save up to" and that the price for this option is higher than the Bundle price
        WaitUtil.untilTimeCompleted(1200);
        ValidationUtil.validateTestStep("Validating Save Up To text is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getBundleItSaveUpToPriceText()));

        double bundleItSaveUpTo = Double.parseDouble(pageObjectManager.getFlightAvailabilityPage().getBundleItSaveUpToPriceText().getText().replace("Save Up To $", ""));
        double boostItSaveUpTo = Double.parseDouble(pageObjectManager.getFlightAvailabilityPage().getBoostItSaveUpToPriceText().getText().replace("Save Up To $", ""));

        ValidationUtil.validateTestStep("BoostIt price is lower compared with BundleIt",
                bundleItSaveUpTo > boostItSaveUpTo);

//-- Step 26: Write down the price for "Thrills Combo" option ()
        double BundleItPrice = Double.parseDouble(pageObjectManager.getFlightAvailabilityPage().getBundleItPriceText().getText().replace("+ $", ""));

//-- Step 27: Click on "Choose Thrills Combo"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //***********************Passenger Information Page Methods********************************/
        ValidationUtil.validateTestStep("Validating Passenger Information Page URL",
                getDriver().getCurrentUrl(), PASSENGER_INFO_URL);

        pageMethodManager.getPassengerInfoMethods().verifySelectedBaseFarePassengerInfo(UPGRADE_VALUE);
    }
}