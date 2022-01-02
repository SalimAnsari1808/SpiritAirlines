package com.spirit.pageMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.enums.*;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.CarPage;
import com.spirit.pageObjects.HotelPage;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HotelPageMethods {

    WebDriver driver;
    PageObjectManager pageObjectManager;
    ScenarioContext scenarioContext;
    HotelPage hotelPage;

    public HotelPageMethods(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
        this.driver=driver;
        this.pageObjectManager = pageObjectManager;
        this.scenarioContext = scenarioContext;
        hotelPage = pageObjectManager.getHotelPage();
    }

    //**********************************************************************************************
    //Method Name: selectHotelRoomHotelPage
    //Description: Method is used to select Hotel on Hotel Page
    //Input Arguments: String->hotelName,   String -> roomType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void selectHotelRoomHotelPage(String hotelName,String roomType){

        final int FIRST_INDEX    = 0;
        if(hotelName.equalsIgnoreCase("NA")){
            hotelPage.getHotelViewButton().get(FIRST_INDEX).click();
            WaitUtil.untilPageLoadComplete(driver);
        }else {

            hotelPage.getHotelNameTextBox().sendKeys(hotelName);

            WaitUtil.untilPageLoadComplete(driver);


            for (int hotelCount = 0;hotelCount<hotelPage.getHotelCard().size();hotelCount++){
                if(hotelPage.getHotelNamesText().get(hotelCount).getText().toUpperCase().contains(hotelName.toUpperCase())){
                    hotelPage.getHotelViewButton().get(hotelCount).click();
                    ValidationUtil.validateTestStep(hotelName+" Hotel Found",true);
                    WaitUtil.untilPageLoadComplete(driver);

                    break;
                }else {
                    ValidationUtil.validateTestStep(hotelName+" Hotel Not Found",false);
                }
            }
        }
//        WaitUtil.untilElementIsClickable(driver, By.xpath(hotelPage.xpath_HotelPopUpSelectRoomsFromLinkButton));
        WaitUtil.untilTimeCompleted(1000);

        JSExecuteUtil.clickOnElement(driver,hotelPage.getHotelPopUpSelectRoomsFromButton());

        //store hotel details
        storeHotelDetails();

        if(roomType.equalsIgnoreCase("NA")){

            hotelPage.getHotelPopUpSelectRoomButton().get(FIRST_INDEX).click();
            WaitUtil.untilPageLoadComplete(driver);
        }else {
            for (int roomCount = 0;roomCount<hotelPage.getHotelPopUpSelectRoomButton().size();roomCount++){
                if(hotelPage.getHotelPopUpRoomTypeText().get(roomCount).getText().toUpperCase().contains(roomType.toUpperCase())){
                    hotelPage.getHotelPopUpSelectRoomButton().get(roomCount).click();
                    ValidationUtil.validateTestStep(roomType+" Room Found",true);
                    WaitUtil.untilPageLoadComplete(driver);

                    break;
                }else {
                    ValidationUtil.validateTestStep(roomType+" Room Not Found",false);
                }
            }
        }
        WaitUtil.untilTimeCompleted(1000);
    }

    //**********************************************************************************************
    //Method Name: selectHotelRoomHotelPage
    //Description: Method is used to select Hotel on Hotel Page
    //Input Arguments: String->hotelName,   String -> roomType
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public synchronized void selectHotelOnOptionPage(String hotelName,String roomType){

        hotelPage.getViewAllHotelsButton().click();

        WaitUtil.untilPageLoadComplete(driver);

        selectHotelRoomHotelPage(hotelName,roomType);
    }

    //**********************************************************************************************
    //Method Name: storeHotelDetails
    //Description: Method is used to store hotel information
    //Input Arguments: NA
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 08-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private synchronized void storeHotelDetails(){

        //HOTEL_DETAILS

        String hotelDetails;
        String hotelName, hotelLandmark, hotelAddress, roomType, roomNumber;

        //get hotel name
        hotelName = hotelPage.getHotelPopUpHotelNameText().getText();

        //store hotel name
        hotelDetails = "HotelName:" + hotelName;

        //get hotel landmark
        hotelLandmark = hotelPage.getHotelPopUpLandmarkNameText().getText();

        //store hotel landmark
        hotelDetails = hotelDetails + "|HotelLandmark:" + hotelLandmark;

        //get hotel address
        hotelAddress = hotelPage.getHotelPopUpAddressText().getText();

        //store hotel address
        hotelDetails = hotelDetails + "|HotelAddress:" + hotelAddress;

        //get room type
        roomType = hotelPage.getHotelPopUpRoomTypeText().get(0).getText().split("-")[0].trim();

        //store room type
        hotelDetails = hotelDetails + "|HotelRoomType:" + roomType;

        //get Room Number
        roomNumber = hotelPage.getHotelPopUpSelectRoomsFromButton().getText().split("\\(")[1].split("\\)")[0];

        //store room type
        hotelDetails = hotelDetails + "|HotelRoomCount:" + roomNumber;

        //store details in global variable
        scenarioContext.setContext(Context.HOTEL_DETAILS,hotelDetails);

        if(!scenarioContext.isContains(Context.HOMEPAGE_ROOMS)){
            //System.out.println(hotelPage.getHotelPopUpSelectRoomsFromButton().getText().split("\\(")[1].split("\\)")[0]);
            scenarioContext.setContext(Context.HOMEPAGE_ROOMS,hotelPage.getHotelPopUpSelectRoomsFromButton().getText().split("\\(")[1].split("\\)")[0]);

        }
    }



    public synchronized void verifySelectedHotelOptionPage(){

        final String SINGLE_SEPARATOR	= "\\|";
        final String DOUBLE_SEPARATOR	= "\\|\\|";

        //HotelName:The D Las Vegas|HotelLandmark:Fremont Street - Downtown|HotelAddress:301 Fremont Street, Las Vegas, United States, 89101|HotelRoomType:DOUBLE RUN OF HOUSE|HotelRoomCount:2

        for(String hotelDetails:scenarioContext.getContext(Context.HOTEL_DETAILS).toString().split(SINGLE_SEPARATOR)){

            switch(hotelDetails.split(":")[0]){
                case "HotelName":

                    ValidationUtil.validateTestStep("Verify Selected Hotel Name on Option Page",
                            hotelPage.getSelectedHotelNameText().getText().trim(),hotelDetails.replace("HotelName:",""));

                    break;
                case "HotelLandmark":

                    ValidationUtil.validateTestStep("Verify Selected Hotel Landmark on Option Page",
                            hotelPage.getSelectedHotelLankmarkText().getText().trim(),hotelDetails.replace("HotelLandmark:",""));

                    break;

                case "HotelAddress":


                    break;
            }
        }
    }


}