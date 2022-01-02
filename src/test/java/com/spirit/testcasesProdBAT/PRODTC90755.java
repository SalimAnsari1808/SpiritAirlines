package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC90755
//Test Case Name: Task 24795: 35288 Customer Info_CP_BP_Validate customer info
//Created By : Gabriela
//Created On : 23-Jul-2019
//Reviewed By: Salim Ansari
//Reviewed On: 30-Jul-2019
//**********************************************************************************************

public class PRODTC90755 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")

    public void Customer_Info_CP_BP_Validate_customer_info(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90755 under PRODUCTION Suite on " + platform + " Browser", true);
        }
        //Home Page Constant variables
        final String LANGUAGE                   = "English";
        final String JOURNEY_TYPE               = "Flight";
        final String TRIP_TYPE                  = "OneWay";
        final String DEP_AIRPORTS               = "AllLocation";
        final String DEP_AIRPORT_CODE           = "FLL";
        final String ARR_AIRPORTS               = "AllLocation";
        final String ARR_AIRPORT_CODE           = "MCO";
        final String DEP_DATE                   = "90";
        final String ARR_DATE                   = "NA";
        final String ADULT                      = "1";
        final String CHILD                      = "1";
        final String INFANT_LAP                 = "1";
        final String INFANT_SEAT                = "1";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT                 = "NonStop";
        final String FARE_TYPE                  = "Standard";
        final String UPGRADE_VALUE              = "BookIt";

        //Passenger Info Page Constant Values
        final String PASSENGER_URL              = "/book/passenger";
        String PASSENGER_HEADER                 = "Passenger Information";
        String MINOR_INFO                       = "Car seats larger than 15.5\"x25\"x25\"(WxHxD) may not fit in our seats, even if they are FAA approved. " +
                "If you are flying onboard an aircraft and your car seat won't fit safely in your assigned seat, we will " +
                "try to accommodate you. Otherwise, your car seat will be checked at no cost.";
        String KTN_INFO                         = "Members of the CBP Trusted Traveler Program (TSA PreCheck, NEXUS, SENTRI, GLOBAL ENTRY, etc.) can enter their Known Traveler Number here.";
        String KTN_MORE_INFO_URL                = "https://www.tsa.gov/";
        String FSN_INFO                         = "Free Spirit is our frequent flier program. It costs nothing to join. Sign up and you'll be banking miles every time you fly with us.";
        String SERVICE_ANIMAL_INFO              = "Requires Authorization";
        String SERVICE_ANIMAL_INFO_URL          = "Can-I-bring-my-service-emotional-support-or-psychiatric-service-animal-on-my-flight";
        String PORTABLE_OXYGEN_INFO             = "If you plan on using your POC onboard, you must ensure it's FAA compliant.";
        String PORTABLE_OXYGEN_INFO_URL         = "Can-I-bring-my-Portable-Oxygen-Concentrator-on-board";
        String ESAN_INFO                        = "Please view additional guidelines that are required when traveling with an emotional support/psychiatric service animal.";
        String ESAN_INFO_URL                    = "Can-I-bring-my-service-emotional-support-or-psychiatric-service-animal-on-my-flight-";
        String OTHER_INFO                       = "Click for More Information about Other Assistive Devices.";
        String OTHER_INFO_URL                   = "Traveling-with-Special-Needs?_ga";
        String WHEELCHAIR_INFO                  = "Please view the different types of wheelchair service we provide.";
        String WHEELCHAIR_INFO_URL              = "Traveling-with-special-needs-what-type-of-special-assistance-services-do-you-provide-";
        String VOLUNTARY_INFO                   = "The Voluntary Provisions of Emergency Service Program permits qualified law enforcement officers, firefighters and emergency medical technicians to volunteer services during in-flight emergencies.";
        String VOLUNTARY_INFO_URL               = "Voluntary-Provision-of-Emergency-Services-Program-VPESP-";
        String REDRESS_INFO                     = "Unique number that helps TSA eliminate misidentification.";
        String REDRESS_INFO_URL                 = "https://www.tsa.gov/";

        //open browser
        openBrowser(platform);

//-- Step 1: Land on Passenger info page
        /*********************************HOME PAGE******************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

        /*********************************Flight Availability Methods*************************************************/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        /*********************************Passenger Info Methods*************************************************/
        WaitUtil.untilPageLoadComplete(getDriver());
//-- Step 2// URL Validation
        ValidationUtil.validateTestStep("Validating Passenger Info Page URL", getDriver().getCurrentUrl(), PASSENGER_URL);

//-- Step 3: InLine section validation
        //Email field displayed on InLine section
        TestUtil.verifyElementDisplayed( pageObjectManager.getPassengerInfoPage().getUserNameInLineLogInTextBox());

        //Password field displayed on InLine section
        TestUtil.verifyElementDisplayed( pageObjectManager.getPassengerInfoPage().getPasswordInLineLogInTextBox());

        //Reset Password link displayed on InLine section
        TestUtil.verifyElementDisplayed( pageObjectManager.getPassengerInfoPage().getResetPasswordInLineLogInLink());

        //Log In Button displayed on InLine section
        TestUtil.verifyElementDisplayed( pageObjectManager.getPassengerInfoPage().getLogInButtonInLineLogInButton());

//-- Step 4: Header Validation
        //Header Title Validation
        ValidationUtil.validateTestStep("Validating right header page title is displayed",
                pageObjectManager.getPassengerInfoPage().getPassengerInformationText().getText(),PASSENGER_HEADER);

//-- Step 5:
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getPassengerHeaderText().size(); count ++) {

            //Validating Title dropdown for each pax
            ValidationUtil.validateTestStep("Validation Title drop down for Passenger "+count+" is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAdultTitleListDropDown().get(count)));

            //Validating First Name Text Box for each pax
            ValidationUtil.validateTestStep("Validation First Name Text Box for Passenger "+count+" is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(count)));

            //Validating Last Name Text Box for each pax
            ValidationUtil.validateTestStep("Validation Last Name Text Box for Passenger "+count+" is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(count)));

            //Validating Suffix Drop Down for each pax
            ValidationUtil.validateTestStep("Validation Suffix Drop Down for Passenger "+count+" is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAdultSuffixListDropDown().get(count)));

            //Validating DOB text Box for each pax
            ValidationUtil.validateTestStep("Validation DOB text Box for Passenger "+count+" is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(count)));

            //Validating KTN field and tooltip for each pax
            ValidationUtil.validateTestStep("Validation KTN field for Passenger "+count+" is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAdultKTNListTextBox().get(count)));
            ValidationUtil.validateTestStep("Validation KTN field tooltip for Passenger "+count+" is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAdultKTNListTextBox().get(count).findElement(By.xpath("//..//i"))));

            //Validating FS# field and Tooltip for each pax
            ValidationUtil.validateTestStep("Validation FS# field for Passenger "+count+" is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(count)));
            ValidationUtil.validateTestStep("Validation FS# field Tooltip for Passenger "+count+" is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().get(count).findElement(By.xpath("//..//i"))));
        }

//-- Step 6
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

        List<WebElement> yearDOB =pageObjectManager.getCommon().getCalanderYearText();

        for (int count = 0; count < yearDOB.size(); count ++) {
            ValidationUtil.validateTestStep("Validating Years displayed on the table",
                    yearDOB.get(count).getText().length() == 4 );
        }

//-- Step 7: Click any year and validate month table displayed
        String yearVal = yearDOB.get(10).getText();
        yearDOB.get(10).click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating right year displayed after selection",
                pageObjectManager.getCommon().getCalanderCurrentYearText().getText(), yearVal);

//-- Step 8: Click any month and validate days table displayed
        List<WebElement> monthDOB = pageObjectManager.getCommon().getCalanderMonthText();
        List<String> monthName = new ArrayList<String>();
        monthName.add("January");
        monthName.add("February");
        monthName.add("March");
        monthName.add("April");
        monthName.add("May");
        monthName.add("June");
        monthName.add("July");
        monthName.add("August");
        monthName.add("September");
        monthName.add("October");
        monthName.add("November");
        monthName.add("December");

        for (int count = 0; count < monthDOB.size(); count ++) {
            ValidationUtil.validateTestStep("Validating Month list details displayed are correct",
                    monthDOB.get(count).getText(),monthName.get(count));
        }

        ValidationUtil.validateTestStep("Validating only 12 months displayed",
                monthDOB.size()==12);


        monthDOB.get(10).click();
        WaitUtil.untilTimeCompleted(1000);

        List<WebElement> daysDOB = getDisplayedWebElement(pageObjectManager.getCommon().getCalanderDaysText());
        ValidationUtil.validateTestStep("Validating days are 30 or 31 in Number",daysDOB.size()==30|| daysDOB.size()==31);
        WaitUtil.untilTimeCompleted(1000);

        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).click();

        String dateString = TestUtil.getStringDateFormat("5", "MM/dd/yyyy");

        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(dateString+ Keys.TAB);
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating no future date can be selected",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCommon().getErrorMessageLabel()));

//-- Step 9:
        List<WebElement> redress = new ArrayList<>();
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getRedressNumberListLinkButton().size(); count ++) {
            if (pageObjectManager.getPassengerInfoPage().getRedressNumberListLinkButton().get(count).isDisplayed()) {
                redress.add(pageObjectManager.getPassengerInfoPage().getRedressNumberListLinkButton().get(count));
            }
        }

        for (int count = 0; count< pageObjectManager.getPassengerInfoPage().getPassengerHeaderText().size(); count ++) {
            ValidationUtil.validateTestStep("Validating Additional Services is displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getAdditionalServicesListLinkButton().get(count)));
            ValidationUtil.validateTestStep("Validating Redress Number is displayed",
                    TestUtil.verifyElementDisplayed(redress.get(count)));
        }

        int militaryCount = 0;
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getActiveMilitaryPersonnelListCheckBox().size(); count ++) {
            if (pageObjectManager.getPassengerInfoPage().getActiveMilitaryPersonnelListCheckBox().get(count).isDisplayed()) {
                militaryCount++;
            }
        }
        ValidationUtil.validateTestStep("Validating only 1 military check box displayed", militaryCount == 1);

//-- Step 10
        pageObjectManager.getPassengerInfoPage().getTravelWithCarSeatToolTipImage().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validating Tooltip's car seat displaying the right info",
                pageObjectManager.getPassengerInfoPage().getTravelWithCarSeatToolTipWindowBodyText().getText(),MINOR_INFO);

// Step 11: DOB auto populated for children
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().size(); count ++) {
            ValidationUtil.validateTestStep("Validating DOB for children is auto populated and disabled",
                    !pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(count).getAttribute("value").isEmpty());
        }

//-- Step 12 and 13
        int carseatCount = 0;
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().size(); count++) {
            if (pageObjectManager.getPassengerInfoPage().getInfantTravelingWithCarSeatCheckBox().get(count).isDisplayed()) {
                carseatCount++;
            }
        }
        ValidationUtil.validateTestStep("Validating car seat check box displayed", carseatCount == 1);

//-- Step 14
        pageObjectManager.getPassengerInfoPage().getAdditionalServicesListLinkButton().get(0).click();

        ValidationUtil.validateTestStep("Validating Hearing Impaired is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getHearingImpairedListCheckBox().get(0)));

        ValidationUtil.validateTestStep("Validating Vision Disability is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getVisionDisabilityListCheckBox().get(0)));

        ValidationUtil.validateTestStep("Validating Service Animal is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getServiceAnimalListCheckBox().get(0)));

        ValidationUtil.validateTestStep("Validating Portable Oxygen is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getPortableOxygenContainerListCheckBox().get(0)));

        ValidationUtil.validateTestStep("Validating ESAN is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getEmotionalSupportAnimalListCheckBox().get(0)));

        ValidationUtil.validateTestStep("Validating Other (CPAP, nebulizer, ventilator, respirator, cane, walker, etc.) is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getOtherDisabilityListCheckBox().get(0)));

        ValidationUtil.validateTestStep("ValidatingNeed help to/from gate is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getWheelChairToAndFromGateListCheckBox().get(0)));

        ValidationUtil.validateTestStep("Validating Need help to/from seat is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getWheelChairToAndFromSeatListCheckBox().get(0)));

        ValidationUtil.validateTestStep("Validating Completely Immobile is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getWheelChairCompletelyImmobileListCheckBox().get(0)));

        ValidationUtil.validateTestStep("Validating Own wheelchair is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getWheelchairIHaveMyOwnListCheckBox().get(0)));

        ValidationUtil.validateTestStep("Validating VOLUNTARY PROVISION OF EMERGENCY SERVICES PROGRAM is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getVoluntaryProvisionofEmergencyServicesProgramListCheckBox().get(0)));

//-- Step 15: Not valid

//-- Step 16:
        pageObjectManager.getPassengerInfoPage().getRedressNumberListLinkButton().get(0).click();
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("Validate redress text box is displayed after click on redress drop down",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getPassengerRedressNumberListTextBox().get(0)));

//-- Step 17:
        //KTN Tooltip
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultKTNListTextBox().size(); count ++) {
            ValidationUtil.validateTestStep("KTN tooltip displayed",
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getKnownTravelerNumberToolTipImage()));
        }

        //Free Spirit Number Tooltip
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdultFSNumberListTextBox().size(); count ++) {
            ValidationUtil.validateTestStep("FSN tooltip displayed",
                    TestUtil.verifyElementDisplayed( pageObjectManager.getPassengerInfoPage().getFSNumberToolTipImage()));
        }

        //Redress Number Tooltip
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getRedressNumberListLinkButton().size(); count ++) {
            if (pageObjectManager.getPassengerInfoPage().getRedressNumberListLinkButton().get(count).isDisplayed()) {
                pageObjectManager.getPassengerInfoPage().getRedressNumberListLinkButton().get(count).click();
                WaitUtil.untilTimeCompleted(1000);

                ValidationUtil.validateTestStep("Redress Number tooltip displayed for pax " + count,
                        TestUtil.verifyElementDisplayed( pageObjectManager.getPassengerInfoPage().getRedressNumberToolTipImage().get(count)));
            }
        }

        //Click on Additional Services for tooltip validation
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdditionalServicesListLinkButton().size(); count ++) {
            pageObjectManager.getPassengerInfoPage().getAdditionalServicesListLinkButton().get(count).click();
            WaitUtil.untilTimeCompleted(1000);

            //Service Animal Tooltip
            ValidationUtil.validateTestStep("Validating Service animal tooltip displayed for pax " +count,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getServiceAnimalToolTipImage().get(count)));

            //Portable Oxygen Tooltip
            ValidationUtil.validateTestStep("Validating Portable Oxygen tooltip displayed for pax " + count,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getPortableOxygenToolTipImage().get(count)));

            //Emotional/Psychiatric Support Animal Tooltip
            ValidationUtil.validateTestStep("Validating Emotional/Psychiatric Support Animal tooltip displayed for pax " + count,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getEmotionalSupportAnimalToolTipImage().get(count)));

            //Other (CPAP, nebulizer, ventilator, respirator, cane, walker, etc.) Tooltip
            ValidationUtil.validateTestStep("Validating Other (CPAP, nebulizer, ventilator, respirator, cane, walker, etc.) tooltip displayed for pax " + count,
                    TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getOtherServicesToolTipImage().get(count)));
        }

        //VOLUNTARY PROVISION OF EMERGENCY SERVICES PROGRAM Tooltip
        ValidationUtil.validateTestStep("Validating VOLUNTARY PROVISION OF EMERGENCY SERVICES PROGRAM tooltip displayed for pax ",
                TestUtil.verifyElementDisplayed(pageObjectManager.getPassengerInfoPage().getVoluntaryProvisionTooltipImage()));

//-- Step 18
        //KTN Tooltip Verbiage
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getKnownTravelerNumberToolTipImage().size(); count ++) {
            pageObjectManager.getPassengerInfoPage().getKnownTravelerNumberToolTipImage().get(count).click();
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("Validating KTN Tooltip info is correct",
                    pageObjectManager.getCommon().getToolTipWindowBodyText().getText(),KTN_INFO);

            pageObjectManager.getCommon().getToolTipWindowMoreInformationLink().click();
            WaitUtil.untilTimeCompleted(1000);

            TestUtil.switchToWindow(getDriver(),1);
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("The user redirected to the correct pdf page",
                    getDriver().getCurrentUrl(),KTN_MORE_INFO_URL);

            getDriver().close();
            TestUtil.switchToWindow(getDriver(),0);
            WaitUtil.untilTimeCompleted(1000);
            pageObjectManager.getPassengerInfoPage().getKnownTravelerNumberToolTipImage().get(count).click();
        }

//-- Step 19
        //FS Number Tooltip Verbiage
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getFSNumberToolTipImage().size(); count ++) {
            pageObjectManager.getPassengerInfoPage().getFSNumberToolTipImage().get(count).click();
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("Validating FS Number Tooltip info is correct",
                    pageObjectManager.getPassengerInfoPage().getFSNumberToolTipWindowBodyText().getText(), FSN_INFO);
            pageObjectManager.getPassengerInfoPage().getFSNumberToolTipImage().get(count).click();
        }

//-- Step 20. Not Valid Step

//-- Step 21
        //Click on Additional Services for tooltip verbiage validation
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getAdditionalServicesListLinkButton().size(); count ++) {

            //Emotional/Psychiatric Support Animal Tooltip
            pageObjectManager.getPassengerInfoPage().getEmotionalSupportAnimalToolTipImage().get(count).click();
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("Validating Emotional/Psychiatric Support Animal Tooltip info is correct",
                    pageObjectManager.getCommon().getToolTipWindowBodyText().getText(), ESAN_INFO);

            pageObjectManager.getCommon().getToolTipWindowMoreInformationLink().click();
            WaitUtil.untilTimeCompleted(1000);

            TestUtil.switchToWindow(getDriver(),1);
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("The user redirected to the correct pdf page", getDriver().getCurrentUrl(),ESAN_INFO_URL);

            getDriver().close();
            TestUtil.switchToWindow(getDriver(),0);
            WaitUtil.untilTimeCompleted(1000);
            pageObjectManager.getPassengerInfoPage().getEmotionalSupportAnimalToolTipImage().get(count).click();

//-- Step 22
            //Portable Oxygen Tooltip
            pageObjectManager.getPassengerInfoPage().getPortableOxygenToolTipImage().get(count).click();
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("Validating Portable Oxygen Tooltip info is correct",
                    pageObjectManager.getCommon().getToolTipWindowBodyText().getText(), PORTABLE_OXYGEN_INFO);

            pageObjectManager.getCommon().getToolTipWindowMoreInformationLink().click();
            WaitUtil.untilTimeCompleted(1000);

            TestUtil.switchToWindow(getDriver(),1);
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("The user redirected to the correct pdf page", getDriver().getCurrentUrl(),PORTABLE_OXYGEN_INFO_URL);

            getDriver().close();
            TestUtil.switchToWindow(getDriver(),0);
            WaitUtil.untilTimeCompleted(1000);
            pageObjectManager.getPassengerInfoPage().getPortableOxygenToolTipImage().get(count).click();

//-- Step 23
            //Other (CPAP, nebulizer, ventilator, respirator, cane, walker, etc.) Tooltip
            pageObjectManager.getPassengerInfoPage().getOtherServicesToolTipImage().get(count).click();
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("Validating Other (CPAP, nebulizer, ventilator, respirator, cane, walker, etc.) Tooltip info is correct",
                    pageObjectManager.getCommon().getToolTipWindowBodyText().getText(), OTHER_INFO);

            pageObjectManager.getCommon().getToolTipWindowMoreInformationLink().click();
            WaitUtil.untilTimeCompleted(1000);

            TestUtil.switchToWindow(getDriver(),1);
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("The user redirected to the correct pdf page", getDriver().getCurrentUrl(),OTHER_INFO_URL);

            getDriver().close();
            TestUtil.switchToWindow(getDriver(),0);
            WaitUtil.untilTimeCompleted(1000);
            pageObjectManager.getPassengerInfoPage().getOtherServicesToolTipImage().get(count).click();


            //Service Animal Tooltip
            pageObjectManager.getPassengerInfoPage().getServiceAnimalToolTipImage().get(count).click();
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("Validating Service Animal Tooltip info is correct",
                    pageObjectManager.getCommon().getToolTipWindowBodyText().getText(), SERVICE_ANIMAL_INFO);

            pageObjectManager.getCommon().getToolTipWindowMoreInformationLink().click();
            WaitUtil.untilTimeCompleted(1000);

            TestUtil.switchToWindow(getDriver(),1);
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("The user redirected to the correct pdf page",
                    getDriver().getCurrentUrl(),SERVICE_ANIMAL_INFO_URL);

            getDriver().close();
            TestUtil.switchToWindow(getDriver(),0);
            WaitUtil.untilTimeCompleted(1000);
            pageObjectManager.getPassengerInfoPage().getServiceAnimalToolTipImage().get(count).click();
        }

//-- Step 24
        //WheelChair Tooltip info
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getWheelChairServicesToolTipImage().size(); count ++) {
            pageObjectManager.getPassengerInfoPage().getWheelChairServicesToolTipImage().get(count).click();
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("Validating WheelChair Tooltip info is correct",
                    pageObjectManager.getCommon().getToolTipWindowBodyText().getText(), WHEELCHAIR_INFO);

            pageObjectManager.getCommon().getToolTipWindowMoreInformationLink().click();
            WaitUtil.untilTimeCompleted(1000);

            TestUtil.switchToWindow(getDriver(),1);
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("The user redirected to the correct pdf page",
                    getDriver().getCurrentUrl().contains(WHEELCHAIR_INFO_URL));

            getDriver().close();
            TestUtil.switchToWindow(getDriver(),0);
            WaitUtil.untilTimeCompleted(1000);
            pageObjectManager.getPassengerInfoPage().getWheelChairServicesToolTipImage().get(count).click();
        }

//-- Step 25
        //VOLUNTARY PROVISION OF EMERGENCY SERVICES PROGRAM tooltip right info
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getVoluntaryProvisionTooltipImage().size(); count ++) {
            pageObjectManager.getPassengerInfoPage().getVoluntaryProvisionTooltipImage().get(count).click();
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("Validating VOLUNTARY PROVISION OF EMERGENCY SERVICES PROGRAM Tooltip info is correct",
                    pageObjectManager.getCommon().getToolTipWindowBodyText().getText(), VOLUNTARY_INFO);

            pageObjectManager.getCommon().getToolTipWindowMoreInformationLink().click();
            WaitUtil.untilTimeCompleted(1000);

            TestUtil.switchToWindow(getDriver(),1);
            WaitUtil.untilTimeCompleted(1000);

            ValidationUtil.validateTestStep("The user redirected to the correct pdf page",
                    getDriver().getCurrentUrl(),VOLUNTARY_INFO_URL);

            getDriver().close();
            TestUtil.switchToWindow(getDriver(),0);
            WaitUtil.untilTimeCompleted(1000);
            pageObjectManager.getPassengerInfoPage().getVoluntaryProvisionTooltipImage().get(count).click();
        }

//-- Step 26. Pet in cabin is not a valid step

//-- Step 27:
        //Redress Tooltip Info
        for (int count = 0; count < pageObjectManager.getPassengerInfoPage().getRedressNumberListLinkButton().size(); count ++) {
            if (pageObjectManager.getPassengerInfoPage().getRedressNumberListLinkButton().get(count).isDisplayed()) {
                pageObjectManager.getPassengerInfoPage().getRedressNumberListLinkButton().get(count).click();
                WaitUtil.untilTimeCompleted(1000);

                pageObjectManager.getPassengerInfoPage().getRedressNumberToolTipImage().get(count).click();
                WaitUtil.untilTimeCompleted(1000);

                ValidationUtil.validateTestStep("Validating Redress Tooltip info is correct",
                        pageObjectManager.getCommon().getToolTipWindowBodyText().getText(), REDRESS_INFO);

                pageObjectManager.getCommon().getToolTipWindowMoreInformationLink().click();
                WaitUtil.untilTimeCompleted(1000);

                TestUtil.switchToWindow(getDriver(),1);
                WaitUtil.untilTimeCompleted(1000);

                ValidationUtil.validateTestStep("The user redirected to the correct pdf page",
                        getDriver().getCurrentUrl(),REDRESS_INFO_URL);

                getDriver().close();
                TestUtil.switchToWindow(getDriver(),0);
                WaitUtil.untilTimeCompleted(1000);

            }
        }

//-- Step 28: Duplicated step with the 10

    }

    private List<WebElement> getDisplayedWebElement(List<WebElement> elementList){
        List<WebElement> elements = new ArrayList<>();
        for(WebElement element:elementList){
            if(element.isDisplayed()){
                elements.add(element);
            }
        }
        return elements;
    }
}