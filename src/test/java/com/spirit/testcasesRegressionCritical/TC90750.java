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
//Test Case ID: TC90750
//Description: Task 24807: 35238 Flight Availability_CP_BP_Early departure time-Standard-The Bundle
//Created By : Gabriela
//Created On : 14-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 16-Aug-2019
//**********************************************************************************************

public class TC90750 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug" , "BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "BundleIt" , "CarryOn" , "CheckedBags" , "Standard" , "FlightAvailabilityUI" , "BagsUI" , "SeatsUI" , "PaymentUI" })
    public void Flight_Availability_CP_BP_Early_departure_time_Standard_The_Bundle(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90750 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "OneWay";
        final String DEP_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "LAS";
        final String ARR_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "DFW";
        final String DEP_DATE               = "2";
        final String ARR_DATE               = "NA";
        final String ADULT                  = "2";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Constant Values
        final String SORT_BY                = "Price";
        final String UNDER_TITLE_TEXT       = " Select your flight time and fare below. Once you select a flight" +
                " you'll be able to add on other options like bags, seats and extras. Additional charges for baggage," +
                " advance seats assignments and any changes apply only if you add these options.\n";
        final String UNDER_TITLE_TEXT1      = "Fares listed are per person, are non-refundable and include all taxes and fees";

        final String BLUE_COLOR             = "rgb(0, 115, 230)";
        final String PAGE_HEADER            = "Choose Your Flight";

        final String REFUNDABILITY_HEADER   = "Refundability";
        final String REFUNDABILITY_INFO     = "REFUNDS ARE ALLOWED FOR RESERVATIONS MADE ONE WEEK OR MORE PRIOR TO YOUR DEPARTURE, PROVIDED THAT YOU MAKE THE REFUND REQUEST WITHIN 24 HOURS OF YOUR INITIAL RESERVATION.";

        final String TAXES_AND_FEES_HEADER  = "Taxes And Fees";
        final String TAXES_AND_FEES_INFO    = "AIRTRAVELWITHINTHEUNITEDSTATESQUOTEDFAREINCLUDESTHEBASEFAREPLUSANYAPPLICABLETAXES,FEESANDSURCHARGES.THEFOLLOWINGGOVERNMENTTAXESANDFEESAPPLYTOYOURDOMESTICAIRTRAVEL:A7.5%FEDERALEXCISETAX(EXCEPTFORAWARDBOOKINGS)SEPTEMBER11THSECURITYFEE:ASEPTEMBER11THSECURITYFEEOF$5.60APPLIESPERPERSONEACHWAYFORTRAVELORIGINATINGATAU.S.AIRPORT.FEEIMPOSEDPERROUND-TRIPSHALLNOTEXCEED$11.20DOMESTICSEGMENTTAX:ASEGMENTTAXOF$4.20PERU.S.DOMESTICFLIGHTSEGMENT(AFLIGHTSEGMENTISDEFINEDASONETAKEOFFANDLANDING)(EXCEPTFORAWARDBOOKINGS)PASSENGERFACILITYCHARGES:UPTO$4.50PERFLIGHTSEGMENTWITHAMAXIMUMOFTWOPFCSCHARGEDONAONE-WAYTRIPORFOURPFCSONAROUNDTRIP,FORAMAXIMUMOF$18.00,INCERTAINPFC-APPROVEDU.S.AIRPORTS(EXCEPTFORAWARDBOOKINGS)THEFOLLOWINGCARRIERFEESAPPLYTOYOURDOMESTICAIRTRAVEL(EXCEPTFORAWARDBOOKINGS):PASSENGERUSAGEFEE:ANON-REFUNDABLEPASSENGERUSAGECHARGEOF$22.99PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.ALOWERFEEMAYAPPLYTOCERTAINDISCOUNTFARES.NOFEEAPPLIESTOBOOKINGSCOMPLETEDATSPIRITAIRLINES'AIRPORTLOCATIONS.REGULATORYCOMPLIANCECHARGEFEE:REGULATORYCOMPLIANCECHARGEFEEOF$7.00PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.NOFEEWILLAPPLYTOCERTAINDISCOUNTFARES.FUELCHARGE:FUELCHARGEOF$12.00PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.THISSURCHARGEWILLNOTAPPLYTOCERTAINDISCOUNTFARES.INTERNATIONALAIRTRAVEL(INCLUDINGPUERTORICOANDTHEU.S.VIRGINISLANDS)QUOTEDFAREINCLUDESTHEBASEFAREPLUSANYAPPLICABLETAXES,FEES,ANDSURCHARGES.THEFOLLOWINGGOVERNMENTTAXESANDFEESAPPLYTOYOURINTERNATIONALAIRTRAVEL:INTERNATIONALARRIVAL/DEPARTURETAXOF$18.60FOREACHARRIVALANDDEPARTURETOANDFROMTHEU.S.(EXCEPTFORAWARDBOOKINGS)SEPTEMBER11THSECURITYFEE:ASEPTEMBER11THSECURITYFEEOF$5.60APPLIESPERPERSONEACHWAYFORTRAVELORIGINATINGATAU.S.AIRPORT.FEEIMPOSEDPERROUND-TRIPSHALLNOTEXCEED$11.20.APHISUSERFEEOF$3.96PERTRAVELINGCUSTOMERUPONARRIVALTOTHEU.S.IMMIGRATIONUSERFEEOF$7.00PERTRAVELINGCUSTOMERUPONARRIVALTOTHEU.S.CUSTOMSUSERFEEOF$5.77PERTRAVELINGCUSTOMERUPONARRIVALTOTHEU.S.PASSENGERFACILITYCHARGES(PFC):UPTOPER$4.50PERFLIGHTSEGMENTWITHAMAXIMUMOFTWOPFCSCHARGEDONAONE-WAYTRIPORFOURPFCSONAROUNDTRIP,FORAMAXIMUMOF$18.00,INCERTAINPFC-APPROVEDU.S.AIRPORTS(EXCEPTFORAWARDBOOKINGS)NON-U.S.GOVERNMENTTAXESANDFEES:CERTAINFOREIGNCOUNTRIESMAYCHARGEADDITIONALTAXESANDFEESTHATARECOLLECTEDDIRECTLYBYTHELOCALGOVERNMENTORCOMPETENTAIRPORTAUTHORITY.THEFOLLOWINGCARRIERFEESAPPLYTOYOURINTERNATIONALAIRTRAVEL(EXCEPTFORAWARDBOOKINGS):PASSENGERUSAGECHARGE:ANON-REFUNDABLEPASSENGERUSAGECHARGEOF$22.99PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.ALOWERFEEMAYAPPLYTOCERTAINDISCOUNTFARES.NOFEEAPPLIESTOBOOKINGSCOMPLETEDATSPIRITAIRLINESAIRPORTLOCATIONS.REGULATORYCOMPLIANCECHARGEFEE:REGULATORYCOMPLIANCECHARGEFEEOF$7.00PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.NOFEEWILLAPPLYTOCERTAINDISCOUNTFARES.FUELCHARGE:FUELCHARGEOF$12.00PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.THISSURCHARGEWILLNOTAPPLYTOCERTAINDISCOUNTFARESALLAIRTRAVELALLFARESARESUBJECTTOCHANGEUNTILPURCHASEDSUBJECTTOCERTAINEXCEPTIONSAND/ORRESTRICTIONSSETFORTHINSPIRITAIRLINESCONTRACTOFCARRIAGE,ALLRESERVATIONSARENON-REFUNDABLEANDNON-TRANSFERABLE.REFUNDSAREALLOWEDFORRESERVATIONSMADE7DAYSORMOREPRIORTODEPARTUREANDPROVIDEDTHATAREFUNDREQUESTISMADEWITHIN24HOURSOFINITIALRESERVATION.CHANGESORCANCELLATIONSMADETOITINERARIESWILLBESUBJECTTOCHANGEFEES,ANYFAREDIFFERENTIAL,ANYDIFFERENCESINGOVERNMENTTAXESANDFEESAND,WITHTHEEXCEPTIONOFFEESFORCARRY-ONBAGSANDFIRSTANDSECONDCHECKED-INBAGS,ANYDIFFERENCEINCARRIERSANCILLARYCHARGES.FORACOMPLETELISTOFRULESANDREGULATIONSPLEASEREFERTOSPIRITAIRLINES'CONTRACTOFCARRIAGEPACKAGES/CARRENTALSRATESINCLUDEFLIGHTANDHOTELTAXESANDFEES.ADDITIONALMANDATORYRESORTFEESMAYBECHARGEDDIRECTLYBYTHEHOTEL.CARRENTALRATESDONOTINCLUDETAXES,AIRPORTFEES,INSURANCE,OPTIONS,UNDERAGEDRIVERCHARGES(IFAPPLICABLE)ORFUELCOSTS,ALLOFWHICHMUSTBEPAIDDIRECTLYTOTHERENTALCARCOMPANYANDARESUBJECTTOCHANGEBYEACHINDIVIDUALSTATEAND/ORLOCATION.OTHERFEESADDITIONALCHARGESFORBAGGAGE,ADVANCESEATASSIGNMENTSANDANYCHANGES,APPLYONLYIFYOUADDTHESEOPTIONS.SPIRITMAY,FROMTIMETOTIME,CONTRACTWITHTHIRDPARTIESTOPROVIDECERTAINADDITIONALPRODUCTSANDSERVICESTOITSCUSTOMERSINCLUDING,BUTNOTLIMITEDTO,TRAVELINSURANCE,HOTELS,ANDCARRENTALS.SPIRITMAYRECEIVECOMPENSATIONFROMSUCHTHIRDPARTIESINCONNECTIONWITHTHESEADDITIONALPRODUCTSANDSERVICES.ADVICETOPASSENGERSFEDERALLAWFORBIDSTHECARRIAGEOFHAZARDOUSMATERIALSABOARDAIRCRAFTINYOURLUGGAGEORONYOURPERSON.AVIOLATIONCANRESULTINFIVEYEARS'IMPRISONMENTANDPENALTIESOF$250,000ORMORE(49U.S.C.5124).HAZARDOUSMATERIALSINCLUDEEXPLOSIVES,COMPRESSEDGASES,FLAMMABLELIQUIDSANDSOLIDS,OXIDIZERS,POISONS,CORROSIVESANDRADIOACTIVEMATERIALS.EXAMPLES:PAINTS,LIGHTERFLUID,FIREWORKS,TEARGASES,OXYGENBOTTLES,ANDRADIO-PHARMACEUTICALS.THEREARESPECIALEXCEPTIONSFORSMALLQUANTITIES(UPTO70OUNCESTOTAL)OFMEDICINALANDTOILETARTICLESCARRIEDINYOURLUGGAGEANDCERTAINSMOKINGMATERIALSCARRIEDONYOURPERSON.FORFURTHERINFORMATIONCONTACTYOURAIRLINEREPRESENTATIVE.E-CIGARETTESANDOTHERBATTERY-POWEREDSMOKINGDEVICESAREPROHIBITEDFORUSEABOARDSPIRITAIRCRAFT.ADDITIONALLY,THESEDEVICESARENOTPERMITTEDINCHECKEDBAGGAGE.E-CIGARETTESANDOTHERBATTERY-POWEREDSMOKINGDEVICESAREPERMITTEDINCARRY-ONBAGGAGEONLY;HOWEVER,SOMECOUNTRIESPROHIBITTHECARRIAGEOFTHESEDEVICESINCARRY-ONBAGGAGE,CHECKEDBAGGAGE,AND/ORONONESPERSON,INWHICHCASESPIRITWILLABIDEBYSUCHPROHIBITION(S).SPARELITHIUMBATTERIESCANNOTBECARRIEDINCHECKEDBAGGAGE.";


        final String FUEL_PASS_THROUGH_URL  = "/howmuchismyfuel.html";
        final String CONTRACT_CARRIAGE_URL  = "/Documents/Contract_of_Carriage.pdf";
        final String BAGGAGE_URL            = "/optional-services#bags";
        final String SEAT_ASSIGNMENTS_URL   = "/optional-services#seats";
        final String ANY_CHANGES_URL        = "/optional-services#other";
        final String FA_URL                 = "/book/flights";
        final String FLIGHTS_TOTAL_TEXT     = "FLIGHTS TOTAL";
        final String MEMBER_TEXT            = "$9 FARE CLUB";
        final String STANDARD_TEXT          = "STANDARD";
        final String EARLY_DEPARTURE_TEXT   = "Early Departure";
        final String UPGRADE_VALUE          = "BundleIt";

        //Passenger Information Constant Values
        final String PASSENGER_INFO_URL     = "/book/passenger";

        //Options Constant Values
        final String OPTION_VALUE           = "CheckInOption:MobileFree";

        //open browser
        openBrowser(platform);

        /*********************************HOME PAGE******************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*********************************Flight Availability Methods*************************************************/
//-- Step 1: Validating verbiage displayed under Page Title
        ValidationUtil.validateTestStep("Validating verbiage displayed under Page Title",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageSubHeaderText().getText(),UNDER_TITLE_TEXT);

        ValidationUtil.validateTestStep("Validating Legal verbiage displayed under Page Title",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageLegalVerbiageText().getText(),UNDER_TITLE_TEXT1);


        //Defining variable for color comparision
        String baggageColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityBaggageLink(), "color");
        String advColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityAdvanceSeatsLink(), "color");
        String changeColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityAnyChangesLink(), "color");
        String nonRefColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityNonRefundableLink(), "color");
        String taxesColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityTaxesAndFeesLink(), "color");

        //baggage link color
        ValidationUtil.validateTestStep("Verify baggage link color", baggageColor, BLUE_COLOR);

        //advance seats assignments link color
        ValidationUtil.validateTestStep("Verify advance seats assignments link color", advColor, BLUE_COLOR);

        //any changes link color
        ValidationUtil.validateTestStep("Verify Any Changes link color", changeColor, BLUE_COLOR);

        //non refundable link color
        ValidationUtil.validateTestStep("Verify No Refundable link color", nonRefColor, BLUE_COLOR);

        //taxes and fees link color
        ValidationUtil.validateTestStep("Verify Taxes and Fees link color", taxesColor, BLUE_COLOR);


//-- Step 2: On Flight Availability page, verify the page title displays left aligned "CHOOSE YOUR FLIGHT" in black bold text
        ValidationUtil.validateTestStep("The page title is correct \"Choose Your Flight\" on Check-In Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText().getText(), PAGE_HEADER);

//-- Step 3: Click the non-refundable link
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityNonRefundableLink().click();
        WaitUtil.untilTimeCompleted(2000);


        ValidationUtil.validateTestStep("Validating refundability pop up header",
                pageObjectManager.getFlightAvailabilityPage().getRefundabilityPopUpHeaderText().getText(), REFUNDABILITY_HEADER);

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating refundability pop up info ",
                pageObjectManager.getFlightAvailabilityPage().getRefundabilityPopUpBodyInfoText().getText(), REFUNDABILITY_INFO);

        pageObjectManager.getFlightAvailabilityPage().getRefundabilityPopUpCloseButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 4: Click the "taxes and fees" link
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityTaxesAndFeesLink().click();
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("Validating taxes and fees pop up header",
                pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpHeaderText().getText(), TAXES_AND_FEES_HEADER);

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating taxes and fees pop up info ",
                pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpBodyInfoText().getText().replace("CLOSE WINDOW", ""), TAXES_AND_FEES_INFO);

//-- Step 5: Click on the Fuel Pass Through link located on the first bullet under All Air Travel header and verify it redirects properly
        //TODO: IN:24709 - PROD: CP: BP: FA: Taxes and fees modal is missing "Most fares include Fuel Pass Through" in the details
        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpFuelPassThroughLink().click();
        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(), 1);
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(FUEL_PASS_THROUGH_URL));

//-- Step 6: Go back to the popup and click on Contract of Carriage link located on the last bullet under All Air Travel headed and verify it redirects properly
        getDriver().close();
        TestUtil.switchToWindow(getDriver(), 0);
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpContractOfCarriageLink().click();
        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(), 1);
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(CONTRACT_CARRIAGE_URL));

//-- Step 7: Go back to the popup and click on baggage link located under OTHER FEES header and verify it redirects properly
        getDriver().close();
        TestUtil.switchToWindow(getDriver(), 0);
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpBaggageLink().click();
        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(), 1);
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(BAGGAGE_URL));

//-- Step 8: Go back to the popup and click on advance seat assignments links located under OTHER FEES header and verify it redirects properly
        getDriver().close();
        TestUtil.switchToWindow(getDriver(), 0);
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getFlightAvailabilityPage().getlnk_TaxesAndFeesPopUpSeatAssignmentLink().click();
        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(), 1);
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(SEAT_ASSIGNMENTS_URL));

//-- Step 9: Go back to the popup and click on any changes link located under OTHER FEES header and verify it redirects properly
        getDriver().close();
        TestUtil.switchToWindow(getDriver(), 0);
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpAnyChangesLink().click();
        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(), 1);
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(ANY_CHANGES_URL));

        getDriver().close();
        TestUtil.switchToWindow(getDriver(), 0);
        WaitUtil.untilTimeCompleted(1200);

//-- Step 10: Close popup by either clicking the X, or clicking the CLOSE button
        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(12000);

        ValidationUtil.validateTestStep("Validating Flight Availability Page URL",
                getDriver().getCurrentUrl(), FA_URL);

//-- Step 11: Click the Month option on the slider button to expand the calendar.
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMonthViewSwitchLabel().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating calendar is expanded",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMonthViewSwitchLabel()));

//-- Step 12: Click the right facing arrow to move forward 2 months and then select the date with the cheapest flight price available
        for (int i = 0; i <= 1; i++) {
            pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselRightButton().get(0).click();
            WaitUtil.untilPageLoadComplete(getDriver());
        }

        pageObjectManager.getFlightAvailabilityPage().getDepartingMonthsDateEachTile().get(15).click();
        WaitUtil.untilPageLoadComplete(getDriver());


//-- Step 13: Click the Sort by drop down menu and select "Price"
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        WaitUtil.untilTimeCompleted(3000);

        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", "Departure");
        pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(0).click();

//-- Step 14: Close popup by either clicking the X, or clicking the CLOSE button
        pageObjectManager.getFlightAvailabilityPage().getSeletedDepatingFlightNatureButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1300);

        //Declaring Lists to store flight info
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

//-- Step 15: Close the popup and click on View Seat Map link to verify the appropriate seat map is displaying
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);

        pageObjectManager.getFlightAvailabilityPage().getSelectedDepatingFlightViewSeatMapButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);
        ValidationUtil.validateTestStep("Verify the appropriate seat map is displaying",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getCloseSeatMapButton()));

        //app-unit//div[contains(@class,'unavailable')]
        List<Integer> unavailableSeats = new ArrayList<>();
        List<Integer> availableSeats = new ArrayList<>();

//-- Step 16:  Capture the seat map image for later comparison on the Seats page
        // Retrieving availability on seats map for future validation
        for (int count = 0; count < pageObjectManager.getFlightAvailabilityPage().getAllSeatsGridView().size(); count++) {
            if (pageObjectManager.getFlightAvailabilityPage().getAllSeatsGridView().get(count).getAttribute("class").contains("unavailable")) {
                unavailableSeats.add(count);
            } else {
                availableSeats.add(count);
            }
        }

//- Step 17: Close popup by either clicking the X, or clicking the CLOSE button
        //Closing seat map
        pageObjectManager.getFlightAvailabilityPage().getCloseSeatMapButton().click();
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("Validating Flight Availability Page URL", getDriver().getCurrentUrl(), FA_URL);


//-- Step 18: Locate the Upsell/Promotion tile surrounded by grey border under the selected flight section.
        // Invalid Step

//-- Step 19: Select the standard price flight by clicking the radio button.
        //Invalid Step. Flight is already selected

//-- Step 20: Locate the black content block with left aligned white font verbiage FLIGHTS TOTAL and verify the price is accurate with the one selected.
        ValidationUtil.validateTestStep("Validating Total Flight Dropdown text",
                pageObjectManager.getFlightAvailabilityPage().getFlightTotalTextText().getText(), FLIGHTS_TOTAL_TEXT);

        //Flight price displayed on flight tile
        double flightPrice = Double.parseDouble(pageObjectManager.getFlightAvailabilityPage().getDepartureFlightBlockPriceText().getText().replace("$", ""));

        //Getting flight price per passenger amount
        double totalPrice = flightPrice * 2;

        //Flight price displayed on the dropdown
        double totalDropDow = Double.parseDouble(pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText().replace("$", ""));

        ValidationUtil.validateTestStep("Validating prices matching", totalDropDow == totalPrice);

//-- Step 21: Click on the Caret/Down arrow to expand the flight total section
        pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().click();

        ValidationUtil.validateTestStep("Validating right price information displayed on the total price dropdown",
                pageObjectManager.getFlightAvailabilityPage().getFlightTotalAmountText().getText(),
                pageObjectManager.getFlightAvailabilityPage().getFlightTotalBreakDownCityPairPriceText().getText());

//-- Step 22:  Verify there are two tiles under the FLIGHTS TOTAL section
        int count = 0;
        for (int fareCount = 0; fareCount < pageObjectManager.getFlightAvailabilityPage().getFareContainerPanel().size(); fareCount++) {
            count++;
        }
        ValidationUtil.validateTestStep("Validating there is 2 tiles displayed",
                count == 2);
        ValidationUtil.validateTestStep("Validating member tile title",
                pageObjectManager.getFlightAvailabilityPage().getMemberTypeText().getText(), MEMBER_TEXT);
        ValidationUtil.validateTestStep("Validating standard tile title",
                pageObjectManager.getFlightAvailabilityPage().getStandardTypeText().getText(), STANDARD_TEXT);

//-- Step 23: Click the CONTINUE button on Standard Price Tile
        pageObjectManager.getFlightAvailabilityPage().getStandardFareButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating Early Departure is displayed",
                pageObjectManager.getFlightAvailabilityPage().getEarlyDepartureHeaderText().getText(), EARLY_DEPARTURE_TEXT);

        //Closing early departure pop up
        pageObjectManager.getFlightAvailabilityPage().getEarlyDepartureContinueButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 24: Click the CHOOSE THE BUNDLE option
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /************************ Passenger Info Page Methods **************************************/
        //Treat yourself to a bundle and save popup should close and move forward to the Passenger Page
        ValidationUtil.validateTestStep("Validating Passenger Information Page URL",
                getDriver().getCurrentUrl(), PASSENGER_INFO_URL);

        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        List<String> title = new ArrayList<>();
        for (int paxCount = 0; paxCount < pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().size(); paxCount++) {
            title.add(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(paxCount).getAttribute("value"));
        }

        List<String> firstName = new ArrayList<>();
        for (int paxCount = 0; paxCount < pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().size(); paxCount++) {
            firstName.add(pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(paxCount).getAttribute("value"));
        }

        List<String> lastName = new ArrayList<>();
        for (int paxCount = 0; paxCount < pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().size(); paxCount++) {
            lastName.add(pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(paxCount).getAttribute("value"));
        }
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /****************************** Bags Page Method *******************************************/
        pageMethodManager.getBagsPageMethods().continueWithOutChangesBag();

        /****************************** Seats Page Method *******************************************/
        WaitUtil.untilPageLoadComplete(getDriver());

        List<Integer> unavailableSeats_1 = new ArrayList<>();
        List<Integer> availableSeats_1 = new ArrayList<>();
        for (int seatCount = 0; seatCount < pageObjectManager.getSeatsPage().getAllSeatGridView().size(); seatCount++) {
            if (pageObjectManager.getSeatsPage().getAllSeatGridView().get(seatCount).getAttribute("class").contains("unavailable")) {
                unavailableSeats_1.add(seatCount);
            } else {
                availableSeats_1.add(seatCount);
            }
        }

        for (int seatCount = 0; seatCount < unavailableSeats_1.size(); seatCount++) {
            ValidationUtil.validateTestStep("Validating seats information matching between Seats Page and the info recorded on Flight Availability Page for unavailable seats",
                    unavailableSeats_1.get(seatCount).equals(unavailableSeats.get(seatCount)));
        }

        for (int seatCount = 0; seatCount < availableSeats_1.size(); seatCount++) {
            ValidationUtil.validateTestStep("Validating seats information matching between Seats Page and the info recorded on Flight Availability Page for available seats",
                    availableSeats_1.get(seatCount).equals(availableSeats.get(seatCount)));
        }

        WaitUtil.untilTimeCompleted(3000);
        int seatsCount = 0;
        for (int seatCount = 0; seatCount < pageObjectManager.getSeatsPage().getStandardSeatsGridView().size(); seatCount ++) {
            if (seatsCount < 2 && !pageObjectManager.getSeatsPage().getStandardSeatsGridView().get(seatCount).getAttribute("class").contains("unavailable")) {
                pageObjectManager.getSeatsPage().getStandardSeatsGridView().get(seatCount).click();
                seatsCount++;
            }
        }
        WaitUtil.untilTimeCompleted(3000);

        pageObjectManager.getSeatsPage().getContinueWithSeatButton().get(0).click();

        /****************************** Options Page Method *******************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /****************************** Payment Page Method *******************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
        //Validating Passenger Info
        for (int i = 0; i < pageObjectManager.getPaymentPage().getPassengerNameText().size(); i++) {
            ValidationUtil.validateTestStep("Validating Pass Info displayed properly",
                    pageObjectManager.getPaymentPage().getPassengerNameText().get(i).getText(), title.get(i) + ". " + firstName.get(i) + " " + lastName.get(i));
        }
        //Original Itinerary Section
        //Recording Dep and Arr info for validation on Original Itinerary section
        List<String> depCityNamePayment = getDisplayedText(pageObjectManager.getPaymentPage().getDepartingFlightCityNameText());
        List<String> arrCityNamePayment = getDisplayedText(pageObjectManager.getPaymentPage().getArriveFlightCityNameText());

        //Validating Dep City info
        for (int cityCount = 0; cityCount < depCityNamePayment.size(); cityCount++) {
            ValidationUtil.validateTestStep("Validating right departing city displayed on the original itinerary section",
                    depCityNamePayment.get(cityCount), depCityName.get(cityCount) + " " + depTime.get(cityCount));
        }
        //Validating Arr City  Info
        for (int cityCount = 0; cityCount < arrCityNamePayment.size(); cityCount++) {
            ValidationUtil.validateTestStep("Validating right Arrival displayed on the original itinerary section",
                    arrCityNamePayment.get(cityCount), arrCityName.get(cityCount) + " " + arrTime.get(cityCount));

//-- Step 25:  Visually select a flight with departure time between 12AM-4AM and click the number of stops link.
            //Invalid Step. Flight info recorded on Step 14
        }
    }
    private List<String> getDisplayedText(List<WebElement> elementList){

        ArrayList<String> arrayList = new ArrayList<>();//empty

        for (WebElement element:elementList) {
            if(element.isDisplayed()){

                arrayList.add(element.getText().trim());
            }
        }
        return arrayList;
    }
}
