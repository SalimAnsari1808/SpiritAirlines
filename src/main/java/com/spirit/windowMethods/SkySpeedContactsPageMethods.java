package com.spirit.windowMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataType.MemberEnrollmentData;
import com.spirit.managers.FileReaderManager;
import com.spirit.managers.WindowObjectManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.windowObjects.SkySpeedContactsPage;
import io.appium.java_client.windows.WindowsDriver;

public class SkySpeedContactsPageMethods {
    private WindowsDriver           driver;
    private WindowObjectManager     windowObjectManager;
    private ScenarioContext         scenarioContext;
    private SkySpeedContactsPage    skySpeedContactsPage;

    public SkySpeedContactsPageMethods(WindowsDriver driver, WindowObjectManager windowObjectManager, ScenarioContext scenarioContext){
        this.driver                 = driver;
        this.scenarioContext        = scenarioContext;
        this.windowObjectManager    = windowObjectManager;
        skySpeedContactsPage        = windowObjectManager.getSkySpeedContactsPage();
    }


    //**********************************************************************************************
    //Method Name: addContactMandatoryFields
    //Description: Method is used to add contact information of primary passenger
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 28-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void addContactMandatoryFields(){
        //click on Add contact info button
        skySpeedContactsPage.getAddContactButton().click();

        //clikced on add button
        ValidationUtil.validateTestStep("Clicked on Add Button on Contact Page", true);

        //fill contact info details
        fillContactMandatoryFields();

    }

    //**********************************************************************************************
    //Method Name: editContactMandatoryFields
    //Description: Method is used to edit contact information of primary passenger
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 28-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void editContactMandatoryFields(){
        //click on save contact information
        skySpeedContactsPage.getContactListText().get(0).click();

        //selected primary passenger from list
        ValidationUtil.validateTestStep("Selected Primary Passenger from Passenger list on Contact Page", true);

        //click on edit contact info button
        skySpeedContactsPage.getEditContactButton().click();

        //clikced on edit button
        ValidationUtil.validateTestStep("Clicked on Edit Button on Contact Page", true);

        //fill data for contact info
        fillContactMandatoryFields();
    }

    //**********************************************************************************************
    //Method Name: deleteContactMandatoryFields
    //Description: Method is used to deleet contact information of primary passenger
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 28-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void deleteContactMandatoryFields(){
        //select primary passenger contact details
        skySpeedContactsPage.getContactListText().get(0).click();

        //selected primary passenger from list
        ValidationUtil.validateTestStep("Selected Primary Passenger from Passenger list on Contact Page", true);

        //click on delete button
        skySpeedContactsPage.getDeleteContactButton().click();

        //clicked on edit button
        ValidationUtil.validateTestStep("Clicked on Delete Button on Contact Page", true);
    }

    //**********************************************************************************************
    //Method Name: fillContactMandatoryFields
    //Description: Method is used to fill contact information of primary passenger
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 28-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void fillContactMandatoryFields(){
        //get contact information from json file
        MemberEnrollmentData memberEnrollmentData = FileReaderManager.getInstance().getJsonReader().getMemberEnrollmentDetailsByTab("ContactTab");

        //enter titile
        //skySpeedContactsPage.getTitleTextBox().sendKeys();

        //enter first name
        //skySpeedContactsPage.getFirstNameTextBox().sendKeys();

        //enter last name
        //skySpeedContactsPage.getLastNameTextBox().sendKeys();

        //enter zip
        skySpeedContactsPage.getPostalCodeTextBox().sendKeys(memberEnrollmentData.zipCode);

        //enter address
        skySpeedContactsPage.getAddressLine1TextBox().sendKeys(memberEnrollmentData.address1);

        //enter city
        skySpeedContactsPage.getCityTextBox().sendKeys(memberEnrollmentData.city);

        //eneter state
        skySpeedContactsPage.getStateTextBox().sendKeys(memberEnrollmentData.stateCode);

        //enter country
        skySpeedContactsPage.getCountryTextBox().sendKeys(memberEnrollmentData.country);

        //enter phone number
        skySpeedContactsPage.getPhoneHomeTextBox().sendKeys(memberEnrollmentData.phoneNumber);

        //enter email
        skySpeedContactsPage.getEMailAddressTextBox().sendKeys("emailtesters@spirit.com");

        //enter distribution list
        skySpeedContactsPage.getItineraryDistributionDropDown().sendKeys("EMail");

        //click on save button
        skySpeedContactsPage.getSaveContactInfoButton().click();

        //enter contact infomationm
        ValidationUtil.validateTestStep("Contact informations are added on Contact Page", true);
    }

    //**********************************************************************************************
    //Method Name: clickNextContactPage
    //Description: Method is used to click next button on contact information page
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 28-Jan-2020
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void clickNextContactPage(){
        //click on Next button
        skySpeedContactsPage.getNextButton().click();

        //click on next button
        ValidationUtil.validateTestStep("Clicked on Next Button on Contact Page", true);
    }
}
