package com.spirit.pageMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.Common;
import com.spirit.pageObjects.TriseptREZAgentPage;
import com.spirit.utility.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;

public class CommonPageMethods {

    private WebDriver driver;
    private PageObjectManager pageObjectManager;
    private ScenarioContext scenarioContext;
    private Common common;
    private TriseptREZAgentPage triseptREZAgentPage;

    public CommonPageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
        this.driver				= driver;
        this.pageObjectManager 	= pageObjectManager;
        this.scenarioContext 	= scenarioContext;
        common                  = pageObjectManager.getCommon();
        triseptREZAgentPage     = pageObjectManager.getTriseptREZAgentPage();
    }

    //**********************************************************************************************
    //Method Name: cancelCarnetCarBooking
    //Description: Method is used to Cancel Selected Car on Carnet Website
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void cancelCarnetCarBooking(){

        //try{
        //login to application
        loginToCarnetApplication();

        //get car details
        retrieveSelectedCarnetCar();

        //cancel car
        cancelSelectedCarnetCar();

        // }catch(Exception e){

        //System.out.println(e);
        //send mail to recipent
        //EmailUtil.sendEmailForVacationBooking(TestUtil.getLatestFailedSnapshotFile(),"Car Confirmation Code:" + scenarioContext.getContext(Context.CONFIRMATION_CARNETCAR_CODE).toString());
        //}
    }

    //**********************************************************************************************
    //Method Name: storeCarnetCarInformation
    //Description: Method is used to store Carnet Car information from Carnet Website
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void storeAllCarnetCarInformation(){
        //login to application
        loginToCarnetApplication();

        //fill car details on Carnet Home Page
        fillCarnetCarDetailsToSearch();

        //get all car details
        searchCarnetCarAvailability();

    }

    public synchronized void cancelAllAutomationBookingOnCarnetWebsite(long daysToSearch){

        //login to application
        loginToCarnetApplication();

        //cancel carnet Booking
        cancelBookingOnCarnetWebsite(daysToSearch);

    }

    //**********************************************************************************************
    //Method Name: storeCarnetCarInformation
    //Description: Method is used to store Carnet Car information from Carnet Website
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void storeCarnetCarLocationInformation(){
        //login to application
        loginToCarnetApplication();

        //fill car details on Carnet Home Page
        fillCarnetCarDetailsToSearch();

        //store car location based on Car details
        getCarnetCarLocation();

    }




    //**********************************************************************************************
    //Method Name: loginToCarnetApplication
    //Description: Method is used to login to Carnet Website
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private synchronized void loginToCarnetApplication(){
        //declare constant used in method
        final int CAR_PROVIDER_TAB  = 1;

        //open new tab
        JSExecuteUtil.openNewTabWithGivenURL(driver, FileReaderManager.getInstance().getConfigReader().getCarProviderURL());

        //switch to car provider Tab
        TestUtil.switchToWindow(driver,CAR_PROVIDER_TAB);

        //get login detail sof Carnet application
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("CarnetLogin");

        //Enter UserName
        triseptREZAgentPage.getCarnetUserNameTextBox().sendKeys(loginCredentialsData.userName);

        //Enter Password
        triseptREZAgentPage.getCarnetPasswordTextBox().sendKeys(loginCredentialsData.password);

        //Click on Sign In button
        triseptREZAgentPage.getCarnetSignInButton().click();

        //wait for Carnet Home Page appear
        //WaitUtil.untilElementIsClickable(driver,By.xpath(triseptREZAgentPage.xpath_CarnetAvailabilitySearchLink));
        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);

        //verify homa page appear on carnet Application
        ValidationUtil.validateTestStep("User verify login to carnet application is sucessfull",
                driver.findElements(By.xpath(triseptREZAgentPage.xpath_CarnetAvailabilitySearchLink)).size()>0);
    }

    //**********************************************************************************************
    //Method Name: selectDateOnCarnetCalendar
    //Description: Method is used to select date on Carnet Calendar
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private synchronized void selectDateOnCarnetCalendar(String dateType){
        //create date format to be selected
        String requiredDate = null;

        if(dateType.equalsIgnoreCase("DEP")){
            requiredDate = TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE),"yyyy-MM-dd");
        }else if(dateType.equalsIgnoreCase("RET")){
            requiredDate = TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE),"yyyy-MM-dd");
        }else{
            ValidationUtil.validateTestStep("Passed Date format is not correct on Carnet Page", false);
        }

        //loop through all date to select required date
        for(WebElement calenderDate : triseptREZAgentPage.getCarnetCalenderDatesText()){
            if(calenderDate.getAttribute("data-date-to-select").equalsIgnoreCase(requiredDate)){
                calenderDate.click();

                break;
            }
        }
    }

    //**********************************************************************************************
    //Method Name: retrieveSelectedCarnetCar
    //Description: Method is used to retrieve Selected Car information from Carnet website
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private synchronized void retrieveSelectedCarnetCar(){
        //wait for retrive page appear on carnet website
        //WaitUtil.untilElementIsClickable(driver,By.xpath(triseptREZAgentPage.xpath_CarnetRetrieveLink));
        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);

        //click on Retrive booking link
        triseptREZAgentPage.getCarnetRetrieveLink().click();

        //wait for retrive page appear on carnet website
        //WaitUtil.untilElementIsClickable(driver,By.xpath(triseptREZAgentPage.xpath_CarnetRetrieveByReferenceTextBox));
        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);

        //Enter reservation number to retrive page
        triseptREZAgentPage.getCarnetRetrieveByReferenceTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_CARNETCAR_CODE).toString());

        //scrool to make element visible
        JSExecuteUtil.scrollDownToElementVisible(driver,triseptREZAgentPage.getCarnetRetrieveReservationSubmitButton());

        //click on search button
        triseptREZAgentPage.getCarnetRetrieveReservationSubmitButton().click();

        WaitUtil.untilTimeCompleted(4000);

        //wait for list to appear on page
        //WaitUtil.untilElementIsClickable(driver,By.xpath(triseptREZAgentPage.xpath_CarnetRetrieveResultsDetailsText));
        //WaitUtil.untilTimeCompleted(2000);
        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);

        //click on the first items of list
        triseptREZAgentPage.getCarnetRetrieveResultsDetailsText().get(0).click();
    }

    //**********************************************************************************************
    //Method Name: cancelSelectedCarnetCar
    //Description: Method is used to retrieve Selected Car information from Carnet website
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private synchronized void cancelSelectedCarnetCar(){
        //wait for camcellation page appear
        //WaitUtil.untilElementIsClickable(driver,By.xpath(triseptREZAgentPage.xpath_CarnetCancelReservationButton));
        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);

        //click on cancel button
        triseptREZAgentPage.getCarnetCancelReservationButton().click();

        //wait for confirmation header on top
        WaitUtil.untilElementIsClickable(driver,By.xpath(triseptREZAgentPage.xpath_CarnetConfirmYesButton));

        //click
        triseptREZAgentPage.getCarnetConfirmYesButton().click();

        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(5000);

//        //wait for 3 sec
//        WaitUtil.untilTimeCompleted(3000);
//
//        //refresh browser
//        JSExecuteUtil.refreshBrowser(driver);
//
//        //wait for 3 sec
//        WaitUtil.untilTimeCompleted(3000);
//
//        //refresh browser
//        JSExecuteUtil.refreshBrowser(driver);
//
//        //wait for 3 sec
//        WaitUtil.untilTimeCompleted(3000);

        //wait for cancellation status appear
        //WaitUtil.untilElementIsClickable(driver,By.xpath(triseptREZAgentPage.xpath_CarnetCancelConfirmationText));

        //get the cancellation verbiage
        ValidationUtil.validateTestStep("Verify User cancel the Car reveration on Carnet Website",
                triseptREZAgentPage.getCarnetCancelConfirmationText().getText(),"Cancelled by customer");

        //close carnet website
        driver.close();

        //switch back to parent page
        TestUtil.switchToWindow(driver,0);
    }

    //**********************************************************************************************
    //Method Name: fillCarnetCarDetailsToSearch
    //Description: Method is used to fill booking details on Carnet Website to Search avaliable Car
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private synchronized void fillCarnetCarDetailsToSearch(){
        //get airport code
        String airportCode = scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().split("\\(")[1].substring(0, 3);

        //enter airport details


        //scenarioContext.getContext(Context.HOMEPAGE_ARR_AIRPORT).toString().split("\\(")[1].substring(0, 3)
        triseptREZAgentPage.getCarnetPickUpLocationTextBox().sendKeys(airportCode);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //select location from list
        //TestUtil.mouseClickOnElement(driver,triseptREZAgentPage.getCarnetPickUpLocationText().get(0));
        for(WebElement element : triseptREZAgentPage.getCarnetPickUpLocationText()){
            if(element.isDisplayed()){
                if(element.getText().contains("("+airportCode+")")){
                    element.click();

                    break;
                }
            }
        }


        //enter pickup date
        triseptREZAgentPage.getCarnetPickUpDateTextBox().click();

        //select pickupdate from calender
        selectDateOnCarnetCalendar("DEP");

        //enter drop off date
        triseptREZAgentPage.getCarnetDropOffDateTextBox().click();

        //select dropoff date from calender
        selectDateOnCarnetCalendar("RET");

        //enter driver age
        triseptREZAgentPage.getCarnetDriverAgeLabel().click();

        //clear text box
        TestUtil.clearTextBoxUsingSendKeys(driver,triseptREZAgentPage.getCarnetDriverAgeTextBox());

        //enter driver age
        triseptREZAgentPage.getCarnetDriverAgeTextBox().sendKeys("30");

        //enter driving country
        triseptREZAgentPage.getCarnetCountryLabel().click();

        TestUtil.selectDropDownUsingVisibleText(triseptREZAgentPage.getCarnetCountryDropDown(),"United States");

        //click on search button
        triseptREZAgentPage.getCarnetSearchButton().click();
    }

    //**********************************************************************************************
    //Method Name: searchCarnetCarAvailability
    //Description: Method is used to store car avaliable Car information on Carnet Car Avaliability Page
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private synchronized void searchCarnetCarAvailability(){
        //declare variable used in method=
        String carName, carVendorName, carModel, carType, carMiles, carTransmission, carBags, carPassengers, carPrice, carDetailsCarnet=null;

        //CarType:THRIFTY ECONOMY CAR|CarModel:CHEVROLET SPARK or similar|Seats:4 Seats|Bags:3 Bags|Options:3 Bags|Miles:Unlimited Miles|Price:278.45|PickUpPoint:7135 GILESPIE STREET LAS VEGAS AP NV 89119
        //loop through all cars to get details
        for(int carDetailCounter=0;carDetailCounter<triseptREZAgentPage.getCarnetCarDetailNameText().size();carDetailCounter++){

            //get car name
            carName = triseptREZAgentPage.getCarnetCarDetailNameText().get(0).getText().split(" ",2)[1].trim();

            //get vendor name
            carVendorName = triseptREZAgentPage.getCarnetCarDetailVendorNameText().get(0).getAttribute("alt").split(" ",2)[0].trim();

            //get car type
            carType = triseptREZAgentPage.getCarnetCarDetailsTypeText().get(0).getText().split(" ",2)[0].trim();

            carModel = carVendorName + " " + carType;

            //get car miles
            carMiles = triseptREZAgentPage.getCarnetCarDetailsMilesText().get(0).getText().trim();

            //get car transmission
            carTransmission = triseptREZAgentPage.getCarnetCarDetailsTransmissionText().get(0).getText().trim();

            //get car bags
            carBags = triseptREZAgentPage.getCarnetCarDetailsBagsText().get(0).getText().trim();

            //get car passengers
            carPassengers = triseptREZAgentPage.getCarnetCarDetailsPassengersText().get(0).getText().trim();

            //get car price
            carPrice = triseptREZAgentPage.getCarnetCarDetailsPriceText().get(0).getText().replace("US$","").trim();

            //strore value in varaibel
            if(carDetailCounter==0){
                carDetailsCarnet = "CarType:" + carName + "|CarModel:" + carModel + "|Seats:" + carPassengers + " Seats|Bags:" + carBags + " Bags|Options:" + carTransmission + "|Miles:" + carMiles + " Miles|Price:" + carPrice;
            }else{
                carDetailsCarnet = carDetailsCarnet + "||CarType:" + carName + "|CarModel:" + carModel + "|Seats:" + carPassengers + " Seats|Bags:" + carBags + " Bags|Options:" + carTransmission + "|Miles:" + carMiles + " Miles|Price:" + carPrice;
            }
        }

        //store into global variable
        scenarioContext.setContext(Context.CAR_DETAILS_CARNET,carDetailsCarnet);

        //close carnet website
        driver.close();

        //switch back to parent page
        TestUtil.switchToWindow(driver,0);
    }


    private synchronized void getCarnetCarLocation(){
        //declare constant used in method
        final String SINGLE_SEPARATOR   = "\\|";
        final String SINGLE_SPACE       = " ";

        //declare variable used in method
        String carType, carModel;

        //CarType:THRIFTY ECONOMY CAR|CarModel:CHEVROLET SPARK or similar|Seats:4 Seats|Bags:3 Bags|Options:3 Bags|Miles:Unlimited Miles|Price:278.45|PickUpPoint:7135 GILESPIE STREET LAS VEGAS AP NV 89119

        carType = scenarioContext.getContext(Context.CAR_DETAILS).toString().split(SINGLE_SEPARATOR)[0].replace("CarType:","").split(SINGLE_SPACE)[0];

        carModel = scenarioContext.getContext(Context.CAR_DETAILS).toString().split(SINGLE_SEPARATOR)[1].replace("CarModel:","");

        while(TestUtil.verifyElementDisplayed(driver,By.xpath(triseptREZAgentPage.xpath_CarnetCarShowMoreLink))){
            triseptREZAgentPage.getCarnetCarShowMoreLink().click();

            WaitUtil.untilJavaScriptPageLoadComplete(driver);

            WaitUtil.untilTimeCompleted(2000);
        }

        for(int carCounter = 0;carCounter<triseptREZAgentPage.getCarnetCarDetailVendorNameText().size();carCounter++){

            String currentCarModel  = triseptREZAgentPage.getCarnetCarDetailNameText().get(carCounter).getText().trim();
            String currentCarType   = triseptREZAgentPage.getCarnetCarDetailVendorNameText().get(carCounter).getAttribute("alt");
            System.out.println("********************************");
            System.out.println(currentCarType.toUpperCase());
            System.out.println(carType.toUpperCase());
            System.out.println("----------------------------------");
            System.out.println(currentCarModel.toUpperCase());
            System.out.println(carModel.toUpperCase());

            if(currentCarType.toUpperCase().contains(carType.toUpperCase()) && currentCarModel.toUpperCase().contains(carModel.toUpperCase().replace(" O SIMILAR",""))){
                triseptREZAgentPage.getCarnetCarDetailCarImage().get(carCounter).click();

                WaitUtil.untilTimeCompleted(1000);

                triseptREZAgentPage.getCarnetCarDetailFullDetailLink().get(carCounter).click();

                break;
            }
        }

        WaitUtil.untilTimeCompleted(3000);

        triseptREZAgentPage.getCarnetCarFullDetailLocationLink().click();

        String pickUpAddress = "";
        for(WebElement address : triseptREZAgentPage.getCarnetCarFullDetailLocationAddressText()){
            if(address.getText().length()>0){
                pickUpAddress = pickUpAddress + " " + address.getText();
            }
        }

        String pickUpTime = "";
        for(int time=0;time<triseptREZAgentPage.getCarnetCarFullDetailLocationOpeningDaysText().size();time++ ){

            pickUpTime       = pickUpTime + "|" + triseptREZAgentPage.getCarnetCarFullDetailLocationOpeningDaysText().get(time).getText().replaceAll(" ","") +
                    TestUtil.convert24HourTo12Hour(triseptREZAgentPage.getCarnetCarFullDetailLocationOpeningTimeText().get(time).getText().split("-")[0].trim()) + "-" +
                    TestUtil.convert24HourTo12Hour(triseptREZAgentPage.getCarnetCarFullDetailLocationOpeningTimeText().get(time).getText().split("-")[1].trim());
        }


        //close carnet website
        driver.close();

        //switch back to parent page
        TestUtil.switchToWindow(driver,0);

        //store in global varoiable
        scenarioContext.setContext(Context.CAR_LOCATION_CARNET,pickUpAddress+pickUpTime);
    }


    public synchronized void cancelHBGHotelBooking(){

        try{
            //login to application
            loginToHotelBedsApplication();

            //get car details
            searchHotelBooking();

            //cancel car
            cancelHotelBooking();

        }catch(Exception e){
            //send mail to recipent
            EmailUtil.sendEmailForVacationBooking(TestUtil.getLatestFailedSnapshotFile(),"Hotel Confirmation Code:" + scenarioContext.getContext(Context.CONFIRMATION_HBGHOTEL_CODE).toString());

            //close browser
            driver.close();

            //switch to main browser;
            TestUtil.switchToWindow(driver,0);

            if(scenarioContext.isContains(Context.CONFIRMATION_CARNETCAR_CODE)){
                //cancel car booking if applicable
                cancelCarnetCarBooking();
            }
        }

    }

    public synchronized void cancelAllAutomationBookingOnHotelBedsWebsite(Long daysToSearch){

        //final String USER_NAME = "Spirit Automation|joe flyer|FSProfile Automation|NFCProfile Automation|ANTHONY FLYER|Zero Miles|FSMC AUTOMATION|gonzlaez rodriguez|UMNR Automation|UMNR AUTOMATION|FiveK Miles|John Doe";
        final String USER_NAME = "Automation";
        //login to application
        loginToHotelBedsApplication();

        triseptREZAgentPage.getHBGHeaderBookingLink().click();

        //WaitUtil.untilTimeCompleted(2000);
        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);

        TestUtil.selectDropDownUsingVisibleText(triseptREZAgentPage.getHBGBookingDateTypeTextBox(),"Booking confirmation");

        TestUtil.clearTextBoxUsingSendKeys(driver,triseptREZAgentPage.getHBGBookingDateFromTextBox());

        triseptREZAgentPage.getHBGBookingDateFromTextBox().click();

        triseptREZAgentPage.getHBGBookingDateFromTextBox().sendKeys(TestUtil.getStringDateFormat(Long.toString(-1*daysToSearch), "dd/MM/yyyy"));

        TestUtil.clearTextBoxUsingSendKeys(driver,triseptREZAgentPage.getHBGBookingDateToTextBox());

        triseptREZAgentPage.getHBGBookingDateToTextBox().click();

        triseptREZAgentPage.getHBGBookingDateToTextBox().sendKeys(TestUtil.getStringDateFormat("0", "dd/MM/yyyy"));

        triseptREZAgentPage.getHBGBookingDestinationTextBox().click();

        TestUtil.selectDropDownUsingVisibleText(triseptREZAgentPage.getHBGBookingStatusDropDown(),"Non-cancelled");

        triseptREZAgentPage.getHBGBookingSearchButton().click();

        //WaitUtil.untilTimeCompleted(10000);
        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(4000);

        String bookingIDValue = "";

        do{

            for(int bookingCounter=0;bookingCounter<triseptREZAgentPage.getHBGRetrieveBookingNameText().size();bookingCounter++){
                System.out.println(triseptREZAgentPage.getHBGRetrieveBookingNameText().get(bookingCounter).getText());
                System.out.println(triseptREZAgentPage.getHBGRetrieveBookingIDText().get(bookingCounter).getText());
                System.out.println(bookingCounter);
                if(triseptREZAgentPage.getHBGRetrieveBookingNameText().get(bookingCounter).getText().toUpperCase().replace("MR ","").replace("MS ","").contains(USER_NAME.toUpperCase())){
                    bookingIDValue = triseptREZAgentPage.getHBGRetrieveBookingIDText().get(bookingCounter).getText();
                    triseptREZAgentPage.getHBGRetrieveBookingCancelAllText().get(bookingCounter).click();

                    WaitUtil.untilTimeCompleted(1000);
                    WaitUtil.untilJavaScriptPageLoadComplete(driver);
                    WaitUtil.untilTimeCompleted(1000);

                    //WaitUtil.untilElementIsClickable(driver,By.xpath(triseptREZAgentPage.xpath_HBGSelectedHotelAcceptCancellationCheckBox));

                    ValidationUtil.validateTestStep("User get the Selected Hotel information on Hotel Beds Website",
                            driver.findElements(By.xpath(triseptREZAgentPage.xpath_HBGSelectedHotelAcceptCancellationCheckBox)).size()>0);

                    if(triseptREZAgentPage.getHBGSelectedHotelBookingModificationIDText().getText().equalsIgnoreCase(bookingIDValue)){
                        triseptREZAgentPage.getHBGSelectedHotelAcceptCancellationCheckBox().click();

                        triseptREZAgentPage.getHBGSelectedHotelCancelServiceButton().click();

                        bookingIDValue = "";
                    }


                    //WaitUtil.untilTimeCompleted(4000);
                    WaitUtil.untilTimeCompleted(1000);
                    WaitUtil.untilJavaScriptPageLoadComplete(driver);
                    WaitUtil.untilTimeCompleted(1000);

                    if(triseptREZAgentPage.getHBGSelectedHotelCancelReasonRadioButton().size()>0){
                        triseptREZAgentPage.getHBGSelectedHotelCancelReasonRadioButton().get(0).click();

                        triseptREZAgentPage.getHBGSelectedHotelConfirmCancelButton().click();

                        //WaitUtil.untilTimeCompleted(5000);
                        WaitUtil.untilTimeCompleted(1000);
                        WaitUtil.untilJavaScriptPageLoadComplete(driver);
                        WaitUtil.untilTimeCompleted(1000);
                    }

                    ValidationUtil.validateTestStep("Verify Hotel Booking is cancelled",
                            triseptREZAgentPage.getHBGSelectedHotelCancelConfirmationMessageText().getText(),"Your booking has been cancelled successfully");

                    //////////////////////////////////////

                    triseptREZAgentPage.getHBGHeaderBookingLink().click();

                    //WaitUtil.untilTimeCompleted(5000);
                    WaitUtil.untilTimeCompleted(1000);
                    WaitUtil.untilJavaScriptPageLoadComplete(driver);
                    WaitUtil.untilTimeCompleted(1000);

                    TestUtil.selectDropDownUsingVisibleText(triseptREZAgentPage.getHBGBookingDateTypeTextBox(),"Booking confirmation");

                    TestUtil.clearTextBoxUsingSendKeys(driver,triseptREZAgentPage.getHBGBookingDateFromTextBox());

                    triseptREZAgentPage.getHBGBookingDateFromTextBox().click();

                    triseptREZAgentPage.getHBGBookingDateFromTextBox().sendKeys(TestUtil.getStringDateFormat(Long.toString(-1*daysToSearch), "dd/MM/yyyy"));

                    TestUtil.clearTextBoxUsingSendKeys(driver,triseptREZAgentPage.getHBGBookingDateToTextBox());

                    triseptREZAgentPage.getHBGBookingDateToTextBox().click();

                    triseptREZAgentPage.getHBGBookingDateToTextBox().sendKeys(TestUtil.getStringDateFormat("0", "dd/MM/yyyy"));

                    triseptREZAgentPage.getHBGBookingDestinationTextBox().click();

                    TestUtil.selectDropDownUsingVisibleText(triseptREZAgentPage.getHBGBookingStatusDropDown(),"Non-cancelled");

                    triseptREZAgentPage.getHBGBookingSearchButton().click();

                    //WaitUtil.untilTimeCompleted(10000);
                    WaitUtil.untilTimeCompleted(1000);
                    WaitUtil.untilJavaScriptPageLoadComplete(driver);
                    WaitUtil.untilTimeCompleted(4000);

                    bookingCounter=-1;

                    ////////////////////////////////////

                    continue;
                }
            }

            if(triseptREZAgentPage.getHBGSelectedHotelPaginationNextButton().getAttribute("class").toString().contains("disabled")){
                break;
            }else{
                triseptREZAgentPage.getHBGSelectedHotelPaginationNextButton().click();

                //WaitUtil.untilTimeCompleted(2000);
                WaitUtil.untilTimeCompleted(1000);
                WaitUtil.untilJavaScriptPageLoadComplete(driver);
                WaitUtil.untilTimeCompleted(3000);
            }

        }while(true);

        driver.close();

        TestUtil.switchToWindow(driver,0);


    }

    //**********************************************************************************************
    //Method Name: loginToCarnetApplication
    //Description: Method is used to login to Carnet Website
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private synchronized void loginToHotelBedsApplication(){

        //open new tab
        JSExecuteUtil.openNewTabWithGivenURL(driver, FileReaderManager.getInstance().getConfigReader().getHotelProviderURL());

        TestUtil.switchToWindow(driver,1);

        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("HotelBedsLogin");

        triseptREZAgentPage.getHBGUserNameTextBox().sendKeys(loginCredentialsData.userName);

        triseptREZAgentPage.getHBGPasswordTextBox().sendKeys(loginCredentialsData.password);

        triseptREZAgentPage.getHBGLoginButton().click();

        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("User login to Hotel Beds Website",
                driver.findElements(By.xpath(triseptREZAgentPage.xpath_HBGConfirmationCodeTextBox)).size()>0);

    }

    private synchronized void searchHotelBooking(){

        triseptREZAgentPage.getHBGConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_HBGHOTEL_CODE).toString());

        triseptREZAgentPage.getHBGConfirmationCodeSearchButton().click();

        //WaitUtil.untilElementIsClickable(driver,By.xpath(triseptREZAgentPage.xpath_HBGSelectedHotelCancelLink));

        WaitUtil.untilTimeCompleted(2000);

        WaitUtil.untilJavaScriptPageLoadComplete(driver);
    }

    private synchronized void cancelHotelBooking(){
        //triseptREZAgentPage.getHBGSelectedHotelCancelLink().click();
        triseptREZAgentPage.getHBGRetrieveBookingCancelAllText().get(0).click();

        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);

        ValidationUtil.validateTestStep("User get the Selected Hotel information on Hotel Beds Website",
                driver.findElements(By.xpath(triseptREZAgentPage.xpath_HBGSelectedHotelAcceptCancellationCheckBox)).size()>0);

        triseptREZAgentPage.getHBGSelectedHotelAcceptCancellationCheckBox().click();

        triseptREZAgentPage.getHBGSelectedHotelCancelServiceButton().click();

        //WaitUtil.untilTimeCompleted(4000);
        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);

        if(triseptREZAgentPage.getHBGSelectedHotelCancelReasonRadioButton().size()>0){
            triseptREZAgentPage.getHBGSelectedHotelCancelReasonRadioButton().get(0).click();

            triseptREZAgentPage.getHBGSelectedHotelConfirmCancelButton().click();

            //WaitUtil.untilTimeCompleted(5000);
            WaitUtil.untilTimeCompleted(1000);
            WaitUtil.untilJavaScriptPageLoadComplete(driver);
            WaitUtil.untilTimeCompleted(1000);
        }


        ValidationUtil.validateTestStep("User verify Selected Hotel is cancelled on Hotel Beds Website",
                triseptREZAgentPage.getHBGSelectedHotelCancelConfirmationMessageText().getText(),"has been cancelled successfully");

        driver.close();

        TestUtil.switchToWindow(driver,0);
    }

    private synchronized void cancelBookingOnCarnetWebsite(long daysToSearch){

        //final String USER_NAME = "Spirit Automation|joe flyer|FSProfile Automation|NFCProfile Automation|ANTHONY FLYER|Zero Miles|FSMC AUTOMATION|gonzlaez rodriguez|UMNR Automation|UMNR AUTOMATION|FiveK Miles|John Doe";
        final String USER_NAME = "Automation";

        triseptREZAgentPage.getCarnetRetrieveLink().click();

        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);

        //Select Affiliate
        TestUtil.clearTextBoxUsingSendKeys(driver,triseptREZAgentPage.getCarnetRetrieveByAffiliateTextBox());

        triseptREZAgentPage.getCarnetRetrieveByAffiliateTextBox().click();

        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);

        triseptREZAgentPage.getCarnetRetrieveByAffiliateText().click();

        triseptREZAgentPage.getCarnetRetrieveByFamilyNameText().click();

        //Reservation Status
        TestUtil.clearTextBoxUsingSendKeys(driver,triseptREZAgentPage.getCarnetRetrieveByReservationStateTextBox());

        triseptREZAgentPage.getCarnetRetrieveByReservationStateTextBox().click();

        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);

        triseptREZAgentPage.getCarnetRetrieveByReservationStateText().click();

        triseptREZAgentPage.getCarnetRetrieveByFamilyNameText().click();

        TestUtil.clearTextBoxUsingSendKeys(driver,triseptREZAgentPage.getCarnetRetrieveDateRangeTextBox());


        triseptREZAgentPage.getCarnetRetrieveDateRangeTextBox().sendKeys(TestUtil.getStringDateFormat(Long.toString(-1*daysToSearch), "dd/MM/yyyy") + " - " + TestUtil.getStringDateFormat("0", "dd/MM/yyyy"));

        triseptREZAgentPage.getCarnetRetrieveDateRangeTextBox().click();

        //scroll to make element visible
        JSExecuteUtil.scrollDownToElementVisible(driver,triseptREZAgentPage.getCarnetRetrieveReservationSubmitButton());

        triseptREZAgentPage.getCarnetRetrieveReservationSubmitButton().click();

        //WaitUtil.untilTimeCompleted(4000);
        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilJavaScriptPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);

        while(!triseptREZAgentPage.getCarnetRetrieveResultsPaginationText().getAttribute("class").toString().contains("disabled")){
            for(int passengerNameCounter=0;passengerNameCounter<triseptREZAgentPage.getCarnetRetrieveResultsPassengerReservationNameText().size();passengerNameCounter++){
                System.out.println(triseptREZAgentPage.getCarnetRetrieveResultsStatusText().get(passengerNameCounter).getText().toString());
                if(triseptREZAgentPage.getCarnetRetrieveResultsStatusText().get(passengerNameCounter).getText().toString().contains("Confirmed")){
                    System.out.println(triseptREZAgentPage.getCarnetRetrieveResultsPassengerReservationNameText().get(passengerNameCounter).getText().toString());
                    if(triseptREZAgentPage.getCarnetRetrieveResultsPassengerReservationNameText().get(passengerNameCounter).getText().toUpperCase().replace("MR ","").replace("MS ","").contains(USER_NAME.toUpperCase())){


                        triseptREZAgentPage.getCarnetRetrieveResultsPassengerReservationNameText().get(passengerNameCounter).click();

                        //WaitUtil.untilTimeCompleted(2000);
                        WaitUtil.untilTimeCompleted(1000);
                        WaitUtil.untilJavaScriptPageLoadComplete(driver);
                        WaitUtil.untilTimeCompleted(1000);

                        //wait for camcellation page appear
                        WaitUtil.untilElementIsClickable(driver,By.xpath(triseptREZAgentPage.xpath_CarnetCancelReservationButton));

                        //click on cancel button
                        triseptREZAgentPage.getCarnetCancelReservationButton().click();

                        //wait for confirmation header on top
                        WaitUtil.untilElementIsClickable(driver,By.xpath(triseptREZAgentPage.xpath_CarnetConfirmYesButton));

                        //click
                        triseptREZAgentPage.getCarnetConfirmYesButton().click();

                        //wait for 3 sec
                        //WaitUtil.untilTimeCompleted(3000);

//                        //refresh browser
//                        JSExecuteUtil.refreshBrowser(driver);
//
//                        //wait for 3 sec
//                        WaitUtil.untilTimeCompleted(3000);
//
//                        //refresh browser
//                        JSExecuteUtil.refreshBrowser(driver);
//
//                        //wait for 3 sec
//                        WaitUtil.untilTimeCompleted(3000);
//
//                        //wait for cancellation status appear
//                        WaitUtil.untilElementIsClickable(driver,By.xpath(triseptREZAgentPage.xpath_CarnetCancelConfirmationText));

                        WaitUtil.untilTimeCompleted(1000);
                        WaitUtil.untilJavaScriptPageLoadComplete(driver);
                        WaitUtil.untilTimeCompleted(1000);

                        driver.navigate().back();

                        //WaitUtil.untilTimeCompleted(4000);
                        WaitUtil.untilTimeCompleted(1000);
                        WaitUtil.untilJavaScriptPageLoadComplete(driver);
                        WaitUtil.untilTimeCompleted(1000);

                        TestUtil.clearTextBoxUsingSendKeys(driver,triseptREZAgentPage.getCarnetRetrieveDateRangeTextBox());

                        triseptREZAgentPage.getCarnetRetrieveDateRangeTextBox().sendKeys(TestUtil.getStringDateFormat(Long.toString(-1*daysToSearch), "dd/MM/yyyy") + " - " + TestUtil.getStringDateFormat("0", "dd/MM/yyyy"));

                        triseptREZAgentPage.getCarnetRetrieveDateRangeTextBox().click();

                        //scroll to make element visible
                        JSExecuteUtil.scrollDownToElementVisible(driver,triseptREZAgentPage.getCarnetRetrieveReservationSubmitButton());

                        triseptREZAgentPage.getCarnetRetrieveReservationSubmitButton().click();

                        //WaitUtil.untilTimeCompleted(4000);
                        WaitUtil.untilTimeCompleted(1000);
                        WaitUtil.untilJavaScriptPageLoadComplete(driver);
                        WaitUtil.untilTimeCompleted(1000);
                    }

                }
            }


            try{
                triseptREZAgentPage.getCarnetRetrieveResultsPaginationText().isDisplayed();
                triseptREZAgentPage.getCarnetRetrieveResultsPaginationText().click();

                WaitUtil.untilTimeCompleted(1000);
                WaitUtil.untilJavaScriptPageLoadComplete(driver);
                WaitUtil.untilTimeCompleted(3000);

            }catch(Exception e){
                break;
            }

            //WaitUtil.untilTimeCompleted(2000);
            WaitUtil.untilTimeCompleted(1000);
            WaitUtil.untilJavaScriptPageLoadComplete(driver);
            WaitUtil.untilTimeCompleted(1000);

        }

        //close carnet website
        driver.close();

        //switch back to parent page
        TestUtil.switchToWindow(driver,0);
    }


    public synchronized void verifyPackageBookingEmails(){

        //declare variable used in method
        String carConfirmationNumber    = null;
        String hotelConfirmationNumber  = null;
        String carDetails               = null;
        String hotelDetails             = null;
        String spiritCinformationCode   = scenarioContext.getContext(Context.CONFIRMATION_CODE).toString();
        String redEyeFlight             = scenarioContext.getContext(Context.AVAILABILITY_RED_EYE).toString();
        String depDate                  = scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString();
        String retDate                  = scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString();
        String depFlightDetails         = scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString();
        String retFlightDetails         = scenarioContext.getContext(Context.AVAILABILITY_RET_FLIGHT_DETAILS).toString();


        if(scenarioContext.isContains(Context.CAR_DETAILS)){
            carDetails               = scenarioContext.getContext(Context.CAR_DETAILS).toString();
        }else{
            carDetails               = "NA";
        }

        if(scenarioContext.isContains(Context.HOTEL_DETAILS)){
            hotelDetails             = scenarioContext.getContext(Context.HOTEL_DETAILS).toString();
        }else{
            hotelDetails             = "NA";
        }

        if(scenarioContext.isContains(Context.CONFIRMATION_CARNETCAR_CODE)){
            carConfirmationNumber    = scenarioContext.getContext(Context.CONFIRMATION_CARNETCAR_CODE).toString();
        }else{
            carConfirmationNumber    = "NA";
        }

        if(scenarioContext.isContains(Context.CONFIRMATION_HBGHOTEL_CODE)){
            hotelConfirmationNumber  = scenarioContext.getContext(Context.CONFIRMATION_HBGHOTEL_CODE).toString();
        }else{
            hotelConfirmationNumber = "NA";
        }

        EmailUtil.verifyPackageBookingEmails(driver, carDetails, hotelDetails,
                spiritCinformationCode, carConfirmationNumber, hotelConfirmationNumber,
                redEyeFlight, depDate, retDate,
                depFlightDetails, retFlightDetails);


    }

}
