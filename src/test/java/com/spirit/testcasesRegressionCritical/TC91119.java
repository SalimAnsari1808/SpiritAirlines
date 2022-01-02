package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//TODO: IN:24709 - PROD: CP: BP: FA: Taxes and fees modal is missing "Most fares include Fuel Pass Through" in the details
//Test Case ID: TC91119
//Description: Task 24816: 35246 Flight Availability_CP_CI_Modify Flight_Links and Wireframe
//Created By : Gabriela
//Created On : 25-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 21-Aug-2019
//**********************************************************************************************

public class TC91119 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug" , "CheckIn" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Connecting" ,
                    "BookIt" , "FlightAvailabilityUI" ,"NoBags","NoSeats", "CheckInOptions" , "Visa" , "ChangeFlight"})

    public void Flight_Availability_CP_CI_Modify_Flight_Links_and_Wireframe(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91119 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE                   = "English";
        final String JOURNEY_TYPE               = "Flight";
        final String TRIP_TYPE                  = "OneWay";
        final String DEP_AIRPORTS               = "AllLocation";
        final String DEP_AIRPORT_CODE           = "FLL";
        final String DEP_AIRPORT_CODE_1         = "LAS";
        final String ARR_AIRPORTS               = "AllLocation";
        final String ARR_AIRPORT_CODE           = "LAX";
        final String DEP_DATE                   = "0";
        final String ARR_DATE                   = "NA";
        final String ADULT                      = "1";
        final String CHILD                      = "0";
        final String INFANT_LAP                 = "0";
        final String INFANT_SEAT                = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                 = "Connecting";
        final String FARE_TYPE                  = "Standard";
        final String UPGRADE_VALUE              = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE              = "CheckInOption:MobileFree";

        //Payment page constant value
        final String CARD_TYPE                  = "VisaCard";
        final String TRAVEL_GUARD               = "NotRequired";

        //Online Check In Page Constant Values
        final String FA_ONLINE_CHECKIN_URL      = "/check-in/flights";
        final String TITLE_TEXT                 = "Choose Your Flight";
        final String UNDER_TITLE_TEXT           = " Select your flight time and fare below. Once you select a flight" +
                                                " you'll be able to add on other options like bags, seats and extras. Additional charges for baggage," +
                                                " advance seats assignments and any changes apply only if you add these options.\n" +
                                                "Fares listed are per person, are non-refundable and include all taxes and fees";
        final String BLUE_COLOR 	            = "rgb(0, 115, 230)";
        final String REFUNDABILITY_HEADER       = "Refundability";
        final String REFUNDABILITY_INFO         = "REFUNDS ARE ALLOWED FOR RESERVATIONS MADE ONE WEEK OR MORE PRIOR TO YOUR DEPARTURE, PROVIDED THAT YOU MAKE THE REFUND REQUEST WITHIN 24 HOURS OF YOUR INITIAL RESERVATION.";
        final String TAXES_AND_FEES_HEADER      = "Taxes And Fees";
        final String TAXES_AND_FEES_INFO        = "AIRTRAVELWITHINTHEUNITEDSTATESQUOTEDFAREINCLUDESTHEBASEFAREPLUSANYAPPLICABLETAXES,FEESANDSURCHARGES.THEFOLLOWINGGOVERNMENTTAXESANDFEESAPPLYTOYOURDOMESTICAIRTRAVEL:A7.5%FEDERALEXCISETAX(EXCEPTFORAWARDBOOKINGS)SEPTEMBER11THSECURITYFEE:ASEPTEMBER11THSECURITYFEEOF$5.60APPLIESPERPERSONEACHWAYFORTRAVELORIGINATINGATAU.S.AIRPORT.FEEIMPOSEDPERROUND-TRIPSHALLNOTEXCEED$11.20DOMESTICSEGMENTTAX:ASEGMENTTAXOF$4.20PERU.S.DOMESTICFLIGHTSEGMENT(AFLIGHTSEGMENTISDEFINEDASONETAKEOFFANDLANDING)(EXCEPTFORAWARDBOOKINGS)PASSENGERFACILITYCHARGES:UPTO$4.50PERFLIGHTSEGMENTWITHAMAXIMUMOFTWOPFCSCHARGEDONAONE-WAYTRIPORFOURPFCSONAROUNDTRIP,FORAMAXIMUMOF$18.00,INCERTAINPFC-APPROVEDU.S.AIRPORTS(EXCEPTFORAWARDBOOKINGS)THEFOLLOWINGCARRIERFEESAPPLYTOYOURDOMESTICAIRTRAVEL(EXCEPTFORAWARDBOOKINGS):PASSENGERUSAGEFEE:ANON-REFUNDABLEPASSENGERUSAGECHARGEOF$22.99PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.ALOWERFEEMAYAPPLYTOCERTAINDISCOUNTFARES.NOFEEAPPLIESTOBOOKINGSCOMPLETEDATSPIRITAIRLINES'AIRPORTLOCATIONS.REGULATORYCOMPLIANCECHARGEFEE:REGULATORYCOMPLIANCECHARGEFEEOF$7.00PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.NOFEEWILLAPPLYTOCERTAINDISCOUNTFARES.FUELCHARGE:FUELCHARGEOF$12.00PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.THISSURCHARGEWILLNOTAPPLYTOCERTAINDISCOUNTFARES.INTERNATIONALAIRTRAVEL(INCLUDINGPUERTORICOANDTHEU.S.VIRGINISLANDS)QUOTEDFAREINCLUDESTHEBASEFAREPLUSANYAPPLICABLETAXES,FEES,ANDSURCHARGES.THEFOLLOWINGGOVERNMENTTAXESANDFEESAPPLYTOYOURINTERNATIONALAIRTRAVEL:INTERNATIONALARRIVAL/DEPARTURETAXOF$18.60FOREACHARRIVALANDDEPARTURETOANDFROMTHEU.S.(EXCEPTFORAWARDBOOKINGS)SEPTEMBER11THSECURITYFEE:ASEPTEMBER11THSECURITYFEEOF$5.60APPLIESPERPERSONEACHWAYFORTRAVELORIGINATINGATAU.S.AIRPORT.FEEIMPOSEDPERROUND-TRIPSHALLNOTEXCEED$11.20.APHISUSERFEEOF$3.96PERTRAVELINGCUSTOMERUPONARRIVALTOTHEU.S.IMMIGRATIONUSERFEEOF$7.00PERTRAVELINGCUSTOMERUPONARRIVALTOTHEU.S.CUSTOMSUSERFEEOF$5.77PERTRAVELINGCUSTOMERUPONARRIVALTOTHEU.S.PASSENGERFACILITYCHARGES(PFC):UPTOPER$4.50PERFLIGHTSEGMENTWITHAMAXIMUMOFTWOPFCSCHARGEDONAONE-WAYTRIPORFOURPFCSONAROUNDTRIP,FORAMAXIMUMOF$18.00,INCERTAINPFC-APPROVEDU.S.AIRPORTS(EXCEPTFORAWARDBOOKINGS)NON-U.S.GOVERNMENTTAXESANDFEES:CERTAINFOREIGNCOUNTRIESMAYCHARGEADDITIONALTAXESANDFEESTHATARECOLLECTEDDIRECTLYBYTHELOCALGOVERNMENTORCOMPETENTAIRPORTAUTHORITY.THEFOLLOWINGCARRIERFEESAPPLYTOYOURINTERNATIONALAIRTRAVEL(EXCEPTFORAWARDBOOKINGS):PASSENGERUSAGECHARGE:ANON-REFUNDABLEPASSENGERUSAGECHARGEOF$22.99PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.ALOWERFEEMAYAPPLYTOCERTAINDISCOUNTFARES.NOFEEAPPLIESTOBOOKINGSCOMPLETEDATSPIRITAIRLINESAIRPORTLOCATIONS.REGULATORYCOMPLIANCECHARGEFEE:REGULATORYCOMPLIANCECHARGEFEEOF$7.00PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.NOFEEWILLAPPLYTOCERTAINDISCOUNTFARES.FUELCHARGE:FUELCHARGEOF$12.00PERSEGMENTPERTRAVELINGCUSTOMERAPPLIESTOMOSTRESERVATIONS.THISSURCHARGEWILLNOTAPPLYTOCERTAINDISCOUNTFARESALLAIRTRAVELALLFARESARESUBJECTTOCHANGEUNTILPURCHASEDSUBJECTTOCERTAINEXCEPTIONSAND/ORRESTRICTIONSSETFORTHINSPIRITAIRLINESCONTRACTOFCARRIAGE,ALLRESERVATIONSARENON-REFUNDABLEANDNON-TRANSFERABLE.REFUNDSAREALLOWEDFORRESERVATIONSMADE7DAYSORMOREPRIORTODEPARTUREANDPROVIDEDTHATAREFUNDREQUESTISMADEWITHIN24HOURSOFINITIALRESERVATION.CHANGESORCANCELLATIONSMADETOITINERARIESWILLBESUBJECTTOCHANGEFEES,ANYFAREDIFFERENTIAL,ANYDIFFERENCESINGOVERNMENTTAXESANDFEESAND,WITHTHEEXCEPTIONOFFEESFORCARRY-ONBAGSANDFIRSTANDSECONDCHECKED-INBAGS,ANYDIFFERENCEINCARRIERSANCILLARYCHARGES.FORACOMPLETELISTOFRULESANDREGULATIONSPLEASEREFERTOSPIRITAIRLINES'CONTRACTOFCARRIAGEPACKAGES/CARRENTALSRATESINCLUDEFLIGHTANDHOTELTAXESANDFEES.ADDITIONALMANDATORYRESORTFEESMAYBECHARGEDDIRECTLYBYTHEHOTEL.CARRENTALRATESDONOTINCLUDETAXES,AIRPORTFEES,INSURANCE,OPTIONS,UNDERAGEDRIVERCHARGES(IFAPPLICABLE)ORFUELCOSTS,ALLOFWHICHMUSTBEPAIDDIRECTLYTOTHERENTALCARCOMPANYANDARESUBJECTTOCHANGEBYEACHINDIVIDUALSTATEAND/ORLOCATION.OTHERFEESADDITIONALCHARGESFORBAGGAGE,ADVANCESEATASSIGNMENTSANDANYCHANGES,APPLYONLYIFYOUADDTHESEOPTIONS.SPIRITMAY,FROMTIMETOTIME,CONTRACTWITHTHIRDPARTIESTOPROVIDECERTAINADDITIONALPRODUCTSANDSERVICESTOITSCUSTOMERSINCLUDING,BUTNOTLIMITEDTO,TRAVELINSURANCE,HOTELS,ANDCARRENTALS.SPIRITMAYRECEIVECOMPENSATIONFROMSUCHTHIRDPARTIESINCONNECTIONWITHTHESEADDITIONALPRODUCTSANDSERVICES.ADVICETOPASSENGERSFEDERALLAWFORBIDSTHECARRIAGEOFHAZARDOUSMATERIALSABOARDAIRCRAFTINYOURLUGGAGEORONYOURPERSON.AVIOLATIONCANRESULTINFIVEYEARS'IMPRISONMENTANDPENALTIESOF$250,000ORMORE(49U.S.C.5124).HAZARDOUSMATERIALSINCLUDEEXPLOSIVES,COMPRESSEDGASES,FLAMMABLELIQUIDSANDSOLIDS,OXIDIZERS,POISONS,CORROSIVESANDRADIOACTIVEMATERIALS.EXAMPLES:PAINTS,LIGHTERFLUID,FIREWORKS,TEARGASES,OXYGENBOTTLES,ANDRADIO-PHARMACEUTICALS.THEREARESPECIALEXCEPTIONSFORSMALLQUANTITIES(UPTO70OUNCESTOTAL)OFMEDICINALANDTOILETARTICLESCARRIEDINYOURLUGGAGEANDCERTAINSMOKINGMATERIALSCARRIEDONYOURPERSON.FORFURTHERINFORMATIONCONTACTYOURAIRLINEREPRESENTATIVE.E-CIGARETTESANDOTHERBATTERY-POWEREDSMOKINGDEVICESAREPROHIBITEDFORUSEABOARDSPIRITAIRCRAFT.ADDITIONALLY,THESEDEVICESARENOTPERMITTEDINCHECKEDBAGGAGE.E-CIGARETTESANDOTHERBATTERY-POWEREDSMOKINGDEVICESAREPERMITTEDINCARRY-ONBAGGAGEONLY;HOWEVER,SOMECOUNTRIESPROHIBITTHECARRIAGEOFTHESEDEVICESINCARRY-ONBAGGAGE,CHECKEDBAGGAGE,AND/ORONONESPERSON,INWHICHCASESPIRITWILLABIDEBYSUCHPROHIBITION(S).SPARELITHIUMBATTERIESCANNOTBECARRIEDINCHECKEDBAGGAGE.";
        final String BAGGAGE_URL                = "optional-services";
        final String SEAT_ASSIGNMENTS_URL       = "/optional-services#seats";
        final String ANY_CHANGES_URL            = "/optional-services#other";
        final String FONT_BLACK                 = "rgba(0, 0, 0, 1)";
        String flightCost                       = "";

        //open browser
        openBrowser(platform);

//STEP 1: Reaching to Flight Availability page on Check In path
        /*********************************************************************************************************
         * ******************************************HOME PAGE****************************************************
         *********************************************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*********************************Flight Availability Methods*************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*********************************Passenger Info Methods*************************************************/
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        /**************************************Bags Page Methods*************************************************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        /*********************************Seats Page Methods*************************************************/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        /*********************************Options Page Methods*************************************************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        /*********************************Payment Page Methods*************************************************/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        /*********************************Confirmation Page Methods*************************************************/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        pageMethodManager.getHomePageMethods().loginToCheckIn();

        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);

        /*********************************Online Check In Page Methods*************************************************/
        //Selecting modify the journey
        final String CHANGE_FLIGHT_POPUP= "Continue";
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepEditLabel().click();
        WaitUtil.untilTimeCompleted(1200);

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();

//-- Step 1: Via CHECK IN, verify user lands on Flight Availability page
        //Validating FA page on online check in path
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating FA Online Check in Page URL",
                getDriver().getCurrentUrl(), FA_ONLINE_CHECKIN_URL);

//-- Step 2: Select the date with the cheapest flight price available
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        WaitUtil.untilTimeCompleted(3000);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE_1, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getFlightAvailabilityMethods().selectFlightCheapCostlyType("Dep","Standard", "Cheap");

//-- Step 3: Verify the page title displays as Flight Availability (Step changes.)
        ValidationUtil.validateTestStep("Validating verbiage displayed under Page Title",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText().getText(),TITLE_TEXT);

//-- Step 4: Verify the verbiage under the page title
        ValidationUtil.validateTestStep("Validating verbiage displayed under Page Title",
                UNDER_TITLE_TEXT,pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageSubHeaderText().getText().trim());

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

//-- Step 5: Click on baggage link located under OTHER FEES header and verify it redirects properly
        WaitUtil.untilTimeCompleted(1000);
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityBaggageLink().click();
        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(),1);
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(BAGGAGE_URL));

//-- Step 6: Go back to Flight Availability page and click on advance seat assignments links located under OTHER FEES header and verify it redirects properly
        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityAdvanceSeatsLink().click();
        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(),1);
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(SEAT_ASSIGNMENTS_URL));

//-- Step 7: Go back to Flight Availability page and click on any changes link located under OTHER FEES header and verify it redirects properly
        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);
        WaitUtil.untilTimeCompleted(1200);

        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityAnyChangesLink().click();
        WaitUtil.untilTimeCompleted(1200);

        TestUtil.switchToWindow(getDriver(),1);
        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(ANY_CHANGES_URL));

        getDriver().close();
        TestUtil.switchToWindow(getDriver(),0);
        WaitUtil.untilTimeCompleted(1200);

//-- Step 8: Go back to Flight Availability page and click the non-refundable link
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityNonRefundableLink().click();
        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Validating refundability pop up header",
                pageObjectManager.getFlightAvailabilityPage().getRefundabilityPopUpHeaderText().getText(),REFUNDABILITY_HEADER);

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating refundability pop up info ",
                pageObjectManager.getFlightAvailabilityPage().getRefundabilityPopUpBodyInfoText().getText(),REFUNDABILITY_INFO);

//-- Step 9: Close popup by either clicking the X, or clicking the CLOSE button
        pageObjectManager.getFlightAvailabilityPage().getRefundabilityPopUpCloseButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating FA Online Check in Page URL",
                getDriver().getCurrentUrl(), FA_ONLINE_CHECKIN_URL);

//-- Step 10: Click the "taxes and fees" link
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityTaxesAndFeesLink().click();

        WaitUtil.untilTimeCompleted(1200);

        ValidationUtil.validateTestStep("Validating taxes and fees pop up header",
                pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpHeaderText().getText(),TAXES_AND_FEES_HEADER);

        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Validating Taxes and Fees pop up info ",
                pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpBodyInfoText().getText().replace("CLOSE WINDOW", ""),TAXES_AND_FEES_INFO);

//-- Step 11: Close popup by either clicking the X, or clicking the CLOSE button
        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpCloseButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//-- Step 12:Invalid If a customer bought Flight Flex during booking, verify the following verbiage is also present (No Flight flex available for this booking made it within 24 hours)

//-- Step 13: Invalid

//-- Step 14: Click the Month option on the slider button to expand the calendar.
        pageObjectManager.getFlightAvailabilityPage().getDepartureFlightEditButton().get(0).click();
        WaitUtil.untilTimeCompleted(3000);

        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMonthViewSwitchLabel().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating calendar is expanded",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMonthViewSwitchLabel()));

        //Verify the unselected tiles have one of the following verbiage:
        for (WebElement priceText : pageObjectManager.getFlightAvailabilityPage().getDepartingMonthsDateEachTileMoreLessText()) {
            String text = priceText.getText();
            boolean hasCorrectText = text.contains("Less") || text.contains("More") || text.contains("Same");
            ValidationUtil.validateTestStep("The Text is correct", hasCorrectText);
        }

//-- Step 15: Verify the flight date purchased in the booking has a plane center aligned the tile
        for (int count = 0; count < pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsPanel().size(); count ++) {
            if ( pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsPanel().get(count).isSelected()) {
                ValidationUtil.validateTestStep("Validating aircraft is displayed on the flight tile selected",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getDepartingMonthsDateSelectedTilePlaneIcon()));
            }
        }

//-- Step 16: Click the right facing arrow to move forward 4 months then click the left arrow and go back 2 months
        //Moving forward 4 months
        for (int count = 0; count < 4; count ++) {
            pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselRightButton().get(0).click();
            WaitUtil.untilPageLoadComplete(getDriver());
        }

        //Moving 2 months back
        for (int count = 0; count < 2; count ++) {
            pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselLeftButton().get(0).click();
            WaitUtil.untilPageLoadComplete(getDriver());
        }

        //Selecting new date for flight
        for (int count = 0; count < pageObjectManager.getFlightAvailabilityPage().getDepartingWeekEachTilesGrid().size(); count ++) {
            if (count == pageObjectManager.getFlightAvailabilityPage().getDepartingWeekEachTilesGrid().size()) {
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselRightButton().get(0).click();
                WaitUtil.untilPageLoadComplete(getDriver());
            }
            if (pageObjectManager.getFlightAvailabilityPage().getDepartingWeekEachTilesGrid().get(count).isDisplayed()) {
                //Validating there is not 9FC price
                if (TestUtil.verifyElementDisplayed(getDriver().findElements(By.xpath("(//div[@data-qa='journey-results'])[1]//app-low-fare-day//span[@class='ndfc-badge font-legal-size font-weight-bold align-middle invisible ng-star-inserted']")).get(count))) {
                    //Storing cost for future validation
                    flightCost = getDriver().findElements(By.xpath("(//div[@data-qa='journey-results'])[1]//app-low-fare-day//div//div//div//div//div//span[2]")).get(count).getText();
                    //Selecting new date with conditions
                    pageObjectManager.getFlightAvailabilityPage().getDepartingWeekEachTilesGrid().get(count).click();
                    break;
                }

            }
        }
//-- Step 17: Following, verify there is a Header to display flight with dark grey background.
        ValidationUtil.validateTestStep("Verify Font is black",
                getDriver().findElements(By.xpath("//div[@class='flex-grow-1 flex-basis-0 d-flex justify-content-start pl-3']")).get(0).getCssValue("color"), FONT_BLACK);

//-- Step 19:  Verify each available flight is displayed as a list of flights separated by tiles. Each tile containing
        for (int i = 0; i < getDriver().findElements(By.xpath("//div[@class='row d-none d-lg-flex flight-avail-card']")).size(); i ++)
        {
            ValidationUtil.validateTestStep("Verifying departing time displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getDepartingDepartTimeText().get(i)));

            ValidationUtil.validateTestStep("Verifying arrival time displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getDepartingArriveTimeText().get(i)));

            ValidationUtil.validateTestStep("Verifying # of stops displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(i)));
        }

//-- Step 18, 20, 21 : Visually select a flight and click the number of stops link.
        //Selecting flight
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getFlightAvailabilityPage().getDepartingStandardFarePriceText().get(0).click();

        //Step 20: Click on Number of Stops link
        WaitUtil.untilTimeCompleted(3000);
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

        //Storing Arrival Cities Time for 1st journey
        for (WebElement nkNum: pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText()){
            nkInfo.add(nkNum.getText().trim().replace("Flight ", "").replace(" ", ""));
        }

        //Step 21: Closing Flight Info Pop Up
        //Salim Modification Required
        //pageObjectManager.getFlightAvailabilityPage().getCloseStopPopUpButton().click();
        WaitUtil.untilTimeCompleted(1000);

//-- Step 22: Verify the flight price right aligned.
        if (flightCost.equals("Same")) {
            System.out.println("for same " + pageObjectManager.getFlightAvailabilityPage().getDepartureFlightBlockPriceText().getText());
            ValidationUtil.validateTestStep("Verifying if the Flight price is equal to the one purchased in the booking, it will say $0.00",
                    pageObjectManager.getFlightAvailabilityPage().getDepartureFlightBlockPriceText().getText().equals("$0.00"));
        }

        if (flightCost.equals("Less")) {
            System.out.println("foe less " + pageObjectManager.getFlightAvailabilityPage().getDepartureFlightBlockPriceText().getText());
            ValidationUtil.validateTestStep("Verifying if the Flight is cheaper, it will say the negative difference in format - $XX.XX",
                    pageObjectManager.getFlightAvailabilityPage().getDepartureFlightBlockPriceText().getText().contains("-$"));
        }

        if (flightCost.equals("More"))
        {
            System.out.println("for more "  + pageObjectManager.getFlightAvailabilityPage().getDepartureFlightBlockPriceText().getText());
            ValidationUtil.validateTestStep("Verifying if the Flight is more expensive, it will say the positive difference in format + $XX.XX",
                    pageObjectManager.getFlightAvailabilityPage().getDepartureFlightBlockPriceText().getText().contains("+ $"));
        }

//-- Step 23: Not Valid. Deadpool description

//-- Step 24: Step covered before

//-- Step 25: Not valid. Deadpool description

//-- Step 26: Verify there is a CONTINUE button
        ValidationUtil.validateTestStep("Continue button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getStandardFareButton()));

//-- Step 27: Verify there is a center aligned Cancel link
//        ValidationUtil.validateTestStep("Cancel button is displayed",
//                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getCancelFlightModificationButton()));

//-- Step 28: Use a reservation in which the customer purchased Flight Flex
        //Invalid Step. Flight Flex is only able to purchase on booking path in journeys outside 24 hours
    }
}

//*******************************************************************
//************************Spirit Member Container********************
//*******************************************************************
//    @FindBy(xpath="//button[contains(text(),'Cancel')]")
//    private WebElement btn_CancelFlightModification;
//    public WebElement getCancelFlightModificationButton() {return btn_CancelFlightModification;}