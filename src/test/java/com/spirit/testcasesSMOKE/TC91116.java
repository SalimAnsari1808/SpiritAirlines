package com.spirit.testcasesSMOKE;


import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

//**********************************************************************************************
//Test Case ID: TC91116
//Description: CP_MT_Flight Only_Modify Flight
//Created By : Anthony C
//Created On : 25-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 26-Mar-2019
//**********************************************************************************************
public class TC91116 extends BaseClass{

    @Parameters ({"platform"})
    @Test (groups = {"MyTrips","Guest","OneWay","DomesticDomestic","WithIn7Days","Adult","Through","BookIt","NoBags",
                    "NoSeats","CheckInOptions","MasterCard","Header","ChangeFlight","FlightAvailabilityUI"})
    public void CP_MT_Flight_Only_Modify_Flight (@Optional("NA")String platform) {
        //mention the browser
        if(!platform.equals("NA")){
            ValidationUtil.validateTestStep("Starting Test Case ID TC91116 under SMOKE suite on " + platform +" Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "OneWay";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "DEN";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "FLL";
        final String DEP_DATE 			= "6";
        final String ARR_DATE 			= "NA";
        final String ADULTS				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "through";
        final String FARE_TYPE			= "Standard";
        final String JOURNEY_UPGRADE	= "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //Payment page Constant Value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "NotRequired";

        //open browser
        openBrowser(platform);

        //Step_ 1,2,3
        //*********Navigate to Flight Availability Page******/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //*********Navigate to Passenger Information Page***/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(JOURNEY_UPGRADE);

        //****************Navigate to Bags Page*************/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //****************Navigate to Seats Page*************/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //***************Navigate to Options Page***********/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //*************Navigate to Payment Page**************/
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //***************Navigate to Confirmation Page******/
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

//Step- 4
        //************Navigate to ManageTravel Page**********/
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();
        pageMethodManager.getHomePageMethods().loginToMyTrip();


//Step- 5
        pageObjectManager.getReservationSummaryPage().getFlightSectionChangeFlightButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(500);

//Step- 6
        pageMethodManager.getReservationSummaryPageMethods().changeFlightOnChangeFlightPopup("dep", "LGA", "Lax","NA");
//        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepEditLabel().click();
//
//        WaitUtil.untilPageLoadComplete(getDriver());
//        WaitUtil.untilTimeCompleted(500);
//
////Step 7
//        String newDepartureCity = " New York, NY - LaGuardia ";
//        String newArrivalCity = " Los Angeles, CA ";
//
//        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepFromCityDropDown(), newDepartureCity);
//        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepToCityDropDown(), newArrivalCity);
//
//        WaitUtil.untilPageLoadComplete(getDriver());
//        WaitUtil.untilTimeCompleted(500);
//
////Step 8
//        //validate when user clicks on the date input that the calendar modal is displayed
//        pageObjectManager.getReservationSummaryPage().getChangeFlightPopupDepDateTextBox().click();
//
//        WaitUtil.untilTimeCompleted(500);

        //Putting the calenders into list and verifying the size is 1
        //List<WebElement> OneWayCalender = getDriver().findElements(By.xpath("//bs-days-calendar-view"));


        ValidationUtil.validateTestStep("One Calenders is displayed", pageObjectManager.getHomePage().getCalenderHeaderText().size()==1);

//Step 9
        //Click on continue button
        //pageObjectManager.getReservationSummaryPage().getChangeFlightPopupContinueButton().click();
        pageMethodManager.getReservationSummaryPageMethods().continueCancelOnChangeFlightPopup("Continue");

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 10, 12
        final String PAGE_HEADER    = "Choose Your Flight";
        ValidationUtil.validateTestStep("The page title is correct \"Choose Your Flight\" on Check-In Flight Availability Page",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageHeaderText().getText(),PAGE_HEADER);

//Step 13
        //validate that the text under the title is correct
        final String PAGE_SUB_HEADER = "Select your flight time and fare below. Once you select a flight you'll be able to add on other options like bags, seats and extras. Additional charges for baggage, advance seats assignments and any changes apply only if you add these options.";

        ValidationUtil.validateTestStep("The Page description text matches the expected",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageSubHeaderText().getText(),PAGE_SUB_HEADER);


        final String LEGAL_TEXT = "Fares listed are per person, are non-refundable and include all taxes and fees";

       // System.out.println(JSExecuteUtil.getElementTextValue(getDriver(),pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageLegalVerbiageText()));
        System.out.println(pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageLegalVerbiageText().getText().replaceAll("  ", " ").trim());
        ValidationUtil.validateTestStep("The disclaimer text matches expected",
                pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityPageLegalVerbiageText().getText().replaceAll("  ", " ").trim(),LEGAL_TEXT);

//Step 14
        final String BAGGAGE_LINK_URL   = "optional-services";

        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityBaggageLink());

        validateRedirectedCorrectly(BAGGAGE_LINK_URL);

//Step 15
        final String SEAT_LINK_URL   = "optional-services#seats";

        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityAdvanceSeatsLink());

        //validate the user is redirected to the correct page
        validateRedirectedCorrectly(SEAT_LINK_URL);


//Step 16
        final String ANY_CHANGE_LINK_URL   = "optional-services#other";

        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityAnyChangesLink());

        //validate the user is redirected to the correct page
        validateRedirectedCorrectly(ANY_CHANGE_LINK_URL);

//Step 17, 18
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityNonRefundableLink().click();
//        JSExecuteUtil.clickOnElement(getDriver(), getDriver().findElement(By.xpath("//a[contains(text(),' non-refundable ')]")));
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("The Refundibilty pop-up is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getRefundabilityPopUpHeaderText()));
//        ValidationUtil.validateTestStep("The Refundibilty pop-up is displayed", getDriver().findElement(By.xpath("//h2[contains(text(),'Refundability')]")).isDisplayed());

//        getDriver().findElement(By.xpath("//app-refundability-modal//i[@class='icon-close']")).click();
        pageObjectManager.getFlightAvailabilityPage().getRefundabilityPopUpCloseButton().click();

//Step 19, 20
        pageObjectManager.getFlightAvailabilityPage().getFlightAvailabilityTaxesAndFeesLink().click();
//        JSExecuteUtil.clickOnElement(getDriver(), getDriver().findElement(By.xpath("//a[contains(text(),'taxes and fees')]")));
        WaitUtil.untilTimeCompleted(1200);

//        ValidationUtil.validateTestStep("The Taxes and Fees pop-up is displayed", getDriver().findElement(By.xpath("//h2[contains(text(),'Taxes And Fees')]")).isDisplayed());
        ValidationUtil.validateTestStep("The Taxes and Fees pop-up is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpHeaderText()));

//        getDriver().findElement(By.xpath("//app-taxes-and-fees-modal//i[@class='icon-close']")).click();
        pageObjectManager.getFlightAvailabilityPage().getTaxesAndFeesPopUpCloseButton().click();
        WaitUtil.untilTimeCompleted(1000);

//step21 not applicable

//Step 22
//        newDepartureCity = newDepartureCity.trim();
//        newDepartureCity = newDepartureCity.substring(0, newDepartureCity.indexOf(","));
//        newArrivalCity = newArrivalCity.trim();
//        newArrivalCity = newArrivalCity.substring(0, newArrivalCity.indexOf(","));
//
//
//        String cityPair = getDriver().findElement(By.xpath("//div[@data-qa='journey-results']//div[@class='headline3'][contains(text(),'" + newDepartureCity + "')]")).getText();
//        ValidationUtil.validateTestStep("The city pair is correclt displayed", cityPair.contains(newDepartureCity) && cityPair.contains(newArrivalCity));


//Step- 23
        //clcik on month view
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMonthViewSwitchLabel().click();

        //there should be 4 or more week rows displayed on the month calendar view

        boolean isMonthView = getDriver().findElements(By.xpath("//div[contains(@class,'d-flex justify-content-center ng-star-inserted')]")).size() >= 4;
        ValidationUtil.validateTestStep("The calendar view is displayed on the flight availability page", isMonthView);

        //validate that each day has price difference
        List <WebElement> savings = pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsSavingsText();

        boolean isTextCorrect = true; //boolean to validate

        //if element does not contain class invisible
        for(WebElement element : savings)
            if (!element.getAttribute("class").contains("invisible")) {
                String savingstext = element.getText().toUpperCase();
                System.out.println(savingstext);
                //validate that the flight container savings says "Less", "More", "Same"
                isTextCorrect  = (savingstext.contains("LESS") && savingstext.contains("$")) ||
                        (savingstext.contains("MORE") && savingstext.contains("$")) ||
                        savingstext.contains("SAME");

                if(!isTextCorrect) break;
            }

        ValidationUtil.validateTestStep("The displayed cards contain the correct price difference", isTextCorrect);

//Step 24
        //click on the right month arraw to move 4 months forward then 2 months back
        for (int i = 0 ; i < 6 ; i++)
        {
            //click on right 4 times
            for( WebElement rightArrow : pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselRightButton())
            {
                if(rightArrow.isDisplayed())
                {
                    rightArrow.click();
                    WaitUtil.untilPageLoadComplete(getDriver());
                    WaitUtil.untilTimeCompleted(1000);
                }
            }

            //click on left 2 times
            if(i < 4)
            {
                for( WebElement leftArrow : pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselLeftButton())
                {
                    if(leftArrow.isDisplayed())
                    {
                        leftArrow.click();
                        WaitUtil.untilPageLoadComplete(getDriver());
                        WaitUtil.untilTimeCompleted(1000);
                    }
                }
            }

        }

//Step 28
        //click on number of stops and validate that Stops pop-up is displayed
        pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(1).click();
        WaitUtil.untilTimeCompleted(1500);
        //ValidationUtil.validateTestStep("The Stops pop-up is displayed" , pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightNumberText().get(0).isDisplayed());
        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();

        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselWeekViewSwitchLabel().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 33
//        String[] flightNumbs = new String[1];
//
//        WaitUtil.untilPageLoadComplete(getDriver());
//        flightNumbs = manageTravel_SelectMoreExpensiveFlight(FARE_TYPE); //store flight numbers
//        WaitUtil.untilTimeCompleted(1000);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightCheapCostlyType("dep","Standard","Costly");

//Step 35
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

        WaitUtil.untilTimeCompleted(1500);
//        getDriver().findElement(By.xpath("//app-branded-modal//button[contains(text(),\"Don't Purchase Bags\")]")).click(); //bags upsell, no
        pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnBagsPopup("DontPurchase");

//        WaitUtil.untilTimeCompleted(1500);
//        getDriver().findElement(By.xpath("//app-branded-modal//button[contains(text(),\"Don't Purchase Seats\")]")).click(); //seats upsell, no
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        WaitUtil.untilPageLoadComplete(getDriver());
//        getDriver().findElement(By.xpath("//button[contains(text(),'Continue')]")).click();//continue on the Extras page
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        WaitUtil.untilPageLoadComplete(getDriver());

//Step - 38

        List <WebElement> newFlightNumbersOnConfirmationPage = getDriver().findElements(By.xpath("(//app-payment-itinerary)[1]//div[contains(@class,'desktop-container position-relative')]//p[contains(text(),'NK')]"));

        String[] newFlightNumber = new String[2];
        int j = 0;

        for(WebElement element : newFlightNumbersOnConfirmationPage)
        {
            if (element.isDisplayed())
            {
                newFlightNumber[j] = element.getText();
                j++;
            }
        }

//        for(int i = 0 ; i < flightNumbs.length -1 ; i++ )
//        {
//            ValidationUtil.validateTestStep("Flight Number " + flightNumbs[i] , newFlightNumber[i].contains(flightNumbs[i]));
//        }

        //Input all payment information
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        //accept terms and conditions
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        WaitUtil.untilTimeCompleted(1500);
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

        //validate that the user is sent to the change confirmation page
        ValidationUtil.validateTestStep("The \"Change Confirmation\" header is displayed", getDriver().findElement(By.xpath("//h1[contains(text(),'Change Confirmation')]")).isDisplayed());
    }

    /**
     * This method clicks on a more expensive fare that is of paramater fareType
     * @param fareType String: "club" , "standard", or "card-holder"
     * @author Anthony Cardona <BR>
     * Created on 21 Mar, 2019
     * Reviewed By:
     * Reviewed Date:
     */
    public String[] manageTravel_SelectMoreExpensiveFlight(String fareType)
    {
        String[] flightNums = new String[2];
        WaitUtil.untilTimeCompleted(1500);

        fareType = fareType.toLowerCase();
        if (fareType.contains("card")) fareType = "card-holder"; //if faretype is card, change faretype to "card-holder"

        WaitUtil.untilPageLoadComplete(getDriver());
        List <WebElement> TempallStandardsFares = getDriver().findElements(By.xpath("//label[contains(@for,'" + fareType + "')]"));

        int i = 0;
        int textIterator = 0;

        List<WebElement> allStandardFares = new ArrayList<>();
        for(WebElement element : TempallStandardsFares)
        {
            if (element.isDisplayed())
            {
                allStandardFares.add(element);
            }
        }

        for(WebElement fare : allStandardFares)
        {
            if (fare.getText().contains("+"))// if label contains - it is more expensive than original flight
            {
                pageObjectManager.getFlightAvailabilityPage().getDepartingNumebrOfStopsButton().get(i).click(); //click on number of stops
                WaitUtil.untilTimeCompleted(1200);

                for(WebElement flightNum : pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText())
                {
                    flightNums[textIterator] = flightNum.getText().replace("Flight","").replace(" ","").trim(); //replace and trim text
                    textIterator++; //increment iterator

                    if(textIterator ==  pageObjectManager.getFlightAvailabilityPage().getStopsPopUpFlightsNumberText().size()) //if text iterator is equal to the number of Stops then close pop-up
                    {
                        pageObjectManager.getFlightAvailabilityPage().getStopsPopUpCloseButton().click();
                        WaitUtil.untilTimeCompleted(1200);
                    }
                }
                fare.click();
                break;
            }
            i++;
        }
        return flightNums;
    }

    private void validateRedirectedCorrectly(String URLcontains)
    {
        TestUtil.switchToWindow(getDriver(),1);

        WaitUtil.untilTimeCompleted(2000);

        ValidationUtil.validateTestStep("Verify New Tab is open for " + URLcontains + " on Flight Availability Page",getDriver().getCurrentUrl().contains(URLcontains));

        getDriver().close();

        TestUtil.switchToWindow(getDriver(),0);

        WaitUtil.untilTimeCompleted(2000);
    }
}