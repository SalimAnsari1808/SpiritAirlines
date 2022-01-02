package com.spirit.dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spirit.dataType.*;
import com.spirit.managers.FileReaderManager;
import io.restassured.response.Response;



public class JsonDataReader {
	//Browser to be used during Parallel Execution
	private final String browserTypePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "BrowserCapability.json";
	private List<BrowserCapabilityData> browserTypeDataList;

	//Credit Card Data
	private final String cardDetailsFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "CardDetails.json";
	private List<CardDetailsData> cardDetailsDataList;

	//Login Credentials Data
	private final String memberEnrollmentFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "MemberEnrollment.json";
	private List<MemberEnrollmentData> memberEnrollmentDataList;

	//Member Enrollment Data
	private final String loginCredentialsFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "LoginCredentials.json";
	private List<LoginCredentialsData> loginCredentialsDataList;

	//Passenger information Data
	private final String passengerInfoFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "PassengerInfo.json";
	private List<PassengerInfoData> passengerInfoDataList;

	//Passenger fs enrollment
	private final String fSEnrollmentErrorFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "FSEnrollmentError.json";
	private List<FSEnrollmentErrorData> fSEnrollmentErrorDataList;

	//Car Details Data
	private final String carDetailsFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "CarDetails.json";
	private List<CarDetailsInfoData> carDetailsInfoDataList;

	//Member Enrollment Data
	private final String skySpeedLoginCredentialsFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "SkySpeedLoginCredentials.json";
	private List<SkySpeedLoginCredentialsData> skySpeedLoginCredentialsDataList;

	public JsonDataReader() {
		cardDetailsDataList 		= getCardDetailsData();
		browserTypeDataList 		= getBrowserCapabilitesData();
		loginCredentialsDataList 	= getLoginCredentialsData();
		memberEnrollmentDataList 	= getMemberEnrollmentData();
		passengerInfoDataList 		= getPassengerInfoData();
		fSEnrollmentErrorDataList 	= getFSEnrollmentErrorData();
		carDetailsInfoDataList 		= getCarDetailsInfoData();
		skySpeedLoginCredentialsDataList 	= getSkySpeedLoginCredentialsData();
	}


	/***************************************************************************************
	 *
	 * Browser Capability Data method
	 *
	 ***************************************************************************************/
	//this method used to read data from json file and store in form of list
	private List<BrowserCapabilityData> getBrowserCapabilitesData() {
		//creating Gson object
		Gson gson = new Gson();

		//create variable to store the json value
		BufferedReader bufferReader = null;
		try {
			//storing value into buffer reader
			bufferReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + browserTypePath));

			//store value in form of array
			BrowserCapabilityData[] broserCapabilityData = gson.fromJson(bufferReader, BrowserCapabilityData[].class);

			//return list
			return Arrays.asList(broserCapabilityData);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + browserTypePath);
		} finally {
			try {
				if (bufferReader != null) bufferReader.close();
			} catch (IOException ignore) {
			}
		}
	}

	//this method return the all value of the browser capability date which is required
	public BrowserCapabilityData getBrowserCapabilityByBrowserType(String browserType) {
		//accessing browser type
		for (BrowserCapabilityData browserCapabilityData : browserTypeDataList) {

			//if browser type is same as given browser then return its all value
			if (browserCapabilityData.browserType.equalsIgnoreCase(browserType)) {
				return browserCapabilityData;
			}
		}
		return null;
	}

	/***************************************************************************************
	 *
	 * Card Details data method
	 *
	 ***************************************************************************************/
	//getting all user preference data
	private List<CardDetailsData> getCardDetailsData() {

		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			//reading data from json file
			bufferReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + cardDetailsFilePath));

			//store into form of array
			CardDetailsData[] cardDetailsData = gson.fromJson(bufferReader, CardDetailsData[].class);

			return Arrays.asList(cardDetailsData);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + cardDetailsFilePath);
		} finally {
			try {
				if (bufferReader != null) bufferReader.close();
			} catch (IOException ignore) {
			}
		}
	}

	//return FSSignUpDate by its first name
	public CardDetailsData getCardDetailsByRequestType(String cardRequestType) {
		//accessing all data from user preference list
		for (CardDetailsData cardDetailsData : cardDetailsDataList) {
			//checking data
			if (cardDetailsData.requestType.equalsIgnoreCase(cardRequestType)) {
				return cardDetailsData;
			}
		}
		return null;
	}

	/***************************************************************************************
	 *
	 * Login credentials data method
	 *
	 ***************************************************************************************/
	//getting all user preference data
	private List<LoginCredentialsData> getLoginCredentialsData() {

		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			//reading data from json file
			bufferReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + loginCredentialsFilePath));

			//store into form of array
			LoginCredentialsData[] loginCredentialsData = gson.fromJson(bufferReader, LoginCredentialsData[].class);

			return Arrays.asList(loginCredentialsData);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + loginCredentialsFilePath);
		} finally {
			try {
				if (bufferReader != null) bufferReader.close();
			} catch (IOException ignore) {
			}
		}
	}

	//return FSSignUpDate by its first name
	public LoginCredentialsData getCredentialsByUserType(String loginCredentialsUserType) {
		//accessing all data from user preference list
		for (LoginCredentialsData loginCredentialsData : loginCredentialsDataList) {
			//checking data
			if (loginCredentialsData.userType.equalsIgnoreCase(loginCredentialsUserType)) {
				return loginCredentialsData;
			}
		}
		return null;
	}

	/***************************************************************************************
	 *
	 * Member Enrollment data method
	 *
	 ***************************************************************************************/
	//memberEnrollmentFilePath
	//MemberEnrollmentData
	//
	//getting all card data
	private List<MemberEnrollmentData> getMemberEnrollmentData() {

		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			//reading data from json file
			bufferReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + memberEnrollmentFilePath));

			//store into form of array
			MemberEnrollmentData[] memberEnrollmentData = gson.fromJson(bufferReader, MemberEnrollmentData[].class);

			return Arrays.asList(memberEnrollmentData);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + memberEnrollmentFilePath);
		} finally {
			try {
				if (bufferReader != null) bufferReader.close();
			} catch (IOException ignore) {
			}
		}
	}

	//return FSSignUpDate by its first name
	public MemberEnrollmentData getMemberEnrollmentDetailsByTab(String memberEnrollmentTabType) {
		//accessing all data from user preference list
		for (MemberEnrollmentData memberEnrollmentData : memberEnrollmentDataList) {
			//checking data
			if (memberEnrollmentData.requestType.equalsIgnoreCase(memberEnrollmentTabType)) {
				return memberEnrollmentData;
			}
		}
		return null;
	}

	/***************************************************************************************
	 *
	 * Passenger information data method
	 *
	 ***************************************************************************************/
	//getting all user preference data
	private List<PassengerInfoData> getPassengerInfoData() {

		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			//reading data from json file
			bufferReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + passengerInfoFilePath));

			//store into form of array
			PassengerInfoData[] passengerInfoData = gson.fromJson(bufferReader, PassengerInfoData[].class);

			return Arrays.asList(passengerInfoData);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + passengerInfoFilePath);
		} finally {
			try {
				if (bufferReader != null) bufferReader.close();
			} catch (IOException ignore) {
			}
		}
	}

	//return FSSignUpDate by its first name
	public PassengerInfoData getpassengerInfoByRequest(String passengerInfoType) {
		//accessing all data from user preference list
		for (PassengerInfoData passengerInfoData : passengerInfoDataList) {
			//checking data
			if (passengerInfoData.requestType.equalsIgnoreCase(passengerInfoType)) {
				return passengerInfoData;
			}
		}
		return null;
	}

	/***************************************************************************************
	 *
	 * FS Enrollment Errors data method
	 *
	 ***************************************************************************************/
	private List<FSEnrollmentErrorData> getFSEnrollmentErrorData() {

		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			//reading data from json file
			bufferReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + fSEnrollmentErrorFilePath));

			//store into form of array
			FSEnrollmentErrorData[] fSEnrollmentErrorData = gson.fromJson(bufferReader, FSEnrollmentErrorData[].class);

			return Arrays.asList(fSEnrollmentErrorData);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + fSEnrollmentErrorFilePath);
		} finally {
			try {
				if (bufferReader != null) bufferReader.close();
			} catch (IOException ignore) {
			}
		}
	}

	//return FSSignUpDate by its first name
	public FSEnrollmentErrorData getFSEnrollmentErrorByRequest(String fsEnroolmentErrorType) {
		//accessing all data from user preference list
		for (FSEnrollmentErrorData fSEnrollmentErrorData : fSEnrollmentErrorDataList) {
			//checking data
			if (fSEnrollmentErrorData.requestType.equalsIgnoreCase(fsEnroolmentErrorType)) {
				return fSEnrollmentErrorData;
			}
		}
		return null;
	}


	/***************************************************************************************
	 *
	 * Car data method
	 *
	 ***************************************************************************************/
	//getting all car preference data
	private List<CarDetailsInfoData> getCarDetailsInfoData() {

		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			//reading data from json file
			bufferReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + carDetailsFilePath));

			//store into form of array
			CarDetailsInfoData[] carDetailsInfoData = gson.fromJson(bufferReader, CarDetailsInfoData[].class);

			return Arrays.asList(carDetailsInfoData);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + carDetailsFilePath);
		} finally {
			try {
				if (bufferReader != null) bufferReader.close();
			} catch (IOException ignore) {
			}
		}
	}

	//return car data
	public CarDetailsInfoData getCarDetailsDataByRequest(String carDetailsInfoType) {
		//accessing all data from user preference list
		for (CarDetailsInfoData carDetailsInfoData : carDetailsInfoDataList) {
			//checking data
			if (carDetailsInfoData.requestType.equalsIgnoreCase(carDetailsInfoType)) {
				return carDetailsInfoData;
			}
		}
		return null;
	}


	/***************************************************************************************
	 *
	 * SkySpeed Login credentials data method
	 *
	 ***************************************************************************************/
	//getting all user preference data
	private List<SkySpeedLoginCredentialsData> getSkySpeedLoginCredentialsData() {

		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			//reading data from json file
			bufferReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + skySpeedLoginCredentialsFilePath));

			//store into form of array
			SkySpeedLoginCredentialsData[] skySpeedLoginCredentialsData = gson.fromJson(bufferReader, SkySpeedLoginCredentialsData[].class);

			return Arrays.asList(skySpeedLoginCredentialsData);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + skySpeedLoginCredentialsFilePath);
		} finally {
			try {
				if (bufferReader != null) bufferReader.close();
			} catch (IOException ignore) {
			}
		}
	}

	//return FSSignUpDate by its first name
	public SkySpeedLoginCredentialsData getSkySpeedCredentialsByUserType(String skySpeedLoginCredentialsUserType) {
		//accessing all data from user preference list
		for (SkySpeedLoginCredentialsData skySpeedLoginCredentialsData : skySpeedLoginCredentialsDataList) {
			//checking data
			if (skySpeedLoginCredentialsData.userType.equalsIgnoreCase(skySpeedLoginCredentialsUserType)) {
				return skySpeedLoginCredentialsData;
			}
		}
		return null;
	}

}

