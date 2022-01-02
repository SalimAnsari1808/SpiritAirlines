package com.spirit.pageMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.enums.Context;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.CancelReservationPage;
import com.spirit.pageObjects.HomePage;
import com.spirit.pageObjects.PaymentPage;
import com.spirit.pageObjects.ReservationSummaryPage;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class ReservationSummaryPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private PaymentPage paymentPage;
    private ReservationSummaryPage reservationSummaryPage;
    private HomePage homePage;
    private CancelReservationPage cancelReservationPage;

    public ReservationSummaryPageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
        this.driver				= driver;
        this.scenarioContext  	= scenarioContext;
        paymentPage 			= pageObjectManager.getPaymentPage();
        reservationSummaryPage	= pageObjectManager.getReservationSummaryPage();
        homePage                = pageObjectManager.getHomePage();
        cancelReservationPage   = pageObjectManager.getCancelReservationPage();
    }

    //**********************************************************************************************
    // Method : clickCheckInAndGetBoardingPass
    // Description: Method is used to select CheckIn And Get Boarding Pass Button
    // Input Arguments: NA
    // Return: NA
    // Created By : Anthony
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void clickCheckInAndGetBoardingPass(){
        //wait for page load to complete
        WaitUtil.untilPageLoadComplete(driver);

        //click on checkin button
        reservationSummaryPage.getCheckInAndGetBoardingPassButton().get(0).click();

        //wait for page load to complete
        WaitUtil.untilPageLoadComplete(driver);

        //verify new dep city s selected
        ValidationUtil.validateTestStep("User CheckIn And Get Boarding Pass Button on Reservation Summary Page",true);
    }

    //**********************************************************************************************
    // Method : cancelItineraryButton
    // Description: Method is used to select Cancel Itinerary Button
    // Input Arguments: NA
    // Return: NA
    // Created By : Anthony
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void cancelItineraryButton(){
        //click on checkin button
        reservationSummaryPage.getCancelItineraryButton().click();

        //wait for page load to complete
        WaitUtil.untilPageLoadComplete(driver);

        //verify new dep city s selected
        ValidationUtil.validateTestStep("User Cancel Itinerary Button on Reservation Summary Page",true);
    }

    //**********************************************************************************************
    // Method : buyBagsSeatsPassengerSection
    // Description: Method is used to select add or Edit Bags and Seat on Passenger Section
    // Input Arguments: NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void buyBagsSeatsPassengerSection(String buyBagSeat){
        //declare constant used in method
        int FIRST_PAX   = 0;

        //check for Bags and Seats
        if(buyBagSeat.equalsIgnoreCase("Bags")){
            if(TestUtil.verifyElementDisplayed(reservationSummaryPage.getPassengerSectionAddBagsButton())){
                //click on add bags
                reservationSummaryPage.getPassengerSectionAddBagsButton().get(FIRST_PAX).click();
            }else if(TestUtil.verifyElementDisplayed(reservationSummaryPage.getPassengerSectionEditBagsButton())){
                //click on Edit Bags
                reservationSummaryPage.getPassengerSectionEditBagsButton().get(FIRST_PAX).click();
            }
        }else if(buyBagSeat.equalsIgnoreCase("Seats")){
            if(TestUtil.verifyElementDisplayed(reservationSummaryPage.getPassengerSectionAddSeatsButton())){
                //click on Add Seat
                reservationSummaryPage.getPassengerSectionAddSeatsButton().get(FIRST_PAX).click();
            }else if(TestUtil.verifyElementDisplayed(reservationSummaryPage.getPassengerSectionEditSeatsButton())){
                //click on Edit Seat
                reservationSummaryPage.getPassengerSectionEditSeatsButton().get(FIRST_PAX).click();
            }
        }

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //verify new dep city s selected
        ValidationUtil.validateTestStep("User Selected " + buyBagSeat + " In Passenger Section on Reservation Summary Page",true);
    }

    //**********************************************************************************************
    // Method : changeFlightOnChangeFlightPopup
    // Description: Method is used to select update source destination date for dep and ret flight on Reservation Summary page
    // Input Arguments: NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void changeFlightOnChangeFlightPopup(String journey, String depCode, String retCode, String travellingDate){

        //check change flight popup is displayed
        if(!TestUtil.verifyElementDisplayed(driver, By.xpath(reservationSummaryPage.xpath_ChangeFlightPopupDepEditLabel))){
            //click on change flight link
            reservationSummaryPage.getFlightSectionChangeFlightButton().click();
        }

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        if(journey.equalsIgnoreCase("Dep")){
            //click on Chnage dep flight checkbox
            reservationSummaryPage.getChangeFlightPopupDepEditLabel().click();

            //check departure airport is required
            if(!depCode.equalsIgnoreCase("NA")){
                //get dep airports
                Select drpdnOrigin = new Select(reservationSummaryPage.getChangeFlightPopupDepFromCityDropDown());

                //loop through all airports
                for(WebElement selectedOrigin : drpdnOrigin.getOptions()){
                    //check departure airport code is present
                    if(selectedOrigin.getAttribute("value").contains(depCode)){
                        //select departure airport
                        drpdnOrigin.selectByVisibleText(selectedOrigin.getText());

                        //save new dep airport in global variable
                        scenarioContext.setContext(Context.HOMEPAGE_DEP_AIRPORT,selectedOrigin.getText());

                        //wait for 1 sec
                        WaitUtil.untilTimeCompleted(1000);

                        //verify new dep city s selected
                        ValidationUtil.validateTestStep("User Selected New Depature Airport " + selectedOrigin.getText() + " on Reservation Summary Page",drpdnOrigin.getFirstSelectedOption().getText().equalsIgnoreCase(selectedOrigin.getText()));

                        //break loop
                        break;
                    }
                }
            }

            //check return airport is required
            if(!retCode.equalsIgnoreCase("NA")){
                //get ret airports
                Select drpdnDestination = new Select(reservationSummaryPage.getChangeFlightPopupDepToCityDropDown());

                //loop through all airports code
                for(WebElement selectedDestination : drpdnDestination.getOptions()){
                    //check return airport code is present
                    if(selectedDestination.getAttribute("value").contains(retCode)){
                        //select return airport
                        drpdnDestination.selectByVisibleText(selectedDestination.getText());

                        //save new dep airport in global variable
                        scenarioContext.setContext(Context.HOMEPAGE_ARR_AIRPORT,selectedDestination.getText());

                        //wait for 1 sec
                        WaitUtil.untilTimeCompleted(1000);

                        //verify new dep city s selected
                        ValidationUtil.validateTestStep("User Selected New Return Airport " + selectedDestination.getText() + " on Reservation Summary Page",drpdnDestination.getFirstSelectedOption().getText().equalsIgnoreCase(selectedDestination.getText()));

                        //break loop
                        break;
                    }
                }
            }

            //select departure date
            if(!travellingDate.equalsIgnoreCase("NA")){
                //declare variable used in method
                String strActualDepDate;

                //get the dep date
                strActualDepDate = TestUtil.getStringDateFormat(travellingDate, "MM/dd/yyyy");

                //clear previous dates
                TestUtil.clearTextBoxUsingSendKeys(driver, reservationSummaryPage.getChangeFlightPopupDepDateTextBox());

                //enter new dates
                reservationSummaryPage.getChangeFlightPopupDepDateTextBox().sendKeys(strActualDepDate);

                //validate depart date
                ValidationUtil.validateTestStep("User Selected Departure Date as "+ strActualDepDate + " on Reservation Summary Page", true);

                //store dep date
                scenarioContext.setContext(Context.HOMEPAGE_DEP_DATE, travellingDate);
            }


        }else if(journey.equalsIgnoreCase("Ret")){
            //click on return checkbox
            reservationSummaryPage.getChangeFlightPopupRetEditLabel().click();

            //check departure airport is required
            if(!depCode.equalsIgnoreCase("NA")){
                //get dep airports
                Select drpdnOrigin = new Select(reservationSummaryPage.getChangeFlightPopupRetFromCityDropDown());

                //loop through all airports
                for(WebElement selectedOrigin : drpdnOrigin.getAllSelectedOptions()){
                    //check departure airport code is present
                    if(selectedOrigin.getAttribute("value").contains(depCode)){
                        //select departure airport
                        drpdnOrigin.selectByVisibleText(selectedOrigin.getText());

                        //save new dep airport in global variable
                        scenarioContext.setContext(Context.HOMEPAGE_ARR_AIRPORT,selectedOrigin.getText());

                        //wait for 1 sec
                        WaitUtil.untilTimeCompleted(1000);

                        //verify new dep city s selected
                        ValidationUtil.validateTestStep("User Selected New Return Airport " + selectedOrigin.getText() + " on Reservation Summary Page",drpdnOrigin.getFirstSelectedOption().getText().equalsIgnoreCase(selectedOrigin.getText()));

                        //break loop
                        break;
                    }
                }
            }

            //check return airport is required
            if(!retCode.equalsIgnoreCase("NA")){
                //get ret airports
                Select drpdnDestination = new Select(reservationSummaryPage.getChangeFlightPopupRetToCityDropDown());

                //loop through all airports code
                for(WebElement selectedDestination : drpdnDestination.getAllSelectedOptions()){
                    //check return airport code is present
                    if(selectedDestination.getAttribute("value").contains(retCode)){
                        //select return airport
                        drpdnDestination.selectByVisibleText(selectedDestination.getText());

                        //save new dep airport in global variable
                        scenarioContext.setContext(Context.HOMEPAGE_DEP_AIRPORT,selectedDestination.getText());

                        //wait for 1 sec
                        WaitUtil.untilTimeCompleted(1000);

                        //verify new dep city s selected
                        ValidationUtil.validateTestStep("User Selected New Departure Airport " + selectedDestination.getText() + " on Reservation Summary Page",drpdnDestination.getFirstSelectedOption().getText().equalsIgnoreCase(selectedDestination.getText()));

                        //break loop
                        break;
                    }
                }
            }

            //select return airport
            if(!travellingDate.equalsIgnoreCase("NA")){
                //declare variable used in method
                String strActualDepDate;

                //get the dep date
                strActualDepDate = TestUtil.getStringDateFormat(travellingDate, "MM/dd/yyyy");

                //clear previous dates
                TestUtil.clearTextBoxUsingSendKeys(driver, reservationSummaryPage.getChangeFlightPopupDepDateTextBox());

                //enter new dates
                reservationSummaryPage.getChangeFlightPopupDepDateTextBox().sendKeys(strActualDepDate);

                //validate depart date
                ValidationUtil.validateTestStep("User Selected Return Date as "+ strActualDepDate + " on Reservation Summary Page", true);

                //store dep date
                scenarioContext.setContext(Context.HOMEPAGE_ARR_DATE, travellingDate);
            }
        }
    }

    //**********************************************************************************************
    // Method : continueCancelOnChangeFlightPopup
    // Description: Method is used to select continue or cancel button on Reservation Summary page
    // Input Arguments: NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void continueCancelOnChangeFlightPopup(String selectButton){
        //check for contiue
        if(selectButton.equalsIgnoreCase("Continue")){
            //click on continue burron
            reservationSummaryPage.getChangeFlightPopupContinueButton().click();
        }else if(selectButton.equalsIgnoreCase("Cancel")){
            //click on cancel button
            reservationSummaryPage.getChangeFlightPopupCancelButton().click();
        }

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //validate step
        ValidationUtil.validateTestStep("User " + selectButton + " Button on Change Flight Popup on Reservation Summary Page",true);
    }

    //**********************************************************************************************
    // Method : purchaseDontPurchaseOnBagsPopup
    // Description: Method is used to select Bags on Bags Popup on Reservation Summary page
    // Input Arguments: NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void purchaseDontPurchaseOnBagsPopup(String selectButton){

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //check popup appear on page
        if(!TestUtil.verifyElementDisplayed(driver,By.xpath(reservationSummaryPage.xpath_BagsPopupPurchaseMyBagsButton))){
            ValidationUtil.validateTestStep("Bags Popup is not appear on Reservation Summary Page",false);
        }

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //check Bags are required
        if(selectButton.equalsIgnoreCase("Purchase")){
            //click on purchase Bags
            reservationSummaryPage.getBagsPopupPurchaseMyBagsButton().click();
        }else if(selectButton.equalsIgnoreCase("DontPurchase")){
            //click on Dont purchase Bags
            reservationSummaryPage.getBagsPopupDontPurchaseMyBagsButton().click();
        }

        //validate step
        ValidationUtil.validateTestStep("User " + selectButton + " Bags on Bags Popup on Reservation Summary Page",true);
    }

    //**********************************************************************************************
    // Method : purchaseDontPurchaseOnSeatsPopup
    // Description: Method is used to select seats on Seats Popup on Reservation Summary page
    // Input Arguments: NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void purchaseDontPurchaseOnSeatsPopup(String selectButton){

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //check popup appear on page
        if(!TestUtil.verifyElementDisplayed(driver,By.xpath(reservationSummaryPage.xpath_SeatsPopupPurchaseMySeatsButton))){
            ValidationUtil.validateTestStep("Seats Popup is not appear on Reservation Summary Page",false);
        }

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //check Seats are required
        if(selectButton.equalsIgnoreCase("Purchase")){
            //click on purchase Bags
            reservationSummaryPage.getSeatsPopupPurchaseMySeatsButton().click();
        }else if(selectButton.equalsIgnoreCase("DontPurchase")){
            //click on Dont purchase Bags
            reservationSummaryPage.getSeatsPopupDontPurchaseMySeatsButton().click();
        }

        //validate step
        ValidationUtil.validateTestStep("User " + selectButton + " Seat on Seat Popup on Reservation Summary Page",true);
    }

    //**********************************************************************************************
    // Method : purchaseDontPurchaseSaveOnBagsPopup
    // Description: Method is used to select Bags on Bags final popup on Reservation Summary page
    // Input Arguments: NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void purchaseDontPurchaseSaveOnBagsPopup(String selectButton){

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //check popup appear on page
        if(!TestUtil.verifyElementDisplayed(driver,By.xpath(reservationSummaryPage.xpath_SaveOnBagsPopupBuyBagsNowButton))){
            ValidationUtil.validateTestStep("Save On Bags Popup is not appear on Reservation Summary Page",false);
        }

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //check Seats are required
        if(selectButton.equalsIgnoreCase("Purchase")){
            //click on purchase Bags
            reservationSummaryPage.getSaveOnBagsPopupBuyBagsNowButton().click();
        }else if(selectButton.equalsIgnoreCase("DontPurchase")){
            //click on Dont purchase Bags
            reservationSummaryPage.getSaveOnBagsPopupNopeIAmGoodButton().click();
        }

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //validate step
        ValidationUtil.validateTestStep("User " + selectButton + " Bags on Save on Bags Popup on Reservation Summary Page",true);
    }

    //**********************************************************************************************
    // Method : purchaseDontPurchaseSelectYourSeatPopup
    // Description: Method is used to select seat or randon seat on final Seat Popup on Reservation Summary page
    // Input Arguments: NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void purchaseDontPurchaseSelectYourSeatPopup(String selectButton){

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //check popup appear on page
        if(!TestUtil.verifyElementDisplayed(driver,By.xpath(reservationSummaryPage.xpath_ChooseYourSeatGetRandomSeatButton))){
            ValidationUtil.validateTestStep("Choose Your Seat Popup is not appear on Reservation Summary Page",false);
        }

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //check Seats are required
        if(selectButton.equalsIgnoreCase("Purchase")){
            //click on purchase Bags
            reservationSummaryPage.getChooseYourSeatSelectSeatButton().click();
        }else if(selectButton.equalsIgnoreCase("DontPurchase")){
            //click on Dont purchase Bags
            reservationSummaryPage.getChooseYourSeatGetRandomSeatButton().click();
        }

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //validate step
        ValidationUtil.validateTestStep("User " + selectButton + " Seats on Choose Your Seat Popup on Reservation Summary Page",true);
    }

    //**********************************************************************************************
    // Method : purchaseDontPurchaseSelectYourSeatPopup
    // Description: Method is used to select seat or randon seat on final Seat Popup on Reservation Summary page
    // Input Arguments: NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void purchaseDontPurchaseRentACarPopup(String selectButton){

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //check Seats are required
        if(selectButton.equalsIgnoreCase("Purchase")){
            if(TestUtil.verifyElementDisplayed(driver,By.xpath(reservationSummaryPage.xpath_RentACarSelectCarButton))){
                //click on purchase Bags
                reservationSummaryPage.getRentACarSelectCarButton().click();
            }
        }else if(selectButton.equalsIgnoreCase("DontPurchase")){
            //check when no cars are avliable
            if(TestUtil.verifyElementDisplayed(driver,By.xpath(reservationSummaryPage.xpath_RentACarContinueButton))){
                //click on contnue button
                reservationSummaryPage.getRentACarContinueButton().click();
            }else if(TestUtil.verifyElementDisplayed(driver,By.xpath(reservationSummaryPage.xpath_RentACarNoThanksButton))){
                //click on no Thanks button
                reservationSummaryPage.getRentACarNoThanksButton().click();
            }
        }

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

    }
    //**********************************************************************************************
    // Method : acceptRejectHazardousMaterialPopup
    // Description: Method is used to select Hazardous Material Popup on Reservation Summary page
    // Input Arguments: NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void acceptRejectHazardousMaterialPopup(String selectButton){

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //check popup appear on page
        if(!TestUtil.verifyElementDisplayed(driver,By.xpath(reservationSummaryPage.xpath_HazardousMaterialPopupAcceptBoardingPassButton))){
            ValidationUtil.validateTestStep("Hazardous Material Popup is not appear on Reservation Summary Page",false);
        }


        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //check Seats are required
        if(selectButton.equalsIgnoreCase("Accept")){
            //click on purchase Bags
            reservationSummaryPage.getHazardousMaterialPopupAcceptBoardingPassButton().click();
        }else if(selectButton.equalsIgnoreCase("Reject")){
            //click on Dont purchase Bags
            reservationSummaryPage.getHazardousMaterialPopupRejectBoardingPassButton().click();
        }

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //validate step
        ValidationUtil.validateTestStep("User " + selectButton + " Hazardous Material Popup on Reservation Summary Page",true);

    }

    //**********************************************************************************************
    // Method : printEmailYourBoardingPassPopup
    // Description: Method is used to print email boarding pass on Reservation Summary page
    // Input Arguments: NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void printEmailYourBoardingPassPopup(String printButton, String emailButton){
        //dclare constant used in methods
        final int PRINT = 0;
        final int EMAIL = 1;

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //check popup appear on page
        if(!TestUtil.verifyElementDisplayed(reservationSummaryPage.getYourBoardingPassPopupPrintBoardingPassOptionsLabel())){
            ValidationUtil.validateTestStep("Boarding Pass Popup is not appear on Reservation Summary Page",false);
        }

        //declare variable used in method
        String emailValue;

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //print boarding apss
        if(printButton.equalsIgnoreCase("Print")){
            //click on print boarding pass
            reservationSummaryPage.getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(PRINT).click();
            ValidationUtil.validateTestStep("Verify the Print used in the Booking is correct on Your Boarding Pass Popup on reservation Summary page",true);
        }

        //email boarding pass
        if(emailButton.equalsIgnoreCase("Email")){
            //click on send email of boarding pass
            reservationSummaryPage.getYourBoardingPassPopupPrintBoardingPassOptionsLabel().get(EMAIL).click();

            //get the email from Boarding Pass Popup
            emailValue = JSExecuteUtil.getElementTextValue(driver,reservationSummaryPage.getYourBoardingPassPopupEmailBoardingPassTextBox()).trim();

            //verify email on boarding pass
            ValidationUtil.validateTestStep("Verify the Email used in the Booking is correct on Your Boarding Pass Popup on reservation Summary page",emailValue.equalsIgnoreCase(scenarioContext.getContext(Context.CUSTOMER_EMAIL).toString()));
        }

        //finish check in process
        reservationSummaryPage.getYourBoardingPassPopupEmailBoardingPassButton().click();

        //wait for popup appear on page
        WaitUtil.untilPageLoadComplete(driver);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);
    }

    //**********************************************************************************************
    // Method : createVoucherReservationCredit
    // Description: Method is used to create voucher/reservation credit and store in global variable
    // Input Arguments: NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 13-Apr-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 13-Apr-2019
    //**********************************************************************************************
    public synchronized void createVoucherReservationCredit(){
        //declare variable used in method
        String resCredit;
        String confirmationCode;

        //click on cancel itenary
        reservationSummaryPage.getCancelItineraryButton().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);

        //click on cancel reservation button
        cancelReservationPage.getCancelReservationButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //cancel reservation on popup
        cancelReservationPage.getReservationCancellationPopUpCancelReservationButton().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);

        if(TestUtil.verifyElementDisplayed(driver,By.xpath(cancelReservationPage.xpath_CreditSummaryValuesAmountText))){
            resCredit  = cancelReservationPage.getCreditSummaryValesAmountText().getText().trim().replace("$","").replaceAll(",","");
            confirmationCode = cancelReservationPage.getConfirmationCodeText().getText();

            //store Reservation credit amount and code in global variable
            scenarioContext.setContext(Context.RESERVATION_CREDIT_AMOUNT,resCredit);
            scenarioContext.setContext(Context.RESERVATION_CREDIT_CODE,confirmationCode);

            ValidationUtil.validateTestStep("Reservation Code: " + scenarioContext.getContext(Context.RESERVATION_CREDIT_CODE).toString(),true);
            ValidationUtil.validateTestStep("Reservation Amount: " + scenarioContext.getContext(Context.RESERVATION_CREDIT_AMOUNT).toString(),true);

            ValidationUtil.validateTestStep("User created a Reservation credit on Cancel reservation Summary Page",true);
        }else if(TestUtil.verifyElementDisplayed(driver,By.xpath(cancelReservationPage.xpath_CreditSummaryVoucherNumberText))){
            resCredit  = cancelReservationPage.getCreditSummaryVoucherAmountText().getText().replace("$","").replace(",","");
            confirmationCode = cancelReservationPage.getCreditSummaryVoucherNumberText().getText().trim();

            //store Voucher amount and code in global variable
            scenarioContext.setContext(Context.RESERVATION_VOUCHER_AMOUNT,resCredit);
            scenarioContext.setContext(Context.RESERVATION_VOUCHER_CODE,confirmationCode);

            ValidationUtil.validateTestStep("Voucher Code: " + scenarioContext.getContext(Context.RESERVATION_VOUCHER_CODE).toString(),true);
            ValidationUtil.validateTestStep("Voucher Amount: " + scenarioContext.getContext(Context.RESERVATION_VOUCHER_AMOUNT).toString(),true);

            ValidationUtil.validateTestStep("User created a Voucher Number on Cancel reservation Summary Page",true);
        }

        //continue to home page
        cancelReservationPage.getContinueToHomePageButton().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);

        //click on spirit logo
        homePage.getSpiritLogoImage().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);

        //click on booking path
        homePage.getBookPathLink().click();
    }

    //**********************************************************************************************
    // Method : selectSSRPerPassenger
    // Description: Method is used to select Additional Services
    // Input Arguments: String -> ServiceAnimal|PortableOxygen||ServiceAnimal|PortableOxygen
    //							OwnWheelChair-
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 1-May-2019
    // Reviewed By: Kartik Chauhan
    // Reviewed On: 1-May-2019
    //**********************************************************************************************
    public synchronized void selectSSRPerPassenger(String ssrField){

        //declare constant used in method
        final String WHEELCHAIR_SERVICE_REQUEST = "A wheelchair service request has been added to your reservation! Please let a Spirit team member know of your request when you arrive to the airport.";
        final String DOUBLE_SAPRATOR = "\\|\\|";
        final String SINGLE_SAPRATOR = "\\|";

        //storing all passenger additional service in array
        String allPassengerSSR[] = ssrField.split(DOUBLE_SAPRATOR);

        //Waiting until page load
        WaitUtil.untilPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(2000);

        //accessing all passenger
        for (int passengerCount = 0;passengerCount<allPassengerSSR.length;passengerCount++){

            //check SSR is required
            if(allPassengerSSR[passengerCount].equalsIgnoreCase("NotRequired")){
                continue;
            }

            //clicking on Additional Services
            reservationSummaryPage.getPassengerSectionAdditionalInfoAddButton().get(passengerCount).click();

            JSExecuteUtil.scrollDownToElementVisible(driver,reservationSummaryPage.getPassengerSectionAdditionalInfoAddButton().get(passengerCount));

            WaitUtil.untilTimeCompleted(3000);

            //storing all additional services in array
            String ssrList[] = allPassengerSSR[passengerCount].split(SINGLE_SAPRATOR);

            //accessing all additional services
            for (int ssrCount = 0;ssrCount<ssrList.length;ssrCount++){

                //checking additional services equals to given text
                if(ssrList[ssrCount].equalsIgnoreCase("HearingImpaired")){

                    //clicking on additional service
                    reservationSummaryPage.getHearingImpairedListCheckBox().click();

                    //validating step
                    ValidationUtil.validateTestStep("Additional Services Hearing Impaired is selected on Reservation Summary Page",true);

                }else if (ssrList[ssrCount].equalsIgnoreCase("ServiceAnimal")){
                    //clicking on additional service
                    reservationSummaryPage.getServiceAnimalListCheckBox().click();

                    //validating step
                    ValidationUtil.validateTestStep("Additional Services Service Animal is selected on Reservation Summary Page",true);

                }else if (ssrList[ssrCount].equalsIgnoreCase("PortableOxygen")){
                    //clicking on additional service
                    reservationSummaryPage.getPortableOxygenContainerListCheckBox().click();

                    //validating step
                    ValidationUtil.validateTestStep("Additional Services Portable Oxygen is selected on Reservation Summary Page",true);

                }else if (ssrList[ssrCount].equalsIgnoreCase("VisionDisability")){
                    //clicking on additional service
                    reservationSummaryPage.getVisionDisabilityListCheckBox().click();

                    //validating step
                    ValidationUtil.validateTestStep("Additional Services Vision Disability is selected on Reservation Summary Page",true);

                }else if (ssrList[ssrCount].equalsIgnoreCase("EmotionalAnimal")){
                    //clicking on additional service
                    reservationSummaryPage.getEmotionalSupportAnimalListCheckBox().click();

                    //validating step
                    ValidationUtil.validateTestStep("Additional Services Emotional Psychiatric Support Animal is selected on Reservation Summary Page",true);

                }else if (ssrList[ssrCount].equalsIgnoreCase("Other")){
                    //clicking on additional service
                    reservationSummaryPage.getOtherDisabilityListCheckBox().click();

                    //validating step
                    ValidationUtil.validateTestStep("Additional Services Other is selected on Reservation Summary Page",true);

                }else if (ssrList[ssrCount].equalsIgnoreCase("WheelChairNeedFromGate")){
                    //clicking on additional service
                    reservationSummaryPage.getWheelChairToAndFromGateListCheckBox().click();

                    //validating step
                    ValidationUtil.validateTestStep("Additional Services WheelChair Need From Gate is selected on Reservation Summary Page",true);

                }else if (ssrList[ssrCount].equalsIgnoreCase("WheelChairNeedFromSeat")){
                    //clicking on additional service
                    reservationSummaryPage.getWheelChairToAndFromSeatListCheckBox().click();

                    //validating step
                    ValidationUtil.validateTestStep("Additional Services WheelChair Need From Seat is selected on Reservation Summary Page",true);

                }else if (ssrList[ssrCount].equalsIgnoreCase("WheelChairCompletelyImmobile")){
                    //clicking on additional service
                    reservationSummaryPage.getWheelChairCompletelyImmobileListCheckBox().click();

                    //validating step
                    ValidationUtil.validateTestStep("Additional Services WheelChair Completely Immobile is selected on Reservation Summary Page",true);

                }else if (ssrList[ssrCount].toLowerCase().contains("ownwheelchair")) {

                    String wheelChairType  = "";
                    //clicking on additional service
                    reservationSummaryPage.getWheelchairIHaveMyOwnListCheckBox().click();

                    WaitUtil.untilTimeCompleted(2000);

                    //BatteryPoweredDryGelCell
                    //BatteryPoweredWetCell
                    //ManuallyPowered
                    if(ssrList[ssrCount].toLowerCase().contains("batterypowereddrygelcell")){
                        wheelChairType = "Battery Powered Dry/Gel Cell Battery";
                    }else if(ssrList[ssrCount].toLowerCase().contains("batterypoweredwetcell")){
                        wheelChairType = "Battery Powered Wet Cell Battery";
                    }else if(ssrList[ssrCount].toLowerCase().contains("manuallypowered")){
                        wheelChairType = "Manually Powered";
                    }

                    TestUtil.selectDropDownUsingVisibleText(reservationSummaryPage.getWheelChairTypeOfWheelChairDropDown(),wheelChairType);

                    //validating step
                    ValidationUtil.validateTestStep("Additional Services Own WheelChair is selected on Reservation Summary Page",true);

                    //If ssrfield contain WheelChair
                    if (ssrField.toLowerCase().contains("wheelchair")){
                        //wait for 2 sec
                        WaitUtil.untilTimeCompleted(2000);

                        ValidationUtil.validateTestStep("'WheelChair Service Request' verbiage is displaying under SSR section on Reservation summary page",
                                reservationSummaryPage.getWheelChairServiceRequestText().getText(),WHEELCHAIR_SERVICE_REQUEST);
                    }


                }else if (ssrList[ssrCount].equalsIgnoreCase("CertifyQualified")){
                    //clicking on additional service
                    reservationSummaryPage.getVoluntaryProvisionofEmergencyServicesProgramListCheckBox().click();
                    //validating step
                    ValidationUtil.validateTestStep("Additional Services \"I certify that I am a qualified law enforcement officer\" is selected on Reservation Summary Page",true);

                }else {
                    ValidationUtil.validateTestStep("No Additional Services is selected",false);
                }
            }

            //click on SSR Save button
            reservationSummaryPage.getSSRSaveChangeButton().click();

            WaitUtil.untilPageLoadComplete(driver);

            WaitUtil.untilTimeCompleted(2000);

        }
    }

    //**********************************************************************************************
    // Method : verifyCarSectionDetails
    // Description: Method is used to verify Car Details like Car Model, Rental Agency, PickUp Time & Address
    // Input Arguments: basefareType -> NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 11-Nov-2019
    // Reviewed By:
    // Reviewed On:
    //**********************************************************************************************
    public synchronized void verifyCarSectionDetails(){

        //declare constant used in method
        final String SINGLE_SEPARATOR = "\\|";
        final String DOUBLE_SEPARATOR = "\\|\\|";

        //verify Car Header
        if(TestUtil.verifyElementDisplayed(driver,By.xpath(paymentPage.xpath_OptionSectionCarHeaderText))){

            //CarType:THRIFTY ECONOMY CAR|CarModel:CHEVROLET SPARK or similar|Seats:4 Seats|Bags:3 Bags|Options:3 Bags|Miles:Unlimited Miles|Price:278.45|PickUpPoint:7135 GILESPIE STREET LAS VEGAS AP NV 89119
            for(String carDetails : scenarioContext.getContext(Context.CAR_DETAILS).toString().split(SINGLE_SEPARATOR)){
                switch (carDetails.split(":")[0]){
                    case "CarModel":
                        //verify Car Model
                        ValidationUtil.validateTestStep("Verify Car Model information for Selected Car on Reservation Summary Page",
                                paymentPage.getOptionSectionCarCarModelText().getText(),carDetails.replace("CarModel:",""));

                        break;

                    case "CarType":
                        //verify Car Type
                        ValidationUtil.validateTestStep("Verify Car Type information for Selected Car on Reservation Summary Page",
                                carDetails.replace("CarType:",""),paymentPage.getOptionSectionCarRentalAgencyText().getText());

                        break;

                    case "PickUp":
                        String pickUpAddress = paymentPage.getOptionSectionCarPickUpAddressText().get(0).getText() + " " + paymentPage.getOptionSectionCarPickUpAddressText().get(1).getText();

                        //verify PickUp Address
                        ValidationUtil.validateTestStep("Verify PickUp Address Information information for Selected Car on Reservation Summary Page",
                                pickUpAddress,carDetails.replace("PickUp:",""));

                        break;
                }
            }

            //verify PickUp time for Selected Car
            //get last leg departure flight information
            int lastLegDetails = scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString().split(DOUBLE_SEPARATOR).length;
            String dateDays = null;

            //loop through all departure flight details of last leg
            for(String arrivalTime : scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString().split(DOUBLE_SEPARATOR)[lastLegDetails-1].split(SINGLE_SEPARATOR)){
                //check for arrival time
                if(arrivalTime.contains("ArrivalTime")){
                    if(scenarioContext.getContext(Context.AVAILABILITY_RED_EYE).toString().split(DOUBLE_SEPARATOR)[0].equals("1")){
                        dateDays = Integer.toString(Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString())+1);
                    }else{
                        dateDays = scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString();
                    }

                    //get arrival time
                    String pickUp = TestUtil.getStringDateFormat(dateDays, "EEEE, MMM dd, yyyy") + " " + arrivalTime.replace("ArrivalTime:","");

                    //validate pickup point of Car
                    ValidationUtil.validateTestStep("Verify Pick Up information for Selected Car on Reservation Summary Page",
                            paymentPage.getOptionSectionCarPickUpTimeText().getText(),pickUp);

                    break;
                }
            }

            //verify drop off time for selected Car
            //loop through all flight details of first leg
            for(String departureTime : scenarioContext.getContext(Context.AVAILABILITY_RET_FLIGHT_DETAILS).toString().split(DOUBLE_SEPARATOR)[0].split(SINGLE_SEPARATOR)){
                //check for arrival time
                if(departureTime.contains("DepartureTime")){

                    dateDays = scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString();

                    //get arribval time
                    String dropOff = TestUtil.getStringDateFormat(dateDays, "EEEE, MMM dd, yyyy") + " " + departureTime.replace("DepartureTime:","");

                    //validate pickup point of Car
                    ValidationUtil.validateTestStep("Verify Drop OFF information for Selected Car on Reservation Summary Page",
                            paymentPage.getOptionSectionCarDropOffTimeText().getText(),dropOff);

                    break;
                }
            }
        }else{
            ValidationUtil.validateTestStep("Selected Car details is not displayed on Reservation Summary page", false);
        }
    }

    //**********************************************************************************************
    // Method : verifyHotelSectionDetails
    // Description: Method is used to verify Hotel Details
    // Input Arguments: basefareType -> NA
    // Return: NA
    // Created By : Salim Ansari
    // Created On : 11-Nov-2019
    // Reviewed By:
    // Reviewed On:
    //**********************************************************************************************
    public synchronized void verifyHotelSectionDetails(){

        final String SINGLE_SEPARATOR	= "\\|";
        final String DOUBLE_SEPARATOR	= "\\|\\|";

        for(String hotelDetails:scenarioContext.getContext(Context.HOTEL_DETAILS).toString().split(SINGLE_SEPARATOR)){

            switch(hotelDetails.split(":")[0]){
                case "HotelName:":

                    ValidationUtil.validateTestStep("Verify Selected Hotel Name on Reservation Summary Page",
                            paymentPage.getOptionSectionHotelNameText().getText().trim(),hotelDetails.replace("HotelName:",""));

                    break;
                case "HotelLandmark":



                    break;

                case "HotelAddress:":

                    boolean addressFlag = true;
                    String actualAddress = paymentPage.getOptionSectionHotelAddressText().getText().trim().replace(","," ").replace("  "," ");
                    String expectedAddress = hotelDetails.replace("HotelAddress:","");

                    for(String allAddress : actualAddress.split(" ")){
                        if(expectedAddress.toUpperCase().contains(allAddress.toUpperCase())){
                            addressFlag = true;
                        }else{
                            addressFlag = false;
                        }
                    }

                    ValidationUtil.validateTestStep("Verify Selected Hotel Address on Reservation Summary Page",
                            addressFlag);

                    break;
            }
        }

        //**********Room Count***********
        //verify room count
        ValidationUtil.validateTestStep("Verify Selected Hotel Rooms count on Reservation Summary Page",
                paymentPage.getOptionSectionHotelRoomsText().getText().trim(),scenarioContext.getContext(Context.HOMEPAGE_ROOMS).toString());

        //**********Passenger Count***********
        //get total passengers
        int paxCount = 	Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString()) +
                Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString())+
                Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTSEAT_COUNT).toString())+
                Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTLAP_COUNT).toString());

        //verify guest count
        ValidationUtil.validateTestStep("Verify Selected Hotel Guest count on Reservation Summary Page",
                paymentPage.getOptionSectionHotelGuestCountText().getText().trim(),Integer.toString(paxCount));

        //**********Night Stay Count***********
        int totalNightStay = 0;
        //verify check-In date
        if(scenarioContext.getContext(Context.AVAILABILITY_RED_EYE).toString().split(DOUBLE_SEPARATOR)[0].equals("1")){
            totalNightStay = 	Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString()) -
                    Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString());
        }else{
            totalNightStay = 	Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString()) -
                    Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString());
        }
        //verify night count
        ValidationUtil.validateTestStep("Verify Selected Hotel Night Stay Count  on Reservation Summary Page",
                paymentPage.getOptionSectionHotelNightsStayText().getText().trim(),Integer.toString(totalNightStay));

        //**********Check-In Date***********
        String dateDays = null;

        //verify check-In date
        if(scenarioContext.getContext(Context.AVAILABILITY_RED_EYE).toString().split(DOUBLE_SEPARATOR)[0].equals("1")){
            dateDays = Integer.toString(Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString())+1);
        }else{
            dateDays = scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString();
        }

        //get Check-In Date
        String checkInDate = TestUtil.getStringDateFormat(dateDays, "EEEE, MMM dd, yyyy");

        //validate Check-In date of Passenger
        ValidationUtil.validateTestStep("Verify Selected Hotel Check-In Date on Reservation Summary Page",
                paymentPage.getOptionSelectedHotelCheckInDateText().getText().trim(),checkInDate);

        //**********Check-Out Date***********
        dateDays = scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString();

        //get arribval time
        String checkOutDate = TestUtil.getStringDateFormat(dateDays, "EEEE, MMM dd, yyyy");

        //validate Check-Out date of Passenger
        ValidationUtil.validateTestStep("Verify Selected Hotel Check-Out Date on Reservation Summary Page",
                paymentPage.getOptionSelectedHotelCheckOutDateText().getText().trim(),checkOutDate);

    }

    public synchronized void selectBidOption(String bidOption)
    {
        WaitUtil.untilTimeCompleted(1000);
        bidOption = bidOption.toUpperCase();

        switch (bidOption){
            case "BID1": //select the bid 1 button
                reservationSummaryPage.getSolicitVolunteerBid1Button().click();
                WaitUtil.untilTimeCompleted(500);
                ValidationUtil.validateTestStep("\"Bid1\" Button is selected" , reservationSummaryPage.getSolicitVolunteerBid1Button().getAttribute("class"),"btn w-100 btn-primary");
                break;
            case "BID2"://select the bid 2 button
                reservationSummaryPage.getSolicitVolunteerBid2Button().click();
                WaitUtil.untilTimeCompleted(500);
                ValidationUtil.validateTestStep("\"Bid2\" Button is selected" ,
                        reservationSummaryPage.getSolicitVolunteerBid2Button().getAttribute("class"),"btn w-100 btn-primary");
                break;
            case "BID3"://select the bid 3 button
                reservationSummaryPage.getSolicitVolunteerBid3Button().click();
                WaitUtil.untilTimeCompleted(500);
                ValidationUtil.validateTestStep("\"Bid3\" Button is selected" ,
                        reservationSummaryPage.getSolicitVolunteerBid3Button().getAttribute("class"),"btn w-100 btn-primary");
                break;
            case "BID4"://select the bid 4 button
                reservationSummaryPage.getSolicitVolunteerBid4Button().click();
                WaitUtil.untilTimeCompleted(500);
                ValidationUtil.validateTestStep("\"Bid4\" Button is selected" ,
                        reservationSummaryPage.getSolicitVolunteerBid4Button().getAttribute("class"),"btn w-100 btn-primary");
                break;
            default://Input an amount into the TextBox
                if(bidOption.contains("$")) bidOption = bidOption.replace("$", ""); //if bid amount contains $, replace is as textbox only takes ints
                reservationSummaryPage.getSolicitVolunteerAmountTextBox().sendKeys(bidOption);//input amount into the textbox
                WaitUtil.untilTimeCompleted(500);
                ValidationUtil.validateTestStep("The amount textbox value of " + bidOption + " is correct" ,
                        reservationSummaryPage.getSolicitVolunteerAmountTextBox().getAttribute("value") , bidOption);

        }
    }

    public synchronized void submitBid()
    {
        reservationSummaryPage.getSolicitVolunteerSubmitBidButton().click();
        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilPageLoadComplete(driver);
        ValidationUtil.validateTestStep("The user clicked on submit bid button and redirected to the Bid Successful Pop-Up" ,
                reservationSummaryPage.getBidAcceptedHeaderText().isDisplayed());
    }

    public void validateSolicitVolunteerText(String region)
    {
        WaitUtil.untilTimeCompleted(1500);

        ValidationUtil.validateTestStep("Solicit Volunteer Header is correct" ,
                reservationSummaryPage.getSolicitVolunteerHeaderText().getText() , "Volunteer and Get Rewarded!");

        ValidationUtil.validateTestStep("Solicit Volunteer Header is correct" ,TestUtil.verifyElementDisplayed(driver ,
                By.xpath(reservationSummaryPage.xpath_SolicitVolunteerCloseButton)));

        ValidationUtil.validateTestStep("The Solicit Volunteer message Subheader is correct",
                reservationSummaryPage.getSolicitVolunteerFullFlightText().getText() , "Your flight may be full!");

        ValidationUtil.validateTestStep("The Solicit Volunteer paragraph 1 text is correct",
                reservationSummaryPage.getSolicitVolunteerParagraph1Text().getText() ,
                "We are seeking volunteers willing to take a different flight in exchange for a travel voucher redeemable within 1 year on spirit.com.");

        ValidationUtil.validateTestStep("The Solicit Volunteer paragraph 2 text is correct",
                reservationSummaryPage.getSolicitVolunteerParagraph2Text().getText() ,
                "Your existing itinerary will not be changed until you review alternative flights at the departure gate.");

        ValidationUtil.validateTestStep("The Solicit Volunteer paragraph 3 text is correct",
                reservationSummaryPage.getSolicitVolunteerParagraph3Text().getText() ,
                "Select or enter the amount of the travel voucher you would accept as compensation for volunteering your seat. We accept the lowest bids first.");

        ValidationUtil.validateTestStep("The Solicit Volunteer Bid instruction text is correct",
                reservationSummaryPage.getSolicitVolunteerParagraphBidMessage().getText() ,
                "If your bid is accepted, you will receive a travel voucher for this amount.");

        String[] regionArr = new String[4];

        switch (region)
        {
            case "1":
                regionArr[0] = "$100";
                regionArr[1] = "$125";
                regionArr[2] = "$150";
                regionArr[3] = "$175";
                break;

            case "2":
                regionArr[0] = "$150";
                regionArr[1] = "$175";
                regionArr[2] = "$200";
                regionArr[3] = "$225";
                break;

            case "3":
                regionArr[0] = "$200";
                regionArr[1] = "$250";
                regionArr[2] = "$300";
                regionArr[3] = "$350";
                break;
        }

        ValidationUtil.validateTestStep("Bid 1 button amount is correct for region " + region,
                reservationSummaryPage.getSolicitVolunteerBid1Button().getText() , regionArr[0]);
        ValidationUtil.validateTestStep("Bid 2 button amount is correct for region " + region,
                reservationSummaryPage.getSolicitVolunteerBid2Button().getText() , regionArr[1]);
        ValidationUtil.validateTestStep("Bid 3 button amount is correct for region " + region,
                reservationSummaryPage.getSolicitVolunteerBid3Button().getText() , regionArr[2]);
        ValidationUtil.validateTestStep("Bid 4 button amount is correct for region " + region,
                reservationSummaryPage.getSolicitVolunteerBid4Button().getText() , regionArr[3]);

        ValidationUtil.validateTestStep("The amount Text box label is correct",
                reservationSummaryPage.getSolicitVolunteerAmountLabel().getText() , "Amount");

        ValidationUtil.validateTestStep("The amount text box is displayed" ,TestUtil.verifyElementDisplayed(driver ,
                By.xpath(reservationSummaryPage.xpath_SolicitVolunteerBidAmountTextBox)));

        ValidationUtil.validateTestStep("The amount text box is displayed" ,TestUtil.verifyElementDisplayed(driver ,
                By.xpath(reservationSummaryPage.xpath_SolicitVolunteerSubmitBidButton)));

        ValidationUtil.validateTestStep("The amount text box is displayed" ,TestUtil.verifyElementDisplayed(driver ,
                By.xpath(reservationSummaryPage.xpath_SolicitVolunteerNoThanksButton)));

    }


}
