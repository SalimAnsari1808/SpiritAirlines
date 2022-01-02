package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Test Case ID: TC91157
//Description: Task 24809: 35242 Flight Availability_CP_BP_Flight Only_Month Calendar Miles View_Wireframe
//Created By : Gabriela
//Created On : 31-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 01-Aug-2019
//**********************************************************************************************
//Bug 26051: CP: BP: Flight Availability FA: User Receives red i block when trying to create a miles booking when logging in either on the homepage, or the FA page
/**10/21/19 test case passed, removed active bug tag**/

public class TC91157 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "Miles" , "FreeSpirit" , "DomesticDomestic" , "WithIn7Days" , "Adult" ,"Guest", "FlightAvailabilityUI" })
    public void Flight_Availability_CP_BP_Flight_Only_Month_Calendar_Miles_View_Wireframe(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91157 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE           = "English";
        final String LOGIN_ACCOUNT      = "FSEmail";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "OneWay";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "DTW";
        final String DEP_AIRPORT_CODE1  = "CAK";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String ARR_AIRPORT_CODE1  = "PDX";
        final String DEP_DATE           = "3";
        final String ARR_DATE           = "NA";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Constant Values
        final String FONT_BLACK         = "0, 0, 0";
        final String FONT_YELLOW        = "255, 236, 0";
        final String FONT_BLUE          = "0,115,230";
        final String FONT_BOLD          = "700";
        final String FARE_CLUB_TEXT     = "$9 Fare Club Fares";
        final String ACTIVE_LINK        = "active";
        final String NO_FLIGHT          = "NOT AVAILABLE";


        //declare variable used in method
        boolean statusFlag              = true;

        //open browser
        openBrowser(platform);


        /******************************************HOME PAGE*****************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_ACCOUNT);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        /*********************************Flight Availability Methods*************************************************/
//-- Step 1: Beneath the left aligned Origin City to Destination City header and right aligned NEW SEARCH button, locate a GREY line all across the page
        ValidationUtil.validateTestStep("Verifying GREY line all across the page displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getDepartingCityPairGreyLineImage()));

//-- Step 2: Locate the left aligned yellow circle 9FC followed by verbiage $9 Fare Club Fares
        ValidationUtil.validateTestStep("Verifying yellow circle 9FC is displayed",
                pageObjectManager.getFlightAvailabilityPage().getDepartingHeader9FCBannerImage().getCssValue("background"),FONT_YELLOW);

        ValidationUtil.validateTestStep("Verifying $9 Fare Club Fares followed by yellow circle 9FC  verbiage is displayed",
                pageObjectManager.getFlightAvailabilityPage().getDepartingHeader9FCBannerText().getText(),FARE_CLUB_TEXT);

//-- Step 3: Right aligned, locate the slider buttons for Week|Month and Dollars|Miles
        // Validate the default view is Week
        ValidationUtil.validateTestStep("Validating Week is selected by default",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselWeekViewSwitchLabel().getAttribute("class"),ACTIVE_LINK);

        // Validate font is black
        ValidationUtil.validateTestStep("Verifying week font is black while is selected",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselWeekViewSwitchLabel().getCssValue("color"), FONT_BLACK);

        // Validate font is blue
        ValidationUtil.validateTestStep("Verifying month font is blue while is not selected",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMonthViewSwitchLabel().getCssValue("color"), FONT_BLUE);

        // Validate the default view is Dollars
        ValidationUtil.validateTestStep("Validating Dollars is selected by default",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDollarsViewSwitchLabel().getAttribute("class"),ACTIVE_LINK);

        // Validate font is black
        ValidationUtil.validateTestStep("Verifying dollars font is black while is selected",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDollarsViewSwitchLabel().getCssValue("color"), FONT_BLACK);

        // Validate font is black
        ValidationUtil.validateTestStep("Verifying miles font is blue while is not selected",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().getCssValue("color"), FONT_BLUE);

//-- Step 5: Click the MILES button
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMilesViewSwitchLabel().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating miles displayed on the price",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsLowestFareInMilesText().size()>0);

//-- Step 4: Click on the MONTH button
        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMonthViewSwitchLabel().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating calendar is expanded",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsPanel().size()==35);

        //Validating Font will be black for MONTH and blue for WEEK
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Verifying Month font is black while is selected",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselMonthViewSwitchLabel().getCssValue("color"), FONT_BLACK);

        // Validate font is blue
        WaitUtil.untilTimeCompleted(1000);
        ValidationUtil.validateTestStep("Verifying Week font is blue while is not selected",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselWeekViewSwitchLabel().getCssValue("color"), FONT_BLUE);


//-- Step 6: Validate there is a left aligned BLUE LEFT-FACING arrow and a right aligned BLUE RIGHT-FACING arrow to the left and right of the carousel
        ValidationUtil.validateTestStep("Validating a blue left arrow displayed",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselLeftButton().get(0).getCssValue("color"), FONT_BLUE);

        ValidationUtil.validateTestStep("Validating a blue right arrow displayed",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselRightButton().get(0).getCssValue("color"), FONT_BLUE);

//-- Step 7: Validate the Carousel contains 7 horizontal tiles by 5 vertical tiles
        ValidationUtil.validateTestStep("Validating calendar display 5 vertical tiles",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardRowsPanel().size() == 5);

        ValidationUtil.validateTestStep("Validating calendar display 7 horizontal tiles",
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardColumnsPanel().size() == 7);

//-- Step 8: Validate the date is displaying on the upper side of each tile
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsPanel().size(); i++) {
            if (!pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsPanel().get(i).getAttribute("class").contains("disabled")) {
                if(!pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsDateText().get(i).getCssValue("text-align").contains("right")){
                    statusFlag = false;

                    //verify date is not right aligned
                    ValidationUtil.validateTestStep("Validating date is not right aligned in the Date Card in " + i , false);
                }

            }
        }

        //verify date is not right aligned
        ValidationUtil.validateTestStep("Validating all dates appeared in Date Block are right aligned" , statusFlag);


//-- Step 9: Within each tile, validate there is MC yellow circle top left aligned on the corner
        mcIcon(5);

////-- Step 10: Verify the selected date is outlined bold-black
        //Selecting a random date for validations
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsDateText().size(); i ++)
        {
            if (pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsDateText().get(i).getText().equals(TestUtil.getStringDateFormat(DEP_DATE,"EEE, MMM dd")))
            {
                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsDateText().get(i).click();

                WaitUtil.untilPageLoadComplete(getDriver());

                WaitUtil.untilTimeCompleted(2000);

                // Validating date selected is displayed in bold
                ValidationUtil.validateTestStep("Validating selected date is outlined bold-black",
                        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsDateText().get(i).getCssValue("font-Weight"),FONT_BOLD);

                break;
            }
        }


//-- Step 11: Validate each tile has top right aligned the date
//Duplicated with Step 8

//-- Step 12: Validate for the past dates, the tile is greyed out and the verbiage "Not available" center aligned
       //Invalid step. For past dates, should not display verbiage at all

//-- Step 13: Validate that for dates with top left aligned yellow circle MC, there is a highlighter yellow verbiage SAVE X,XXX Miles at the bottom of the tile
        validateFSMCISavingText(5);

////-- Step 14: Validate that each tile has center aligned the flight price in format X,XXX Miles + $XXX.XX
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsMCIconText().size(); i ++)
        {
                ValidationUtil.validateTestStep("Validating save text is displayed on tiles " + i,
                        TestUtil.verifyElementDisplayed(pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsLowestFareInMilesText()));
        }

//-- Step 15: For sold out flights or dates in which Spirit does not flight to the destination, there should be a greyed out tile with center aligned verbiage "Not Available" inside
        //Select city pair on which flight has no flights
        pageObjectManager.getFlightAvailabilityPage().getNewSearchButton().click();
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE1, ARR_AIRPORTS, ARR_AIRPORT_CODE1);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        WaitUtil.untilTimeCompleted(2000);

        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getDepCalBlocksGridView().size(); i ++)
        {
            ValidationUtil.validateTestStep("Validating not available text for dates which Spirit doesn't have flights",
                    pageObjectManager.getFlightAvailabilityPage().getDepCalBlocksGridView().get(i).getText(),NO_FLIGHT);
        }
    }

    public void validateFSMCISavingText(int nextMonth)
    {
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsMCIconText().size(); i ++)
        {
            //if dateBox has mastercard icon that is not invisible, validate the savings text when not invisible
            if( !pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsMCIconText().get(i).getAttribute("class").contains("invisible") &&
                    !pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsSavingsText().get(i).getAttribute("class").contains("invisible"))
            {
                ValidationUtil.validateTestStep("The mileage save text is displayed" ,
                        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsSavingsText().get(i).isDisplayed());

                //break loop on last iteration
                break;
            }

            //if the departing carousel date is on the last iteration, click on the next month
            if ( i == pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsMCIconText().size() - 1 )
            {
                if(nextMonth == 0 )
                {
                    break;
                }

                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselRightButton().get(0).click();
                WaitUtil.untilPageLoadComplete(getDriver());
                validateFSMCISavingText(nextMonth - 1);
            }
        }
    }

    public void mcIcon (int nextMonth){
        final String FONT_YELLOW        = "rgb(255, 236, 0)";
        for (int i = 0; i < pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsMCIconText().size(); i ++)
        {
            if (!pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsMCIconText().get(i).getAttribute("class").contains("invisible"))
            {

                ValidationUtil.validateTestStep("Validate there is MC yellow circle on the corner if available",
                        pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsMCIconText().get(i).getCssValue("background"),FONT_YELLOW);

                if(!pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsMCIconText().get(i).getCssValue("text-align").contains("center")){
                    ValidationUtil.validateTestStep("Validate MC text appear in Date block is not left aligned",false);
                }

                //break loop on last iteration
                break;

            }

            //if the departing carousel date is on the last iteration, click on the next month
            if ( i == pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselDateCardsMCIconText().size() - 1 )
            {
                if(nextMonth == 0 )
                {
                    break;
                }

                pageObjectManager.getFlightAvailabilityPage().getDepartingCarouselRightButton().get(0).click();
                WaitUtil.untilPageLoadComplete(getDriver());
                mcIcon(nextMonth - 1);
            }
        }
    }
}