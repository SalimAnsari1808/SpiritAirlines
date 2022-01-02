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
//TODO: [IN:15876] - CP: BP: Options page:  Cars or Hotels sections are not displayed for an International destination (CUN)
//Test Case ID: TC373941
//Description : Task:27879 | TC373941 - US 21915 - 009 - CP - Verbiage Car Driver Below 25 - Flight + Hotel + Car - Hertz
//Created By  : Anthony Cardona
//Created On  : 20-Nov-2019
//Reviewed By : Gabriela
//Reviewed On : 5-Dec-2019
//**********************************************************************************************
public class TC373941 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug", "BookPath", "FlightHotelCar", "RoundTrip", "DomesticInternational", "Outside21Days", "Adult" ,
                    "Guest", "MandatoryFields" , "PassengerInformationUI","Spanish"})
    public void Verbiage_Car_Driver_Below_25_Flight_Hotel_Car_Hertz(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373941 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel+Car";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "LAS";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "CUN";
        final String DEP_DATE           = "4";
        final String ARR_DATE           = "9";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String DRIVER_AGE 		= "21-24";


        final String UPGRADE_FARE		= "BookIt";

//- Step 1: Create a vacation booking flight + hotel + car | DOM to INT | Any date outside 48h | 1 ADT| Drivers age 21 - 24 | "SEARCH VACATION"
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().selectDriversAge(DRIVER_AGE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
//Step 2: Select "VIEW" in the box of a Hotel you want
//Step 3: Select "ROOMS FROM $$$" and "SELECT ROOM"
//Step 4:Scroll down and select "CONTINUE"
       pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");
//Step 5: Select "BOOK IT" in the Car content box of a the Dollar car you want
        pageMethodManager.getCarPageMethods().selectCarOnCarPage("Hertz","NA");
//Step 6: If the Upgrade & Save pop up is displayed select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);
        WaitUtil.untilPageLoadComplete(getDriver());
//Step 7: Input the passenger DOB as 24 years old and populate all the required information.
        //fill passenger fields
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        //modify second passenger information
        int twentyOneYearsOld = (366 * 24) * -1;
        WaitUtil.untilTimeCompleted(2000);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).clear();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(0).sendKeys(TestUtil.getStringDateFormat(twentyOneYearsOld, "MM/dd/yyyy"));
        String driverPaxName = pageObjectManager.getPassengerInfoPage().getAdultFirstNameListTextBox().get(0).getAttribute("value") + " " +
                pageObjectManager.getPassengerInfoPage().getAdultLastNameListTextBox().get(0).getAttribute("value");
//Step 8: On the Drop down for Primary driver selection, section "Who's driving?" choose the passenger with the age of 24
        pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown().click();
        TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPassengerInfoPage().getPrimaryDriverDropDown(), driverPaxName);
        WaitUtil.untilPageLoadComplete(getDriver());
//Step 9: Above the Drop down in the section Who's driving? verify that the following verbiage is displayed in bold:
        String primaryDriverVerbiage = "Additional underage charges and/or restrictions may apply for Guests under 25 years age.";
        ValidationUtil.validateTestStep("The under age charge and restriction above the primary driver label is displayed", underAgeChargesForUnder25().getText(), primaryDriverVerbiage);
//Step 10: Click the Spanish link on the Header and the verbiage should be displayed in Spanish:
        JSExecuteUtil.scrollDownToElementVisible(getDriver(), pageObjectManager.getHeader().getEnglishSpanishLink());
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHeader().getEnglishSpanishLink());

        WaitUtil.untilTimeCompleted(1200);
        WaitUtil.untilPageLoadComplete(getDriver());
        String spanish_primaryDriverVerbiage = "Cargos adicionales y/o restricciones por minoría de edad podrían aplicar a Invitados menores de 25 años de edad.";
        ValidationUtil.validateTestStep("The under age charge and restriction above the primary driver label is displayed in spanish" , underAgeChargesForUnder25().getText(), spanish_primaryDriverVerbiage);
    }
    public WebElement underAgeChargesForUnder25() {
        return getDriver().findElement(By.xpath("//app-contact-input/preceding-sibling::section[1]//div[1]//div[2]//div"));
    }
}
