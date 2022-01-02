package com.spirit.pageMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataType.*;
import com.spirit.enums.Context;
import com.spirit.managers.*;
import com.spirit.pageObjects.CarPage;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

public class CarPageMethods {

    WebDriver driver;
    PageObjectManager pageObjectManager;
    ScenarioContext scenarioContext;
    CarPage carPage;

    public CarPageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
        this.driver = driver;
        this.pageObjectManager = pageObjectManager;
        this.scenarioContext = scenarioContext;
        carPage = pageObjectManager.getCarPage();
    }

    //**********************************************************************************************
    //Method Name:lastChanceCarPopup
    //Description: Method is used to select cancel Car on Last chance popup on CheckIn Path
    //Input Arguments:NA
    //Return: Boolean Flag
    //Created By : Salim Ansari
    //Created On : 9-Feb-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void lastChanceCarPopup(String selectButton) {
        //declare constant used in method
        final String ENGLISH_HEADER = "Last Chance!";

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //check last chance popup appear
        if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_LastChanceHeaderText))) {
            //check booking language
            if (scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("English")) {
                //check popup header
                if (carPage.getLastChanceHeaderText().getText().equalsIgnoreCase(ENGLISH_HEADER)) {
                    ValidationUtil.validateTestStep("Header text of Last Chance Car popup is correct on check-In path", true);
                } else {
                    //return from method
                    return;
                }
            } else if (scenarioContext.getContext(Context.HOMEPAGE_LANGUAGE).toString().equalsIgnoreCase("Spanish")) {
                throw new RuntimeException("Spanish part is not implemented in lastChanceCarPopup");
            }
        } else {
            //return from method
            return;
        }

        //verify company logo image
        ValidationUtil.validateTestStep("Verify Company Logo appear on Last Chance Car Popup on Check-In path",
                TestUtil.verifyLinks(carPage.getLastChanceCarCompanyLogoImage().getAttribute("src")));

        //verify car logo image
        ValidationUtil.validateTestStep("Verify Car Image appear on Last Chance Car Popup on Check-In path",
                TestUtil.verifyLinks(carPage.getLastChanceCarLogoImage().getAttribute("src")));

        if (selectButton.equalsIgnoreCase("BookCar")) {
            //save car name in global variable
            scenarioContext.setContext(Context.CAR_NAME, carPage.getLastChanceCarNameText().getText());

            //save car price in global variable
            scenarioContext.setContext(Context.CAR_PRICE, carPage.getLastChanceCarPriceText().getText().trim().replace("$", ""));

            //click on book car button
            carPage.getLastChanceBookCarButton().click();
        } else if (selectButton.equalsIgnoreCase("CancelCar")) {
            //click on continue without car
            carPage.getLastChanceContinueWithoutCarButton().click();
        } else if (selectButton.equalsIgnoreCase("ViewAllCar")) {
            //click on view all Car
            carPage.getLastChanceViewAllCarsButton().click();
        }

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

    }

    //**********************************************************************************************
    //Method Name:clickAddCarButtonOptionPage
    //Description: Method is used to Add Car according to car company name
    //Input Arguments:1. String -> Car Company Name
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 05-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void clickAddCarButtonOptionPage(String carCompanyName){

        final int FIRST_INDEX = 0;
        boolean flag = true;
        WaitUtil.untilTimeCompleted(1000);
        if(carCompanyName.equalsIgnoreCase("NA")){
            carPage.getCarcardViewLink().get(FIRST_INDEX).click();
        }else {
            while (flag){
                for (int count = 0;count<carPage.getCarsPanel().size();count++){
                    if(carPage.getCarCardCompanyNameText().get(count).getText().toUpperCase().trim().contains(carCompanyName.toUpperCase())){
                        carPage.getCarcardViewLink().get(count).click();
                        flag = false;
                        break;
                    }
                }
                if (TestUtil.verifyElementDisplayed(driver,By.xpath(carPage.xpath_CarCarouselRightButton))&&carPage.getCarCarouselRightButton().isEnabled()&&flag){
                    carPage.getCarCarouselRightButton().click();
                    WaitUtil.untilTimeCompleted(1000);
                }
            }
        }
        WaitUtil.untilPageLoadComplete(driver);
        WaitUtil.untilTimeCompleted(1000);
    }


    //**********************************************************************************************
    //Method Name:selectCarOnOptionPage
    //Description: Method is used to select Car according to carName and company name
    //Input Arguments:1. String -> Car Company Name   2. String -> car name
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 05-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void selectCarOnOptionPage(String carCompanyName, String carName) {
        //click on veiw all car button
        carPage.getViewAllCarsButton().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);

        //select car
        selectCarOnCarPage(carCompanyName, carName);
    }

    //**********************************************************************************************
    //Method Name:selectCarOnCarPage
    //Description: Method is used to select Car according to carName and company name
    //Input Arguments:1. String -> Car Company Name   2. String -> car name
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 05-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void selectCarOnCarPage(String carCompanyName, String carName) {

        final int FIRST_INDEX = 0;
        WaitUtil.untilTimeCompleted(1000);

        //select any rental ageny and any car
        if (carCompanyName.equalsIgnoreCase("NA") && carName.equalsIgnoreCase("NA")) {
            //select first car
            selectCarByIndexValue(FIRST_INDEX);
        } else if (!carCompanyName.equalsIgnoreCase("NA") || !carName.equalsIgnoreCase("NA")) {

            if (!carCompanyName.equalsIgnoreCase("NA")) {
                //filter car by Rental Agency
                filterCarByRentalAgency(carCompanyName);

                //filter Car by Car Type
                filterCarByCarType(carCompanyName);
            }

            for (int carCounter = 0; carCounter < carPage.getCarsPageCarModelText().size(); carCounter++) {
                if (carPage.getCarsPageCarModelText().get(carCounter).getText().contains(carName) || carName.equalsIgnoreCase("NA")) {
                    //select Car
                    selectCarByIndexValue(carCounter);

                    break;
                }
            }
        }

        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilPageLoadComplete(driver);

    }

    //**********************************************************************************************
    //Method Name:clickCarMoreLinkPage
    //Description: Method is used to click on More Info according to carName and company name
    //Input Arguments:1. String -> Car Company Name   2. String -> car name
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 05-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void clickCarMoreLinkPage(String carCompanyName, String carName) {

        final int FIRST_INDEX = 0;
        WaitUtil.untilTimeCompleted(1000);

        //select any rental ageny and any car
        if (carCompanyName.equalsIgnoreCase("NA") && carName.equalsIgnoreCase("NA")) {
            //select first car
            carPage.getCarsPageMoreInfoLink().get(FIRST_INDEX).click();
        } else if (!carCompanyName.equalsIgnoreCase("NA") || !carName.equalsIgnoreCase("NA")) {

            if (!carCompanyName.equalsIgnoreCase("NA")) {
                //filter car by Rental Agency
                filterCarByRentalAgency(carCompanyName);

                //filter Car by Car Type
                filterCarByCarType(carCompanyName);
            }

            for (int carCounter = 0; carCounter < carPage.getCarsPageCarModelText().size(); carCounter++) {
                if (carPage.getCarsPageCarModelText().get(carCounter).getText().contains(carName) || carName.equalsIgnoreCase("NA")) {
                    //select Car
                    carPage.getCarsPageMoreInfoLink().get(carCounter).click();

                    break;
                }
            }
        }

        WaitUtil.untilTimeCompleted(1000);
        WaitUtil.untilPageLoadComplete(driver);
    }

    //**********************************************************************************************
    //Method Name:filterCarBySeats
    //Description: Method is used to filter Car based on Seat type
    //Input Arguments:1. String -> Car Seat Type
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void filterCarBySeats(String seatType) {
        //check Car Seat Filter list is display on Car
        if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_SeatsFilterDropDown))) {
            //check Seat filter option is not visible on screen
            if (carPage.getSeatsFilterDropDown().getAttribute("style").contains("180deg")) {
                //open filter option for Seat Type
                carPage.getSeatsFilterDropDown().click();

                //wait for page to load is complete
                WaitUtil.untilPageLoadComplete(driver);

                //wait for 1 sec
                WaitUtil.untilTimeCompleted(1000);

                //validate Seat filter
                ValidationUtil.validateTestStep("User open Seat Filter list on Car Page", true);
            }

            //check filter value to be select for Seat Type
            switch (seatType.toUpperCase()) {
//                case "4ORLESS":
//                    //click on 4 for less seat option
//                    carPage.getSeatsFilter4orLessButton().click();
//
//                    //wait for page to load is complete
//                    WaitUtil.untilPageLoadComplete(driver);
//
//                    //veiry user selection
//                    ValidationUtil.validateTestStep("User selected 4 or Less option from Seat Type Filter on Car Page", true);
//
//                    //break;
//                    break;
                case "5OR6":
                    //click on 5 or 6 seat option
                    carPage.getSeatsFilter5to6Button().click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //verify user selection
                    ValidationUtil.validateTestStep("User selected 5 or 6 option from Seat Type Filter on Car Page", true);

                    //break
                    break;
                case "7ORMORE":
                    //click on 7 or more seat option
                    carPage.getSeatsFilter7orMoreButton().click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //verify user selection
                    ValidationUtil.validateTestStep("User selected 7 or More option from Seat Type Filter on Car Page", true);

                    //break
                    break;
            }
        } else {
            ValidationUtil.validateTestStep("Seat Filter chevron link is not visible on Car Page", false);
        }
    }


    //**********************************************************************************************
    //Method Name:filterCarByBags
    //Description: Method is used to filter Car based on Bags type
    //Input Arguments:1. String -> Car Bags Type
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void filterCarByBags(String bagsType) {
//
        //check Car Bags Filter list is display on Car
        if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_BagsFilterDropDown))) {
            //check Bags filter option is not visible on screen
            if (carPage.getBagsFilterDropDown().getAttribute("style").contains("180deg")) {
                //open filter option for Bags Type
                carPage.getBagsFilterDropDown().click();

                //wait for page to load is complete
                WaitUtil.untilPageLoadComplete(driver);

                //wait for 1 sec
                WaitUtil.untilTimeCompleted(1000);

                //validate Bags filter
                ValidationUtil.validateTestStep("User open Bags Filter list on Car Page", true);
            }

            //check filter value to be select for Bags Type
            switch (bagsType.toUpperCase()) {
                case "1TO2":
                    //click on 1 to 2 Bags option
                    carPage.getBagsFilter1to2Button().click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //veiry user selection
                    ValidationUtil.validateTestStep("User selected 1 to 2 option from Bags Type Filter on Car Page", true);

                    //break;
                    break;
                case "3TO4":
                    //click on 3 to 4 Bags option
                    carPage.getBagsFilter3to4Button().click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //verify user selection
                    ValidationUtil.validateTestStep("User selected 3 to 4 option from Bags Type Filter on Car Page", true);

                    //break
                    break;
                case "5ORMORE":
                    //click on 5 or more Bags option
                    carPage.getBagsFilter5orMoreButton().click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //verify user selection
                    ValidationUtil.validateTestStep("User selected 5 or More option from Bags Type Filter on Car Page", true);

                    //break
                    break;
            }
        } else {
            ValidationUtil.validateTestStep("Bags Filter chevron link is not visible on Car Page", false);
        }
    }


    //**********************************************************************************************
    //Method Name:filterCarByRentalAgency
    //Description: Method is used to filter Car based on Rental Agency
    //Input Arguments:1. String -> Car Rental Agency
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void filterCarByRentalAgency(String rentalAgencyToBeSelected) {
        //check rental Agency drop down chevron is visible on Car Page
        if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_RentalAgencyFilterDropDown))) {
            //check Rental Agency Dropdown Chevron is not open
            if (carPage.getRentalAgencyFilterDropDown().getAttribute("style").contains("180deg")) {
                //open Rental Agency Dropdown Chevron
                carPage.getRentalAgencyFilterDropDown().click();

                //wait for page to load is complete
                WaitUtil.untilPageLoadComplete(driver);

                //wait for 1 sec
                WaitUtil.untilTimeCompleted(1000);

                //validate Rental Agency filter
                ValidationUtil.validateTestStep("User open Rental Agency Filter list on Car Page", true);
            }
            boolean select = false;
            //loop through to find given rental Agency
            for (WebElement rentalAgencyName : carPage.getRentalAgencyFilterOptionListButton()) {
                //check required rental Agency
                if (rentalAgencyToBeSelected.toUpperCase().contains(rentalAgencyName.getText().toUpperCase())) {
                    //click on rental agency
                    rentalAgencyName.click();
                    select = true;
                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //break loop
                    break;
                }

            }
            ValidationUtil.validateTestStep("Rental Agency available on Car Page", select);

        } else {
            ValidationUtil.validateTestStep("Rental Agency chevron link is not visible on Car Page", false);
        }

    }

    //**********************************************************************************************
    //Method Name:filterCarByCarType
    //Description: Method is used to filter Car based on Car Type
    //Input Arguments:1. String -> Car Name
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void filterCarByCarType(String carNameTobeSelected) {

        //check Car Type drop down chevron is visible on Car Page
        if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_CarTypeFilterDropDown))) {
            //check Car Type Dropdown Chevron is not open
            if (carPage.getCarTypeFilterDropDown().getAttribute("style").contains("180deg")) {
                //open Car Type Dropdown Chevron
                carPage.getCarTypeFilterDropDown().click();

                //wait for page to load is complete
                WaitUtil.untilPageLoadComplete(driver);

                //wait for 1 sec
                WaitUtil.untilTimeCompleted(1000);

                //validate Car Type filter
                ValidationUtil.validateTestStep("User open Car Type Filter list on Car Page", true);
            }

            //loop through to find given Car Type
            for (WebElement carTypeName : carPage.getCarTypeFilterOptionListButton()) {
                //check required Car Type
                if (carNameTobeSelected.toUpperCase().contains(carTypeName.getText().toUpperCase())) {
                    //click on Car Type
                    carTypeName.click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //break loop
                    break;
                }
            }
        } else {
            ValidationUtil.validateTestStep("Car Type chevron link is not visible on Car Page", false);
        }
    }

    //**********************************************************************************************
    //Method Name:filterCarByCarOptions
    //Description: Method is used to filter Car based on Car Options
    //Input Arguments:1. String -> Car Name
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void filterCarByCarOptions(String carOptionsToBeSelected) {
        //check Car Options drop down chevron is visible on Car Page
        if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_CarOptionsFilterDropDown))) {
            //check Car Options Dropdown Chevron is not open
            if (carPage.getCarOptionsFilterDropDown().getAttribute("style").contains("180deg")) {
                //open Car Options Dropdown Chevron
                carPage.getCarOptionsFilterDropDown().click();

                //wait for page to load is complete
                WaitUtil.untilPageLoadComplete(driver);

                //wait for 1 sec
                WaitUtil.untilTimeCompleted(1000);

                //validate Car Type filter
                ValidationUtil.validateTestStep("User open Car Options Filter list on Car Page", true);
            }

            //loop through to find given Car Type
            for (WebElement carOptions : carPage.getCarOptionsFilterOptionListButton()) {
                //check required Car Type
                if (carOptionsToBeSelected.toUpperCase().contains(carOptions.getText().toUpperCase())) {
                    //click on Car Type
                    carOptions.click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //break loop
                    break;
                }
            }
        } else {
            ValidationUtil.validateTestStep("Car Options chevron link is not visible on Car Page", false);
        }
    }

    //**********************************************************************************************
    //Method Name:removeCarFilterByCategory
    //Description: Method is used to remove car filters
    //Input Arguments:1. String -> Car Name
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void removeCarFilterByCategory(String filterType) {

        switch (filterType.toUpperCase()) {
            case "ALLFILTER":
                //check remove all filter is visible on car page
                if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_ClearAllFilters))) {
                    //click clear all filter button
                    carPage.getCarsPageClearAllFiltersButton().click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //verify clear all filter is removed
                    ValidationUtil.validateTestStep("Clear All Filter link is not visible on Car Page",
                            !TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_ClearAllFilters)));
                } else {
                    ValidationUtil.validateTestStep("Clear All Filter link is not visible on Car Page", false);
                }

                //break statement
                break;
            case "PRICE":
                //check price filter is visible on car page
                if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_PriceFilterClear))) {
                    //click price filter button
                    carPage.getPriceFilterClearButton().click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //verify price filter is removed
                    ValidationUtil.validateTestStep("Price clear filter link is not visible on Car Page",
                            !TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_PriceFilterClear)));
                } else {
                    ValidationUtil.validateTestStep("Price clear filter link is not visible on Car Page", false);
                }

                //break statement
                break;
            case "SEATS":
                //check Seats filter is visible on car page
                if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_SeatsFilterClear))) {
                    //click Seats filter button
                    carPage.getSeatsFilterClearButton().click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //verify Seats filter is removed
                    ValidationUtil.validateTestStep("Seats clear filter link is not visible on Car Page",
                            !TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_SeatsFilterClear)));
                } else {
                    ValidationUtil.validateTestStep("Seats clear filter link is not visible on Car Page", false);
                }

                //break statement
                break;
            case "BAGS":
                //check Bags filter is visible on car page
                if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_BagsFilterClear))) {
                    //click Bags filter button
                    carPage.getBagsFilterClearButton().click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //verify Bags filter is removed
                    ValidationUtil.validateTestStep("Bags clear filter link is not visible on Car Page",
                            !TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_BagsFilterClear)));
                } else {
                    ValidationUtil.validateTestStep("Bags clear filter link is not visible on Car Page", false);
                }

                //break statement
                break;
            case "RENTALAGENCY":
                //check Rental Agency filter is visible on car page
                if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_RentalAgencyFilterClear))) {
                    //click Rental Agency  filter button
                    carPage.getRentalAgencyFilterClearButton().click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //verify Bags filter is removed
                    ValidationUtil.validateTestStep("Rental Agency clear filter link is not visible on Car Page",
                            !TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_RentalAgencyFilterClear)));
                } else {
                    ValidationUtil.validateTestStep("Rental Agency clear filter link is not visible on Car Page", false);
                }

                //break statement
                break;
            case "CARTYPE":
                //check Car Type filter is visible on car page
                if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_CarTypeFilterClear))) {
                    //click Car Type filter button
                    carPage.getCarTypeFilterClearButton().click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //verify Car Type filter is removed
                    ValidationUtil.validateTestStep("Car Type clear filter link is not visible on Car Page",
                            !TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_CarTypeFilterClear)));
                } else {
                    ValidationUtil.validateTestStep("Car Type clear filter link is not visible on Car Page", false);
                }

                //break statement
                break;
            case "CAROPTIONS":
                //check Car Options filter is visible on car page
                if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_CarOptionsFilterClear))) {
                    //click Car Options filter button
                    carPage.getCarOptionsFilterClearButton().click();

                    //wait for page to load is complete
                    WaitUtil.untilPageLoadComplete(driver);

                    //verify Car Options filter is removed
                    ValidationUtil.validateTestStep("Car Options clear filter link is not visible on Car Page",
                            !TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_CarOptionsFilterClear)));
                } else {
                    ValidationUtil.validateTestStep("Car Options clear filter link is not visible on Car Page", false);
                }

                //break statement
                break;
        }
    }

    //**********************************************************************************************
    //Method Name:storeCarDetails
    //Description: Method is used to store all car information into Environment variable
    //Input Arguments:1. int -> Index value of Car to be selected
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private synchronized void storeCarDetails(int selectCarByIndexValue) {

        //CAR_DETAILS->CarType:|CarModel:|Seats:|Bags:|Options:|Miles:|Price:|EMI:|PickUpPoint:
        //declare variable used in method
        String carDetails = "";

        //store car type information
        carDetails = carDetails + "CarType:" + carPage.getCarsPageCarTypeText().get(selectCarByIndexValue).getText() + "|";

        //store car model information
        carDetails = carDetails + "CarModel:" + carPage.getCarsPageCarModelText().get(selectCarByIndexValue).getText() + "|";

        //store Car number of seats information
        carDetails = carDetails + "Seats:" + carPage.getCarsCardNumberOfSeatsText().get(selectCarByIndexValue).getText() + "|";

        //store Car Bags storage information
        carDetails = carDetails + "Bags:" + carPage.getCarsCardNumberOfBagsText().get(selectCarByIndexValue).getText() + "|";

        //store Car Options Information
        carDetails = carDetails + "Options:" + carPage.getCarsCardTransmissionTypeText().get(selectCarByIndexValue).getText() + "|";

        //store Car mileage information
        carDetails = carDetails + "Miles:" + carPage.getCarsCardMileageLimitText().get(selectCarByIndexValue).getText() + "|";

        //store Car Price information
        carDetails = carDetails + "Price:" + carPage.getCarsPageRentalPriceText().get(selectCarByIndexValue).getText().replace("$", "") + "|";

        //click on more info link
        try
        {
            carPage.getCarsPageMoreInfoLink().get(selectCarByIndexValue).click();
        }
        catch (Exception e) {
            JSExecuteUtil.clickOnElement(driver, carPage.getCarsPageMoreInfoLink().get(selectCarByIndexValue));
        }
        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);

        //click on More Info location link
//        carPage.getCarPageMoreInfoLocationsLink().click();
        JSExecuteUtil.clickOnElement(driver,carPage.getCarPageMoreInfoLocationsLink());

        //wait for 1 sec
        WaitUtil.untilTimeCompleted(1000);

        //get pick up address
        String pickUpAddress = carPage.getCarPageMoreInfoLocationPickUpPointLink().get(0).getText() + " " + carPage.getCarPageMoreInfoLocationPickUpPointLink().get(1).getText();

        //remove
        pickUpAddress = pickUpAddress.replace(",", "");

        //store car pick up location
        carDetails = carDetails + "PickUpPoint:" + pickUpAddress;

        //check car detail environment variable
//        if(scenarioContext.isContains(Context.CAR_DETAILS)){
        //store car detils in environment variable
        scenarioContext.setContext(Context.CAR_DETAILS, carDetails);
//        }else{
//            //validate in case envio=ronment variableis not found
//            ValidationUtil.validateTestStep("Car Detail environment valriable is not avaliable for data Storage",false);
//        }

        //click on less info link
        carPage.getCarsPageLessInfoLink().click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);
    }


    //**********************************************************************************************
    //Method Name:selectCarByIndexValue
    //Description: Method is used to select and store Car information
    //Input Arguments:1. int -> Index value of Car to be selected
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void selectCarByIndexValue(int indexValueOfCarToBeSelected) {

        //strore Car Information
        storeCarDetails(indexValueOfCarToBeSelected);

        //select Car by Index Value
        carPage.getCarsPageBookButton().get(indexValueOfCarToBeSelected).click();

        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);
    }

    //**********************************************************************************************
    //Method Name:verifySelectedCarOptionPage
    //Description: Method is used to verify Selected Car information
    //Input Arguments:1. int -> Index value of Car to be selected
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void verifySelectedCarOptionPage() {
        //declare constant used in method
        final String SINGLE_SEPARATOR = "\\|";
        final String DOUBLE_SEPARATOR = "\\|\\|";

//CarType:THRIFTY ECONOMY CAR|CarModel:CHEVROLET SPARK or similar|Seats:4 Seats|Bags:3 Bags|Options:3 Bags|Miles:Unlimited Miles|Price:278.45|PickUpPoint:7135 GILESPIE STREET LAS VEGAS AP NV 89119
        for (String carDetails : scenarioContext.getContext(Context.CAR_DETAILS).toString().split(SINGLE_SEPARATOR)) {
            switch (carDetails.split(":")[0]) {
                case "CarModel":
                    //verify Car Type
                    ValidationUtil.validateTestStep("Verify Car Type information for Selected Car on Option Page",
                            carPage.getSelectedCarCarTypeText().getText(), carDetails.replace("CarModel:", ""));

                    break;

                case "CarType":
                    //verify Car Model
                    ValidationUtil.validateTestStep("Verify Car Model information for Selected Car on Option Page",
                            carPage.getSelectedCarCarModelText().getText(), carDetails.replace("CarType:", ""));

                    break;

                case "Seats":
                    //verify Car Seats
                    ValidationUtil.validateTestStep("Verify Seats information for Selected Car on Option Page",
                            carPage.getCarsCardNumberOfSeatsText().get(0).getText(), carDetails.replace("Seats:", ""));

                    break;

                case "Bags":
                    //verify Car Bags
                    ValidationUtil.validateTestStep("Verify Bags information for Selected Car on Option Page",
                            carPage.getCarsCardNumberOfBagsText().get(0).getText(), carDetails.replace("Bags:", ""));

                    break;

                case "Options":
                    //verify Car Options
                    ValidationUtil.validateTestStep("Verify Transmission information for Selected Car on Option Page",
                            carPage.getCarsCardTransmissionTypeText().get(0).getText(), carDetails.replace("Options:", ""));

                    break;

                case "Miles":
                    //verify Car Miles
                    ValidationUtil.validateTestStep("Verify Miles information for Selected Car on Option Page",
                            carPage.getCarsCardMileageLimitText().get(0).getText(), carDetails.replace("Miles:", ""));

                    break;
            }
        }

        //verify PickUp time for Selected Car
        //get last leg departure flight information
        int lastLegDetails = scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString().split(DOUBLE_SEPARATOR).length;
        String dateDays = null;

        //loop through all departure flight details of last leg
        for (String arrivalTime : scenarioContext.getContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS).toString().split(DOUBLE_SEPARATOR)[lastLegDetails - 1].split(SINGLE_SEPARATOR)) {
            //check for arrival time
            if (arrivalTime.contains("ArrivalTime")) {
                //get arribval time

                //System.out.println(scenarioContext.getContext(Context.AVAILABILITY_RED_EYE).toString().split(DOUBLE_SEPARATOR)[0]);
                if (scenarioContext.getContext(Context.AVAILABILITY_RED_EYE).toString().split(DOUBLE_SEPARATOR)[0].equals("1")) {
                    dateDays = Integer.toString(Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString()) + 1);
                } else {
                    dateDays = scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString();
                }

                //String pickUp = TestUtil.getStringDateFormat(dateDays, "EEEE, MMM dd, yyyy") + " " + arrivalTime.replace("ArrivalTime:","");
                String pickUp = TestUtil.getStringDateFormat(dateDays, "EEEE, MMM dd, yyyy");

                //validate pickup point of Car
                ValidationUtil.validateTestStep("Verify Pick Up information for Selected Car on Option Page",
                        carPage.getSelectedCarPickUPTimeText().getText(), pickUp);

                break;
            }
        }

        //verify drop off time for selected Car
        //loop through all flight details of first leg
        for (String departureTime : scenarioContext.getContext(Context.AVAILABILITY_RET_FLIGHT_DETAILS).toString().split(DOUBLE_SEPARATOR)[0].split(SINGLE_SEPARATOR)) {
            //check for arrival time
            if (departureTime.contains("DepartureTime")) {

                dateDays = scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString();

                //get arrival time
                //String dropOff = TestUtil.getStringDateFormat(dateDays, "EEEE, MMM dd, yyyy") + " " + departureTime.replace("DepartureTime:", "");
                String dropOff = TestUtil.getStringDateFormat(dateDays, "EEEE, MMM dd, yyyy");
                //validate pickup point of Car
                ValidationUtil.validateTestStep("Verify Drop OFF information for Selected Car on Option Page",
                        carPage.getSelectedCarDropOffTimeText().getText(), dropOff);

                break;
            }
        }
    }

    public synchronized void continueWithoutCar() {
        WaitUtil.untilPageLoadComplete(driver);
        if (TestUtil.verifyElementDisplayed(driver, By.xpath(carPage.xpath_CarsPageContinueWithoutCarButton))) {
            carPage.getCarsPageContinueWithoutCarButton().click();

            WaitUtil.untilPageLoadComplete(driver);
        }

    }

    public synchronized void storeCarInformationOnCarPage(String carCompanyName, String carName) {
        final int FIRST_INDEX = 0;
        WaitUtil.untilTimeCompleted(1000);

        //select any rental ageny and any car
        if (carCompanyName.equalsIgnoreCase("NA") && carName.equalsIgnoreCase("NA")) {
            //select first car
            storeCarDetails(FIRST_INDEX);
        } else if (!carCompanyName.equalsIgnoreCase("NA") || !carName.equalsIgnoreCase("NA")) {

            if (!carCompanyName.equalsIgnoreCase("NA")) {
                //filter car by Rental Agency
                filterCarByRentalAgency(carCompanyName);

                //filter Car by Car Type
                filterCarByCarType(carCompanyName);
            }

            for (int carCounter = 0; carCounter < carPage.getCarsPageCarModelText().size(); carCounter++) {
                if (carPage.getCarsPageCarModelText().get(carCounter).getText().contains(carName) || carName.equalsIgnoreCase("NA")) {
                    //select Car
                    storeCarDetails(carCounter);

                    break;
                }
            }
        }
    }

    public synchronized void verifyLocationTabDetailsWithCarnetWebsite(){
        String carType  = scenarioContext.getContext(Context.CAR_DETAILS).toString().split("\\|")[0].replace("CarType:","");
        String carModel = scenarioContext.getContext(Context.CAR_DETAILS).toString().split("\\|")[1].replace("CarModel:","");
        for(int moreInfo=0;moreInfo<carPage.getCarsPageCarTypeText().size();moreInfo++){

            String currentCarType  = carPage.getCarsPageCarTypeText().get(moreInfo).getText();
            String currentCarModel = carPage.getCarsPageCarModelText().get(moreInfo).getText();
            if(currentCarType.equalsIgnoreCase(carType) && currentCarModel.equalsIgnoreCase(carModel)){
                //click on more info link
                carPage.getCarsPageMoreInfoLink().get(moreInfo).click();

                //wait for page load is complete
                WaitUtil.untilPageLoadComplete(driver);

                //click on More Info location link
                carPage.getCarPageMoreInfoLocationsLink().click();

                //wait for 1 sec
                WaitUtil.untilTimeCompleted(1000);

                String pickUpAddress = carPage.getCarPageMoreInfoLocationPickUpPointLink().get(0).getText() + " " +
                        carPage.getCarPageMoreInfoLocationPickUpPointLink().get(1).getText() + " " +
                        carPage.getCarPageMoreInfoLocationPickUpVoiceText().getText().replace("(","").replace(")","").replace("Voice:","").replace(" ","").replace("-","");


                for(String pickUpAddres: scenarioContext.getContext(Context.CAR_LOCATION_CARNET).toString().split("\\|")[0].split(" ")){

                    ValidationUtil.validateTestStep("User verify PickUp address "+ pickUpAddres + " mention on Car net is not matching with Spirit website on car Page",
                            pickUpAddres.toUpperCase().contains(pickUpAddres.toUpperCase()));
                }


                for(int dayTimeCounter=1;dayTimeCounter<scenarioContext.getContext(Context.CAR_LOCATION_CARNET).toString().split("\\|").length;dayTimeCounter++){

                    String actaulDayTime = carPage.getCarnetPickUpLocationDayTimeText().get(dayTimeCounter-1).getText().toString().trim().replaceAll("\n","").replaceAll(" ","");
                    String expectedDayTime = scenarioContext.getContext(Context.CAR_LOCATION_CARNET).toString().split("\\|")[dayTimeCounter].replaceAll(" AM","AM").replaceAll(" PM","PM");

                    ValidationUtil.validateTestStep("User verify the " + actaulDayTime + " Day Time on Location Tab of Car page",
                            actaulDayTime,expectedDayTime);
                }

                break;
            }
        }
    }
    //**********************************************************************************************
    //Method Name:verifyCarDescriptionAndPolicies
    //Description: Method is used to verify Car Description and policies
    //Input Arguments:
    //Return: NA
    //Created By : Sunny Sok
    //Created On : 10-Dec-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void verifyCarDescriptionAndPolicies(String carCompanyName) {

        CarDetailsInfoData carDetailsData = FileReaderManager.getInstance().getJsonReader().getCarDetailsDataByRequest(carCompanyName);

        ValidationUtil.validateTestStep("Validating first Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(0).getText(),carDetailsData.VEHICLE_DESCRIPTION1);

        ValidationUtil.validateTestStep("Validating second Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(1).getText(),carDetailsData.VEHICLE_DESCRIPTION2);

        ValidationUtil.validateTestStep("Validating third Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(2).getText(),carDetailsData.VEHICLE_DESCRIPTION3);

        ValidationUtil.validateTestStep("Validating fourth Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(3).getText(),carDetailsData.VEHICLE_DESCRIPTION4);

        ValidationUtil.validateTestStep("Validating fifth Section of Vehicle Description appear under More Info link on Car Page",
                pageObjectManager.getCarPage().getCarPopUpVehicleDescriptionText().get(4).getText(),carDetailsData.VEHICLE_DESCRIPTION5);


        // Verify Policies tab verbiage:
        pageObjectManager.getCarPage().getCarPageMoreInfoPoliciesLink().click();

        WaitUtil.untilTimeCompleted(3000);

        ValidationUtil.validateTestStep("Child Seats Required by Law Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(0).getText(), carDetailsData.CHILD_SEAT_POLICY);

        ValidationUtil.validateTestStep("Child Seats Required by Law Text \"Yes\" is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(1).getText(), carDetailsData.CHILD_SEAT_REQUIREMENT);

        ValidationUtil.validateTestStep("Child Seats Required by Law Text Verbiage is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesChildSeatsRequiredText().get(2).getText(), carDetailsData.CHILD_SEAT_VERBIAGE);

        ValidationUtil.validateTestStep("Minimum Age to Rent Verbiage Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().get(0).getText(), carDetailsData.AGE_TO_RENT_POLICY);

        ValidationUtil.validateTestStep("Minimum Age to Rent Text \"21\" is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().get(1).getText(), carDetailsData.AGE_REQUIREMENT);

        ValidationUtil.validateTestStep("Minimum Age to Rent Note Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesMinimumAgeText().get(2).getText(), carDetailsData.AGE_POLICY_VERBIAGE);

        ValidationUtil.validateTestStep("Refund for Unused Rental Days/Hours Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRefundForUnusedText().get(0).getText(),carDetailsData.REFUND_POLICY);

        ValidationUtil.validateTestStep("Refund for Unused Rental Days/Hours \"NO\" Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesRefundForUnusedText().get(1).getText(), carDetailsData.REFUND_REQUIREMENT);

        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().get(0).getText(), carDetailsData.LICENSE_POLICY);

        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().get(1).getText(), carDetailsData.LICENSE_REQUIREMENT);

        ValidationUtil.validateTestStep("Valid Driver's License Required Text is displayed on Car PopUp under Policy Section",
                pageObjectManager.getCarPage().getCarPopUpPoliciesLicenseRequiredText().get(2).getText(), carDetailsData.LICENSE_VERBIAGE);
    }

}