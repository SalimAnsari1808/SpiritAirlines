package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.dataType.*;
import com.spirit.managers.*;
import com.spirit.utility.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test CaseID: TC90734
//Description: CP_MT_Payment Page_Payment_Method_Credit_Card
//Created By : Alex Rodriguez
//Created On : 22-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On: 01-AUG-2019
//**********************************************************************************************
//TODO: Bug 25598 Prod: CP: BP: Payment Page: No error message is displayed for Name On Card textbox when two spaces are entered.
public class TC90734 extends BaseClass {
  @Parameters({"platform"})
  @Test(groups = {"ActiveBug" , "MyTrips" , "RoundTrip" , "DomesticDomestic" , "WithIn7Days" ,"WithIn21Days", "Adult" , "FreeSpirit" , "NonStop" , "BookIt" , "CarryOn" , "CheckedBags" , "NoSeats" ,"ShortCutBoarding", "CheckInOptions" , "MasterCard" , "PaymentUI" , "AddEditBags"})
  public void CP_MT_Payment_Page_Payment_Method_Credit_Card(@Optional("NA") String platform) {

    /******************************************************************************
     ***********************Navigate to Payment Page*******************************
     ******************************************************************************/

    //Mention Suite and Browser in reports
    if(!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC90734 under REGRESSION CRITICAL Suite on " + platform + " Browser" , true);
    }

    //Home Page Constant Values
    final String LANGUAGE 			                = "English";
    final String LOGIN_EMAIL 		                = "FSEmail";
    final String JOURNEY_TYPE                       = "Flight";
    final String TRIP_TYPE                          = "RoundTrip";
    final String DEP_AIRPORTS                       = "AllLocation";
    final String DEP_AIRPORT_CODE_DOM               = "FLL";
    final String ARR_AIRPORTS                       = "AllLocation";
    final String ARR_AIRPORT_CODE                   = "ORD";
    final String DEP_DATE                           = "5";
    final String ARR_DATE                           = "10";
    final String ADULTS                             = "1";
    final String CHILD                              = "0";
    final String INFANT_LAP                         = "0";
    final String INFANT_SEAT                        = "0";

    //Flight Availability Page Constant Values
    final String DEP_FLIGHT                         = "NonStop";
    final String RET_FLIGHT                         = "NonStop";
    final String FARE_TYPE                          = "Standard";
    final String UPGRADE_VALUE                      = "BookIt";

    //Bags Page Constant Values
    final String DEP_BAGS                           = "Carry_1|Checked_1";

    //Options Page Constant Value
    final String OPTION_VALUE                       = "CheckInOption:MobileFree";

    //Payment Page Constant values
    final String TRAVEL_GUARD 		                = "NotRequired";
    final String CARD_TYPE                          = "MasterCard";

    //Seats pop up
    final String SEATS                              = "DontPurchase";


    //Payment page constant values
    final String PAYMENT_URL                        = "/my-trips/payment";
    final String HEADER                             = "PAYMENT";
    final String HEADER_FONT                        = "Source_Sans_Pro_Black, Verdana, sans-serif";
    final String MIN_LENGTH                         = "Min Length 2";
    final String NUMERIC_ERROR                      = "Invalid characters entered";
    final String CARD_NUMBER_LABEL                  = "Card Number";
    final String NAME_REQUIRED_ERROR                = "Name is required";
    final String DATE_FORMAT                        = "/^(?:0?[1-9]|1[0-2]) *\\/ *[1-9][0-9]$/";
    final String SECURITYCODE_POPUP_TXT             = "Your security code is a 4-digit number located on the front or a 3-digit number located on the back of your card.";
    final String STATE_REQUIRED_ERROR               = "State is required";
    final String ZIPCODE_REQUIRED_ERROR             = "Zip/Postal Code is required";
    final String ADDRESS_REQUIRED_ERROR             = "Address is required";
    final String CITY_REQUIRED_ERROR                = "City is required";
    final String ZIPCODE_INVALID_ERROR              = "This is an invalid zip code";
    final String ADDRESS_MAXLENTH                   = "50";
    final String CITY_MAXLENGTH                     = "30";
    final String DEFAULT_COUNTRY_DROPDOWN_VALUE     = "US";
    final String CARDNUMBER_INVALID_ERROR           = "Invalid card number.";
    final String EXPIRATIONDATE_INVALID_ERROR       = "Expiration date is invalid";


    //open browser
    openBrowser(platform);

//-- Step 1:
    //******************************************************************************
    //*******************************Navigate to Payment Page***********************
    //******************************************************************************
    //Home Page Methods
    pageMethodManager.getHomePageMethods().launchSpiritApp();
    pageMethodManager.getHomePageMethods().loginToApplication(LOGIN_EMAIL);
    pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
    pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
    pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
    pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE_DOM, ARR_AIRPORTS, ARR_AIRPORT_CODE);
    pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
    pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
    pageMethodManager.getHomePageMethods().clickSearchButton();

    //Flight Availability Methods
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Ret", RET_FLIGHT);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
    pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

    //Customer Info Page Method
    pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
    pageMethodManager.getPassengerInfoMethods().clickContinueButton();

    //Bags page
    pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
    WaitUtil.untilPageLoadComplete(getDriver());

    //Seats Page
    pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
    WaitUtil.untilPageLoadComplete(getDriver());

    //Option Page Methods
    pageMethodManager.getOptionsPageMethods().selectOptions(OPTION_VALUE);
    pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

    //Payment Page Method
    pageMethodManager.getPaymentPageMethods().selectTravelGuard(TRAVEL_GUARD);
    pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails(CARD_TYPE);
    pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();

    //Confirmation Page Methods
    pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
    pageMethodManager.getConfirmationPageMethods().storeInformationFromConfirmation();

    //Retrieve PNR through My Trips
    pageMethodManager.getHomePageMethods().loginToMyTrip();

    pageMethodManager.getReservationSummaryPageMethods().buyBagsSeatsPassengerSection("Bags");
    pageMethodManager.getBagsPageMethods().selectDepartingBags(DEP_BAGS);
    pageMethodManager.getBagsPageMethods().selectBagsFare(FARE_TYPE);

    pageMethodManager.getReservationSummaryPageMethods().purchaseDontPurchaseOnSeatsPopup(SEATS);

    WaitUtil.untilPageLoadComplete(getDriver());
    pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();

    //*****************************************************************************
    //***************************Validation on Payment Page********************
    //******************************************************************************/
// Header Validation

    ValidationUtil.validateTestStep("Validating payment page URL",getDriver().getCurrentUrl(),PAYMENT_URL);

    ValidationUtil.validateTestStep("Validating Payment page header",
            pageObjectManager.getPaymentPage().getPaymentHeaderText().getText().trim(),HEADER);

    //Header Font Validation
    ValidationUtil.validateTestStep("Validating Payment page header font",
            pageObjectManager.getPaymentPage().getPaymentHeaderText().getCssValue("font-family"),HEADER_FONT);

    //CardHolder Textbox validation
    //Validating Name on card is displayed
    ValidationUtil.validateTestStep("Validating Name on Card Label message",
            TestUtil.verifyElementDisplayed(pageObjectManager.getMemberEnrollmentPage().getnameOnCardText()));

    //Validating Minimun length value for Name on card textbox
    pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox().sendKeys(TestUtil.getAlphaCharacterString(1));
    pageObjectManager.getPaymentPage().getBookTripButton().click();
    ValidationUtil.validateTestStep("Validating Minimum Length should be 2 characters",
            pageObjectManager.getCommon().getErrorMessageLabel().getText().trim(),MIN_LENGTH);

    //Validating error message for Maximum length value for Name with VISA
    ValidatingNameOnCardTxbxMaxlenValforCC("VisaCard",21);

    //Validating error message for Maximum length value for Name with Mastercard
    ValidatingNameOnCardTxbxMaxlenValforCC("MasterCard",22);

    //Validating error message for Maximum length value for Name with AMEX
    ValidatingNameOnCardTxbxMaxlenValforCC("AmericanExpressCard", 27);

    //Validating error message for Maximum length value for Name with DISCOVER
    ValidatingNameOnCardTxbxMaxlenValforCC("DiscoverCard1", 30);

    //Verify ALPHA, apostrophe (''), dash (-), space, dot (.), or comma (,) are allowed
    JSExecuteUtil.refreshBrowser(getDriver());
    WaitUtil.untilPageLoadComplete(getDriver());

    CardDetailsData VisaCardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(CARD_TYPE);
    JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox());
    PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("FSMember");

    pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox().sendKeys(passengerInfoData.firstName +" '.-,");
    pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox().sendKeys(VisaCardDetailsData.cardNumber);
    pageObjectManager.getMemberEnrollmentPage().getExpirationMonthYearTextBox().sendKeys(VisaCardDetailsData.expirationDate);
    pageObjectManager.getMemberEnrollmentPage().getSecurityCodeTextBox().sendKeys(VisaCardDetailsData.securityCode);
    pageObjectManager.getPaymentPage().getTermsAndConditionsLabel().click();

    ValidationUtil.validateTestStep("Validating ALPHA, apostrophe (''), dash (-), space, dot (.), or comma (,) are allowed",
            !TestUtil.verifyElementDisplayed(pageObjectManager.getCommon().getErrorMessageLabel()));

    //Verify Name is Required
    TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox());

    ValidationUtil.validateTestStep("Validating Name is required error message",
            pageObjectManager.getCommon().getErrorMessageLabel().getText().trim(), NAME_REQUIRED_ERROR);

    //verify error message when a user enters numeric characters in the field
    pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox().sendKeys("00");
    ValidationUtil.validateTestStep("Validating numeric characters error message",
            pageObjectManager.getCommon().getErrorMessageLabel().getText().trim(), NUMERIC_ERROR);

    //Verify Card Number Textbox
    JSExecuteUtil.refreshBrowser(getDriver());
    WaitUtil.untilPageLoadComplete(getDriver());
    JSExecuteUtil.scrollDownToElementVisible(getDriver(), pageObjectManager.getMemberEnrollmentPage().getCardNumberText());
    ValidationUtil.validateTestStep("Validate Card Number Label",
            pageObjectManager.getMemberEnrollmentPage().getCardNumberText().getText().trim(),CARD_NUMBER_LABEL);

    //validating Length value for Card Number text box and badges
    ValidateCardNumberTxbx("MasterCard", "MASTER-CARD","16" );

    ValidateCardNumberTxbx("VisaCard", "VISA-CARD","16" );

    ValidateCardNumberTxbx("DiscoverCard1", "DISCOVER-CARD","16" );

    ValidateCardNumberTxbx("AmericanExpressCard", "AMERICAN-EXPRESS","15" );

    //Validate only numbers are allowed for input in Card Number textbox
    TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox());

    pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox().sendKeys(TestUtil.getAlphaCharacterString(5));

    ValidationUtil.validateTestStep("Validating alpha characters can not be entered",
            pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox().getAttribute("value"),"");

    //Validate Invalid Card Number error message
    //get the card details
    CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType("MasterCard");
    pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox().sendKeys(cardDetailsData.cardHolderName);
    pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox().sendKeys("3546546546546546");
    pageObjectManager.getPaymentPage().getBookTripButton().click();
    ValidationUtil.validateTestStep("Validate Invalid Card Number error message",
            pageObjectManager.getCommon().getErrorMessageLabel().getText().trim(),CARDNUMBER_INVALID_ERROR);

    //Validating Expiration Date Textbox
    JSExecuteUtil.refreshBrowser(getDriver());
    WaitUtil.untilPageLoadComplete(getDriver());
    JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getMemberEnrollmentPage().getExpirationMonthYearTextBox());
    ValidationUtil.validateTestStep("Validate Expiration date label is displayed",
            TestUtil.verifyElementDisplayed(pageObjectManager.getMemberEnrollmentPage().getExpirationDateText()));

    pageObjectManager.getMemberEnrollmentPage().getExpirationMonthYearTextBox().sendKeys(TestUtil.getAlphaCharacterString(4));
    ValidationUtil.validateTestStep("Validating alpha characters can not be entered into expiration date textbox",
            pageObjectManager.getMemberEnrollmentPage().getExpirationMonthYearTextBox().getAttribute("value"),"");
    ValidationUtil.validateTestStep("Validating Format: MM / YY - 2 digit month and 2 digit year",
            pageObjectManager.getMemberEnrollmentPage().getExpirationMonthYearTextBox().getAttribute("pattern"),DATE_FORMAT);

    pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox().sendKeys(cardDetailsData.cardHolderName);
    pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox().sendKeys(cardDetailsData.cardNumber);

    //Validate error message when date is not entered
    pageObjectManager.getPaymentPage().getBookTripButton().click();
    ValidationUtil.validateTestStep("Validating error message when date is not entered",
            pageObjectManager.getCommon().getErrorMessageLabel().getText(),"Expiration date is required");
    //Validate error when invalid Date format
    pageObjectManager.getMemberEnrollmentPage().getExpirationMonthYearTextBox().sendKeys("01");
    ValidationUtil.validateTestStep("Validating error message when date is not entered",
            pageObjectManager.getCommon().getErrorMessageLabel().getText(),EXPIRATIONDATE_INVALID_ERROR);

    //Validating Security Code Textbox
    JSExecuteUtil.refreshBrowser(getDriver());
    WaitUtil.untilPageLoadComplete(getDriver());
    JSExecuteUtil.scrollDownToElementVisible(getDriver(),pageObjectManager.getMemberEnrollmentPage().getSecurityCodeTextBox());
    ValidationUtil.validateTestStep("Verifying Security Code label is displayed",
            TestUtil.verifyElementDisplayed(pageObjectManager.getMemberEnrollmentPage().getSecurityCodeText()));

    pageObjectManager.getMemberEnrollmentPage().getSecurityCodeTextBox().sendKeys(TestUtil.getAlphaCharacterString(3));
    ValidationUtil.validateTestStep("Validating alpha characters can not be entered into security code textbox",
            pageObjectManager.getMemberEnrollmentPage().getSecurityCodeTextBox().getAttribute("value"),"");

    ValidateSecurityCodeMaxLength("VisaCard", "3","12");

    ValidateSecurityCodeMaxLength("MasterCard", "3","12");

    ValidateSecurityCodeMaxLength("DiscoverCard1", "3","12");

    ValidateSecurityCodeMaxLength("AmericanExpressCard", "4","123");

    TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getSecurityCodeTextBox());
    pageObjectManager.getMemberEnrollmentPage().getSecurityCodeTextBox().sendKeys("1234");

    //Validating Security tool tip
    pageObjectManager.getMemberEnrollmentPage().getSecurityCodeToolTipIcon().click();
    WaitUtil.untilPageLoadComplete(getDriver(),(long)2000);
    ValidationUtil.validateTestStep("Validating security code popup is displayed",
            TestUtil.verifyElementDisplayed(pageObjectManager.getCommon().getPopOverWindow()));

    ValidationUtil.validateTestStep("Validating security code popup is displayed",
            pageObjectManager.getCommon().getPopOverWindowBodyText().getText().trim(),SECURITYCODE_POPUP_TXT);

    //Validate Save this card checkbox
    ValidationUtil.validateTestStep("Verifying Save This card for future booking label",
            pageObjectManager.getPaymentPage().getPaymentSectionSaveCardCLabel().getText(),"Save this card for future bookings");

    ValidationUtil.validateTestStep("Verifying Save This card for future bookins checkbox is not selected",
            !pageObjectManager.getPaymentPage().getPaymentSectionSaveCardCheckBox().isSelected());

    //Validating billing Address Label
    ValidationUtil.validateTestStep("Verifying Billing Address Label is displayed",
            TestUtil.verifyElementDisplayed(pageObjectManager.getMemberEnrollmentPage().getBillingAddressText()));

    //Validating HORIZONTAL LINE  be located directly under Billing Address
    ValidationUtil.validateTestStep("Verifying HORIZONTAL LINE  be located directly under Billing Address",
            TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getBillingAddressHorizontalLine()));

    //Verify billing address checkbox is selected
    ValidationUtil.validateTestStep("Validate billing address checkbox is selected",pageObjectManager.getPaymentPage().getUseSameAddressCheckBox().isSelected());

    MemberEnrollmentData ContactsDetail = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("ContactTab");
    //Verify billing Address
    ValidationUtil.validateTestStep("Verify Billing Address",
            pageObjectManager.getPaymentPage().getUseSameAddressLabel().getText().trim(),"Please use " + ContactsDetail.address1 + ", " + ContactsDetail.city + ", " + ContactsDetail.stateCode + " " + ContactsDetail.zipCode +", " + ContactsDetail.countryCode + " for my billing information.");

    //Verify when Label Unchecked: Billing Address different from Contact Information page and should be expanded so user interface is displayed with blank Billing Address fields
    pageObjectManager.getPaymentPage().getUseSameAddressLabel().click();

    WaitUtil.untilPageLoadComplete(getDriver(),(long)3000);

    //Validate Checkbox should be cleared and Billing Address fields should expand for data entry
    ValidationUtil.validateTestStep("verify Same Address Checkbox should be cleared",
            !pageObjectManager.getPaymentPage().getUseSameAddressCheckBox().isSelected());
    ValidationUtil.validateTestStep("Verify Address field are displayed for data entry and max length on Payment Page",
            TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getAddressTextbox())
                    && pageObjectManager.getPaymentPage().getAddressTextbox().getAttribute("value").equals("")
                    && pageObjectManager.getPaymentPage().getAddressTextbox().getAttribute("appmaxlength").equals(ADDRESS_MAXLENTH));

    ValidationUtil.validateTestStep("Verify City fields are displayed for data entry and max length on Payment Page",
            TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getCityTextbox())
                    && pageObjectManager.getPaymentPage().getCityTextbox().getAttribute("value").equals("")
                    && pageObjectManager.getPaymentPage().getCityTextbox().getAttribute("maxlength").equals(CITY_MAXLENGTH));

    ValidationUtil.validateTestStep("Verify State field are displayed for data entry on Payment Page",
            TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getStateTextBox())
                    && pageObjectManager.getPaymentPage().getStateTextBox().getAttribute("value").equals(""));

    ValidationUtil.validateTestStep("Verify Zip Code fields are displayed for data entry on Payment Page",
            TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getZipCodeTextbox())
                    && pageObjectManager.getPaymentPage().getZipCodeTextbox().getAttribute("value").equals(""));

    ValidationUtil.validateTestStep("Verify Country fields are displayed for data entry on Payment Page",
            TestUtil.verifyElementDisplayed(pageObjectManager.getPaymentPage().getCountryDropdown())
                    && pageObjectManager.getPaymentPage().getCountryDropdown().getAttribute("value").equals(DEFAULT_COUNTRY_DROPDOWN_VALUE));

    pageObjectManager.getMemberEnrollmentPage().getAddressForBillingTextBox().sendKeys(ContactsDetail.address1);
    pageObjectManager.getMemberEnrollmentPage().getBillingCityTextBox().sendKeys(ContactsDetail.city);

    //Verify State and zip code is Required ONLY when Country is U.S or Canada
    pageObjectManager.getPaymentPage().getBookTripButton().click();

    ValidationUtil.validateTestStep("Verify State is required when Country is U.S or Canada",
            pageObjectManager.getCommon().getErrorMessageLabel().getText(),STATE_REQUIRED_ERROR);

    TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getBillingStateDropDown(),ContactsDetail.state);
    pageObjectManager.getPaymentPage().getBookTripButton().click();

    ValidationUtil.validateTestStep("Verify Zipcode is required when Country is U.S or Canada",
            pageObjectManager.getCommon().getErrorMessageLabel().getText(),ZIPCODE_REQUIRED_ERROR);

    TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPaymentPage().getCountryDropdown(),"Canada");
    pageObjectManager.getPaymentPage().getBookTripButton().click();

    //TODO: Bug "State is required" is not present here.
        ValidationUtil.validateTestStep("Verify State is required when Country is U.S or Canada",
                pageObjectManager.getCommon().getErrorMessageLabel().getText(),STATE_REQUIRED_ERROR);

    TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getBillingStateDropDown(),"Alberta");

    ValidationUtil.validateTestStep("Verify ZipCode is required when Country is U.S or Canada",
            pageObjectManager.getCommon().getErrorMessageLabel().getText(),ZIPCODE_REQUIRED_ERROR);

    TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPaymentPage().getCountryDropdown(),"Aruba");
    pageObjectManager.getPaymentPage().getTermsAndConditionsLabel().click();

    ValidationUtil.validateTestStep("Verify State is not required when Country is not U.S or Canada",
            !TestUtil.verifyElementDisplayed(pageObjectManager.getCommon().getErrorMessageLabel()));
    ValidationUtil.validateTestStep("Verify ZipCode is not required when Country is not U.S or Canada",
            !TestUtil.verifyElementDisplayed(pageObjectManager.getCommon().getErrorMessageLabel()));

    //Verify when Guest does not complete the Address* field, error message should be displayed.
    TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getPaymentPage().getCountryDropdown(),"United States of America");

    pageObjectManager.getPaymentPage().getBookTripButton().click();

    TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getAddressForBillingTextBox());
    TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getBillingCityTextBox());

    WaitUtil.untilTimeCompleted(500);
    ValidationUtil.validateTestStep("Verify Address error message",
            pageObjectManager.getCommon().getErrorMessageLabel().getText(),ADDRESS_REQUIRED_ERROR);
    WaitUtil.untilTimeCompleted(500);
    pageObjectManager.getMemberEnrollmentPage().getAddressForBillingTextBox().sendKeys("23 Parking Street");

    //Verify When City field is not complete error message is displayed
    ValidationUtil.validateTestStep("Verify City error message",
            pageObjectManager.getCommon().getErrorMessageLabel().getText(),CITY_REQUIRED_ERROR);
    WaitUtil.untilTimeCompleted(500);

    pageObjectManager.getMemberEnrollmentPage().getBillingCityTextBox().sendKeys("New York");
    TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getMemberEnrollmentPage().getBillingStateDropDown(),"Florida");

    //Verify When less than 4 characters are entered for zipcode, error message should be displayed
    pageObjectManager.getPaymentPage().getZipCodeTextbox().sendKeys(TestUtil.getAlphaCharacterString(6));
    WaitUtil.untilTimeCompleted(500);
    ValidationUtil.validateTestStep("Verify ZipCode error message",
            pageObjectManager.getCommon().getErrorMessageLabel().getText(),ZIPCODE_INVALID_ERROR);
  }

  //Method for validating Name on Card Textbox for length value for credit cards
  private void ValidatingNameOnCardTxbxMaxlenValforCC(String CardType, int length){
    //string error message
    String INVALID_CARD_LENGTH = "Card holder name length invalid.";
    //get card details
    CardDetailsData CardTypeDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(CardType);
    //clear card number number textbox
    TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox());
    //send card number
    pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox().sendKeys(CardTypeDetailsData.cardNumber);
    //clear Name on card text box
    TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox());
    //sending random keys to trigger error message
    pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox().sendKeys(TestUtil.getAlphaCharacterString(length));
    //Validating error message
    ValidationUtil.validateTestStep("Validating MAX LENGTH error message for " +CardType,
            pageObjectManager.getCommon().getErrorMessageLabel().getText().trim(),INVALID_CARD_LENGTH);
  }

  //Method for Validation Card Number Text box
  private void ValidateCardNumberTxbx(String CardType, String CreditCardBadge, String Length){
    //clearing Card number textbox
    TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox());
    //get card details
    CardDetailsData CardTypeDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(CardType);

    //send card details
    pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox().sendKeys(CardTypeDetailsData.cardNumber);

    //validation credit card badge
    ValidationUtil.validateTestStep("Validating badge for " +CardType,
            pageObjectManager.getMemberEnrollmentPage().getCardTypeImage().getAttribute("class"), CreditCardBadge);
    String MasterCardLengthVal = Integer.toString(pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox().getAttribute("value").trim().replaceAll(" ", "").length());

    //validation credit card length
    ValidationUtil.validateTestStep("Validate Length value for Creditcard " + CardType, MasterCardLengthVal, Length);
  }

  //Method for Validation Security Code max length
  private void ValidateSecurityCodeMaxLength(String CardType, String MaxLength, String SecurityCode){
    //Error messages
    String SecurityCodeErrorMessage = "The Credit Card security code must be at least 3 numeric characters";
    String SecurityCodeErrorMessageAmex = "The Credit Card security code must be at least 4 numeric characters";

    //Clearing Card Number text box
    TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox());
    TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox());
    TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getMemberEnrollmentPage().getExpirationMonthYearTextBox());
    //get card details
    CardDetailsData CardTypeDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(CardType);

    //send card details
    pageObjectManager.getMemberEnrollmentPage().getAccountHolderNameTextBox().sendKeys(CardTypeDetailsData.cardHolderName);
    pageObjectManager.getMemberEnrollmentPage().getCardNumberTextBox().sendKeys(CardTypeDetailsData.cardNumber);
    pageObjectManager.getMemberEnrollmentPage().getExpirationMonthYearTextBox().sendKeys(CardTypeDetailsData.expirationDate);
    //validation for security code max length
    ValidationUtil.validateTestStep("Validating Security code max length for " + CardType,
            pageObjectManager.getMemberEnrollmentPage().getSecurityCodeTextBox().getAttribute("maxlength"), MaxLength);
    pageObjectManager.getMemberEnrollmentPage().getSecurityCodeTextBox().sendKeys(SecurityCode);
    pageObjectManager.getPaymentPage().getBookTripButton().click();

    //validation for error message
    if(CardType =="AmericanExpressCard"){
      ValidationUtil.validateTestStep("Validating Security code Min length Error for " + CardType,
              pageObjectManager.getCommon().getErrorMessageLabel().getText(),SecurityCodeErrorMessageAmex);
    }
    else{
      ValidationUtil.validateTestStep("Validating Security code Min length Error for " + CardType,
              pageObjectManager.getCommon().getErrorMessageLabel().getText(),SecurityCodeErrorMessage);
    }
  }
}
