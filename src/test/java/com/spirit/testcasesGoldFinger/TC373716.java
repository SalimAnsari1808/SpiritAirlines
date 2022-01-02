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

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373716
//Description: Task 27145: TC373716 - US 17540 - 001 - CP - Hotel Upsell carousel - Validate the Carousel for a booking with an Infant on Lap
//Created By: Gabriela
//Created On: 01-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 18-Nov-2019
//**********************************************************************************************

public class TC373716 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult","InfantLap", "Guest", "NonStop", "BookIt",
                    "NoBags", "NoSeats", "FlightHotel", "CheckInOptions", "OptionalUI", "HotelsUI", "Visa"})
    public void CP_Hotel_Upsell_carousel_Validate_the_Carousel_for_a_booking_with_an_Infant_on_Lap(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373716 under GoldFinger Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "RoundTrip";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "MCO";
        final String DEP_DATE           = "115";
        final String ARR_DATE           = "116";
        final String ADULT              = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "1";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String OPTIONS_URL        = "/book/options";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- STep 1: Access to the GoldFinger Test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Create a regular booking for 1 Adt and 1 INF on LAP, DOM to DOM, RT, outside 48 hrs, and click on Search Flight button.
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Fill out infant child of birth and choose lap child and click continue
        pageMethodManager.getHomePageMethods().fillYoungTravellerPopup();

//- Step 4: Choose flights for POO and POD
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);

//- Step 5: Click Continue at the bottom of the page.
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 6: If the pop up for Upgrade & Save appears select Book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

//- Step 7: Fill out passenger info and click "Continue"
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 8 & 9: click "continue without bags" at the bottom of the pag & Click "I Don't Need Bags"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 10: Click "Continue without seats"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 11: Validate that the Hotel carousel is displayed on the Options page.
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 12: Validate that the Hotel carousel is displaying 4 tiles and is having an Arrow pointing at the right and left on each side.
        List<WebElement> hotelTileList = getDriver().findElements(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]"));
        for (int i = 0; i < 4; i ++)
        {
            ValidationUtil.validateTestStep("Validating 4 tiles are present on Hotel Carousel", TestUtil.verifyElementDisplayed(hotelTileList.get(i)));
        }

//- Step 13: Click the Right Arrow once.
        List<String> hotelCarousel = new ArrayList<>();
        for (int i = 0; i < hotelTileList.size(); i ++)
        {
            hotelCarousel.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }
//        System.out.println("hotelCarousel: " + hotelCarousel);

        pageObjectManager.getHotelPage().getHotelCarouselRightButton().click();
        WaitUtil.untilTimeCompleted(3000);

        List<String> hotelCarouselNew = new ArrayList<>();
        for (int i =0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselNew.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }
//        System.out.println("hotelCarouselNew: " + hotelCarouselNew);

        for (int i = 0; i <hotelCarousel.size(); i ++)
        {
            for (int e = 0; e < hotelCarouselNew.size(); e ++) {
                ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the right arrow",
                        !hotelCarousel.get(i).equals(hotelCarouselNew.get(e)));
            }
        }

//- Step 14: Click the Right Arrow per second time
        pageObjectManager.getHotelPage().getHotelCarouselRightButton().click();
        WaitUtil.untilTimeCompleted(3000);

        List<String> hotelCarouselNew1 = new ArrayList<>();
        for (int i =0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselNew1.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }
//        System.out.println("hotelCarouselNew1: " + hotelCarouselNew1);

        for (int i = 0; i <hotelCarouselNew.size(); i ++)
        {
            for (int e = 0; e < hotelCarouselNew1.size(); e ++) {
                ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the right arrow",
                        !hotelCarouselNew.get(i).equals(hotelCarouselNew1.get(e)));
            }
        }
//- Step 15: Click the Right Arrow for third time
        pageObjectManager.getHotelPage().getHotelCarouselRightButton().click();
        WaitUtil.untilTimeCompleted(3000);

        List<String> hotelCarouselNew2 = new ArrayList<>();
        for (int i =0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselNew2.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }
//        System.out.println("hotelCarouselNew2: " + hotelCarouselNew2);

        for (int i = 0; i <hotelCarouselNew1.size(); i ++)
        {
            for (int e = 0; e < hotelCarouselNew2.size(); e ++) {
                ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the right arrow",
                        !hotelCarouselNew1.get(i).equals(hotelCarouselNew2.get(e)));
            }
        }

//- Step 16: Click over the Arrow pointing Right again and validate that the arrow is gray and unresponsive to the user action.
        ValidationUtil.validateTestStep("Validating right arrow is disabled on gray color",
                pageObjectManager.getHotelPage().getHotelCarouselRightButton().getCssValue("-webkit-tap-highlight-color"),"rgba(0, 0, 0, 0)");

//- Step 17: Click the Left Arrow once.
        List<String> hotelCarouselLeft = new ArrayList<>();
        for (int i = 0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselLeft.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }
//        System.out.println("hotelCarouselLeft: " + hotelCarouselLeft);

        pageObjectManager.getHotelPage().getHotelCarouselLeftButton().click();
        WaitUtil.untilTimeCompleted(3000);

        List<String> hotelCarouselLeftNew = new ArrayList<>();
        for (int i =0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselLeftNew.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }
//        System.out.println("hotelCarouselLeftNew: " + hotelCarouselLeftNew);

        for (int i = 0; i <hotelCarouselLeft.size(); i ++)
        {
            for (int e = 0; e < hotelCarouselNew.size(); e ++) {
                ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the right arrow",
                        !hotelCarouselLeft.get(i).equals(hotelCarouselLeftNew.get(e)));
            }
        }

//- Step 18: Click the Left Arrow per second time
        pageObjectManager.getHotelPage().getHotelCarouselLeftButton().click();
        WaitUtil.untilTimeCompleted(3000);

        List<String> hotelCarouselLeftNew1 = new ArrayList<>();
        for (int i =0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselLeftNew1.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }
//        System.out.println("hotelCarouselLeftNew1: " + hotelCarouselLeftNew1);

        for (int i = 0; i <hotelCarouselLeftNew.size(); i ++)
        {
            for (int e = 0; e < hotelCarouselLeftNew1.size(); e ++) {
                ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the right arrow",
                        !hotelCarouselLeftNew.get(i).equals(hotelCarouselLeftNew1.get(e)));
            }
        }

//- Step 19: Click the Leftt Arrow for third time
        pageObjectManager.getHotelPage().getHotelCarouselLeftButton().click();
        WaitUtil.untilTimeCompleted(3000);

        List<String> hotelCarouselLeftNew2 = new ArrayList<>();
        for (int i =0; i < hotelTileList.size(); i ++)
        {
            hotelCarouselLeftNew2.add(getDriver().findElement(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']")).getText());
        }
//        System.out.println("hotelCarouselLeftNew2: " + hotelCarouselLeftNew2);

        for (int i = 0; i <hotelCarouselLeftNew1.size(); i ++)
        {
            for (int e = 0; e < hotelCarouselLeftNew2.size(); e ++) {
                ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the right arrow",
                        !hotelCarouselLeftNew1.get(i).equals(hotelCarouselLeftNew2.get(e)));
            }
        }

//- Step 20: Click over the Arrow pointing Left again and validate that the arrow is gray and unresponsive to the user action.
        ValidationUtil.validateTestStep("Validating right arrow is disabled on gray color",
                pageObjectManager.getHotelPage().getHotelCarouselLeftButton().getCssValue("-webkit-tap-highlight-color"),"rgba(0, 0, 0, 0)");

//- Step 21: Select View inside of one Hotel tile
        //click on any hotel card
        JSExecuteUtil.clickOnElement(getDriver(), getDriver().findElements(By.xpath("//app-hotel//app-ancillary-item[contains(@class,'options-table')]//p[@class='clamp-text-box']//a")).get(0));
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating hotel information pops up in a new window",
                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("//app-package-detail-select-modal"))));

//- Step 22: Verify that the following information is displayed:
        //Hotel name and address
        ValidationUtil.validateTestStep("Verifying hotel name information displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomCategoryHotelNameText()));

        ValidationUtil.validateTestStep("Verifying hotel address information displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomCategoryHotelAddressText()));

        //Hotel image
        ValidationUtil.validateTestStep("Verifying hotel Image displayed",
                TestUtil.verifyElementDisplayed(getDriver().findElements(By.xpath("//app-package-detail-select-modal//img[contains(@class,'hotel-image')]"))));
//        ValidationUtil.validateTestStep("Verifying hotel Image displayed",
//                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCardImageImage()));

        //View map hyperlink
        ValidationUtil.validateTestStep("Verifying hotel map link displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomCategoryViewMapText()));

        //ROOMS FROM $ ##.##
        ValidationUtil.validateTestStep("Verifying hotel Rooms From button is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton()));

        //Additional info modal (all fields/tabs) (Overview, Policies, Accommodations, Dining, Activities, Photos, Map)
        ValidationUtil.validateTestStep("Verifying hotel Rooms link displayed",
                TestUtil.verifyElementDisplayed( pageObjectManager.getHotelPage().getHotelPopUpRoomsTab()));

        ValidationUtil.validateTestStep("Verifying hotel Overview link displayed",
                TestUtil.verifyElementDisplayed( pageObjectManager.getHotelPage().getHotelPopUpOverviewTab()));

        ValidationUtil.validateTestStep("Verifying hotel Policies link displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpPoliciesTab()));

        ValidationUtil.validateTestStep("Verifying hotel Amenities link displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpAmenitiesTab()));

        ValidationUtil.validateTestStep("Verifying hotel Dining Link displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpDiningTab()));

//        ValidationUtil.validateTestStep("Verifying hotel Activities&Entertainment link displayed",
//                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpActivitiesTab()));

        ValidationUtil.validateTestStep("Verifying hotel Photos link displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpPhotosTab()));

        ValidationUtil.validateTestStep("Verifying hotel map link displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelPopUpMapTab()));

//- Step 23: Open HBG website and use to validate Hotel information is correct shown with the same city pair and date chossen for the booking.
        //This step is not valid for automation

////- Step 24: Select the button Rooms From $##.## and Continue with Purchase
        getDriver().findElement(By.xpath("//app-hotel-detail-tabset//a[@id='roomSelect']//button")).click();
//        pageObjectManager.getHotelPage().getRoomsFromButton().click();
        WaitUtil.untilTimeCompleted(3000);

//- Step 25: Click the Select Room button on the first available Room
        pageObjectManager.getHotelPage().getHotelWindowSelectRoomButton().get(0).click();
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 26: Verify the user remains on the Options page and the hotel is now selected
        ValidationUtil.validateTestStep("Verify Options Page URL",
                getDriver().getCurrentUrl(),(OPTIONS_URL));

        ValidationUtil.validateTestStep("Verifying hotel selected is displayed",
                TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("//img[@alt='Selected Hotel']"))));

//- Step 27: Validate that the Flight Flex Option is available after the user selects a Hotel.
        ValidationUtil.validateTestStep("Validating Flight Flex options is available",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardNotAvailableText()));

//- Step 28: Click continue with purchase
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 29: Use a valid credit card from roles and credentials and complete the booking
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
        pageMethodManager.getPaymentPageMethods().selectTravelGuardPopup(TRAVEL_GUARD);

//- Step 30: Verify that in addition to the flight PNR a Packaging confirmation will display: Hotel Confirmation
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
        pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

//- Step 31: Retrive the HBG Confirmation code on the HBG website https://www.hotelbeds.com observe the price charged for the Hotel
        pageMethodManager.getCommonPageMethods().cancelHBGHotelBooking();

//- Step 32: Login to skyspeed, retrieve the booking and locate the DPF
        //This Step is not valid for automation

//- Step 33: Verify Package Confirmation Code in Comments section
        //This Step is not valid for automation
    }
}