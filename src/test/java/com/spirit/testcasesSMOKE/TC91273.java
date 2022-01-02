package com.spirit.testcasesSMOKE;


import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91273
//Description:  Uplift_CP_BP_Flight Only_FS_Standard Fare
//Created By : Sunny Sok
//Created On : 09-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 11-Apr-2019
//**********************************************************************************************
public class TC91273 extends BaseClass{
    @Parameters ({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "Outside21Days" , "Adult" , "FreeSpirit" ,"Connecting" , "CarsUI","HotelsUI", "BookIt" ,
            "CarryOn" , "CheckedBags" , "NoSeats" ,"ShortCutBoarding", "CheckInOptions" , "TravelInsuranceBlock", "OptionalUI","MasterCard"} )
    public void Uplift_CP_BP_Flight_Only_FS_Standard_Fare (@Optional("NA")String platform){
        //****************************Navigate to Option Page**************************
        //Mention Suite and Browser in reports
        if(!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91273 under SMOKE Suite on " + platform + " Browser"   , true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "English";
        final String LOGIN_EMAIL 		= "FSEmail";
        final String JOURNEY_TYPE 		= "Flight";
        final String TRIP_TYPE 			= "RoundTrip";
        final String DEP_AIRPORTS 		= "AllLocation";
        final String DEP_AIRPORT_CODE 	= "FLL";
        final String ARR_AIRPORTS 		= "AllLocation";
        final String ARR_AIRPORT_CODE 	= "MCO";
        final String DEP_DATE 			= "125";
        final String ARR_DATE 			= "128";
        final String ADULT				= "1";
        final String CHILD				= "0";
        final String INFANT_LAP			= "0";
        final String INFANT_SEAT		= "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		= "NonStop";
        final String ARR_Flight 		= "NonStop";
        final String FARE_TYPE			= "Standard";
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
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
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
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getBagsPage().getContinueWithStandardBagsContainerContinueButton().click();

        //Seats Page Methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //put wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
        //**********************Validation to Option Page******************************
        //declare constant used in validation
        final String BOOKING_STATUS     = "Confirmed";
        final String CONFIRMATION_URL   = "book/confirmation";
        double itineraryTotalAmount = Double.parseDouble(pageObjectManager.getHeader().getItineraryTotalAmountText().getText().replace("$",""));
        int daysOut	= Integer.parseInt(ARR_DATE) - Integer.parseInt(DEP_DATE);


        //STEP--10,11
        //****************************************CAR SECTION*****************************************
        //check Car upsell appear on page
        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPanel().size(); i ++)
        {
            double hotelPrice = Double.parseDouble(pageObjectManager.getHotelPage().getHotelCardPriceLink().get(i).getText().replace("$",""));


            if (((hotelPrice*daysOut) + itineraryTotalAmount) >= 300)
            {
                ValidationUtil.validateTestStep("Validating Hotel uplift options is available when booking is above $300",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardUpliftPricingText().get(i)));

                pageObjectManager.getHotelPage().getHotelCardNameText().get(i).click();
                WaitUtil.untilPageLoadComplete(getDriver());
                WaitUtil.untilTimeCompleted(1000);

                ValidationUtil.validateTestStep("Validating uplift is displayed under car description window",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpUpliftPricingText()));

                pageObjectManager.getHotelPage().getHotelPopUpExitIconLink().click();
                WaitUtil.untilTimeCompleted(1200);
            }
        }
//        if(TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardUpliftPricingText())){
//            for(int upliftcounter=0;upliftcounter<pageObjectManager.getCarPage().getCarCardUpliftPricingText().size();upliftcounter++){
//                if(pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(upliftcounter).isDisplayed()){
//                    //Validate Car Uplift Tag is displaying on Option page
//                    ValidationUtil.validateTestStep("Car Uplift Tag is displaying on Option page when booking is above $300", pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(upliftcounter).isDisplayed());
//
//                    //click on card view link
//                    pageObjectManager.getCarPage().getCarcardViewLink().get(upliftcounter).click();
//
//                    //break loop
//                    break;
//                }
//            }
//
//            //Put the wait till page load completely
//            WaitUtil.untilPageLoadComplete(getDriver());
//
//            //put wait for 2 sec
//            WaitUtil.untilTimeCompleted(2000);
//
//            //Validate Car Uplift Tag on Car View Pop-Up
//            ValidationUtil.validateTestStep("Car Uplift Tag on Car View Pop-Up is displaying on Option page when booking is above $300",
//                    pageObjectManager.getCarPage().getCarPopUpUpliftPricingText().isDisplayed());
//
//            //click on card view link
//            pageObjectManager.getCarPage().getCarPopUpExitIconButton().click();
//
//            //Put the wait till page load completely
//            WaitUtil.untilPageLoadComplete(getDriver());
//        }


        //STEP--12
        //****************************************HOTEL SECTION*****************************************
        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPanel().size(); i ++)
        {
            double hotelPrice = Double.parseDouble(pageObjectManager.getHotelPage().getHotelCardPriceLink().get(i).getText().replace("$",""));


            if (((hotelPrice*daysOut) + itineraryTotalAmount) >= 300)
            {
                ValidationUtil.validateTestStep("Validating Hotel uplift options is available when booking is above $300",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardUpliftPricingText().get(i)));

                pageObjectManager.getHotelPage().getHotelCardNameText().get(i).click();
                WaitUtil.untilPageLoadComplete(getDriver());
                WaitUtil.untilTimeCompleted(1000);

                ValidationUtil.validateTestStep("Validating uplift is displayed under car description window",
                        TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpUpliftPricingText()));

                pageObjectManager.getHotelPage().getHotelPopUpExitIconLink().click();
                WaitUtil.untilTimeCompleted(1200);
            }
        }
//        if(TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardUpliftPricingText())){
//            for(int upliftcounter=0;upliftcounter<pageObjectManager.getHotelPage().getHotelCardUpliftPricingText().size();upliftcounter++){
//                if(pageObjectManager.getHotelPage().getHotelCardUpliftPricingText().get(upliftcounter).isDisplayed()){
//                    //Validate Car Uplift Tag is displaying on Option page
//                    ValidationUtil.validateTestStep("Hotel Uplift Tag is displaying on Option page when booking is above $300",
//                            pageObjectManager.getHotelPage().getHotelCardUpliftPricingText().get(upliftcounter).isDisplayed());
//
//                    //click on card view link
//                    pageObjectManager.getHotelPage().getHotelCardViewLink().get(upliftcounter).click();
//
//                    //break loop
//                    break;
//                }
//            }
//
//            //Put the wait till page load completely
//            WaitUtil.untilPageLoadComplete(getDriver());
//
//            //put wait for 2 sec
//            WaitUtil.untilTimeCompleted(2000);
//
//            //Validate Hotel Uplift Tag on Hotel View Pop-Up
//            ValidationUtil.validateTestStep("Hotel Uplift Tag on Hotel View Pop-Up is displaying on Option page when booking is above $300",
//                    pageObjectManager.getHotelPage().getHotelPopUpUpliftPricingText().isDisplayed());
//
//            pageObjectManager.getHotelPage().getHotelPopUpExitIconLink().click();
//
//            //Put the wait till page load completely
//            WaitUtil.untilPageLoadComplete(getDriver());
//        }
        pageMethodManager.getHotelPageMethods().selectHotelOnOptionPage("Universal","NA");


        //STEP--13
        //Activity is DESCOPED!!!!!!
        //****************************************ACTIVITY SECTION*****************************************
        //TODO: Out of scope
//        if(TestUtil.verifyElementDisplayed(pageObjectManager.getActivityPage().getActivitiesCardUpliftPricingText())){
//            for(int upliftcounter=0;upliftcounter<pageObjectManager.getActivityPage().getActivitiesCardUpliftPricingText().size();upliftcounter++){
//                if(pageObjectManager.getActivityPage().getActivitiesCardUpliftPricingText().get(upliftcounter).isDisplayed()){
//                    //Validate Car Uplift Tag is displaying on Option page
//                    ValidationUtil.validateTestStep("Activity Uplift Tag is displaying on Option page when booking is above $300", pageObjectManager.getActivityPage().getActivitiesCardUpliftPricingText().get(upliftcounter).isDisplayed());
//
//                    //click on card view link
//                    try{
//                        pageObjectManager.getActivityPage().getActivitiesCardViewLink().get(upliftcounter).click();
//                    }catch (Exception e){
//
//                    }
//
//                    //break loop
//                    break;
//                }
//            }
//
//            //Put the wait till page load completely
//            WaitUtil.untilPageLoadComplete(getDriver());
//
//            //put wait for 2 sec
//            WaitUtil.untilTimeCompleted(2000);
//
//            //Validate Activity Uplift Tag on Activity View Pop-Up
//            ValidationUtil.validateTestStep("Activity Uplift Tag on Activity View Pop-Up is displaying on Option page when booking is above $300", pageObjectManager.getActivityPage().getActivityPopUpUpliftPricingText().isDisplayed());
//
//            //click on close
//            pageObjectManager.getActivityPage().getActivityPopUpExitIconButton().click();
//
//            //Put the wait till page load completely
//            WaitUtil.untilPageLoadComplete(getDriver());
//
//
//        }
        //STEP--14
        //Option Page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(CHECKIN_OPTION);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //STEP--15
        //Payment Page Methods
        //Put the wait till page load completely
        WaitUtil.untilPageLoadComplete(getDriver());

        //Validate Hotel Uplift Tag on Hotel View Pop-Up
        if (Double.parseDouble(pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().replace("$","")) >= 300){
            ValidationUtil.validateTestStep("Pay Monthly From section is displaying on payments page when booking is above $300",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getTotalDueUpLiftPriceText()));
        }

        //STEP--16
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(PAYMENT_CARD);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

        //confirmation page Methods
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

        //Cancelling HBG Reservation
        try {
            //verify booking is confirmed
            ValidationUtil.validateTestStep("Verify complete booking and reached on Confirmation Page",
                    getDriver().getCurrentUrl().contains(CONFIRMATION_URL));

            //verify booking is confirmed
            ValidationUtil.validateTestStep("Verify booking is confirmed on Confirmation Page",
                    pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText().contains(BOOKING_STATUS));

            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
        } catch(AssertionError fail)
        {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            ValidationUtil.validateTestStep("Test case failed on prices validation after Payment Page " + fail.getMessage() , false );
        }
        //This catch block will catch any Exceptions (null pointer, no such element, etc) after Payment and still cancel reservations
        catch (Exception fail)
        {
            pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();
            ValidationUtil.validateTestStep("Test case failed on prices validation after Payment Page " + fail.getMessage() , false );
        }

    }

}