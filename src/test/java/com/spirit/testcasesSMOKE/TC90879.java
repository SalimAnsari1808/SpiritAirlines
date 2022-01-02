package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//**********************************************************************************************
//Test Case ID: TC90879
//Test Name: Confirmation Page_CP_ BP_$9FC
//Description: Validate the Confirmation page for a 9DFC member
//Created By : Anthony Cardona
//Created On : 17-APR-2019
//Reviewed By: Salim Ansari
//Reviewed On: 29-Apr-2019
//**********************************************************************************************
public class TC90879 extends BaseClass {
    @Parameters({"platform"})
    @Test (groups = {"BookPath","OneWay","DomesticDomestic","Outside21Days","Adult","NineDFC","BookIt",
                    "NoBags","NoSeats","CheckInOptions","TravelInsuranceBlock","MasterCard","ConfirmationUI"})
    public void Confirmation_Page_CP_BP_9FC(@Optional("NA") String platform) {
        //************Navigate to Home Page**************************
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC90879 under SMOKE Suite on " + platform + " Browser", true);
        }
        //Home Page Constant Values
        final String LANGUAGE               = "English";
        final String LOGIN_EMAIL            = "NineDFCEmail";
        final String JOURNEY_TYPE           = "Flight";
        final String TRIP_TYPE              = "Oneway";
        final String ARR_AIRPORTS           = "AllLocation";
        final String DEP_AIRPORT_CODE       = "FLL";
        final String DEP_AIRPORTS           = "AllLocation";
        final String ARR_AIRPORT_CODE       = "LAS";
        final String DEP_DATE               = "40";
        final String ARR_DATE               = "NA";
        final String ADULTS                 = "1";
        final String CHILDS                 = "0";
        final String INFANT_LAP             = "0";
        final String INFANT_SEAT            = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT 		    = "9DFC";
        final String FARE_TYPE              = "member";
        final String UPGRADE_FARE           = "BookIt";
        final String JOURNEY                = "Dep";

        final String CHECKIN_VALUE          = "CheckInOption:MobileFree";

        //Payement page constant value
        final String CARD_TYPE              = "MasterCard";
        final String TRAVEl_GAURD           = "Required";

        final String MYTRIP_DATE_FORMAT     = "MMMMM d, YYYY";
        final String MYTRIP_STATUS          = "Confirmed";
        final String FILE_NAME              = "spirit";
        final String FILE_EXTENTION         = "ics";

        final String PASSENGER_INFO_TYPE    = "NineFCMember";

//Step 1
        //open browser
        openBrowser(platform);
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILDS, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType(JOURNEY, DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_FARE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();

        //get the contact information used for later validation
        String contactPhoneNumberPasssengerInformationPage = pageObjectManager.getPassengerInfoPage().getContactPersonPhoneNumberTextBox().getAttribute("value");

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
        String totalOnThePaymentPage = pageObjectManager.getPaymentPage().getTotalDuePriceText().getText();

        //get Travel Guard Price on payment page, this price is not added to the total price until after the booking is complete se we must store this total
        String temp = pageObjectManager.getPaymentPage().getYesTravelGuardLabel().getText();
//        temp = temp.substring(temp.indexOf("$")).replace(" covering all customers on this reservation." , "");
        temp = temp.substring(temp.indexOf("$")).replace(" covering all guests on this reservation." , "");
        double travelGuardPrice = Double.parseDouble(temp.replace("$",""));

        //Payment Page Methods
        pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEl_GAURD);
        pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
        pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

//Step 2
        //close the Rocket Pop Up and validate the header, sub header text
        pageMethodManager.getConfirmationPageMethods().closeROKTPopup();

        //Confirmation page expected values
        String confirmationPageHeading      = "You’re Booked!";
        String confirmationPageSubHeading   = "You’ll get a confirmation email as well, but check the details carefully here.  Have a great trip!";
        String confirmationPageNoteExpected  = "NOTE: This page is not your boarding pass.";

        //Confirmation page actual values
        String confirmationPageHeadingActual      = pageObjectManager.getConfirmationPage().getConfirmationPageHeaderText().getText();
        String confirmationPageSubHeadingActual   = pageObjectManager.getConfirmationPage().getConfirmationPageSubHeaderText().getText();
        String confirmationPageNoteExpectedActual  = pageObjectManager.getConfirmationPage().getNotYourBoardingPassText().getText();

        ValidationUtil.validateTestStep("The header is correct on the confirmation page", confirmationPageHeading , confirmationPageHeadingActual);
        ValidationUtil.validateTestStep("The subheading is correct on the confirmation page", confirmationPageSubHeading , confirmationPageSubHeadingActual);
        ValidationUtil.validateTestStep("The Note is correct on the confirmation page", confirmationPageNoteExpected , confirmationPageNoteExpectedActual);

//Step 3
        //validate confirmation code box
        //booking date
        String bookingDateOnpage = getDisplayedTextList(pageObjectManager.getConfirmationPage().getBookingSectionTopDateText()).get(0).replace("Booking Date: " , "");
        Date curDate = TestUtil.convertStringToDate(bookingDateOnpage.trim(),MYTRIP_DATE_FORMAT);
        ValidationUtil.validateTestStep(bookingDateOnpage.trim()+"The booking date format is correct",!curDate.equals(null));
        System.out.println(curDate);

        //Confirmation Status
        String bookingStatus = pageObjectManager.getConfirmationPage().getBookingSectionTopStatusText().getText();
        ValidationUtil.validateTestStep("The booking status is confirmed" , bookingStatus, MYTRIP_STATUS);

        //Confirmation code
        int expectedPNFLength = 6;
        int confirmationCodeLength = getDisplayedTextList(pageObjectManager.getConfirmationPage().getBookingSectionTopConfirmationCode()).get(0).replace("Confirmation Code: ","").trim().length();
        ValidationUtil.validateTestStep("The confirmation code is the correct length" , expectedPNFLength == confirmationCodeLength );

//Step 4
        //Validate that the add rip to calendar button is displayed
        ValidationUtil.validateTestStep("The add to trip button is not displayed" , pageObjectManager.getConfirmationPage().getAddTripToCalendarButton().isDisplayed());
//        pageObjectManager.getConfirmationPage().getAddTripToCalendarButton().click();
//        WaitUtil.untilTimeCompleted(5000);
//        ValidationUtil.validateTestStep("Click on Add trip my calendar spirit.ics file is downloaded",TestUtil.verifyFileDownload(FILE_NAME,FILE_EXTENTION));

//Step 5
        //validate that the booking date contains current date in MMMMM DD, YYYY format
        for (int count = 0;count<getDisplayedTextList(pageObjectManager.getConfirmationPage().getFlightSectionDateText()).size();count++){
            String dateString = getDisplayedTextList(pageObjectManager.getConfirmationPage().getFlightSectionDateText()).get(count);
            Date date = TestUtil.convertStringToDate(dateString.trim(),MYTRIP_DATE_FORMAT);
            ValidationUtil.validateTestStep(dateString.trim()+"The booking date format is correct",!date.equals(null));
            System.out.println(curDate);

        }

        for (String departingCity: getDisplayedTextList(pageObjectManager.getConfirmationPage().getFlightSectionDepartCityText())) {

            ValidationUtil.validateTestStep(departingCity.trim()+" City code comes after the Origin and Destination City and time at last also "
                    ,departingCity.trim().matches("(.)*\\s\\(([A-Z]){3}\\)\\s([0-9]){1,2}\\:([0-9]){2}\\s([A-Z]){2}"));

        }

        for(String flightNumber: getDisplayedTextList(pageObjectManager.getConfirmationPage().getFlightSectionFlightNumberText())){
            ValidationUtil.validateTestStep(flightNumber.trim()+" City code comes after the Origin and Destination City and time at last also "
                    ,flightNumber.trim().matches("^Flight:\\s([A-Z0-9]){5,6}$"));

        }

//Step 6
        //validate passenger section
        //Expected data from PassengerInfo JSON
        LoginCredentialsData loginCredentialsData =FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(LOGIN_EMAIL);
        String fsNumberExpected = loginCredentialsData.fsNumber;
        passengerNamePaymentPage = passengerNamePaymentPage;

        //Actual data from page
        String fsNumberActual = pageObjectManager.getConfirmationPage().getPassengerFreeSpirirtNumberText().get(0).getText();
        String passengerName = pageObjectManager.getConfirmationPage().getPassengerSectionNamesText().get(0).getText();


        //validate FS number and passenger name
        ValidationUtil.validateTestStep("The passenger Name is correct", passengerName , passengerNamePaymentPage);
        ValidationUtil.validateTestStep("The FS number is correct", fsNumberActual, fsNumberExpected);

        ValidationUtil.validateTestStep("The Bag icon is displayed" , pageObjectManager.getConfirmationPage().getBagsIcon().get(0).isDisplayed());
        ValidationUtil.validateTestStep("The Seats icon is displayed" ,  pageObjectManager.getConfirmationPage().getSeatsIcon().get(0).isDisplayed());
        ValidationUtil.validateTestStep("The Passenger additional Info text is displayed" ,  pageObjectManager.getConfirmationPage().getPassengerAdditionalInfo().get(0).isDisplayed());

//Step 7 and 8
        //this step is skipped because no options were added

//Step 9
        //Validate Travel Guard text box
        //constant Variable
        String expectedTravelInsuranceNoteText = "NOTE: View your policy online . For additional questions about your policy please contact Travel Guard directly at 866-877-3191.";

        //validate policy number
        int policyNumberlength = pageObjectManager.getConfirmationPage().getTravelInsurancePolicyNumber().getText().length();
        int policyNumberExpectedlength = 9;
        ValidationUtil.validateTestStep("The policy number is the correct length" , policyNumberlength >= policyNumberExpectedlength);

        //validate the primary insured name
        String primaryInsuredNameActual = pageObjectManager.getConfirmationPage().getTravelInsurancePrimaryInsuredName().getText();
        ValidationUtil.validateTestStep("The primary insured name is correct" , passengerName, primaryInsuredNameActual);

        //validate that the travel insurance Note is displayed correctly
        String ActualTravelInsuranceNoteText = pageObjectManager.getConfirmationPage().getTravelInsuranceNoteText().getText();
        ValidationUtil.validateTestStep("The primary insured name is correct" , ActualTravelInsuranceNoteText,expectedTravelInsuranceNoteText);

//Step 10
        //Validate contact text box
        //constant Variable
        String contactPersonNoteMessage = "We'll keep you posted about any changes to this trip,\nso please double check your email and phone number.";
        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest(PASSENGER_INFO_TYPE);
        //Validate contact name
        String contactName = pageObjectManager.getConfirmationPage().getContactPersonName().getText().trim();
        String contactEmail = pageObjectManager.getConfirmationPage().getContactPersonEmail().getText().trim();
        //validation of member information
        ValidationUtil.validateTestStep("The Contact Person name is correct" , contactName,passengerInfoData.firstName+" "+passengerInfoData.lastName);
        ValidationUtil.validateTestStep("The Contact Email is correct" , contactEmail,loginCredentialsData.userName);

        //validate contact phone number
        String contactPhoneNumber = pageObjectManager.getConfirmationPage().getContactPersonPhoneNumber().getText();
        //the page adds the country code (Example: +1 for US, India +91) therefore we compare with contains text
        ValidationUtil.validateTestStep("The contact phone number is correct" , contactPhoneNumber.contains(contactPhoneNumberPasssengerInformationPage));

        ValidationUtil.validateTestStep("The Contact note message to check phone and email is displayed", pageObjectManager.getConfirmationPage().getContactPersonNote().getText().trim(),contactPersonNoteMessage);

//Step 11
        //validate the $9 FARE CLUB SAVINGS is displayed on the confirmation page
        final String CONFIRMATION_9FC_SAVING = "$9 Fare Club savings";

        String actual9FCSaving = pageObjectManager.getConfirmationPage().get9FCBookingSavingVerbageText().getText().trim();

        ValidationUtil.validateTestStep("The 9FC savings amount is displayed", pageObjectManager.getConfirmationPage().get9FCBookingSavingsAmount().isDisplayed());

        ValidationUtil.validateTestStep("A content block label '$9 Fare Club Savings' is displyed",actual9FCSaving,CONFIRMATION_9FC_SAVING);

//Step 12
        //validate the total price (Cannot validate full breakdown because the payment page breakdown and Confirmation page breakdown do not match when validating extras)
        String confirmationTotal = pageObjectManager.getConfirmationPage().getTotalPaidPriceText().getText();
        double paymentPagePlusTravelGuard = Double.parseDouble(totalOnThePaymentPage.replace("$","")) + travelGuardPrice; //add the stored travel guarld price
        totalOnThePaymentPage = "$" + paymentPagePlusTravelGuard;
        ValidationUtil.validateTestStep("The total paid on confirmation page and the total charges from payment page is correct" , totalOnThePaymentPage , confirmationTotal );

//Step 13
        //Not an international booking so there won't be a passport App marketing link

//Step 14

        //validate print confirmation button
        ValidationUtil.validateTestStep("The print confirmation button is displayed" , pageObjectManager.getConfirmationPage().getHeaderPrintConfirmationButton().isDisplayed());

//Step 15
        //validate mobile app marketing verbiage
        //constant marketing text
        String text1Expected = "Thank you for choosing Spirit. We look forward to serving you on your upcoming trip!";
        String text2Expected = "For modifications to flight only itineraries, please call 1.801.401.2222";
        String text3Expected = "For modifications to vacation package itineraries please call 1.954.698.0125";
        String text4Expected = "To provide feedback, please email support@spirit.com or write to Spirit Customer Relations, 2800 Executive Way, Miramar, FL 33025.";

        boolean markettingTextIsDisplayed = getDriver().findElement(By.xpath("//div[contains(@class,'row')]//p[contains(text(),'" + text1Expected + "')]")).isDisplayed()&&
                getDriver().findElement(By.xpath("//div[contains(@class,'row')]//p[contains(text(),'" + text2Expected + "')]")).isDisplayed()&&
                getDriver().findElement(By.xpath("//div[contains(@class,'row')]//p[contains(text(),'" + text3Expected + "')]")).isDisplayed()&&
                getDriver().findElement(By.xpath("//div[contains(@class,'row')]//p[contains(text(),'" + text4Expected + "')]")).isDisplayed();
        ValidationUtil.validateTestStep("The marketing text at the bottom of the confirmation page is displayed", markettingTextIsDisplayed);

//Step 16 skip
        //(UI) checking the display of the footer, this is not functionality of this page

//Step 17 skip
        //valiadate PNR on Skyspeed
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