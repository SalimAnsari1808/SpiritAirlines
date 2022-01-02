package com.spirit.misc;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//**********************************************************************************************
//Test Case ID: Bag_Embargo_Testing_Assistance_RoundTrip
//Description: Bags Embargo Testing Assistance for Round Trip
//Created By: Un Fai Chan
//Created On: 12/4/2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class Bag_Embargo_Testing_Assistance_RoundTrip_Int_Dom extends BaseClass {

    @Parameters("platform")
    @Test(groups = {"BookPath", "RoundTrip", "Flight", "DomesticInternational", "Outside21Days", "Child", "Guest", "BookIt","NoBags", "NoSeats"})
    public void Bag_Embargo_Test(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID Bag_Embargo_Testing_Assistance_RoundTrip_Int_Dom under GoldFinger Suite on " + platform + " Browser", true);
        }

        final List<String> INTERNATIONAL_FLIGHTS = new ArrayList<>(Arrays.asList("GUA","PTY","SAP","MGA","SJO","SAL","AXM","CLO","GYE","MDE","BOG","CTG","LIM","BQN","MBJ","SJU","STX","AUA","PAP","STI","SXM","KIN","PUJ","SDQ","STT"));

        // For storing the index of each failure case for each iteration
        List<Integer> failList = new ArrayList<>();

        //Step 1 : open browser
//        openBrowser(platform);

        //Step 2 : For each international flight i
        for (int i = 0; i < INTERNATIONAL_FLIGHTS.size() ; i++){
//        for (int i = 0; i < 1; i++){
            //TODO:: Implement better methods to quit the driver
            if (i != 0){
                getDriver().quit();
            }
            openBrowser(platform);
            final String LANGUAGE           = "English";
            final String JOURNEY_TYPE       = "Flight";
            final String TRIP_TYPE 	        = "RoundTrip";
            final String DEP_AIRPORTS 	    = "AllLocation";
            final String DEP_AIRPORT_CODE   = INTERNATIONAL_FLIGHTS.get(i);;
            final String ARR_AIRPORTS       = "AllLocation";
            final String ARR_AIRPORT_CODE 	= "FLL";
            final String DEP_DATE           = "5";
            final String ARR_DATE           = "10";
            final String ADULTS	            = "3";
            final String CHILDREN 	        = "0";
            final String INFANT_LAP	        = "0";
            final String INFANT_SEAT        = "0";
            final int PAX_COUNT             = Integer.parseInt(ADULTS) + Integer.parseInt(CHILDREN);
            final int MAX_CHK_BAGS          = 1;

            //Flight Availability Page Constant Values
            final String DEP_FLIGHT         = "AutoSelect";
            final String ARR_Flight         = "AutoSelect";
            final String FARE_TYPE	        = "Standard";
            final String UPGRADE_VALUE	    = "BookIt";


            //Home Page Methods
            pageMethodManager.getHomePageMethods().launchSpiritApp();
            pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
            pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
            pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
            pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
            pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
            pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
            pageMethodManager.getHomePageMethods().clickSearchButton();

            //Flight Availability Methods
            pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
            pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
            pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
            pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

            //Passenger Info Methods
            pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
            pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
            pageMethodManager.getPassengerInfoMethods().clickContinueButton();

            //Bags Page Methods
            for (int paxIndex = 0; paxIndex < PAX_COUNT; paxIndex ++){
                // Click Departure Bags and validate
                pageObjectManager.getBagsPage().getDepartingSportingEquipmentLinkButton().get(paxIndex).click();
                // Defining grey sign color
                String GreySignColor = JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardMinusButton().get(paxIndex), "color");

                if (!validateDepartingCheckBags(paxIndex,GreySignColor,failList,MAX_CHK_BAGS,i)){
                    break;
                }

                pageObjectManager.getBagsPage().getReturningSportingEquipmentLinkButton().get(paxIndex).click();

                if (!validateReturningCheckBags(paxIndex,GreySignColor,failList,MAX_CHK_BAGS,i)){
                    break;
                }

            }
        }

        if (!failList.isEmpty()){
            System.out.println("The following " + failList.size() + " international flights did not pass the test: ");
            for (int i = 0; i < failList.size(); i++){
                System.out.println(INTERNATIONAL_FLIGHTS.get(failList.get(i)));
            }
            ValidationUtil.validateTestStep("At least one of the international flight did not pass bag embargo test", false);
        }else{
            ValidationUtil.validateTestStep("Every international flight has been verified with bag embargo", true);
        }
    }

    private boolean checkIfAllDepartingThreeBagsPlusGreyedOut(int paxIndex, String greySignColor){
        if (!JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(paxIndex),"color").equals(greySignColor)
                || !JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(paxIndex),"color").equals(greySignColor)
                || !JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingBicyclePlusButton().get(paxIndex), "color").equals(greySignColor)){
            return false;
        }
        return true;
    }

    private boolean checkIfAllReturningThreeBagsPlusGreyedOut(int paxIndex, String greySignColor){
        if (!JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(paxIndex),"color").equals(greySignColor)
                || !JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningSurfBoardPlusButton().get(paxIndex),"color").equals(greySignColor)
                || !JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningBicyclePlusButton().get(paxIndex), "color").equals(greySignColor)){
            return false;
        }
        return true;
    }

    private boolean validateDepartingCheckBags(int paxIndex, String GreySignColor, List<Integer> failList, int MAX_CHK_BAGS, int i){
        pageObjectManager.getBagsPage().getDepartingCarryOnPlusButton().get(paxIndex).click();
        WaitUtil.untilTimeCompleted(100);
        if (!JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getDepartingCarryOnPlusButton().get(paxIndex), "color").equals(GreySignColor)){
            failList.add(i);
            return false;
        }

        clickWebElement(pageObjectManager.getBagsPage().getDepartingCheckedBagPlusButton().get(paxIndex),MAX_CHK_BAGS);
        WaitUtil.untilTimeCompleted(100);
        if (!checkIfAllDepartingThreeBagsPlusGreyedOut(paxIndex,GreySignColor)){
            failList.add(i);
            return false;
        }
        clickWebElement(pageObjectManager.getBagsPage().getDepartingCheckedBagMinusButton().get(paxIndex),MAX_CHK_BAGS);

        clickWebElement(pageObjectManager.getBagsPage().getDepartingSurfBoardPlusButton().get(paxIndex),MAX_CHK_BAGS);
        WaitUtil.untilTimeCompleted(100);
        if (!checkIfAllDepartingThreeBagsPlusGreyedOut(paxIndex,GreySignColor)){
            failList.add(i);
            return false;
        }
        clickWebElement(pageObjectManager.getBagsPage().getDepartingSurfBoardMinusButton().get(paxIndex),MAX_CHK_BAGS);

        clickWebElement(pageObjectManager.getBagsPage().getDepartingBicyclePlusButton().get(paxIndex),MAX_CHK_BAGS);
        WaitUtil.untilTimeCompleted(100);
        if (!checkIfAllDepartingThreeBagsPlusGreyedOut(paxIndex,GreySignColor)){
            failList.add(i);
            return false;
        }
        clickWebElement( pageObjectManager.getBagsPage().getDepartingBicycleMinusButton().get(paxIndex),MAX_CHK_BAGS);
        return true;
    }

    private boolean validateReturningCheckBags(int paxIndex, String GreySignColor, List<Integer> failList, int MAX_CHK_BAGS, int i){
        pageObjectManager.getBagsPage().getReturningCarryOnPlusButton().get(paxIndex).click();
        WaitUtil.untilTimeCompleted(100);
        if (!JSExecuteUtil.getElementBeforeProperty(getDriver(), pageObjectManager.getBagsPage().getReturningCarryOnPlusButton().get(paxIndex), "color").equals(GreySignColor)){
            failList.add(i);
            return false;
        }

        clickWebElement(pageObjectManager.getBagsPage().getReturningCheckedBagPlusButton().get(paxIndex),MAX_CHK_BAGS);
        WaitUtil.untilTimeCompleted(100);
        if (!checkIfAllReturningThreeBagsPlusGreyedOut(paxIndex,GreySignColor)){
            failList.add(i);
            return false;
        }
        clickWebElement(pageObjectManager.getBagsPage().getReturningCheckedBagMinusButton().get(paxIndex),MAX_CHK_BAGS);

        clickWebElement(pageObjectManager.getBagsPage().getReturningSurfBoardPlusButton().get(paxIndex),MAX_CHK_BAGS);
        WaitUtil.untilTimeCompleted(100);
        if (!checkIfAllReturningThreeBagsPlusGreyedOut(paxIndex,GreySignColor)){
            failList.add(i);
            return false;
        }
        clickWebElement(pageObjectManager.getBagsPage().getReturningSurfBoardMinusButton().get(paxIndex),MAX_CHK_BAGS);

        clickWebElement(pageObjectManager.getBagsPage().getReturningBicyclePlusButton().get(paxIndex),MAX_CHK_BAGS);
        WaitUtil.untilTimeCompleted(100);
        if (!checkIfAllReturningThreeBagsPlusGreyedOut(paxIndex,GreySignColor)){
            failList.add(i);
            return false;
        }
        clickWebElement(pageObjectManager.getBagsPage().getDepartingBicycleMinusButton().get(paxIndex),MAX_CHK_BAGS);
        return true;
    }

    private void clickWebElement (WebElement element, int times){
        try {
            for (int i = 0 ; i < times ; i++){
                WaitUtil.untilTimeCompleted(100);
                element.click();
            }
        }catch (Exception e){
            System.out.println("Unable to click the element: " +  element.getText());
        }
    }
}
