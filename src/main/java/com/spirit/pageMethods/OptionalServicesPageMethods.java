package com.spirit.pageMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.enums.Context;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.Header;
import com.spirit.pageObjects.OptionalServicesPage;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class OptionalServicesPageMethods {

    private WebDriver driver;
    private ScenarioContext scenarioContext;
    private OptionalServicesPage optionalServicesPage;
    private Header header;

    public OptionalServicesPageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.scenarioContext = scenarioContext;
        optionalServicesPage = pageObjectManager.getOptionalServicesPage();
        header   = pageObjectManager.getHeader();
    }

    public synchronized void selectTrip(String tripType){

        WaitUtil.untilTimeCompleted(2000);

        if(tripType.equalsIgnoreCase("NewTrip")){
            optionalServicesPage.getBagOTronNewTripLabel().click();
        }else if(tripType.equalsIgnoreCase("ExistingTrip")){
            optionalServicesPage.getBagOTronExistingTripLabel().click();
        }else{
            throw new RuntimeException("Value is not passed correctly in selectTrip method on Bag O Tron");
        }

        //verify trip is clicked
        ValidationUtil.validateTestStep("User clicked on " + tripType + " Type in Bag o Tron Section on Optional Service Page",
                TestUtil.verifyElementDisplayed(driver,By.xpath(optionalServicesPage.xpath_BagOTronStartOverButton)));

        //store in global variable
        scenarioContext.setContext(Context.HOMEPAGE_JOURNEYTYPE,"Flight");

    }

    public synchronized void selectAirports(String depAirport, String retAirport){

        WaitUtil.untilTimeCompleted(2000);

        //declare variable used in method
        int airportCounter = 0;
        String airportValue = "";
        List<WebElement> allAirports = null;

        //create object for dep airports
        Select drpdnDepAirport = new Select(optionalServicesPage.getNewTripDepartingAirportDropDown());

        //get all dep airports
        allAirports  = drpdnDepAirport.getOptions();

        //loop through all dep airports
        for(airportCounter=0;airportCounter<allAirports.size();airportCounter++){
            //check dep airport code is present
            if(allAirports.get(airportCounter).getText().contains(depAirport)){
                //get the display text
                airportValue = allAirports.get(airportCounter).getText();

                //break loop
                break;
            }
        }

        //store dep Airport in global variable
        scenarioContext.setContext(Context.HOMEPAGE_DEP_AIRPORT, airportValue);

        //select dep airport
        drpdnDepAirport.selectByVisibleText(airportValue);

        //wait for 0.5 sec
        WaitUtil.untilTimeCompleted(500);

        //verify dep airport is selected
        ValidationUtil.validateTestStep("User verify Depart airport " + depAirport + " is selected in Bag o Tron Section on Optional Service Page",
                drpdnDepAirport.getFirstSelectedOption().getText(),depAirport);


        if(!retAirport.equalsIgnoreCase("NA")){
            //create object for ret airport
            Select drpdnRetAirport = new Select(optionalServicesPage.getNewTripReturningAirportDropDown());

            //get all return airport
            allAirports  = drpdnRetAirport.getOptions();

            //loop through all return airport
            for(airportCounter=0;airportCounter<allAirports.size();airportCounter++){
                //check ret airports
                if(allAirports.get(airportCounter).getText().contains(retAirport)){
                    //store return airport code value
                    airportValue = allAirports.get(airportCounter).getText();

                    //break loop
                    break;
                }
            }

            //store arr Airport in global variable
            scenarioContext.setContext(Context.HOMEPAGE_ARR_AIRPORT, airportValue);

            //select return airport
            drpdnRetAirport.selectByVisibleText(airportValue);

            //wait for 0.5 sec
            WaitUtil.untilTimeCompleted(500);

            //verify return airport selected
            ValidationUtil.validateTestStep("User verify Return airport " + retAirport + " is selected in Bag o Tron Section on Optional Service Page",
                    drpdnRetAirport.getFirstSelectedOption().getText(),retAirport);

            //store trip type in global variable
            scenarioContext.setContext(Context.HOMEPAGE_TRIP_TYPE, "RoundTrip");
        }else{
            //store trip type in global variable
            scenarioContext.setContext(Context.HOMEPAGE_TRIP_TYPE, "OneWay");
        }


    }


    public synchronized void selectDates(String depDate, String retDate){
        //select dep date
        optionalServicesPage.getNewTripDepartingDateTextBox().sendKeys(TestUtil.getStringDateFormat(depDate,"MM/dd/yyyy"));

        //store dep date
        scenarioContext.setContext(Context.HOMEPAGE_DEP_DATE, depDate);
        scenarioContext.setContext(Context.HOMEPAGE_TRIP_TYPE,"OneWay");

        //set global variable for 24 hour flight
        if(depDate.equalsIgnoreCase("0")){
            scenarioContext.setContext(Context.HOMEPAGE_CHECK_IN, "24Hours");
        }else{
            scenarioContext.setContext(Context.HOMEPAGE_CHECK_IN, "Outside24Hours");
        }
        //verify dep date
        ValidationUtil.validateTestStep("User selected Departure Date as "+ TestUtil.getStringDateFormat(depDate,"MM/dd/yyyy") + " in Bag o Tron Section on Optional Service Page", true);

        //check ret date is required
        if(!retDate.equalsIgnoreCase("NA")){
            optionalServicesPage.getNewTripReturningDateTextBox().sendKeys(TestUtil.getStringDateFormat(retDate,"MM/dd/yyyy"));

            //store ret date
            scenarioContext.setContext(Context.HOMEPAGE_ARR_DATE, retDate);
            scenarioContext.setContext(Context.HOMEPAGE_TRIP_TYPE,"RoundTrip");

            //verify dep date
            ValidationUtil.validateTestStep("User selected Return Date as "+ TestUtil.getStringDateFormat(retDate,"MM/dd/yyyy") + " in Bag o Tron Section on Optional Service Page", true);
        }

    }

    public synchronized void selectTravellingPassenger(String strAdultCount,String strChildCount,String strInfantSeatCount,String strInfantLapCount){

        //store pax details on global variable
        scenarioContext.setContext(Context.HOMEPAGE_ADULT_COUNT, strAdultCount);
        scenarioContext.setContext(Context.HOMEPAGE_CHILD_COUNT, strChildCount);
        scenarioContext.setContext(Context.HOMEPAGE_INFANTSEAT_COUNT, strInfantSeatCount);
        scenarioContext.setContext(Context.HOMEPAGE_INFANTLAP_COUNT, strInfantLapCount);

        int passengerCounter = 0;
        String airportValue = "";
        List<WebElement> allPassenger = null;

        int intChildCount      = Integer.parseInt(strChildCount);
        int intInfantSeatCount = Integer.parseInt(strInfantSeatCount);
        int intInfantLapCount  = Integer.parseInt(strInfantLapCount);

        int intTotalChild    = (intChildCount + intInfantSeatCount + intInfantLapCount);
        String strTotalChild = Integer.toString(intTotalChild);

        Select drpdnAdult = new Select(optionalServicesPage.getNewTripAdultCountDropDown());

        //get all return airport
        allPassenger  = drpdnAdult.getOptions();

        //loop through all return airport
        for(passengerCounter=0;passengerCounter<allPassenger.size();passengerCounter++){
            //check ret airports
            if(allPassenger.get(passengerCounter).getText().contains(strAdultCount)){
                //store return airport code value
                airportValue = allPassenger.get(passengerCounter).getText();

                //break loop
                break;
            }
        }

        //select dep airport
        drpdnAdult.selectByVisibleText(airportValue);

        //wait for 0.5 sec
        WaitUtil.untilTimeCompleted(500);

        //verify dep airport is selected
        ValidationUtil.validateTestStep("User verify Adult:" + strAdultCount + " is selected in Bag o Tron Section on Optional Service Page",
                drpdnAdult.getFirstSelectedOption().getText(),strAdultCount);


        if(!strTotalChild.equalsIgnoreCase("0")){
            Select drpdnChild= new Select(optionalServicesPage.getNewTripChildCountDropDown());

            //get all return airport
            allPassenger  = drpdnChild.getOptions();

            //loop through all return airport
            for(passengerCounter=0;passengerCounter<allPassenger.size();passengerCounter++){
                //check ret airports
                if(allPassenger.get(passengerCounter).getText().contains(strTotalChild)){
                    //store return airport code value
                    airportValue = allPassenger.get(passengerCounter).getText();

                    //break loop
                    break;
                }
            }

            //select dep airport
            drpdnChild.selectByVisibleText(airportValue);

            //wait for 0.5 sec
            WaitUtil.untilTimeCompleted(500);

            //verify dep airport is selected
            ValidationUtil.validateTestStep("User verify Child:" + strTotalChild + " is selected in Bag o Tron Section on Optional Service Page",
                    drpdnChild.getFirstSelectedOption().getText(),strTotalChild);
        }

    }

    public synchronized void enterPromoCode(String promoCode){

        optionalServicesPage.getBNewTripPromoCodeTextBox().sendKeys(promoCode);

        ValidationUtil.validateTestStep("User verify Promo Code:" + promoCode + " is entered in Bag o Tron Section on Optional Service Page",
                true);

    }

    public synchronized void selectFreeSpiritMiles(){

        optionalServicesPage.getNewTripPurchaseFlightMilesCheckBox().click();

        ValidationUtil.validateTestStep("User verify Free Spirit Miles Checkbox is selected in Bag o Tron Section on Optional Service Page",
                true);
    }

    public synchronized void selectBagoTronButton(String buttonValue){

        if(buttonValue.equalsIgnoreCase("DisplayBagPrice")){
            optionalServicesPage.getBagOTronDisplayBagPriceButton().click();

            ValidationUtil.validateTestStep("User verify Display Bag Price is selected in Bag o Tron Section on Optional Service Page",
                    true);

        }else if(buttonValue.equalsIgnoreCase("UpdateBagPrice")){
            optionalServicesPage.getBagOTronUpdateBagPriceButton().click();

            ValidationUtil.validateTestStep("User verify Update Bag Prices is selected in Bag o Tron Section on Optional Service Page",
                    true);
        }else if(buttonValue.equalsIgnoreCase("StartOver")){
            optionalServicesPage.getBagOTronStartOverButton().click();

            ValidationUtil.validateTestStep("User verify Start over is selected in Bag o Tron Section on Optional Service Page",
                    true);
        }

        //wait till page load is complete
        WaitUtil.untilPageLoadComplete(driver,(long)120);
    }

    public synchronized void selectBookTrip(){
        //clicked on book trip
        optionalServicesPage.getBagOTronBookTripButton().click();

        //verify user cliked on book trip
        ValidationUtil.validateTestStep("User verify clicked Book Trip button in Bag o Tron Section on Optional Service Page",
                true);

        WaitUtil.untilTimeCompleted(2000);
        //wait for page load is complete
        WaitUtil.untilPageLoadComplete(driver);
    }


    public synchronized void displayExistingTripBagPrice(){
        //enter last name
        optionalServicesPage.getExistingTripLastNameTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_LASTNAME).toString());

        //enter confirmation code
        optionalServicesPage.getExistingTripConfirmationCodeTextBox().sendKeys(scenarioContext.getContext(Context.CONFIRMATION_CODE).toString());

        ValidationUtil.validateTestStep("User verify Enter Last Name and Confirmation Code in Bag o Tron Section on Optional Service Page",
                true);
    }


    public synchronized void setBagOTronBagPrices(String journeyType, String pathType, String bagsFare){
        //declare variable used in method
        int tripBagStartCounter = 0;
        String bagsPrice        = "";
        double cuuBagPrice      = 0;
        int journeyCounter      = 0;

        if(journeyType.equalsIgnoreCase("Dep")){
            journeyCounter = 1;
        }else if(journeyType.equalsIgnoreCase("Ret")){
            journeyCounter = 2;
        }


        if(pathType.equalsIgnoreCase("BookingPath")){
            tripBagStartCounter =2;
        }else if(pathType.equalsIgnoreCase("ManageTravelPath")){
            tripBagStartCounter =4;
        }else if(pathType.equalsIgnoreCase("CheckInPath")){
            tripBagStartCounter =6;
        }

        for(int tripCounter=tripBagStartCounter;tripCounter<=tripBagStartCounter+1;tripCounter++){
            for(int bagCounter=1;bagCounter<=4;bagCounter++){

                WebElement bagPriceSection = driver.findElement(By.xpath("(//table)[" + journeyCounter + "]/tbody//tr[" + bagCounter +"]/td[" + tripCounter + "]/p"));

                cuuBagPrice = Double.parseDouble(bagPriceSection.getText().replace("$",""));
                bagsPrice = bagsPrice + cuuBagPrice + "|";

            }
            bagsPrice = bagsPrice+ cuuBagPrice+ "|" + cuuBagPrice;

            double newDouble = 0;
            String newString = "";
            String lstBagPrice[] = bagsPrice.split("\\|");
            for(int priceCounter=0;priceCounter<lstBagPrice.length;priceCounter++){

                if(priceCounter==1){
                    newDouble = Double.parseDouble(lstBagPrice[priceCounter]);
                }else if(priceCounter>1){
                    newDouble = newDouble + Double.parseDouble(lstBagPrice[priceCounter]);
                }

                if(priceCounter<=1){
                    newString = newString + lstBagPrice[priceCounter] + "|";
                }else{
                    newString = newString + newDouble + "|";
                }
            }

            newString = newString.substring(0,newString.length()-1);

            if(bagsFare.equalsIgnoreCase("Standard")&& tripCounter > tripBagStartCounter){
                if(journeyType.equalsIgnoreCase("Dep")){
                    scenarioContext.setContext(Context.BAG_O_TRON_DEP_BAG_PRICE,newString + "||" + bagsPrice);
                }else if(journeyType.equalsIgnoreCase("Ret")){
                    scenarioContext.setContext(Context.BAG_O_TRON_RET_BAG_PRICE,newString+ "||" + bagsPrice);
                }
            }else if(bagsFare.equalsIgnoreCase("Member") && tripCounter ==tripBagStartCounter){
                if(journeyType.equalsIgnoreCase("Dep")){
                    scenarioContext.setContext(Context.BAG_O_TRON_DEP_BAG_PRICE,newString+ "||" + bagsPrice);
                }else if(journeyType.equalsIgnoreCase("Ret")){
                    scenarioContext.setContext(Context.BAG_O_TRON_RET_BAG_PRICE,newString+ "||" + bagsPrice);
                }
            }
            bagsPrice = "";
        }
    }


    public synchronized String getBagOTronBagPrice(String journeyType, String bagsPriceType, String carryBagCount, String checkedBagCount){

        //declare constant used in method
        final String DOUBLE_SEPARATOR   = "\\|\\|";
        final String SINGLE_SEPARATOR   = "\\|";
        final int FIRST_INDEX           = 0;
        final int SECOND_INDEX          = 1;

        String currBagPrice             = "";
        String allBagPrices[]           = null;
        String finalBagPrice            = "";
        double SelectedBag              = 0.0;


        if(journeyType.equalsIgnoreCase("Dep")){

            if(bagsPriceType.equalsIgnoreCase("EachBagPrice")){
                currBagPrice = scenarioContext.getContext(Context.BAG_O_TRON_DEP_BAG_PRICE).toString().split(DOUBLE_SEPARATOR)[SECOND_INDEX];
            }else if(bagsPriceType.equalsIgnoreCase("TotalBagPrice")){
                currBagPrice = scenarioContext.getContext(Context.BAG_O_TRON_DEP_BAG_PRICE).toString().split(DOUBLE_SEPARATOR)[FIRST_INDEX];
            }
        }else if(journeyType.equalsIgnoreCase("Ret")){
            if(bagsPriceType.equalsIgnoreCase("EachBagPrice")){
                currBagPrice = scenarioContext.getContext(Context.BAG_O_TRON_RET_BAG_PRICE).toString().split(DOUBLE_SEPARATOR)[SECOND_INDEX];
            }else if(bagsPriceType.equalsIgnoreCase("TotalBagPrice")){
                currBagPrice = scenarioContext.getContext(Context.BAG_O_TRON_RET_BAG_PRICE).toString().split(DOUBLE_SEPARATOR)[FIRST_INDEX];
            }
        }


        if(!(carryBagCount.equalsIgnoreCase("0") && checkedBagCount.equalsIgnoreCase("0")) ){

            allBagPrices = currBagPrice.split(SINGLE_SEPARATOR);

            if(carryBagCount.equalsIgnoreCase("0")){
                finalBagPrice = allBagPrices[0];
            }else{
                finalBagPrice = "00.00";
            }

            if(checkedBagCount.equalsIgnoreCase("0")){
                for(int checkedBagCounter=1;checkedBagCounter<=5;checkedBagCounter++){
                    finalBagPrice = finalBagPrice + "|" + allBagPrices[checkedBagCounter];
                }
            }else{
                SelectedBag = Double.parseDouble(allBagPrices[Integer.parseInt(checkedBagCount)]);

                for(int checkedBagCounter=1;checkedBagCounter<=5;checkedBagCounter++){

                    if(Double.parseDouble(allBagPrices[checkedBagCounter]) - SelectedBag <= 0){
                        finalBagPrice = finalBagPrice + "|" + "00.00";
                    }else{
                        finalBagPrice = finalBagPrice + "|" + TestUtil.convertTo2DecimalValue(Double.parseDouble(allBagPrices[checkedBagCounter]) - SelectedBag );
                    }

                }
            }

            return finalBagPrice;
        }else{
            return currBagPrice;
        }

    }

}
