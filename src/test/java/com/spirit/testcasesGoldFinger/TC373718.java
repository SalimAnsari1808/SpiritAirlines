package com.spirit.testcasesGoldFinger;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.*;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC373718
//Description: Task 27147: TC373718 - US 17540 - 006 - CP - Hotel Upsell carousel - for a Spanish booking
//Created By: Gabriela
//Created On: 05-Nov-2019
//Reviewed By: Kartik Chauhan
//Reviewed On: 18-Nov-2019
//**********************************************************************************************

public class TC373718 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"BookPath", "RoundTrip", "DomesticDomestic", "Outside21Days", "Adult", "Guest", "NonStop", "BookIt",
            "NoBags", "NoSeats","Spanish", "FlightHotel", "CheckInOptions", "OptionalUI", "HotelsUI","Visa"})
    public void CP_Hotel_Upsell_carousel_for_a_Spanish_booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC373718 under GoldFinger Suite on " + platform + " Browser", true);
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
        final String ARR_DATE           = "106";
        final String ADULT              = "8";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "NonStop";
        final String ARR_Flight         = "NonStop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";

        //Options Constant Values
        final String HOTEL_SPANISH_TITLE= "Hoteles por menos";
        final String HOTEL_SPANISH_LINK = "Ver todos los Hoteles";
        final String HOTEL_SPANISH_BUTTON = "Ver Habitacion";

        final String OPTIONS_VALUE		= "CheckInOption:MobileFree";

        //Payment Page Constant values
        final String TRAVEL_GUARD 		= "NotRequired";
        final String CARD_DETAIL 		= "VisaCard";

//- Step 1: Access to the GoldFinger Test environment.
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();

//- Step 2: Create a regular booking for 8 Adt , DOM to DOM, RT, outside 48 hrs, and click on Search Flight button.
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

//- Step 3: Choose flights for POO and POD
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_Flight);

//- Step 4: Click Continue at the bottom of the page.
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);

//- Step 5: If the pop up for Upgrade & Save appears select Book it
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);
//- Step 6: Fill out passenger info and click "Continue"
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

//- Step 7 7 8: click "continue without bags" at the bottom of the pag & Click "I Don't Need Bags"
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

//- Step 9: Click "Continue without seats"
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

//- Step 10: Validate that the Hotel carousel is displayed on the Options page.
        WaitUtil.untilPageLoadComplete(getDriver());

//- Step 11: Validate that the Hotel carousel is displaying 4 tiles and is having an Arrow pointing at the right and left on each side.
        int tile = 0;
        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPanel().size(); i ++)
        {
            if ( pageObjectManager.getHotelPage().getHotelPanel().get(i).isDisplayed())
            {
                tile++;
            }
        }

        ValidationUtil.validateTestStep("Validating arrow pointing to the left is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselLeftButton()));

        ValidationUtil.validateTestStep("Validating arrow pointing to the right is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelCarouselRightButton()));

//- Step 12: Click the Right Arrow once. // running this step only if we are receiving more than 4 hotels offered on options page for 8 passenger in only 1 room
        List<String> hotelCarousel = new ArrayList<>();
        if (tile > 4) {
            for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPanel().size(); i++) {
                hotelCarousel.add(pageObjectManager.getHotelPage().getHotelCardNameText().get(i).getText());
            }
            System.out.println("hotelCarousel: " + hotelCarousel);

            pageObjectManager.getHotelPage().getHotelCarouselRightButton().click();
            WaitUtil.untilTimeCompleted(3000);

            List<String> hotelCarouselNew = new ArrayList<>();
            for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPanel().size(); i++) {
                hotelCarouselNew.add(pageObjectManager.getHotelPage().getHotelCardNameText().get(i).getText());
            }
        System.out.println("hotelCarouselNew: " + hotelCarouselNew);

            for (int i = 0; i < hotelCarousel.size(); i++) {
                for (int e = 0; e < hotelCarouselNew.size(); e++) {
                    ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the right arrow",
                            !hotelCarousel.get(i).equals(hotelCarouselNew.get(e)));
                }
            }

//- Step 13: Click the Right Arrow per second time
            pageObjectManager.getHotelPage().getHotelCarouselRightButton().click();
            WaitUtil.untilTimeCompleted(3000);

            List<String> hotelCarouselNew1 = new ArrayList<>();
            for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPanel().size(); i++) {
                hotelCarouselNew1.add(pageObjectManager.getHotelPage().getHotelCardNameText().get(i).getText());
            }
        System.out.println("hotelCarouselNew1: " + hotelCarouselNew1);

            for (int i = 0; i < hotelCarouselNew.size(); i++) {
                for (int e = 0; e < hotelCarouselNew1.size(); e++) {
                    ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the right arrow",
                            !hotelCarouselNew.get(i).equals(hotelCarouselNew1.get(e)));
                }
            }
//- Step 14: Click the Right Arrow for third time
            pageObjectManager.getHotelPage().getHotelCarouselRightButton().click();
            WaitUtil.untilTimeCompleted(3000);

            List<String> hotelCarouselNew2 = new ArrayList<>();
            for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPanel().size(); i++) {
                hotelCarouselNew2.add(pageObjectManager.getHotelPage().getHotelCardNameText().get(i).getText());
            }
        System.out.println("hotelCarouselNew2: " + hotelCarouselNew2);

            for (int i = 0; i < hotelCarouselNew1.size(); i++) {
                for (int e = 0; e < hotelCarouselNew2.size(); e++) {
                    ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the right arrow",
                            !hotelCarouselNew1.get(i).equals(hotelCarouselNew2.get(e)));
                }
            }

//- Step 15: Click over the Arrow pointing Right again and validate that the arrow is gray and unresponsive to the user action.
            ValidationUtil.validateTestStep("Validating right arrow is disabled on gray color",
                    pageObjectManager.getHotelPage().getHotelCarouselRightButton().getCssValue("-webkit-tap-highlight-color"), "rgba(0, 0, 0, 0)");

//- Step 16: Click the Left Arrow once.
            List<String> hotelCarouselLeft = new ArrayList<>();
            for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPanel().size(); i++) {
                hotelCarouselLeft.add(pageObjectManager.getHotelPage().getHotelCardNameText().get(i).getText());
            }
        System.out.println("hotelCarouselLeft: " + hotelCarouselLeft);

            pageObjectManager.getHotelPage().getHotelCarouselLeftButton().click();
            WaitUtil.untilTimeCompleted(3000);

            List<String> hotelCarouselLeftNew = new ArrayList<>();
            for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPanel().size(); i++) {
                hotelCarouselLeftNew.add(pageObjectManager.getHotelPage().getHotelCardNameText().get(i).getText());
            }
        System.out.println("hotelCarouselLeftNew: " + hotelCarouselLeftNew);

            for (int i = 0; i < hotelCarouselLeft.size(); i++) {
                for (int e = 0; e < hotelCarouselNew.size(); e++) {
                    ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the right arrow",
                            !hotelCarouselLeft.get(i).equals(hotelCarouselLeftNew.get(e)));
                }
            }

//- Step 17: Click the Left Arrow per second time
            pageObjectManager.getHotelPage().getHotelCarouselLeftButton().click();
            WaitUtil.untilTimeCompleted(3000);

            List<String> hotelCarouselLeftNew1 = new ArrayList<>();
            for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPanel().size(); i++) {
                hotelCarouselLeftNew1.add(pageObjectManager.getHotelPage().getHotelCardNameText().get(i).getText());
            }
        System.out.println("hotelCarouselLeftNew1: " + hotelCarouselLeftNew1);

            for (int i = 0; i < hotelCarouselLeftNew.size(); i++) {
                for (int e = 0; e < hotelCarouselLeftNew1.size(); e++) {
                    ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the right arrow",
                            !hotelCarouselLeftNew.get(i).equals(hotelCarouselLeftNew1.get(e)));
                }
            }

//- Step 18: Click the Leftt Arrow for third time
            pageObjectManager.getHotelPage().getHotelCarouselLeftButton().click();
            WaitUtil.untilTimeCompleted(3000);

            List<String> hotelCarouselLeftNew2 = new ArrayList<>();
            for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPanel().size(); i++) {
                hotelCarouselLeftNew2.add(pageObjectManager.getHotelPage().getHotelCardNameText().get(i).getText());
            }
        System.out.println("hotelCarouselLeftNew2: " + hotelCarouselLeftNew2);

            for (int i = 0; i < hotelCarouselLeftNew1.size(); i++) {
                for (int e = 0; e < hotelCarouselLeftNew2.size(); e++) {
                    ValidationUtil.validateTestStep("Validating 4 new tiles are displayed after click on the right arrow",
                            !hotelCarouselLeftNew1.get(i).equals(hotelCarouselLeftNew2.get(e)));
                }
            }
        }
//- Step 19: Click over the Arrow pointing Left again and validate that the arrow is gray and unresponsive to the user action.
        ValidationUtil.validateTestStep("Validating right arrow is disabled on gray color",
                pageObjectManager.getHotelPage().getHotelCarouselLeftButton().getCssValue("-webkit-tap-highlight-color"),"rgba(0, 0, 0, 0)");

//- Step 21: Select the Español hyperlink on the header.
        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHomePage().getSelectedLanguage().get(0));
        WaitUtil.untilPageLoadComplete(getDriver());

        ValidationUtil.validateTestStep("Validating Hotel Section's title is in Spanish",
                pageObjectManager.getHotelPage().getHotelCarouselTitleText().getText(), HOTEL_SPANISH_TITLE);

        for (int i = 0; i < pageObjectManager.getHotelPage().getHotelPanel().size(); i++) {
            ValidationUtil.validateTestStep("Validating Ver Habitacion button is in Spanish",
                    pageObjectManager.getHotelPage().getHotelCardViewRoomButton().get(i).getText(), HOTEL_SPANISH_BUTTON);
        }

        ValidationUtil.validateTestStep("Validating Ver Todos Los Hoteles link is in Spanish",
                pageObjectManager.getHotelPage().getViewAllHotelsButton().getText(), HOTEL_SPANISH_LINK);

//- Step 20: Select View inside of one Hotel tile
        //click on any hotel card
        pageObjectManager.getHotelPage().getHotelCardViewRoomButton().get(0).click();

        ValidationUtil.validateTestStep("Validating hotel information pops up in a new window",
                TestUtil.verifyElementDisplayed(getDriver(),By.xpath("//app-package-detail-select-modal")));

//- Step 22: Verify that the following information is displayed:
        //Hotel name and address
        ValidationUtil.validateTestStep("Verifying hotel name information displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomCategoryHotelNameText()));

        ValidationUtil.validateTestStep("Verifying hotel address information displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelWindowRoomCategoryHotelAddressText()));

        //Hotel image
        ValidationUtil.validateTestStep("Verifying hotel Image displayed",
                TestUtil.verifyElementDisplayed(getDriver().findElements(By.xpath("//app-package-detail-select-modal//img[contains(@class,'hotel-image')]"))));

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

//- Step 24: Select the button Rooms From $##.##
        pageObjectManager.getHotelPage().getHotelPopUpSelectRoomsFromButton().click();
        WaitUtil.untilTimeCompleted(1200);

// - Step 25: Click the Select Room button on the first available Room
       pageObjectManager.getHotelPage().getHotelPopUpSelectRoomButton().get(0).click();
       WaitUtil.untilPageLoadComplete(getDriver());

       WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Verifying hotel selected is displayed",
                TestUtil.verifyElementDisplayed(pageObjectManager.getHotelPage().getHotelSelectedPanel()));


//- Step 26: On the options page book a hotel and select check-in online.
        pageMethodManager.getOptionsPageMethods().selectOptions(OPTIONS_VALUE);

//- Step 27: Validate that the Flight Flex Option is available after the user selects a Hotel.
        ValidationUtil.validateTestStep("Validating Flight Flex options is available",
                !TestUtil.verifyElementDisplayed(pageObjectManager.getOptionsPage().getFlightFlexCardNotAvailableText()));

//- Step 28: Click continue with purchase
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

//- Step 29: Use a valid credit card from roles and credentials and complete the booking
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_DETAIL);
        //TODO: Not completing the payment due no Universal or MGM hotels rooms available for 8 pax

    }
}
/***************** Hotel Page XPATH *****************************/
//public final String xpath_HotelCarouselNameLink = "//app-hotel//div[contains(@class,'hotel-name-container')]";
//    @FindBy(xpath = xpath_HotelCarouselNameLink)
//    private List<WebElement> lnk_HotelCarouselName;
//public List<WebElement> getHotelCarouselNameLink(){return lnk_HotelCarouselName;}

//public final String xpath_HotelCarouselViewRoomsButton = "//app-hotel//button[contains(text(),'View Rooms') or contains(text(),'Ver Habitacion')]";
//    @FindBy(xpath = xpath_HotelCarouselViewRoomsButton)
//    private List<WebElement> btn_HotelCarouselViewRooms;
//public List<WebElement> getHotelCarouselViewRoomsButton(){return btn_HotelCarouselViewRooms;}

//public final String xpath_HotelWindowRoomCategoryImage = "//app-package-detail-select-modal//img[contains(@class,'hotel-image')]";
//    @FindBy (xpath=xpath_HotelWindowRoomCategoryImage)
//    private WebElement img_HotelWindowRoomCategory;
//public WebElement getHotelWindowRoomCategoryImage() {return img_HotelWindowRoomCategory;}


/************************ HOtel Page XPATH Modified ************************/
//public final String xpath_RoomsFromButton = "//button[contains(text(),'Rooms from') or contains(text(),'Habitaciones desde')]";
//    @FindBy(xpath = xpath_RoomsFromButton)
//    private WebElement btn_RoomsFrom;
//public WebElement getRoomsFromButton() {return btn_RoomsFrom;}

//public final String xpath_HotelWindowSelectRoomButton = "//app-package-detail-select-modal//button[contains(text(),'Select room') or contains(text(),'Seleccione la habitación')]";
//    @FindBy (xpath=xpath_HotelWindowSelectRoomButton)
//    private List<WebElement> btn_HotelWindowSelectRoom;
//public List<WebElement> getHotelWindowSelectRoomButton() {return btn_HotelWindowSelectRoom;}