package com.spirit.windowMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.managers.WindowObjectManager;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import com.spirit.windowObjects.SkySpeedPassengerPage;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SkySpeedPassengerPageMethods {
    private WindowsDriver           driver;
    private WindowObjectManager     windowObjectManager;
    private ScenarioContext         scenarioContext;
    private SkySpeedPassengerPage   skySpeedPassengerPage;

    public SkySpeedPassengerPageMethods(WindowsDriver driver, WindowObjectManager windowObjectManager, ScenarioContext scenarioContext){
        this.driver                 = driver;
        this.scenarioContext        = scenarioContext;
        this.windowObjectManager    = windowObjectManager;
        skySpeedPassengerPage       = windowObjectManager.getSkySpeedPassengerPage();
    }

    //**********************************************************************************************
    //Method Name: fillPassengerMandatoryFields
    //Description: Method is used to select fill passenger mandatory details on Passenger Page
    //Input Arguments: String->tripType, String->flightType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 22-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void fillPassengerMandatoryFields(){

        //declare constant for infant is more or equal to 4 days
        final int INFANT_DOB_NUMBER = -14;

        //declare constant for child is more or equal to 5 years(1825 days)
        final int CHILD_DOB_NUMBER = -2190;

        PassengerInfoData passengerInfoData;

        for(int passengerCounter=0;passengerCounter<skySpeedPassengerPage.getPassengerTypeListText().size();passengerCounter++){
            passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger"+(passengerCounter+1));

            //check passenger details are not prefilled
            if(passengerCounter==0){
                if(skySpeedPassengerPage.getPassengerNameListText().get(0).getText().toString().length()>5){
                    continue;
                }
            }

            //select passenger from passenger list
            skySpeedPassengerPage.getPassengerTypeListText().get(passengerCounter).click();

            //enetr passenger title
            skySpeedPassengerPage.getPassengerTitleTextBox().sendKeys(passengerInfoData.title);

            //enter passenger first name
            skySpeedPassengerPage.getPassengerFirstNameTextBox().sendKeys(passengerInfoData.firstName);

            //enter passenger last name
            skySpeedPassengerPage.getPassengerLastNameTextBox().sendKeys(passengerInfoData.lastName);

            if(skySpeedPassengerPage.getPassengerTypeListText().get(passengerCounter).getText().contains("ADT")){
                //enter passenger DOB
                skySpeedPassengerPage.getPassengerDOBTextBox().sendKeys(TestUtil.convertDateToString(TestUtil.convertStringToDate(passengerInfoData.dob,"MM/dd/yyyy"), "dd MMM yyyy"));
            }else{
                //enter child DOB
                skySpeedPassengerPage.getPassengerDOBTextBox().sendKeys(TestUtil.getStringDateFormat(Integer.toString(CHILD_DOB_NUMBER+passengerCounter), "dd MMM yyyy"));
            }

            //passenger details added
            ValidationUtil.validateTestStep("Added Passenger details for PAssenger:"+(passengerCounter+1)+" on Passenger Page", true);

            if(!skySpeedPassengerPage.getPassengerTypeListText().get(passengerCounter).getText().contains("INF")){
                if(passengerCounter<Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_INFANTLAP_COUNT).toString())){

                    //Add Infant SSR
                    selectSSRPerPax("Infant");

                    //enter infant title
                    skySpeedPassengerPage.getInfantTitleTextBox().sendKeys(passengerInfoData.title);

                    //enter infant first name
                    skySpeedPassengerPage.getInfantFirstNameTextBox().sendKeys(passengerInfoData.firstName);

                    //enter infant last name
                    skySpeedPassengerPage.getInfantLastNameTextBox().sendKeys(passengerInfoData.lastName);

                    //enter infant DOB
                    skySpeedPassengerPage.getInfantDOBTextBox().sendKeys(TestUtil.getStringDateFormat((INFANT_DOB_NUMBER-passengerCounter),"dd MMM yyyy"));

                    //passenger details added
                    ValidationUtil.validateTestStep("Added Infant SSR for PAssenger:"+(passengerCounter+1)+" on Passenger Page", true);
                }
            }

            //click on save button
            skySpeedPassengerPage.getSaveButton().click();
        }
    }

    //**********************************************************************************************
    //Method Name: fillPassengerOverBookingMandatoryFields
    //Description: Method is used to select fill passenger mandatory details on Passenger Page
    //Input Arguments: String->tripType, String->flightType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 30-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void fillPassengerOverBookingMandatoryFields(){
        //getGroupBookingCheckBox

        PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");

        //select passenger from passenger list
        skySpeedPassengerPage.getPassengerTypeListText().get(0).click();

        //enetr passenger title
        skySpeedPassengerPage.getPassengerTitleTextBox().sendKeys(passengerInfoData.title);

        //enter passenger first name
        skySpeedPassengerPage.getPassengerFirstNameTextBox().sendKeys(passengerInfoData.firstName);

        //enter passenger last name
        skySpeedPassengerPage.getPassengerLastNameTextBox().sendKeys(passengerInfoData.lastName);

        //enter passenger DOB
        skySpeedPassengerPage.getPassengerDOBTextBox().sendKeys(TestUtil.convertDateToString(TestUtil.convertStringToDate(passengerInfoData.dob,"MM/dd/yyyy"), "dd MMM yyyy"));

        //passenger details added
        ValidationUtil.validateTestStep("Added First Passenger details for Group booking on Passenger Page", true);

        //click on group booking checkbox
        skySpeedPassengerPage.getGroupBookingCheckBox().click();

        //click on save button
        skySpeedPassengerPage.getSaveButton().click();

        WaitUtil.untilSkySpeedPageLoadComplete(driver);

    }

    //**********************************************************************************************
    //Method Name: selectSSRPerPax
    //Description: Method is used to select SSR's per Pax from SSR window on Passenger Page
    //Input Arguments: String->selectSSRList
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 22-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void selectSSRPerPax(String selectSSRList){

        if(selectSSRList.equalsIgnoreCase("NA")){
            return;
        }

        String[] perPaxSSR      = selectSSRList.split("\\|");
        String   propertyValue  = "";

        Actions action = new Actions(driver);

        //clicked on manage SSR button
        skySpeedPassengerPage.getManageSSRButton().click();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //loop through all SSR to get SSR code
        for(String valueSSR : perPaxSSR){
            if(valueSSR.equalsIgnoreCase("HearingDisability")){
                propertyValue = "DEAF";
            }else if(valueSSR.equalsIgnoreCase("VisionDisability")){
                propertyValue = "BLND";
            }else if(valueSSR.equalsIgnoreCase("ServiceAnimal")){
                propertyValue = "SRVA";
            }else if(valueSSR.equalsIgnoreCase("EmotionalSupportAnimal")){
                propertyValue = "ESAN";
            }else if(valueSSR.equalsIgnoreCase("PortableOxygenConcentrators")){
                propertyValue = "POCS";
            }else if(valueSSR.equalsIgnoreCase("WheelChairToFromGate")){
                propertyValue = "WCHR";
            }else if(valueSSR.equalsIgnoreCase("WheelChairNeedHelpToFromSeat")){
                propertyValue = "WCHC";
            }else if(valueSSR.equalsIgnoreCase("WheelChairCompletelyImmobile")){
                propertyValue = "WCHS";
            }else if(valueSSR.equalsIgnoreCase("IHaveMyOwnWheelChair")){
                propertyValue = "WCMB";
            }else if(valueSSR.equalsIgnoreCase("VoluntaryProvision")){
                propertyValue = "MEDA";
            }else if(valueSSR.equalsIgnoreCase("Other")){
                propertyValue = "OTHS";
            }else if(valueSSR.equalsIgnoreCase("Infant")){
                propertyValue = "INFT";
            }else if(valueSSR.equalsIgnoreCase("Selectee")){
                propertyValue = "SSSS";
            }else if(valueSSR.equalsIgnoreCase("UnAccompainedMinaor")){
                propertyValue = "UMNR";
            }else if(valueSSR.equalsIgnoreCase("nrCarryBag")){
                //Incase of Non revenue booking add on free carry on bag
                propertyValue = "BAGE";
            }else if(valueSSR.equalsIgnoreCase("nrCheckBag")){
                //Incase of Non revenue booking add on free Checkin bag
                propertyValue = "BAGN";
            }

            //select SSR on SSR window
            action.sendKeys(propertyValue).build().perform();

            //add SSR
            skySpeedPassengerPage.getManageSSRAddSSRButton().click();

            //click on SSR list on SSR window
            for(WebElement ssrList : skySpeedPassengerPage.getManageSSRAvailableSSRList()){
                if(ssrList.getText().equalsIgnoreCase(propertyValue)){
                    action.click(ssrList).build().perform();

                    break;
                }
            }
        }

        //close SSR window
        action.click(skySpeedPassengerPage.getManageSSRCloseButton()).build().perform();
    }

    //**********************************************************************************************
    //Method Name: selectSSRPerPax
    //Description: Method is used to select SSR's per Pax from SSR window on Passenger Page
    //Input Arguments: String->selectSSRList
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 22-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void selectSSR(String sSRValue){

        final String DOUBLE_SEPARATOR = "\\|\\|";

        String[] allPaxSSRValue = sSRValue.split(DOUBLE_SEPARATOR);

        //loop through all passenger
        for(int passengerCounter=0;passengerCounter<skySpeedPassengerPage.getPassengerTypeListText().size();passengerCounter++) {
            //slect passenger from passenger list
            skySpeedPassengerPage.getPassengerTypeListText().get(passengerCounter).click();

            //select SSR per passenger
            selectSSRPerPax(allPaxSSRValue[passengerCounter]);

            //SSR added
            ValidationUtil.validateTestStep("Added SSR's: " + allPaxSSRValue[passengerCounter] + " for Passenger:"+(passengerCounter+1)+" on Passenger Page", true);
        }
    }

    //**********************************************************************************************
    //Method Name: selectSSRPerPax
    //Description: Method is used to select SSR's per Pax from SSR window on Passenger Page
    //Input Arguments: String->selectSSRList
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 22-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void searchCustomerByLookUp(String lookUpSearch){

        Actions action = new Actions(driver);

        //click on LookUp
        action.click(skySpeedPassengerPage.getLookUpButton()).build().perform();

        if(lookUpSearch.equalsIgnoreCase("ContactInformation")){
            //click on contact information
            action.click(skySpeedPassengerPage.getLookUpPopUpContactRadioButton()).build().perform();

            //enter last anme
            skySpeedPassengerPage.getLookUpPopUpLastNameTextBox().sendKeys("Test");

            //enter first name
            skySpeedPassengerPage.getLookUpPopUpFirstNameTextBox().sendKeys("Joe");

            //enter phone number
            //skySpeedPassengerPage.getLookUpPopUpPhoneNumberTextBox().sendKeys();

            //enter email address
            skySpeedPassengerPage.getLookUpPopUpEmailAddressTextBox().sendKeys("test@test123.com");
        }else if(lookUpSearch.equalsIgnoreCase("CustomerProgram")){
            //click on customer program information
            //action.click(skySpeedPassengerPage.getLookUpPopUpCustomerProgramRadioButton()).build().perform();

            //select customer information from drop down
            //skySpeedPassengerPage.getLookUpPopUpCustomerProgramDropDown().sendKeys();

            //enter program number
            //skySpeedPassengerPage.getLookUpPopUpProgramNumberTextBox().sendKeys();
        }

        //click onLook Up button
        action.click(skySpeedPassengerPage.getLookUpPopUpLookUpButton()).build().perform();

        //wait for 2 sec
        WaitUtil.untilTimeCompleted(2000);

        //select from customer list
        //action.click(skySpeedPassengerPage.getLookUpPopUpCustomerListText().get(0)).build().perform();

        //click ok Button
        action.click(skySpeedPassengerPage.getLookUpPopUpOkButton()).build().perform();

        //enter passenger DOB
        skySpeedPassengerPage.getPassengerDOBTextBox().sendKeys("18 Aug 1981");

        //click on save button
        skySpeedPassengerPage.getSaveButton().click();
    }

    //**********************************************************************************************
    //Method Name: addTravelDocument
    //Description: Method is used to select Travel Document on Passenger Page
    //Input Arguments: String->selectSSRList
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 22-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void addTravelDocument(String documetType){

        final String SINGLE_SEPARATOR   = "\\|";
        final String DOUBLE_SEPARATOR   = "\\|\\|";
        PassengerInfoData passengerInfoData;

        String[] perPaxTravelDocumet = documetType.split(DOUBLE_SEPARATOR);
        String[] perTravelDocument  = null;

        //loop through all passengers
        for(int passengerCounter=0;passengerCounter<perPaxTravelDocumet.length;passengerCounter++){

            passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger"+(passengerCounter+1));

            //click on passenger from passenger list
            skySpeedPassengerPage.getPassengerTypeListText().get(passengerCounter).click();

            //get travel document per passenger
            perTravelDocument = perPaxTravelDocumet[passengerCounter].split(SINGLE_SEPARATOR);

            //declare variable used in method
            String travelDocumentType = "";
            String travelDocumentNumber = "";

            //loop through all travel document per passenger
            for(int passenterTravelDocumetCounter = 0;passenterTravelDocumetCounter<perTravelDocument.length;passenterTravelDocumetCounter++){
                //click on add travel document
                skySpeedPassengerPage.getAddTravelDocumentButton().click();

                //check travel document type
                if(perTravelDocument[passenterTravelDocumetCounter].equalsIgnoreCase("Passport")){
                    travelDocumentType = "Passport (P)";
                    travelDocumentNumber = passengerInfoData.paasportNumber;
                }else if(perTravelDocument[passenterTravelDocumetCounter].equalsIgnoreCase("KTN")){
                    travelDocumentType = "Known Traveler ID (K)";
                    travelDocumentNumber = passengerInfoData.ktNumber;
                }else{
                    ValidationUtil.validateTestStep("Method is only implemeneted for Passport and KTN and not implemented for " + perTravelDocument[passenterTravelDocumetCounter],false);
                }

                //select the document type
                skySpeedPassengerPage.getTravelDocumentTypeDropDownDropDown().sendKeys(travelDocumentType);

                //wait for 2 sec
                WaitUtil.untilTimeCompleted(2000);

                //select nationality
                skySpeedPassengerPage.getTravelDocumentsNationalityTextBox().sendKeys("United States of America (US)");

                //select birth country
                skySpeedPassengerPage.getTravelDocumentsBirthCountryTextBox().sendKeys("United States of America (US)");

                //enter travel document number
                skySpeedPassengerPage.getTravelDocumentsNumberTextBox().sendKeys(travelDocumentNumber);

                //select isssue country
                skySpeedPassengerPage.getTravelDocumentsIssueCountryTextBox().sendKeys("United States of America (US)");

                //enter issue date
                skySpeedPassengerPage.getTravelDocumentsExpDateTextBox().sendKeys(TestUtil.getStringDateFormat(Integer.toString(4190), "dd MMM yyyy"));

                //enter expire date
                skySpeedPassengerPage.getTravelDocumentsIssuedDateTextBox().sendKeys(TestUtil.getStringDateFormat(Integer.toString(-4190), "dd MMM yyyy"));

                //click on ok button
                skySpeedPassengerPage.getTravelDocumentsOkButton().click();

                //click on save button
                skySpeedPassengerPage.getSaveButton().click();
            }

            //add travel document
            ValidationUtil.validateTestStep("Added Travel Document:" + perPaxTravelDocumet[passengerCounter]+ " for Passenger:"+(passengerCounter+1)+" on Passenger Page", true);
        }
    }

    //**********************************************************************************************
    //Method Name: clickNextPassengerPage
    //Description: Method is used to select next button on Passenger Page
    //Input Arguments: String->selectSSRList
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 22-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void clickNextPassengerPage(){
        //click on Next button
        skySpeedPassengerPage.getCustomerInformationNextButton().click();

        //wait for page load
        WaitUtil.untilSkySpeedPageLoadComplete(driver);

        //click on next button
        ValidationUtil.validateTestStep("Clicked on Next Button on Passenger Page", true);
    }

}
