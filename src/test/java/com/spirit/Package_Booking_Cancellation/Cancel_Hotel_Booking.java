package com.spirit.Package_Booking_Cancellation;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


public class Cancel_Hotel_Booking extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = "BookingPackage")
    public void Cancel_Hotel_Booking(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case Cancel_Hotel_Booking " + platform + " Browser", true);
        }

        final String HOTEL_LOGIN        = "HotelBedsLogin";
        final String OTHERS             = "Others";
        final String OTHERS_TEXT_BOX    = "Test";
        final String SUCCESSFULL_MESSAGE = "Your booking has been cancelled successfully";
        final String USER_NAME_LIST     = "Test";
        List<String> DATE_DROP_DOWN     = Arrays.asList("Booking confirmation", "Check in");

        int DATE_INI                    = -60;
        int DATE_LAST                   = -30;
        int COUNT                       = 3;

        //open browser
        openBrowser(platform);

        //open new tab
        JSExecuteUtil.openNewTabWithGivenURL(getDriver(), FileReaderManager.getInstance().getConfigReader().getHotelProviderURL());
        TestUtil.switchToWindow(getDriver(),1);

        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(HOTEL_LOGIN);

        pageObjectManager.getTriseptREZAgentPage().getHBGUserNameTextBox().sendKeys(loginCredentialsData.userName);
        pageObjectManager.getTriseptREZAgentPage().getHBGPasswordTextBox().sendKeys(loginCredentialsData.password);

        pageObjectManager.getTriseptREZAgentPage().getHBGLoginButton().click();

        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("User login to Hotel Beds Website",
                getDriver().findElements(By.xpath(pageObjectManager.getTriseptREZAgentPage().xpath_HBGConfirmationCodeTextBox)).size()>0);

        //moving to the search reservations page
        pageObjectManager.getTriseptREZAgentPage().getHBGHeaderBookingLink().click();
        System.out.println("User select bookings options");
        WaitUtil.untilJavaScriptPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);

        for (int e = 0; e < DATE_DROP_DOWN.size(); e++) {
            TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateTypeTextBox(), DATE_DROP_DOWN.get(e));
            System.out.println("Selecting " + DATE_DROP_DOWN.get(e) + " from date dropdown");

            if (DATE_DROP_DOWN.get(e).equals("Check in")) {
                DATE_INI = 0;
                DATE_LAST = 30;
                COUNT = 5;
            }

            while (COUNT > 0) {
                //Filtering search per date rage
                pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateFromTextBox().clear();
                pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateFromTextBox().sendKeys(TestUtil.getStringDateFormat(DATE_INI, "dd/MM/yyyy"));
                TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateToTextBox());
                pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateToTextBox().sendKeys(TestUtil.getStringDateFormat(DATE_LAST, "dd/MM/yyyy"));
                pageObjectManager.getTriseptREZAgentPage().getHBGBookingDestinationTextBox().click();

                //Filtering for only confirmed reservation
                TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getTriseptREZAgentPage().getHBGBookingStatusDropDown(), "Non-cancelled");
                System.out.println("Filtering per Non-Cancelled reservations");

                //Filtering for specific user
                pageObjectManager.getTriseptREZAgentPage().getHBGNameSearchTextBox().clear();
                pageObjectManager.getTriseptREZAgentPage().getHBGNameSearchTextBox().sendKeys(USER_NAME_LIST);
                System.out.println("Filtering Test as Passenger's last name");

                //Search reservations
                pageObjectManager.getTriseptREZAgentPage().getHBGSearchButton().click();
                WaitUtil.untilPageLoadComplete(getDriver(),(long)20);
                WaitUtil.untilTimeCompleted(200);

                if (!TestUtil.verifyElementDisplayed(pageObjectManager.getTriseptREZAgentPage().getHBGRetrieveBookingCancelAllText())) {
                    DATE_INI += 30;
                    System.out.println("DATE_INI: " + DATE_INI);
                    DATE_LAST += 30;
                    System.out.println("DATE_LAST: " + DATE_LAST);
                    COUNT--;
                    System.out.println("COUNT: " + COUNT);
                    pageObjectManager.getTriseptREZAgentPage().getHBGNameSearchTextBox().clear();
                } else {
                    for (int d = 0; d < pageObjectManager.getTriseptREZAgentPage().getHBGRetrieveBookingCancelAllText().size(); d++) {
                        System.out.println("Looking for Automation name on hotel reservation list");

                        if (pageObjectManager.getTriseptREZAgentPage().getHBGRetrievedPaxNameText().get(d).getText().contains("AUTOMATION") &&
                                TestUtil.verifyElementDisplayed(pageObjectManager.getTriseptREZAgentPage().getHBGRetrieveBookingCancelAllText().get(d))) {
                            String paxName = pageObjectManager.getTriseptREZAgentPage().getHBGRetrievedPaxNameText().get(d).getText();
                            System.out.println(paxName + " passenger found");

                            pageObjectManager.getTriseptREZAgentPage().getHBGRetrieveBookingCancelAllText().get(d).click();
                            System.out.println("Cancelling hotel reservation for " + paxName);
                            WaitUtil.untilJavaScriptPageLoadComplete(getDriver());
                            WaitUtil.untilTimeCompleted(1000);

                            //Accept cancellation check box
                            pageObjectManager.getTriseptREZAgentPage().getHBGSelectedHotelAcceptCancellationCheckBox().click();

                            //Cancel booking button
                            pageObjectManager.getTriseptREZAgentPage().getHBGCancelBookingButton().click();
                            WaitUtil.untilJavaScriptPageLoadComplete(getDriver());

                            for (int f = 0; f < pageObjectManager.getTriseptREZAgentPage().getHBGSelectedHotelCancelReasonRadioButton().size(); f++) {
                                if (pageObjectManager.getTriseptREZAgentPage().getHBGSelectedHotelCancelReasonRadioButton().get(f).getText().equals(OTHERS)) {
                                    pageObjectManager.getTriseptREZAgentPage().getHBGSelectedHotelCancelReasonRadioButton().get(f).click();
                                    WaitUtil.untilTimeCompleted(1200);
                                }
                            }

                            pageObjectManager.getTriseptREZAgentPage().getHBGOthersTextTextBox().sendKeys(OTHERS_TEXT_BOX);
                            pageObjectManager.getTriseptREZAgentPage().getHBGSelectedHotelConfirmCancelButton().click();
                            WaitUtil.untilJavaScriptPageLoadComplete(getDriver());

                            ValidationUtil.validateTestStep("Validating hotel reservation was successfully cancelled",
                                    pageObjectManager.getTriseptREZAgentPage().getHBGSelectedHotelCancelConfirmationMessageText().getText(), SUCCESSFULL_MESSAGE);

                            //moving to the search reservations page
                            pageObjectManager.getTriseptREZAgentPage().getHBGHeaderBookingLink().click();
                            WaitUtil.untilJavaScriptPageLoadComplete(getDriver());
                            WaitUtil.untilTimeCompleted(1000);

                            //Filtering search to reservation creation
                            TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateTypeTextBox(), DATE_DROP_DOWN.get(e));
                            System.out.println("Selecting " + DATE_DROP_DOWN.get(e) + " from date dropdown");

                            //Filtering search per date rage
                            pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateFromTextBox().clear();
                            pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateFromTextBox().sendKeys(TestUtil.getStringDateFormat(Long.toString(DATE_INI), "dd/MM/yyyy"));
                            TestUtil.clearTextBoxUsingSendKeys(getDriver(), pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateToTextBox());
                            pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateToTextBox().sendKeys(TestUtil.getStringDateFormat(Long.toString(DATE_LAST), "dd/MM/yyyy"));
                            pageObjectManager.getTriseptREZAgentPage().getHBGBookingDestinationTextBox().click();

                            //Filtering for only confirmed reservation
                            TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getTriseptREZAgentPage().getHBGBookingStatusDropDown(), "Non-cancelled");

                            //Filtering for specific user
                            pageObjectManager.getTriseptREZAgentPage().getHBGNameSearchTextBox().clear();
                            pageObjectManager.getTriseptREZAgentPage().getHBGNameSearchTextBox().sendKeys(USER_NAME_LIST);
                            System.out.println("Filtering Test as Passenger's last name");

                            //Search reservations
                            pageObjectManager.getTriseptREZAgentPage().getHBGSearchButton().click();
                            WaitUtil.untilJavaScriptPageLoadComplete(getDriver());
                            WaitUtil.untilPageLoadComplete(getDriver(),(long)20);
                            WaitUtil.untilTimeCompleted(200);
                        }

                    }
                    DATE_INI += 30;
                    System.out.println("DATE_INI: " + DATE_INI);
                    DATE_LAST += 30;
                    System.out.println("DATE_LAST: " + DATE_LAST);
                    COUNT--;
                    System.out.println("COUNT: " + COUNT);
                }
            }
        }

    }
}

///********************TriseptREZAgentPage
//public final String xpath_HBGConfirmedLink = "//ul[@class='bookings-shortcuts']//strong[contains(text(),'Confirmed')]";
//    @FindBy(xpath=xpath_HBGConfirmedLink)
//    private WebElement lnk_HBGConfirmed;

//    public final String xpath_HBGNameSearchTextBox = "//input[@id='holdersurname']";
//    @FindBy(xpath=xpath_HBGNameSearchTextBox)
//    private WebElement txtbx_HBGNameSearch;

//    public final String xpath_HBGSearchButton = "//input[@id='bookingSearchBtn']";
//    @FindBy(xpath=xpath_HBGSearchButton)
//    private WebElement btn_HBGSearch;

//    public final String xpath_HBGCancelBookingButton = "//input[@id='cancelBookingBtn']";
//    @FindBy(xpath=xpath_HBGCancelBookingButton)
//    private WebElement btn_HBGCancelBooking;

//    public final String xpath_HBGOthersTextTextBox = "//textarea[@id='cancellationReasonText']";
//    @FindBy(xpath=xpath_HBGOthersTextTextBox)
//    private WebElement txtbx_HBGOthersText;

//    public WebElement getHBGConfirmedLink(){return lnk_HBGConfirmed;}
//public WebElement getHBGNameSearchTextBox(){return txtbx_HBGNameSearch;}
//public WebElement getHBGSearchButton(){return btn_HBGSearch;}
//public WebElement getHBGCancelBookingButton(){return btn_HBGCancelBooking;}
//public WebElement getHBGOthersTextTextBox(){return txtbx_HBGOthersText;}