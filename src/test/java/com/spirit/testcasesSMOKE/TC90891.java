package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90891
//Test Name: Task 23041: 31338 Confirmation Page_CP_BP_Master Mold
//Description: Validation on Confirmation page for non 9FC
//Created By : Gabriela
//Created On : 20-MAY-2019
//Reviewed By: Salim Ansari
//Reviewed On: 21-MAY-2019
//**********************************************************************************************

public class TC90891 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" , "Adult" , "Guest" , "NonStop" ,"BookIt",
            "NoBags" , "NoSeats" , "CheckInOptions" , "TravelInsuranceBlock" , "MasterCard","ConfirmationUI"})
    public void Confirmation_Page_CP_BP_Master_Mold(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90891 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "RoundTrip";
        final String ARR_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "LAX";
        final String DEP_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "3";
        final String ARR_DATE               = "5";
        final String ADULT                  = "1";
        final String CHILD                  = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		    = "Nonstop";
        final String ARR_FLIGHT             = "Nonstop";
        final String FARE_TYPE              = "Standard";
        final String UPGRADE_FARE           = "BookIt";

        //Options Page Constant Values
        final String CHECKIN_VALUE          = "CheckInOption:MobileFree";

        //Payment page constant value
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEL_GAURD           = "Required";

        // Confirmation page Constant Values for validation
        final String MYTRIP_DATE_FORMAT     = "MMMMM d, YYYY";
        final String MYTRIP_STATUS          = "Confirmed";

        final String PASSENGER_INFO_TYPE    = "Passenger1";

        //constant marketing text
        String text1Expected = "Thank you for choosing Spirit. We look forward to serving you on your upcoming trip!";
        String text2Expected = "For modifications to flight only itineraries, please call 1.801.401.2222";
        String text3Expected = "For modifications to vacation package itineraries please call 1.954.698.0125";
        String text4Expected = "To provide feedback, please email support@spirit.com or write to Spirit Customer Relations, 2800 Executive Way, Miramar, FL 33025.";

        //open browser
        openBrowser(platform);
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULT, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", ARR_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //get the contact information used for later validation
        String contactPhoneNumberPassengerInfoPage = pageObjectManager.getPassengerInfoPage().getContactPersonPhoneNumberTextBox().getAttribute("value");
        String contactEmailPassengerInfoPage = pageObjectManager.getPassengerInfoPage().getContactPersonEmailTextBox().getAttribute("value");
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        //Bags Page Methods
        pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();

        //Seats page method
        pageMethodManager.getSeatsPageMethods().continueWithoutSeats();

        //Options page Methods
        pageMethodManager.getOptionsPageMethods().selectOptions(CHECKIN_VALUE);
        pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

        //Payment page
        String passengerNamePaymentPage = pageObjectManager.getPaymentPage().getPassengerNameText().get(0).getText();

        //get total on the payment page
        String totalOnThePaymentPage = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText().trim();

        //get Travel Guard Price on payment page, this price is not added to the total price until after the booking is complete se we must store this total
        String temp = pageObjectManager.getPaymentPage().getYesTravelGuardLabel().getText();

//        temp = temp.substring(temp.indexOf("$")).replace(" covering all customers on this reservation." , "");
        temp = temp.substring(temp.indexOf("$")).replace(" covering all guests on this reservation." , "");
        double travelGuardPrice = Double.parseDouble(temp.replace("$",""));

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GAURD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();


//--Step 2: Close ROKT pop-up and navigate to the Page Title located under the Breadcrumb
        WaitUtil.untilPageLoadComplete(getDriver());
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Confirmation page expected values
        final String confirmationPageHeading        = "You’re Booked!";
        final String confirmationPageSubHeading     = "You'll get a confirmation email as well, but check the details carefully here.  Have a great trip!";
        final String confirmationPageNoteExpected   = "NOTE: This page is not your boarding pass.";

        //Confirmation page actual values
        String confirmationPageHeadingActual = pageObjectManager.getConfirmationPage().getConfirmationPageHeaderText().getText().trim();
        String confirmationPageSubHeadingActual = pageObjectManager.getConfirmationPage().getConfirmationPageSubHeaderText().getText().trim();
        String confirmationPageNoteExpectedActual = pageObjectManager.getConfirmationPage().getNotYourBoardingPassText().getText().trim();

//        ValidationUtil.validateTestStep("",false);

        ValidationUtil.validateTestStep("The header is correct on the confirmation page",
                confirmationPageHeading , confirmationPageHeadingActual);

        ValidationUtil.validateTestStep("The subheading is correct on the confirmation page",
                confirmationPageSubHeading.replace("  "," ").replace("'","") , TestUtil.removeNonAlphaNumericCharacterFromString(confirmationPageSubHeadingActual.replace("  "," ")).replace("'",""));

        ValidationUtil.validateTestStep("The Note is correct on the confirmation page",
                confirmationPageNoteExpected , confirmationPageNoteExpectedActual);


//--Step 3: Navigate to the Confirmation Code section underneath the title section and marketing verbiage
        String bookingDateOnPage = getDisplayedTextList(pageObjectManager.getConfirmationPage().getBookingSectionTopDateText()).get(0).replace("Booking Date: " , "");
        Date curDate = TestUtil.convertStringToDate(bookingDateOnPage.trim(),MYTRIP_DATE_FORMAT);
        ValidationUtil.validateTestStep(bookingDateOnPage.trim()+"The booking date format is correct",!curDate.equals(null));
        System.out.println(curDate);

        // Status
        String bookingStatus = pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText();
        ValidationUtil.validateTestStep("The booking status is confirmed" , bookingStatus, MYTRIP_STATUS);

        //Confirmation code
        int expectedPNFLength = 6;
        int confirmationCodeLength = getDisplayedTextList(pageObjectManager.getConfirmationPage().getBookingSectionTopConfirmationCode()).get(0).replace("Confirmation Code: ","").trim().length();
        ValidationUtil.validateTestStep("The confirmation code is the correct length" ,
                expectedPNFLength == confirmationCodeLength );

//--Step 4: Navigate down to the Flight section and ensure the heading and 'Add Trip to Calender" link are correctly displayed
        ValidationUtil.validateTestStep("The add to trip button is not displayed" ,
                pageObjectManager.getConfirmationPage().getAddTripToCalendarButton().isDisplayed());

//--Step 5: Move down into the content block and verify the values displaying correspond with the city pair, date, time and flight number selected during booking
        for (int count = 0;count<getDisplayedTextList(pageObjectManager.getConfirmationPage().getFlightSectionDateText()).size();count++){
            String dateString = getDisplayedTextList(pageObjectManager.getConfirmationPage().getFlightSectionDateText()).get(count);
            Date date = TestUtil.convertStringToDate(dateString.trim(),MYTRIP_DATE_FORMAT);
            ValidationUtil.validateTestStep(dateString.trim()+"The booking date format is correct",!date.equals(null));
            System.out.println(curDate);
        }

        for (String departingCity: getDisplayedTextList(pageObjectManager.getConfirmationPage().getFlightSectionDepartCityText())) {

            ValidationUtil.validateTestStep(departingCity.trim()+" City code comes after the Origin and Destination City and time at last also "
                    ,departingCity.trim().replace("  "," ").matches("(.)*\\s\\(([A-Z]){3}\\)\\s([0-9]){1,2}\\:([0-9]){2}\\s([A-Z]){2}"));
        }

        for(String flightNumber: getDisplayedTextList(pageObjectManager.getConfirmationPage().getFlightSectionFlightNumberText())){
            ValidationUtil.validateTestStep(flightNumber.trim()+" City code comes after the Origin and Destination City and time at last also "
                    ,flightNumber.trim().matches("^Flight:\\s([A-Z0-9]){5,6}$"));
        }

//-- Step 6: Navigate down to the Passengers section and verify that PAX information is correct and properly displayed
        String passengerName = pageObjectManager.getConfirmationPage().getPassengerSectionNamesText().get(0).getText();

        ValidationUtil.validateTestStep("The passenger Name is correct",
                passengerName , passengerNamePaymentPage);

        ValidationUtil.validateTestStep("The Bag icon is displayed" ,
                pageObjectManager.getConfirmationPage().getBagsIcon().get(0).isDisplayed());

        ValidationUtil.validateTestStep("The Seats icon is displayed" ,
                pageObjectManager.getConfirmationPage().getSeatsIcon().get(0).isDisplayed());

        ValidationUtil.validateTestStep("The Passenger additional Info text is displayed" ,
                pageObjectManager.getConfirmationPage().getPassengerAdditionalInfo().get(0).isDisplayed());

//-- Step 7: Navigate down to the Options section and verify that the selected options and extras are properly displayed
//-- Step 8: Navigate down to the Extras subsection of the Options section and verify that the selected extras are properly displayed
        //Both Steps are skipped because, no Options were added om the booking

//-- Step 9: IF Travel Insurance was purchased, navigate down to the Travel Insurance section and verify it is displayed

//        pageMethodManager.getConfirmationPageMethods().verifyTravelInsuranceSection();

//-- Step 10: Navigate down to the Contact section and verify the contact information is correct and properly displayed
        String contactPersonNoteMessage = "We'll keep you posted about any changes to this trip,so please double check your email and phone number.";

        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest(PASSENGER_INFO_TYPE);

        //Validate contact name
        String contactName = pageObjectManager.getConfirmationPage().getContactPersonName().getText().trim();
        String contactEmail = pageObjectManager.getConfirmationPage().getContactPersonEmail().getText().trim();

        //validation of member information
        ValidationUtil.validateTestStep("The Contact Person name is correct" ,
                contactName,passengerInfoData.firstName+" "+ passengerInfoData.lastName);

        ValidationUtil.validateTestStep("The Contact Email is correct" ,
                contactEmail,contactEmailPassengerInfoPage);

        //validate contact phone number
        String contactPhoneNumber = pageObjectManager.getConfirmationPage().getContactPersonPhoneNumber().getText();

        ValidationUtil.validateTestStep("The contact phone number is correct" ,
                contactPhoneNumber.contains(contactPhoneNumberPassengerInfoPage));

        ValidationUtil.validateTestStep("The Contact note message to check phone and email is displayed",
                pageObjectManager.getConfirmationPage().getContactPersonNote().getText().trim().replace("\n","").replace("  ",""),contactPersonNoteMessage);

//-- Step 11: Navigate to the Purchase Price section and verify the display and price breakdown for Flight, Bags, Seats, Extras, Government's Cut and Total Paid subsections are correct.
        String confirmationTotal = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText().trim();
        double paymentPagePlusTravelGuard = Double.parseDouble(totalOnThePaymentPage.replace("$","")) + travelGuardPrice; //add the stored travel guard price
        totalOnThePaymentPage = "$" + Math.rint((paymentPagePlusTravelGuard)*100)/100;
        //totalOnThePaymentPage = "$" + paymentPagePlusTravelGuard;


        ValidationUtil.validateTestStep("The total paid on confirmation page and the total charges from payment page is correct" , totalOnThePaymentPage, confirmationTotal);

//-- Step 13: Navigate right to PRINT CONFIRMATION button and verify it is correctly displayed. Then, click it.
        ValidationUtil.validateTestStep("The print confirmation button is displayed",
                pageObjectManager.getConfirmationPage().getHeaderPrintConfirmationButton().isDisplayed());

//-- Step 14: Navigate below the mobile apps and verify Customer Service marketing verbiage is correct
        boolean marketingTextIsDisplayed = getDriver().findElement(By.xpath("//div[contains(@class,'row')]//p[contains(text(),'" + text1Expected + "')]")).isDisplayed()&&
                getDriver().findElement(By.xpath("//div[contains(@class,'row')]//p[contains(text(),'" + text2Expected + "')]")).isDisplayed()&&
                getDriver().findElement(By.xpath("//div[contains(@class,'row')]//p[contains(text(),'" + text3Expected + "')]")).isDisplayed()&&
                getDriver().findElement(By.xpath("//div[contains(@class,'row')]//p[contains(text(),'" + text4Expected + "')]")).isDisplayed();

        ValidationUtil.validateTestStep("The marketing text at the bottom of the confirmation page is displayed",
                marketingTextIsDisplayed);

//-- Step 15: Navigate to the Mobile App section in the footer and verify correct mobile app verbiage and icons are displayed
        //(UI) checking the display of the footer, this is not functionality of this page

//Step 16 skip
        //Unable tp validate PNR on SkysPeed
    }

    private List<String> getDisplayedTextList(List<WebElement> elementList){
        ArrayList<String> arrayList = new ArrayList<>();
        for (WebElement element:elementList) {
            if(element.isDisplayed()){
                arrayList.add(element.getText().trim());
            }
        }
        return arrayList;
    }
}