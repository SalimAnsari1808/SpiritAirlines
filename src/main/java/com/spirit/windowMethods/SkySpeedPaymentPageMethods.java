package com.spirit.windowMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataType.CardDetailsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.managers.WindowObjectManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import com.spirit.windowObjects.SkySpeedPaymentPage;
import io.appium.java_client.windows.WindowsDriver;

public class SkySpeedPaymentPageMethods {
    private WindowsDriver       driver;
    private WindowObjectManager windowObjectManager;
    private ScenarioContext     scenarioContext;
    private SkySpeedPaymentPage skySpeedPaymentPage;

    public SkySpeedPaymentPageMethods(WindowsDriver driver, WindowObjectManager windowObjectManager, ScenarioContext scenarioContext){
        this.driver                 = driver;
        this.scenarioContext        = scenarioContext;
        this.windowObjectManager    = windowObjectManager;
        skySpeedPaymentPage         = windowObjectManager.getSkySpeedPaymentPage();
    }




    //CreateVoucher
    //click on CreateVoucherButton
    //click on ShowButton
    //select voucher type from VouchersTypeList "60BCS"
    //Set PNR at reservation number text box
    //Set the voucher value - Maximum value of voucher can be 500 only
    //Set the comments value
    //Reason for voucher Baggage Claim Settlement Voucher (BCS)
    //Set the first name
    //Set the Last name
    //Click on Create voucher button
    //store the voucher number

    //CancelBooking
    //get PaymentSummaryList before cancel booking
    //click on ReverseBookingButton button
    //click on SavePayment button
    //Get payment list after reverse or add resevation credit
    //Make sure the count After reservation is greater than before reservation

    //CreateReservationCredit
    //get PaymentSummaryList before cancel booking
    //Click on Add Reservation button
    //Select credit option as "Reservation Credit"
    //Set reason for reservaiton
    //Click save button
    //Get payment list after reverse or add resevation credit
    //Make sure the count After reservation is grerater than before reservation
    //store Reservation Credit

    //**********************************************************************************************
    //Method Name: cashPayment
    //Description: Method is used to do booking payment using Cash
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 29-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void cashPayment(){

        //Get the visible text in payment summary
        String totalDueAmount = skySpeedPaymentPage.getTotalDueAmountText().getText().replace(",","");

        //check total due amount is greater than zero
        if(!(Double.parseDouble(totalDueAmount)>0)){
            ValidationUtil.validateTestStep("Cash Payment method is not applicable as Total Due Amount is zero", false);
        }

        //Set the option as cash "Cash (CA\PrePaid)"
        skySpeedPaymentPage.getPaymentMethodDropDown().sendKeys("Cash (CA\\PrePaid)");

        //Click on next button
        //skySpeedPaymentPage.getNextButton().click();

        //click on save payment button
        skySpeedPaymentPage.getSavePaymentButton().click();

        ValidationUtil.validateTestStep("Booking Payment is completed in Cash on Payment Page", true);
    }


    //**********************************************************************************************
    //Method Name: voucherPayment
    //Description: Method is used to do booking partial payment using Voucher
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 29-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void voucherPayment(){

        //Get the visible text in payment summary
        String totalDueAmount = skySpeedPaymentPage.getTotalDueAmountText().getText().replace(",","");

        //check total due amount is greater than zero
        if(!(Double.parseDouble(totalDueAmount)>0)){
            ValidationUtil.validateTestStep("Voucher Payment method is not applicable as Total Due Amount is zero", false);
        }

        //Set the option as voucher "Voucher (VO\\Voucher)"
        skySpeedPaymentPage.getPaymentMethodDropDown().sendKeys("Voucher (VO\\Voucher)");

        //Click on next button
        skySpeedPaymentPage.getNextButton().click();

        //enter voucher number
        skySpeedPaymentPage.getVoucherNumberTextBox().sendKeys("");

        //enter voucher Code
        skySpeedPaymentPage.getVoucherCodeTextBox().sendKeys("");

    }

    //**********************************************************************************************
    //Method Name: reservationCreditPayment
    //Description: Method is used to do booking partial payment using Reservation credit
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 29-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void reservationCreditPayment(){

        //Get the visible text in payment summary
        String totalDueAmount = skySpeedPaymentPage.getTotalDueAmountText().getText().replace(",","");

        //check total due amount is greater than zero
        if(!(Double.parseDouble(totalDueAmount)>0)){
            ValidationUtil.validateTestStep("Reservation Credit Payment method is not applicable as Total Due Amount is zero", false);
        }

        //Set the option as reservation credit Credit File (CF\CustomerAccount)
        skySpeedPaymentPage.getPaymentMethodDropDown().sendKeys("Credit File (CF\\CustomerAccount)");

        //Click on next button
        skySpeedPaymentPage.getNextButton().click();

        //select reservation credit
        skySpeedPaymentPage.getReservationCreditAccountTypeDropDown().sendKeys("Reservation Credit");

        //enter reservation credit
        skySpeedPaymentPage.getReservationCreditAccountNumberTextBox().sendKeys("");
    }

    //**********************************************************************************************
    //Method Name: cardPayment
    //Description: Method is used to do booking partial payment using Card(Master, Visa, AmEx, Discover)
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 29-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void cardPayment(String cardType){
        String cardValue = "";

        CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(cardType);

        if(cardType.equalsIgnoreCase("MasterCard")){
            cardValue = "MasterCard (MC\\ExternalAccount)";
        }else if(cardType.equalsIgnoreCase("VisaCard")){
            cardValue = "Visa (VI\\ExternalAccount)";
        }else if(cardType.equalsIgnoreCase("AmericanExpressCard")){
            cardValue = "American Express (AX\\ExternalAccount)";
        }else if(cardType.contains("DiscoverCard")){
            cardValue = "Discover (DS\\ExternalAccount)";
        }

        //Get the visible text in payment summary
        String totalDueAmount = skySpeedPaymentPage.getTotalDueAmountText().getText().replace(",","");

        //check total due amount is greater than zero
        if(!(Double.parseDouble(totalDueAmount)>0)){
            ValidationUtil.validateTestStep("Reservation Credit Payment method is not applicable as Total Due Amount is zero", false);
        }

        //Click on next button
        skySpeedPaymentPage.getNextButton().click();

        //Select the required type of payment
        skySpeedPaymentPage.getPaymentMethodDropDown().sendKeys(cardValue);

        //Click on next button to make payment option visible
        skySpeedPaymentPage.getNextButton().click();

        //Click on Use contact address button to fill pre filled values
        skySpeedPaymentPage.getUseContactAddressButton().click();

        //Fill the card number
        skySpeedPaymentPage.getAccountNumberTextBox().sendKeys(cardDetailsData.cardNumber);

        //Fill the expiry month
        skySpeedPaymentPage.getExpiryMonthTextBox().sendKeys(cardDetailsData.expirationDate.split("/")[0]);

        //Fill the expiry year
        skySpeedPaymentPage.getExpiryYearTextBox().sendKeys("20"+cardDetailsData.expirationDate.split("/")[1]);

        //Fill the CVV number
        skySpeedPaymentPage.getCVVTextBox().sendKeys(cardDetailsData.securityCode);

        //Fill the AccountHolderName number
        skySpeedPaymentPage.getAccountHolderNameTextBox().sendKeys(cardDetailsData.cardHolderName);

        //Fill the phone number
        skySpeedPaymentPage.getHomePhoneNumberTextBox().sendKeys("7834862764");

        //Fill Email ID "emailtesters@spirit.com"
        skySpeedPaymentPage.getEmailD1TextBox().sendKeys("emailtesters@spirit.com");

        skySpeedPaymentPage.getEmailD2TextBox().sendKeys("emailtesters@spirit.com");

        //click on save button
        skySpeedPaymentPage.getSavePaymentButton().click();

        WaitUtil.untilSkySpeedPageLoadComplete(driver);
    }

    //**********************************************************************************************
    //Method Name: clickNextPaymentPage
    //Description: Method is used to click on Next button on Payment Page
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 29-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void clickNextPaymentPage(){
        //click on Next button
        skySpeedPaymentPage.getNextButton().click();

        //click on next button
        ValidationUtil.validateTestStep("Clicked on Next Button on Payment Page", true);
    }



}
