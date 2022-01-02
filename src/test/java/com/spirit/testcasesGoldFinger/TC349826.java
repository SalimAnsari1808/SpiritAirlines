package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//IN25106 : GoldFinger R1: CP: BP: F+H: PI: Insufficient age popup does not display on Pax Info page when one guest is under 18 years old for a hotel room
//**********************************************************************************************
//Test CaseID: TC349826
//Title      : Task: 27813 |TC349826: 008 - CP - Flight + Hotel - Availability Page - NEG Validating One ADT is required per room
//Created By : Anthony Cardona
//Created On : 07-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On:10-Dec-2019
//**********************************************************************************************
public class TC349826 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"ActiveBug","BookPath", "RoundTrip", "DomesticDomestic", "FlightHotelCar", "Outside21Days", "Adult", "Guest",
                    "PassengerInformationUI"})
    public void CP_Flight_Hotel_Availability_Page_NEG_Validating_One_ADT_is_required_per_room(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC349826 under GoldFinger Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Vacation";
        final String TRIP_TYPE          = "Flight+Hotel";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LAS";
        final String DEP_DATE           = "132";
        final String ARR_DATE           = "133";
        String ADULT                    = "1";
        String CHILD                    = "1";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";
        final String ROOMS_VALUE        = "2 Rooms";
        final String UPGRADE_VALUE      = "BookIt";

        //declare variable used in navigation
        int child17YO               = (365 * 18) * -1;


        //Flight Availability Page Constant Values
//- Step 1: Access test environment
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

//- Step 2: Start booking a Vacation [Flight + Hote] 1 ADT, 1 CHD (17 yrs old)
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);

//       validate that there is only a 1 room option
        Select RoomOptionDropDown = new Select(pageObjectManager.getHomePage().getRoomsDropDown());
        ValidationUtil.validateTestStep("There is only 1 room option on the rooms drop down" , RoomOptionDropDown.getOptions().size() == 1
                && RoomOptionDropDown.getFirstSelectedOption().getText().contains("1 Room"));

//- Step 3 Reuse the search widget and now enter 2 ADT, 2 Rooms
        ADULT = "2";
        CHILD = "0";
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);

        pageMethodManager.getHomePageMethods().selectHotelRoom(ROOMS_VALUE);
        pageMethodManager.getHomePageMethods().clickSearchButton();
        WaitUtil.untilPageLoadComplete(getDriver());

        String Flight_Car_Page = "/book/flights-hotels";

        //verify user reached on Passenger information page
        ValidationUtil.validateTestStep("Verify user reached Flight + Car Page",
                getDriver().getCurrentUrl(),Flight_Car_Page);


//- Step 4: Select a Hotel and continue to the Passenger Information page
        pageMethodManager.getHotelPageMethods().selectHotelRoomHotelPage("NA","NA");
        WaitUtil.untilPageLoadComplete(getDriver());

        pageObjectManager.getCarPage().getCarsPageContinueWithoutCarButton().click();
        WaitUtil.untilPageLoadComplete(getDriver());


//- Step 5: Select "BOOK IT"
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        String PassengerInformation_Page = "/book/passenger";
        //verify user reached on Passenger information page
        ValidationUtil.validateTestStep("Verify user reached Passenger Information Page",
                getDriver().getCurrentUrl(),PassengerInformation_Page);
//Step 5: Enter passengers information. Make sure one of the passengers is 17 yrs old
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();

        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(1).clear();
        WaitUtil.untilTimeCompleted(1200);
        pageObjectManager.getPassengerInfoPage().getAdultDOBListTextBox().get(1).sendKeys(TestUtil.getStringDateFormat((child17YO), "MM/dd/yyyy"));
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Insufficient age popup displays and the user is not able to continue. One adult is required per room and one of the passengers is less than 18 yrs old
        ValidationUtil.validateTestStep("Insufficient age popup will not displays" ,
                !TestUtil.verifyElementDisplayed(getDriver(), By.xpath(pageObjectManager.getPassengerInfoPage().xpath_InsufficientAgePopUpHeaderText)));
    }
}
