package com.spirit.dataProviders;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;

import java.util.*;

public class APIJsonDataReader {
    /**
     * Common methods for extracting JsonElements for given key from a JsonElement
     * @param searchKey Key to search for
     * @param object Any JsonElement that needs to be decoded
     * @return List of JsonElements that matches the searchKey
     */
    public List<JsonElement> jsonExtractor (String searchKey, JsonElement object){
        //list for storing all the returning result
        List<JsonElement> result = new LinkedList<>();

        if (object instanceof JsonArray){
            for (int i = 0; i < object.getAsJsonArray().size(); i++){
                result.addAll(jsonExtractor(searchKey, object.getAsJsonArray().get(i)));
            }
        }else if (object instanceof JsonObject){
            Set<String> listOfKeys = object.getAsJsonObject().keySet();
            for(String key: listOfKeys){
                if (key.equals(searchKey)){
                    result.add(object.getAsJsonObject().get(key));
                    result.addAll(jsonExtractor(searchKey,object.getAsJsonObject().get(key)));
                }else{
                    result.addAll(jsonExtractor(searchKey,object.getAsJsonObject().get(key)));
                }
            }
        }
        return result;
    }

    public List<String> getFareAvailabilityKeyFromSearchData (Response searchResponse, int nth){
        List<String> result = new LinkedList<>();
        JsonElement jsonElement = new JsonParser().parse(searchResponse.asString()).getAsJsonObject();
        List<JsonElement> faresList = jsonExtractor("fares", jsonElement);
        List<JsonElement> fareAvailabilityKeys = jsonExtractor("fareAvailabilityKey",faresList.get(nth));
        for(JsonElement element: fareAvailabilityKeys){
            result.add(element.getAsString());
        }
        return result;
    }

    public List<String> getJourneyKeyFromSearchData(Response searchResponse){
        List<String> result = new LinkedList<>();
        JsonElement jsonElement = new JsonParser().parse(searchResponse.asString()).getAsJsonObject();
        List<JsonElement> journeyKeyList = jsonExtractor("journeyKey", jsonElement);
        for(JsonElement element: journeyKeyList){
            result.add(element.getAsString());
        }
        return result;
    }

    public double getBalanceDueFromBookingData (Response bookingResponse){
        JsonElement jsonElement = new JsonParser().parse(bookingResponse.asString()).getAsJsonObject();
        List<JsonElement> balanceDueList = jsonExtractor("balanceDue", jsonElement);
        for(JsonElement element: balanceDueList){
            return element.getAsDouble();
        }
        return 0;
    }

    public List<String> getIdentifierFromSearchData(Response searchResponse){
        List<String> result = new LinkedList<>();
        JsonElement jsonElement = new JsonParser().parse(searchResponse.asString()).getAsJsonObject();
        List<JsonElement> identifierList = jsonExtractor("identifier", jsonElement);
        for(JsonElement element: identifierList){
            if (element instanceof JsonObject){
                continue;
            }
            result.add(element.getAsString());
        }
        return result;
    }

    public List<JsonElement> getJourneysAvailableFromSearchData(Response searchResponse){
        APIJsonDataReader apiJsonDataReader = new APIJsonDataReader();
        JsonElement searchJsonElement = new JsonParser().parse(searchResponse.asString()).getAsJsonObject();
        List<JsonElement> journeysAvailable = apiJsonDataReader.jsonExtractor("journeysAvailable",searchJsonElement);
        return journeysAvailable;
    }
}
