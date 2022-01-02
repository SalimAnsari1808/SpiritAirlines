package com.spirit.dataProviders;

import com.spirit.utility.*;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.*;
import org.testng.annotations.DataProvider;
import java.lang.reflect.*;
import java.util.*;

public class DataGenerator {

        String FileName = "TESTDATA.xlsx";
        ExcelUtil testData = new ExcelUtil(FileName);
        String sheetName = "WilburBat";
//        int rowNum = 1;


        @DataProvider(name="dp")
        public Object[][] getData(Method m) {

//        String sheetName = m.getName();
        int rows = testData.getRowCount(sheetName);
        int cols = testData.getColumnCount(sheetName);

        Object[][] data = new Object[rows - 1][1];

        Hashtable<String,String> table;

        for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

            table = new Hashtable<>();

            for (int colNum = 0; colNum < cols; colNum++) {

                // data[0][0]
                table.put(testData.getCellData(sheetName, colNum, 1), testData.getCellData(sheetName, colNum, rowNum));
                data[rowNum - 2][0] = table;
            }

        }

        return data;

    }



}
