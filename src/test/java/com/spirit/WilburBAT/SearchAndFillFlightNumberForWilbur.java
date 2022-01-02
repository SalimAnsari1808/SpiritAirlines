package com.spirit.WilburBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.managers.PageObjectManager;
import com.spirit.utility.ExcelUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class SearchAndFillFlightNumberForWilbur extends BaseClass {

    @Test
    public void findAndFillFlightNumberForWilBur(){
        initializeAPITesting();
        pageMethodManager.getAPIMethods().createSession();
        String sheet = "WilburBat";
        HashMap<String,Integer> flightTypeDataMap = new HashMap<>();
        ExcelUtil excel = new ExcelUtil("TESTDATA.xlsx");
        for (int i = 2; i <= excel.getRowCount(sheet) ;i++){
            String flightType = String.valueOf((int) Double.parseDouble(excel.getCellData(sheet,"FLIGHT_TYPE",i)));
            String depCode = excel.getCellData(sheet, "DEP_AIRPORT_CODE",i);
            String arrCode = excel.getCellData(sheet, "ARR_AIRPORT_CODE",i);
            int adtCount = (int) Double.parseDouble(excel.getCellData(sheet, "ADULTS",i));
            int chdCount = (int) Double.parseDouble(excel.getCellData(sheet, "CHILD",i));
            if (!flightTypeDataMap.containsKey(flightType+depCode+arrCode)){
                flightTypeDataMap.put(flightType+depCode+arrCode,adtCount+chdCount);
            }else{
                int paxCount = flightTypeDataMap.get(flightType+depCode+arrCode);
                flightTypeDataMap.put(flightType+depCode+arrCode, paxCount + adtCount + chdCount);
            }
        }
        HashMap<String,List<Integer>> seatsAvailableListMap = new HashMap<>();
        HashMap<String,List<String>> flightNumberListMap = new HashMap<>();
        for (int i = 2; i <= excel.getRowCount(sheet);i++){
            String flightType = String.valueOf((int) Double.parseDouble(excel.getCellData(sheet,"FLIGHT_TYPE",i)));
            System.out.println(flightType);
            String depCode = excel.getCellData(sheet, "DEP_AIRPORT_CODE",i);
            String arrCode = excel.getCellData(sheet, "ARR_AIRPORT_CODE",i);
            String depDate = String.valueOf((int) Double.parseDouble(excel.getCellData(sheet,"DEP_DATE",i)));
            String arrDate = String.valueOf((int) Double.parseDouble(excel.getCellData(sheet,"ARR_DATE",i)));
            int rowNum = (int) Double.parseDouble(excel.getCellData(sheet, "ROW",i));
            int adtCount = (int) Double.parseDouble(excel.getCellData(sheet, "ADULTS",i));
            int chdCount = (int) Double.parseDouble(excel.getCellData(sheet, "CHILD",i));
            if (!seatsAvailableListMap.containsKey(flightType+depCode+arrCode)){
                List<String> flightNumberList = pageMethodManager.getAPIMethods().getFlightNumberListByFlightType(depCode,arrCode,depDate,flightType);
                if (flightNumberList.size() == 0 && depDate.equals("0")){
                    flightNumberList = pageMethodManager.getAPIMethods().getFlightNumberListByFlightType(depCode,arrCode,"1",flightType);
                }
                if (flightNumberList.size() == 0){
                    throw new IllegalArgumentException("There is no flight on the current selected date");
                }
                flightNumberListMap.put(flightType+depCode+arrCode,flightNumberList);
                seatsAvailableListMap.put(flightType+depCode+arrCode,pageMethodManager.getAPIMethods().seatsLeftByFlightNumber(depCode,arrCode,depDate,flightNumberList));
            }
            int flightIndex = 0;
            System.out.println(seatsAvailableListMap);
            while (seatsAvailableListMap.get(flightType+depCode+arrCode).get(flightIndex) < adtCount+chdCount){
                flightIndex++;
                if(seatsAvailableListMap.get(flightType+depCode+arrCode).size() < flightIndex){
                    //TODO:: runs out of seats for the selected day
                    throw new IllegalArgumentException("The current selected date does not have enough seats for booking");
                }
            }
            seatsAvailableListMap.get(flightType+depCode+arrCode).set(flightIndex, seatsAvailableListMap.get(flightType+depCode+arrCode).get(flightIndex)-adtCount-chdCount);
            excel.setCellData(sheet,"FLIGHT_NUM",rowNum + 1,flightNumberListMap.get(flightType+depCode+arrCode).get(flightIndex));
            excel = new ExcelUtil("TESTDATA.xlsx");
        }
    }

}
