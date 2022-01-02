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
//Test Case ID: TC373395
//Description: Task 27776: TC373395 - 006 - CP - Verbiage Car Driver Below 25 - Booking Upsell - Thrifty
//Created By: Anthony Cardona
//Created On: 20-Nov-2019
//Reviewed By: Gabriela
//Reviewed On: 1-20-2020
//**********************************************************************************************
public class TC373395 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug","BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult","Child", "Guest",
                    "NonStop", "MandatoryFields", "CarsUI", "OptionalUI"})
    public void CP_Verbiage_Car_Driver_Below_25_Booking_Upsell_Thrifty(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373395 under GoldFinger Suite on " + platform + " Browser", true);
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
        final String ADULT              = "3";
        final String CHILD              = "3";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

//- Step 1: Book RT DOM-DOM | Any date outside 48h | 3 ADT 3 CHD | No Bags / No Seats / No Bundle / Select "Search Flight"
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
//- Step 2: Fill DOB for all child PAX, select "Continue"
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 3: Select first flights, click "Continue"
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", "NonStop");
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", "NonStop");
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
//- Step 4: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
//Step 5: Input one of the passenger DOB as 21 years old, populate all the required information and click the continue button
        //fill passenger fields
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        //modify second passenger information
        int twentyOneYearsOld = (366 * 21) * -1;
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).clear();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(TestUtil.getStringDateFormat(twentyOneYearsOld, "MM/dd/yyyy"));
        String driverPaxName = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value") + " " +
                pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value");
        //fill contact information
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 6: Scroll down to the bottom of the page and select "CONTINUE WITHOUT ADDING BAGS"
//- Step 7: Select "I DON'T NEED BAGS"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 8: Select "CONTINUE WITHOUT SELECTING SEATS"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 9: Scroll through Car Carousel Thrifty car
        int carCarouselOption = findCarByCarCompany("Thrifty");

//- Step 10: Click on "View" hyperlink on the Car carousel
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getCarPage().getAddCarButton().get(carCarouselOption).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1200);


//Step 11: On the "Vehicle Description" tab left aligned under the section Who's driving Select the 21 passenger as Primary driver.
        //TODO: no valid
//        pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown().click();
//        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(), driverPaxName.toUpperCase());
//        WaitUtil.untilPageLoadComplete(getDriver());

//Step 12: Click the "Policies" tab and Verify  the following verbiage should be displayed at the bottom in bold:
//        pageObjectManager.getCarPage().getCarPopUpPoliciesTab().click();

        //TODO: [IN:26210]	CP: BP: Car Up-sell- Options Page: "Additional underage charges and/or restrictions may apply for Guests under 25 years age." information is missing when the primary driver selected by default is under 25 years old on "Who's driving" window
        String primaryDriverVerbiage = "Additional underage charges and/or restrictions may apply for Drivers under 25 years age.";
        ValidationUtil.validateTestStep("The under age charge and restriction above the primary driver label is displayed", underAgeChargesForUnder25().getText(), primaryDriverVerbiage);

        pageObjectManager.getCarPage().getCarPopUpExitIconButton().click();
        WaitUtil.untilTimeCompleted(1000);

//Step 14: Click the Spanish link on the Header and the verbiage should be displayed in Spanish:
        JSExecuteUtil.scrollDownToElementVisible(getDriver(), pageObjectManager.getHeader().getEnglishSpanishLink());
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getEnglishSpanishLink());
        WaitUtil.untilTimeCompleted(1000);

        //open the same car that was previously opened
        pageObjectManager.getCarPage().getAddCarButton().get(carCarouselOption).click();
        WaitUtil.untilPageLoadComplete(getDriver());
        //select the 21 year old passenger
       /* pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown().click();
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getCarPage().getCarPopUpPrimaryDriverDropDown(), driverPaxName.toUpperCase());
        WaitUtil.untilPageLoadComplete(getDriver());*/

       //TODO: [IN:26210]	CP: BP: Car Up-sell- Options Page: "Additional underage charges and/or restrictions may apply for Guests under 25 years age." information is missing when the primary driver selected by default is under 25 years old on "Who's driving" window
        String spanish_primaryDriverVerbiage = "Cargos adicionales y/o restricciones por minoría de edad podrían aplicar a Conductores menores de 25 años de edad.";
        ValidationUtil.validateTestStep("The under age charge and restriction above the primary driver label is displayed in spanish", underAgeChargesForUnder25().getText(), spanish_primaryDriverVerbiage);
    }

    private int findCarByCarCompany(String carCompany)
    {
        int iterationCounter = 0;

        for (WebElement carDescription :  pageObjectManager.getCarPage().getCarCardCompanyNameText())
        {
            iterationCounter++;

            if (carDescription.isDisplayed())
            {
                if (carDescription.getText().toLowerCase().contains(carCompany.toLowerCase()))
                {
                    return iterationCounter -1;
                }
                else if (iterationCounter % 4 == 0) {
                    try{pageObjectManager.getCarPage().getCarCarouselRightButton().click();}
                    catch (Exception e) {ValidationUtil.validateTestStep("the user cannot find " + carCompany + " and cant click on next right button on options page" , false );}
                }
            }
        }

        ValidationUtil.validateTestStep("the user cannot find " + carCompany + " and cant click on next right button on options page" , false );
        return 0;
    }

    public WebElement underAgeChargesForUnder25() {
//        return getDriver().findElement(By.xpath("//form[@id='carsForm']//div[2]/div[2]"));
        return getDriver().findElement(By.xpath("(//form[@id='carsForm']//div)[3]"));
    }
}
