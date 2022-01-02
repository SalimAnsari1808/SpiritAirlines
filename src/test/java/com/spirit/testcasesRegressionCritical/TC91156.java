package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Map;

//**********************************************************************************************
//Test Case ID: TC91156
//Description: $9FCBooking_CP_BP_Delete a card that was added
//Created By : Anthony Cardona
//Created On : 19-Jun-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC91156 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath" , "OneWay" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "NineDFC" , "NonStop" , "BookIt" , "NoBags" , "NoSeats" ,"CheckInOptions", "MasterCard" , "AccountProfileUI" })
    public void $9FCBooking_CP_BP_Delete_a_card_that_was_added(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91156 under RegressionCritical Suite on " + platform + " Browser", true);
        }

        //open browser
        openBrowser(platform);

        //Home Page Method
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//Step 1: create a 9DFC member with 0 miles
        //return map of member credentials
        Map<String, String> memberCredentials = pageMethodManager.getMemberEnrollmentPageMethods().getNew9FCMemberInformation();

        //User Constant Variables
        final String USER_EMAIL          = memberCredentials.get("email");
        final String USER_PASSWORD       = memberCredentials.get("password");

        createBookingAndSaveCard(USER_EMAIL, USER_PASSWORD);

//Step 2: Go to edit billing information page
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getHeader().getUserDropDown().click();

        //wait for 1.5 sec
        WaitUtil.untilTimeCompleted(1500);
        pageObjectManager.getHeader().getMyAccountUserLink().click();
        ValidationUtil.validateTestStep("The user clicks on My account link", true);
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getAccountProfilePage().getLeftMenuBillingInformationLink().click();
        ValidationUtil.validateTestStep("The user clicks on Edit billing link", true);

//Step 3: Validate that the primary card does not have the option to be deleted
        //this xpath should not exist
        try {
            getDriver().findElement(By.xpath
                    ("(//h4[contains(text(),'Primary Card') or contains(text(),'Tarjeta primaria')]/../../following-sibling::div//tbody/tr//td)[6]//a[contains(text(),'Delete')]"));
        }
        catch (Exception e)
        {
            ValidationUtil.validateTestStep("The delete button is not present for the primary card" , true);
        }


//Step 4 : Validate that the newly added card can be deleted and modified
        ValidationUtil.validateTestStep("The Additional Card does have a delete button", TestUtil.verifyElementDisplayed(
                pageObjectManager.getAccountProfilePage().getBillingInformationAdditionalCardDeleteLink()));


//Step 5: Click on the delete link for the additional card
        pageObjectManager.getAccountProfilePage().getBillingInformationAdditionalCardDeleteLink().get(0).click();
        ValidationUtil.validateTestStep("The user correctly clicks on the Delete additional card link", true);

//Step 6: Click outside the modal
        WaitUtil.untilTimeCompleted(1200);
        Actions action = new Actions(getDriver());
        action.moveByOffset(100,100).click().perform();//nothing should happen
        //Modal should still be displayed
        TestUtil.verifyElementDisplayed(pageObjectManager.getAccountProfilePage().getDeleteCardPopupDeleteCardButton());
        ValidationUtil.validateTestStep("The modal is still displayed" , true );

//Step 7: Click on the X of the modal
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getAccountProfilePage().getDeleteCardPopupCloseLink().click();
        ValidationUtil.validateTestStep("The user clicks on the modal close button", true);

//Step 8: Click on the delete hyperlink for the additional card
        pageObjectManager.getAccountProfilePage().getBillingInformationAdditionalCardDeleteLink().get(0).click();
        ValidationUtil.validateTestStep("The user correctly clicks on the Delete additional card link", true);

//Step 9: Click on delete card link
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getAccountProfilePage().getDeleteCardPopupDeleteCardButton().click();
        ValidationUtil.validateTestStep("The user deletes the additional card", true);

        //validate card was deleted
        WaitUtil.untilTimeCompleted(1200);
//        String tableBodyStr = getDriver().findElement(By.xpath("(//h4[contains(text(),'Additional Cards') or contains(text(),'Tarjetas Adicionales')]/../../following-sibling::div[2])//tbody")).getText().trim();
        String tableBodyStr = getDriver().findElement(By.xpath("(//h4[contains(text(),'Additional Cards') or contains(text(),'Tarjetas Adicionales')]/../../following-sibling::div)//tbody")).getText().trim();
        ValidationUtil.validateTestStep("The Card was successfully deleted", tableBodyStr.length() ==0);

    }

    private void createBookingAndSaveCard(String userName, String password)
    {
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "5";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILDREN           = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String SORT_BY            = "Departure";
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Member";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Value
        final String OPTIONS_VALUE      = "CheckInOption:MobileFree";

        //payment page constant value
        final String CARD_TYPE          = "MasterCard";
        final String TRAVEL_GUARD       = "Notrequired";


        //Home Page Methods
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        pageObjectManager.getHeader().getSignInLink().click();
        pageObjectManager.getHomePage().getUserNameBox().sendKeys(userName);
        pageObjectManager.getHomePage().getPasswordBox().sendKeys(password);
        pageObjectManager.getHomePage().getLoginButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDREN, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectSortingOption("Dep", SORT_BY);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        //Seats page methods
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        //Options page methods
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
        //Payment page methods
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageObjectManager.getPaymentPage().getPaymentSectionSaveCardCLabel().click(); //Click to save the card to the new user
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);
        //confirmation page
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getHeader().getSpiritLogoImage().click();
        JSExecuteUtil.refreshBrowser(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
    }
}