package com.spirit.testcasesGoldFinger;

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

//**********************************************************************************************
//TODO: [IN:26210]	CP: BP: Car Up-sell- Options Page: "Additional underage charges and/or restrictions may apply for
// Guests under 25 years age." information is missing when the primary driver selected by default is under 25 years old on "Who's driving" window

//Test Case ID: TC373396
//Description: Task 27948: TC373396 - 004 - CP - Verbiage Car Driver Below 25 - Booking Upsell - Hertz
//Created By: Anthony Cardona
//Created On: 20-Nov-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************

public class TC373396 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug","BookPath","RoundTrip","DomesticDomestic","Outside21Days","Adult","Guest","NonStop",
                    "MandatoryFields","Cars","CarsUI"})
    public void CP_Verbiage_Car_Driver_Below_25_Booking_Upsell_Hertz(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373396 under GoldFinger Suite on " + platform + " Browser", true);
        }
        // Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "135";
        final String ARR_DATE           = "139";
        final String ADULT              = "2";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

//- Step 1: Access GoldFinger test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Book RT DOM-DOM | Any date outside 48h | 2 ADT | No Bags / No Seats / No Bundle / Select "Search Flight"
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Select first flights, click "Continue"
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", "NonStop");
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", "NonStop");

        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 4: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//Step 5: Input one of the passenger DOB as 21 years old, populate all the required information and click the continue button
        //fill passenger fields
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        int twentyOneYearsOld = (366 * 21) * -1;
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).clear();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(TestUtil.getStringDateFormat(twentyOneYearsOld, "MM/dd/yyyy"));

        String primaryPaxName = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value") + " " +
                pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value");

        //fill contact information
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();


//- Step 6: Scroll down to the bottom of the page and select "CONTINUE WITHOUT ADDING BAGS"
//- Step 7: Select "I DON'T NEED BAGS"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 8:
//Select "CONTINUE WITHOUT SELECTING SEATS"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 9: Scroll through Car Carousel or click VIEW ALL CARS to open Cars page to locate a Hertz car
        pageObjectManager.getCarPage().getViewAllCarsButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

        //Scroll down to a hertz car
        int carsIterated = 0;
        for (WebElement carCompany : pageObjectManager.getCarPage().getCarsPageCarTypeText()) {
            if (carCompany.getText().toLowerCase().contains("hertz")) {
                JSExecuteUtil.scrollDownToElementVisible(getDriver(), carCompany);

                break;
            }
            carsIterated++;
        }

        pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(carsIterated).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The \"More\" button is clicked and Vehicle description tile is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionTab()));

        pageObjectManager.getCarPage().getAddCarButton().get(0).click();
//- Step 10:
//If you found the car on the Carousel, click on "ADD CAR" button.
//If you found the car on the Cars page, click the "More" link in Car tile
        pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown().click();

//Step 11: Under the section Who's driving, select the 21 passenger as Primary driver.
        System.out.println(primaryPaxName);
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(), primaryPaxName.toUpperCase());
        WaitUtil.untilPageLoadComplete(getDriver());

//Step 12: Verify the following verbiage displays above Primary driver in bold: Additional underage charges and/or restrictions may apply for Guests under 25 years age.
        //TODO: [IN:26210]	CP: BP: Car Up-sell- Options Page: "Additional underage charges and/or restrictions may apply for Guests under 25 years age." information is missing when the primary driver selected by default is under 25 years old on "Who's driving" window
        String primaryDriverVerbiage = "Additional underage charges and/or restrictions may apply for Drivers under 25 years age.";
        ValidationUtil.validateTestStep("The under age charge and restriction above the primary driver label is displayed" , underAgeChargesForUnder25().getText(), primaryDriverVerbiage);

//Step 13: Click the Spanish link on the Header and the verbiage should be displayed in Spanish:
        JSExecuteUtil.scrollDownToElementVisible(getDriver() , pageObjectManager.getHeader().getEnglishSpanishLink());
        JSExecuteUtil.clickOnElement(getDriver() , pageObjectManager.getHeader().getEnglishSpanishLink());

        String spanish_primaryDriverVerbiage = "Cargos adicionales y/o restricciones por minoría de edad podrían aplicar a Conductores menores de 25 años de edad.";

        //TODO: [IN:26210]	CP: BP: Car Up-sell- Options Page: "Additional underage charges and/or restrictions may apply for Guests under 25 years age." information is missing when the primary driver selected by default is under 25 years old on "Who's driving" window
        ValidationUtil.validateTestStep("The under age charge and restriction above the primary driver label is displayed in spanish" , underAgeChargesForUnder25().getText(), spanish_primaryDriverVerbiage);

    }

    public WebElement underAgeChargesForUnder25()
    {
        return getDriver().findElement(By.xpath("//app-branded-modal//form[@id='carsForm']//div[2]"));
    }
}