package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//TODO: [IN:23950] CP: BP: PROD: Passenger Information Page PI: Already member is use pop up is missing when the user enters an existing email account and select "Yes, I want to become a Free Spirit member" checkbox
//Test Case ID: TC91211
//Test Case Name: Task 24784: 35280 Customer Info_CP_BP_Already member pop up modal wireframe
//Created By:  Gabriela
//Created On:  01-Aug-2019
//Reviewed By: Salim Ansari
//Reviewed On: 13-Aug-2019
//**********************************************************************************************

public class TC91211 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "OneWay", "DomesticDomestic", "Adult", "PassengerinfoSignUp", "PassengerInformationUI", "ActiveBug"})
    public void Customer_Info_CP_BP_Already_member_pop_up_modal_wireframe(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91211 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE                   = "English";
        final String JOURNEY_TYPE               = "Flight";
        final String TRIP_TYPE                  = "OneWay";
        final String DEP_AIRPORTS               = "AllLocation";
        final String DEP_AIRPORT_CODE           = "FLL";
        final String ARR_AIRPORTS               = "AllLocation";
        final String ARR_AIRPORT_CODE           = "MCO";
        final String DEP_DATE                   = "2";
        final String ARR_DATE                   = "NA";
        final String ADULT                      = "1";
        final String CHILD                      = "0";
        final String INFANT_LAP                 = "0";
        final String INFANT_SEAT                = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                 = "NonStop";
        final String FARE_TYPE                  = "Standard";
        final String UPGRADE_VALUE              = "BookIt";

        //Passenger Info Page Constant Values
        final String PASSENGER_URL              = "/book/passenger";
        final String POP_UP_TITLE               = "$9 Fare Club";
        final String BACKGROUND_YELLOW          = "rgba(255, 236, 0, 1)";
        final String UNDER_HEADER               = "The email address you entered is already in use";
        final String EMAIL_INPUT_INFO           = "Email Address or Free Spirit Number";
        final String PASSWORD                   = "Password";
        final String UNDER_LOG_IN               = "No, I don't want to join and save\n" +
                "Your selection will be priced at the standard rates";

        //open browser
        openBrowser(platform);

        /*********************************HOME PAGE******************************************************/
//-- Step 1: Begin creating a booking with any number of PAX and continue to the customer information page.
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
        WaitUtil.untilPageLoadComplete(getDriver());
//-- Step 2: Verify customer Info page loads
        ValidationUtil.validateTestStep("Validating Passenger Info Page URL",
                getDriver().getCurrentUrl(), PASSENGER_URL);

        String farePrice = pageObjectManager.getHeader().getItineraryTotalAmountText().getText();
        System.out.println("farePrice " + farePrice);

//-- Step 3: Enter information for s $9FC Member and other all PAX
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("NineFCMember");
        pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0).sendKeys(passengerInfoData.title);
        pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).sendKeys(passengerInfoData.firstName);
        pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).sendKeys(passengerInfoData.lastName);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(passengerInfoData.dob);

//-- Stp 4: On the contact section of the page fill out all information and for the email put a FS member email address
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("FSEmail");

        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //Filing email field with FS member email address
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().clear();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().sendKeys(loginCredentialsData.userName);

        //Filing confirm email field with FS member email address
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().clear();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPassengerInfoPage().getContactPersonConfirmEmailTextBox().sendKeys(loginCredentialsData.userName);

        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPassengerInfoPage().getYesIwantToJoinFSCheckBox().click();

        //TODO: [IN:23950] CP: BP: PROD: Passenger Information Page PI: Already member is use pop up is missing when the user enters an existing email account and select "Yes, I want to become a Free Spirit member" checkbox
//-- Step 5: A pop up should appear saying you are already a FS member
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Pop up displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpHeaderText()));

//-- Step 6: Verify modal Should have a left aligned $9 FC logo yellow background.
        ValidationUtil.validateTestStep("Validating POp UP header",
                pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpHeaderText().getText(), POP_UP_TITLE);

        ValidationUtil.validateTestStep("Verify $9FC tile yellow background",
                pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpHeaderBackgroundPanel().getCssValue("background-color"),BACKGROUND_YELLOW);

//-- Step 7: Verify modal Should have a right aligned X to close modal
        ValidationUtil.validateTestStep("X button to close modal is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpCloseButton()));

//-- Step 8: Verify modal Should have "Join and save on flights and bags for everyone on your bookings." (In Bold)
        //Invalid Step

//-- Step 9: Verify modal Should have the text "The email address you entered is already in use. Enter your credentials below to log in and receive $9 Fare Club savings. (If $9DFC flight is selected."
        ValidationUtil.validateTestStep("Validating information displayed under header",
                pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpUnderHeaderText().getText(),UNDER_HEADER);

//-- Step 10: The following should not be present Labeled within with Facebook logo LOG IN WITH FACEBOOK center aligned is a rectangle with dark blue background color, text is white, Facebook logo is a blue in a white square.
        //NOt valid.

//-- Step 11: Verify modal has a Center aligned horizontal line OR horizontal line
        //Not Valid

//-- Step 12: Verify The following is shown "put text box labeled above on the left Email or FREE SPIRIT Number
        ValidationUtil.validateTestStep("Validating right Email or FREE SPIRIT Number field labeled",
                pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpEmailAddressOrFSNumberLabel().getText(), EMAIL_INPUT_INFO);

//-- Step 13: Verify text box labeled above on the left Password Right aligned below is RESET PASSWORD
        ValidationUtil.validateTestStep("Validating right Password field labeled",
                pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpPasswordLabel().getText(), PASSWORD);

//-- Step 14: Verify modal LOG IN primary button when clicked user email or FS number entered is matched with password for user to log in. Upon being logged in the user profile details are populated on the page, and the user receives membership benefits
        ValidationUtil.validateTestStep("Validating Log In button displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpLoginButton()));

//-- Step 15: Verify The following text should be shown "No, I don't want to join and save" and "Your selection will be priced at the standard rates" on the modal
        ValidationUtil.validateTestStep("Validating right info displayed under Log In button",
                pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpUnderLoginButtonText().getText(), UNDER_LOG_IN);

//-- Step 16: Verify Center aligned secondary button CONTINUE WITH STANDARD FARES when clicked prices are updated to reflect standard prices and pop up is closed, user is processed to the bags page. (Step in repair)
        ValidationUtil.validateTestStep("Validating CONTINUE WITH STANDARD FARES is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpContinueWithStandardFaresButton()));

        pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpContinueWithStandardFaresButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating Passenger Info Page URL",
                getDriver().getCurrentUrl(), PASSENGER_URL);

        ValidationUtil.validateTestStep("Validating Standard prices still reflected",
                pageObjectManager.getHeader().getItineraryTotalAmountText().getText(),farePrice);

// Continuing with step 14.
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPassengerInfoPage().getYesIwantToJoinFSCheckBox().click();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPassengerInfoPage().getYesIwantToJoinFSCheckBox().click();

        //Typing FS email
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpEmailAddressOrFSNumberTextBox().sendKeys(loginCredentialsData.userName);
        //Typing Password
        pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpPasswordTextBox().sendKeys(loginCredentialsData.password);

        //Click on Log In Button
        pageObjectManager.getPassengerInfoPage().getAlreadyAMemberPopUpLoginButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        PassengerInfoData passengerInfoData1 = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("FSMember");

//        System.out.println("title page: " +  pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0).getAttribute("value").replace(".",""));
//        System.out.println("passengerInfoData1.title " + passengerInfoData1.title);
//        System.out.println("name page: " + pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value"));
//        System.out.println("passengerInfoData1.firstName: " + passengerInfoData1.firstName);
//        System.out.println("last name page: " + pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"));
//        System.out.println("passengerInfoData1.lastName " + passengerInfoData1.lastName);
//        System.out.println("DOB Page: " +  pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).getAttribute("value"));
//        System.out.println("passengerInfoData1.dob " + passengerInfoData1.dob);
//        System.out.println("FSN page " +  pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0).getAttribute("value"));
//        System.out.println("loginCredentialsData.fsNumber " + loginCredentialsData.fsNumber);

        //Validating right information id auto populated for FS member
        ValidationUtil.validateTestStep("Validating title auto populated is correct",
                pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(0).getAttribute("value").replace(".",""),passengerInfoData1.title.replace(".",""));

        ValidationUtil.validateTestStep("Validating first name auto populated is correct",
                pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value"),passengerInfoData1.firstName);

        ValidationUtil.validateTestStep("Validating last name auto populated is correct",
                pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value"),passengerInfoData1.lastName);

        ValidationUtil.validateTestStep("Validating DOB auto populated is correct",
                pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).getAttribute("value"),passengerInfoData1.dob);

        ValidationUtil.validateTestStep("Validating FS Number auto populated is correct",
                pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(0).getAttribute("value"),loginCredentialsData.fsNumber);

    }
}

