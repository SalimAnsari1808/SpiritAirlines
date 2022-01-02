package com.spirit.APIMethods;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spirit.baseClass.ScenarioContext;
import com.spirit.dataProviders.APIJsonDataReader;
import com.spirit.dataType.CardDetailsData;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.dataType.PassengerInfoData;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.ValidationUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.restassured.RestAssured.given;

public class APIMethods {

    private ScenarioContext scenarioContext;
    private final String ROOT_URL = "https://qa03dotrez.spirit.com";
    
    public APIMethods(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;
    }
    /**
     * This function search for flight with certain period of dates
     * @param dep Airport code for the departure flight
     * @param dest Airport code for the Arrival flight
     * @param beginDate in format "#" (# days from today)
     * @param endDate in format "#" (# days from today)
     * @return Rest assured response class Response from API /api/nsk/nk/availability/search
     */
    public synchronized Response search(String dep, String dest, String beginDate, String endDate){
        //SCENARIO_CONTEXT
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        String aPIPath = "/api/nsk/nk/availability/search";
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"criteria\":[{\"stations\":{\"originStationCodes\":[\""+ dep +"\"],\"destinationStationCodes\":[\""+ dest +"\"],\"searchDestinationMacs\":true,\"searchOriginMacs\":true},\"dates\":{\"beginDate\":\""+ dateFromToday(beginDate) +"\",\"endDate\":\""+ dateFromToday(endDate) +"\"}}],\"passengers\":{\"types\":[{\"type\":\"ADT\",\"count\":1}]},\"codes\":{\"currency\":\"USD\"},\"fareFilters\":{},\"taxesAndFees\":\"TaxesAndFees\",\"originalJourneyKeys\":[],\"originalBookingRecordLocator\":null,\"infantCount\":0,\"birthDates\":[]}")
                .when()
                .post(ROOT_URL + aPIPath);
        ValidationUtil.validateTestStep("API call \"/api/nsk/nk/availability/search\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        return resp;
    }

    /**
     * Initializing the token for a new session. SESSION_BEARER_TOKEN will be created
     */
    public synchronized void createSession(){
        String aPIPath = "/api/nsk/v1/token";
        Response resp = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("")
                .when()
                .post(ROOT_URL+ aPIPath);

        String BEARER_TOKEN = resp
                .jsonPath()
                .getMap("data")
                .get("token")
                .toString();

        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        scenarioContext.setContext(Context.SESSION_BEARER_TOKEN,BEARER_TOKEN);
        System.out.println("SESSION_BEARER_TOKEN : " + BEARER_TOKEN);
    }

    /**
     * Initializing the token for a new session. SESSION_BEARER_TOKEN will be created
     */
    public synchronized void createSession(String userName, String passWord){
        String aPIPath = "/api/nsk/v1/token";
        System.out.println("{\"credentials\":{\"username\":\"Webservices\",\"password\":\"Password1!\",\"domain\":\"EXT\"}}");
        Response resp = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"credentials\":{\"username\":\"Webservices\",\"password\":\"Password1!\",\"domain\":\"EXT\"}}")
                .when()
                .post(ROOT_URL+ aPIPath);

        System.out.println(resp.asString());
        String BEARER_TOKEN = resp
                .jsonPath()
                .getMap("data")
                .get("token")
                .toString();

        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        scenarioContext.setContext(Context.SESSION_BEARER_TOKEN,BEARER_TOKEN);
        System.out.println("SESSION_BEARER_TOKEN : " + BEARER_TOKEN);
    }

    /**
     * This function checks if flights exist on selected dates
     * @param dep departure flight airport code
     * @param ret returning flight airport code
     * @param depDate departure flight date
     * @param retDate returning flight date
     * @return true: if flight exists, false: if flight doesn't exists
     */
    public synchronized boolean checkIfFlightExist(String dep, String ret, String depDate, String retDate){
        Response searchResult = search(dep,ret,depDate,retDate);
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        List<String> journeyKeys = apiJsonDataReader.getJourneyKeyFromSearchData(searchResult);
        return (journeyKeys.size() > 0) ? true:false;
    }

    /**
     * This function makes the API call for lowfare
     * @param dep departure flight airport code
     * @param ret returning flight airport code
     * @param depDate departure flight date
     * @param retDate returning flight date
     * @return Response from API call
     */
    public synchronized Response lowFare(String dep, String ret, String depDate, String retDate, String selectedDate){
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        String aPIPath = "/api/nsk/nk/availability/lowfare";
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"criteria\":[{\"originStationCodes\":[\""+ dep +"\"],\"destinationStationCodes\":[\""+ ret +"\"],\"beginDate\":\""+ dateFromToday(depDate) +"\",\"endDate\":\""+ dateFromToday(retDate) +"\",\"selectedDate\":\""+ dateFromToday(selectedDate) +"\"}],\"passengers\":{\"types\":[{\"type\":\"ADT\",\"count\":1}]},\"codes\":{\"currency\":\"USD\"},\"filters\":{},\"includeTaxesAndFees\":true,\"infantCount\":0,\"birthDates\":[],\"originalBookingRecordLocator\":null,\"originalJourneyKeys\":[]}")
                .when()
                .post(ROOT_URL + aPIPath);
        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        return resp;
    }

    /**
     * This function makes the API call for validate
     * @param promoCode promotion code to check
     * @param dep departure flight airport code
     * @param ret returning flight airport code
     * @param depDate departure flight date
     * @param retDate returning flight date
     * @return Response from API call
     */
    public synchronized Response validate (String promoCode, String dep, String ret, String depDate, String retDate){
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        String aPIPath = "/api/nsk/nk/promotions/validate";
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"promoCode\":\""+ promoCode +"\",\"originStationCode\":\""+ dep +"\",\"destinationStationCode\":\""+ ret +"\",\"dates\":[\""+ dateFromToday(depDate) +"\",\""+ dateFromToday(retDate) +"\"]}")
                .when()
                .post(ROOT_URL+ aPIPath);
        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);

        return resp;
    }


    /**
     * This function calls the API ssrs and put ssrs information in state for purchasing
     * @return Response from the API call
     */
    public synchronized Response ssrs () {
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        String aPIPath = "/api/nsk/nk/passengers/ssrs";
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"passengers\":[{\"passengerKey\":\"MCFBRFQ-\",\"isHearingImpaired\":false,\"hasServiceAnimal\":false,\"hasPortableOxygen\":false,\"hasVisionDisability\":false,\"hasEmotionalSupportAnimal\":false,\"hasOther\":false,\"hasWheelchairToFromGate\":false,\"hasWheelchairCustomerAisleChair\":false,\"hasWheelchairStraightBack\":false,\"isVolunteer\":false,\"isMilitary\":false,\"hasPetInCabin\":false,\"hasWheelchairStoredInCabin\":false,\"hasWheelchairManuallyPowered\":false,\"hasWheelchairBatteryPoweredWetCell\":false,\"hasWheelchairBatteryPoweredDryGelCell\":false}]}")
                .when()
                .put(ROOT_URL + aPIPath);
        ValidationUtil.validateTestStep("API call \"" + aPIPath + "\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);

        return resp;
    }


    /**
     * This function makes the API call for commit
     * @return Response from the API call
     */
    public synchronized Response commit(){
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        String aPIPath = "/api/nsk/nk/package/type/commit";
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("")
                .when()
                .post(ROOT_URL + aPIPath);
        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        return resp;
    }

    /**
     * This function makes the API call for commit
     * @return Response from the API call
     */
    public synchronized Response retrieve(String PNR){
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        String aPIPath = "/api/nsk/v1/booking/retrieve/byRecordLocator";
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("")
                .when()
                .get(ROOT_URL + aPIPath + "/" + PNR);
        System.out.println(resp.asString());
        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        return resp;
    }

    /**
     * This function takes the log in user type and log into the account for the current session
     * @param loginUserType User type defined in the LoginCredentials.json
     * @return Response from the API call
     */
    public synchronized Response logIn (String loginUserType){
        if (loginUserType.equalsIgnoreCase("Guest")){
            return null;
        }
        scenarioContext.setContext(Context.HOMEPAGE_LOGINTYPE,loginUserType);
        String aPIPath = "/api/nsk/nk/token";
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType(loginUserType);
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"credentials\":{\"username\":\""+ loginCredentialsData.userName +"\",\"password\":\""+ loginCredentialsData.password +"\",\"domain\":\"WWW\",\"applicationName\":\"dotRezWeb\"},\"replacePrimaryPassenger\":false}")
                .when()
                .put(ROOT_URL + aPIPath);
        ValidationUtil.validateTestStep("API call for login was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        return resp;
    }

    /**
     * This function convert the date "#" into "yyyy-MM-dd" format
     * @param date
     * @return date # from today in format "yyyy-MM-dd" format
     */
    private String dateFromToday (String date){
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        c.add(Calendar.DATE,Integer.parseInt(date));
        return (dateFormat.format(c.getTime()));
    }

    /**
     * function for calling package/search API
     * @param dep departure airport code
     * @param dest destination airport code
     * @param depDate departure date from today
     * @param retDate returning date from today
     * @return Response from the API call
     */
    public synchronized Response packageSearchHotelPlusCar(String dep, String dest, String depDate, String retDate){
        String[] destCoord = getAirportCoordinate(dest);
        String aPIPath = "/api/nsk/nk/package/availability/search";
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"originCode\":\""+ dep +"\",\"destinationCode\":\""+ dest +"\",\"destLatitude\":\""+ destCoord[0] +"\",\"destLongitude\":\""+ destCoord[1] +"\",\"hotelCheckinDateTime\":\""+ dateFromToday(depDate) +"T23:03:00\",\"hotelCheckoutDateTime\":\""+ dateFromToday(retDate) +"T22:30:00\",\"carPickupDateTime\":\""+ dateFromToday(depDate) +"T23:03:00\",\"carDropoffDateTime\":\""+ dateFromToday(retDate) +"T22:30:00\",\"passengers\":[{\"age\":\"32\",\"type\":\"ADT\"}],\"hotelRooms\":\"1\",\"requestHotels\":true,\"requestCars\":true,\"requestFeatures\":true,\"packageType\":\"FHC\",\"all\":false}")
                .when()
                .post(ROOT_URL + aPIPath);
        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        return resp;
    }

    /**
     * Function that returns if there are any hotel availability for certain period
     * @param dep departure airport code
     * @param dest destination airport code
     * @param depDate departure date from today
     * @param retDate returning date from today
     * @return true if hotel available for that period, false if not
     */
    public boolean checkHotelAvailability(String dep, String dest, String depDate, String retDate){
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        Response resp = packageSearchHotelPlusCar(dep,dest,depDate,retDate);
        JsonElement jsonElement = new JsonParser().parse(resp.asString()).getAsJsonObject();
        if (apiJsonDataReader.jsonExtractor("HotelAvailRS", jsonElement).get(0).isJsonNull()){
            return false;
        }
        return true;
    }

    /**
     * Function that get airport coordinate for making vacation search
     * @param dest destination airport code
     * @return coordinates in format of [Latitude][Longitude]
     */
    private String[] getAirportCoordinate(String dest){
        HashMap<String,String[]> map = new HashMap<>();
        map.put("LAS",new String[] {"360448N","1150908W"});
        map.put("MCO",new String[] {"282555N","0811929W"});
        map.put("CUN",new String[] {"210200N","0865200W"});
        return map.get(dest);
    }




    /*******************************************************
     *******************************************************
     **********API methods for flight availability**********
     *******************************************************
     *******************************************************
     */


    /**
     * This function checks if a specific day has a flight available for selection
     * @param depDate number of days from today for departure
     * @param retDate number of days from today for returning ("NA" if OneWay)
     * @param dep departure airport code
     * @param dest destination airport code
     * @param flightType acceptable flightType : "nonStop", "connecting", "through", ("NA" if not required)
     * @param flightClub acceptable flightClue : "9DFC", ("NA" if not required)
     * @return if there is a flight available for selection with given parameters
     */
    public boolean checkFlightTypeAvailability( String dep, String dest,String depDate,String retDate, String flightType, String flightClub){
        if (!retDate.equalsIgnoreCase("NA")){
            return checkFlightOptionsOnDate(depDate,dep,dest,flightType,flightClub) && checkFlightOptionsOnDate(retDate,dep,dest,flightType,flightClub);
        }else{
            return checkFlightOptionsOnDate(depDate,dep,dest,flightType,flightClub);
        }

    }

    /**
     * This function checks if a flight on a particular date and city pair exists with specific flightType/flightClub
     * @param date date of the flight
     * @param dep departure airport code
     * @param dest destination airport code
     * @param flightType acceptable flightType : "nonStop", "connecting", "through", ("NA" if not required)
     * @param flightClub acceptable flightClue : "9DFC", ("NA" if not required)
     * @return true: if flight exists, false: if flight not found
     */
    private boolean checkFlightOptionsOnDate(String date, String dep, String dest, String flightType, String flightClub){
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        Response depSearchResponse = search(dep,dest,date,date);
        JsonElement journeysAvailable = apiJsonDataReader.getJourneysAvailableFromSearchData(depSearchResponse).get(0);
        for(int i = 0; i < journeysAvailable.getAsJsonArray().size(); i++){
            boolean flightClubFound = false;
            boolean flightTypeFound = false;
            if (flightClub.equalsIgnoreCase("9DFC")){
                if (isClubFare(journeysAvailable.getAsJsonArray().get(i))){
                    flightClubFound = true;
                }
            }else{ // flightClub == "NA"
                // if flight is sold out, flightCLubFound = false, else true
                if (apiJsonDataReader.jsonExtractor("isClubFare",journeysAvailable.getAsJsonArray().get(i)).size() == 0){
                    flightClubFound = false;
                }else{
                    flightClubFound = true;
                }
            }
            flightTypeFound = isFlightType(journeysAvailable.getAsJsonArray().get(i),flightType);
            if (flightTypeFound && flightClubFound){
                return true;
            }
        }
        return false;
    }

    /**
     *  This function determines if a journey is a 9DFC club fare
     * @param journey journey JsonElement from api search Response
     * @return true if the journey is available for 9DFC
     */
    private boolean isClubFare(JsonElement journey){
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        // if the flight is sold out
        if (journey.getAsJsonObject().size() == 0){
            return false;
        }
        List<JsonElement> isClubFareList = apiJsonDataReader.jsonExtractor("isClubFare",journey);
        for(JsonElement isClubFare: isClubFareList){
            if (isClubFare.getAsBoolean()){
                return true;
            }
        }
        return false;
    }

    /**
     *  This function determines if a journey is certain flightType
     * @param journey journey JsonElement from api search Response
     * @param flightType available input: "NA", "Connecting", "through", "NonStop"
     * @return true: if flightType matches requirement. false if not.
     */
    private boolean isFlightType(JsonElement journey, String flightType){
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        // if the flight is sold out
        if (apiJsonDataReader.jsonExtractor("isClubFare",journey).size() == 0){
            return false;
        }
        if (flightType.equalsIgnoreCase("NA")){
            return true;
        }
        /*
        Case 1: stop == 0 : flightType == nonStop
        Case 2: stop == 1 && segments size == 2 : flightType == Connecting
        Case 3: stop == 1 && segments size == 1 : flightType == through
        Case 4: stops > 2 TODO:: implement it later for through-connecting / connecting-through
         */
        int journeyStops = apiJsonDataReader.jsonExtractor("stops",journey).get(0).getAsInt();
        int journeySegmentsSize = apiJsonDataReader.jsonExtractor("segments",journey).get(0).getAsJsonArray().size();
        if (journeyStops == 0 && flightType.equalsIgnoreCase("NonStop")){
            return true;
        }else if (journeyStops == 1 && journeySegmentsSize == 2 && flightType.equalsIgnoreCase("Connecting")){
            return true;
        }else if (journeyStops == 1 && journeySegmentsSize == 1 && flightType.equalsIgnoreCase("through")){
            return true;
        }
        return false;
    }

    /*******************************************************
     *******************************************************
     **********API methods for bookings*********************
     *******************************************************
     *******************************************************
     */

    /**
     * This function retrieves the primary passenger last name after booking
     * @return last name of the primary passenger
     */
    public String getPrimaryPassengerLastName(){
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        Response bookingResp = aPIbooking();
        JsonElement bookingJsonElement = new JsonParser().parse(bookingResp.asString()).getAsJsonObject();
        List<JsonElement> listOfPrimaryPaxInfo = apiJsonDataReader.jsonExtractor(getPassengerKey("Adult",0), bookingJsonElement);
        for (JsonElement primaryPaxInfo: listOfPrimaryPaxInfo){
            List<JsonElement> lastNameList = apiJsonDataReader.jsonExtractor("last", primaryPaxInfo);
            if (lastNameList.size() > 0){
                return lastNameList.get(0).getAsString();
            }
        }
        return null;
    }
    /**
     * This function retrieves the booking state and return the Response from the booking get request
     * @return Response from the API call
     */
    public synchronized Response aPIbooking(){
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        String aPIPath = "/api/nsk/nk/booking";
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("")
                .when()
                .get(ROOT_URL + aPIPath);
        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        System.out.println(resp.asString());
        return resp;
    }

    /**
     * This function retrieves the booking state and return the Response from the booking get request
     * @param cardType card type for payment
     * @return Response from the API call
     */
    //     * @param checkInOptionSelected Acceptable input: "PrePaidBPT", "notdecideBPT", "FreeBPT"
    //     * @param travelGuardSelected true: selected travel guard, false: not selected
    //     * @param trialClubMembershipSelected true: selected trial 9DFC, false: not selected
    public synchronized Response aPIbook(String cardType){
        Response bookingResponse = aPIbooking();
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        String aPIPath = "/api/nsk/nk/booking/book";
        CardDetailsData cardDetailsData = FileReaderManager.getInstance().getJsonReader().getCardDetailsByRequestType(cardType);
        String cardNumber = cardDetailsData.cardNumber;
        String expDate = convertCreditCardDate(cardDetailsData.expirationDate);
        String cardTypeShort = getCardType(cardDetailsData.cardName);
        String name    = cardDetailsData.cardHolderName;
        String cvv     = cardDetailsData.securityCode;
        double balanceDue = apiJsonDataReader.getBalanceDueFromBookingData(bookingResponse);
        System.out.println("Total balance Due: " + balanceDue);
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"payment\":{\"amount\":"+ balanceDue +",\"currencyCode\":\"USD\",\"creditCard\":{\"name\":\""+ name +"\",\"address\":{\"lineOne\":\"2800 Executive Way\",\"city\":\"Miramar\",\"postalCode\":\"33025\",\"provinceState\":\"FL\",\"countryCode\":\"US\"},\"number\":\"" + cardNumber + "\",\"cvv\":\""+ cvv +"\",\"expiration\":\""+ expDate +"\",\"cardType\":\""+ cardTypeShort +"\"},\"installments\":1,\"deviceFingerprintId\":\"9fcdb9b8-df60-4e9c-bc4e-54b4c2aebcf4\"},\"travelGuardSelected\":false,\"trialClubMembershipSelected\":false,\"checkInOptionSelected\":\"FreeBPT\",\"paymentType\":\"navitaire\",\"flow\":\"book\",\"travelGuardVersion\":2}")
                .when()
                .post(ROOT_URL + aPIPath);
        System.out.println(resp.asString());
        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        return resp;
    }

    private String getCardType(String cardName){
        if (cardName.equalsIgnoreCase("Visa")){
            return "VI";
        }else if (cardName.equalsIgnoreCase("Master")){
            return "MC";
        }
        return null;
    }

    /**
     * This function takes in date in MM/yy format and return in format yyyy-MM-ddTHH:mm:ss.sssZ
     * @param date in format MM/yy
     * @return date format required for payment
     */
    private String convertCreditCardDate (String date){
        String[] separated = date.split("/");
        return "20" + separated[1] + "-" + separated[0] + "-28T05:00:00.000Z";

    }

    /**
     * This function retrieve the PNR from the book API call response
     * @param bookResponse book response from /book API call
     * @return PNR for the current session booking
     */
    public String getPNRFromBook(Response bookResponse){
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        JsonElement bookJsonElement = new JsonParser().parse(bookResponse.asString()).getAsJsonObject();
        return apiJsonDataReader.jsonExtractor("recordLocator",bookJsonElement).get(0).getAsString();

    }

    /*******************************************************
     *******************************************************
     **********API methods for passengers*******************
     *******************************************************
     *******************************************************
     */

    /**
     * This function returns the nth passengerKey
     * @param type Acceptable input: "Adult" or "Child"
     * @param nth nth passenger
     * @return the passengerKey for API/passengers call
     */
    private String getPassengerKey(String type, int nth){
        List<String> numberList = Arrays.asList("MC","MS","Mi","My","NC","NS","Ni","Ny","OC","OS","Oi","Oy");
        if (type.equalsIgnoreCase("Adult")){
            return numberList.get(nth) + "FBRFQ-";
        }else if (type.equalsIgnoreCase("child")){
            return numberList.get(nth) + "FDSEQ-";
        }
        return null;
    }

    /**
     * This function calls the API passengers and put passenger information in state for purchasing
     * @return Response from the API call
     */
    public synchronized Response passengers (String infantCount){

        final String adtCount = scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString();
        final String chdCount = scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString();
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        StringBuilder passengersSB = new StringBuilder();
        String aPIPath = "/api/nsk/nk/passengers";
        Response personResponse = aPIPerson();
        // begin first passenger
        if (personResponse.getStatusCode() == 200 || personResponse.getStatusCode() == 201){
            JsonElement personJsonElement = new JsonParser().parse(personResponse.asString()).getAsJsonObject();
            String firstName = apiJsonDataReader.jsonExtractor("first",personJsonElement).get(0).getAsString();
            String lastName = apiJsonDataReader.jsonExtractor("last",personJsonElement).get(0).getAsString();
            String title = apiJsonDataReader.jsonExtractor("title",personJsonElement).get(0).getAsString();
            String programLevelCode = apiJsonDataReader.jsonExtractor("programLevelCode",personJsonElement).get(0).getAsString();
            String programCode = apiJsonDataReader.jsonExtractor("programCode",personJsonElement).get(0).getAsString();
            String programNumber = apiJsonDataReader.jsonExtractor("programNumber",personJsonElement).get(0).getAsString();
            String dateOfBirth = apiJsonDataReader.jsonExtractor("dateOfBirth",personJsonElement).get(0).getAsString();
            passengersSB.append("{\"passengerKey\":\""+ getPassengerKey("adult",0) +"\",\"passengerAlternateKey\":null,\"fees\":[],\"name\":{\"title\":\""+ title +"\",\"first\":\""+ firstName +"\",\"middle\":null,\"last\":\""+ lastName +"\",\"suffix\":null},\"passengerTypeCode\":\"ADT\",\"discountCode\":\"\",\"bags\":[],\"program\":{\"levelCode\":\""+ programLevelCode +"\",\"code\":\""+ programCode+"\",\"number\":\""+ programNumber+"\"},\"info\":{\"nationality\":\"\",\"residentCountry\":\"US\",\"gender\":\"Male\",\"dateOfBirth\":\""+ dateOfBirth +"\",\"familyNumber\":0},");
        }else{
            PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger1");
            passengersSB.append("{\"passengerKey\":\""+ getPassengerKey("adult",0) +"\",\"passengerAlternateKey\":null,\"fees\":[],\"name\":{\"title\":\"MR\",\"first\":\""+ passengerInfoData.firstName +"\",\"middle\":null,\"last\":\""+ passengerInfoData.lastName +"\",\"suffix\":null},\"passengerTypeCode\":\"ADT\",\"discountCode\":\"\",\"bags\":[],\"info\":{\"nationality\":\"\",\"residentCountry\":\"US\",\"gender\":\"Male\",\"dateOfBirth\":\"1984-08-18T10:00:00.000Z\",\"familyNumber\":0},");
        }
        for (int i = 1; i < Integer.parseInt(infantCount)+1;i++){
            PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger" + (i+1));
            passengersSB.append("\"infant\":{\"name\":{\"title\":\"MR\",\"first\":\""+ passengerInfoData.firstName+"\",\"middle\":null,\"last\":\""+ passengerInfoData.lastName+"\",\"suffix\":null},\"dateOfBirth\":\"02/06/2019\",\"gender\":\"Male\"},");
        }
        passengersSB.append("\"travelDocuments\":[],\"addresses\":[],\"weightCategory\":1}");

        for (int i = 1; i < Integer.parseInt(adtCount); i++){
            if(passengersSB.length() > 0){
                passengersSB.append(',');
            }
            PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger" + (i+1));
            passengersSB.append("{\"passengerKey\":\""+ getPassengerKey("adult",i) +"\",\"passengerAlternateKey\":null,\"fees\":[],\"name\":{\"title\":\"MR\",\"first\":\""+ passengerInfoData.firstName +"\",\"middle\":null,\"last\":\""+ passengerInfoData.lastName +"\",\"suffix\":null},\"passengerTypeCode\":\"ADT\",\"discountCode\":\"\",\"bags\":[],\"infant\":null,\"info\":{\"nationality\":\"\",\"residentCountry\":\"US\",\"gender\":\"Male\",\"dateOfBirth\":\"1984-08-18T10:00:00.000Z\",\"familyNumber\":0},\"travelDocuments\":[],\"addresses\":[],\"weightCategory\":1}");
        }
        for (int i = 0; i < Integer.parseInt(chdCount); i++){
            if(passengersSB.length() > 0){
                passengersSB.append(',');
            }
            PassengerInfoData passengerInfoData = FileReaderManager.getInstance().getJsonReader().getpassengerInfoByRequest("Passenger" + (Integer.parseInt(adtCount) + i + 1));
            passengersSB.append("{\"passengerKey\":\""+ getPassengerKey("child", Integer.parseInt(adtCount)+i) +"\",\"passengerAlternateKey\":null,\"fees\":[],\"name\":{\"title\":\"MR\",\"first\":\""+ passengerInfoData.firstName +"\",\"middle\":null,\"last\":\""+ passengerInfoData.lastName +"\",\"suffix\":null},\"passengerTypeCode\":\"CHD\",\"discountCode\":\"\",\"bags\":[],\"infant\":null,\"info\":{\"nationality\":\"\",\"residentCountry\":\"US\",\"gender\":\"Male\",\"dateOfBirth\":\"01/24/2014\",\"familyNumber\":0},\"travelDocuments\":[],\"addresses\":[],\"weightCategory\":1}");
        }
        String passengerOrdered = passengersSB.toString();
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"hasPassword\":false,\"password\":\"\",\"passengers\":["+ passengerOrdered +"],\"contact\":{\"name\":{\"title\":null,\"first\":\"Clark\",\"middle\":null,\"last\":\"Kent\",\"suffix\":null},\"address\":{\"lineOne\":\"2800 executive way\",\"countryCode\":\"US\",\"provinceState\":\"FL\",\"city\":\"Miramar\",\"postalCode\":\"33025\"},\"homePhone\":\"13053051234\",\"emailAddress\":\"Superman@spirit.com\",\"dateOfBirth\":\"1982-02-04T15:57:09.000Z\"}}")
                .when()
                .put(ROOT_URL + aPIPath);
        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);

        return resp;
    }

    public synchronized Response aPIPerson(){
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        String aPIPath = "/api/nsk/v1/user/person";
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("")
                .when()
                .get(ROOT_URL + aPIPath);
        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200 || resp.getStatusCode() == 401);
        return resp;
    }



    /***************************************
     **********API methods for bags*********
     ****************************************/

    /**
     * This function calls the API bags and put the bags information in state for purchasing
     * @return Response from the API call
     */
    public synchronized Response aPIBags (String bagsString){
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        final int adtCount = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString());
        final int chdCount = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString());
        String aPIPath = "/api/nsk/nk/booking/bags";
        StringBuilder finalBagsInfo = new StringBuilder();
        for (String sessionKey: (List<String>) scenarioContext.getContext(Context.SESSION_JOURNEY_KEY)){
            System.out.println(sessionKey);
            if (finalBagsInfo.length() != 0){
                finalBagsInfo.append(",");
            }
            finalBagsInfo.append(createBagsForJourney(bagsString,sessionKey));
        }
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"bags\":{"+ finalBagsInfo.toString() +"}}")
                .when()
                .put(ROOT_URL + aPIPath);
        System.out.println("{\"bags\":{"+ finalBagsInfo.toString() +"}}");
        System.out.println(resp.asString());
        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        return resp;
    }

    private String createBagsForJourney(String bagsString, String journeyString){
        final int adtCount = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString());
        final int chdCount = Integer.parseInt(scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString());
        List<List<Integer>> bagsList = extractBagsString(bagsString);
        StringBuilder bagsInfo = new StringBuilder();
        for (int i = 0; i < adtCount; i++){
            if (bagsInfo.length() > 0){
                bagsInfo.append(',');
            }
            bagsInfo.append("\""+getPassengerKey("adult",i)+"\":["+ bagsOrder(bagsList.get(0).get(i),bagsList.get(1).get(i)) +"]");
        }
        for (int i = 0; i < chdCount; i++){
            if (bagsInfo.length() > 0){
                bagsInfo.append(',');
            }
            bagsInfo.append("\""+getPassengerKey("child",adtCount+i)+"\":["+ bagsOrder(bagsList.get(0).get(adtCount+i),bagsList.get(1).get(adtCount+i)) +"]");
        }
        return "\""+ journeyString + "\":{"+ bagsInfo.toString() +"}";
    }

    /**
     * This function takes in a bagsString in format "Carry_#|Checked_#||Carry_#|Checked_#"
     * @param bagsString in format "Carry_#|Checked_#||Carry_#|Checked_#"
     * @return return list of list of integer where the first list contains number of carry on bags and second one contains number of checked bags
     */
    private List<List<Integer>> extractBagsString (String bagsString){
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); //first list for carry on
        result.add(new ArrayList<>()); //second list for checked bags

        //declare constant used in method
        final String CARRY_ON_BAG = "Carry_";
        final String CHECKED_BAG  = "Checked_";
        final String NULL_VALUE	  = "";
        final String DOUBLE_SAPRATOR = "\\|\\|";
        final String SINGLE_SAPRATOR = "\\|";

        //get all the Bags per passenger
        String[] allReturnBags = bagsString.split(DOUBLE_SAPRATOR);

        //loop through all passengers Bags
        for(String perPassengerBags : allReturnBags) {
            //get bag type and count
            String[] passengerBagsType = perPassengerBags.split(SINGLE_SAPRATOR);

            //loop through bag type to get the bag count
            for (String perPassengerBag : passengerBagsType) {
                //check Bag type to get the count
                if (perPassengerBag.contains(CARRY_ON_BAG)) {
                    //get carry bag count
                    result.get(0).add(Integer.parseInt(perPassengerBag.replace(CARRY_ON_BAG, NULL_VALUE).trim()));
                } else if (perPassengerBag.contains(CHECKED_BAG)) {
                    //get checked bag count
                    result.get(1).add(Integer.parseInt(perPassengerBag.replace(CHECKED_BAG, NULL_VALUE).trim()));
                }
            }
        }
        return result;
    }

    /**
     * this function returns a json String that contains numbers of carryOn and checkBags for bags API call
     * @param carryOn number of carry on bags
     * @param checkedBags number of checked bags
     * @return a json String for the bags ordered for a guest
     */
    private String bagsOrder (int carryOn,int checkedBags){
        StringBuilder bagsSB = new StringBuilder();
        if(carryOn == 1){
            bagsSB.append("{\"ssrCode\":\"BAGC\",\"count\":1}");
        }else if (carryOn > 1){
            throw new IllegalArgumentException("Please enter carryOn number less than or equals to 1");
        }
        for(int i = 0; i < checkedBags; i++){
            if (bagsSB.length() > 0){
                bagsSB.append(',');
            }
            bagsSB.append("{\"ssrCode\":\"BAG"+ (i+1) +"\",\"count\":1}");
        }
        return bagsSB.toString();
    }

    /*************************************
     ***********Sell Methods**************
     *************************************/

    public int seatsLeftByFlightNumber(String dep, String ret, String depDate, String flightNumber){
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        Response searchResponse = search(dep,ret,depDate,depDate);
        JsonElement searchJsonElement = new JsonParser().parse(searchResponse.asString()).getAsJsonObject();
        List<JsonElement> segmentsList = apiJsonDataReader.jsonExtractor("segments",searchJsonElement);
        for (JsonElement segments : segmentsList){
            JsonObject firstSegmentsJsonObject = segments.getAsJsonArray().get(0).getAsJsonObject();
            if (flightNumber.equals(apiJsonDataReader.jsonExtractor("identifier",firstSegmentsJsonObject).get(1).getAsString())){
                int seatsLeft = Integer.MAX_VALUE;
                for (int i = 0 ; i < segments.getAsJsonArray().size(); i++){
                    int cap = apiJsonDataReader.jsonExtractor("capacity", segments).get(i).getAsInt();
                    int sold = apiJsonDataReader.jsonExtractor("sold", segments).get(i).getAsInt();
                    seatsLeft = Math.min(seatsLeft,cap-sold);
                }
                return seatsLeft;
            }
        }
        return 0;
    }

    public List<Integer> seatsLeftByFlightNumber(String dep, String ret, String depDate, List<String> flightNumbers){
        List<Integer> result = new ArrayList<>();
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        Response searchResponse = search(dep,ret,depDate,depDate);
        JsonElement searchJsonElement = new JsonParser().parse(searchResponse.asString()).getAsJsonObject();
        List<JsonElement> segmentsList = apiJsonDataReader.jsonExtractor("segments",searchJsonElement);
        for (JsonElement segments : segmentsList){
            JsonObject firstSegmentsJsonObject = segments.getAsJsonArray().get(0).getAsJsonObject();
            for (String flightNumber: flightNumbers){
                if (flightNumber.equals(apiJsonDataReader.jsonExtractor("identifier",firstSegmentsJsonObject).get(1).getAsString())){
                    int seatsLeft = Integer.MAX_VALUE;
                    for (int i = 0 ; i < segments.getAsJsonArray().size(); i++){
                        int cap = apiJsonDataReader.jsonExtractor("capacity", segments).get(i).getAsInt();
                        int sold = apiJsonDataReader.jsonExtractor("sold", segments).get(i).getAsInt();
                        seatsLeft = Math.min(seatsLeft,cap-sold);
                    }
                    result.add(seatsLeft);
                }
            }
        }
        return result;
    }

    /**
     * This function gets a list of flight number by aircraft type
     * @param dep airport code for departure
     * @param ret airport code for arrival
     * @param depDate **IMPORTANT** if depDate == 0 || 1, It automatically checks if its within 24 hours window before adding flight
     * @param flightType Acceptable input "319", "320", "321"
     * @return List of flight number of the search result
     */
    public List<String> getFlightNumberListByFlightType(String dep, String ret, String depDate,String flightType){
        List<String> result = new ArrayList<>();
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        Response searchResponse = search(dep,ret,depDate,depDate);
        JsonElement searchJsonElement = new JsonParser().parse(searchResponse.asString()).getAsJsonObject();
        List<JsonElement> segmentsList = apiJsonDataReader.jsonExtractor("segments",searchJsonElement);
        for (JsonElement segments : segmentsList){
            String flightNumber = null;
            List<JsonElement> statusList = apiJsonDataReader.jsonExtractor("status", segments);
            for (JsonElement status:statusList){
                // if status == 1; flight is sold out
                if (status.getAsInt() == 1){
                    continue;
                }
            }
            JsonObject firstSegmentsJsonObject = segments.getAsJsonArray().get(0).getAsJsonObject();
            String equipmentType = apiJsonDataReader.jsonExtractor("equipmentType",firstSegmentsJsonObject).get(0).getAsString();
            if (    (flightType.equals("319") && equipmentType.equals("319")) ||
                    (flightType.equals("320") && (equipmentType.equals("32A") || equipmentType.equals("32N")) ||
                    (flightType.equals("321") &&equipmentType.equals("32B")))){
                flightNumber = apiJsonDataReader.jsonExtractor("identifier",firstSegmentsJsonObject).get(1).getAsString();
                String departureTime = apiJsonDataReader.jsonExtractor("departureTime",firstSegmentsJsonObject).get(0).getAsString();
                if (!checkIfTimeWithin24Hrs(departureTime) && (depDate == "0" || depDate == "1")){
                    continue;
                }
                result.add(flightNumber);
            }
        }


        return result;
    }

    private boolean checkIfTimeWithin24Hrs(String flightTime){
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
        final long MILLIS_FOUR_HOURS = 4 * 60 * 60 * 1000L;
        String[] strDate = flightTime.split("T");
        String formattedFlightTime = strDate[0]+strDate[1];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(formattedFlightTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar flightCal = Calendar.getInstance();
        flightCal.setTime(date);
        Calendar currentCalendar = Calendar.getInstance();
        if (flightCal.getTimeInMillis() - currentCalendar.getTimeInMillis() > MILLIS_FOUR_HOURS && flightCal.getTimeInMillis() - currentCalendar.getTimeInMillis() < MILLIS_PER_DAY){
            return true;
        }
        return false;
    }

    /**
     *
     * This function takes select flight based on particular flight number provided
     * @param dep departure flight airport code
     * @param ret returning flight airport code
     * @param depDate departure flight date
     * @param flightNumber flight number (without NK) for selecting
     * @param adtCount number of adults for booking
     * @param chdCount number of children for booking
     * @return Response from the API call
     */
    public synchronized Response sellByFlightNumber(String dep, String ret, String depDate,String flightNumber, String adtCount, String chdCount){
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        Response searchResponse = search(dep,ret,depDate,depDate);
        JsonElement searchJsonElement = new JsonParser().parse(searchResponse.asString()).getAsJsonObject();
        List<JsonElement> journeySegmentsList = apiJsonDataReader.jsonExtractor("segments",searchJsonElement);
        for(int i = 0; i < journeySegmentsList.size(); i++){
            if (journeySegmentsList.get(i).getAsJsonArray().size()>0){
                List<JsonElement> identifierList = apiJsonDataReader.jsonExtractor("identifier",journeySegmentsList.get(i));
                for(JsonElement identifier: identifierList) {
                    if (identifier instanceof JsonObject) {
                        continue;
                    }
                    if (identifier.getAsString().equalsIgnoreCase(flightNumber)) {
                        return sell(dep, ret, depDate, adtCount, chdCount, i);
                    }
                }
            }
        }
        return null;
    }

    /**
     * This function makes the API call for sell (sell puts the booking in state for purchasing)
     * @param dep departure flight airport code
     * @param ret returning flight airport code
     * @param depDate departure flight date
     * @param nth select the nth flight from search API result
     * @param adtCount number of adults for booking
     * @param chdCount number of children for booking
     * @return Response from API call
     */
    public synchronized Response sell (String dep, String ret, String depDate,String adtCount,String chdCount, int nth){
        APIJsonDataReader jsonDataReader = new APIJsonDataReader();

        String aPIPath = "/api/nsk/nk/trip/sell";
        Response searchResponse = search(dep,ret,depDate,depDate);
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        List<String> journeyKeys = new ArrayList<>();

        if (scenarioContext.getContext(Context.SESSION_JOURNEY_KEY)!=null){
            journeyKeys = (List<String>) scenarioContext.getContext(Context.SESSION_JOURNEY_KEY);
        }
        journeyKeys.add(jsonDataReader.getJourneyKeyFromSearchData(searchResponse).get(nth));
        scenarioContext.setContext(Context.HOMEPAGE_ADULT_COUNT, adtCount);
        scenarioContext.setContext(Context.HOMEPAGE_CHILD_COUNT, chdCount);
        scenarioContext.setContext(Context.SESSION_JOURNEY_KEY, journeyKeys);
        scenarioContext.setContext(Context.SESSION_FARE_AVAILABILITY_KEY, jsonDataReader.getFareAvailabilityKeyFromSearchData(searchResponse,nth).get(0));
        String paxType = "{\"type\":\"ADT\",\"count\":"+ adtCount +"}";
        if (!chdCount.equals("0")){
            paxType = paxType + ",{\"type\":\"CHD\",\"count\":"+ chdCount +"}";
        }
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"preventOverlap\":true,\"keys\":[{\"journeyKey\":\""+ jsonDataReader.getJourneyKeyFromSearchData(searchResponse).get(nth) +"\",\"fareAvailabilityKey\":\""+ jsonDataReader.getFareAvailabilityKeyFromSearchData(searchResponse,nth).get(0) +"\",\"standardKey\":\""+ jsonDataReader.getFareAvailabilityKeyFromSearchData(searchResponse,nth).get(0) + "\"}],\"suppressPassengerAgeValidation\":true,\"passengers\":{\"types\":[" + paxType + "]},\"currencyCode\":\"USD\",\"infantCount\":0}")
                .when()
                .post(ROOT_URL + aPIPath);
        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        System.out.println(scenarioContext.getContext(Context.SESSION_JOURNEY_KEY));
        return resp;
    }

//    public synchronized Response sellByFareType(String dep, String ret, String depDate, String retDate,String clubFare, String adtCount, String chdCount){
//        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
//        Response searchResponse = search(dep,ret,depDate,retDate);
//        JsonElement searchJsonElement = new JsonParser().parse(searchResponse.asString()).getAsJsonObject();
//        List<JsonElement> journeySegmentsList = apiJsonDataReader.jsonExtractor("details",searchJsonElement);
//        for(int i = 0; i < journeySegmentsList.size(); i++){
//            if (journeySegmentsList.get(i).getAsJsonArray().size()>0){
//                List<JsonElement> identifierList = apiJsonDataReader.jsonExtractor("identifier",journeySegmentsList.get(i));
//                for(JsonElement identifier: identifierList) {
//                    if (identifier instanceof JsonObject) {
//                        continue;
//                    }
//                    if (identifier.getAsString().equalsIgnoreCase(flightNumber)) {
//                        return sell(dep, ret, depDate, retDate, adtCount, chdCount, i);
//                    }
//                }
//            }
//        }
//        return null;
//    }

    /*****************************
     *********Seats API***********
     ******************************/

    /**
     * This function calls the API seats and put the seats information in state for purchasing
     * @param seatType acceptable input: "Standard", "exitRow", "BFS" (big front seat), "NA"
     * @return Response from the API call
     */
    public synchronized Response aPISeats(String seatType){
        final String adtCount = scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString();
        final String chdCount = scenarioContext.getContext(Context.HOMEPAGE_CHILD_COUNT).toString();
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        Response seatMapsResponse = getSeatMaps();
        JsonElement seatMapsJsonElement = new JsonParser().parse(seatMapsResponse.asString()).getAsJsonObject();
        List<JsonElement> listOfSeatUnits = apiJsonDataReader.jsonExtractor("units", seatMapsJsonElement);
        StringBuilder seatJsonSB = new StringBuilder();
        for (JsonElement seatUnits: listOfSeatUnits){
            if (seatJsonSB.length() != 0){
                seatJsonSB.append(",");
            }
            seatJsonSB.append(addSeatsForPassengers(seatUnits,adtCount,chdCount,seatType));
        }
        String aPIPath = "/api/nsk/nk/passengers/seats";
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"passengerSeatRequests\":["+ seatJsonSB.toString() +"]}")
                .when()
                .put(ROOT_URL + aPIPath);
        System.out.println(seatJsonSB.toString());
        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        return resp;
    }

    /**
     * get the seat maps for current session
     * @return response from API call seatmaps
     */
    public synchronized Response getSeatMaps(){
        String bearerToken = scenarioContext.getContext(Context.SESSION_BEARER_TOKEN).toString();
        String aPIPath = "/api/nsk/nk/booking/seatmaps";
        Response resp = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("")
                .when()
                .get(ROOT_URL + aPIPath);
        ValidationUtil.validateTestStep("API call \""+ aPIPath +"\" was successful, Status Code: " + resp.getStatusCode(), resp.getStatusCode() <= 202 && resp.getStatusCode() >= 200);
        return resp;
    }


    /**
     * This function takes in the seat map for a certain flight and return the Json element in string form for booking
     * @param seatUnits seat map for a certain flight in JsonArray format
     * @param adtCount number of adults
     * @param chdCount number of children
     * @param seatType type of seat needs to be selected
     * @return JsonElement in string format for booking
     */
    private String addSeatsForPassengers(JsonElement seatUnits, String adtCount, String chdCount, String seatType){
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        StringBuilder seatSB = new StringBuilder();
        JsonArray arrayOfSeatUnits = seatUnits.getAsJsonArray();
        for (int i = 0; i < Integer.parseInt(adtCount); i ++){
            if (seatSB.length() != 0){
                seatSB.append(",");
            }
            for (JsonElement seat: arrayOfSeatUnits){
                JsonObject seatObject = seat.getAsJsonObject();
                if (getSeatType(seatObject).equalsIgnoreCase(seatType)){
                    seatSB.append("{\"unitKey\":\""+ apiJsonDataReader.jsonExtractor("unitKey",seat).get(0).getAsString() +"\",\"passengerKey\":\""+ getPassengerKey("Adult", i) +"\"}");
                    seat.getAsJsonObject().addProperty("availability",1);
                    break;
                }
            }
        }
        for (int i = 0; i < Integer.parseInt(chdCount); i ++){
            if (seatSB.length() != 0){
                seatSB.append(",");
            }
            for (JsonElement seat: arrayOfSeatUnits){
                JsonObject seatObject = seat.getAsJsonObject();
                if (getSeatType(seatObject).equalsIgnoreCase(seatType)){
                    seatSB.append("{\"unitKey\":\""+ apiJsonDataReader.jsonExtractor("unitKey",seat).get(0).getAsString() +"\",\"passengerKey\":\""+ getPassengerKey("Child", i+Integer.parseInt(adtCount)) +"\"}");
                    seat.getAsJsonObject().addProperty("availability",1);
                    break;
                }
            }
        }
        return seatSB.toString();
    }

    /**
     * This function return the seat type for a certain seat
     * @param seat a JsonObject inside "units" JsonArray
     * @return "NA" if the seat is not available, "BFS" if seat is big front seat, "EXITROW" if the seat is exit row, and "STANDARD" if seat is standard
     */
    private String getSeatType(JsonObject seat){
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        if (apiJsonDataReader.jsonExtractor("availability",seat).get(0).getAsInt() == 1 || apiJsonDataReader.jsonExtractor("availability",seat).get(0).getAsInt() == 9){
            return "NA";
        }
        List<JsonElement> listOfCode = apiJsonDataReader.jsonExtractor("code",seat);
        for(JsonElement code: listOfCode){
            if (code.getAsString().equalsIgnoreCase("BFS")){
                return "BFS";
            }else if (code.getAsString().equalsIgnoreCase("EXITROW")){
                return "EXITROW";
            }
        }
        return "STANDARD";
    }


    /*****************************************************
     ***********Flight Availability Methods***************
     *****************************************************/

    public String getFareClassByFlightNumber(String depCode, String retCode, String date, String flightNumber){
        Response searchResponse = search(depCode,retCode,date,date);
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        JsonElement searchJsonElement = new JsonParser().parse(searchResponse.asString()).getAsJsonObject();
        JsonArray journeysAvailable = apiJsonDataReader.jsonExtractor("journeysAvailable",searchJsonElement).get(0).getAsJsonArray();
        for(int i = 0; i < journeysAvailable.size(); i++){
            if (journeysAvailable.get(i).getAsJsonObject().size()>0){
                List<JsonElement> identifierList = apiJsonDataReader.jsonExtractor("identifier",journeysAvailable.get(i));
                List<JsonElement> fareClassList = apiJsonDataReader.jsonExtractor("fareClassOfService",journeysAvailable.get(i));
                for(JsonElement identifier: identifierList) {
                    if (identifier instanceof JsonObject) {
                        continue;
                    }
                    if (identifier.getAsString().equalsIgnoreCase(flightNumber)) {
                        for(JsonElement fareClass: fareClassList){
                                return fareClass.getAsString();
                        }
                    }
                }
            }
        }

        return null;

    }


}
