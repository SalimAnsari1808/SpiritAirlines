package com.spirit.WilburBAT;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataProviders.DataGenerator;
import com.spirit.utility.ExcelUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import io.restassured.response.Response;
import org.testng.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class WilburBat extends BaseClass {
	/******************************************************************************
	 ***********************Set-Up / Configurations********************************
	 ******************************************************************************/

	String platform = "NA";
	@Parameters({"platform"})
	@Test(dataProviderClass = DataGenerator.class, dataProvider = "dp")

	public void WilburBat(Hashtable<String,String> data){

		if(!data.get("Runmode").equals("Y")){
			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		getExtText().pass("Generating Pnr for Test Case ID "+ data.get("TestID") +" "+ data.get("Test_Case_Name"));

		String DEP_AIRPORT_CODE = data.get("DEP_AIRPORT_CODE");
		String ARR_AIRPORT_CODE = data.get("ARR_AIRPORT_CODE");
		String DEP_DATE         = String.valueOf(((int) Double.parseDouble(data.get("DEP_DATE"))));
		String FLIGHT_NUM       = String.valueOf(((int) Double.parseDouble(data.get("FLIGHT_NUM"))));
		String ADULTS           = String.valueOf(((int) Double.parseDouble(data.get("ADULTS"))));
		String CHILD            = String.valueOf(((int) Double.parseDouble(data.get("CHILD"))));
		String LAP_CHILD		= String.valueOf(((int) Double.parseDouble(data.get("LAPCHILD"))));
		String DEP_BAGS         = data.get("DEP_BAGS");
		String ROW              = String.valueOf(((int) Double.parseDouble(data.get("ROW"))));
		String cardType			= data.get("CARD_TYPE");
		String logIn			= data.get("LOGIN");
		String seatType			= data.get("SEATS");


		initializeAPITesting();
		pageMethodManager.getAPIMethods().createSession();
		pageMethodManager.getAPIMethods().logIn(logIn);
		pageMethodManager.getAPIMethods().sellByFlightNumber(DEP_AIRPORT_CODE,ARR_AIRPORT_CODE,DEP_DATE,FLIGHT_NUM,ADULTS,CHILD);
		pageMethodManager.getAPIMethods().passengers(LAP_CHILD);
		pageMethodManager.getAPIMethods().ssrs();
		pageMethodManager.getAPIMethods().aPIBags(DEP_BAGS);
		pageMethodManager.getAPIMethods().aPISeats(seatType);
		Response aPIBookResponse = pageMethodManager.getAPIMethods().aPIbook(cardType);
		System.out.println("aPIBookResponse : " + aPIBookResponse.asString());
		String PNR = pageMethodManager.getAPIMethods().getPNRFromBook(aPIBookResponse);
		String LastName = pageMethodManager.getAPIMethods().getPrimaryPassengerLastName();
		String sheet = "WilburBat";
		ExcelUtil excel = new ExcelUtil("TESTDATA.xlsx");
		excel.setCellData(sheet, "PNR", Integer.parseInt(ROW)+1, PNR);
		excel.setCellData(sheet, "LastName", Integer.parseInt(ROW)+1, LastName);

	}

}

