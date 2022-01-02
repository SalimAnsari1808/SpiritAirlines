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

public class Cancel_Hotel_Check_In extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = "")
    public void Cancel_Hotel_Check_In(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case Cancel_Hotel_Check_In " + platform + " Browser", true);
        }
        final String HOTEL_LOGIN        = "HotelBedsLogin";
        final String USER_NAME          = "Test";
        final String OTHERS             = "Others";
        final String OTHERS_TEXT_BOX    = "Test";
        final String SUCCESSFULL_MESSAGE = "Your booking has been cancelled successfully";

        int DATE_INI              = 0;
        int DATE_LAST             = 29;
        int COUNT                 = 3;

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
        WaitUtil.untilJavaScriptPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1000);

        while (COUNT>0){
            //Filtering for specific user
            pageObjectManager.getTriseptREZAgentPage().getHBGNameSearchTextBox().sendKeys(USER_NAME);

            //Filtering search to reservation creation
            //Booking confirmation
            //Check in
            //Cancellation Charges
            TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateTypeTextBox(),"Check in");

            //Filtering search per date rage
            pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateFromTextBox().clear();
            pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateFromTextBox().sendKeys(TestUtil.getStringDateFormat(DATE_INI, "dd/MM/yyyy"));
            TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateToTextBox());
            pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateToTextBox().sendKeys(TestUtil.getStringDateFormat(DATE_LAST, "dd/MM/yyyy"));
            pageObjectManager.getTriseptREZAgentPage().getHBGBookingDestinationTextBox().click();

            //Filtering for only confirmed reservation
            TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getTriseptREZAgentPage().getHBGBookingStatusDropDown(), "Non-cancelled");

            //Search reservations
            pageObjectManager.getTriseptREZAgentPage().getHBGSearchButton().click();
            WaitUtil.untilJavaScriptPageLoadComplete(getDriver());
            WaitUtil.untilTimeCompleted(1000);

            if (!TestUtil.verifyElementDisplayed(pageObjectManager.getTriseptREZAgentPage().getHBGRetrieveBookingCancelAllText())) {
                DATE_INI  +=30;
                System.out.println("DATE_INI: " +DATE_INI );
                DATE_LAST +=30;
                System.out.println("DATE_LAST: " +DATE_LAST );
                COUNT--;
                System.out.println("COUNT: " +COUNT );
                pageObjectManager.getTriseptREZAgentPage().getHBGNameSearchTextBox().clear();
            }else {
                int reservation_Amount = Integer.parseInt(getDriver().findElement(By.xpath("//div[@id='booking-summary-module']//h3")).getText().substring(0, 1));
                System.out.println("reservation_Amount: " + reservation_Amount);

                while (reservation_Amount>0) {
                    pageObjectManager.getTriseptREZAgentPage().getHBGRetrieveBookingCancelAllText().get(0).click();
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

                    //Filtering for specific user
                    pageObjectManager.getTriseptREZAgentPage().getHBGNameSearchTextBox().sendKeys(USER_NAME);

                    //Filtering search to reservation creation
                    TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateTypeTextBox(),"Booking confirmation");

                    //Filtering search per date rage
                    pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateFromTextBox().clear();
                    pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateFromTextBox().sendKeys(TestUtil.getStringDateFormat(DATE_INI, "dd/MM/yyyy"));
                    TestUtil.clearTextBoxUsingSendKeys(getDriver(),pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateToTextBox());
                    pageObjectManager.getTriseptREZAgentPage().getHBGBookingDateToTextBox().sendKeys(TestUtil.getStringDateFormat(DATE_LAST, "dd/MM/yyyy"));
                    pageObjectManager.getTriseptREZAgentPage().getHBGBookingDestinationTextBox().click();

                    //Filtering for only confirmed reservation
                    TestUtil.selectDropDownUsingVisibleText(pageObjectManager.getTriseptREZAgentPage().getHBGBookingStatusDropDown(), "Non-cancelled");

                    //Search reservations
                    pageObjectManager.getTriseptREZAgentPage().getHBGSearchButton().click();
                    WaitUtil.untilJavaScriptPageLoadComplete(getDriver());
                    WaitUtil.untilTimeCompleted(1000);

                }

            }
        }
    }
}