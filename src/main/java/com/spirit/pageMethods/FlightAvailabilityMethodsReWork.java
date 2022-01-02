package com.spirit.pageMethods;

import com.spirit.baseClass.ScenarioContext;
import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import com.spirit.managers.PageObjectManager;
import com.spirit.pageObjects.FlightAvailabilityPage;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.TestUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightAvailabilityMethodsReWork {

	WebDriver driver;
	PageObjectManager pageObjectManager;
	ScenarioContext scenarioContext;
	FlightAvailabilityPage flightAvailabilityPage;

	public FlightAvailabilityMethodsReWork(WebDriver driver, PageObjectManager pageObjectManager, ScenarioContext scenarioContext) {
		this.driver=driver;
		this.pageObjectManager = pageObjectManager;
		this.scenarioContext = scenarioContext;
		flightAvailabilityPage = pageObjectManager.getFlightAvailabilityPage();
	}

	public synchronized void selectFlightCheapCostlyType(String journey, String fareType, String flightCost){
		//declare variable used in method
		boolean selectedFlightFlag = false;
		int searchCounter = 3;

		WaitUtil.untilPageLoadComplete(driver);

		List<WebElement> allFlightRows = null;

		//check search criteria alue is overwritten or not
		if(scenarioContext.isContains(Context.AVAILABILITY_DATE_SEARCH_CRITERIA_OVERWRITE)){
			searchCounter = Integer.parseInt(scenarioContext.getContext(Context.AVAILABILITY_DATE_SEARCH_CRITERIA_OVERWRITE).toString());
		}

		//loop though complete month
		for (int calenderCounter = 0; calenderCounter < searchCounter; calenderCounter++) {

			//*****************************************************************
			//*************************Flight Week Calendar********************
			//*****************************************************************
			//declare variable used in method
			int calenderPrices = 0;
			Date journeyDate = null;
			boolean flightFound = false;
			List<WebElement> calenderBlock = null;

			//System.out.println(journey);

			//assign dep flight calendar
			calenderBlock = flightAvailabilityPage.getDepCalBlocksGridView();

			//assign dep or ret block based on journey type
			if (journey.equalsIgnoreCase("dep")) {
				//get dep date
				journeyDate = TestUtil.convertStringToDate(TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE), "E, MMM dd yyyy"), "E, MMM dd yyyy");
			} else if (journey.equalsIgnoreCase("ret")) {
				//get arr date
				journeyDate = TestUtil.convertStringToDate(TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE), "E, MMM dd yyyy"), "E, MMM dd yyyy");
			}

			//loop through all calendar block and check flight is available on selected date
			for (calenderPrices = 0; calenderPrices < calenderBlock.size(); calenderPrices++) {
				//check date block is visible. This block will handle Mobile Date block in which only 3 Date block is visible
				if (!calenderBlock.get(calenderPrices).isDisplayed()) {
					//move to next date block if date block is not visible
					continue;
				}

				//when flight prices are available then webelement count is 4 else 2
				List<WebElement> CalenderPrices = calenderBlock.get(calenderPrices).findElements(By.tagName("span"));

				//"i" tag will contains aircraft image in calendar blocks
				WebElement CalenderPlane = calenderBlock.get(calenderPrices).findElements(By.tagName("i")).get(0);

				//loop through all elements of calendar block
				for (WebElement calendarDateText : CalenderPrices) {
					if (calendarDateText.getText().contains(",")) {
						//get the date from calendar blocks
						Date calenderDate = TestUtil.convertStringToDate(calendarDateText.getText().trim(), "E, MMM dd");

//						System.out.println("Selected Departure Date is " + journeyDate);
//						System.out.println("Current Date is " + calenderDate);
//						System.out.println("Plane is visible " + CalenderPlane.getAttribute("class").contains("invisible"));
//						System.out.println("Prices is visible " + CalenderPrices.size());

						//check selected date is after journey date or same date
						if (calenderDate.after(journeyDate) && CalenderPlane.getAttribute("class").contains("invisible")) {
							if (!fareType.equalsIgnoreCase("9DFC")) {
								//System.out.println(CalenderPrices.size());
								if (CalenderPrices.size() >= 3) {
									//click on calendar date block
									calendarDateText.click();

									WaitUtil.untilPageLoadComplete(driver);

									//wait for 3 sec
									WaitUtil.untilTimeCompleted(3000);

									//traveling flight found
									flightFound = true;

									//break from per date block
									break;
								}
							} else {
								for (WebElement memberFare : CalenderPrices) {
									if (memberFare.getText().trim().contains("9FC")) {
										//click on calendar date block
										calendarDateText.click();

										WaitUtil.untilPageLoadComplete(driver);

										//wait for 3 sec
										WaitUtil.untilTimeCompleted(3000);

										//traveling flight found
										flightFound = true;

										//break from per date block
										break;
									}
								}
							}
						} else if (calenderDate.equals(journeyDate) && !CalenderPlane.getAttribute("class").contains("invisible")) {
							if (!fareType.equalsIgnoreCase("9DFC")) {
								//traveling flight found
								flightFound = true;

								//break from per date block
								break;
							} else {
								for (WebElement memberFare : CalenderPrices) {
									if (memberFare.getText().trim().contains("9FC")) {
										//traveling flight found
										flightFound = true;

										//break from per date block
										break;
									}
								}
							}
						}
					}//if block of calendar text data
				}//with in Date Block

				//check flight found flag is set
				if (flightFound) {
					//*****************************************************************
					//**********************Flight Availability Table******************
					//*****************************************************************
					//select flight rows depending upon journey
					allFlightRows = flightAvailabilityPage.getDepFlightRowsGridView();

					//loop through all flight rows
					//for(int flightRows=0;flightRows<allFlightRows.size();flightRows++){
					for (int flightRows = allFlightRows.size() - 1; flightRows >= 0; flightRows--) {
						//check flight is not sold out before selection
						if (allFlightRows.get(flightRows).getText().contains("Sold Out") || allFlightRows.get(flightRows).getText().contains("Agotado")) {
							continue;
						}

						//check flight is not already selected on manage Travel or check In path
						if (allFlightRows.get(flightRows).getText().contains("Original Choice") || allFlightRows.get(flightRows).getText().contains("Elección Original")) {
							continue;
						}

						List<WebElement> allFlightPrices = allFlightRows.get(flightRows).findElements(By.tagName("label"));

						//System.out.println(allFlightPrices.size());

						for (int flightPrice = allFlightPrices.size()-1 ;flightPrice >= 0; flightPrice--) {
							//allFlightPrices.get(flightPrice)
							if(allFlightPrices.get(flightPrice).isDisplayed()){
								if(fareType.equalsIgnoreCase("Standard") && allFlightPrices.get(flightPrice).getAttribute("for").contains("standard")){
									if(JSExecuteUtil.getElementBeforeProperty(driver, allFlightPrices.get(flightPrice), "background-color").contains("255, 255, 255")){
										if(flightCost.contains("Costly") && allFlightPrices.get(flightPrice).getText().contains("+")){
											allFlightPrices.get(flightPrice).click();
											return;
										}else if(flightCost.contains("Cheap") && allFlightPrices.get(flightPrice).getText().contains("-")){
											allFlightPrices.get(flightPrice).click();
											return;
										}else if(flightCost.contains("Same") && !allFlightPrices.get(flightPrice).getText().contains("+") && !allFlightPrices.get(flightPrice).getText().contains("-")){
											allFlightPrices.get(flightPrice).click();
											return;
										}
									}
								}else if(fareType.equalsIgnoreCase("9DFC") && allFlightPrices.get(flightPrice).getAttribute("for").contains("radio-club")){
									if(JSExecuteUtil.getElementBeforeProperty(driver, allFlightPrices.get(flightPrice), "background-color").contains("255, 255, 255")){
										if(flightCost.contains("Costly") && allFlightPrices.get(flightPrice).getText().contains("+")){
											allFlightPrices.get(flightPrice).click();
											return;
										}else if(flightCost.contains("Cheap") && allFlightPrices.get(flightPrice).getText().contains("-")){
											allFlightPrices.get(flightPrice).click();
											return;
										}else if(flightCost.contains("Same") && !allFlightPrices.get(flightPrice).getText().contains("+") && !allFlightPrices.get(flightPrice).getText().contains("-")){
											allFlightPrices.get(flightPrice).click();
											return;
										}
									}
								}
							}


						}


					}//flight found if block
					//set flight found flag to false for next flight row
					flightFound = false;

					if (calenderPrices < calenderBlock.size()) {
						WaitUtil.untilTimeCompleted(100);
					}
				}
			}//Calendar Date Blocks

			//move to next week calendar block when flight is not available in current week block
			if (!selectedFlightFlag) {
				//loop through all flight next rows
				for (WebElement calNextButton : flightAvailabilityPage.getCalNextArrowImage()) {
					//check for displayed next row button
					if (calNextButton.isDisplayed()) {
						//click on next week button
						calNextButton.click();

						//wait for page load
						WaitUtil.untilPageLoadComplete(driver);

						//Wait for 3 sec
						WaitUtil.untilTimeCompleted(3000);

						//break from loop
						break;
					}
				}
			}
		}//outer loop for 5 times

		//journey, String fareType, String flightCost
		ValidationUtil.validateTestStep(flightCost + " fare under " + fareType  + " fares is not found for " + journey + " is not found on Modify Flight Availability Page","Flight Not Found","Flight Found");

	}

	public synchronized void selectFlightFareType(String journey, String fareType) {
		WaitUtil.untilPageLoadComplete(driver);
		List<WebElement> allFlightRows = new ArrayList<>();
		List<WebElement> calenderBlock = new ArrayList<>();
		Date journeyDate = new Date();

		journeyDate = getCalendarBlockInfo(journey,calenderBlock,journeyDate);


		int calenderPrices = 0;
		boolean flightFound = false;
		for(calenderPrices=0;calenderPrices<calenderBlock.size();calenderPrices++) {

			//System.out.println(calenderPrices);

			//check date block is visible. This block will handle Mobile Date block in which only 3 Date block is visible
			if (!calenderBlock.get(calenderPrices).isDisplayed()) {
				//move to next date block if date block is not visible
				continue;
			}

			//when flight prices are available then webelement count is 4 else 2
			List<WebElement> CalenderPrices = calenderBlock.get(calenderPrices).findElements(By.tagName("span"));

			//"i" tag will contains aircraft image in calendar blocks
			WebElement CalenderPlane = calenderBlock.get(calenderPrices).findElements(By.tagName("i")).get(0);
		}

	}
	private Date getCalendarBlockInfo(String journey, List<WebElement> calenderBlock, Date journeyDate){
		/**
		 * This methods get the corresponding calendar block for dep or arrival as well as returning the journeyDate
		 */
		if(journey.equalsIgnoreCase("dep")){
			//assign dep flight calendar
			calenderBlock.addAll(flightAvailabilityPage.getDepCalBlocksGridView());

			//get dep date
			return TestUtil.convertStringToDate(TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE), "E, MMM dd yyyy"), "E, MMM dd yyyy");
		}else if(journey.equalsIgnoreCase("ret")){
			//assign ret flight calendar
			calenderBlock.addAll(flightAvailabilityPage.getArrCalBlocksGridView());

			//update dates in case any modification happened
			updateJourneyDates("Dep");

			//get arr date
			return TestUtil.convertStringToDate(TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE), "E, MMM dd yyyy"), "E, MMM dd yyyy");
		} else {
			throw new IllegalArgumentException("The function only accept journey = \"ret\" or \"dep\"");
		}
	}

	public synchronized void selectFlightNatureType(String journey, String flightType) {
		selectFlight(journey, "NA", flightType, "NA", "NA",  "NA" );
	}

	public synchronized void selectFlightNumberType(String journey,String flightNumber) {
		selectFlight(journey, "NA", "NA", flightNumber, "NA",  "NA" );
	}

	public synchronized void selectFlightAircraftType(String journey, String aircraftType) {
		selectFlight(journey, "NA", "NA", "NA", aircraftType,  "NA" );
	}

	public synchronized void selectFlightSeatType(String journey, String seatType) {
		selectFlight(journey, "NA", "NA", "NA", "NA",  seatType );
	}

	public synchronized void selectFlight(String journey, String fareType, String flightType, String flightNumber,String aircraftType, String seatType ) {
		//declare variable used in method
		boolean selectedFlightFlag = false;
		int searchCounter = 3;

		WaitUtil.untilPageLoadComplete(driver);

		List<WebElement> allFlightRows = null;

		//check search criteria alue is overwritten or not
		if(scenarioContext.isContains(Context.AVAILABILITY_DATE_SEARCH_CRITERIA_OVERWRITE)){
			searchCounter = Integer.parseInt(scenarioContext.getContext(Context.AVAILABILITY_DATE_SEARCH_CRITERIA_OVERWRITE).toString());
		}

		//loop though complete month
		for(int calenderCounter=0;calenderCounter<searchCounter;calenderCounter++){

			//*****************************************************************
			//*************************Flight Week Calendar********************
			//*****************************************************************
			//declare variable used in method
			int calenderPrices = 0;
			Date journeyDate = null;
			boolean flightFound = false;
			List<WebElement> calenderBlock = null;

			//System.out.println(journey);
			//assign dep or ret block based on journey type
			if(journey.equalsIgnoreCase("dep")){
				//assign dep flight calendar
				calenderBlock = flightAvailabilityPage.getDepCalBlocksGridView();

				//get dep date
				journeyDate = TestUtil.convertStringToDate(TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE), "E, MMM dd"), "E, MMM dd");
			}else if(journey.equalsIgnoreCase("ret")){
				//assign ret flight calendar
				calenderBlock = flightAvailabilityPage.getArrCalBlocksGridView();

				//update dates in case any modification happened
				updateJourneyDates("Dep");

				//get arr date
				journeyDate = TestUtil.convertStringToDate(TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE), "E, MMM dd"), "E, MMM dd");
			}

			//loop through all calendar block and check flight is available on selected date
			for(calenderPrices=0;calenderPrices<calenderBlock.size();calenderPrices++){

				//System.out.println(calenderPrices);

				//check date block is visible. This block will handle Mobile Date block in which only 3 Date block is visible
				if(!calenderBlock.get(calenderPrices).isDisplayed()) {
					//move to next date block if date block is not visible
					continue;
				}

				//when flight prices are available then webelement count is 4 else 2
				List<WebElement> CalenderPrices = calenderBlock.get(calenderPrices).findElements(By.tagName("span"));

				//"i" tag will contains aircraft image in calendar blocks
				WebElement CalenderPlane  = calenderBlock.get(calenderPrices).findElements(By.tagName("i")).get(0);

				//loop through all elements of calendar block
				for(WebElement calendarDateText : CalenderPrices) {
					if(calendarDateText.getText().contains(",")) {
						//get the date from calendar blocks
						Date calenderDate = TestUtil.convertStringToDate(calendarDateText.getText().trim(), "E, MMM dd");

						System.out.println("Selected Departure Date is " + journeyDate);
						System.out.println("Current Date is " + calenderDate);
						System.out.println("Plane is visible " + CalenderPlane.getAttribute("class").contains("invisible"));
						System.out.println("Prices is visible " + CalenderPrices.size());

						//check selected date is after journey date or same date
						if(calenderDate.after(journeyDate) && CalenderPlane.getAttribute("class").contains("invisible")) {
							if(!fareType.equalsIgnoreCase("9DFC")) {
								//System.out.println(CalenderPrices.size());
								if(CalenderPrices.size()>=4){
									//click on calendar date block
									calendarDateText.click();

									//wait for 2 sec
									WaitUtil.untilTimeCompleted(2000);

									//wait till page load is complete
									WaitUtil.untilPageLoadComplete(driver);

									//wait for 2 sec
									WaitUtil.untilTimeCompleted(2000);

									//traveling flight found
									flightFound = true;

									//break from per date block
									break;
								}
							}else {
								for(WebElement memberFare:CalenderPrices) {
									if(memberFare.getText().trim().contains("9FC")) {
										//click on calendar date block
										calendarDateText.click();

										//wait for 2 sec
										WaitUtil.untilTimeCompleted(2000);

										//wait till page load is complete
										WaitUtil.untilPageLoadComplete(driver);

										//wait for 2 sec
										WaitUtil.untilTimeCompleted(2000);

										//traveling flight found
										flightFound = true;

										//break from per date block
										break;
									}
								}
							}
						}else if(calenderDate.equals(journeyDate) && !CalenderPlane.getAttribute("class").contains("invisible")) {
							if(!fareType.equalsIgnoreCase("9DFC")) {
								//traveling flight found
								flightFound = true;

								//break from per date block
								break;
							}else {
								for(WebElement memberFare:CalenderPrices) {
									if(memberFare.getText().trim().contains("9FC")) {
										//traveling flight found
										flightFound = true;

										//break from per date block
										break;
									}
								}
							}
						}
					}//if block of calendar text data

					if(flightFound) {
						break;
					}
				}//with in Date Block

				//check flight found flag is set
				if(flightFound) {
					//*****************************************************************
					//**********************Flight Availability Table******************
					//*****************************************************************
					//select flight rows depending upon journey
					if(journey.equalsIgnoreCase("dep")){
						//select dep flight rows
						allFlightRows = flightAvailabilityPage.getDepFlightRowsGridView();
					}else if(journey.equalsIgnoreCase("ret")){
						//select return flight rows
						allFlightRows = flightAvailabilityPage.getArrFlightRowsGridView();
					}else{
						//do nothing
					}

					//loop through all flight rows
					//for(int flightRows=0;flightRows<allFlightRows.size();flightRows++){
					for(int flightRows=allFlightRows.size()-1;flightRows>=0;flightRows--){
						//check flight is not sold out before selection
						if(allFlightRows.get(flightRows).getText().contains("Sold Out") || allFlightRows.get(flightRows).getText().contains("Agotado")) {
							continue;
						}

						//check flight is not already selected on manage Travel or check In path
						if(allFlightRows.get(flightRows).getText().contains("Original Choice") || allFlightRows.get(flightRows).getText().contains("Elección Original")) {
							continue;
						}

						//check flight is avaliable in 24 hours
						if(scenarioContext.getContext(Context.HOMEPAGE_CHECK_IN).toString().equalsIgnoreCase("24Hours") && journey.equalsIgnoreCase("dep")){
							//get dep date and flight dep time
							String jouDate = flightAvailabilityPage.getDepartingFlightDateText().getText().split(",",2)[1].trim();
							String depTime = flightAvailabilityPage.getDepartingDepartTimeText().get(flightRows).getText().trim();

							//convert flight dep time into date format
							Date d1 =TestUtil.convertStringToDate(jouDate+  " " + depTime,"MMM dd, yyyy hh:mm a");
							//System.out.println(d1);
							//get current system date and time
							Date d2 = TestUtil.convertStringToDate(TestUtil.getStringDateFormat("0","MMM dd, yyyy hh:mm a"), "MMM dd, yyyy hh:mm a");
							//System.out.println(d2);

							//System.out.println(d1.getTime()-d2.getTime());
							//get the difference of two dates
							long dateDiff = (d1.getTime()-d2.getTime())/(60 * 1000);

							//System.out.println("Time Difference of " + dateDiff);

							//check current dep flight is not in in 24 hour
							if(!(dateDiff>=70 && dateDiff<=1435)){
								//move to night flight
								continue;
							}

						}

						if(!fareType.equalsIgnoreCase("9DFC")) {
							//get the buttons from flight rows
							List<WebElement> allStopCount = allFlightRows.get(flightRows).findElements(By.tagName("button"));

							//loop through all buttons with in flight row
							for(WebElement stopCount: allStopCount){
								//scroll down to stop link
								JSExecuteUtil.scrollDownToElementVisible(driver,stopCount);
								//check flight is select using flight type or flight numver
								if(!flightType.equalsIgnoreCase("na") || !flightNumber.equalsIgnoreCase("na")){
									//check button contains "stop" label
									if(stopCount.isDisplayed() && (stopCount.getText().toLowerCase().contains("stop") || stopCount.getText().toLowerCase().contains("sin escalas"))){
										//click on stop link on flight row
//										stopCount.click();
										JSExecuteUtil.clickOnElement(driver,stopCount);

										//get the flight status from FLight Stop Popup
										selectedFlightFlag = getFlightTypeStopPopUp(journey, flightType, flightNumber);
									}
								}else if(!aircraftType.equalsIgnoreCase("na") || !seatType.equalsIgnoreCase("na")){
									//check button contains "view seat map" label
									if(stopCount.isDisplayed() && (stopCount.getText().toLowerCase().contains("view seat map") ||  stopCount.getText().toLowerCase().contains("mapa de asientos"))){
										//click on view seat map link on flight row
//										stopCount.click();
										JSExecuteUtil.clickOnElement(driver,stopCount);

										//get flight status from Seat Map Popup
										selectedFlightFlag = getFlightTypeSeatPopUp(seatType, aircraftType, scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString());
									}
								}

								//check flight is found
								if(selectedFlightFlag){
									//get all labels in flight row
									List<WebElement> allFareType = allFlightRows.get(flightRows).findElements(By.tagName("label"));

									//get all radio button in flight row
									List<WebElement> allRadioType = allFlightRows.get(flightRows).findElements(By.tagName("input"));

									//loop through all available flight fare
									for(int fareCounter=0;fareCounter<allRadioType.size();fareCounter++) {
										//check for standard flight fare
										if(allFareType.get(fareCounter).getAttribute("for").contains("-standard-")) {
											//check standard flight fare is enabled
											if(!TestUtil.verifyAttributePresent(allRadioType.get(fareCounter),"disabled")) {
												//click standard flight fare
												//allFareType.get(fareCounter).click();
												JSExecuteUtil.clickOnElement(driver,allFareType.get(fareCounter));
											}else {
												//click member fare
												//allFareType.get(fareCounter-1).click();
												JSExecuteUtil.clickOnElement(driver,allFareType.get(fareCounter-1));
											}
											//wait for page load
											WaitUtil.untilPageLoadComplete(driver);

											//wait for 2 sec
											WaitUtil.untilTimeCompleted(2000);
										}
									}
									//wait for page load
									WaitUtil.untilPageLoadComplete(driver);

									//update dates in case any modification happened
									updateJourneyDates(journey);

									//flight selected
									return;
								}
							}//Flight Stop Count Loop
						}else {
							//get all labels in flight row
							List<WebElement> allFareType = allFlightRows.get(flightRows).findElements(By.tagName("label"));

							for(WebElement memberFareType :allFareType) {
								//if(journey.equalsIgnoreCase("ret")){
								//System.out.println(memberFareType.getAttribute("for"));
								//}

								if(memberFareType.getAttribute("for").contains("-club-")) {
									memberFareType.click();

									//wait for page load
									WaitUtil.untilPageLoadComplete(driver);

									//wait for 2 sec
									WaitUtil.untilTimeCompleted(2000);

									//flight found
									selectedFlightFlag = true;

									//update dates in case any modification happened
									updateJourneyDates(journey);

									return;
								}

							}
						}

					}//Flight Row loop
				}//flight found if block
				//set flight found flag to false for next flight row
				flightFound = false;

				if(calenderPrices<calenderBlock.size()){
					WaitUtil.untilTimeCompleted(100);
				}

			}//Calendar Date Blocks

			//move to next week calendar block when flight is not available in current week block
			if(!selectedFlightFlag){
				//loop through all flight next rows
				for(WebElement calNextButton : flightAvailabilityPage.getCalNextArrowImage()) {
					//check for displayed next row button
					if(calNextButton.isDisplayed()) {
						//click on next week button
//						calNextButton.click();
						JSExecuteUtil.clickOnElement(driver,calNextButton);

						//wait for page load
						WaitUtil.untilPageLoadComplete(driver);

						//Wait for 3 sec
						WaitUtil.untilTimeCompleted(3000);

						//break from loop
						break;
					}
				}
			}
		}//outer loop for 5 times


		if(!fareType.equalsIgnoreCase("NA")){
			ValidationUtil.validateTestStep(fareType + " is not found for " + journey + " Flight ","Flight Not Found","Flight Found");
		}else if(!flightType.equalsIgnoreCase("NA")){
			ValidationUtil.validateTestStep(flightType + "is not found for " + journey + " Flight","Flight Not Found","Flight Found");
		}else if(!flightNumber.equalsIgnoreCase("NA")){
			ValidationUtil.validateTestStep(flightNumber + "is not found for " + journey + " Flight","Flight Not Found","Flight Found");
		}else if(!aircraftType.equalsIgnoreCase("NA")){
			ValidationUtil.validateTestStep(aircraftType + "is not found for " + journey + " Flight","Flight Not Found","Flight Found");
		}else if(!seatType.equalsIgnoreCase("NA")){
			ValidationUtil.validateTestStep(seatType + "is not found for " + journey + " Flight","Flight Not Found","Flight Found");
		}
	}



	public synchronized void selectMilesFlightFareType(String journey, String fareType) {
		selectMilesFlight(journey, fareType, "NA", "NA", "NA",  "NA" );
	}

	public synchronized void selectMilesFlightNatureType(String journey, String flightType) {
		selectMilesFlight(journey, "NA", flightType, "NA", "NA",  "NA" );
	}

	public synchronized void selectMilesFlightNumberType(String journey,String flightNumber) {
		selectMilesFlight(journey, "NA", "NA", flightNumber, "NA",  "NA" );
	}

	public synchronized void selectMilesFlightAircraftType(String journey, String aircraftType) {
		selectMilesFlight(journey, "NA", "NA", "NA", aircraftType,  "NA" );
	}

	public synchronized void selectMilesFlightSeatType(String journey, String seatType) {
		selectMilesFlight(journey, "NA", "NA", "NA", "NA",  seatType );
	}

	public synchronized void selectMilesFlight(String journey, String fareType, String flightType, String flightNumber,String aircraftType, String seatType ) {
		//declare variable used in method
		boolean selectedFlightFlag = false;
		int searchCounter = 3;

		WaitUtil.untilPageLoadComplete(driver);

		List<WebElement> allFlightRows = null;

		//check search criteria alue is overwritten or not
		if(scenarioContext.isContains(Context.AVAILABILITY_DATE_SEARCH_CRITERIA_OVERWRITE)){
			searchCounter = Integer.parseInt(scenarioContext.getContext(Context.AVAILABILITY_DATE_SEARCH_CRITERIA_OVERWRITE).toString());
		}

		//loop though complete month
		for(int calenderCounter=0;calenderCounter<searchCounter;calenderCounter++){

			//*****************************************************************
			//*************************Flight Week Calendar********************
			//*****************************************************************
			//declare variable used in method
			int calenderPrices = 0;
			Date journeyDate = null;
			boolean flightFound = false;
			boolean milesAvailable = false;
			List<WebElement> calenderBlock = null;
			WebElement CalenderPlane = null;

			//System.out.println(journey);
			//assign dep or ret block based on journey type
			if(journey.equalsIgnoreCase("dep")){
				//assign dep flight calendar
				calenderBlock = flightAvailabilityPage.getDepCalBlocksGridView();

				//get dep date
				journeyDate = TestUtil.convertStringToDate(TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE), "E, MMM dd yyyy"), "E, MMM dd yyyy");
			}else if(journey.equalsIgnoreCase("ret")){
				//assign ret flight calendar
				calenderBlock = flightAvailabilityPage.getArrCalBlocksGridView();

				//update dates in case any modification happened
				updateJourneyDates("Dep");

				//get arr date
				journeyDate = TestUtil.convertStringToDate(TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE), "E, MMM dd yyyy"), "E, MMM dd yyyy");
			}

			//loop through all calendar block and check flight is available on selected date
			for(calenderPrices=0;calenderPrices<calenderBlock.size();calenderPrices++){

				//System.out.println(calenderPrices);

				//check date block is visible. This block will handle Mobile Date block in which only 3 Date block is visible
				if(!calenderBlock.get(calenderPrices).isDisplayed()) {
					//move to next date block if date block is not visible
					continue;
				}

				//when flight prices are available then webelement count is 4 else 2
				List<WebElement> CalenderPrices = calenderBlock.get(calenderPrices).findElements(By.tagName("span"));

				for(WebElement milesPrices : CalenderPrices){
					if(milesPrices.getText().contains("miles")){
						milesAvailable = true;

						CalenderPlane = milesPrices;

						break;
					}
				}

				//loop through all elements of calendar block
				for(WebElement calendarDateText : CalenderPrices) {
					if(calendarDateText.getText().contains(",")) {
						//get the date from calendar blocks
						Date calenderDate = TestUtil.convertStringToDate(calendarDateText.getText().trim(), "E, MMM dd");

						System.out.println("Selected Departure Date is " + journeyDate);
						System.out.println("Current Date is " + calenderDate);
						// System.out.println("Plane is visible " + CalenderPlane.getAttribute("class").contains("invisible"));
						System.out.println("Prices is visible " + CalenderPrices.size());

						//check selected date is after journey date or same date
						if(calenderDate.after(journeyDate) && !CalenderPlane.getCssValue("font-weight").contains("700")) {
							if(!fareType.equalsIgnoreCase("MC")) {
								//System.out.println(CalenderPrices.size());
								if(milesAvailable){
									//click on calendar date block
									calendarDateText.click();

									//wait for 3 sec
									WaitUtil.untilTimeCompleted(3000);

									//traveling flight found
									flightFound = true;

									//break from per date block
									break;
								}
							}else {
								for(WebElement memberFare:CalenderPrices) {
									if(memberFare.getText().trim().contains("MC") && !memberFare.getAttribute("class").contains("invisible")) {
										//click on calendar date block
										calendarDateText.click();

										//wait for 3 sec
										WaitUtil.untilTimeCompleted(3000);

										//traveling flight found
										flightFound = true;

										//break from per date block
										break;
									}

								}
							}
						}else if(calenderDate.equals(journeyDate) && CalenderPlane.getCssValue("font-weight").contains("700")) {
							if(!fareType.equalsIgnoreCase("MC")) {
								//traveling flight found
								flightFound = true;

								//break from per date block
								break;
							}else {
								for(WebElement memberFare:CalenderPrices) {
									if(memberFare.getText().trim().contains("MC") && !memberFare.getAttribute("class").contains("invisible")) {
										//traveling flight found
										flightFound = true;

										//break from per date block
										break;
									}
								}
							}
						}
						break;
					}//if block of calendar text data
				}//with in Date Block

				//check flight found flag is set
				if(flightFound) {
					//*****************************************************************
					//**********************Flight Availability Table******************
					//*****************************************************************
					//select flight rows depending upon journey
					if(journey.equalsIgnoreCase("dep")){
						//select dep flight rows
						allFlightRows = flightAvailabilityPage.getDepFlightRowsGridView();
					}else if(journey.equalsIgnoreCase("ret")){
						//select return flight rows
						allFlightRows = flightAvailabilityPage.getArrFlightRowsGridView();
					}else{
						//do nothing
					}

					//loop through all flight rows
					//for(int flightRows=0;flightRows<allFlightRows.size();flightRows++){
					for(int flightRows=allFlightRows.size()-1;flightRows>=0;flightRows--){
						//check flight is not sold out before selection
						if(allFlightRows.get(flightRows).getText().contains("Sold Out") || allFlightRows.get(flightRows).getText().contains("Agotado")) {
							continue;
						}

						//check flight is not already selected on manage Travel or check In path
						if(allFlightRows.get(flightRows).getText().contains("Original Choice") || allFlightRows.get(flightRows).getText().contains("Elección Original")) {
							continue;
						}

						if(scenarioContext.getContext(Context.HOMEPAGE_CHECK_IN).toString().equalsIgnoreCase("24Hours") && journey.equalsIgnoreCase("dep")){
							//get dep date and flight dep time
							String jouDate = flightAvailabilityPage.getDepartingFlightDateText().getText().split(",",2)[1].trim();
							String depTime = flightAvailabilityPage.getDepartingDepartTimeText().get(flightRows).getText().trim();

							//convert flight dep time into date format
							Date d1 =TestUtil.convertStringToDate(jouDate+  " " + depTime,"MMM dd, yyyy hh:mm a");
							//System.out.println(d1);
							//get current system date and time
							Date d2 = TestUtil.convertStringToDate(TestUtil.getStringDateFormat("0","MMM dd, yyyy hh:mm a"), "MMM dd, yyyy hh:mm a");
							//System.out.println(d2);

							//System.out.println(d1.getTime());
							//get the difference of two dates
							long dateDiff = (d1.getTime()-d2.getTime())/(60 * 1000);

							//check current dep flight is not in in 24 hour
							if(!(dateDiff>=70 && dateDiff<=1435)){
								//move to night flight
								continue;
							}

						}



						if(!fareType.equalsIgnoreCase("MC")) {
							//get the buttons from flight rows
							List<WebElement> allStopCount = allFlightRows.get(flightRows).findElements(By.tagName("button"));

							//loop through all buttons with in flight row
							for(WebElement stopCount: allStopCount){
								//scroll down to stop link
								JSExecuteUtil.scrollDownToElementVisible(driver,stopCount);
								//check flight is select using flight type or flight numver
								if(!flightType.equalsIgnoreCase("na") || !flightNumber.equalsIgnoreCase("na")){
									//check button contains "stop" label
									if(stopCount.isDisplayed() && (stopCount.getText().toLowerCase().contains("stop") || stopCount.getText().toLowerCase().contains("sin escalas"))){
										//click on stop link on flight row
										stopCount.click();

										//get the flight status from FLight Stop Popup
										selectedFlightFlag = getFlightTypeStopPopUp(journey, flightType, flightNumber);
									}
								}else if(!aircraftType.equalsIgnoreCase("na") || !seatType.equalsIgnoreCase("na")){
									//check button contains "view seat map" label
									if(stopCount.isDisplayed() && (stopCount.getText().toLowerCase().contains("view seat map") ||  stopCount.getText().toLowerCase().contains("mapa de asientos"))){
										//click on view seat map link on flight row
										stopCount.click();

										//get flight status from Seat Map Popup
										selectedFlightFlag = getFlightTypeSeatPopUp(seatType, aircraftType, scenarioContext.getContext(Context.HOMEPAGE_ADULT_COUNT).toString());
									}
								}

								//check flight is found
								if(selectedFlightFlag){
									//get all labels in flight row
									List<WebElement> allFareType = allFlightRows.get(flightRows).findElements(By.tagName("label"));

									//get all radio button in flight row
									List<WebElement> allRadioType = allFlightRows.get(flightRows).findElements(By.tagName("input"));

									//loop through all available flight fare
									for(int fareCounter=0;fareCounter<allRadioType.size();fareCounter++) {
										//check for standard flight fare
										if(allFareType.get(fareCounter).getAttribute("for").contains("-standard-")) {
											//check standard flight fare is enabled
											if(!TestUtil.verifyAttributePresent(allRadioType.get(fareCounter),"disabled")) {
												//click standard flight fare
//												allFareType.get(fareCounter).click();
												JSExecuteUtil.clickOnElement(driver,allFareType.get(fareCounter));
											}else {
												//click member fare
//												allFareType.get(fareCounter-1).click();
												JSExecuteUtil.clickOnElement(driver,allFareType.get(fareCounter-1));
											}
											//wait for page load
											WaitUtil.untilPageLoadComplete(driver);

											//wait for 2 sec
											WaitUtil.untilTimeCompleted(2000);
										}
									}
									//wait for page load
									WaitUtil.untilPageLoadComplete(driver);

									//update dates in case any modification happened
									updateJourneyDates(journey);

									//flight selected
									return;
								}
							}//Flight Stop Count Loop
						}else {
							//get all labels in flight row
							List<WebElement> allFareType = allFlightRows.get(flightRows).findElements(By.tagName("label"));

							for(WebElement memberFareType :allFareType) {
								//if(journey.equalsIgnoreCase("ret")){
								//System.out.println(memberFareType.getAttribute("for"));
								//}

								if(memberFareType.getAttribute("for").contains("-card-")) {
									memberFareType.click();

									//wait for page load
									WaitUtil.untilPageLoadComplete(driver);

									//wait for 2 sec
									WaitUtil.untilTimeCompleted(2000);

									//flight found
									selectedFlightFlag = true;

									//update dates in case any modification happened
									updateJourneyDates(journey);

									return;
								}

							}
						}

					}//Flight Row loop
				}//flight found if block
				//set flight found flag to false for next flight row
				flightFound = false;

				if(calenderPrices<calenderBlock.size()){
					WaitUtil.untilTimeCompleted(100);
				}

			}//Calendar Date Blocks

			//move to next week calendar block when flight is not available in current week block
			if(!selectedFlightFlag){
				//loop through all flight next rows
				for(WebElement calNextButton : flightAvailabilityPage.getCalNextArrowImage()) {
					//check for displayed next row button
					if(calNextButton.isDisplayed()) {
						//click on next week button
						calNextButton.click();

						//wait for page load
						WaitUtil.untilPageLoadComplete(driver);

						//Wait for 3 sec
						WaitUtil.untilTimeCompleted(3000);

						//break from loop
						break;
					}
				}
			}
		}//outer loop for 5 times

	}

	public synchronized void storeFlightDetailsForVacationBooking(){

		//click on selected Departing flight stop link
//		flightAvailabilityPage.getSeletedDepatingFlightNatureButton().get(0).click();
		JSExecuteUtil.clickOnElement(driver,flightAvailabilityPage.getSeletedDepatingFlightNatureButton().get(0));

		//wait for page load is complete
		WaitUtil.untilPageLoadComplete(driver);

		//store flight information
		storeFlightLegInformation("DEP");
		storeFlightInformation("DEP");
		getRedEyeFlightInformation("DEP");


		//wait for 1.2 seconds
		WaitUtil.untilTimeCompleted(1200);

		//close flight Stop Popup
//		flightAvailabilityPage.getStopsPopUpCloseButton().click();
		JSExecuteUtil.clickOnElement(driver,flightAvailabilityPage.getStopsPopUpCloseButton());

		//click on selected returning flight stop link
//		flightAvailabilityPage.getSelectedReturningFlightNatureButton().get(0).click();
		JSExecuteUtil.clickOnElement(driver,flightAvailabilityPage.getSelectedReturningFlightNatureButton().get(0));

		//wait for page l;oad is complete
		WaitUtil.untilPageLoadComplete(driver);

		//store flight information
		storeFlightLegInformation("RET");
		storeFlightInformation("RET");
		getRedEyeFlightInformation("RET");

		//wait for 1.2 seconds
		WaitUtil.untilTimeCompleted(1200);

		//close flight Stop Popup
		flightAvailabilityPage.getStopsPopUpCloseButton().click();
	}


	//*******************************************************************
	//***********************Common Methods******************************
	//*******************************************************************
	private synchronized boolean getFlightTypeStopPopUp(String journey, String flightType, String flightNumber ) {
		//declare variable used in method
		boolean selectedFlightFlag = false;
		List<WebElement> allFlightNumber = flightAvailabilityPage.getStopsPopUpFlightsNumberText();

		//wait for page load
		WaitUtil.untilPageLoadComplete(driver);

		if(!flightType.equalsIgnoreCase("na")){
			String firstFlightNumber  = null;
			String secondFlightNumber = null;
			String thirdFlightNumber  = null;

			if(flightType.equalsIgnoreCase("autoselect") && allFlightNumber.size() >= 1){
				selectedFlightFlag = true;
			}else if(flightType.equalsIgnoreCase("nonstop") && allFlightNumber.size() == 1){
				selectedFlightFlag = true;
			}else if((flightType.equalsIgnoreCase("connecting") || flightType.equalsIgnoreCase("through")) && allFlightNumber.size() == 2){
				firstFlightNumber  = allFlightNumber.get(0).getText().replaceAll("Flight NK", "").replaceAll("Vuelo NK", "").trim();
				secondFlightNumber = allFlightNumber.get(1).getText().replaceAll("Flight NK", "").replaceAll("Vuelo NK", "").trim();

				if(firstFlightNumber.equalsIgnoreCase(secondFlightNumber) && flightType.equalsIgnoreCase("through")){
					selectedFlightFlag = true;
				}else if(!firstFlightNumber.equalsIgnoreCase(secondFlightNumber) && flightType.equalsIgnoreCase("connecting")){
					selectedFlightFlag = true;
				}
			}else if((flightType.equalsIgnoreCase("connecting-through") || flightType.equalsIgnoreCase("through-connecting")) && allFlightNumber.size() == 3){
				firstFlightNumber  = allFlightNumber.get(0).getText().replaceAll("Flight NK", "").replaceAll("Vuelo NK", "").trim();
				secondFlightNumber = allFlightNumber.get(1).getText().replaceAll("Flight NK", "").replaceAll("Vuelo NK", "").trim();
				thirdFlightNumber  = allFlightNumber.get(2).getText().replaceAll("Flight NK", "").replaceAll("Vuelo NK", "").trim();

				if(firstFlightNumber.equalsIgnoreCase(secondFlightNumber) && !secondFlightNumber.equalsIgnoreCase(thirdFlightNumber) && flightType == "through-connecting"){
					selectedFlightFlag = true;
				}else if(!firstFlightNumber.equalsIgnoreCase(secondFlightNumber) && secondFlightNumber.equalsIgnoreCase(thirdFlightNumber) && flightType == "connecting-through"){
					selectedFlightFlag = true;
				}
			}
		}else if (!flightNumber.equalsIgnoreCase("na")){
			for(WebElement depFlightNum : allFlightNumber){
				if(depFlightNum.getText().toLowerCase().contains(flightNumber)){
					selectedFlightFlag = true;
				}
			}
		}

		if(selectedFlightFlag) {
			storeFlightLegInformation(journey);
			storeFlightInformation(journey);
		}

		//wait for 1.2 seconds
		WaitUtil.untilTimeCompleted(1200);

		//close flight Stop Popup
		flightAvailabilityPage.getStopsPopUpCloseButton().click();

		return selectedFlightFlag;
	}


	private synchronized void storeFlightInformation(String journey){
		//FlightNumber:NK562|DepartureAirport:FLL|ArrivalAirport:LAS|DepartureTime:6:34AM|ArrivalTime:34AM|FlightDuration:5 hours, 2 minutes|LayOverTime:4 HOURS, 32 MINUTES LAYOVER
		//declare variable in method
		String flightNumber 			= "";

		//declare constant used in method
		final String FLIGHT_NUMBER		= "FlightNumber:";
		final String AIRCRAFT_TYPE		= "|AircraftType:";
		final String DEPARTURE_AIRPORT	= "|DepartureAirport:";
		final String ARRIVAL_AIRPORT	= "|ArrivalAirport:";
		final String DEPARTURE_TIME		= "|DepartureTime:";
		final String ARRIVAL_TIME		= "|ArrivalTime:";
		final String FLIGHT_DURATION	= "|FlightDuration:";
		final String LAYOVER_TIME		= "|LayOverTime:";

		//declare variable used in method
		String journeyLegDetails = "";

		//assign values of flight number departing and returning airports
		List<WebElement> allFlightNumber 		= flightAvailabilityPage.getStopsPopUpFlightsNumberText();
		List<WebElement> allFlightAircraftType 	= flightAvailabilityPage.getStopsPopUpAircraftTypeText();
		List<WebElement> allDepartureAirport 	= flightAvailabilityPage.getStopsPopUpDepartureAirportsText();
		List<WebElement> allArrivalAirport   	= flightAvailabilityPage.getStopsPopUpArrivalAirportsText();
		List<WebElement> allDepartureTime		= flightAvailabilityPage.getStopsPopUpDepartureTimeText();
		List<WebElement> allArrivalTime   		= flightAvailabilityPage.getStopsPopUpArrivalTimeText();
		List<WebElement> allFlightFlyTime  		= flightAvailabilityPage.getStopsPopUpFlightDurationTimeText();
		List<WebElement> allFlightLayoverTime  	= flightAvailabilityPage.getStopsPopUpLayoverTimeText();

		//loop through all flight leg and store information
		for(int flightLegCount=0;flightLegCount<=allFlightNumber.size()-1;flightLegCount++) {
			if(flightLegCount>0){
				journeyLegDetails = journeyLegDetails + "||";
			}
			//add flight number
			flightNumber = allFlightNumber.get(flightLegCount).getText().trim().replaceAll(" ","");

			journeyLegDetails = journeyLegDetails + FLIGHT_NUMBER + flightNumber.substring(flightNumber.indexOf("NK"),flightNumber.length());

			//add aircraft type
			journeyLegDetails = journeyLegDetails + AIRCRAFT_TYPE + allFlightAircraftType.get(flightLegCount).getText().trim().replaceAll(" ","");

			//add departure airport
			journeyLegDetails = journeyLegDetails + DEPARTURE_AIRPORT + allDepartureAirport.get(flightLegCount).getText().trim().replaceAll(" ","");

			//add arrival airport
			journeyLegDetails = journeyLegDetails + ARRIVAL_AIRPORT + allArrivalAirport.get(flightLegCount).getText().trim().replaceAll(" ","");

			//add departure time
			journeyLegDetails = journeyLegDetails + DEPARTURE_TIME + allDepartureTime.get(flightLegCount).getText().trim().replaceAll(" ","");

			//add arrival time
			journeyLegDetails = journeyLegDetails + ARRIVAL_TIME + allArrivalTime.get(flightLegCount).getText().trim().replaceAll(" ","");

			//add flight fly duration time
			journeyLegDetails = journeyLegDetails + FLIGHT_DURATION + allFlightFlyTime.get(flightLegCount).getText().trim().replaceAll(" ","");

			if(allFlightLayoverTime.size()>flightLegCount){
				//add layover time
				journeyLegDetails = journeyLegDetails + LAYOVER_TIME + allFlightLayoverTime.get(flightLegCount).getText().trim().replaceAll(" ","").replaceAll("LAYOVER","").replaceAll("ESCALA DE","");
			}
		}

		if(journey.equalsIgnoreCase("dep")) {
			scenarioContext.setContext(Context.AVAILABILITY_DEP_FLIGHT_DETAILS, journeyLegDetails);
		}else if(journey.equalsIgnoreCase("ret")) {
			scenarioContext.setContext(Context.AVAILABILITY_RET_FLIGHT_DETAILS, journeyLegDetails);
		}else {
			//do noting code is not implemented
			throw new RuntimeException("Code is not written for connecting-through and through-connecting flights");
		}
	}


	private synchronized void storeFlightLegInformation(String journey) {
		//declare variable used in method
		String journeyLegDetails = null;

		//assign values of flight number departing and returning airports
		List<WebElement> allFlightNumber = flightAvailabilityPage.getStopsPopUpFlightsNumberText();
		List<WebElement> allDepartureAir = flightAvailabilityPage.getStopsPopUpDepartureAirportsText();
		List<WebElement> allArrivalAir   = flightAvailabilityPage.getStopsPopUpArrivalAirportsText();

		//loop through all flight leg and store information
		for(int flightLegCount=0;flightLegCount<=allFlightNumber.size()-1;flightLegCount++) {
			//store the first leg detail
			//add another leg detail fro second leg in connecting flight
			//store first leg departing and second leg returning for through flight
			if(flightLegCount==0) {
				//store first leg information
				journeyLegDetails = allDepartureAir.get(flightLegCount).getText() + " - " + allArrivalAir.get(flightLegCount).getText();
			}else if(!allFlightNumber.get(flightLegCount).getText().equals(allFlightNumber.get(flightLegCount-1).getText())) {
				//append departing and returning airports details in pervious leg information
				journeyLegDetails = journeyLegDetails + ":" + allDepartureAir.get(flightLegCount).getText() + " - " + allArrivalAir.get(flightLegCount).getText();
			}else if(allFlightNumber.get(flightLegCount).getText().equals(allFlightNumber.get(flightLegCount-1).getText())) {
				//check the length of store leg detail to find flight is through
				if(journeyLegDetails.split(":").length == 1) {
					//remove previous data and add first lef departing and second leg returning airport
					journeyLegDetails = allDepartureAir.get(flightLegCount-1).getText() + " - " + allArrivalAir.get(flightLegCount).getText();
				}else if(journeyLegDetails.split(":").length > 1) {
					//do noting code is not implemented
					throw new RuntimeException("Code is not written for connecting-through and through-connecting flights in method storeFlightLegInformation");
				}
			}
		}

		if(journey.equalsIgnoreCase("dep")) {
			scenarioContext.setContext(Context.AVAILABILITY_DEP_LEG, journeyLegDetails);
		}else if(journey.equalsIgnoreCase("ret")) {
			scenarioContext.setContext(Context.AVAILABILITY_RET_LEG, journeyLegDetails);
		}else {
			//do noting code is not implemented
			throw new RuntimeException("Code is not written for connecting-through and through-connecting flights");
		}

	}

	private synchronized boolean getFlightTypeSeatPopUp(String seatType, String aircraftType, String strAdultCount) {
		//declare variable used in method
		boolean selectedFlightFlag = true;
		List<WebElement> allFlightLegs = flightAvailabilityPage.getFlightLegSeatMapLabel();

		//wait for page load
		WaitUtil.untilPageLoadComplete(driver);

		if(!seatType.equalsIgnoreCase("na")){
			List<WebElement> flightSeats;
			int totalSeatCount = 0;

			for(int legCount=0;legCount<allFlightLegs.size();legCount++){

				allFlightLegs.get(legCount).click();

				//wait for page load
				WaitUtil.untilPageLoadComplete(driver);

				if(seatType.equalsIgnoreCase("bigfront")){
					flightSeats = flightAvailabilityPage.getBFSSeatMapGridView();

					for(int seatCount=1;seatCount<flightSeats.size();seatCount++){
						if(!flightSeats.get(seatCount).getAttribute("class").contains("unavailable")){
							totalSeatCount = totalSeatCount + 1;
						}
					}

				}else if(!seatType.equalsIgnoreCase("premium")){
					flightSeats = flightAvailabilityPage.getPSSeatMapgridview();

					for(int seatCount=1;seatCount<flightSeats.size();seatCount++){
						if(!flightSeats.get(seatCount).getAttribute("class").contains("unavailable")){
							totalSeatCount = totalSeatCount + 1;
						}
					}
				}

				if(totalSeatCount > Integer.parseInt(strAdultCount)){
					selectedFlightFlag = selectedFlightFlag && true;
				}else {
					selectedFlightFlag = false;
				}
			}


		}else if(!aircraftType.equalsIgnoreCase("na")){
			if(flightAvailabilityPage.getAircraftTypeSeatMapLabel().getText().replace("Airbus", "").trim().toLowerCase().equals(aircraftType.toLowerCase())){
				selectedFlightFlag = true;
			}
		}
		//close Seat Popup
		flightAvailabilityPage.getCloseSeatMapButton().click();

		return selectedFlightFlag;
	}

	public synchronized void updateJourneyDates(String journey){
		String currDepDate = null;
		//wait for page load
		WaitUtil.untilPageLoadComplete(driver);

		//add the date difference to Ret date
		//calculate the date difference for Dep and Ret Date

		if(journey.equalsIgnoreCase("Dep")){

			System.out.println(flightAvailabilityPage.getSelectedDepDateText().getText());
			currDepDate =  flightAvailabilityPage.getSelectedDepDateText().getText().split(",",2)[1].trim();

			//convert the available date into date format
			Date date1 = TestUtil.convertStringToDate(currDepDate, "MMMM d, yyyy");

			//convert the return date into date format
			Date date2 = TestUtil.convertStringToDate(TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE), "MMMM d, yyyy"), "MMMM d, yyyy");

			//calculate the date difference between two dates
			long currDateDiff = Math.abs((date2.getTime() - date1.getTime())/ 1000 / 60 / 60 / 24);

			//set new Dep date
			scenarioContext.setContext(Context.HOMEPAGE_DEP_DATE, Long.toString(Long.parseLong(scenarioContext.getContext(Context.HOMEPAGE_DEP_DATE).toString()) + currDateDiff));

			//set new Dep date
			if(!scenarioContext.getContext(Context.HOMEPAGE_TRIP_TYPE).toString().equalsIgnoreCase("OneWay")){
				scenarioContext.setContext(Context.HOMEPAGE_ARR_DATE, Long.toString(Long.parseLong(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString()) + currDateDiff));
			}

		}else{
			currDepDate =  flightAvailabilityPage.getSelectedRetDateText().getText().split(",",2)[1].trim();

			//convert the available date into date format
			Date date1 = TestUtil.convertStringToDate(currDepDate, "MMMM d, yyyy");

			//convert the return date into date format
			Date date2 = TestUtil.convertStringToDate(TestUtil.getStringDateFormat(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE), "MMMM d, yyyy"), "MMMM d, yyyy");

			//calculate the date difference between two dates
			long currDateDiff = Math.abs((date2.getTime() - date1.getTime())/ 1000 / 60 / 60 / 24);

			//set new Dep date
			scenarioContext.setContext(Context.HOMEPAGE_ARR_DATE, Long.toString(Long.parseLong(scenarioContext.getContext(Context.HOMEPAGE_ARR_DATE).toString()) + currDateDiff));
		}
	}

	public synchronized void selectJourneyFare(String memberFareType) {

		//store red eye information
		getRedEyeFlightInformation("Dep");
		if(scenarioContext.isContains(Context.HOMEPAGE_ARR_DATE)){
			getRedEyeFlightInformation("Ret");
		}


		//store value in global variables
		//check the login type
		if(scenarioContext.isContains(Context.HOMEPAGE_LOGINTYPE)){
			//if(scenarioContext.getContext(Context.HOMEPAGE_LOGINTYPE).toString().equalsIgnoreCase("NineDFCEmail")){
			if(flightAvailabilityPage.getFareContainerPanel().size()==1){
				if(flightAvailabilityPage.getFareContainerPanel().get(0).getAttribute("class").contains("standard")){
					memberFareType = "Standard";
				}else if(flightAvailabilityPage.getFareContainerPanel().get(0).getAttribute("class").contains("fare-club")){
					memberFareType = "Member";
				}
			}
			//}
		}

		//loop through all prices on flight availability page
		for(WebElement contButton : flightAvailabilityPage.getFareContainerPanel()){
			//check for standard fare
			if(contButton.getAttribute("class").contains("standard")){
				if(!flightAvailabilityPage.getStandardFarePriceText().getText().contains("+")){
					//store standard fare in global variable
					scenarioContext.setContext(Context.AVAILABILITY_FS_TOTAL_PRICE,flightAvailabilityPage.getStandardFarePriceText().getText().replace("$","").trim());
				}else{
					String standardFare	= flightAvailabilityPage.getStandardFarePriceText().getText();

					//store standard fare in global variable
					scenarioContext.setContext(Context.AVAILABILITY_MILES_TOTAL_PRICE,standardFare.split("\\+")[0].replace("miles","").trim());

					//store standard fare in global variable
					scenarioContext.setContext(Context.AVAILABILITY_FS_TOTAL_PRICE,standardFare.split("\\+")[1].replace("$","").trim());
				}

			}else if(contButton.getAttribute("class").contains("fare-club")){
				//store member fare in global variable
				scenarioContext.setContext(Context.AVAILABILITY_9DFC_TOTAL_PRICE,flightAvailabilityPage.getMemberFarePriceText().getText().replace("$","").trim());

				//store member saving amount
				scenarioContext.setContext(Context.AVAILABILITY_9DFC_SAVEUPTO_PRICE,flightAvailabilityPage.getMemberFareSaveUpToPriceText().getText().trim());
			}
		}

		//loop through all flight blocks
		for(int flightBlock=0;flightBlock<flightAvailabilityPage.getSelectedFlightBlocksPanel().size();flightBlock++){
			if(flightBlock==0){
				//store departing flight price
				scenarioContext.setContext(Context.AVAILABILITY_DEP_FLIGHT_PRICE,flightAvailabilityPage.getDepartureFlightBlockPriceText().getText().replace("$","").trim());
			}else{
				//store return flight price
				scenarioContext.setContext(Context.AVAILABILITY_RET_FLIGHT_PRICE,flightAvailabilityPage.getArrivalFlightBlockPriceText().getText().replace("$","").trim());
			}
		}

		//check the spirit fare to be selected on FA page
		if(memberFareType.equalsIgnoreCase("Standard")) {
			//select standard fare
			flightAvailabilityPage.getStandardFareButton().click();

			//validate standard fare is selected
			ValidationUtil.validateTestStep("User selected Standard fare on Flight Availability Page", true);
		}else if(memberFareType.equalsIgnoreCase("Member")) {
			//select member fare
			flightAvailabilityPage.getMemberFareButton().click();

			//validate member fare is selected
			ValidationUtil.validateTestStep("User selected Member fare on Flight Availability Page", true);
		}else {

		}

		//set global variable for journey fare
		scenarioContext.setContext(Context.AVAILABILITY_JOURNEY_FARE,memberFareType);

		//wait for page load
		WaitUtil.untilPageLoadComplete(driver,(long)120);

		//call early departure Popup method
		selectEarlyDeparturePopup();

		//check no other popup appear on flight availability page
		if(pageObjectManager.getCommon().getPopupBlockPanel().size()==0) {
			//wait till passenger info page appear on web
			WaitUtil.untilPageURLVisible(driver, "book/passenger");
		}


	}

	public synchronized void selectJourneyUpgrade(String upgradeValue) {
		//wait for page load
		WaitUtil.untilPageLoadComplete(driver);
		//check passenger page not appear
		if(!driver.getCurrentUrl().contains("passenger")) {
			//check Upgrade And Save Pop Up appear on application
			if(flightAvailabilityPage.getBookItButton().size() > 0) {
				//check normal fare is selected
				if(upgradeValue.equalsIgnoreCase("BookIt")) {
					if(TestUtil.verifyElementDisplayed(flightAvailabilityPage.getBookItButton())){
						//click on standard fares
						flightAvailabilityPage.getBookItButton().get(0).click();
					}else{
						ValidationUtil.validateTestStep("Book It option is not available on Bare Fare popup on Flight Availability Page", false);
					}
				}else if(upgradeValue.equalsIgnoreCase("BoostIt")){
					if(TestUtil.verifyElementDisplayed(flightAvailabilityPage.getBoostItButton())){
						//click on Boost it
						//save boost it price
						scenarioContext.setContext(Context.AVAILABILITY_BOOSTIT_SAVEUPTO_PRICE,flightAvailabilityPage.getBoostItSaveUpToPriceText().getText().trim());
						flightAvailabilityPage.getBoostItButton().get(0).click();

					}else{
						ValidationUtil.validateTestStep("Boost It option is not available on Bare Fare popup on Flight Availability Page", false);
					}
				}else if(upgradeValue.equalsIgnoreCase("BundleIt")) {
					if(TestUtil.verifyElementDisplayed(flightAvailabilityPage.getBundleItButton())){
						//save bundle it price
						scenarioContext.setContext(Context.AVAILABILITY_BUNDLEIT_SAVEUPTO_PRICE,flightAvailabilityPage.getBundleItSaveUpToPriceText().getText().trim());
						//click on bundle fares
						flightAvailabilityPage.getBundleItButton().get(0).click();

					}else{
						ValidationUtil.validateTestStep("Bundle It option is not available on Bare Fare popup on Flight Availability Page", false);
					}
				}

				//wait for page load
				WaitUtil.untilPageLoadComplete(driver);


				WaitUtil.untilPageURLVisible(driver, "book/passenger");
			}else {
				//Do nothing as popup is not appeared
			}
		}else{
			if(!upgradeValue.equalsIgnoreCase("BookIt")){
				//verify Bare Fare popup
				ValidationUtil.validateTestStep("Bare Fare Popup is not appear on Flight Availability Page", false);
			}
		}
	}

	private synchronized void selectEarlyDeparturePopup() {
		//click on continue button of early departure Popup
		if(TestUtil.verifyElementDisplayed(driver,By.xpath(flightAvailabilityPage.xpath_EarlyDepartureHeaderText))){

			//Wait till page load is complete
			WaitUtil.untilPageLoadComplete(driver);

			//click on early deparure popup
			flightAvailabilityPage.getEarlyDepartureContinueButton().click();

			//Wait till page load is complete
			WaitUtil.untilPageLoadComplete(driver);
		}

	}

	public synchronized void selectSortingOption(String journey, String sortingOption) {
		//declare constant used in method
		final int FIRST_INDEX = 0;

		//select sorting options
		TestUtil.selectDropDownUsingVisibleText(flightAvailabilityPage.getSortByDropDown().get(FIRST_INDEX), sortingOption);

		//verify sorting option is selected
		ValidationUtil.validateTestStep("Verify User select Sorting option " + sortingOption + " for " + journey + " on Flight Availability Page", true);
	}




	private synchronized void getRedEyeFlightInformation(String journey){

		String redEyeFlights = null;

		if(journey.equalsIgnoreCase("Dep")){
			//for(WebElement flightPanel : flightAvailabilityPage.getSelectedFlightBlocksPanel()){
			//if(flightPanel.isDisplayed()){
			if(flightAvailabilityPage.getSelectedFlightBlocksPanel().get(0).getText().contains("next day") || flightAvailabilityPage.getSelectedFlightBlocksPanel().get(0).getText().contains("Próx. Día")){
				redEyeFlights = "1||";
			}else{
				redEyeFlights = "0||";
			}
			//}
			//}
		}else if(journey.equalsIgnoreCase("Ret")){
			//for(WebElement flightPanel : flightAvailabilityPage.getSelectedArrivalFlightBlockPanel()){
			//if(flightPanel.isDisplayed()){
			if(flightAvailabilityPage.getSelectedFlightBlocksPanel().get(1).getText().contains("next day") || flightAvailabilityPage.getSelectedFlightBlocksPanel().get(1).getText().contains("Próx. Día")){
				redEyeFlights = "1";
			}else{
				redEyeFlights = "0";
			}
			//}
			//}
		}

		//store red eye information in ev
		if(scenarioContext.isContains(Context.AVAILABILITY_RED_EYE)){
			scenarioContext.setContext(Context.AVAILABILITY_RED_EYE,scenarioContext.getContext(Context.AVAILABILITY_RED_EYE).toString() + redEyeFlights);
		}else{
			scenarioContext.setContext(Context.AVAILABILITY_RED_EYE,redEyeFlights);
		}
	}

	/**
	 * Rework version below
	 */

	/**
	 * This function clicks on the next arrow on the calendar block based on journey type
	 * @param journey Available input: "ret", "dep"
	 */
	private synchronized void clickNextWeekByJourneyType(String journey){
		if (journey.equalsIgnoreCase("dep")){
			//getCalNextArrowImage() has size of 6, 0,2 contains departure flight arrow for web and mobile version
			if (flightAvailabilityPage.getCalNextArrowImage().get(0).isDisplayed()){
				flightAvailabilityPage.getCalNextArrowImage().get(0).click();
			}else if (flightAvailabilityPage.getCalNextArrowImage().get(2).isDisplayed()){
				flightAvailabilityPage.getCalNextArrowImage().get(2).click();
			}
		}else if (journey.equalsIgnoreCase("ret")){
			//getCalNextArrowImage() has size of 6, 3,5 contains returning flight arrow for web and mobile version
			if (flightAvailabilityPage.getCalNextArrowImage().get(3).isDisplayed()){
				flightAvailabilityPage.getCalNextArrowImage().get(3).click();
			}else if (flightAvailabilityPage.getCalNextArrowImage().get(5).isDisplayed()){
				flightAvailabilityPage.getCalNextArrowImage().get(5).click();
			}
		}
	}

	/**
	 * This function select flight based on flight number
	 * @param journey Acceptable input: "ret", "dep"
	 * @param flightNumber Acceptable input: String
	 */
	public synchronized void selectFlightNumberTypeTest(String journey, String flightNumber){
		// determine the weeks this method will look through
		final int weeksToLoopThrough = 3;
		WaitUtil.untilPageLoadComplete(driver);
		WaitUtil.untilTimeCompleted(2000);
		if (selectFlightNumberTypeOnCurrentDate(journey,flightNumber)){
			return;
		}
		if (journey.equalsIgnoreCase("dep")){
			for (int week = 0; week < weeksToLoopThrough; week++){
				WaitUtil.untilPageLoadComplete(driver);
				for(int date = 0; date < flightAvailabilityPage.getDepCalBlocksGridView().size(); date ++){
					flightAvailabilityPage.getDepCalBlocksGridView().get(date).click();
					WaitUtil.untilPageLoadComplete(driver);
					if(selectFlightNumberTypeOnCurrentDate(journey,flightNumber)){
						return;
					}
				}
				clickNextWeekByJourneyType("dep");
			}
		}else if (journey.equalsIgnoreCase("ret")) {
			for (int week = 0; week < weeksToLoopThrough; week++) {
				WaitUtil.untilPageLoadComplete(driver);
				for (int date = 0; date < flightAvailabilityPage.getDepCalBlocksGridView().size(); date++) {
					flightAvailabilityPage.getArrCalBlocksGridView().get(date).click();
					WaitUtil.untilPageLoadComplete(driver);
					if (selectFlightNumberTypeOnCurrentDate(journey, flightNumber)) {
						return;
					}
				}

				clickNextWeekByJourneyType("ret");
			}
		}else{
			throw new IllegalArgumentException("Invalid journey type input");
		}
		ValidationUtil.validateTestStep("No suitable flight found for type: " + flightNumber, false);
	}

	/**
	 * This function select flight based on fare type input
	 * @param journey Available input: "ret", "dep"
	 * @param fareType acceptable input: "9DFC", "Standard"
	 */
	public synchronized void selectFlightFareTypeTest(String journey, String fareType){
		final int weeksToLoopThrough = 8;
		WaitUtil.untilPageLoadComplete(driver);
		WaitUtil.untilTimeCompleted(2000);

		if (selectFlightFareTypeOnCurrentDate(journey,fareType)){
			return;
		}
		if (journey.equalsIgnoreCase("dep")){
			for (int week = 0; week < weeksToLoopThrough; week++){
				WaitUtil.untilPageLoadComplete(driver);
				//looking for which calendar block has the 9FC icon visible
				for(int date = 0; date < flightAvailabilityPage.getDepCalBlocksGridView().get(date).findElements(By.xpath(".//span[(contains(text(),'9FC')) and not (contains(@class,'invisible'))]")).size(); date ++){
					flightAvailabilityPage.getDepCalBlocksGridView().get(date).findElements(By.xpath(".//span[(contains(text(),'9FC')) and not (contains(@class,'invisible'))]")).get(date).click();
					WaitUtil.untilPageLoadComplete(driver);
					if(selectFlightFareTypeOnCurrentDate(journey,fareType)){
						return;
					}
				}
				clickNextWeekByJourneyType("dep");
			}
		}else if (journey.equalsIgnoreCase("ret")) {
			for (int week = 0; week < weeksToLoopThrough; week++) {
				WaitUtil.untilPageLoadComplete(driver);
				//looking for which calendar block has the 9FC icon visible
				for (int date = 0; date < flightAvailabilityPage.getArrCalBlocksGridView().get(date).findElements(By.xpath(".//span[(contains(text(),'9FC')) and not (contains(@class,'invisible'))]")).size(); date++) {
					flightAvailabilityPage.getArrCalBlocksGridView().get(date).findElements(By.xpath(".//span[(contains(text(),'9FC')) and not (contains(@class,'invisible'))]")).get(date).click();
					WaitUtil.untilPageLoadComplete(driver);
					if (selectFlightFareTypeOnCurrentDate(journey, fareType)) {
						return;
					}
				}
				clickNextWeekByJourneyType("ret");
			}
		}else{
			throw new IllegalArgumentException("Invalid journey type input");
		}
		ValidationUtil.validateTestStep("No suitable flight found for type: " + fareType, false);
	}

	/**
	 * This function select flight based on nature type input
	 * @param journey Available input: "ret", "dep"
	 * @param natureType Available input: "connecting", "through", "Nonstop", "autoSelect", "connecting-through", "through-connecting"
	 */
	public synchronized void selectFlightNatureTypeTest(String journey, String natureType){
		// determine the weeks this method will look through
		final int weeksToLoopThrough = 3;
		WaitUtil.untilPageLoadComplete(driver);
		WaitUtil.untilTimeCompleted(2000);
		if (selectFlightNatureTypeOnCurrentDate(journey,natureType)){
			return;
		}
		if (journey.equalsIgnoreCase("dep")){
			for (int week = 0; week < weeksToLoopThrough; week++){
				WaitUtil.untilPageLoadComplete(driver);
				for(int date = 0; date < flightAvailabilityPage.getDepCalBlocksGridView().size(); date ++){
					flightAvailabilityPage.getDepCalBlocksGridView().get(date).click();
					WaitUtil.untilPageLoadComplete(driver);
					if(selectFlightNatureTypeOnCurrentDate(journey,natureType)){
						return;
					}
				}
				clickNextWeekByJourneyType("dep");
			}
		}else if (journey.equalsIgnoreCase("ret")) {
			for (int week = 0; week < weeksToLoopThrough; week++) {
				WaitUtil.untilPageLoadComplete(driver);
				for (int date = 0; date < flightAvailabilityPage.getDepCalBlocksGridView().size(); date++) {
					flightAvailabilityPage.getArrCalBlocksGridView().get(date).click();
					WaitUtil.untilPageLoadComplete(driver);
					if (selectFlightNatureTypeOnCurrentDate(journey, natureType)) {
						return;
					}
				}
				clickNextWeekByJourneyType("ret");
			}
		}else{
			throw new IllegalArgumentException("Invalid journey type input");
		}


		ValidationUtil.validateTestStep("No suitable flight found for type: " + natureType, false);
	}

	/**
	 * This function selects the flight with corresponding flight number on current selected date
	 * @param journey available input: "dep", "ret"
	 * @param flightNumber Available input: "connecting", "through", "Nonstop", "autoSelect", "connecting-through", "through-connecting"
	 */
	public synchronized boolean selectFlightNumberTypeOnCurrentDate(String journey, String flightNumber){
		List<WebElement> potentialListOfFlightCards = getPotentialListOfFlightCardsWithFlightNumber(journey);
		for (WebElement flightCard: potentialListOfFlightCards){
			if (selectFlightCardWithFlightNumberType(journey, flightCard, flightNumber)){
				return true;
			}
		}
		return false;
	}

	/**
	 * This function selects the flight with corresponding flight type on current selected date
	 * @param journey available input: "dep", "ret"
	 * @param natureType Available input: "connecting", "through", "Nonstop", "autoSelect", "connecting-through", "through-connecting"
	 */
	public synchronized boolean selectFlightNatureTypeOnCurrentDate(String journey, String natureType){
		List<WebElement> potentialListOfFlightCards = getPotentialListOfFlightCardsWithFlightType(journey,natureType);
		for (WebElement flightCard: potentialListOfFlightCards){
			if (selectFlightCardWithNatureType(journey, flightCard, natureType)){
				return true;
			}
		}
		return false;
	}

	/**
	 * This function selects the flight with corresponding fareType on current selected date
	 * @param journey available input: "dep", "ret"
	 * @param fareType available input: "9DFC", "Standard"
	 */
	public synchronized boolean selectFlightFareTypeOnCurrentDate(String journey, String fareType){
		List<WebElement> potentialListOfFlightCards = getPotentialListOfFlightCardsWithFareType(journey,fareType);
		for (WebElement flightCard: potentialListOfFlightCards){
			if (selectFlightCardWithFareType(journey, flightCard, fareType)){
				return true;
			}
		}
		return false;
	}


	/**
	 * This function stores the List of stop buttons depends on journey
	 * @param journey available input: "dep", "ret"
	 * @return ArrayList<WebElement> of stop buttons of journey type
	 */
	private synchronized List<WebElement> getJourneyListOfStopButtons(String journey){
		if (journey.equalsIgnoreCase("dep")){
			return new ArrayList<>(flightAvailabilityPage.getDepartingNumebrOfStopsButton());
		}else if (journey.equalsIgnoreCase("ret")){
			return new ArrayList<>(flightAvailabilityPage.getReturningNumebrOfStopsButton());
		}else{
			throw new IllegalArgumentException("Invalid input for journey");
		}
	}

	/**
	 * This function stores the List of flight cards depends on journey
	 * @param journey available input: "dep", "ret"
	 * @return ArrayList<WebElement> of flight cards of journey type
	 */
	private synchronized List<WebElement> getJourneyListOfFlightCards(String journey){
		if (journey.equalsIgnoreCase("dep")){
			// xpath (//div[@class="d-flex flex-column"])[1] for departing
			return new ArrayList<>(driver.findElements(By.xpath("(//div[@class=\"d-flex flex-column\"])[1]//div//app-journey")));
		}else if (journey.equalsIgnoreCase("ret")){
			// xpath (//div[@class="d-flex flex-column"])[2] for returning
			return new ArrayList<>(driver.findElements(By.xpath("(//div[@class=\"d-flex flex-column\"])[2]//div//app-journey")));
		}else{
			throw new IllegalArgumentException("Invalid input for journey");
		}
	}

	/**
	 * This function takes the flight type and return the list of potential suitable WebElements for flight selection
	 * @param journey
	 * @param flightType Available input: "connecting", "through", "Nonstop", "autoSelect", "connecting-through", "through-connecting"
	 * @return list of potential elements for corresponding flight type
	 */
	private synchronized List<WebElement> getPotentialListOfFlightCardsWithFlightType(String journey, String flightType){
		List<WebElement> potentialFlightCards = new ArrayList<>();
		List<WebElement> journeyListOfStopButtons = getJourneyListOfStopButtons(journey);
		List<WebElement> journeyListOfFlightCards = getJourneyListOfFlightCards(journey);

		//Find the potential flight cards, and add them to the potential list
		for (int i = 0; i < journeyListOfStopButtons.size(); i++){
			if (flightType.equalsIgnoreCase("connecting") || flightType.equalsIgnoreCase("through")){
				if(journeyListOfStopButtons.get(i).getText().equalsIgnoreCase("1 Stop")){
					potentialFlightCards.add(journeyListOfFlightCards.get(i));
				}
			}else if (flightType.equalsIgnoreCase("Nonstop")){
				if(journeyListOfStopButtons.get(i).getText().equalsIgnoreCase("Nonstop")){
					potentialFlightCards.add(journeyListOfFlightCards.get(i));
				}
			}else if (flightType.equalsIgnoreCase("autoSelect")) {
				potentialFlightCards.add(journeyListOfFlightCards.get(i));
			}else if (flightType.equalsIgnoreCase("connecting-through") || flightType.equalsIgnoreCase("through-connecting")){
				if(journeyListOfStopButtons.get(i).getText().equalsIgnoreCase("2 Stops")){
					potentialFlightCards.add(journeyListOfFlightCards.get(i));
				}
			}else{
				throw new IllegalArgumentException("Invalid input for flightType");
			}
		}
		return potentialFlightCards;
	}

	private synchronized List<WebElement> getPotentialListOfFlightCardsWithFlightNumber(String journey){
		List<WebElement> potentialFlightCards = new ArrayList<>();
		List<WebElement> journeyListOfStopButtons = getJourneyListOfStopButtons(journey);
		List<WebElement> journeyListOfFlightCards = getJourneyListOfFlightCards(journey);

		//Find the potential flight cards, and add them to the potential list
		for (int i = 0; i < journeyListOfStopButtons.size(); i++){
			if(journeyListOfStopButtons.get(i).getText().equalsIgnoreCase("1 Stop") || journeyListOfStopButtons.get(i).getText().equalsIgnoreCase("Nonstop")){
				potentialFlightCards.add(journeyListOfFlightCards.get(i));
			}
		}
		return potentialFlightCards;
	}
	/**
	 * This function takes the flight type and return the list of potential suitable WebElements for flight selection
	 * @param journey
	 * @param fareType Available input: "Standard", "9DFC"
	 * @return list of potential elements for corresponding flight type
	 */
	private synchronized List<WebElement> getPotentialListOfFlightCardsWithFareType(String journey, String fareType){
		List<WebElement> potentialFlightCards = new ArrayList<>();
		List<WebElement> journeyListOfFlightCards = getJourneyListOfFlightCards(journey);
		driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
		if (fareType.equalsIgnoreCase("9DFC")){
			for (WebElement flightCard: journeyListOfFlightCards){
				if (flightCard.findElements(By.xpath(".//label[contains(@for,'club')]")).size() > 0){
					potentialFlightCards.add(flightCard);
				}
			}
		}else if (fareType.equalsIgnoreCase("Standard")){
			for (WebElement flightCard: journeyListOfFlightCards){
				if (flightCard.findElements(By.xpath(".//label[contains(@for,'standard')]")).size() > 0){
					potentialFlightCards.add(flightCard);
				}
			}
		}

		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWait(), TimeUnit.SECONDS);
		return potentialFlightCards;
	}
	/**
	 * This function return true if flight fare type is available for selection
	 * @param journey Available input: "dep","ret"
	 * @param flightCard WebElement for the flight card
	 * @param fareType Acceptable parameters - standard, 9DFC
	 * @return true: flight selected, false: flight not selected
	 */
	public synchronized boolean selectFlightCardWithFareType(String journey, WebElement flightCard, String fareType){
		if (fareType.equalsIgnoreCase("standard")){
			return clickToSelectFlightCardByFareType(flightCard,"standard");
		}else if(fareType.equalsIgnoreCase("9DFC")){
			return clickToSelectFlightCardByFareType(flightCard,"9DFC");
		}else{
			throw new IllegalArgumentException("This function only accept fareType = standard or 9DFC");
		}
	}

	/**
	 * This function return true if flight nature type is available for selection
	 * @param journey Available input: "dep","ret"
	 * @param flightCard WebElement for the flight card
	 * @param natureType acceptable parameters: "connecting", "through", "nonstop", "connecting-through", "through-connecting", "autoselect"
	 * @return true: flight selected, false: flight not selected
	 */
	public synchronized boolean selectFlightCardWithNatureType(String journey, WebElement flightCard, String natureType){
		WaitUtil.untilPageLoadComplete(driver);

		List<WebElement> stopButtons = flightCard.findElements(By.xpath(".//button"));
		if (stopButtons.get(0).isDisplayed()){ //for Web version
			stopButtons.get(0).click();
		}else if (stopButtons.get(2).isDisplayed()){
			stopButtons.get(2).click();
		}
		// if the flight matches the nature type
		if (getFlightTypeStopPopUp(journey,natureType,"NA")){
			return clickToSelectFlightCardByFareType(flightCard,"standard");

		}
		return false;
	}

	/**
	 * This function return true if flight number type is available for selection
	 * @param journey Available input: "dep","ret"
	 * @param flightCard WebElement for the flight card
	 * @param FlightNumber Flight number being selected
	 * @return
	 */
	public synchronized boolean selectFlightCardWithFlightNumberType(String journey, WebElement flightCard, String FlightNumber){
		WaitUtil.untilPageLoadComplete(driver);

		List<WebElement> stopButtons = flightCard.findElements(By.xpath(".//button"));
		if (stopButtons.get(0).isDisplayed()){ //for Web version
			stopButtons.get(0).click();
		}else if (stopButtons.get(2).isDisplayed()){
			stopButtons.get(2).click();
		}
		// if the flight matches the nature type
		if (getFlightTypeStopPopUp(journey,"NA",FlightNumber)){
			return clickToSelectFlightCardByFareType(flightCard,"standard");
		}
		return false;
	}

	/**
	 * This function select flight based on fare type input - note: "auto" select 9DFC if standard is not available
	 * @param flightCard WebElement flight card for selection
	 * @param fareType Available input: "standard", "9DFC" or "auto"
	 * @return return true if the flight is successfully selected
	 */
	private synchronized boolean clickToSelectFlightCardByFareType(WebElement flightCard, String fareType){
		WaitUtil.untilPageLoadComplete(driver);
		List<WebElement> flightPrices = null;
		//If the flight type matches the nature type, select the standard price for the flight
		if (fareType.equalsIgnoreCase("Standard")){
			flightPrices = flightCard.findElements(By.xpath(".//label[contains(@for,'standard')]"));
		}else if(fareType.equalsIgnoreCase("9DFC")){
			flightPrices = flightCard.findElements(By.xpath(".//label[contains(@for,'club')]"));
		}
		//The for loops take cares of hidden elements due for web/mobile version
		for (WebElement priceTag: flightPrices){
			if (priceTag.isDisplayed()){
				//TODO:: store flight information
				priceTag.click();
				WaitUtil.untilPageLoadComplete(driver);
				//check if after selection the block is collapsed
				ValidationUtil.validateTestStep("Flight was selected",
						!priceTag.isDisplayed());
				return true;
			}
		}
		return false;
	}
}