package com.spirit.misc.API_Testing;

import static org.hamcrest.Matchers.hasItems;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spirit.APIMethods.APIMethods;
import com.spirit.baseClass.BaseClass;
import com.spirit.dataProviders.APIJsonDataReader;
import com.spirit.dataProviders.JsonDataReader;
import com.spirit.enums.Context;
import com.sun.deploy.services.WPlatformService;
import io.restassured.response.Response;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class API_Testing extends BaseClass {
    @Parameters({"platform"})
    @Test
    public void Simple_Book_Retrieve_PNR_API_T(@Optional("NA") String platform){


        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        initializeAPITesting();
        pageMethodManager.getAPIMethods().createSession();
//        System.out.println(pageMethodManager.getAPIMethods().checkFlightTypeAvailability("FLL","TPA","8","NA","NA","9DFC"));
//        Response lowFareResponse = pageMethodManager.getAPIMethods().lowFare("FLL","TPA","1","3","2");
//        System.out.println(lowFareResponse.asString());
//        Response searchResponse = pageMethodManager.getAPIMethods().search("FLL","LAS","10","10");
//        System.out.println(searchResponse.asString());
//        JsonElement jsonElement = new JsonParser().parse(searchResponse.asString()).getAsJsonObject();
//        System.out.println(apiJsonDataReader.getIdentifierFromSearchData(searchResponse));
//        Response validateResponse = pageMethodManager.getAPIMethods().validate("10OFF","FLL","TPA","1","3");
//        System.out.println(validateResponse.asString());
//        System.out.println("Hotel availability : " + pageMethodManager.getAPIMethods().checkHotelAvailability("FLL","LAS","30","35"));
        System.out.println("sellByFlightNumber :" + pageMethodManager.getAPIMethods().sellByFlightNumber("FLL","LAS","1","777","3","0").asString());
        Response passengerResponse = pageMethodManager.getAPIMethods().passengers("3");
        System.out.println("passengerResponse : " + passengerResponse.asString());
        Response ssrsResponse = pageMethodManager.getAPIMethods().ssrs();
        System.out.println("ssrsResponse : " + ssrsResponse.asString());
        Response APIBagsResponse = pageMethodManager.getAPIMethods().aPIBags("Carry_1|Checked_2||Carry_0|Checked_2||Carry_0|Checked_1");
        System.out.println("APIBagsResponse : " + APIBagsResponse.asString());
        Response aPISeatsResponse = pageMethodManager.getAPIMethods().aPISeats("standard");
        System.out.println("aPISeatsResponse : " + aPISeatsResponse.asString());
        Response commitResponse = pageMethodManager.getAPIMethods().commit();
        System.out.println("commitResponse : " + commitResponse.asString());
        Response bookingResponse = pageMethodManager.getAPIMethods().aPIbooking();
        System.out.println("bookingResponse : " + bookingResponse.asString());
        Response aPIBookResponse = pageMethodManager.getAPIMethods().aPIbook("MasterCard");
        System.out.println("aPIBookResponse : " + aPIBookResponse.asString());

        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage("ENGLISH");
        String PNR = pageMethodManager.getAPIMethods().getPNRFromBook(aPIBookResponse);  
        scenarioContext.setContext(Context.CONFIRMATION_LASTNAME,"Test");
        scenarioContext.setContext(Context.CONFIRMATION_CODE,PNR);
        pageMethodManager.getHomePageMethods().loginToMyTrip();

//        pageMethodManager.getFlightAvailabilityMethods().selectFlightFareType();



//        Response sellResponse = pageMethodManager.getAPIMethods().sell();
//        pageMethodManager.getAPIMethods().checkIfFlightExist("FLL","DEN","2020-01-18","2020-01-20");

    }
}
