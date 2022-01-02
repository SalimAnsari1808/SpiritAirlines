package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

//**********************************************************************************************
//Test Case ID: TC349803
//Description: Task 28166: TC349803 - 005 - CP - Flight Only - Car Page - Validate Tile components
//Created By: Gabriela
//Created On: 3-Dec-2019
//Reviewed By:
//Reviewed On:
//**********************************************************************************************
public class TC349803 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "CarsUI"})
    public void  CP_Flight_Only_Car_Page_Validate_Tile_components(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349803 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "105";
        final String ARR_DATE           = "107";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Page Constant Values:
        final String OPTIONS_PAGE       = "/book/options";

//- Step 1: Access test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Start booking a flight only RT DOM outside 7 days
        /*** Home Page **/
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        /*** Flight Availability Page **/
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
//- Step 3: Continue with the booking until you reach the Options Page.
        /*** Passenger Information Page **/
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();
        /*** Bags Page **/
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
        /*** Seats Page **/
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
        /*** Options Page **/
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("The user redirected to the correct page",
                getDriver().getCurrentUrl(),(OPTIONS_PAGE));

//- Step 4: Under the Car Carousel, click on VIEW ALL CARS
        pageObjectManager.getCarPage().getViewAllCarsButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 5: Validate the first tile is outlined blue and labeled "DEAL OF THE DAY"
        //Invalid Step

//- Step 6:Validate each car tile contains the Car Supplier Name such as:
        boolean carAgencyFlag = true;
        for (int i = 0; i < pageObjectManager.getCarPage().getRentalAgencyFilterOptionListButton().size(); i ++)
        {
            ValidationUtil.validateTestStep(" Validating car supplier information displayed",
                    pageObjectManager.getCarPage().getRentalAgencyFilterOptionListButton().get(i).getText().contains("Thrifty")||pageObjectManager.getCarPage().getRentalAgencyFilterOptionListButton().get(i).getText().contains("Dollar")||pageObjectManager.getCarPage().getRentalAgencyFilterOptionListButton().get(i).getText().contains("Hertz"));
        }

//- Step 7: Validate each car tile contains the "Car Make" and "Model" and the verbiage "or similar"
        for (int i = 0; i < pageObjectManager.getCarPage().getCarsPageCarsPanel().size(); i ++)
        {
            ValidationUtil.validateTestStep("Validating  'Car Make' and 'Model' and the verbiage 'or similar' displayed",
                    pageObjectManager.getCarPage().getCarsPageCarModelText().get(i).getText().contains(" or similar"));

//- Step 8: Validate each car tile contains the following icons: Note: Min of 1 icon will be displayed, max 4.

            //Seat count
            ValidationUtil.validateTestStep("Validating Seats Count is displayed in each tile", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardNumberOfSeatsText().get(i)));
            //Bag count
            ValidationUtil.validateTestStep("Validating Bags Count is displayed in each tile", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardNumberOfBagsText().get(i)));
            //Transmission type (= Automatic vs. Manual)
            ValidationUtil.validateTestStep("Validating Transmission Type is displayed in each tile", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardTransmissionTypeText().get(i)));
            //Mileage
            ValidationUtil.validateTestStep("Validating Mileage is displayed in each tile", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsCardMileageLimitText().get(i)));

//- Step 9: Validate each tile has a "More" link with a chevron pointing down, then click on it.
            ValidationUtil.validateTestStep("Validating More Link is displayed in each tile", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageMoreInfoLink().get(i)));

//- Step 10: Validate each car tile contains 2 different pricing:
            //Car price Per day, right aligned under the icons
            ValidationUtil.validateTestStep("The car Total Price per day text is displayed" , TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageRentalPriceText().get(i)));
            //Car price Total, center aligned, almost at the end of the tile
            ValidationUtil.validateTestStep("The car Total Price is displayed" , TestUtil.verifyElementDisplayed(carTotalPrice().get(i)));

//- Step 11: If the Total booking is $200  of more, validate each car tile contains the Uplift option is displaying
            //$ amount per month
            try {
                ValidationUtil.validateTestStep("ValidatingUplift is displayed in each tile", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarCardUpliftPricingText().get(i)));
                //Otherwise, ignore this step
            }catch (Exception e){}

//- Step 12: Validate each car tile has an ADD CAR button.
            ValidationUtil.validateTestStep("Validating More Link is displayed in each tile", TestUtil.verifyElementDisplayed(pageObjectManager.getCarPage().getCarsPageBookButton().get(i)));

        }
    }
    public List<WebElement> carTotalPrice()
    {
        return getDriver().findElements(By.xpath("(//app-car-list-item/div/div[3]//p[2])"));
    }
}