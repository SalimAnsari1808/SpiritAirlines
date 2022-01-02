package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91270
//Description:  Uplift_CP_BP_Flight Only_9DFC_Bare Fare
//Created By : Sunny Sok
//Created On : 03-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 05-Aug-2019
//**********************************************************************************************
public class TC91270 extends BaseClass{

    @Parameters ({"platform"})
    @Test(groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "NineDFC" , "Connecting" , "BookIt" , "CarryOn" , "CheckedBags" , "NoSeats","ShortCutBoarding", "CarsUI" ,"HotelsUI", "CheckInOptions" , "MasterCard" , "TravelInsuranceBlock" , "PaymentUI"})
    public void Uplift_CP_BP_Flight_Only_9DFC_Bare_Fare (@Optional("NA")String platform){

        /******************************************************************************
         *****************************Navigate to Option Page**************************
         ******************************************************************************/

        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91270 under REGRESSION-CRITICAL Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_EMAIL 		= "NineDFCEmail";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "LAS";
        final String DEP_DATE 			= "25";
        final String ARR_DATE 			= "30";
        final String ADULTS				= "1";
        final String CHILDS				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "Connecting";
        final String ARR_Flight 		= "Connecting";
        final String FARE_TYPE			= "Member";
        final String UPGRADE_FARE		= "BookIt";

        //Bags Page constant values
        final String DEP_BAGS 			= "Carry_1|Checked_1";
        final String RET_BAGS			= "Carry_1|Checked_1";

        //Options page constant Values
        final String CHECKIN_OPTION     = "CheckInOption:MobileFree";

        //Payment page constant values
        final String TRAVEL_GUARD 		= "Required";
        final String PAYMENT_CARD 		= "MasterCard";

        //open browser
        openBrowser( platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();


        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
        pageMethodManager.getBagsPageMethods().selectReturnBags(RET_BAGS);
        pageMethodManager.getBagsPageMethods().continueWithSelectingBags();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        /******************************************************************************
         ***********************Validation to Option Page******************************
         ******************************************************************************/
        //STEP--10,11
        //****************************************CAR SECTION*****************************************
        //check Car up sell appear on page
        if(TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardUpliftPricingText())){
            for(int upliftcounter=0;upliftcounter<pageObjectManager.getCarPage().getCarCardUpliftPricingText().size();upliftcounter++){
                if(pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(upliftcounter).isDisplayed()){
                    //Validate Car Uplift Tag is displaying on Option page
                    ValidationUtil.validateTestStep("Car Uplift Tag is displaying on Option page when booking is above $200",
                            TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(upliftcounter)));

                    //click on card view link
                    pageObjectManager.getCarPage().getCarcardViewLink().get(upliftcounter).click();

                    //break loop
                    break;
                }
            }

            //Put the wait till page load completely
            WaitUtil.untilPageLoadComplete(getDriver());

            //put wait for 2 sec
            WaitUtil.untilTimeCompleted(2000);

            //Validate Car Uplift Tag on Car View Pop-Up
            ValidationUtil.validateTestStep("Car Uplift Tag on Car View Pop-Up is displaying on Option page when booking is above $200",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpUpliftPricingText()));

            //click on card view link
            pageObjectManager.getCarPage().getCarPopUpExitIconButton().click();

            //Put the wait till page load completely
            WaitUtil.untilPageLoadComplete(getDriver());
        }


        //STEP--12
        //****************************************HOTEL SECTION*****************************************
        if(TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardUpliftPricingText())){
            for(int upliftcounter=0;upliftcounter<pageObjectManager.getHotelPage().getHotelCardUpliftPricingText().size();upliftcounter++){
                if(pageObjectManager.getHotelPage().getHotelCardUpliftPricingText().get(upliftcounter).isDisplayed()){
                    //Validate Car Uplift Tag is displaying on Option page
                    ValidationUtil.validateTestStep("Hotel Uplift Tag is displaying on Option page when booking is above $200",
                            TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardUpliftPricingText().get(upliftcounter)));

                    //click on card view link
                    pageObjectManager.getHotelPage().getHotelCardViewLink().get(upliftcounter).click();

                    //break loop
                    break;
                }
            }

            //Put the wait till page load completely
            WaitUtil.untilPageLoadComplete(getDriver());

            //put wait for 2 sec
            WaitUtil.untilTimeCompleted(2000);

            //Validate Hotel Uplift Tag on Hotel View Pop-Up
            ValidationUtil.validateTestStep("Hotel Uplift Tag on Hotel View Pop-Up is displaying on Option page when booking is above $200",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpUpliftPricingText()));

            pageObjectManager.getHotelPage().getHotelPopUpExitIconLink().click();

            //Put the wait till page load completely
            WaitUtil.untilPageLoadComplete(getDriver());
        }


        //STEP--13
        //Activity is DESCOPED!!!!!!
        //****************************************ACTIVITY SECTION*****************************************

        if(TestUtil.verifyElementDisplayed(pageObjectManager.getActivityPage().getActivitiesCardUpliftPricingText())){
            for(int upliftcounter=0;upliftcounter<pageObjectManager.getActivityPage().getActivitiesCardUpliftPricingText().size();upliftcounter++){
                if(pageObjectManager.getActivityPage().getActivitiesCardUpliftPricingText().get(upliftcounter).isDisplayed()){
                    //Validate Car Uplift Tag is displaying on Option page
                    ValidationUtil.validateTestStep("Activity Uplift Tag is displaying on Option page when booking is above $200",
                            TestUtil.verifyElementDisplayed(pageObjectManager.getActivityPage().getActivitiesCardUpliftPricingText().get(upliftcounter)));

                    //click on card view link
                    try{
                        pageObjectManager.getActivityPage().getActivitiesCardViewLink().get(upliftcounter).click();
                    }catch (Exception e){

                    }

                    //break loop
                    break;
                }
            }

            //Put the wait till page load completely
            WaitUtil.untilPageLoadComplete(getDriver());

            //put wait for 2 sec
            WaitUtil.untilTimeCompleted(2000);

            //Validate Activity Uplift Tag on Activity View Pop-Up
            ValidationUtil.validateTestStep("Activity Uplift Tag on Activity View Pop-Up is displaying on Option page when booking is above $200",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getActivityPage().getActivityPopUpUpliftPricingText()));

            //click on close
            pageObjectManager.getActivityPage().getActivityPopUpExitIconButton().click();

            //Put the wait till page load completely
            WaitUtil.untilPageLoadComplete(getDriver());
        }
        //STEP--14
        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(CHECKIN_OPTION);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //STEP--15
        //Payment Page Methods
        //Put the wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate Hotel Uplift Tag on Hotel View Pop-Up
        ValidationUtil.validateTestStep("Pay Monthly From section is displaying on Option page when booking is above $200",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDuePayMonthlyText()));

        //STEP--16
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(PAYMENT_CARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //declare constant used in validation
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";

        //confirmation page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page", getDriver().getCurrentUrl(),CONFIRMATION_URL);

        //verify booking is confirmed
        ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText(),BOOKING_STATUS);
    }
}